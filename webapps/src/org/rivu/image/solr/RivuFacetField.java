package org.rivu.image.solr;

import org.apache.solr.client.solrj.response.FacetField;

public class RivuFacetField {
	private String name  ;
	private String code ;
	private FacetField facet;
	private int value ;
	private int index ;
	
	public RivuFacetField(String name , String code , FacetField facet)
	{
		this.name = name ;
		this.code = code ;
		this.facet = facet ;
	}
	public RivuFacetField(String name , String code , int value , FacetField facet)
	{
		this.name = name ;
		this.code = code ;
		this.facet = facet ;
		this.value = value ;
	}
	public RivuFacetField(String name , String code , int value , int index, FacetField facet)
	{
		this.name = name ;
		this.code = code ;
		this.facet = facet ;
		this.value = value ;
		this.index = index ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacetField getFacet() {
		return facet;
	}
	public void setFacet(FacetField facet) {
		this.facet = facet;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
