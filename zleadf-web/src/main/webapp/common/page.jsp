<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


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
	function jump(p,s){
		//window.location.href="/news/getUserServlte?currentPage="+p+"&pageSize="+s;
		document.submitForm.currentPage.value=p;
		document.submitForm.pageSize.value=s;
		//document.submitForm.action=<%=basePath%>user/getAllUser;
		document.submitForm.submit();
	}
	function color(a,index){
		if(index % 2 == 0){
			a.style.background='#F0F0F0';
		}else{
			a.style.background='';
		}
	}
</script>

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
					</select>
			</td>
		</tr>
	</table>