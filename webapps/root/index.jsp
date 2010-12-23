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
		<LINK href="css/css.css" type=text/css rel=stylesheet>
	</head>
	<body>
		<div class="header_container">
			<div class="header_top">
				<div class="logo">
					
				</div>
			</div>
		</div>
		<!-- End header-->

		<!-- End Containers-->

		<!-- End Contents-->
		<div class="Contents">
			<div class="Search">
				<div class="logo_image">
					
				</div>
				<div class="searchdiv">
					
					<div class="searchForm">
						<div class="searchtext">请输入图片地址或检索关键词</div>
						<form action="/search.rv" method="get">
						<input type="hidden" name="ps" value="100"/>
						<ul>
							<li style="margin-top:2px;"><input type="text" maxlength="100000" name="q" id="url_box" class="searchinput"></li>
							<li style="margin-left:10px;"><input type="image" type="submit" src="images/searchbtn.png"></li>
							<li style="margin-left:10px;"><input type="image" type="submit" src="images/searchbtn.png"></li>
						</ul>
						</form>
						<div style="clear:both;"></div>
						<div class="searchtip">检索关键词或图片地址，例如：圣诞，http://www.domain.com/logo.gif , 或上传一副本地图片</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
