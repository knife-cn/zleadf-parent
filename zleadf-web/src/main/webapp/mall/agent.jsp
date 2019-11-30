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
		<link rel="stylesheet" href="css/style.css?v=1.13"/>
		<link rel="stylesheet" href="css/agent.css?v=1"/>
		<script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/config/url.js?v=1"></script>
		<script type="text/javascript" src="js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="js/control/agent.js"></script>
</head>
<body>
<div id="errorMsg"></div>
<div class="agentInfo">
	<div class="agentTitle">
		<img src="img/icon-back.png" class="fl go-back-1" >
		<span>入驻申请</span>
	</div>
	<form id="form1" name="form1" action="../agent/doApplyAgent" method="post">
		<input class="agentDiv-input" type="text" hidden id="agentEnterMoney" name="agentEnterMoney" value="ERROR">
	<div class="agent">
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-user.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="contactName" name="contactName" placeholder="请输入联系人姓名（必填）">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-shouji.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="contactPhone" name="contactPhone" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入联系人电话（必填）">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-email.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="contactMail" name="contactMail" placeholder="请输入联系人邮箱">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-gsmc.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyName" name="companyName" placeholder="请输入公司名称">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-diz.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<div class="browser">
					<section class="express-area">
							<a id="expressArea" href="javascript:void(0)">
								<dl style="line-height:0.73rem;height:0.73rem;">
									<dt style="line-height:0.73rem;height:0.73rem;">请选择公司所在省市</dt>
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
					<script type="text/javascript" src="js/library/jquery.area.js"></script>
				</div>
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-dw.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyAddress" name="companyAddress" placeholder="请输入详细地址（必填）">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-zijinl.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyCapital" name="companyCapital" placeholder="请输入公司注册资本">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-peoples.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyPersonCount" name="companyPersonCount" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入公司人数">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-dhl.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyPhone" name="companyPhone" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入公司电话">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-user.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyLegalPerson" name="companyLegalPerson" placeholder="请输入公司法人名字">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-sfzh.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="companyLegalPersonSn" name="companyLegalPersonSn" placeholder="请输入身份证号（必填）">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-yyzz.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="businessLicenceSn" name="businessLicenceSn" placeholder="请输入营业执照编号（必填）">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-diz.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="businessLicenceAddress" name="businessLicenceAddress" placeholder="请输入营业执照地址（必填）">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-yhk.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="bankAccount" name="bankAccount" placeholder="请输入银行账户">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-user.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="bankAccountName" name="bankAccountName" placeholder="请输入银行银行开户名称">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-yhk.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="bankName" name="bankName" placeholder="请输入银行名称">
			</div>
		</div>
		<div class="agentDiv">
			<img class="agentDivImg" src="img/icon-diz.png" alt="">
			<div class="agentDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="agentDiv-input" type="text" id="bankAddress" name="bankAddress" placeholder="请输入开户银行地址">
			</div>
		</div>
	</div>
	<div class="payChoose">
		<p>支付方式选择</p>
		<div class="choose">
			<div class="chooseDiv">
				<input type="radio" name="payType" value="0"/>
				<span>我要线上支付</span>
			</div>
			<div class="chooseDiv">
				<input type="radio" name="payType" value="1"/>
				<span>我要线下支付</span>
			</div>
		</div>
	</div>
	</form>
	<input id="prive" type="hidden" />
	<input id="city" type="hidden" />
	<input id="trxo" type="hidden" />

	<div class="validate">
		<img id="validate" src="img/btn-next-hightlight.png" alt="" onclick="validateForm()">
	</div>
</div>
</body>
</html>