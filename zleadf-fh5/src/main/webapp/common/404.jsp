<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>页面不存在</title>
    <link type="text/css" rel="stylesheet" href="${static_ctx}/adminStyles/css/global.css" />
    <style>
        body{background:#ECECEC;}
        .wrapper{width:880px; height: 100%; margin:0 auto;}
        .wrapper-con{ margin-top:30%; width:100%; display: inline-block; text-align: center;}
    </style>
</head>
<body>
<div class="container min-w1000">
    <div class="wrapper">
        <div class="wrapper-con">
            <img src="${static_ctx}/adminStyles/images/error-6.png">
        </div>
        <div style="text-align: center">
            <button style="width: 100px;height: 40px;border-radius: 5px;border: 1px solid grey;" onclick="window.top.location.href='${ctx}'">返回首页</button>
        </div>
    </div>
</div>
</body>
</html>