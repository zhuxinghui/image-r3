package org.rivu.image.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.imageanalysis.CEDD;
import net.semanticmetadata.lire.imageanalysis.LireFeature;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.rivu.image.core.RivuContext;
import org.rivu.image.solr.RivuDocument;
import org.rivu.image.solr.RivuParams;

import com.opensymphony.xwork2.Action;

public class SearchHandler extends Handler{
	private static final long serialVersionUID = 1L;
	private SearchResult result = new SearchResult();
	private String q ;	//query
	private String s ;	//start
	private String ps = "20";	//page size
	private String eq ;	//encoding query
	private String sr ;	//sort 
	private String t ; //templet id
	private String id ;
	private String qd ;		//
	private String c ;	  //commit delete by query
	private String f ;   //Query filter
	private String ef ;  //encode Query Filter ;
	private String tf ; // terms field
	private String ty ; //Search Category
	private String fn ; //Preview File Name
	private String fq ; //function query
	private int pn ;
	private String img ;
	private int width ;
	private int height ;
	private String imageType ;
	private int length ;
	private static java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\{([\\w\\.\\_\\-]*?)\\}") ;
	/**
	 * 插件使用
	 */
	public String execute(){
		if(q!=null && q.length()>0){
			try {
				String image = request.getParameter("image") ;
				if(q.matches("http[s]{0,}://[\\s\\S]*")){
					if(image==null || image.length()==0){
						BufferedImage qImage = ImageIO.read(new URL(q)) ;
						width = qImage.getWidth() ;
						height = qImage.getHeight() ;
						if(qImage!=null){
							LireFeature c = new CEDD();
							c.extract(qImage) ;
							image = c.getStringRepresentation() ;
							img = q ;
						}
					}
					q = "*:*" ;
				}
				
				String l= request.getParameter("l")!=null&&request.getParameter("l").length()>0?request.getParameter("l"):"0" ;
//				String u= request.getParameter("u")!=null&&request.getParameter("u").length()>0?request.getParameter("u"):"10" ;
				String u = String.valueOf(10) ;
				if(image!=null && !image.equals("")){
					fq = "{!frange l="+l+" u="+u+"}rivu(cedd,'"+image+"')" ;
					sr = "rivu(cedd,'"+image+"') asc" ;
				}
				q="*:*";
				search();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Action.SUCCESS ;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String search() throws IOException{
		if(q==null || q.equals("")){
			return super.sendError("搜索请求无效，请输入搜索关键词.") ;
		}
		SolrQuery query = new SolrQuery(); 
		
		Map paramMap = new HashMap() ;
		ps = ps !=null && ps.matches("[\\d]+") ? ps :"20" ;
		paramMap.put("rows", ps) ;
		paramMap.put("start", String.valueOf((p-1)*Integer.parseInt(ps))) ;
		try {
			eq = java.net.URLEncoder.encode(q, "UTF-8") ;
		} catch (UnsupportedEncodingException e1) {}
		paramMap.put("q", q) ;
		
		result = new SearchResult() ;
		result.setPageSize(Integer.parseInt(ps)) ;
		{
			{
				if(sr!=null)
					paramMap.put("sort", sr) ;
				/**
				 * Facet Field
				 */
				RivuParams params = new RivuParams(paramMap) ;
				query.add(params) ;
				
				QueryResponse rsp = null;
				try {
					rsp = RivuContext.getServer().query( query );
					result.setQ(q) ;
					result.setEncodeq(java.net.URLEncoder.encode(q, "UTF-8")) ;
					result.setDocNum(rsp.getResults().getNumFound()) ;
					result.setStart(rsp.getResults().getStart()) ;
					result.setRows(rsp.getResults().size()) ;
					result.setTime(rsp.getQTime()) ;
					RuntimeData.setSearchQueryNum(1) ;
					RuntimeData.setSearchQueryTime(result.getTime()) ;
					result.setStatus(rsp.getStatus()) ;
					RivuDocument rivuDocument = null ;
					
					for(int i=0 ; i<rsp.getResults().size() ; i++){
						
						rivuDocument = new RivuDocument(rsp.getResults().get(i), rsp.getHighlighting()!=null?rsp.getHighlighting().get(rsp.getResults().get(i).get("id")):null) ;
						result.getDocList().add(rivuDocument) ;
					}
					result.setSpellcheck(rsp.getSpellCheckResponse()) ;
				} catch (Exception e) {
					e.printStackTrace();
					result = new SearchResult() ;
					result.setError(true) ;
					result.setErrormsg(String.valueOf(e)) ;
					return super.sendError("Query Failed : "+e.getMessage()) ;
				} 
			}
		}
		return Action.SUCCESS;
	}

	public SearchResult getResult() {
		return result;
	}

	public void setResult(SearchResult result) {
		this.result = result;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getEq() {
		return eq;
	}

	public void setEq(String eq) {
		this.eq = eq;
	}

	public String getSr() {
		return sr;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQd() {
		return qd;
	}

	public void setQd(String qd) {
		this.qd = qd;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getEf() {
		return ef;
	}

	public void setEf(String ef) {
		this.ef = ef;
	}

	public String getTf() {
		return tf;
	}

	public void setTf(String tf) {
		this.tf = tf;
	}

	public String getTy() {
		return ty;
	}

	public void setTy(String ty) {
		this.ty = ty;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getFq() {
		return fq;
	}

	public void setFq(String fq) {
		this.fq = fq;
	}

	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public static java.util.regex.Pattern getPattern() {
		return pattern;
	}

	public static void setPattern(java.util.regex.Pattern pattern) {
		SearchHandler.pattern = pattern;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
