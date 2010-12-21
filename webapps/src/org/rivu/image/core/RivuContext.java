package org.rivu.image.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.rivu.image.task.JobDetail;


public class RivuContext {
	public static List<JobDetail> jobList = new ArrayList<JobDetail>();
	public static String IMAGE_FILE = "jpg,jpeg,gif,png,bmp" ;
	private static org.apache.solr.core.SolrCore core = null ;
	
	@SuppressWarnings("deprecation")
	public static SolrCore getCore(){
		return core!=null ? core : (core = org.apache.solr.core.SolrCore.getSolrCore());
	}
}
