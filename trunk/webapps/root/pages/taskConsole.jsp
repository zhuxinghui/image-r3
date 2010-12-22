<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="change">
<script language="javascript">
	$(document).ready(function(){
		doGetUpdate("task!change.rv","imagetask");
	});
</script>
</s:if>
<table style="width:100%;background-color:#ffffff;" border="0" cellspacing="0" cellpadding="0">
	<s:iterator value="jobList">
	<tr>
		<td><b><s:property value="name"/>：</b><s:property value="report.status"/> 开始 <s:date name="report.starttime" format="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	</s:iterator>
</table>