<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/stylemain.css" type="text/css"
	media="all" />
</head>
<body>
	<!-- Header -->
	<div id="header">
		<div class="shell">
			<!-- Logo + Top Nav -->
			<div id="top">
				<h1>BBS论坛</h1>
				<div id="top-navigation">
					欢迎 <a href="#"><strong>${sessionScope.user.uname}</strong></a>
					<span>|</span> <a
						href="<%=request.getContextPath()%>/UserServlet?method=logout">退出</a>
				</div>
			</div>
			<!-- End Logo + Top Nav -->

			<!-- Main Nav -->
			<div id="navigation">
				<ul>
			    <li><a href="<%=request.getContextPath()%>/UserServlet?method=list"><span>用户管理</span></a></li>
			    <li><a href="<%=request.getContextPath()%>/SectionServlet?method=select&id=1" class="active"><span>论坛</span></a></li>
			    <li><a href="<%=request.getContextPath()%>/help.jsp"><span>帮助</span></a></li>
			</ul>
			</div>
			<!-- End Main Nav -->
		</div>
	</div>
	<!-- End Header -->

	<!-- Container -->
	<div id="container">
		<div class="shell">

			<!-- Small Nav -->
			<div class="small-nav">
				<a
					href="<%=request.getContextPath()%>/SectionServlet?method=select&id=1">所有版块</a>
			</div>
			<!-- End Small Nav -->

			<br />
			<!-- Main -->
			<div id="main">
				<div class="cl">&nbsp;</div>

				<!-- Content -->
				<div id="content">

					<!-- Box -->
					<div class="box">
						<!-- Box Head -->
						<div class="box-head">
							<h2 class="left">版块列表</h2>
							<div class="right">
								<label>搜索版块</label> <input type="text" class="field small-field" />
								<input type="submit" class="button" value="search" />
							</div>
						</div>
						<!-- End Box Head -->

						<!-- Table -->
						<div class="table">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th>版块名</th>
									<th>版块信息</th>
									<th>拥有帖子数</th>
									<th>版主名</th>
								</tr>
								<c:forEach items="${requestScope.sectionList}" var="section">
									<tr>
										<td><a
											href="TopicServlet?method=select&sid=${section.sid}&id=1&sname=${section.sname}">${section.sname}</a></td>
										<td>${section.sinfo}</td>
										<td>${section.snumber}</td>
										<td>${section.user.uname}</td>
									</tr>
								</c:forEach>
							</table>


						</div>
						<!-- Table -->
					</div>
					<!-- End Box -->
				</div>
				<!-- End Content -->


				<div class="cl">&nbsp;</div>
			</div>
			<!-- Main -->
		</div>
	</div>
	<!-- End Container -->

	<!-- Footer -->
	<div id="footer">
		<div class="shell">
			<span class="left">&copy; 2014</span> <span class="right"> 版本
				2014V1.0 </span>
		</div>
	</div>
</body>
</html>