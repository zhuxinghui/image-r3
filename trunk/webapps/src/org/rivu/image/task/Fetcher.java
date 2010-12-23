package org.rivu.image.task;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.lucene.document.Document;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrConfig;
import org.apache.solr.core.SolrCore;
import org.apache.solr.schema.IndexSchema;
import org.apache.solr.update.AddUpdateCommand;
import org.apache.solr.update.CommitUpdateCommand;
import org.apache.solr.update.DocumentBuilder;
import org.apache.solr.update.processor.UpdateRequestProcessor;
import org.apache.solr.update.processor.UpdateRequestProcessorChain;
import org.rivu.image.core.RivuContext;
import org.rivu.image.tools.RivuTools;

public class Fetcher implements Runnable {
	private static SolrCore core = null;
	private static RivuQueryRequest rivuRequest = null;
	private static UpdateRequestProcessorChain processorChain = null;
	private static UpdateRequestProcessor processor = null;

	public static SolrCore getCore() {
		return core;
	}

	public static void setCore(SolrCore core) {
		Fetcher.core = core;
	}
	static {
		try {
			rivuRequest = new RivuQueryRequest(RivuContext.getCore());
			processorChain = RivuContext.getCore().getUpdateProcessingChain("");
			processor = processorChain.createProcessor(rivuRequest, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JobDetail job = null;
	private Resource resource = null;

	private long start = System.currentTimeMillis(); // start time of fetcher
	private AtomicInteger activeThreads = new AtomicInteger(0);
	private AtomicLong bytes = new AtomicLong(0); // total bytes fetched
	private AtomicInteger pages = new AtomicInteger(0); // total pages fetched
	private AtomicInteger errors = new AtomicInteger(0); // total pages errored
	private AtomicInteger duppage = new AtomicInteger(0); // total pages duplicate

	public Fetcher(JobDetail job) {
		this.job = job;
		job.setRun(true) ;
		try {
			resource = Resource.getResource(job);
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			job.getReport().setErrors(1) ;
			resource = null ;
		}catch(NoSuchMethodException ex){
			job.getReport().setErrors(1) ;
			resource = null ;
		}catch (Exception e1) {
			e1.printStackTrace();
			if (e1.getMessage() != null
					&& job.getReport().getErrormsg() == null) {
				job.getReport().setStatus(e1.getMessage()) ;
				job.getReport().setErrormsg(e1.getMessage());
			}
		}
	}

	/**
	 * This class picks items from queues and fetches the pages.
	 */
	private class FetcherThread extends Thread {
		public FetcherThread(ThreadGroup group, String name) {
			super(group, name);
		}

		public void run() {
			activeThreads.incrementAndGet(); // count threads
			try {
				reportStatus();
				OutputTextFormat obj;
				while (job.isFetcher() && resource!=null && (obj = resource.next()) != null ) {
					try {
						while(job.isPause()){
							Thread.sleep(1000) ;
						}
						if(obj.getData()!=null)
							output(obj);
					} catch (Throwable t) { // unexpected exception
						t.printStackTrace();
						logError(t.toString());
					}
				}
				/**
		         * 
		         */
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				activeThreads.decrementAndGet(); // count threads
			}
		}

		private void logError(String message) {
			errors.incrementAndGet();
			job.getReport().setErrors(errors.intValue());
		}
		
		private void output(OutputTextFormat object) {
			if(RivuTools.add(object.getId() , job.getId())){
				try {
					OutputTextFormat outputTextFormat = resource.getText(object);
					SolrInputDocument solrDoc = new SolrInputDocument();
					{
						String id = resource.getId(object);
						if (id == null || "".equals(id))
							return;
						pages.incrementAndGet();
						bytes.addAndGet(outputTextFormat.getLength());
						reportStatus();
						solrDoc.addField("id", id);
						solrDoc.addField("datatype", job.getTasktype());
						solrDoc.addField("size",outputTextFormat.getLength());
						solrDoc.addField("width", outputTextFormat.getProperty().get("width")!=null?outputTextFormat.getProperty().get("width"):0);
						solrDoc.addField("height", outputTextFormat.getProperty().get("height")!=null?outputTextFormat.getProperty().get("height"):0);
						solrDoc.addField("title", outputTextFormat.getTitle()!=null?outputTextFormat.getTitle():"");
						solrDoc.addField("taskid", job.getTaskid());
						Document document = (Document)outputTextFormat.getData() ;
						solrDoc.addField("dsc", document.getField("dsc")!=null?document.getField("dsc").stringValue():"");
						solrDoc.addField("dcl", document.getField("dcl")!=null?document.getField("dcl").stringValue():"");
						solrDoc.addField("deh", document.getField("deh")!=null?document.getField("deh").stringValue():"");
//						solrDoc.addField("facc", document.getField("facc")!=null?document.getField("facc").stringValue():"");
//						solrDoc.addField("fch", document.getField("fch")!=null?document.getField("fch").stringValue():"");
						solrDoc.addField("cedd", document.getField("cedd")!=null?document.getField("cedd").stringValue():"");
						solrDoc.addField("fcth", document.getField("fcth")!=null?document.getField("fcth").stringValue():"");
						solrDoc.addField("tamura", document.getField("tamura")!=null?document.getField("tamura").stringValue():"");
						solrDoc.addField("fg", document.getField("fg")!=null?document.getField("fg").stringValue():"");
						solrDoc.addField("fs", document.getField("fs")!=null?document.getField("fs").stringValue():"");
						solrDoc.addField("fsh", document.getField("fsh")!=null?document.getField("fsh").stringValue():"");
						solrDoc.addField("dii", document.getField("dii")!=null?document.getField("dii").stringValue():"");
						
						AddUpdateCommand addCmd = new AddUpdateCommand();
						addCmd.allowDups = false;
						addCmd.commitWithin = -1;
						addCmd.overwriteCommitted = false;
						addCmd.overwritePending = false;
						addCmd.solrDoc = solrDoc;
						synchronized (processor) {
							processor.processAdd(addCmd);
						}
					}
					if(pages.intValue()%5000==0)
					{
						commit();
					}
					reportStatus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				duppage.incrementAndGet();
			}
		}

		/**
		 * 
		 * @return
		 */
		private boolean isAvaMemory() {
			Runtime runtime = java.lang.Runtime.getRuntime();
			return ((float) (runtime.totalMemory() - runtime.freeMemory()) / (float) runtime
					.totalMemory()) > 0.60 ? true : false;
		}

		/**
		 * 
		 * @throws IOException
		 */
		private void reportStatus() throws IOException {
			String status;
			long elapsed = (System.currentTimeMillis() - start) / 1000;
			StringBuffer strb = new StringBuffer() ;
			status = strb.append(activeThreads)
					.append( " threads, ")
					.append( pages)
					.append( " pages, ")
					.append( duppage)
					.append( " duplicate, ")
					.append( errors)
					.append( " errors, ")
					.append( Math.round(((float) pages.get() * 10) / elapsed)
					/ 10.0)
					.append( " pages/s, ")
					.append( Math
							.round(((((float) bytes.get()) * 8) / 1024)
									/ elapsed) ).append( " kb/s, ").toString();
			job.getReport().setStatus(status);
			job.getReport().setPages(pages.intValue());
			job.getReport().setType(job.getTasktype());
			if (elapsed != 0)
				job.getReport().setSpeed(
						(float) ((pages.get() * 10) / elapsed) / 10.0);
			job.getReport().setBytes(bytes.longValue());
			if (elapsed != 0)
				job.getReport().setBytespeed(bytes.get() * 8 / 1024 / elapsed);
			job.getReport().setEndtime(new java.util.Date());
			// RivuContext.getLogger(this.getClass()).info(status) ;
		}

	}

	private static final String THREAD_GROUP_NAME = "crawl";

	public void run() {
		if (resource != null) {
			ThreadGroup threadGroup = new ThreadGroup(job.getName()+"_"+THREAD_GROUP_NAME);
			job.getReport().setStarttime(new java.util.Date());
			for (int i = 0; i < 10; i++) { // spawn
				new FetcherThread(threadGroup, job.getName()+"_"+THREAD_GROUP_NAME + i).start();
			}

			while (true) {
				boolean noMoreThread = true;
				int n = threadGroup.activeCount();
				Thread[] threadList = new Thread[n];
				threadGroup.enumerate(threadList);
				for (int i = 0; i < n; i++) {
					if (threadList[i] == null)
						continue;
					String tname = threadList[i].getName();
					if (tname.startsWith(job.getName()+"_"+THREAD_GROUP_NAME)) // prove it
					{
						noMoreThread = false;
						break;
					}
				}
				if (noMoreThread) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			commit();
			try {
				resource.close();
				RivuContext.jobList.remove(job) ; //É¾³ýÈÎÎñ
				RivuContext.jobChange = true ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void commit(){
		try {
			if(processor!=null){
				synchronized (processor) {
					processor.processCommit(new CommitUpdateCommand(true));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}