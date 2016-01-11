<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" type="text/css"
	media="all" />
<script type="text/javascript">
	function check(){
		var ttitle=document.getElementById("ttitle");
		var tcontents=document.getElementById("tcontents");
		if(ttitle.value==null||ttitle.value==""){
			alert("帖子名不能为空  ");
			return false;
		}else if(ttitle.value.length>20){
			alert("帖子名长度不符");
			return false;
		}else if(tcontents.value==null||tcontents.value==""){
			alert("内容不能为空 ");
			return false;
		}else if(tcontents.value.length>1000){
			alert("内容长度不符");
			return false;
		}else{
			document.aform.action="TopicServlet?method=save&id=1&sid=${requestScope.section.sid}";
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
				<span>&gt;</span>${requestScope.section.sname}
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
							<h2 class="left">帖子列表</h2>
							<div class="right">
								<label>帖子搜索</label> <input type="text" class="field small-field" />
								<input type="submit" class="button" value="搜索" />
							</div>
						</div>
						<!-- End Box Head -->

						<!-- Table -->
						<div class="table">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th align="left">帖子名</th>
									<th align="left">发帖人</th>
									<th align="left">发帖时间</th>
									<th align="left">回帖数</th>
								</tr>
								<c:forEach items="${requestScope.topicList}" var="topic">
									<tr>
										<td><a
											href="ReplyServlet?method=select&id=1&tid=${topic.tid}">${topic.ttitle}</a></td>
										<td>${topic.user.uname}</td>
										<td>${topic.ttime}</td>
										<td>${topic.tnumber}</td>
									</tr>
								</c:forEach>

							</table>



						</div>
						<!-- Table -->

					</div>
					<!-- End Box -->

					<!-- Box -->
					<div class="box">
						<!-- Box Head -->
						<div class="box-head">
							<h2>发表新帖</h2>
						</div>
						<!-- End Box Head -->

						<form
							action=""
							method="post" name="aform">

							<!-- Form -->
							<div class="form">
								<p>
									<span class="req">最多输入40个字符</span> <label>帖子名 <span></span>
									</label> <input type="text" name="ttitle" id="ttitle" class="field size1" />
								</p>
								<p>
									<span class="req">最多输入1000个字符</span> <label>内容 </label>
									<textarea class="field size1" name="tcontents" id="tcontents" rows="10"
										cols="30"></textarea>
								</p>

							</div>
							<!-- End Form -->

							<!-- Form Buttons -->
							<div class="buttons" align="center">
								<input type="hidden" name="sid" value="${requestScope.sid}" /> <input
									type="submit" class="button" onclick="return check()" value="提交" /> <input type="reset"
									class="button" value="重置" />
							</div>
							<!-- End Form Buttons -->
						</form>
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