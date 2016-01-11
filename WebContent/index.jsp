<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>首页</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/stylemain.css" type="text/css" media="all" />
</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- 顶部 -->
		<div id="top">
			<h1>BBS论坛</h1>
			<div id="top-navigation">
				欢迎 <a href="#"><strong>${sessionScope.user.uname}</strong></a>
				<span>|</span>
				<a href="<%=request.getContextPath()%>/UserServlet?method=logout">退出</a>
			</div>
		</div>
		
		
		<!-- 导航 -->
		<div id="navigation">
			<ul>
			    <li><a href="<%=request.getContextPath()%>/UserServlet?method=list" class="active"><span>用户管理</span></a></li>
			    <li><a href="<%=request.getContextPath()%>/SectionServlet?method=select&id=1"><span>论坛</span></a></li>
			    <li><a href="<%=request.getContextPath()%>/help.jsp"><span>帮助</span></a></li>
			</ul>
		</div>
	</div>
</div>


<!-- 顶部 -->
<div id="footer">
	<div class="shell">
		<span class="left">&copy; 2014</span>
		<span class="right">
			版本 2014V1.0
		</span>
	</div>
</div>
</body>
</html>