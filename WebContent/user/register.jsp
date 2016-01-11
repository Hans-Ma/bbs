<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
<style type="text/css">
body {
	
}
</style>
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

<body style="background-color: #A7432B; margin-top:10%;">

<center>
<p><font color="#FFFFFF">用户注册</font></p>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/UserServlet?method=register" onsubmit="return check()">
  <table>
  	<tr>
        <td><font color="#FFFFFF">用户名：</font></td>
        <td><input type="text" name="name" id="name" value=""/></td>
    </tr>
    <tr>
        <td><font color="#FFFFFF">密码：</font></td>
        <td><input type="password" name="pwd" id="pwd" /></td>
    </tr>
    <tr>
    <td><font color="#FFFFFF">确认密码：</font></td>
	<td><input type="password" name="pwd1" id="pwd1" /></td>
    </tr>
    <tr>
    	<td colspan="2" align="center">
        	<input type="submit" value="提交"/>
            <input type="reset" value="重置"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>
