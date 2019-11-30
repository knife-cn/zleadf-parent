<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="config.jsp" %>

<%!
    //判断对象不为null或空
//    private static final boolean $isNotEmpty(Object pattern) {
//        return Ognl.isNotEmpty(pattern);
//    }

    private static final String $getParam(ServletRequest request, String name,
                                          String defval) {
        String param = request.getParameter(name);
        return (param != null ? param : defval);
    }

    private static final Long $getParam(ServletRequest request, String name,
                                          Long defval) {
        Long result = null;
        String param = request.getParameter(name);
        if(param != null){
            result = Long.parseLong(param);
        }else{
            result = defval;
        }
        return result;
    }

    private static final int $getParam(ServletRequest request, String name,
                                       int defval) {
        String param = request.getParameter(name);
        int value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    private static final Long[] $getParams(ServletRequest request, String name) {
        String[] params = request.getParameterValues(name);
        if (params == null) {
            return null;
        }
        int j = params.length;
        Long[] result = new Long[j];
        for (int i = 0; i < j; i++) {
            result[i] = Long.valueOf(params[i]);
        }
        return result;
    }
%>
