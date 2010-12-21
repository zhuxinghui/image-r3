
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tr>
											<td><img src="../../images/list/report.png" width="48" height="48" /></td>
											<td colspan="3" valign="top"><strong>系统运行状态信息</strong><br />
											<span id="result_box"><span title="您可以在此处查看系统运行的状态，如内存使用状况、CPU利用率，磁盘存储空间，访问次数，索引数据量等信息">此功能提供一个查看系统运行状态信息的界面, 例如： 内存分配大小和剩余大小, CPU利用率, 磁盘存储空间剩余量, 数据检索次数, 以及索引总量信息等。</span></span></td>
										  </tr>
										  <tr>
											<td>&nbsp;</td>
											<td colspan="3" valign="top"><table width="100%" border="0" cellpadding="2" cellspacing="1"
														style="background-color: #EFEFEF">
											  <tr style="background-color: #FFFFFF">
												<td width="14%"><img src="../images/icon_arrow2.png" width="6" height="9" /> <strong> 分配内存</strong></td>
												<td width="12%"><s:property value="runtime.allocateMemory/1024/1024"/>M</td>
												<td width="14%"><img src="../images/icon_arrow2.png" width="6" height="9" /> <strong> 当前用户</strong></td>
												<td width="19%"><s:property value="runtime.osUser"/></td>
												<td width="17%"><img src="../images/icon_arrow2.png" width="6" height="9" /> <strong> 索引信息总量</strong></td>
												<td width="24%"><font color="green"><s:property value="runtime.indexDocuments"/></font> docs</td>
											  </tr>
											  <tr style="background-color: #FFFFFF">
												<td nowrap="nowrap"><img src="../images/icon_arrow2.png" alt="" width="6"
																	height="9" /> <strong>空闲内存</strong> <br /></td>
												<td><font color="red"><s:property value="runtime.remainingMemory/1024/1024"/></font>M</td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /><strong> JAVA虚拟机</strong></td>
												<td><s:property value="runtime.jvm"/></td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /> <strong> 检索请求次数</strong></td>
												<td><s:property value="runtime.searchQueryNum"/></td>
											  </tr>
											  <tr style="background-color: #FFFFFF">
												<td><img src="../images/icon_arrow2.png" alt="" width="6"
																	height="9" /> <strong>已使用内存</strong> <br /></td>
												<td><s:property value="runtime.usingMemory/1024/1024"/>M</td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /><strong> 虚拟机版本</strong></td>
												<td><s:property value="runtime.jvmVersion"/></td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /> <strong> 平均检索响应时间</strong></td>
												<td><s:property value="runtime.averageResponseTime"/>s</td>
											  </tr>
											  <tr style="background-color: #FFFFFF">
												<td><img src="../images/icon_arrow2.png" alt="" width="6"
																	height="9" /> <strong>操作系统</strong> <br /></td>
												<td width="12%"> <s:property value="runtime.operatingSystem"/></td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /><strong> 虚拟机Class版本</strong></td>
												<td><s:property value="runtime.jreVersion"/></td>
											   <td><img src="../images/icon_arrow2.png" width="6" height="9" /><strong> 系统启动时间</strong></td>
												<td><s:date name="runtime.starttime" format="yyyy-MM-dd HH:mm:ss"/></td>
											  </tr>
											  <tr style="background-color: #FFFFFF">
												<td><img src="../images/icon_arrow2.png" alt="" width="6"
																	height="9" /> <strong>虚拟机路径</strong> <br /></td>
												<td colspan="5"><s:property value="runtime.jdkPath"/></td>
											  </tr>
											  <tr style="background-color: #FFFFFF">
												<td><img src="../images/icon_arrow2.png" alt="" width="6"
																	height="9" /> <strong>用户默认路径</strong> <br /></td>
												<td colspan="5"><s:property value="runtime.userPath"/></td>
											  </tr>
											   <tr style="background-color: #FFFFFF">
												<td height="23"><img src="../images/icon_arrow2.png" alt="" width="6"
																	height="9" /> <strong>磁盘空间</strong> <br /></td>
												<td>
													<s:if test="runtime.diskSpace==0">
														Unknow
													</s:if>
													<s:else>
													<s:property value="runtime.diskSpace/1024/1024/1024"/>G
													</s:else>
													</td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /><strong> 磁盘剩余空间</strong></td>
												<td>
													<s:if test="runtime.diskFreeSpace==0">
														Unknow
													</s:if>
													<s:else>
													<font color="red"><s:property value="runtime.diskFreeSpace/1024/1024/1024"/>G</font>
													</s:else>					
													</td>
												<td><img src="../images/icon_arrow2.png" width="6" height="9" /><strong> 已使用磁盘空间</strong></td>
												<td>
													<s:if test="runtime.usedDiskSpace==0">
														Unknow
													</s:if>
													<s:else>
													<s:property value="runtime.usedDiskSpace/1024/1024/1024"/>G
													</s:else>
												</td>
											  </tr>
											</table></td>
										  </tr>