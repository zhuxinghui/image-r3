package org.rivu.image.web;

import java.io.IOException;
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
	protected int p = 1;

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
	
	protected String sendError(String msg) throws IOException{
		StringBuffer strb = new StringBuffer() ;
		for(int i=0 ; i<msg.length() ; i++){
			if(msg.charAt(i)>=19968 && msg.charAt(i)<=40959){
				strb.append("&#").append((int)msg.charAt(i)).append(";") ;
			}else{
				strb.append(msg.charAt(i)) ;
			}
		}
		response.addHeader("emsg", strb.toString()) ;
		response.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html,charset=UTF-8") ;
		response.sendError(400,msg);
		
		return null ;
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

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}
	
}
