/**
 * 
 */
package org.rivu.image.web;

import java.io.File;
import java.util.Date;

import org.apache.solr.core.CoreContainer;
import org.rivu.image.core.RivuContext;
/**
 * @author iceworld
 *
 */
public class RuntimeData {
	private long allocateMemory = Runtime.getRuntime().maxMemory();
	private long remainingMemory  = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory();
	private long usingMemory  = Runtime.getRuntime().totalMemory();
	private String operatingSystem = System.getProperty("os.name");
	private String jdkPath = System.getProperty("java.home");
	private String userPath = System.getProperty("user.dir");
	private String osUser = System.getProperty("user.name");
	private String jvm = System.getProperty("java.vm.name");
	private String jreVersion = System.getProperty("java.class.version");
	private String jvmVersion = System.getProperty("java.version");
	private int indexDocuments = 0 ;
	private static int searchQueryNum = 0;
	private float averageResponseTime = 0 ;
	private static long searchQueryTime = 0 ;
	private long diskSpace = new File(".").getTotalSpace();
	private long diskFreeSpace = new File(".").getFreeSpace();
	private long usedDiskSpace = diskSpace - diskFreeSpace;
	private static Date starttime = new Date();
	
	
	public long getAllocateMemory() {
		return allocateMemory;
	}
	public void setAllocateMemory(long allocateMemory) {
		this.allocateMemory = allocateMemory;
	}
	public long getRemainingMemory() {
		return remainingMemory;
	}
	public void setRemainingMemory(long remainingMemory) {
		this.remainingMemory = remainingMemory;
	}
	public long getUsingMemory() {
		return usingMemory;
	}
	public void setUsingMemory(long usingMemory) {
		this.usingMemory = usingMemory;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getJdkPath() {
		return jdkPath;
	}
	public void setJdkPath(String jdkPath) {
		this.jdkPath = jdkPath;
	}
	public String getUserPath() {
		return userPath;
	}
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
	public String getOsUser() {
		return osUser;
	}
	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}
	public String getJvm() {
		return jvm;
	}
	public void setJvm(String jvm) {
		this.jvm = jvm;
	}
	public String getJreVersion() {
		return jreVersion;
	}
	public void setJreVersion(String jreVersion) {
		this.jreVersion = jreVersion;
	}
	public String getJvmVersion() {
		return jvmVersion;
	}
	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}
	public int getIndexDocuments() {
		Object data = RivuContext.getCore().getInfoRegistry().get("searcher") ;
		if(data!=null && data instanceof org.apache.solr.search.SolrIndexSearcher){
			return ((org.apache.solr.search.SolrIndexSearcher)data).getIndexReader().numDocs() ;
		}else{
			return 0 ;
		}
	}
	public void setIndexDocuments(int indexDocuments) {
		this.indexDocuments = indexDocuments;
	}
	public int getSearchQueryNum() {
		return this.searchQueryNum;
	}
	public static void setSearchQueryNum(int searchQueryNum) {
		RuntimeData.searchQueryNum += searchQueryNum;
	}
	public float getAverageResponseTime() {
		return ((float)((int)((this.searchQueryNum!=0? ((float)searchQueryTime/1000f)/(float)this.searchQueryNum:0)*1000)))/1000f;
	}
	public void setAverageResponseTime(float averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}
	public long getDiskSpace() {
		return diskSpace;
	}
	public void setDiskSpace(long diskSpace) {
		this.diskSpace = diskSpace;
	}
	public long getDiskFreeSpace() {
		return diskFreeSpace;
	}
	public void setDiskFreeSpace(long diskFreeSpace) {
		this.diskFreeSpace = diskFreeSpace;
	}
	public long getUsedDiskSpace() {
		return usedDiskSpace;
	}
	public void setUsedDiskSpace(long usedDiskSpace) {
		this.usedDiskSpace = usedDiskSpace;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public long getSearchQueryTime() {
		return searchQueryTime;
	}
	public static void setSearchQueryTime(long curremtSearchQueryTime) {
		RuntimeData.searchQueryTime += curremtSearchQueryTime;
	};
	
	
}
