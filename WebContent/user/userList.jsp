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
	<script type="text/javascript">
	function del(uid){
		if(!confirm("是否确认删除?")){
			return;
		}
		window.location.href="<%=request.getContextPath()%>/UserServlet?method=del&uid="+uid;
	}
	
	function search(){
		var searchVal = document.getElementById("searchVal").value;
		window.location.href="<%=request.getContextPath()%>/UserServlet?method=list&searchVal="+searchVal;
	}
	</script>
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
	<!-- End Header -->

	<!-- Container -->
	<div id="container">
		<div class="shell">

			<!-- Small Nav -->
			 <div class="small-nav">
				<a href="<%=request.getContextPath()%>/UserServlet?method=list">用户管理</a>
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
							<h2 class="left">用户列表</h2>
							<div class="right">
								<label>搜索</label> <input type="text" class="field small-field" id="searchVal"/>
								<input type="submit" class="button" value="search" onclick="search()"/>
							</div>
						</div>
						<!-- End Box Head -->

						<!-- Table -->
						<div class="table">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th>用户ID</th>
									<th>用户名</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${requestScope.userList}" var="user">
									<tr>
										<td>${user.uid}</td>
										<td>${user.uname}</td>
										<td>${user.ustatus}</td>
										<td>
											<a href="<%=request.getContextPath()%>/UserServlet?method=toEdit&uid=${user.uid}">修改</a>
											<a href="#" onclick="del('${user.uid}')">删除</a>
										</td>
									</tr>
								</c:forEach>
							</table>


							<!-- Pagging -->

							<%-- <div class="pagging">
								<div class="left">共${requestScope.pageSum}条纪录，当前第${requestScope.goPage}/${requestScope.countPage}页，每页${requestScope.pagenum}条纪录</div>
								<div class="right">
									<c:if
										test="${requestScope.goPage<=1&&requestScope.goPage!=requestScope.countPage}">
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&goPage=${requestScope.goPage+1}&id=1">下一页</a>
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&goPage=${requestScope.countPage}&id=1">尾页</a>
									</c:if>
									<c:if
										test="${requestScope.goPage!=1&&requestScope.goPage>=requestScope.countPage}">
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&id=1">首页</a>
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&goPage=${requestScope.goPage-1}&id=1">上一页</a>
									</c:if>
									<c:if
										test="${requestScope.goPage!=1&&requestScope.goPage!=requestScope.countPage}">
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&id=1">首页</a>
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&goPage=${requestScope.goPage-1}&id=1">上一页</a>
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&goPage=${requestScope.goPage+1}&id=1">下一页</a>
										<a
											href="<%=request.getContextPath()%>/SectionServlet?method=select&goPage=${requestScope.countPage}&id=1">尾页</a>
									</c:if>
									<c:if
										test="${requestScope.goPage==1&&requestScope.goPage==requestScope.countPage}"></c:if>
								</div>
							</div> --%>
							<!-- End Pagging -->
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