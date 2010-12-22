<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="dataList" id="rivuData">
	<div
		class="List_Main_UserInfo_Thumbnail List_Main_UserInfo_Thumbnail_File" title="<s:property value='resource'/>">
		<table style="width:100%;background-color:#ffffff;" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="10%">
					<a  class="taskPic" href="javascript:void(0)" rel="floatBox${stat.index}" style="margin:0px;padding:0px;" onclick="doGet('${pageContext.request.contextPath}/crawl/ftp!edit.rv?data.id=<s:property value="id"/>','editPanel')">
						<img src="../images/list/imagetask.png" 
							style="width:50px;"/>
					</a>
				</td>
				<td align="left" valign="top">																	
					<div style="background-color:#ffffff;margin-top:5px;margin-left:10px;">
						<div style="background-color:#ffffff;">
							<a href="javascript:void(0)" onclick="doGet('${pageContext.request.contextPath}/crawl/ftp!edit.rv?data.id=<s:property value="id"/>','editPanel')"><s:property value="resource"/></a>
						</div>
						<div style="background-color:#ffffff;">
							<img src="../images/list/gou.png" alt="message" name="m_<s:property value='id'/>"/>
							<span name="pre" id="p_<s:property value='id'/>" style="<s:if test='taskstatus==0||taskstatus==null'>display:inline;</s:if><s:else>display:none;</s:else>">
								<a href="javascript:void(0)" name="con" tid='<s:property value="id"/>' onclick="jConfirm('请确认启动图片采集任务?', '确认窗口', function(r) {if(r) doGet('task!run.rv?data.id=<s:property value="id"/>','imagetask');});" title="start task"><img src="../images/list/run.png"/></a>
								<a href="javascript:void(0)" onclick="jConfirm('请确认删除图片采集任务?', '确认窗口', function(r) {if(r) doGet('task!rm.rv?data.id=<s:property value="id"/>','imagetask');});"><img src="../images/list/del.png" alt="delete" /></a>
							</span>
							<span name="run" id="r_<s:property value='id'/>" style="<s:if test='taskstatus==1 || taskstatus==2'>display:inline;</s:if><s:else>display:none;</s:else>">
								<a href="javascript:void(0)" onclick="jConfirm('Task is running ,Can you confirm stop this Net File Task?', 'Confirmation Dialog', function(r) {if(r) doGet('${pageContext.request.contextPath}/crawl/ftp!stop.rv?p=<s:property value='p'/>&data.id=<s:property value="id"/>','imagetask');});" title="task is running"><img src="../images/list/stop.png"/><img style="padding:0 0 3px 7px;" src="../images/list/running.gif"/></a>
							</span>
						</div>
					</div>
					
				</td>
			</tr>
		</table>
	</div>
</s:iterator>