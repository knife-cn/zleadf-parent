$(function() {
	shifujine=0;
	agentInfo();
	init();
})

//获取信息
function init() {
	
}

function agentInfo(){
	var pointType=appSupport.cm.queryString("pointType");
	if(pointType==3){
		$.ajax({
		    url: '../agent/getAgentCashBalance',
		    type: 'post',
            success: function(res) {
		        if(res.code==1){
		        	$(".dikou").html(res.data.agentCashAcctBalance);
		        	price=$(".price").text();
		        	yunfei=$(".yunfei").text();
		        	dikou=$(".dikou").text();
		        	shifujine=new Number(price)+new Number(yunfei)-new Number(dikou);
                    shifujine=shifujine.toFixed(2);
		        	if (shifujine<0){
		        	    shifujine=0;
                    }
		        	$(".shifu").html(shifujine);
		        	$("#totalPrice").html(shifujine);

		        }else{
                    $(".dikou").html(0);
                    price=$(".price").text();
                    yunfei=$(".yunfei").text();
                    dikou=$(".dikou").text();
                    shifujine=new Number(price)-new Number(yunfei)-new Number(dikou);
                    shifujine=shifujine.toFixed(2);
                    if (shifujine<0){
                        shifujine=0;
                    }
                    $(".shifu").html(shifujine);
                    $("#totalPrice").html(shifujine);
				}
                if(shifujine==0){
		            $(".pay").hide();
                }
                $("#totalPrice").text(shifujine);
		    }
		})
	}
}

function gotoPay(){
	var orderNo=appSupport.cm.queryString("orderNo");
	var totalAmount=$("#totalPrice").text();
	var type=$('input[name="zf"]:checked').val();
    if(shifujine==0){
        //不跳转支付包
        $.ajax({
            url: '../agent/payAgentOrder',
            type: 'post',
            data:{
                orderId:orderNo,
                payType:4
            },
            success: function(res) {
                if(res.code==1){
                    var msg = res.message;
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                        window.location.href = "../index.jsp";
                    }, 1000);

                }else{
                    var msg = res.message;
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                }

            }
        })
    }else{
        $.ajax({
            url: '../agent/payAgentOrder',
            type: 'post',
            data:{
                orderId:orderNo,
                payType:3
            },
            success: function(res) {
                var msg = res.message;
                appSupport.cm.errorMessageShow("正在转入第三方支付", "正在转入第三方支付");
                setTimeout(function() {
                    window.location.href = "../ajeasy/pay/aliPagePay?orderNo="+orderNo+"&totalAmount="+totalAmount;
                }, 1000);
            }
        })
    }
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

}