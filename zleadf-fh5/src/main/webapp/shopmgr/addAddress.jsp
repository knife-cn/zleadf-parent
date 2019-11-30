<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
		<!-- 宽度设置为设备实际宽度，初始化倍数为1，最小倍数为1，最大倍数为1，用户缩放为否 -->
		<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
		<title>宏古</title>
		<link rel="stylesheet" href="./css/common.css?v=1"/>
		<link rel="stylesheet" href="./css/style.css?v=1.13"/>
		<link rel="stylesheet" href="./css/addAddress.css"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="./js/control/addAddress.js"></script>
<title>宏古</title>
</head>
<body>
<div id="errorMsg"></div>
<div class="addressInfo">
	<div class="addressTitle">
	<img src="img/icon-back.png" class="fl go-back-1" />
		<span>地址管理</span>
	</div>
	<div class="addressDiv">
		<div class="address">
			<img class="addressImg" src="img/icon-name.png" alt="">
			<div class="addressInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="address-input" type="text" id="userName" name="userName" placeholder="请输入收货人姓名">
			</div>
		</div>
		<div class="address">
			<img class="addressImg" src="img/icon-phone.png" alt="">
			<div class="addressInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="address-input" type="text" id="userPhone" name="userPhone" placeholder="请输入您的手机号码">
			</div>
		</div>
		<div class="address">
			<img class="addressImg" src="img/icon-dz.png" alt="">
			<div class="addressInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<div class="browser">
					<section class="express-area">
							<a id="expressArea" href="javascript:void(0)">
								<dl>
									<dt>省市区</dt>
								</dl>
							</a>
					</section>
					<!--选择地区弹层-->
					<section id="areaLayer" class="express-area-box">
						<header>
							<h3>选择地区</h3>
							<a id="backUp" class="back" href="javascript:void(0)" title="返回"></a>
							<a id="closeArea" class="close" href="javascript:void(0)" title="关闭"></a>
						</header>
						<article id="areaBox">
							<ul id="areaList" class="area-list"></ul>
						</article>
					</section>
					<div id="areaMask" class="mask"></div>
					<script type="text/javascript" src="js/library/jquery.area.js?v=1.1"></script>
				</div>
			</div>
		</div>
		<div class="address">
			<img class="addressImg" src="img/icon-dz.png" alt="">
			<div class="addressInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="address-input" type="text" id="userAress" name="userAress" placeholder="请输入详细地址">
			</div>
		</div>
		<div class="address">
			<img class="addressImg" src="img/icon-dz.png" alt="">
			<div class="addressInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<div class="browser">设为默认地址</div>
				<!-- <img class="choose" src="img/icon-duihao-nomal.png" alt="">
				<img style="display:none;" class="noChosoe" src="img/icon-duihao.png" alt=""> -->
				<input class="address-isDefault" type="radio" name="radio" id="isDefault" value="0">
			</div>
		</div>
	</div>
	<div class="addBtn" id="preservation">
		<img src="img/btn-tianjia.png" alt="">
	</div>
	<input id="prive" type="hidden" />
	<input id="city" type="hidden" />
	<input id="trxo" type="hidden" />
</div>
</body>
</html>