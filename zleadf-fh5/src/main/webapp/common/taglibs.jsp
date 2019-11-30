<%@ page import="com.up72.zx.util.ProjectProperties" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="http://www.up72.com" prefix="up72" %>
<%@ taglib uri="http://www.up72.com/p" prefix="p" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/> <%-- 上下文路径，用于页面中的请求 --%>
<c:set var="static_ctx" value="${pageContext.request.contextPath}"/> <%-- 静态资源上下文路径，用于图片、js、css等静态资源 --%>
<c:set var="file_ctx" value="<%=ProjectProperties.fileCtx%>"/> <%-- 上传文件上下文路径，用于分布式文件上传与下载，仅在使用了分布式文件上传时有用 --%>
<c:set var="defaultImg" value="${file_ctx}/group1/M00/00/00/000000000000000000000000000001.png"/> <%-- 默认图片 --%>
<c:set var="v" value="2016-12-08" />
<c:set var="ctrl_ctx" value="<%=ProjectProperties.urlPrefix%>"/><%-- 服务器公共路径 --%>