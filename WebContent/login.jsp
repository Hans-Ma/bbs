<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript">
	function check(){
		
		return true;
	}
	function register(){
		window.location.href="<%=request.getContextPath()%>/user/register.jsp";
	}
</script>
</head>
<body>
	<form action="UserServlet"  method="post" id="myform" name="myform">
		<table border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td><input type="hidden" name="method" value="login" /></td>
			</tr>
		<!-- 上 -->
			<tr>
			<td  background="images/login_04.gif" width="862px" height="266px"></td>
			</tr>
		<!-- 中 -->
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td background="images/login_06.gif" width="424px" height="95px"></td>
						<td background="images/login_07.gif" width="183px" height="95px">
							<table>
								<tr height="30px">
									<td class="word">用户</td>
									<td><input type="text" id="username" name="username" style="width:130px"/>
								</tr>
								<tr height="30px">
									<td class="word">密码</td>
									<td><input type="password" id="password" name="password" style="width:130px"/>
								</tr>
								<tr>
									<td colspan="2" style="text-align:center">
										<input type="submit" id="submit1" value="登陆" />
										<input type="button" id="reg" value="注册"  onclick="register()"/>
									</td>
								</tr>
							</table>
						</td>
						<td background="images/login_08.gif" width="255px" height="95px"></td>
					</tr>
					</table>
				
				</td>
			</tr>
		<!-- 下 -->
			<tr>
			<td height="247" width="862px" background="images/login_09.gif"></td>			
			</tr>
		</table>
		
	</form>
</body>
</html>