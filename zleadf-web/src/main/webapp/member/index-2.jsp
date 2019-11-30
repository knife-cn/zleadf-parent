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
		<link rel="stylesheet" href="./css/index.css?v=1.1"/>
		<link rel="stylesheet" href="./css/unslider.css"/>
		<link rel="stylesheet" href="./css/unslider-dots.css"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/library/banner.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="./js/control/index.js?v=1.1"></script>
		<script type="text/javascript" src="./js/library/unslider-min.js"></script>
</head>
<body>
<!-- <div id="errorMsg"></div>
<div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="indexInfo">
	<form action="#" class="clearfix">
		<div class="search fl" onclick="gotoSeach()">
			<input type="search" placeholder="宏古等你来">
		</div>
		<img src="img/share.png" class="fl msg">
	</form>
	<div class="default-slider" style="background: #FFFFFF;width:7.2rem;margin:0 auto;">
		<ul id="slider-inner"></ul>
	</div>
	<div class="indexList fl">
		<div class="indexList-title">
			<img src="img/img-shangpintuijian.png" alt=""/>
		</div>
		<div class="recommend">
			<ul id="recommendList">
				<!-- <li>
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
				</li> -->
			</ul>
		</div>
		<div class="join-in">
			<div class="join-in-1">
				<img src="img/jm.png" alt="">
			</div>
			<div class="join-in-2">
				<p class="join-in-2-p1">加盟招商，赢取好礼！</p>
				<p class="join-in-2-p2">2018-3-19</p>
			</div>
		</div>
		<div class="join-in">
			<div class="join-in-1">
				<img src="img/jm.png" alt="">
			</div>
			<div class="join-in-2">
				<p class="join-in-2-p1">今日，山西某食品公司食品大幅降价</p>
				<p class="join-in-2-p2">2018-3-19</p>
			</div>
		</div>
		<div class="indexList-title">
			<img src="img/img-qbsp.png" alt=""/>
		</div>
		<div class="list">
			<ul class="listUl" id="listUl">
	<!-- 			<li>
					<div class="list-info">
						<div class="list-info-img">
							<img src="img/111.png" alt=""/>
						</div>
						<div class="list-info-title">
							<p>资生堂洗颜专科深层清洁毛孔面乳霜</p>
						</div>
						<div class="list-info-peole">
							<img src="img/img-quan.png" alt=""/>
							<span style="margin-left:0.12rem;">已有<label>300</label>人购买</span>
						</div>
						<div class="list-info-price">
							<p><span class="list-price">RMB<label>268</label></span><span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/><label class="integral">268</label></span></p>
							<span class="list-price">RMB<label>268</label></span>
							<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>268</span>
						</div>
					</div>
				</li>
				<li>
					<div class="list-info">
						<div class="list-info-img">
							<img src="img/111.png" alt=""/>
						</div>
						<div class="list-info-title">
							<p>资生堂洗颜专科深层清洁毛孔面乳霜</p>
						</div>
						<div class="list-info-peole">
							<img src="img/img-quan.png" alt=""/>
							<span style="margin-left:0.12rem;">已有<label>300</label>人购买</span>
						</div>
						<div class="list-info-price">
							<p><span class="list-price">RMB<label>268</label></span><span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/><label class="integral">268</label></span></p>
							<span class="list-price">RMB<label>268</label></span>
							<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>268</span>
						</div>
					</div>
				</li>
				<li>
					<div class="list-info">
						<div class="list-info-img">
							<img src="img/111.png" alt=""/>
						</div>
						<div class="list-info-title">
							<p>资生堂洗颜专科深层清洁毛孔面乳霜</p>
						</div>
						<div class="list-info-peole">
							<img src="img/img-quan.png" alt=""/>
							<span style="margin-left:0.12rem;">已有<label>300</label>人购买</span>
						</div>
						<div class="list-info-price">
							<p><span class="list-price">RMB<label>268</label></span><span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/><label class="integral">268</label></span></p>
							<span class="list-price">RMB<label>268</label></span>
							<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>268</span>
						</div>
					</div>
				</li>
				<li>
					<div class="list-info">
						<div class="list-info-img">
							<img src="img/111.png" alt=""/>
						</div>
						<div class="list-info-title">
							<p>资生堂洗颜专科深层清洁毛孔面乳霜</p>
						</div>
						<div class="list-info-peole">
							<img src="img/img-quan.png" alt=""/>
							<span style="margin-left:0.12rem;">已有<label>300</label>人购买</span>
						</div>
						<div class="list-info-price">
							<p><span class="list-price">RMB<label>268</label></span><span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/><label class="integral">268</label></span></p>
							<span class="list-price">RMB<label>268</label></span>
							<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>268</span>
						</div>
					</div>
				</li>
				<li>
					<div class="list-info">
						<div class="list-info-img">
							<img src="img/111.png" alt=""/>
						</div>
						<div class="list-info-title">
							<p>资生堂洗颜专科深层清洁毛孔面乳霜</p>
						</div>
						<div class="list-info-peole">
							<img src="img/img-quan.png" alt=""/>
							<span style="margin-left:0.12rem;">已有<label>300</label>人购买</span>
						</div>
						<div class="list-info-price">
							<p><span class="list-price">RMB<label>268</label></span><span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/><label class="integral">268</label></span></p>
							<span class="list-price">RMB<label>268</label></span>
							<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>268</span>
						</div>
					</div>
				</li>
				<li>
					<div class="list-info">
						<div class="list-info-img">
							<img src="img/111.png" alt=""/>
						</div>
						<div class="list-info-title">
							<p>资生堂洗颜专科深层清洁毛孔面乳霜</p>
						</div>
						<div class="list-info-peole">
							<img src="img/img-quan.png" alt=""/>
							<span style="margin-left:0.12rem;">已有<label>300</label>人购买</span>
						</div>
						<div class="list-info-price">
							<p><span class="list-price">RMB<label>268</label></span><span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/><label class="integral">268</label></span></p>
							<span class="list-price">RMB<label>268</label></span>
							<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>268</span>
						</div>
					</div>
				</li> -->
			</ul>
		</div>
	</div>
	<div class="footer">
		<ul>
			<li class="active">
				<img src="img/nav-menu1-1.png" alt=""/>
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