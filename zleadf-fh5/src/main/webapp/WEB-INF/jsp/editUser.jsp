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
    
    <title>编辑用户</title>
    
	<script type="text/javascript">
	function updateUser(){
		var form = document.forms[0];
		form.action = "<%=basePath%>user/updateUser";
		form.method="post";
		form.submit();
	}
</script>

  </head>
  
  <body>
    <h1>添加用户</h1>
	<form action="" name="userForm">
		<input type="hidden" name="id" value="${user.id }"/>
		姓名：<input type="text" name="username" value="${user.username }"><br/>
		密码：<input type="password" name="password" value="${user.password }"><br/>
		权限：<select name="status" value="${user.status }">
				<option value="1" <c:if test="${user.status == '1'}">selected="selected"</c:if>>管理员</option>
				<option value="2" <c:if test="${user.status == '2'}"> selected</c:if>>普通用户</option>
				</select><br/>
		roleId：:<select name="roleId" value="${user.roleId }">
				<option value="1" <c:if test="${user.roleId == '1'}">selected="selected"</c:if>>1</option>
				 <option value="2" <c:if test="${user.roleId == '2'}"> selected</c:if>>2</option>
				</select><br/>
		<input type="button" value="提交编辑" onclick="updateUser()"/>
	</form>
  </body>
  
</html>
