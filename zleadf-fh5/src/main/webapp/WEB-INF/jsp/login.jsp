<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/form.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btn").click(function(){
				$("#myform").ajaxSubmit({
					success: function(responseText, statusText, xhr, $form){
						if((responseText.msg)=="suc"){
			        		alert("返回成功!");
						}
					}
				});
				
			});
			
		});
	</script>
  </head>
  
  <body>
    <form id="myform" action="<%=basePath%>user/login" method="post" ><!-- 这里的action可以不写，由控制器去控制 -->
    		
    	用户名：<input type="text" name="username" ><br>
    	<!-- <input type="hidden" name="username" value="test"> -->
    	密码：<input type="password" name="password" ><br>
    	<input id="btn" type="submit" value="提交" >
    </form>
  </body>
</html>
