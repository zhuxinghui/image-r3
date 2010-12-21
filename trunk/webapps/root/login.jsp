<!doctype html public "-//w3c//dtd html 4.01 transitional//en" "http://www.w3c.org/tr/1999/rec-html401-19991224/loose.dtd">
<!-- saved from url=(0027)https://login.pepperio.net/ -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv=content-type content="text/html; charset=UTF-8" />
		<title>开源图像搜索引擎，IMAGE-R3</title>
		<LINK href="css/css.css" type=text/css rel=stylesheet>
		<LINK href="css/head.css" type=text/css rel=stylesheet>
		<LINK href="css/common.css" type=text/css rel=stylesheet>
		<LINK href="css/jquery.fancybox-1.3.0.css" type=text/css rel=stylesheet>
		<LINK href="css/jquery.alerts.css" type=text/css rel=stylesheet>
		<LINK href="css/Pager.css" type=text/css rel=stylesheet>
		<link rel="STYLESHEET" type="text/css"
			href="codebase/dhtmlxtree.css">

		<link href="css/login.css" type=text/css rel=stylesheet>
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
		<script src="codebase/rivu.js"></script>
		<script language="javascript">
			$(document).ready(function(){
				document.getElementById("data.username").focus();
		});
		</script>
	</head>
	<body>
		<div id=login style="left: 50%; top: 50%">
			<div id=login_panel>
				<div id=input_block>
					<form id="form" name="login" action="login.rv" method="post" onsubmit="return doLogin(this,function(){location.href='welcome.rv'},function(){document.getElementById('data.username').focus();});">
						<div>
							<h6>
								<s:text name="login.username"/>
							</h6>
							<input class="txt_field required" id="data.username" maxlength=55 size=25
								name="data.username">
							<em><s:text name="login.usernametitle"/></em>
							<h6>
								<s:text name="login.password"/>
							</h6>
							<input class="required txt_field" id="password" type="password" maxlength=55
								size=15 name="data.password">
							<input id=btnlogon type=submit value=login name=btnlogon>
							<br>
							<a href="mailto:rivulet.es@gmail.com"><s:text name="login.forgetpassword"/></a>
							<br/><s:text name="login.defaultadmin"/><b>123456</b>
							<br/><s:text name="login.defaultuser"/><b>123456</b>
							<br>
							<strong><b>version 4.5.0</b></strong>
						</div>
					</form>
				</div>
				<div id=error_block>
					<p class=warning>
						<s:text name="login.logintip"/>						
					</p>
				</div>
			</div>
			<div id=login_copyright>
				<p>
					<s:text name="login.copyright"/>					
				</p>
				<br>
			</div>
			<div id="tempDiv" style="display:none;width:0px;height:0px"/>
		</div>
	</body>
</html>
