$(function() {
	/*$("#loginBtn").on("touchstart", function() {
		$(this).css("background", "#2fa9b6");
	})*/
	//点击登录发送请求
	$("#loginBtn").on("touchend", function() {
		var account =$("#telNumber").val();
		var password =$("#passWord").val();
		/*$(this).css("background", "#3dc5d3");*/
		if(login() == true) {
			$(this).css("background", "url(img/btn-nexthightlight.png)");
			$(this).css("background-size", "3.25rem 0.8rem");
			var msg = "登陆中...";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			var data = {
				account: account,
				password:password
				
			}
			var url = "../zlead/login/login";
			appSupport.cm.postAjaxFunction(url,data,callback);
			/*console.log("输入正确");*/
		}
	})
	//处理返回数据
	function callback(evt) {
		if(evt.code=="1"){
			localStorage.setItem("memberId",evt.data.memberId);
			localStorage.setItem("storeType",evt.data.storeType);
            localStorage.setItem("memberType",evt.data.memberType);
			/*localStorage.setItem("hasPayPassword",evt.data.hasPayPassword);
			localStorage.setItem("userPhone",evt.data.phone);
			localStorage.setItem("userNo",evt.data.userNo);
			localStorage.setItem("level",evt.data.level);*/
			window.location.href="index.jsp";
						
		}else if(evt.code!="1"){
			var msg = evt.message;
				appSupport.cm.errorMessageShow(errorMsg, msg);
				setTimeout(function() {
					appSupport.cm.errorMessageHide(errorMsg);
				}, 500);
		}
	}
})
//	验证输入信息
	function login() {
		var phoneNumber = $("#telNumber").val();
		var passWord = $("#passWord").val();
		var re = /^1\d{10}$/;
		if(phoneNumber == "") {
			var msg = "请输入登陆账号！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else if(passWord == "") {
			var msg = "请输入密码！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else {
			return true;
		}
	}

	function gotoPassword(){
		window.location.href="forgetPassword.jsp";
	}