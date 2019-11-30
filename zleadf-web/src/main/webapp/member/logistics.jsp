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
		<title>爱聚益</title>
		<link rel="stylesheet" href="./css/common.css"/>
		<link rel="stylesheet" href="./css/logistics.css"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="./js/control/logistics.js"></script>
</head>
<body>
<div id="errorMsg"></div>
<div class="logisticsInfo">
	<div class="logisticsTitle">
		<img src="img/icon-back.png" class="fl go-back-1" onclick="goBack()"/>
		<span>查看物流</span>
	</div>
	<div class="logistics">
		<div class="logistics-img">
			<img class="logisticsImg" src="" alt="">
		</div>
		<div class="logistics-info">
			<p>
				<img src="img/img-quan.png" alt="">
				<span id="orderStatus"></span>
			</p>
			<p>
				<img src="img/img-quan.png" alt="">
				<span id="comName"></span>
			</p>
			<p>
				<img src="img/img-quan.png" alt="">
				<span>单号：<label id="expressNu"></label></span>
			</p>
		</div>
	</div>
	<div class="logistics-div">
		<div class="logistics-div-1"></div>
		<div class="logistics-div-2"></div>
	</div>
</div>
</body>
</html>