package org.rivu.image.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.core.SolrCore;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.schema.IndexSchema;
import org.apache.solr.search.SolrIndexSearcher;

public class RivuQueryRequest implements SolrQueryRequest{
	
	private SolrCore core ;
	private SolrParams params  ;
	public RivuQueryRequest(){}
	public RivuQueryRequest(SolrCore core){
		this.core = core ;
	}
	public void close() {
		// TODO Auto-generated method stub
		
	}

	public Iterable<ContentStream> getContentStreams() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Object, Object> getContext() {
		// TODO Auto-generated method stub
		return new java.util.HashMap();
	}

	public SolrCore getCore() {
		return this.core;
	}

	public int getLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SolrParams getOriginalParams() {
		// TODO Auto-generated method stub
		return this.params;
	}

	public String getParam(String name) {
		// TODO Auto-generated method stub
		return params.get(name);
	}

	public String getParamString() {
		// TODO Auto-generated method stub
		return null;
	}

	public SolrParams getParams() {
		// TODO Auto-generated method stub
		return this.params;
	}

	public String[] getParams(String name) {
		// TODO Auto-generated method stub
		return params.getParams(name);
	}

	public String getQueryString() {
		// TODO Auto-generated method stub
		return params.get("q");
	}

	public String getQueryType() {
		// TODO Auto-generated method stub
		return params.get("qt");
	}

	public IndexSchema getSchema() {
		// TODO Auto-generated method stub
		return this.core.getSchema();
	}

	public SolrIndexSearcher getSearcher() {
		// TODO Auto-generated method stub
		return core.getSearcher().get() ;
	}

	public int getStart() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setParams(SolrParams params) {
		// TODO Auto-generated method stub
		this.params = params ;
	}

	
}
