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
		<link rel="stylesheet" href="css/common.css"/>
		<link rel="stylesheet" href="css/index.css"/>
		<link rel="stylesheet" href="css/news.css"/>
		<link rel="stylesheet" href="css/unslider.css"/>
		<link rel="stylesheet" href="css/unslider-dots.css"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/library/banner.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js?v=1.1"></script>
		<script type="text/javascript" src="js/control/aboutUs.js"></script>
		<script type="text/javascript" src="js/library/unslider-min.js"></script>
<title>宏古</title>
</head>
<body>
<!-- <div id="errorMsg"></div>
<div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="indexInfo">
	<div class="newsListTltle">
		<img src="img/icon-back.png" class="fl go-back-1">
		<span>关于我们</span>
	</div>
	<div class="default-slider" style="background: #FFFFFF;width:7.2rem;margin:0 auto;">
		<ul id="slider-inner"></ul>
	</div>
	<%--<div class="recommend">
		<ul id="recommend">
			<li>
				<div class="recommend-1">
					<img src="" alt="">
				</div>
				<div class="recommend-2">
					<p class="recommend-2-1">微软和鱼肉胡</p>
					<p class="recommend-2-2">
						<img src="img/img-quan.png" alt="">
						<span>已有<label>300</label>人购买</span>
					</p>
					<p class="list-price">RMB<label>268</label></p>
				</div>
			</li>
			<li>
				<div class="recommend-1">
					<img src="" alt="">
				</div>
				<div class="recommend-2">
					<p class="recommend-2-1">微软和鱼肉胡</p>
					<p class="recommend-2-2">
						<img src="img/img-quan.png" alt="">
						<span>已有<label>300</label>人购买</span>
					</p>
					<p class="list-price">RMB<label>268</label></p>
				</div>
			</li>
		</ul>
	</div>--%>
	<div class="newsList">
		<ul id="newsList">
		</ul>
	</div>
	<div class="footer">
		<ul>
			<li>
				<img src="img/nav-menu1.png" alt=""/>
				<p>首页</p>
			</li>
			<li>
				<img src="img/nav-menu2.png" alt=""/>
				<p>商城</p>
			</li>
			<li>
				<img src="img/nav-menu3.png" alt=""/>
				<p>购物车</p>
			</li>
			<li>
				<img src="img/nav-menu4.png" alt=""/>
				<p>会员中心</p>
			</li>
		</ul>
	</div>

</div>
</body>
</html>