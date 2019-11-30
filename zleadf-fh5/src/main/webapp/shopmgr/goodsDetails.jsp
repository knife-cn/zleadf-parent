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
		<link rel="stylesheet" href="./css/goodsDetails.css"/>
		<link rel="stylesheet" href="./css/unslider.css"/>
		<link rel="stylesheet" href="./css/unslider-dots.css"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/library/banner.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js"></script>
		<script type="text/javascript" src="./js/control/goodsDetails.js?v=1.1"></script>
		<script type="text/javascript" src="./js/library/unslider-min.js"></script>
		<script type="text/javascript" src="./js/config/dropload.min.js?v=1.1"></script>
<title>宏古</title>
</head>
<body>
<div id="errorMsg"></div>
<!-- <div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="detailsInfo">
	<!-- <form action="#" class="clearfix">
		<div class="search fl">
			<input type="search" placeholder="宏古等你来">
		</div>
		<img src="img/share.png" class="fl msg">
	</form> -->
	<div class="detailsTltle">
		<img src="img/icon-back.png" class="fl go-back-1">
		<span>商品详情</span>
	</div>
	<div class="default-slider" style="background: #FFFFFF;width:7.03rem;margin:0.3rem auto;">
		<ul id="slider-inner"></ul>
	</div>
	<div class="details">
		<p class="details-1">NARS NARS敢要唇膏 经典红色CARS4.2G</p>
		<div class="details-2 fl">
			<span class="details-2-price"><label class="details-2-label">65.00</label>RMB</span>
			<span class="details-2-jf">
				<img src="img/icon-jifen.png" alt=""/>
				<label class="details-2-jfl" id="details-2-2">65</label>
			</span>
		</div>
		<!-- <p class="details-2">
			
		</p> -->
		<p class="details-3"><img src="img/img-quan.png" alt=""/><span>销量<label id="sales">10</label>笔</span></p>
		<p class="details-3"><img src="img/img-quan.png" alt=""/><span>库存<label id="stock">10000</label>件</span></p>
		<input type="hidden" id="shopId" value="">
		<input type="hidden" id="prodType" value="">
		<div class="number">
			<input class="add" type="button" value="+"/>
			<input class="amount" type="text" onkeyup="value=value.replace(/[^0-9]/g,'')"/>
			<input class="min" type="button" value="-"/>
		</div>
	</div>
	<div class="title">
		<img src="img/img-spxq.png" alt=""/>
	</div>
	<div class="detailsImg"></div>
	<div class="detailsFooter">
		<img class="collect" src="img/btn-shoucang.png" alt=""/>
		<img class="shopCar" src="img/btn-gouwuche.png" alt="" onclick="addCar()"/>
		<img class="buy" src="img/btn-lijigoumai.png" alt="" onclick="buy()"/>
	</div>
</div>
</body>
</html>