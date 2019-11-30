<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户</title>
    
	<script type="text/javascript">
	function addUser(){
		var form = document.forms[0];
		form.action = "<%=basePath%>user/addUser";
		form.method="post";
		form.submit();
	}
</script>

  </head>
  
  <body>
    <h1>添加用户</h1>
	<form action="" name="userForm">
		姓名：<input type="text" name="username"><br/>
		密码：<input type="password" name="password"><br/>
		权限：<select name="status">
				<option value="1" <c:if test="${user.status == '1'}">selected="selected"</c:if>>管理员</option>
				<option value="2" <c:if test="${user.status == '2'}"> selected</c:if>>普通用户</option>
				</select><br/>
		roleId：:<select name="roleId">
				<option value="1" <c:if test="${user.roleId == '1'}">selected="selected"</c:if>>1</option>
				 <option value="2" <c:if test="${user.roleId == '2'}"> selected</c:if>>2</option>
				</select>
		<input type="button" value="添加" onclick="addUser()">
	</form>
  </body>
</html>
