package org.rivu.image.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.rivu.image.solr.FacetData;
import org.rivu.image.solr.RivuDocument;
import org.rivu.image.solr.RivuFacetField;

/**
 * @author jaddy0302 Rivulet SearchResult.java 2010-3-25
 * 
 */
public class SearchResult {
	private int status ;
	private int time ;
	private long docNum ;
	private long start ;
	private int pageSize = 10;
	private float maxscore ;
	private int rows ;
	private SolrParams params ;
	private List<RivuDocument> docList = new ArrayList<RivuDocument>();
	private FacetData facetList = new FacetData() ;
	private List<FacetData> staList = new ArrayList<FacetData>() ;
	private boolean error = false ;
	private String errormsg ;
	private String encodeq ;
	private String q ;
	private SpellCheckResponse spellcheck ;
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * @return the docNum
	 */
	public long getDocNum() {
		return docNum;
	}
	/**
	 * @param docNum the docNum to set
	 */
	public void setDocNum(long docNum) {
		this.docNum = docNum;
	}
	/**
	 * @return the start
	 */
	public long getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(long start) {
		this.start = start;
	}
	/**
	 * @return the maxscore
	 */
	public float getMaxscore() {
		return maxscore;
	}
	/**
	 * @param maxscore the maxscore to set
	 */
	public void setMaxscore(float maxscore) {
		this.maxscore = maxscore;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the params
	 */
	public SolrParams getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(SolrParams params) {
		this.params = params;
	}
	
	
	/**
	 * @return the docList
	 */
	public List getDocList() {
		return docList;
	}
	/**
	 * @param docList the docList to set
	 */
	public void setDocList(List docList) {
		this.docList = docList;
	}
	/**
	 * @return the facetList
	 */
	public FacetData getFacetList() {
		return facetList;
	}
	/**
	 * @param facetList the facetList to set
	 */
	public void setFacetList(FacetData facetList) {
		this.facetList = facetList;
	}
	/**
	 * @return the staList
	 */
	public List<FacetData> getStaList() {
		return staList;
	}
	/**
	 * @param staList the staList to set
	 */
	public void setStaList(List<FacetData> staList) {
		this.staList = staList;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	/**
	 * @return the encodeq
	 */
	public String getEncodeq() {
		return encodeq;
	}
	/**
	 * @param encodeq the encodeq to set
	 */
	public void setEncodeq(String encodeq) {
		this.encodeq = encodeq;
	}
	/**
	 * @return the q
	 */
	public String getQ() {
		return q;
	}
	/**
	 * @param q the q to set
	 */
	public void setQ(String q) {
		this.q = q;
	}
	public SpellCheckResponse getSpellcheck() {
		return spellcheck;
	}
	public void setSpellcheck(SpellCheckResponse spellcheck) {
		this.spellcheck = spellcheck;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object getValue(String key,Map<String , Object> data){
		return data!=null ? data.get(key) : null;
 	}
	/**
	 * Convert 
	 * @param strArr
	 * @return
	 */
	public String getValue(Object strArr){
		if(strArr==null)
			return null ;
		StringBuffer strb = new StringBuffer() ;
		if(strArr instanceof String[]){
			for(String str : (String[])strArr)
				strb.append(str) ;
		}else if(strArr instanceof List){
			for(String str : (List<String>)strArr)
				strb.append(str) ;
		}else if(strArr instanceof String){
			strb.append((String)strArr) ;
		}else{
	    	strb.append(strArr) ;
	    }
		return strb.toString() ;
	}
	/**
	 * 前台使用，获得统计数字
	 * @param key
	 * @param dataList
	 * @return
	 */
	public long value(String key , List<FacetField.Count> dataList){
		long value =0 ;
		for(FacetField.Count count : dataList){
			if(count.getName().equals(key)){
				value = count.getCount() ;
			}
		}
		return value ;
	}
	/**
	 * 前台使用，获得统计数字
	 * @param key
	 * @param dataList
	 * @return
	 */
	public long value(String key , long count, List<FacetField.Count> dataList){
		long value =0 ;
		if(key!=null && key.matches("[\\d]*")){
			int iKey = Integer.parseInt(key) ;
			for(FacetField.Count dl : dataList){
				if(dl.getName().equals(String.valueOf(iKey-1))){
					value = dl.getCount() ;
				}
			}
		}
		return value ;
	}
	
	/**
	 * 前台使用，获得购买频次
	 * @param key
	 * @param dataList
	 * @return
	 */
	public String value(int count , String code, List<RivuFacetField> faceFieldList){
		String value = null ;
		List<FacetField.Count> dataList = null ;
		for(RivuFacetField rff : faceFieldList){
			if(rff.getCode().equals(code)){
				dataList = rff.getFacet().getValues();
			}
		}
		if(dataList!=null){
			for(FacetField.Count dl : dataList){
				if(dl.getCount()==count){
					value = dl.getName() ;
				}
			}
		}
		return value ;
	}
	

	/**
	 * 前台使用，获得购买频次
	 * @param key
	 * @param dataList
	 * @return
	 */
	public long value(String code , String key, List<RivuFacetField> faceFieldList){
		long value = 0 ;
		List<FacetField.Count> dataList = null ;
		for(RivuFacetField rff : faceFieldList){
			if(rff.getCode().equals(code)){
				dataList = rff.getFacet().getValues();
			}
		}
		if(dataList!=null){
			for(FacetField.Count dl : dataList){
				if(dl.getName().equals(key)){
					value = dl.getCount() ;
				}
			}
		}
		return value ;
	}
	
	private StringBuffer strb = new StringBuffer();
	/**
	 * 前台使用，获得统计数字
	 * @param key
	 * @param dataList
	 * @return
	 */
	public long value(String key){
		long value =0 ;
		if(key!=null && key.matches("[\\d]*")){
			value = Integer.parseInt(key) ;
		}
		return value ;
	}
	
	/**
	 * 前台使用，获得统计数字
	 * @param key
	 * @param dataList
	 * @return
	 */
	public float value(long value , long sum){
		return (float)(int)(((float)value/(float)sum)*10000)/100f ;
	}
	
	/**
	 * 前台使用，获得统计数字
	 * @param key
	 * @param dataList
	 * @return
	 */
	public float value(long value , long sum, boolean baifen){
		float bf = (float)(int)(((float)value/(float)sum)*10000)/100f  ;
		return bf<100 ? bf : 100 ;
	}
	
	/**
	 * 前台使用，获得统计同比增长数字
	 * @param key
	 * @param dataList
	 * @return
	 */
	public String value(long count, String key , List<FacetField.Count> dataList){
		String tb = "<b class='tongbi'>0%</b>";
		long value =0 ;
		if(key!=null && key.matches("[\\d]*")){
			int iKey = Integer.parseInt(key) - 1  ;
			for(FacetField.Count dl : dataList){
				if(dl.getName().equals(String.valueOf(iKey))){
					value = dl.getCount() ;
				}
			}
		}
		strb = new StringBuffer();
		if(value>0){
			float b = (float)((int)(10000*(((float)count-(float)value)/(float)value)))/100f ;
			
			if((count-value)>=0){
				strb.append("<b class='tongZ'>").append(b).append("%</b>");
			}else{
				strb.append("<b class='tongF'>").append(b).append("%</b>");
			}
			tb = strb.toString() ;
		}else{
			tb = "<b class='tongbi'>-</b>";
		}
		return tb;
	}
	
	
	
	/**
	 * 前台使用，获得统计数字
	 * 参数 tongbi 为是否计算同比
	 * @param key
	 * @param dataList
	 * @return
	 */
	public long value(String code , String key , List<RivuFacetField> faceFieldList , boolean tongbi){
		long value =0 ;
		for(RivuFacetField rff : faceFieldList){
			if(rff.getCode().equals(code)){
				value = value(key , rff.getFacet().getValues());
			}
		}
		
		return value ;
	}
	
	/**
	 * 截断字符
	 * @param input
	 * @param length
	 * @return
	 */
	public String substring(String input , int length){
		return input!=null && input.length()>0?input.substring(0 , length>0&&length<input.length()?length:input.length()):"" ;
	}
	
	/**
	 * 截断字符
	 * @param input
	 * @param length
	 * @return
	 */
	public String substring(String input ,int start , int length){
		return input!=null && input.length()>0&&start>0&&start<length?input.substring(start , length>0&&length<input.length()?length:input.length()):"" ;
	}
	
	/**
	 * 
	 * @param pagesize
	 */
	public void setPageSize(int pagesize){
		this.pageSize = pagesize ;
	}
	/**
	 * 
	 * @return
	 */
	public int getTotal(){
		int total = 0 ;
		if(docNum%pageSize==0)
			total = (int)(docNum/this.pageSize) ;
		else
			total = (int)(docNum/this.pageSize)+1 ;
		return total ;
	}
}
