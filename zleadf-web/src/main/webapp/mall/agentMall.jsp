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
		<link rel="stylesheet" href="css/agentMall.css?v=1.1"/>
		<link rel="stylesheet" href="css/unslider.css"/>
		<link rel="stylesheet" href="css/unslider-dots.css"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/library/banner.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js"></script>
		<script type="text/javascript" src="js/control/agentMall.js?v=1.1"></script>
		<script type="text/javascript" src="js/library/unslider-min.js"></script>
		<script type="text/javascript" src="js/config/dropload.min.js?v=1.1"></script>
		<script>
			! function(e) {
				function t(a) {
					if(i[a]) return i[a].exports;
					var n = i[a] = {
						exports: {},
						id: a,
						loaded: !1
					};
					return e[a].call(n.exports, n, n.exports, t), n.loaded = !0, n.exports
				}
				var i = {};
				return t.m = e, t.c = i, t.p = "", t(0)
			}([function(e, t) {
				"use strict";
				Object.defineProperty(t, "__esModule", {
					value: !0
				});
				var i = window;
				t["default"] = i.flex = function(e, t) {
					var a = e || 100,
						n = t || 1,
						r = i.document,
						o = navigator.userAgent,
						d = o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),
						l = o.match(/U3\/((\d+|\.){5,})/i),
						c = l && parseInt(l[1].split(".").join(""), 10) >= 80,
						p = navigator.appVersion.match(/(iphone|ipad|ipod)/gi),
						s = i.devicePixelRatio || 1;
					p || d && d[1] > 534 || c || (s = 1);
					var u = 1 / s,
						m = r.querySelector('meta[name="viewport"]');
					m || (m = r.createElement("meta"), m.setAttribute("name", "viewport"), r.head.appendChild(m)), m.setAttribute("content", "width=device-width,user-scalable=no,initial-scale=" + u + ",maximum-scale=" + u + ",minimum-scale=" + u), r.documentElement.style.fontSize = a / 2 * s * n + "px"
				}, e.exports = t["default"]
			}]);
			flex(100, 1);
		</script>
		<style>
		a{
		 display:block;/*这个属性是必须的*/
		 font-size:0.26rem;
		 line-height:1.25rem;
		 text-decoration:none;
		 color:#000;
		 opacity:0.7;
		}
		</style>
<title>宏古</title>
</head>
<body>
<!-- <div id="errorMsg"></div>
<div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="indexInfo">
	<form action="#" class="clearfix">
		<img src="img/icon-back.png" class="fl go-back">
		<div class="search fl">
			<input type="search" placeholder="宏古等你来">
		</div>
		<img src="img/share.png" class="fl msg">
	</form>
	<div class="default-slider" style="background: #FFFFFF;width:7.03rem;margin:0.3rem auto;">
		<ul id="slider-inner"></ul>
	</div>
	<div class="activity">
		<img src="img/img-huodong.png" alt="">
	</div>
	<div class="indexList fl">
		<div class="indexList-title">
			<img src="img/img-daili.png" alt=""/>
		</div>
		<div class="list">
			<ul class="listUl" id="list"></ul>
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