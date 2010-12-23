package org.rivu.image.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rivu.image.core.RivuContext;
import org.rivu.image.task.JobDetail;
import org.rivu.image.task.Reporter;
import org.rivu.image.tools.db.GenDAO;
import org.rivu.image.tools.db.RivuData;

import com.opensymphony.xwork2.Action;
import com.sleepycat.persist.EntityCursor;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class CrawlTaskHandler extends Handler{
	int p = 1 ; //page no
	int ps = 5 ; //page size
	int t = 0 ; //total page
	List<RivuData> dataList = new ArrayList<RivuData>() ;
	private RivuData data ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @return
	 */
	public String list(){
		EntityCursor<RivuData> cursor = GenDAO.getDataByType("task") ;
		try{
			RivuData rivuData = cursor.first() ;
			if(rivuData!=null){
				int count = cursor.count() ;
				t = count;
				int start = (p-1)*ps ;
				if(start>0){
					for(int i=0 ; i<start ; i++){
						cursor.next() ;
					}
				}else{
					dataList.add(rivuData) ;
					start = start+1 ;
				}
				for(int i= start; i<p*ps && i<count; i++){
					dataList.add(cursor.next()) ;
				}
				for(JobDetail job : RivuContext.jobList){
					for(RivuData rivu : dataList){
						if(rivu.getId().equals(job.getId())){
							rivu.setTaskstatus("2") ;
						}
					}
				}
			}
		}finally{
			if(cursor!=null){
				cursor.close() ;
			}
		}
		cursor.close();
		return Action.SUCCESS ;
	}
	/**
	 * Ìí¼ÓÈÎÎñ
	 * @return
	 */
	public String add(){
		if(data!=null && data.getResource()!=null && data.getResource().length()>0){
			if(data.getId()==null){
				data.setType("task") ;
				data.setId(String.valueOf(System.nanoTime())) ;
			}
			GenDAO.saveRivuData(data) ;
			GenDAO.sync();
		}
		return list() ;
	}
	/**
	 * É¾³ý
	 */
	public String rm(){
		if(data!=null && data.getId()!=null){
			GenDAO.clearByID(data.getId()) ;
			GenDAO.sync();
		}
		return list();
	}
	
	/**
	 * É¾³ý
	 */
	public String change(){
		if(RivuContext.jobChange){
			RivuContext.jobChange = false ;
		}
		return list();
	}
	public String run(){
		if(data!=null && data.getId()!=null){
			data = GenDAO.getDataByID(data.getId()) ;
			data.setTaskstatus("2") ;
			JobDetail job = new JobDetail();
			job.setId(data.getId()) ;
			job.setClazz("org.rivu.image.resource.LocalFileResource") ; 
			job.setName(data.getResource()) ;
			job.setSource(data.getResource()) ;
			job.setReport(new Reporter());
			job.getReport().setStarttime(new Date()) ;
			job.setTasktype("localfile") ;
			job.setTaskid(data.getId()) ;
			RivuContext.jobList.add(job) ;
		} 
		
		return list();
	}
	
	public List<RivuData> getDataList() {
		return dataList;
	}
	public void setDataList(List<RivuData> dataList) {
		this.dataList = dataList;
	}
	public RivuData getData() {
		return data;
	}
	public void setData(RivuData data) {
		this.data = data;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	
}
