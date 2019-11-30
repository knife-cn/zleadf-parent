<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    // 说明：这个jsp用于检查请求的IP地址是否为指定的地址，如果不是则跳转到404页面

    // 允许的IP地址
    private String[] allowIpArr = new String[]{"61.148.220.94"};

    // 检查，如果不是允许的IP，则跳转到404页面
    private void checkIp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = getFullIpAddr(request);
        // 如果是内网IP，则允许执行
        if (ip.startsWith("192.168.") || ip.startsWith("127.0.") || ip.startsWith("0:0:0:0:0:0:0:1")) {
            return;
        }
        boolean isAllow = false; // 默认是不允许的
        for (String allowIp : allowIpArr) {
            if (ip.contains(allowIp)) {
                isAllow = true;
                break;
            }
        }
        if (!isAllow) {
            response.sendRedirect(request.getContextPath() + "/common/404.jsp");
        }
    }

    // 根据请求获取请求方的本地IP，可能是多个IP，中间以逗号分隔，可能有空格
    public String getFullIpAddr(HttpServletRequest request) {
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
        return ip;
    }
%>
