/**
 * 
 */
package org.rivu.image.solr;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.highlight.SolrHighlighter;
import org.apache.solr.schema.IndexSchema;
import org.rivu.image.tools.RivuTools;
import org.springframework.beans.BeanUtils;

/**
 * @author iceworld
 *
 */
public class RivuDocument {
	private SolrDocument document ;
	private Map<String , List<String>> hlList ;
	private SolrDocumentList morelike = new SolrDocumentList();
	public RivuDocument(){}
	public RivuDocument(SolrDocument document , Map<String , List<String>> hlList){
		this.document = document ;
		this.hlList = hlList!=null ? hlList: new java.util.HashMap() ;
	}
	public SolrDocument getDocument() {
		return document;
	}
	public void setDocument(SolrDocument document) {
		this.document = document;
	}
	
	public Map<String, List<String>> getHlList() {
		return hlList;
	}
	public void setHlList(Map<String, List<String>> hlList) {
		this.hlList = hlList;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String get(String name){
		return getValue(document.getFieldValue(name)) ;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String get(String name , boolean replace){
		String value = getValue(document.getFieldValue(name)) ; 
		return escape(replace?value.replace("<", "&lt;").replaceAll(">", "&gt;").replaceAll("[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n", "").replaceAll("[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n", "").replaceAll("[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n", "\n").replaceAll("\\?", "&#63;"):value) ;
	}	
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String get(String name , String encode) throws UnsupportedEncodingException{
		return java.net.URLEncoder.encode(get(name),encode) ; 
	}	
	
	public String escape(String text){
		return text!=null?text.toLowerCase().replaceAll("<table", "&lt;table").replaceAll("table>", "table&gt;").replaceAll("<div", "&lt;div").replaceAll("div>", "div&gt;").replaceAll("<tr", "&lt;tr").replaceAll("tr>", "tr&gt;").replaceAll("<td", "&lt;td").replaceAll("td>", "td&gt;"):"" ;
	}
	/**
	 * Convert 
	 * @param strArr
	 * @return
	 */
	private String getValue(Object strArr){
		if(strArr==null)
			return null ;
		StringBuffer strb = new StringBuffer() ;
		if(strArr instanceof String[]){
			for(String str : (String[])strArr)
				strb.append(str) ;
		}else if(strArr instanceof List){
			for(Object str : (List)strArr)
				strb.append(str!=null?str.toString():"") ;
		}else if(strArr instanceof String){
			strb.append((String)strArr) ;
		}else if(strArr instanceof Date){
			strb.append(RivuTools.formatDate((Date)strArr)) ;
		}else{
	    	strb.append(strArr) ;
	    }
		return strb.toString() ;
	}

	public SolrDocumentList getMorelike() {
		return morelike;
	}
	public void setMorelike(SolrDocumentList morelike) {
		this.morelike = morelike;
	}
	
}
