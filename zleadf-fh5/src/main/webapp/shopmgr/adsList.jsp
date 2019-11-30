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
		<link rel="stylesheet" href="./css/common.css?v=1.1"/>
		<link rel="stylesheet" href="./css/allianceList.css?v=1.1"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/config/dropload.min.js?v=1.1"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="./js/control/adsList.js"></script>
</head>
<body>
<div id="errorMsg"></div>
<div class="orderInfo">
	<div class="orderTitle">
		<img src="./img/icon-back.png" class="fl go-back-1 ">
		<span>广告列表</span>
		<img class="allianceBtn" src="./img/icon-tianjiai.png" alt="" onclick="gotoAddAds()">
	</div>
	<div class="list-1">
		<ul>
			<li style="width:15%">标题</li>
			<li style="width:18%">添加时间</li>
			<li style="width:20%">状态</li>
			<li style="width:20%">置顶</li>
			<li style="width:27%">操作</li>
		</ul>
	</div>
	<div class="list-2">
		<div id="list"></div>
	</div>
</div>
</body>
</html>