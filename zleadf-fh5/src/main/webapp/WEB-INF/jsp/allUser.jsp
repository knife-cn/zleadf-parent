<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>用户列表</title>
        
    <style type="text/css">
		body,table,tr,td{
			padding:0;
			margin:0;
			text-align:center;
			font-size:14px;
			height:20px;
		}
		.main_table{
			width:98%;
			border-collapse: collapse;
			margin:0 auto;
		}
    </style>
    
	<script type="text/javascript">
	function del(id){
		$.get("<%=basePath%>user/delUser?id=" + id,function(data){
			if("success" == data.result){
				alert("删除成功");
				window.location.reload();
			}else{
				alert("删除失败");
			}
		});
	}
	
		    window.onload = function () {		//隔行变色方法
            var otab = document.getElementById('tab1');
            var thiscolor = '';
            for (var i = 0; i < otab.tBodies[0].rows.length; i++) {
                otab.tBodies[0].rows[i].onmouseover = function () {
                    thiscolor = this.style.background;
                    this.style.background = '#FF2D2D';
                };
                otab.tBodies[0].rows[i].onmouseout = function () {
                    this.style.background = thiscolor;
                };
                if (i % 2) {
                    otab.tBodies[0].rows[i].style.background = '';
                }
                else {
                    otab.tBodies[0].rows[i].style.background = '#ccc';
                };
            };
        };	
	
	
	function jjump(p,s){
		//window.location.href="/news/getUserServlte?currentPage="+p+"&pageSize="+s;
		var reg = /^\d*$/g; //限定数字
		if(!reg.test(p)){
			var p = 1;
		}
		
		document.submitForm.currentPage.value=p;
		document.submitForm.pageSize.value=s;
		//document.submitForm.action=<%=basePath%>user/getAllUser;
		document.submitForm.submit();
	}
	/*
	function color(a,index){
		if(index % 2 == 0){
			a.style.background='#F0F0F0';
		}else{
			a.style.background='';
		}
	}
	*/
		
	    function jump(p,s){
		//window.location.href="${ctx}/user/findUsers.action?page.currentPage="+p+"&page.pageSize="+s;
		var reg = /^\d*$/g; //限定数字
		if(!reg.test(p)){
			var p = 1;
		}
		
		
		if(parseInt(p) < 1){
			p = 1;
		}else if(parseInt(p) > parseInt(document.submitForm.pageCount.value)){  
			p = parseInt(document.submitForm.pageCount.value);
		}	
			document.submitForm.currentPage.value=p;
			document.submitForm.pageSize.value=s;
			//document.submitForm.action=<%=basePath%>user/getAllUser;
			document.submitForm.submit();
		}	
		
</script>
  </head>
  
  <body>
  <br/>
  欢迎您！ ${sessionScope.user.username}&nbsp&nbsp权限：<td> ${sessionScope.user.status == "1" ? "<span style='color:red'>管理员</span>":"普通用户"}</td><br/>
  <hr/>
  	<form name="submitForm" action="<%=basePath%>user/getAllUser" method="post" onsubmit="jump(1,${pager.pageSize })">
  		<input type="hidden" name="currentPage" >
		<input type="hidden" name="pageSize" >
		<input type="hidden" name="pageCount" value="${pager.pageCount }"><!-- 给Pager添加一个set方法解决报错 -->
		<table style="width:70%;margin:0 auto;">
  		<tr>
  			<td>用户名：<input type="text" name="username" value="${user.username }"/></td>
  			<td>密码：<input type="text" name="password" value="${user.password }" /></td>
  			<td><input type="submit" value="查询" /></td>
  		</tr>
  	</table>
  	</form>
	<table border="1"  id="tab1" class="main_table" >
		<tbody>
			<tr style="background-color: gray;" >
				<th>序号</th>
				<th>姓名</th>
				<th>密码</th>
				<th>权限</th>
				<th>创建时间</th>
				<th>roleId</th>
				<th>操作/<a href="<%=basePath%>user/toAddUser">增加</a></th>
			</tr >
			<c:if test="${!empty userList }">
				<c:forEach items="${userList}" var="user" varStatus="s">
					<tr align="center" <c:if test="${s.index%2==0 }">style="background-color:#F0F0F0"</c:if> onmouseover="this.style.background='#C0C0C0'" onmouseout="color(this,${s.index})" >
						<td>${(pager.currentPage-1)*pager.pageSize+s.index+1 }</td>
						<td>${user.username }</td>
					<td>${user.password }</td>
						<td>${user.status == "1" ? "<span style='color:red'>管理员</span>":"普通用户"}</td>
						                           
						<td>
						<fmt:formatDate value="${user.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/> 
						</td>
						<td>${user.roleId }</td>
						<td>
							<a href="<%=basePath%>user/getUser?id=${user.id}">编辑</a>
							<a href="javascript:del('${user.id }')">删除</a>
						</td>
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<table>
		<tr>
			<td>
			第<span style="color: red;">${pager.currentPage }</span>/${pager.pageCount }页
			共${pager.total }条
			</td>
			<td style="text-align:right;">
				<c:choose>
					<c:when test="${pager.currentPage==1 }">
						首页
						上一页
					</c:when>
					<c:otherwise>
						<a href="javascript:jump(1,${pager.pageSize })">首页</a>
						<a href="javascript:jump(${pager.currentPage-1 },${pager.pageSize })">上一页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pager.currentPage==pager.pageCount }">
						下一页
						末页
					</c:when>
					<c:otherwise>
						<a href="javascript:jump(${pager.currentPage+1 },${pager.pageSize })">下一页</a>
						<a href="javascript:jump(${pager.pageCount },${pager.pageSize })">末页</a>
					</c:otherwise>
				</c:choose>
				跳到:<input style="width:20px" id="gPage" type="text" value="${pager.currentPage }" />
				<input type="button" value="go" onclick="jump(document.getElementById('gPage').value,${pager.pageSize })" />
				每页显示:<select onchange="jump(1,this.value)">
						<option value="3" <c:if test="${pager.pageSize==3 }">selected="selected"</c:if>>3</option>
						<option value="5" <c:if test="${pager.pageSize==5 }">selected="selected"</c:if>>5</option>
						<option value="10" <c:if test="${pager.pageSize==10 }">selected="selected"</c:if>>10</option>
						<option value="15" <c:if test="${pager.pageSize==15 }">selected="selected"</c:if>>15</option>
						<option value="20" <c:if test="${pager.pageSize==20 }">selected="selected"</c:if>>20</option>
					</select>
			</td>
		</tr>
	</table>
  </body>
</html>
