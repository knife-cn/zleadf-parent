$(function() {
	var indexWeiXin = 1 //默认微信支付
	var flag = appSupport.cm.queryString("order_ty");
	$(".ordermanage_title li p").removeClass("active");
	$(".ordermanage_title li").eq(flag).find("p").addClass("active");
	init();

	function init() {
		var url = baseUrl.formal + "order/order_manage.html";
		var data = {
			flag: flag,
			page: 1,
			userId: localStorage.getItem("userId")
		};
		appSupport.cm.postAjaxFunction(url, data, showOrder);
	}

	function showOrder(evt) {
		if(evt.code == "1") {
			$("#orderList").empty();
			if(evt.data.length == 1) {
				var str = "";
				str += "<img src= img/order_none_03.png class='no-order-img'>";
				str += "<div class='no-order-words'>暂无订单记录</div>";
				$("#orderList").append(str);
				$("#loding").hide();
			} else {
				var str = "";
				var length = evt.data.length - 1;
				var car = evt.data;
				for(var i = 0; i < length; i++) {
					str += "<li class='prusuct_li prusuct_li2 clearfix'>";
					length2 = car[i].length - 2;
					if(car[i][length2] == "1") {
						str += "<h2><span>买家未付款</span></h2>";
					} else if(car[i][length2] == "2") {
						str += "<h2><span>买家已付款</span></h2>";
					} else if(car[i][length2] == "3") {
						str += "<h2><span>卖家已发货</span></h2>";
					} else if(car[i][length2] == "4") {
						str += "<h2><span>买家已收货</span></h2>";
					}
					iLength = car[i].length - 6;
					for(var j = 0; j < iLength; j++) {
						str += "<img class='show_img' src=" + car[i][j].logo + ">";
						str += "<div class='prusuct_li_eords'>";
						str += "<p>" + car[i][j].proName + "</p>";
						str += "<p>" + car[i][j].attributeName + "</p>";
						str += "<div class='clearfix'>";
						str += "<p class='fl'>¥" + car[i][j].buyPrice + "</p>";
						str += "<div class='shopcaradd clearfix'>";
						str += "<p class='fr'>x" + car[i][j].buyNum + "</p>";
						str += "</div>";
						str += "</div>";
						str += "</div>";
						str += "<p class='line fl'></p>";
					}
					str += "<div class='fl pruduct_jian'>";
					buyNum = car[i].length - 4;
					money = car[i].length - 5;
					payFree = car[i].length - 3;
					str += "共" + car[i][buyNum] + "件商品   合计:¥" + car[i][money] + "(含运费" + car[i][payFree] + ")";
					str += "</div>";
					if(car[i][length2] == "1") {
						str += "<div class='fr cliarfix' style='margin-right: 0.38rem; margin-top:0.27rem;'>";
						str += "<div class='cancel fl'>取消订单</div>";
						str += "<div class='pay fl'>付款</div>";
						str += "</div>";
					} else if(car[i][length2] == "3") {
						str += "<div class='fr cliarfix' style='margin-right: 0.38rem; margin-top:0.27rem;'>";
						str += "<div class='see-map fl'>查看物流</div>";
						str += "<div class='y-prduct fl'>确认收货</div>";
						str += "</div>";
					}
					str += "</li>";
				}
				$("#orderList").append(str);
				$("#loding").hide();
				//			取消订单
				$(".cancel").on("click", function() {
					$("#loding").show().css("background", "none");
					index = $(this).parent().parent().index();
					var length = car[index].length - 1;
					console.log(index);
					var url = baseUrl.formal + "order/cancel_order.html";
					var data = {
						orderId: car[index][length]
					};
					appSupport.cm.postAjaxFunction(url, data, canelOrder);
				})
				//确认收货
				$(".y-prduct").on("click", function() {
					$("#loding").show().css("background", "none");
					index = $(this).parent().parent().index();
					var length = car[index].length - 1;
					console.log(index);
					var url = baseUrl.formal + "order/confirm_receipt.html";
					var data = {
						orderId: car[index][length]
					};
					appSupport.cm.postAjaxFunction(url, data, canelOrder);
				})
				//查看物流
				$(".see-map").on("click", function() {
					index = $(this).parent().parent().index();
					var length = car[index].length - 1;
					window.location.href="order-track.html?orderno="+car[index][length];
					//window.open("order-track.html?orderno="+car[index][length]);
					//console.log(index);
				})
				
				//			付款
				$(".pay").on("click", function() {
					$(".pay_detailes").show();
					var index = $(this).parent().parent().index();
					var length = car[index].length - 1;
					order.id = car[index][length];
					$("#needPayMoney").text("¥" + car[index][money]);
				});
			}

		}
	}
	//	选项切换
	$(".ordermanage_title li").on("click", function() {
		$("#loding").show().css("background", "none");
		flag = $(this).index();
		$(".ordermanage_title li p").removeClass("active");
		$(".ordermanage_title li").eq(flag).find("p").addClass("active");
		init();
	});

	function canelOrder(evt) {
		if(evt.code == "1") {
			init();
		}
	}
	//			取消支付
	$(".close_pay,.shdow").on("click", function() {
		$(".pay_detailes").hide();

	})
	//选择支付方式
	$(".check_fang li div").on("click", function() {
		indexWeiXin = $(this).parent().index();
		$(".check_fang li div").removeClass("active");
		$(this).addClass("active");
	})
	//			点击付款
	$("#goPay").on("click", function() {
		if(indexWeiXin == 1) {
			//微信支付
			var url = baseUrl.formal + "wxPay/wxPay.html";
			var data = {
				openId: localStorage.getItem("openId"),
				orderId: order.id,
				userno: localStorage.getItem("userNo")
			};
			appSupport.cm.postAjaxFunction(url, data, weiXinPay);
		} else if(indexWeiXin == 2) {
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
						// http:'//weixin.p-share.com/wx_sa_share/html5/views/index.html#/home/XHprotocol'
						window.location.href = "orderdetailes.html?orderid=" + order.id + "&transFerFee=" + payFree + "&waitPayPrice=" + money + "&ordertype=" + 1;
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