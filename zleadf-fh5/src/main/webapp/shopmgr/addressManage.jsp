<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
		<!-- 宽度设置为设备实际宽度，初始化倍数为1，最小倍数为1，最大倍数为1，用户缩放为否 -->
		<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
		<!-- 删除默认的苹果工具栏和菜单栏 -->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!-- 苹果手机顶部为黑色 -->
		<meta name="apple-mobile-web-status-bar-style" content="block" />
		<!-- 屏蔽浏览器自动识别数字为电话号码 -->
		<meta name="fromat-detecition" content="telephone=no" />
		<title>宏古</title>
		<link rel="stylesheet" href="./css/common.css"/>
		<link rel="stylesheet" href="./css/addressManage.css"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="./js/control/addressManage.js?v=1.1"></script>
<title>宏古</title>
</head>
<body>
<div id="errorMsg"></div>
<div class="addressInfo">
	<div class="addressTitle">
		<img src="./img/icon-back.png" class="fl go-back-1" onclick="goBack()"/>
		<span>地址管理</span>
	</div>
	<div class="InfoList">
		<ul id="InfoList"></ul>
	</div>
	<div class="addressButton" onclick="gotoAdd()">
		<img src="./img/btn-tianjia.png" alt="">
	</div>
</div>
</body>
</html>