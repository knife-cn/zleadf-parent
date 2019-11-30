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
		<link rel="stylesheet" href="css/style.css?v=1.1"/>
		<link rel="stylesheet" href="css/login.css?v=1.1"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/library/banner.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js"></script>
		<script type="text/javascript" src="js/control/register.js?v=1"></script>
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
	<div class="login-info" style="height:3.85rem;">
		<img class="login-info-img" src="img/img-touxiang.png" alt=""/>
		<div class="info">
			<div class="info-input">
				<img class="info-input-img" src="img/icon-shoujihao.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" style="width:2.4rem;" type="text" id="phone" name="phone" placeholder="请输入手机号">
					<%--<input class="validate" type="button" id="validate" value="获取验证码" onclick="sendMsg()"/>
					<input type="hidden" id="codeMsg"/>--%>
				</div>
			</div>
			<%--<div class="info-input">
				<img class="info-input-img" src="img/icon-yanzhengma.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="text" id="code" name="code" placeholder="请输入验证码" onblur="upperCase()" >
				</div>
			</div>
			<div class="info-input">
				<img class="info-input-img" src="img/icon-yaoqingren.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="text" id="parentId" name="parentId" placeholder="请输入邀请码   ID1为平台">
				</div>
			</div>--%>
			<div class="info-input">
				<img class="info-input-img" src="img/icon-dizhi.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<div class="browser">
					<section class="express-area" style="height:0.73rem;">
							<a id="expressArea" href="javascript:void(0)">
								<dl style="height:0.73rem;width:3rem;line-height:0.73rem;margin-left:0.1rem;font-size:0.24rem">
									<dt style="height:0.73rem;line-height:0.73rem;margin-left:0.1rem;font-size:0.24rem">省市区</dt>
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
					<!-- <input class="login-input" type="text" id="areaResult" name="areaResult" placeholder="请输入地址"> -->
				</div>
			</div>
			<div class="info-input">
				<img class="info-input-img" src="img/icon-password.png" alt=""/>
				<div class="info-input-div">
					<img src="img/img-dianred.png" alt=""/>
					<input class="login-input" type="password" id="password" name="password" placeholder="请输入密码">
				</div>
			</div>
		</div>
	</div>
	<div class="bottom-btn-1" id="regBtn" onclick="checkOut()"></div>
	<div class="bottomdiv" style="height:2rem";></div>
	
	<input id="prive" type="hidden" />
	<input id="city" type="hidden" />
	<input id="trxo" type="hidden" />
</div>
</body>
</html>