<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<script type="text/javascript">
	function check(){
		var rcontents=document.getElementById("rcontents");
		if(rcontents.value==null||rcontents.value==""){
			alert("内容不能为空 ");
			return false;
		}else if(rcontents.value.length>1000){
			alert("内容长度不符");
			return false;
		}else{
			document.aform.action="<%=request.getContextPath()%>/ReplyServlet?method=save&tid=${requestScope.topic.tid}";
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
				<span>&gt;</span> <a
					href="TopicServlet?method=select&sid=${requestScope.topic.section.sid}&id=1&sname=${requestScope.topic.section.sname}">${requestScope.topic.section.sname}</a>
				<span>&gt;</span> ${requestScope.topic.ttitle}
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
							<h2 class="left">回帖显示</h2>
							<div class="right">
								<label>搜索回帖</label> <input type="text" class="field small-field" />
								<input type="submit" class="button" value="search" />
							</div>
						</div>
						<!-- End Box Head -->

						<!-- Table -->
						<div class="table">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<table width="100%">
											<tr>
												<th align="left">楼主：${requestScope.topic.user.uname}</th>
												<th align="right">发帖时间：${requestScope.topic.ttime}</th>
											</tr>
											<tr>
												<td colspan="2">${requestScope.topic.tcontents}</td>
											</tr>
										</table>
									</td>
								<tr>
									<c:forEach items="${requestScope.replyList}" var="reply">
										<tr>
											<td>
												<table width="100%">
													<tr>
														<th align="left">回帖人：${reply.user.uname}</th>
														<th align="right">回帖时间：${reply.rtime}</th>
													</tr>
													<tr>
														<td colspan="2">${reply.rcontents}</td>
													</tr>
												</table>
											</td>
										<tr>
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
							<h2>回复</h2>
						</div>
						<!-- End Box Head -->

						<form
							action="" name="aform"
							method="post">

							<!-- Form -->
							<div class="form">
								<p>
									<span class="req">最多输入1000个字符</span>
									<textarea class="field size1" name="rcontents" id="rcontents" rows="10"
										cols="30"></textarea>
								</p>

							</div>
							<!-- End Form -->

							<!-- Form Buttons -->
							<div class="buttons">
								<input type="hidden" name="tid" value="${requestScope.tid}" />
								<input type="submit" onclick="return check()" class="button" value="提交" /> <input
									type="reset" class="button" value="重置" />
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