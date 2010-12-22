package org.rivu.image.task;

import java.util.HashMap;
import java.util.Map;


public class OutputTextFormat {
	private String id ;
	private String text ;
	private JobDetail job ;
	private String title ;
	private String parent ;
	private String path ;
	private Object data ;
	private String type ;
	private String contentType ;
	private int length = 0;
	private boolean split = false ;
	private Map<String,String> property = new HashMap<String,String>();
	public OutputTextFormat(){}
	public OutputTextFormat(Object data , String id){
		this.data = data ;
		this.id = id ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public JobDetail getJob() {
		return job;
	}
	public void setJob(JobDetail job) {
		this.job = job;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean isSplit() {
		return split;
	}
	public void setSplit(boolean split) {
		this.split = split;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Map<String, String> getProperty() {
		return property;
	}
	public void setProperty(Map<String, String> property) {
		this.property = property;
	}
	
}
