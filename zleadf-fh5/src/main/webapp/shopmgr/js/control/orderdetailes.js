$(function() {
	var orderType = appSupport.cm.queryString("ordertype");
	var index = 1 //默认微信支付
	if(orderType == 0) {
		$(".orderdetailes_title span").html("买家未付款");
		$(".foot_words").html("待付款");
	} else if(orderType == 1) {
		$(".orderdetailes_title span").html("买家已付款");
		$(".foot_words").html("待发货");
		$(".pay_btn").hide();
		var str = "";
		str += "<li>支付明细</li>";
		str += "<li>成长贝:<span id='grouthBei'></span></li>";
		str += "<li>需付款:<span id='waitPayPrice' class='red_words'></span></li>";
		$("#orderdetailesUl").empty().append(str);
		var orderPayTime = "";
		orderPayTime = "<li>支付时间:<span id='orderPayTime'></span></li>";
		$(".orderdetailes_ul3").append(orderPayTime);
	}

	function init() {
		var url = baseUrl.formal + "order/order_info.html";
		var data = {
			orderId: appSupport.cm.queryString("orderid"),
			transFerFee: appSupport.cm.queryString("transFerFee"),
			waitPayPrice: appSupport.cm.queryString("waitPayPrice")
		};
		appSupport.cm.postAjaxFunction(url, data, orderInfo);
	}
	init();
	//时间戳转换
	function add0(m) {
		return m < 10 ? '0' + m : m
	}

	function format(timestamp) {
		//timestamp是整数，否则要parseInt转换,不会出现少个0的情况

		var time = new Date(timestamp);
		var year = time.getFullYear();
		var month = time.getMonth() + 1;
		var date = time.getDate();
		var hours = time.getHours();
		var minutes = time.getMinutes();
		var seconds = time.getSeconds();
		return year + '-' + add0(month) + '-' + add0(date) + ' ' + add0(hours) + ':' + add0(minutes) + ':' + add0(seconds);
	}
	//订单详情回调
	function orderInfo(evt) {
		if(evt.code == "1") {
			productShow(evt);
			addressShow(evt);
			orderShow(evt);
			$("#loding").hide();
		}
	}
	//	展示商品信息
	function productShow(evt) {
		var length = evt.data.orderAttributeMap.length;
		var car = evt.data.orderAttributeMap;
		var str = "";
		for(var i = 0; i < length; i++) {
			str += "<div class='prusuct_li prusuct_li2 clearfix'>";
			str += "<img class='show_img' src=" + car[i].logo + ">";
			str += "<div class='prusuct_li_eords'>";
			str += "<p>" + car[i].productName + "</p>";
			str += "<p>" + car[i].attributeName + "</p>";
			str += "<div class='clearfix'>";
			str += "<p class='fl'>¥" + car[i].buyPrice + "</p>";
			str += "<div class='shopcaradd clearfix'>";
			str += "<p class='fr'>x" + car[i].buyNum + "</p>";
			str += "</div>";
			str += "</div>";
			str += "</div>";
			str += "</div>";
		}
		$("#product").append(str);
		//		$("#logo").attr("src", evt.data.orderAttributeMap[0].logo);
		//		$("#productName").html(evt.data.orderAttributeMap[0].productName);
		//		$("#attributeName").html(evt.data.orderAttributeMap[0].attributeName);
		//		$("#buyPrice").html("¥" + evt.data.orderAttributeMap[0].buyPrice);
		//		$("#buyNum").html("x" + evt.data.orderAttributeMap[0].buyNum);
	}
	//展示地址
	function addressShow(evt) {
		$("#name").html(evt.data.address.name);
		$("#phone").html(evt.data.address.phone);
		var userAddress=evt.data.address.provinceId + evt.data.address.cityId + evt.data.address.districtId + evt.data.address.address;
				if(userAddress.length>14){
					userAddress=userAddress.substring(0,14)+"...";
				}
				$("#userAddress").html(userAddress);
		//$("#userAddress").html(evt.data.address.provinceId + evt.data.address.cityId + evt.data.address.districtId + evt.data.address.address);
	}
	//	展示订单总价明细
	function orderShow(evt) {
		$("#proTotalPrice").html("¥" + evt.data.proTotalPrice);
		$("#transFerFee").html("¥" + evt.data.transFerFee);
		$("#orderPrice").html("¥" + evt.data.orderPrice);
		$("#grouthBei").html("¥-" + evt.data.grouthBei);
		$("#waitPayPrice").html("¥" + evt.data.waitPayPrice);
		$("#backGrouthBey").html("¥" + evt.data.backGrouthBey);
		$("#orderNo").html(evt.data.orderNo);
		$("#orderCreateTime").html(format(evt.data.orderCreateTime.time));
		$("#invalidTimeNote").html(evt.data.invalidTimeNote);
		$("#needPayMoney").text("¥" + evt.data.waitPayPrice);
		if(orderType == 1) {
			$("#orderPayTime").text(format(evt.data.orderPayTime.time));
			$("#backGrouthBey").html("¥" + evt.data.backRebate);
		}
	}
	//	取消订单
	$("#cancelOrder").on("click", function() {
		var url = baseUrl.formal + "order/cancel_order.html";
		var data = {
			orderId: appSupport.cm.queryString("orderid")
		};
		appSupport.cm.postAjaxFunction(url, data, cancelOrderFunction);
	})

	function cancelOrderFunction(evt) {
		if(evt.code == "1") {
			var msg = "取消成功";

			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				window.location.href = "class.html?p_type=" + 0;
				$(".orderdetailes").remove();
			}, 500);

		}
	}
	$("#payFor").on("click", function() {
		$(".pay_detailes").show();
		order.id = appSupport.cm.queryString("orderid");
	});
	//			取消支付跳转到订单详情页
	$(".close_pay,.shdow").on("click", function() {
		$(".pay_detailes").hide();

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
				orderId: appSupport.cm.queryString("orderid"),
				userno: localStorage.getItem("userNo")
			};
			appSupport.cm.postAjaxFunction(url, data, weiXinPay);
		} else if(index == 2) {
			//成长金支付
			$(".pswd_pay_config").show();
			$("#carNumberFirst,#forgetPswd,#carNumberOther2").show();
			$("#nowPay").hide(100);
		}
	})
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
						window.location.href = "orderdetailes.html?orderid=" + appSupport.cm.queryString("orderid") + "&transFerFee=" + appSupport.cm.queryString("transFerFee") + "&waitPayPrice=" + appSupport.cm.queryString("waitPayPrice") + "&ordertype=" + 1;
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
	});
})