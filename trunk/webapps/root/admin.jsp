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

		<title>开源图像搜索引擎，IMAGE-R3</title>
		<LINK href="css/admin.css" type=text/css rel=stylesheet>
		<LINK href="css/head.css" type=text/css rel=stylesheet>
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
		<script language="javascript">
			$(document).ready(function(){
				welcomeDate("welcomeDate");
				doUpdateRunningStatus();
			});
		</script>
	</head>
	<body>

		<!-- Begin header-->
		<div class="header_container">
			<div class="header_top">
				<div class="logo">
					<a href="welcome.rv"><img src="/images/logo_s.png" height="33" /></a>
				</div>
				<div class="search">
					<form action="/search.rv" method="get" onsubmit="return doPost(this,'indexPanel',function(){changeCSS('searchResult');});" id="sForm">
						<input type="hidden" name="ty" id="ty"/>
						<input type="hidden" name="ps" id="ps"/>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<div class="searchInput">
										<input name="q" type="text" class="searchInputText required" id="q"/>
									</div>
								</td>
								<td>
									<input type="image"
										src="../images/head_btn_search.png" style="margin-top:2px;"/>
								</td>
								<td>
									<img src="../images/head_dotline.png" align="absmiddle" />
								</td>
								<td name="searchIn">
									<img src="../images/head_home.png" align="absmiddle" />
									<a class="txtBlue" rel="shareit" name="searchInBox" href="rivu.rv"><s:text name="index.visitsite"/></a>
								</td>
								
								<!--
								<td>
									<a href="#"><img src="../images/icon_arrow_down.png" />
									</a>
								</td>
								-->
							</tr>
						</table>
					</form>
				</div>
				<div class="right">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							
							<td>
								<a href="#"></a>
							</td>
							<td>
								<img src="../images/head_dotline.png" align="absmiddle" />
							</td>
							<td>
								<a name="userprofile" href="system/changepass.rv">
									<img src="../images/icon_person.png" align="absmiddle" />
									<span class="txtBlue"><s:property value="#session.LOGIN_USER.username"/>
									</span>
								</a>		
								<script language="javascript">
									$("a:[name='userprofile']").fancybox({ajax : {type	: 'get'}});
								</script>
							</td>
							<td>
								<span style="margin:5px 10px 0 10px;margin-top:5px;"><a href="javascript:void(0);" onclick="jConfirm('<s:text name="index.cf_logout"/>', 'Confirmation Dialog', function(r) {if(r) doLogout('logout.rv',function(){location.href='welcome.rv';})});"><img src="../images/logout.png" /></a></span>
							</td>
						</tr>
					</table>
				</div>

			</div>
			<div class="head_menu">
				<!-- Begin head_menu_Tab-->
				<div class="head_menu_Tab_Choosed" id="welcomeMenu">
					<div class="Tab_Left"></div>
					<div class="Tab_bg">
						<a href="#" onClick="doGet('first.rv','indexPanel',function(){changeCSS('welcomeMenu');});"><s:text name="index.menu_welcome"/></a>
					</div>
					<div class="Tab_Right"></div>
					<div class="clears"></div>
				</div>
				

			</div>
		</div>
		<!-- End header-->

		<!-- End Containers-->

		<!-- End Contents-->
		<div class="Contents" id="indexPanel">
			<div class="Nav_Link" id="menuTextDiv">
				企业搜索配置管理 -> 欢迎页面
			</div>
			<!-- Begin Contents_List-->
			<div class="Contents_List" style="">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						
						<td valign="top" class="Contents_List_Main" id="crawlMainPanel">
							<!-- End Contents-->
							<div class="Contents" style="padding-top:0px;margin-top:0px;">
								<div id="board" style="margin-top:0px;">
									<img class="logo" src="images/icons/home_welcome.png"/>									
									<div class="news">
										
										<ul>
											<li> 
												<a href="javascript:void(0)">系统处理的信息总量 ：<span class="text-key"> <s:property value="runtime.indexDocuments"/> </span></a>
											</li>
											<li>
												<a href="javascript:void(0)">系统分配内存/空闲内存 ：<span class="text-key"> <s:property value="runtime.allocateMemory/1024/1024"/>/<s:property value="runtime.remainingMemory/1024/1024"/>M </span>
												
												</a>
											</li>
										</ul>
									</div>
									<div style="float:right;padding-right:10px;"><a href="javascript:void(0)" title="关闭ESP-R3服务器" onclick="jConfirm('确认关闭ESP-R3服务器吗？','确认窗口',function(r) {if(r){shutdown();}})"><img src="images/shutdown.png"/></a></div>
									<p>您好，<span class="font_b"><s:property value="#session.LOGIN_USER.username"/></span>	  
									<br><span class="text-key" id="welcomeDate"></span>
									<br>系统启动时间：<span class="text-key"><s:date name="runtime.starttime" format="yyyy-MM-dd HH:mm:ss"/></span>，共检索 <span class="text-key"><s:property value="runtime.searchQueryNum"/></span> 次	，平均响应时间：<span class="text-key"><s:property value="runtime.averageResponseTime"/>s</span>		</p>
									<span class="clear"></span>
								</div>
								<div id="quick">
									
									<div style="border-bottom:1px solid #A7D5F6">快速入口</div>
									<span style="width:100%;margin:0px;padding:0px;">
										<table style="width:100%;" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td style="width:10%;">
													<ul>
														<li><a href="javascript:changeFunction(312);"><img src="../../images/icons/user_32.png"> 用户管理 <br><span>User Manage</span></a></li>
														<li><a href="javascript:changeFunction(13);"><img src="../../images/icons/shield_32.png"> 系统设置 <br><span>System Setting</span></a></li>
														<li><a href="javascript:changeFunction(13);"><img src="../../images/icons/shield_32.png"> 索引维护 <br><span>System Setting</span></a></li>
													</ul>
												</td>
												<td style="border-left:5px solid #DEDEDE;padding-left:10px;width:390px;padding:5px;" valign="top">
													<div id="imagetask" style="background-color:#ffffff;">
														<%@ include file="/pages/taskList.jsp"%>	
													</div>
													<div style="float:right;background-color:#ffffff;">
														<img src="/images/list/metadata.png" onclick="jPrompt('图片采集目录','','添加图片采集任务',function(r){doGet('task.rv?data.resource='+encodeURIComponent(r),'imagetask');})"/>
													</div>
												</td>
												<td style="border-left:5px solid #DEDEDE;padding-left:10px;" valign="top">
													<div id="taskConsole" style="font-weight:normal;">
													<%@ include file="/pages/taskConsole.jsp"%>	
													</div>
												</td>
											</tr>
										</table>
									</span>
									<span class="clear"></span>
									<div style="border-bottom:1px solid #A7D5F6;border-top:1px solid #A7D5F6;">服务器状态</div>
									<table>
										<%@ include file="/pages/serverstates.jsp"%>	
									</table>
								</div>
							
								
							<!-- End Contents_List-->
						</div>

				<!-- End Contents-->
				</td>
			</tr>
		</table>
	</div>
		<hr style="height:1px;border:none;border-top:1px solid #a7d5f6;"/>
		<div style="width:100%;text-align:center;">
		Copyright © 2010 | Powered by: <a href="http://www.rivues.com" target="_blank">起点R3</a><br/>
		开源图像搜索IMAGE-R3<br/>
		<a href="mailto:rivulet.es@gmail.com">rivulet.es@gmail.com</a>
		</div>
		 

		</div>
		<!-- End Contents-->
		<div class="clears"></div>
		<!-- End Containers-->
		<div id="pubDiv"></div>
		<div id="temp" style="width=0px ; height:0px;display:none;"><!--temp div--></div> 
	</body>
</html>
