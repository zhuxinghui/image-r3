package org.rivu.image.task;

import java.util.Date;


public class JobDetail {
	private String id ;
	private String name ;
	private String clazz ;
	private String taskid ;
	private String tasktype ; //file:netfile:ftp:pop3:oracle...
	private boolean plantask = false;    // Auto Exec
	private String source ;   //File source eg:e:\document
	private Date taskfiretime ;
	private String crawltaskid ;
	private boolean fetcher = true ;
	private boolean pause ;
	private boolean plantaskreadtorun = false ;
	private boolean run = false ;
	private Reporter report ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	public boolean isPlantask() {
		return plantask;
	}
	public void setPlantask(boolean plantask) {
		this.plantask = plantask;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getTaskfiretime() {
		return taskfiretime;
	}
	public void setTaskfiretime(Date taskfiretime) {
		this.taskfiretime = taskfiretime;
	}
	public String getCrawltaskid() {
		return crawltaskid;
	}
	public void setCrawltaskid(String crawltaskid) {
		this.crawltaskid = crawltaskid;
	}
	public boolean isFetcher() {
		return fetcher;
	}
	public void setFetcher(boolean fetcher) {
		this.fetcher = fetcher;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public boolean isPlantaskreadtorun() {
		return plantaskreadtorun;
	}
	public void setPlantaskreadtorun(boolean plantaskreadtorun) {
		this.plantaskreadtorun = plantaskreadtorun;
	}
	public Reporter getReport() {
		return report;
	}
	public void setReport(Reporter report) {
		this.report = report;
	}
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	
}
