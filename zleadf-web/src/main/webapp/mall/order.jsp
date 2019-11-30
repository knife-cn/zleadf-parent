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
		<link rel="stylesheet" href="css/common.css?v=1.1"/>
		<link rel="stylesheet" href="css/order.css?v=1.2"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/config/dropload.min.js?v=1.1"></script>
		<script type="text/javascript" src="js/library/banner.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="js/control/order.js"></script>
</head>
<body>
<div id="errorMsg"></div>
<div class="orderInfo">
	<div class="orderTitle">
		<img src="img/icon-back.png" class="fl go-back-2">
		<span>我的订单</span>
	</div>
	<div class="chooseType">
		<ul>
			<li class="active" id="type-1" onclick="changeTab('-1')">
				<span>全部</span>
			</li>
			<li id="type0" onclick="changeTab('0')">
				<span>待支付</span>
			</li>
			<li id="type1" onclick="changeTab('1')">
				<span>待发货</span>
			</li>
			<li id="type2" onclick="changeTab('2')">
				<span>已发货</span>
			</li>
			<li id="type3" onclick="changeTab('3')">
				<span>已完成</span>
			</li>
		</ul>
	</div>
	<div class="orderInfoList">
		<div id="orderList">
			
		</div>
	</div>
</div>
</body>
</html>