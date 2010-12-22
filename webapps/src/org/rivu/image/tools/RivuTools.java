package org.rivu.image.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.IndexReaderFactory;
import org.apache.solr.search.SolrIndexSearcher;
import org.apache.solr.util.RefCounted;
import org.rivu.image.core.RivuContext;

/**
 * @author jaddy0302 Rivulet RivuData.java 2010-4-7
 * 
 */
public class RivuTools {
	
	private static Map<String,String> keyMap = new HashMap<String, String>();
	
	/**
	 * 
	 */
	public static void init() {}
	/**
	 * 
	 */
	public static void clear(String taskid){
		Iterator<String> iterator = keyMap.keySet().iterator() ;
		List<String> tempList = new ArrayList<String>();
		while(iterator.hasNext()){
			String dataid = iterator.next();
			if(taskid.equals(keyMap.get(dataid))){
				tempList.add(dataid) ;
			}
		}
		for(String id : tempList){
			keyMap.remove(id) ;
		}
		tempList.clear() ;
	}
	/**
	 * 
	 */
	public static void clearAll(){
		keyMap.clear() ;
	}
	/**
	 * 
	 * @param dataid
	 * @param taskid
	 * @return
	 */
	public synchronized static boolean add(String dataid , String taskid) {
		boolean add = true;
		if ((keyMap.get(dataid)) != null) {
			add = false ;
		}if(!isAva(dataid)){
			add = false ;
		} else {
			keyMap.put(dataid, taskid);
		}
		return add;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isAva(String id){
		boolean ava = false ;
		if(id!=null){
			if(keyMap.get(id)!=null){
				ava = false ;
			}else{
				try {
					QueryResponse rsp = RivuContext.getServer().query( new SolrQuery().add("q", "id:"+id) );
					if(rsp.getResults().size()>=1){
						ava = false;
					}else{
						ava = true ;
					}
				}catch(Exception ex){
					ava = false ;
				}
			}
		}
		return ava ;
	}
	
}
