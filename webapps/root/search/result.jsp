<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Expires" content="0">   
		<meta http-equiv="Cache-Control" content="no-cache">   
		<meta http-equiv="Cache-Control" content="no-store">   
		<meta http-equiv="Pragma" content="no-cache">  
		<LINK href="../css/css.css" type=text/css rel=stylesheet>
		<LINK href="css/common.css" type=text/css rel=stylesheet>
		<LINK href="css/jquery.fancybox-1.3.0.css" type=text/css rel=stylesheet>
		<LINK href="css/jquery.alerts.css" type=text/css rel=stylesheet>
		<LINK href="css/Pager.css" type=text/css rel=stylesheet>
		<LINK href="css/search.css" type=text/css rel=stylesheet>

		<LINK href="css/clickmenu.css"  type="text/css"  rel="stylesheet" >
		<LINK rel="STYLESHEET" type="text/css"	href="codebase/dhtmlxtree.css">
		<script src="codebase/dhtmlxcommon.js"></script>
		<script src="codebase/dhtmlxtree.js"></script>
		<script src="codebase/ext/dhtmlxtree_start.js"></script>
		<script src="codebase/jquery-1.3.2.js"></script>
		<script src="codebase/jquery.form.js"></script>
		<script src="codebase/jquery.pager.js"></script>
		<script src="codebase/jquery.easing-1.3.pack.js"></script>
		<script src="codebase/jquery.fancybox-1.3.0.js"></script>
		<script src="codebase/jquery.ui.draggable.js"></script>
		<script src="codebase/jquery.alerts.js"></script>
		<script src="codebase/jquery.timers.js"></script>
		<script src="codebase/jquery.blockUI.js"></script>
		<script src="codebase/validation.js"></script>
		<script src="codebase/jquery.clickmenu.js"></script>
		<script src="codebase/rivu.js"></script>

	</head>
	<body>
		<div class="header_container">
			<div class="header_top">
				
			</div>
		</div>
		<!-- End header-->
		
		<!-- End Containers-->

		<!-- End Contents-->
		<div class="Contents">
			<div class="searchForm searchForm2">
				<div class="logo_rs"></div>
				<form action="/search.rv" method="get">
				<s:hidden value="ps"/>
				<ul style="float:left;">
					<li style="margin-top:2px;"><input type="text" maxlength="100000" name="q" id="url_box" class="searchinput2" value="<%=request.getParameter("q")%>"></li>
					<li style="margin-left:10px;"><input type="image" type="submit" src="/images/searchbtn.png"></li>
					<li style="margin-left:10px;"><input type="image" type="submit" src="/images/searchbtn.png"></li>
				</ul>
				</form>
				<div class="searchtip" style="float:left;">检索关键词或图片地址，例如：圣诞，http://www.domain.com/logo.gif , 或上传一副本地图片</div>
				<div style="clear:both;"></div>
			</div>
			<div class="rsinfo">
				<div style="float:left;">共找到 <s:property value="result.docNum"/> 幅图片，花费 <s:property value="result.time"/>ms，检索条件：</div><div class="overHidden" style="width:400px;float:left;" title="<%=request.getParameter("q")%>"><b><%=request.getParameter("q")%></b> </div>  <a href="http://code.google.com/p/image-r3" target="_blank" style="float:right;margin-right:40px;color:red;">需要一个同样的图片搜索引擎？</a>
			</div>
			<div>
				<ul class="imagePanel">
					<li class="leftPanel">
						<s:if test="img!=null">
						<img src="<%=request.getParameter("q")%>" class="image">
						<div style="color:#cccccc;text-align:center;">图片尺寸：<s:property value="width"/>*<s:property value="height"/></div>
						</s:if>
						<div style="margin-top:20px;font-size:15px;text-align:right;">
							<ul>
								<li style="font-weight:bold;">排序方式</li>
								<li style="font-weight:bold;">相似度</li>
								<li style="font-weight:bold;">尺寸</li>
							</ul>
						</div>
					</li>
					<li class="rightPanel" style="margin:0;">
						<ul class="imageRightPanel">
							<s:iterator value="result.docList">
							<li title="<s:property value="get('title')"/>" style="margin:5px;"><img src="<s:property value='path'/>/<s:property value='get("id")'/>.png"></li>
							</s:iterator>
						</ul>
					</li>
				</ul>
			</div>
			<div style="clear:both;"></div>
		</div>
		<div>
			<hr style="height:1px;border:none;border-top:1px solid #a7d5f6;"/>
			<div style="width:100%;text-align:center;">
				Copyright © 2010 | Powered by: <a href="http://www.rivues.com" target="_blank">起点R3</a><br/>
				开源图像搜索IMAGE-R3<br/>
				<a href="mailto:rivulet.es@gmail.com">rivulet.es@gmail.com</a>
			</div>
		</div>
	</body>
</html>
