$(function() {
	//请求页面展示信息
	var scidlist = localStorage.getItem("scidlist"); //购物车id
	var type = localStorage.getItem("type"); //类型:作用是为了判断是否要切换地址
	var grouType = "1"; //是否使用成长贝状态
	var index = 1 //默认微信支付
	function init() {
		var url = baseUrl.formal + "order/go_account.html";
		var data = {
			scidlist: scidlist,
			flag: 1,
			userId: localStorage.getItem("userId")
		}
		appSupport.cm.postAjaxFunction(url, data, rightNowFunction);
	}
	init();
	//	展示信息
	function rightNowFunction(evt) {
		if(evt.code == "32") {
			var length = evt.data.productInfoMap.length;
			var car = evt.data.productInfoMap;
			var str = "";
			for(var i = 0; i < length; i++) {
				str += "<div class='prusuct_li prusuct_li2 clearfix'>";
				str += "<img class='show_img' src=" + car[i].logoPic + ">";
				str += "<div class='prusuct_li_eords'>";
				str += "<p>" + car[i].productname + "</p>";
				str += "<p>" + car[i].attributeName + "</p>";
				str += "<div class='clearfix'>";
				str += "<p class='fl'>¥" + car[i].proPrice + "</p>";
				str += "<div class='shopcaradd clearfix'>";
				str += "<p>x" + car[i].singleProNum + "</p>";
				str += "</div>";
				str += "</div>";
				str += "</div>";
				str += "</div>";
			}
			$("#product").append(str);
			$("#allPrice").html("¥" + evt.data.totalPrice);
			$("#baoyou").html(evt.data.tranferFeeInfoMap.postageTip);
			$("#singleIPostPrice").html("¥" + evt.data.tranferFee);
			$("#gropth").html("-¥" + evt.data.grouthBei);
			grouthBei = evt.data.grouthBei;
			var needPay = evt.data.totalPrice + evt.data.tranferFee - evt.data.grouthBei;
			$("#needPay").html("¥" + needPay);
			//	是否使用成长贝
			$("#icoGrou").on("click", function() {
				if(grouType == "1") {
					$(this).removeClass("use_grouth").addClass("no_use_grouth");
					grouType = "0";
					var needPay = evt.data.totalPrice + evt.data.tranferFee;
					$("#needPay").html("¥" + needPay);
				} else {
					$(this).removeClass("no_use_grouth").addClass("use_grouth");
					grouType = "1";
					var needPay = evt.data.totalPrice + evt.data.tranferFee - evt.data.grouthBei;
					$("#needPay").html("¥" + needPay);
				}
			})
			getAddress();
		} else if(evt.code == "1") {
			window.location.href = "shopcar.html";
		}

	}
	//	请求收货地址
	function getAddress() {
		var url = baseUrl.formal + "user/query_address.html";
		var data = {
			userId: localStorage.getItem("userId")
		}
		appSupport.cm.postAjaxFunction(url, data, callbackAddress);
	}

	//展示地址信息
	function callbackAddress(evt) {
		if(evt.success == false) {
			window.location.href = "login.html";
		}
		if(evt.code == "1") {
			$("#loding").hide();
			//没有收货地址
			if(evt.data.addressList.length == 0) {
				$("#haveAddress").hide();
				$("#address").show();
			} else if(type == null || type == "defelet") {
				$("#address").hide();
				$("#userName").html(evt.data.defaultAddress.name);
				$("#userPhone").html(evt.data.defaultAddress.phone);
				var userAddress = evt.data.defaultAddress.provinceId + evt.data.defaultAddress.cityId + evt.data.defaultAddress.districtId + evt.data.defaultAddress.address;
				if(userAddress.length > 14) {
					userAddress = userAddress.substring(0, 14) + "...";
				}
				$("#userAddress").html(userAddress);
				$("#haveAddress").show();
				//	提交订单
				$("#submitOrder").on("click", function() {
					var url = baseUrl.formal + "order/create_order.html";
					var data = {
						scidlist: scidlist,
						flag: 1,
						userId: localStorage.getItem("userId"),
						ifUseGrouthBei: grouType,
						addressId: evt.data.defaultAddress.id
					}
					appSupport.cm.postAjaxFunction(url, data, orderFunction);
				})

			} else {
				$("#address").hide();
				$("#userName").html(evt.data.addressList[type].name);
				$("#userPhone").html(evt.data.addressList[type].phone);
				var userAddress = evt.data.addressList[type].provinceId + evt.data.addressList[type].cityId + evt.data.addressList[type].districtId + evt.data.addressList[type].address;
				if(userAddress.length > 14) {
					userAddress = userAddress.substring(0, 14) + "...";
				}
				$("#userAddress").html(userAddress);
				$("#haveAddress").show();
				$("#submitOrder").on("click", function() {
					var url = baseUrl.formal + "order/create_order.html";
					var data = {
						scidlist: scidlist,
						flag: 1,
						userId: localStorage.getItem("userId"),
						ifUseGrouthBei: grouType,
						addressId: evt.data.addressList[type].id
					}
					appSupport.cm.postAjaxFunction(url, data, orderFunction);
				})
			}
		}
	}
	//	切换或获取收货地址
	$("#haveAddress,#address").on("click", function() {
		localStorage.setItem("type", 0);
		window.location.href = "managementaddress.html";
	})
	//生成订单后回调函数
	function orderFunction(evt) {
		if(evt.code == "1") {
			console.log(evt.data.waitPayPrice);
			order.id = evt.data.orderId; //生成订单编号
			order.transFerFee = evt.data.transFerFee; //运费
			order.waitPayPrice = evt.data.waitPayPrice; //总价
			$(".pay_detailes").show();
			$("#needPayMoney").html("¥" + evt.data.waitPayPrice);
			//			取消支付跳转到订单详情页
			$(".close_pay,.shdow").on("click", function() {
				$(".pay_detailes").hide();
				window.location.href = "orderdetailes.html?orderid=" + evt.data.orderId + "&transFerFee=" + evt.data.transFerFee + "&waitPayPrice=" + evt.data.waitPayPrice + "&ordertype=" + 0;
			})
			//选择支付方式
			$(".check_fang li div").on("click", function() {
				index = $(this).parent().index();
				$(".check_fang li div").removeClass("active");
				$(this).addClass("active");
			})
			//			点击付款
			$("#goPay").on("click", function() {
				if(index == 1) {
					//微信支付
					var url = baseUrl.formal + "wxPay/wxPay.html";
					var data = {
						openId: localStorage.getItem("openId"),
						orderId: order.id,
						userno: localStorage.getItem("userNo")
					};
					appSupport.cm.postAjaxFunction(url, data, weiXinPay);
				} else if(index == 2) {
					//成长金支付
					var isHasPay = localStorage.getItem("hasPayPassword");
					if(isHasPay == "false") {
						$("#serPayPswd").show();

					} else {
						$(".pswd_pay_config").show();
						$("#carNumberFirst,#forgetPswd,#carNumberOther2").show();
						$("#nowPay").hide(100);
					}
				}
			})
		}
	}
	//	调起微信支付
	function weiXinPay(evt) {
		if(evt.success == true) {
			WeixinJSBridge.invoke(
				'getBrandWCPayRequest', {
					"appId": evt.data.appId, //公众号名称，由商户传入
					"timeStamp": evt.data.timeStamp, //时间戳，自1970年以来的秒数
					"nonceStr": evt.data.nonceStr,
					"package": evt.data.package,
					"signType": evt.data.signType, //微信签名方式：
					"paySign": evt.data.paySign //微信签名
				},
				function(res) {
					if(res.err_msg == "get_brand_wcpay_request:ok") {
						//$scope.temPayHelper.payFlag = true;
						// http:'//weixin.p-share.com/wx_sa_share/html5/views/index.jsp#/home/XHprotocol'
						window.location.href = "orderdetailes.html?orderid=" + order.id + "&transFerFee=" + order.transFerFee + "&waitPayPrice=" + order.waitPayPrice + "&ordertype=" + 1;
						//alert();
					} // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
				}
			);
		}
	}
	//关闭设置支付密码页面
	$("#cancel").on("click", function() {
		$(".set_pswd_li input").val("");
		$("#serPayPswd").hide();
	})
	//	设置支付密码
	$("#confirm").on("click", function() {

		if(checkPswd() == true) {
			var payPassword = $("#userPswd").val();
			var comfirm_payPassword = $("#twoPswd").val();
			var url = baseUrl.formal + "setUp_payPassword.html";
			var data = {
				payPassword: appSupport.cm.hash(payPassword),
				comfirm_payPassword: appSupport.cm.hash(comfirm_payPassword),
				userId: localStorage.getItem("userId")
			}
			appSupport.cm.postAjaxFunction(url, data, setPswdSuccess);
		}
	})
	//	校验密码格式
	function checkPswd() {
		var r = /^[0-9]*$/;
		var payPassword = $("#userPswd").val();
		var comfirm_payPassword = $("#twoPswd").val();
		if(payPassword.length < 6 || comfirm_payPassword < 6 || payPassword == "" || comfirm_payPassword == "") {
			var msg = "请输入6位数字密码！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else if(payPassword != comfirm_payPassword) {
			var msg = "两次密码不一致！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else if(!r.test(payPassword)) {
			var msg = "请输入数字密码！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else {
			return true;
		}
	}

	function setPswdSuccess(evt) {
		if(evt.code == "1") {
			$("#serPayPswd").hide();
			var msg = "设置支付密码成功！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			localStorage.setItem("hasPayPassword", "true");
		}
	}

})