/**
 * Licensed to the Rivulet under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     webapps/LICENSE-Rivulet-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rivu.image.solr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;

/**
 * @author jaddy0302 Rivulet FacetData.java 2010-3-26
 * 
 */
public class FacetData {
	private Map<String ,Integer> facetQuery = new HashMap<String ,Integer>();
	private List<RivuFacetField> faceFieldList = new ArrayList<RivuFacetField>();
	private List<RivuFacetField> facetDateList = new ArrayList<RivuFacetField>();
	private List<RivuFacetField> facetQueryValue = new ArrayList<RivuFacetField>();
	public Map<String, Integer> getFacetQuery() {
		return facetQuery;
	}
	public void setFacetQuery(Map<String, Integer> facetQuery) {
		if(facetQuery!=null){
			Iterator<String> iterator = facetQuery.keySet().iterator() ;
			while(iterator.hasNext()){
				String query = iterator.next() ;
				{
					this.facetQuery.put(query, facetQuery.get(query)) ;
					this.facetQueryValue.add(new RivuFacetField(query,query ,facetQuery.get(query) ,this.facetQueryValue.size() , null)) ;
				}
			}
			Collections.sort(this.facetQueryValue, new Comparator<RivuFacetField>(){
				public int compare(RivuFacetField o1, RivuFacetField o2) {
					return o1.getValue()< o2.getValue()?1:0;
				}}) ;
		}
	}
	/**
	 * 排序
	 */
	public boolean sortByIndex(){
		Collections.sort(this.facetQueryValue, new Comparator<RivuFacetField>(){
			public int compare(RivuFacetField o1, RivuFacetField o2) {
				return o1.getIndex()>= o2.getIndex()?1:0;
			}}) ;
		return true ;
	}
	/**
	 * 排序
	 */
	public boolean sortByValue(){
		Collections.sort(this.facetQueryValue, new Comparator<RivuFacetField>(){
			public int compare(RivuFacetField o1, RivuFacetField o2) {
				return o1.getValue()< o2.getValue()?1:0;
			}}) ;
		return true ;
	}
	
	public List<RivuFacetField> getFaceFieldList() {
		return faceFieldList;
	}
	
	public void setFaceFieldList(List<FacetField> faceFieldList) {
		if(faceFieldList!=null){
			for(FacetField ff : faceFieldList){
				List<Count> facetField = ff.getValues() ;
				for(int i=0 ; facetField!=null && i<facetField.size() ;){
					while(facetField.size()>i && facetField.get(i).getCount()==0){
						facetField.remove(i) ;
					}
					i++ ;
				}
				this.faceFieldList.add(new RivuFacetField(ff.getName(),ff.getName(),ff)) ;
			}
		}
	}
	public List<RivuFacetField> getFacetDateList() {
		return facetDateList;
	}
	public void setFacetDateList(List<FacetField> facetDateList) {
		if(facetDateList!=null){
			for(FacetField ff : facetDateList){
				List<Count> facetField = ff.getValues() ;
				for(int i=0 ; i<facetField.size() ;){
					while(facetField.size()>i && facetField.get(i).getCount()==0){
						facetField.remove(i) ;
					}
					i++ ;
				}
				this.facetDateList.add(new RivuFacetField(ff.getName(),ff.getName(),ff)) ;
			}
		}
	}
	public List<RivuFacetField> getFacetQueryValue() {
		return facetQueryValue;
	}
	
	
	
}
