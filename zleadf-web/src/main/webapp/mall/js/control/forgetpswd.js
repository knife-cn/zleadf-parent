$(function() {
	$(".incode img").attr("src", baseUrl.formal + "make_verify_code.html?type=h5");
	$(".incode").on("click", function() {
		//window.location.reload();
		var url = baseUrl.formal + "make_verify_code.html?type=h5";
		var data = {};
		$.ajax({
			type: "post",
			url: url,
			data: data,
			async: true,
			success: function() {
				callback2();
			}
		});
	})

	function callback2() {
		$(".incode img").attr("src", baseUrl.formal + "make_verify_code.html?type=h5");
	}
	$("#next").on("touchstart", function() {
		$(this).css("background", "#2fa9b6");
	})
	//注册账号
	$("#next").on("touchend", function() {
		$(this).css("background", "#3dc5d3");
		if($("#code").val() == '') {
			var msg = "请输入验证码！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else {
			var data = {
				inputCode: $("#code").val()

			}
			var url = baseUrl.formal + "check_inputCode.html";
			appSupport.cm.postAjaxFunction(url, data, rigSuccessFunction);

		}
	})
	//验证码动画
	var InterValObj; //timer变量，控制时间//间隔函数，1秒执行
	var curCount = 60; //当前剩余秒数
	var re = /^1\d{10}$/;

	function SetRemainTime() {
		if(curCount == 0) {
			window.clearInterval(InterValObj); //停止计时器
			//$("#btnSendCode").removeAttr("disabled");//启用按钮
			$("#getCode2").hide();
			$("#getCode").show().html("点击重新获取");
			curCount = 60;
		} else {
			curCount--;
			$("#getCode2").html(curCount + "秒");
		}
	}
	//发送验证码
	$("#getCode").on("click", function() {
		if($("#phoneNumber").val() == "") {
			var msg = "请输入手机号！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else if(!re.test($("#phoneNumber").val())) {
			var msg = "请输入正确手机号！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else if($("#imgcode").val() == "") {
			var msg = "请输入图文验证码";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
		} else {
			var url = baseUrl.formal + "new_send_check_code.html";
			var data = {
				phone: $("#phoneNumber").val(),
				inputVerifyCode: $("#imgcode").val()
			}

			appSupport.cm.postAjaxFunction(url, data, callback);
		}

	})
	//验证码发送成功回调
	function callback(evt) {
		if(evt.code == "200") {
			var msg = "验证码发送成功";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			$("#getCode").hide();
			$("#getCode2").show().html("60秒");
			InterValObj = window.setInterval(SetRemainTime, 1000);
		} else if(evt.code == "1018") {
			var msg = "验证码输入有误";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
		}
	}
	//注册成功回调
	function rigSuccessFunction(evt) {
		if(evt.code == "1") {
			window.location.href = "newpswd.html?phone=" + $("#phoneNumber").val();
		} else {
			var msg = "验证失败";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
		}
	}
})