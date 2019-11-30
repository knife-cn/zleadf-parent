<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" session="false" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/function.jsp" %>

<%
    // 异常页面，根据请求的IP来判断是内网还是外网，如果是内网，显示的是异常堆栈信息，如果是外网，显示的是“功能建设中”
    Throwable throwable = exception;
    String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    if (throwable == null) {
        throwable = (Throwable) request.getAttribute("exception");
        requestUri = (String) request.getAttribute("requestUri");
    }
    LoggerFactory.getLogger(requestUri).error("系统异常", throwable); // 打印日志
    throwable.getMessage();
    String errorMsg = throwable.getMessage();
    request.setAttribute("requestUri", requestUri);
    request.setAttribute("isInnerIp", isInnerIp(request));
    request.setAttribute("errorMsg", errorMsg);
%>

<%!

    // 是否为内网IP
    private boolean isInnerIp(HttpServletRequest request) {
        String ip = getIpAddr(request);
        if (ip.startsWith("192.168.") || ip.startsWith("127.0.") || ip.startsWith("localhost")) {
            return true;
        }
        return false;
    }

    //根据请求获取请求方的本地IP
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        if (ip != null && ip.startsWith("0:")) {
            ip = "localhost";
        }
        return ip;
    }

%>

<html>
<head>
    <title>${isInnerIp ? "系统异常" : "功能建设中..."}</title>
    <script language="javascript">
        function showDetail() {
            var elm = document.getElementById('detail_system_error_msg');
            if (elm.style.display == '') {
                elm.style.display = 'none';
            } else {
                elm.style.display = '';
            }
        }
    </script>
</head>

<body>

<c:if test="${isInnerIp}">
    <div id="content">
        <h3>
            系统异常，请求的uri为：${requestUri}<br/>
        </h3>
        <b>错误信息:${errorMsg}</b>
        <br>
        <div id="detail_system_error_msg">
            <pre><%throwable.printStackTrace(new java.io.PrintWriter(out));%></pre>
        </div>
    </div>
</c:if>
<c:if test="${not isInnerIp}">
    <div style="width: 100%;height: 100%;text-align: center;vertical-align: middle;margin-top:20%;">
        <h1>功能建设中...</h1><br>
    </div>
</c:if>


</body>
</html>