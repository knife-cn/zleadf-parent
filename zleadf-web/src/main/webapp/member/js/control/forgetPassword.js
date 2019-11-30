var InterValObj; //timer变量，控制时间//间隔函数，1秒执行
var curCount = 60; //当前剩余秒数
function SetRemainTime() {
	if(curCount == 0) {
		window.clearInterval(InterValObj); //停止计时器
		$('#validate').val('重新获取');
		curCount = 60;
	} else {
		curCount--;
		$("#validate").val(curCount + "秒");
	}
}

//发送验证码
function sendMsg(){
	var phone=$("#phone").val();
	if(phone == "") {
		var msg = "请输入手机号";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	} else {
		var url ="../zlead/login/sendMsg";
		var data = {
			phone: phone
		}
		appSupport.cm.postAjaxFunction(url, data, callback);
	}
}

//验证码发送成功回调
function callback(evt) {
	if(evt.code == "1") {
		var msg = "验证码发送成功";
		var code=evt.data;
		$("#codeMsg").html(code);
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		$("#validate").val("获取验证码");
		InterValObj = window.setInterval(SetRemainTime, 1000);
	} else if(evt.code != "1") {
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	}
}

function upperCase(){
	var code=$("#code").val();
	var codeMsg=$("#codeMsg").text();
	if(code != codeMsg){
		var msg = "验证码错误！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	}
}

//忘记密码
function checkOut(){
	var phone=$("#phone").val();
	var code=$("#code").val();
	var newPass=$("#newPass").val();
	var newPassr=$("#newPassr").val();
	var codeMsg=$("#codeMsg").text();
	if(code == '') {
		var msg = "请输入验证码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(code !=codeMsg) {
		var msg = "验证码错误！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(newPass == '') {
		var msg = "请输入新密码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(newPassr == '') {
		var msg = "请输入确认密码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(newPass !=newPassr) {
		var msg = "两次密码输入不一致！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else {
		var data = {
			"phone": phone,
			"newPass": newPass
		}
		var url = "../ajeasy/login/forgetPass.html";
		appSupport.cm.postAjaxFunction(url, data, rigSuccessFunction);
	}
}

//忘记密码成功回调
function rigSuccessFunction(evt) {
	if(evt.code == "1") {
		var msg = "修改密码成功！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			goToLogin();
		}, 2000);
	}else{
		var msg = evt.message;
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
	}
}

function goToLogin() {
	var phone=$("#phone").val();
	var password=$("#newPass").val();
	var url = "../ajeasy/login/login";
			var data = {
				account: phone,
				password: password
			};
		appSupport.cm.postAjaxFunction(url, data, goToIndex);			
}
function goToIndex(evt){
	localStorage.setItem("memberId", evt.data.memberId);
	/*localStorage.setItem("hasPayPassword", evt.data.hasPayPassword);
	localStorage.setItem("userPhone", evt.data.phone);
	localStorage.setItem("userNo", evt.data.userNo);
	localStorage.setItem("memberType", evt.data.memberType);
	localStorage.setItem("level", evt.data.level);*/
	window.location.href = "index.jsp";
}

function fanhui(){
	window.history.back();
}