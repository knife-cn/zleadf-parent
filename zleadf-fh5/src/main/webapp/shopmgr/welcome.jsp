<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/13
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/bootstrap-table.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/qyDetails.css" rel="stylesheet">
    <link href="css/fx.css" rel="stylesheet">
    <script src="js/qrcode.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-table.min.js"></script>
    <script src="js/bootstrap-table-zh-CN.min.js"></script>
    <script src="js/bootstrapValidator.min.js"></script>
    <script src="js/area.js"></script>
    <script src="js/layer.min.js"></script>
    <script src="js/fileinput.min.js"></script>
    <script src="js/config/comn.js?v=1"></script>
    <script src="js/control/welcome.js"></script>
    <title>注册成功</title>
</head>
<body>
<input type="hidden" id="memberId" value="${memberId}">
<input type="hidden" id="memberType" value="${memberType}">
<input type="hidden" id="username" value="${username}">
<input type="hidden" id="password" value="${password}">
<input type="hidden" id="shopId" value="${shopId}">
<div class="one">
    <div class="div-one">
        <div class="businessTltle">
        </div>
        <div class="div-top">
            <div class="one-title">
                <h5 class="color">注册成功</h5>
                <img class="redLine" src="./images/line.png"/>
            </div>
            <input type="hidden" class="form-control" id="id" name="id" value=""/>
            <div class="one-center">
                <div class="one-one">
                    <img src="./img/icon-yonghu.png"/>
                    <span id="username" name="username">账号:${username}</span>
                </div>
                <div class="one-one">
                    <img src="./img/icon-mima.png"/>
                    <span id="password" name="password">密码:${username}</span>
                </div>
            </div>
            <div id="count"> <span>请牢记账号密码,<span id="num">10</span>秒后跳转到主页</span></div>

        </div>
        <p class="line"></p>
    </div>

</div>

</body>
</html>
