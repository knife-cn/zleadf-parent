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
		<link rel="stylesheet" href="css/orderPay.css"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/library/banner.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js"></script>
		<script type="text/javascript" src="js/control/orderPay.js"></script>
<title>宏古</title>
</head>
<body>
<div id="errorMsg"></div>
<!-- <div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="orderInfo">
	 <div class="orderTitle">
		<p>支付详情</p>
	</div>
	<!--<div class="address">
		<p>
			<img src="img/con-ren.png" alt="">
			<span>李云龙</span>
		</p>
		<p>
			<img src="img/icon-dingweia.png" alt="">
			<span>大别山深处</span>
		</p>
		<p>
			<img src="img/icon-dianhua.png" alt="">
			<span>13800138000</span>
		</p>
	</div>
	<div class="order">
       	<div class="order-1">
        	<img src="img/img-quan.png" alt="">
        	<span>324234</span>
        </div>
        <div class="order-2">
        	<div class="order-2-img">
        		<img src="" alt="">
        	</div>
	        <div class="order-2-info">
	        	<p class="order-2-info1 mb" id="'+res.data.goods.id+'"></p>
	        	<p class="order-2-info2 mb">
	        		<img src="img/img-quan.png" alt="">
	        		<span>默认规格</span>
	        	</p>
	        	<p class="order-2-info2">
	        		<img src="img/img-quan.png" alt="">
	        			<span><label></label>件</span>
	        	</p>
	        	<p class="order-2-info3">
	        		<label></label>
	        		<span>RMB</span>
	        	</p>
	        </div>
        </div>
    </div>
	<div class="order">
		<div class="order-1">
			<img src="img/img-quan.png" alt="">
			<span>李云龙军火库</span>
		</div>
		<div class="order-2">
			<div class="order-2-img">
				<img src="" alt="">
			</div>
			<div class="order-2-info">
				<p class="order-2-info1 mb">AMERTHHRT</p>
				<p class="order-2-info2 mb">
					<img src="img/img-quan.png" alt="">
					<span>默认规格</span>
				</p>
				<p class="order-2-info2">
					<img src="img/img-quan.png" alt="">
					<span><label>3</label>件</span>
				</p>
				<p class="order-2-info3">
					<label>268.00</label>
					<span>RMB</span>
				</p>
			</div>
		</div>
	</div>
	<div class="pay">
       	<div class="payDiv">
       		<input type="radio" id="zfb" name="zf" value="1">
       		<div class="payDiv-kuang">
       			<img src="img/img-zhifubao.png" alt="">
       			<p>支付宝</p>
       		</div>
       	</div>
       	<div class="payDiv">
       		<input type="radio" id="wx" name="zf" value="2">
       		<div class="payDiv-kuang">
       			<img src="img/img-weixin.png" alt="">
       			<p>微信</p>
       		</div>
       	</div>
       </div>
	<div class="total">
        <span class="totalText">您本次支付金额</span>
        <span class="totalSpan">RMB<label id="totalPrice"></label></span>
    </div>
    <div class="buy">
    	<img src="img/btn-lijizhifu.png" alt="" onclick="gotoPay()">
    </div>
	<div class="total">
		<span class="totalText">您本次支付金额</span>
		<span class="totalSpan">RMB<label>65.00</label></span>
	</div>
	<div class="buy">
		<img src="img/btn-lijijiesuan.png" alt="">
	</div> -->
</div>
</body>
</html>