$(function() {
	var orderSn=appSupport.cm.queryString("orderSn");
	var paySn=appSupport.cm.queryString("paySn");
	if(orderSn =="" || orderSn == undefined){
		init1();
	}else{
		init();
	}
})

//获取信息
function init() {
	var orderSn=appSupport.cm.queryString("orderSn");
	var html='';
	$.ajax({
		url: '../ajeasy/order/ShopOrderInfo',
        type: 'post',
        data: {
        	"orderSn":orderSn
        },
        success: function(res) {
        	if(res.code=1){
        		html+='<div class="orderTitle"><p>支付详情</p></div>';
        		html+='<div class="total">'
        			+'<span class="totalText">您本次支付金额</span>'
        			+'<span class="totalSpan">RMB<label id="totalPrice">'+res.data.zxOrder.payableAmount+'</label></span>'
        			+'</div>'
        			+'<div class="buy">'
        			+'<img src="img/btn-lijizhifu.png" alt="" onclick="gotoPay()">'
        			+'</div>';
        		html+='<div class="pay">'
        			+'<div class="payDiv">'
        			+'<input type="radio" id="zfb" name="zf" value="1">'
        			+'<div class="payDiv-kuang">'
        			+'<img src="img/img-zhifubao.png" alt="">'
        			+'<p>支付宝</p>'
        			+'</div>'
        			+'</div>'
        			+'<div class="payDiv">'
        			+'<input type="radio" id="wx" name="zf" value="2">'
        			+'<div class="payDiv-kuang">'
        			+'<img src="img/img-weixin.png" alt="">'
        			+'<p>微信</p>'
        			+'</div>'
        			+'</div>'
        			+ '</div>';
        		$(".orderInfo").html(html);
    		}
        }
	})
}

//获取信息
function init1() {
	var paySn=appSupport.cm.queryString("paySn");
	var html='';
	$.ajax({
		url: '../ajeasy/order/ShopOrderInfo',
        type: 'post',
        data: {
        	"paySn":paySn
        },
        success: function(res) {
        	if(res.code=1){
        		html+='<div class="orderTitle"><p>支付详情</p></div>';
        		html+='<div class="total">'
        			+'<span class="totalText">您本次支付金额</span>'
        			+'<span class="totalSpan">RMB<label id="totalPrice">'+res.data.zxOrder.payableAmount+'</label></span>'
        			+'</div>'
        			+'<div class="buy">'
        			+'<img src="img/btn-lijizhifu.png" alt="" onclick="gotoPay()">'
        			+'</div>';
        		html+='<div class="pay">'
        			+'<div class="payDiv">'
        			+'<input type="radio" id="zfb" name="zf" value="1">'
        			+'<div class="payDiv-kuang">'
        			+'<img src="img/img-zhifubao.png" alt="">'
        			+'<p>支付宝</p>'
        			+'</div>'
        			+'</div>'
        			+'<div class="payDiv">'
        			+'<input type="radio" id="wx" name="zf" value="2">'
        			+'<div class="payDiv-kuang">'
        			+'<img src="img/img-weixin.png" alt="">'
        			+'<p>微信</p>'
        			+'</div>'
        			+'</div>'
        			+ '</div>';
        		$(".orderInfo").html(html);
    		}
        }
	})
}

function gotoPay(){
	var orderNo=appSupport.cm.queryString("orderNo");
	var paySn=appSupport.cm.queryString("paySn");
	var totalAmount=$("#totalPrice").text();
	var type=$('input[name="zf"]:checked').val();
	if(paySn == "" || paySn == undefined){
		if (totalAmount!=0){
	        if($('input[name="zf"]').is(':checked')==true){
	            if(type==1){

	                window.location.href = "../ajeasy/pay/aliPagePay?orderNo="+orderNo+"&totalAmount="+totalAmount;
	            }else if(type==2){
	                var msg = "微信支付尚未开通！";
	                appSupport.cm.errorMessageShow(errorMsg, msg);
	                setTimeout(function() {
	                    appSupport.cm.errorMessageHide(errorMsg);
	                }, 500);
	            }
	        }else{
	            var msg = "请选择支付方式！";
	            appSupport.cm.errorMessageShow(errorMsg, msg);
	            setTimeout(function() {
	                appSupport.cm.errorMessageHide(errorMsg);
	            }, 500);
	        }
	    }
	}else{
		if (totalAmount!=0){
	        if($('input[name="zf"]').is(':checked')==true){
	            if(type==1){

	                window.location.href = "../ajeasy/pay/aliPagePay?paySn="+paySn+"&totalAmount="+totalAmount;
	            }else if(type==2){
	                var msg = "微信支付尚未开通！";
	                appSupport.cm.errorMessageShow(errorMsg, msg);
	                setTimeout(function() {
	                    appSupport.cm.errorMessageHide(errorMsg);
	                }, 500);
	            }
	        }else{
	            var msg = "请选择支付方式！";
	            appSupport.cm.errorMessageShow(errorMsg, msg);
	            setTimeout(function() {
	                appSupport.cm.errorMessageHide(errorMsg);
	            }, 500);
	        }
	    }
	}
}