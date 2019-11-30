<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="toolsUtil.jsp" %>
<%
    /** mvc方法 **/
    final String $MD_E = "edit";//3
    final String $MD_F = "form";//2
    final String $MD_D = "del";//4
    final String $MD_P = "page";//1
    final String $MD_V = "view";//5
    final String $MD_CF = "createFile";//6 createFile创建文件
    final String $MD_P_JSON = "page.json";//7
    final String $MD_V_JSON = "view.json";//8

    pageContext.setAttribute("MD_E", $MD_E);
    pageContext.setAttribute("MD_F", $MD_F);
    pageContext.setAttribute("MD_D", $MD_D);
    pageContext.setAttribute("MD_P", $MD_P);
    pageContext.setAttribute("MD_V", $MD_V);
    pageContext.setAttribute("MD_CF", $MD_CF);
    pageContext.setAttribute("MD_P_JSON", $MD_P_JSON);
    pageContext.setAttribute("MD_V_JSON", $MD_V_JSON);
/** mvc方法 **/
%>
<%!

    //mvc方法开始
    //mvc判断
    private static final Integer $swithMD(HttpServletRequest request) {
        Integer result = 1;
        HashMap<String, Integer> methods = (HashMap<String, Integer>) request.getAttribute("$MD_MAP");
        String method = $getParam(request, "method", "page");
        if (methods.containsKey(method)) {
            result = methods.get(method);
        }
        return result;
    }

    private static final String $getMD(HttpServletRequest request) {
        String method = $getParam(request, "method", "page");
        request.setAttribute("method", method);
        return method;
    }

    private static final boolean $isMD(String md, HttpServletRequest request) {
        String method = $getMD(request);
        if (md.equals($getMD(request))) {
            return true;
        }
        return false;
    }

    private static final boolean $isMDJson(HttpServletRequest request) {
        String method = $getMD(request);
        if (method.endsWith(".json")) {
            return true;
        }
        return false;
    }

    //初始化method方法
    private static final void $initMD(HttpServletRequest request) {
        HashMap<String, Integer> $MD_MAP = new HashMap<String, Integer>();
        $MD_MAP.put("page", 1);
        $MD_MAP.put("form", 2);
        $MD_MAP.put("edit", 3);
        $MD_MAP.put("del", 4);
        $MD_MAP.put("view", 5);
        $MD_MAP.put("createFile", 6);
        $MD_MAP.put("page.json", 7);
        $MD_MAP.put("view.json", 8);
        request.setAttribute("$MD_MAP", $MD_MAP);
    }

    //添加method方法
//    private static final void $addMD(HttpServletRequest request, String methodKey, Integer methodNum) {
//        HashMap<String, Integer> $MD_MAP = null;
//        if (!$isNotEmpty(request.getAttribute("$MD_MAP"))) {
//            $initMD(request);
//            $MD_MAP = (HashMap<String, Integer>) request.getAttribute("$MD_MAP");
//        }
//        $MD_MAP.put(methodKey, methodNum);
//    }
//mvc方法结束
%>
