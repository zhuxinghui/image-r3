/**
 * 
 */
package org.rivu.image.solr;

import java.util.Iterator;
import java.util.Map;

import org.apache.solr.common.params.SolrParams;

/**
 * @author iceworld
 *
 */
public class RivuParams extends SolrParams{
	protected final Map map;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RivuParams(Map map){
		this.map = map ;
	}
	@Override
	public String get(String param) {
		// TODO Auto-generated method stub
		return map.get(param) instanceof String[] ? ((String[])map.get(param))[0]:(String)map.get(param);
	}

	@Override
	public Iterator<String> getParameterNamesIterator() {
		return map.keySet().iterator();
	}

	@Override
	public String[] getParams(String param) {
	    return map.get(param)==null ? new String[]{} :map.get(param) instanceof String[] ? (String[])map.get(param):new String[]{(String)map.get(param)};
	}

}
