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
		<link rel="stylesheet" href="css/common.css?v=1.2"/>
		<link rel="stylesheet" href="css/shopDetails.css?v=1.1"/>
		<link rel="stylesheet" href="css/unslider.css"/>
		<link rel="stylesheet" href="css/unslider-dots.css"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/library/banner.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js?v=1.2"></script>
		<script type="text/javascript" src="js/control/shopDetails.js?v=1"></script>
		<script type="text/javascript" src="js/library/unslider-min.js"></script>
		<script type="text/javascript" src="js/config/dropload.min.js?v=1.1"></script>
</head>
<body>
<div id="errorMsg"></div>
<!-- <div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="indexInfo">
	<form action="#" class="clearfix">
		<img src="img/icon-back.png" class="fl go-back">
		<div class="search fl" onclick="gotoSeach()">
			<input type="search" placeholder="宏古等你来" >
		</div>
	</form>
	<!-- <div class="default-slider" style="background: #FFFFFF;width:7.03rem;margin:0.3rem auto;">
		<ul id="slider-inner"></ul>
	</div> -->
	<div class="addressInfo">
		<div class="addressInfo-1">
			<div class="addressInfo-1-1">
				<img class="addressInfo-1-1-img" src="" alt="">
			</div>
			<div class="addressInfo-1-2">
				<p class="addressInfo-1-2-p1"></p>
				<p class="addressInfo-1-2-p3">
					<img src="img/icon-dh.png" alt="">
					<span class="companyPhone"></span>
				</p>
			</div>
			<img class="addressInfo-1-3" src="img/img-jmsb.png" alt="">
		</div>
		<div class="addressInfo-2">
			<img src="img/icon-dzhi.png" alt="">
			<span class="companyAddress"></span>
		</div>
	</div>
	<div class="shopBanner">
		<img src="img/img-cjxp.png" alt="">
	</div>
	<div class="indexList fl">
		<div class="indexList-title">
			<img src="img/img-qbsp.png" alt=""/>
		</div>
		<div class="list">
			<ul class="listUl" id="list"></ul>
		</div>
	</div>
</div>
</body>
</html>