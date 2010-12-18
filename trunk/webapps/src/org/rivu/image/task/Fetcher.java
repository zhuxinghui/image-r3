package org.rivu.image.task;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
			core = new CoreContainer().getCore(null);
			rivuRequest = new RivuQueryRequest(core);
			processorChain = core.getUpdateProcessingChain("");
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
						if(obj.getId()!=null && obj.getData()!=null)
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
			pages.incrementAndGet();
			if(true){
				try {
					bytes.addAndGet(1024);
					OutputTextFormat outputTextFormat = resource.getText(object);
					SolrInputDocument solrDoc = new SolrInputDocument();
					{
						String id = resource.getId(object);
						if (id == null || "".equals(id))
							return;
						solrDoc.addField("id", id);
						solrDoc.addField("datatype", "");
						solrDoc.addField("size", "");
						solrDoc.addField("width", "");
						solrDoc.addField("height", "");
						solrDoc.addField("taskid", "");
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
					if(pages.intValue()%50000==0)
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
			for (int i = 0; i < 50; i++) { // spawn
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