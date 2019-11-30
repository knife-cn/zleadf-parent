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
		<link rel="stylesheet" href="./css/login.css?v=1.1"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/library/banner.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js"></script>
		<script type="text/javascript" src="./js/control/forgetPassword.js?v=1.2"></script>
</head>
<body>
<div id="errorMsg"></div>
<!-- <div id="loding">
	<img src="img/loding.gif">
</div> -->
<div class="wrap">
	<div class="back-img">
		<img src="img/img-beijingtu.png" alt=""/>
	</div>
	<div class="login-info" style="height:5.2rem">
		<img class="login-info-img" src="img/img-touxiang.png" alt=""/>
		<div class="info">
			<div class="info-input">
				<img class="info-input-img" src="img/icon-shoujihao.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="text" id="phone" name="phone" placeholder="请输入手机号">
					<input class="validate" type="button" id="validate" value="获取验证码" onclick="sendMsg()"/>
					<input type="hidden" id="codeMsg"/>
				</div>
			</div>
			<div class="info-input">
				<img class="info-input-img" src="img/icon-yanzhengma.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="text" id="code" name="code" placeholder="请输入验证码" onblur="upperCase()" >
				</div>
			</div>
			<div class="info-input">
				<img class="info-input-img" src="img/icon-password.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="password" id="newPass" name="newPass" placeholder="请输入新密码">
				</div>
			</div>
			<div class="info-input">
				<img class="info-input-img" src="img/icon-password.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="password" id="newPassr" name="newPassr" placeholder="请确认输入密码">
				</div>
			</div>
		</div>
	</div>
	<div class="bottom-button">
		<img class="bottom-button-1" onclick="checkOut()" src="img/btn-queren.png" alt="">
		<img class="bottom-button-1" onclick="fanhui()" src="img/btn-quexiao.png" alt="">
	</div>
	<div class="bottomdiv" style="height:2rem";></div>
</div>
</body>
</html>
<script>
</script>