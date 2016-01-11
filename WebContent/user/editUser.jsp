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
	function check(){
		var name=document.getElementById("name");
		var pwd=document.getElementById("pwd");
		var pwd1=document.getElementById("pwd1");
		if(name.value=="admin"){
			alert("用户名已存在");
			return false;
		}else if(name.value==null||name.value==""){
			alert("用户名不能为空");
			return false;
		}else if(pwd.value==null||pwd.value==""){
			alert("密码不能为空");	
			return false;
		}else if(pwd1.value==null||pwd1.value==""){
			alert("确认密码不能为空");
			return false;
		}else if(pwd.value!=pwd1.value){
			alert("密码和确认密码不一致");
			return false;
		}else if(name.value.length>10){
			alert("用户名长度不符");
			return false;
		}else if(pwd.value.length>10){
			alert("密码长度不符");	
			return false;
		}else if(pwd1.value.length>10){
			alert("确认密码长度不符");
			return false;
		}
		else{
			return true;
		}
	}
</script>
</head>
<body>
	<!-- Header -->
	<div id="header">
		<div class="shell">
			<!-- Logo + Top Nav -->
			<div id="top">
				<h1>BBS论坛</h1>
				<div id="top-navigation">
					欢迎 <a href="#"><strong>${sessionScope.user.uname}</strong></a> <span>|</span>
					<a href="<%=request.getContextPath()%>/UserServlet?method=logout">退出</a>
				</div>
			</div>
			<!-- End Logo + Top Nav -->

			<!-- Main Nav -->
			<div id="navigation">
				<ul>
			    <li><a href="<%=request.getContextPath()%>/UserServlet?method=list" class="active"><span>用户管理</span></a></li>
			    <li><a href="<%=request.getContextPath()%>/SectionServlet?method=select&id=1"><span>论坛</span></a></li>
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
				<a href="<%=request.getContextPath()%>/UserServlet?method=list">用户管理</a> &gt; 用户修改
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
							<h2 class="left">用户修改</h2>

						</div>
						<!-- End Box Head -->

						<!-- Table -->
						<p>
							<font color="#FFFFFF">用户修改</font>
						</p>
						<center>
						<form id="form1" name="form1" method="post" 
							action="<%=request.getContextPath()%>/UserServlet?method=edit"
							onsubmit="return check()" >
							<table>
								<tr>
									<td><font color="#AD462D">用户名：</font></td>
									<td><input type="text" name="name" id="name"
										value="${user.uname }" /> <input type="hidden" name="uid"
										value="${user.uid }" /></td>
								</tr>
								<tr>
									<td><font color="#AD462D">密码：</font></td>
									<td><input type="password" name="pwd" id="pwd"
										value="${user.upassword }" /></td>
								</tr>
								<tr>
									<td><font color="#AD462D">确认密码：</font></td>
									<td><input type="password" name="pwd1" id="pwd1" value="${user.upassword }"/></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="提交" /> <input type="reset" value="重置" /></td>
								</tr>
							</table>
						</form>
						</center>
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