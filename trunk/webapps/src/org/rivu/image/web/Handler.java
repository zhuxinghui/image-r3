package org.rivu.image.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rivu.image.core.RivuContext;
import org.rivu.image.task.JobDetail;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Web
 * @author iceworld
 *
 */
public class Handler extends ActionSupport implements ServletHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String msg = null;
	private String namespace ;
	private String actionName ;

	public void setAction(String namespace, String action) {
		this.namespace = namespace.startsWith("/")?namespace.substring(1):namespace ;
		this.actionName = action ;
	}

	public void setServletRequest(HttpServletRequest request) throws Exception {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response)
			throws Exception {
		this.response = response ;
	}
	/**
	 * 
	 * @return
	 */
	public RuntimeData getRuntime(){
		return new RuntimeData() ;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public User getUser(){
		return new User();
	}
	public List<JobDetail> getJobList(){
		return RivuContext.jobList ;
	}
	public boolean isChange(){
		return RivuContext.jobChange ;
	}
}
