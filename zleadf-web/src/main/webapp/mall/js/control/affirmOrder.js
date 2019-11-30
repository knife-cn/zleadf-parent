$(function() {
	shifujine=0;
	orderList();
})

//订单列表
function orderList() {
	/*var goodsId=appSupport.cm.queryString("goodsId");
	var buyNum=appSupport.cm.queryString("buyNum");*/
	var goodsId=appSupport.cm.queryString("goodsId");
	var buyNum=appSupport.cm.queryString("buyNum");
	var prodType=appSupport.cm.queryString("prodType");
	var html='';
	$.ajax({
		url: '../zlead/torder/newConfirmOrder',
        type: 'post',
        data: {
        	"goodsId":goodsId,
        	"buyNum":buyNum,
        	"prodType":prodType
        },
        success: function(res) {
        	if(res.data.code=1){
        		html+='<div class="orderTitle">'
        			+'<img src="img/icon-back.png" class="fl go-back-1" onclick="goback()">'
        			+'<span>确认订单</span>'
        			+'</div>';
        		if(res.data.defaultAddress!=null){
        			html+='<div class="address">'
        				+ '<p><img src="img/con-ren.png" alt=""><span class="memberName">'+res.data.defaultAddress.memberName+'</span></p>'
        				if(res.data.defaultAddress.address.length>23){
        					html+='<p><img src="img/icon-dingweia.png" alt=""><span class="detailAddress">'+res.data.defaultAddress.address.substring(0, 23)+"..."+'</span></p>';
        				}else{
        					html+='<p><img src="img/icon-dingweia.png" alt=""><span class="detailAddress">'+res.data.defaultAddress.address+'</span></p>';
        				}
        				html+='<p><img src="img/icon-dianhua.png" alt=""><span class="phone">'+res.data.defaultAddress.phone+'</span></p>'
        				html+='<input class="addressId" type="hidden" value="'+res.data.defaultAddress.id+'">'
        				+ '</div>';
        		}else{
        			html+='<div class="nullAddress" >'
        				+ '<img src="img/img-tjdz.png" alt="">'
        				+ '<p>您还没有添加地址哦</p>'
        				+ '<div class="add" onclick="add()">'
        				+ '<img src="img/btn-tjdzi.png" alt="">'
        				+ '</div>'
        				+ '</div>';
        		}
        		for(var i=0;i<res.data.cartInfoList.length; i++){
    				html+='<div class="order">'
    					 +'<div class="order-1">'
    					 +'<input type="radio" class="shopId" name="shopId" value="'+res.data.cartInfoList[i].shopId+'" onclick="TotalPrice('+res.data.cartInfoList[i].shopId+','+i+')")">'
            			 +'<span >'+res.data.cartInfoList[i].shopName+'</span>'
            			 +'</div>'
    					 +'<input type="hidden" class="inputAllPrice" value="'+res.data.cartInfoList[i].totalPrice+'">';
        			for(var j=0;j<res.data.cartInfoList[i].confirmOrderInfo.length; j++){
        				html+='<div class="order-2">'
            			+ '<div class="order-2-img">'
            			+ '<img src="http://116.62.124.171/group1/'+res.data.cartInfoList[i].confirmOrderInfo[j].goods.firstImg+'" alt="">'
            			+ '</div>'
            			html+='<div class="order-2-info">';
            			if(res.data.cartInfoList[i].confirmOrderInfo[j].goods.fullName.length > 14){
            				html+='<p class="order-2-info1 mb" id="'+res.data.cartInfoList[i].confirmOrderInfo[j].goods.id+'">'+res.data.cartInfoList[i].confirmOrderInfo[j].goods.fullName.substring(0, 14)+"..."+'</p>';
            			}else{
            				html+='<p class="order-2-info1 mb" id="'+res.data.cartInfoList[i].confirmOrderInfo[j].goods.id+'">'+res.data.cartInfoList[i].confirmOrderInfo[j].goods.fullName+'</p>';
            			}
        				html+='<p class="order-2-info2 mb">';
        				html+='<img src="img/img-quan.png" alt="">'
            			+ '<span>默认规格</span>'
            			+ '</p>'
            			+ '<p class="order-2-info2">'
            			+ '<img src="img/img-quan.png" alt="">'
            			+ '<span><label>'+res.data.cartInfoList[i].confirmOrderInfo[j].buyNum+'</label>件</span>'
            			+ '</p>'
            			+ '<p class="order-2-info3">'
            			+ '<label class="zj">'+res.data.cartInfoList[i].confirmOrderInfo[j].goods.price+'</label>'
            			+ '<input type="hidden" class="inputZj" value="'+res.data.cartInfoList[i].confirmOrderInfo[j].totalPrice+'">';
            			if(prodType==1){
            				html+='<span>积分</span>';
            			}else{
            				html+='<span>RMB</span>';
            			}
            			html+='</p>';
            			html+='</div>';
            			html+='</div>';
        			}
    				+'</div>';
    			}
        		if(prodType==1){
        			$(".total").hide();
        			$(".jf").show();
        		}else{
        			$(".total").show();
        			$(".jf").hide();
        		}
        		$(".orderInfoList").html(html);
        		TotalPrice();
    		}
        }
	})
}

function TotalPrice(id,index) {
	var total=$(".inputAllPrice").eq(index).val();
	$(".totalPrice").html(total);
	$(".totalJf").html(total);
}

function goPay(){
	var shopId=$('input[name="shopId"]:checked').val();
	var addressId=$(".addressId").val();
	var buyNum=appSupport.cm.queryString("buyNum");
	var goodsId=appSupport.cm.queryString("goodsId");
    prodType=appSupport.cm.queryString("prodType");
	var memberName=$(".memberName").text();
	var detailAddress=$(".detailAddress").text();
	var phone=$(".phone").text();
	var totalPrice=$(".totalPrice").text();
	if(prodType==0){//prodType==0积分购
		orderType=5;	//orderType==5积分购
	}else if(prodType==1){//prodType==1积分换
		orderType=4;	//orderType==4积分换
	}/*else if(prodType==2){//prodType==2共享商城
		orderType=0;	//orderType==0共享商城
	}else if(prodType==3){//prodType==3代理商城
		orderType=9;	//orderType==10代理商城
	}else if(prodType==5){//prodType==5附近商家
		orderType=7;	//orderType==7附近商家
	}else if(prodType==6){//prodType==6加盟商城
		orderType=14;//orderType==14会员购买加盟商城订单
	}else if(prodType==4){//prodType==4加盟商进货首单
		orderType=12;//加盟商采购首单
	}*/
	var status=appSupport.cm.queryString("status");
	if(shopId==null || shopId==""){
		var msg = "请选择需要结算的店铺！";
		 appSupport.cm.errorMessageShow(errorMsg, msg);
		 setTimeout(function() {
			 appSupport.cm.errorMessageHide(errorMsg);
		 }, 1000);
	}else if(status!=null){
		if(status==2 ||status==5){
			$.ajax({
				url: '../zlead/torder/newDoAddCarOrder2',
		        type: 'post',
		        data: {
		        	"goodsId":goodsId,
		        	"buyNum":buyNum,
		        	"addressId":addressId,
		        	"orderType":orderType,
		        	"shopId":shopId
		        },
		        success: function(res) {
		        	 if(res.code==1){
		        		 var msg = res.message;
		     			 appSupport.cm.errorMessageShow(errorMsg, msg);
		     			 setTimeout(function() {
		     				 appSupport.cm.errorMessageHide(errorMsg);
		     				 var orderNo=res.data.orderList;
		     				 if (prodType==3){
                                 window.location.href = "agentOrderPay.jsp?status=2&orderNo="+orderNo+"&goodsId="+goodsId+"&buyNum="+buyNum+"&memberName="+memberName+"&detailAddress="+detailAddress+"&phone="+phone+"&totalPrice="+totalPrice+"&prodType="+prodType;;
							 }else{
                                 window.location.href = "orderPay.jsp?orderNo="+orderNo+"&prodType="+prodType;
							 }

		     			 }, 1000);
		        	 }                                        
		        }
			})
		}else if(status==1){
			$.ajax({
				url: '../zlead/torder/newDoAddOrder',
		        type: 'post',
		        data: {
		        	"goodsId":goodsId,
		        	"buyNum":buyNum,
		        	"addressId":addressId,
		        	"orderType":orderType,
		        	"storeBuyType":"1",
		        	"buyType":"1"
		        },
		        success: function(res) {
		        	 if(res.code==1){
	     				 var orderNo=res.data.orderSn;
	     				 if(orderType==4){
	     					$.ajax({
	     						url: '../zlead/torder/pointOrderPay',
	     				        type: 'post',
	     				        data: {
	     				        	"orderNo":orderNo
	     				        },
	     				        success: function(res) {
	     				        	 if(res.code==1){
	     				        		 var msg = res.message;
	     				     			 appSupport.cm.errorMessageShow(errorMsg, msg);
	     				     			 setTimeout(function() {
	     				     				 appSupport.cm.errorMessageHide(errorMsg);
	     				     				 window.location.href = "index.jsp";
	     				     			 }, 1000);
	     				        	 }else{
	     				        		var msg ="兑换失败，系统异常！";
	     				     			 appSupport.cm.errorMessageShow(errorMsg, msg);
	     				     			 setTimeout(function() {
	     				     				 appSupport.cm.errorMessageHide(errorMsg);
	     				     			 }, 1000); 
	     				        	 }                                         
	     				        }
	     					})
	     				 }else{
	     					 window.location.href = "orderPay.jsp?orderNo="+orderNo+"&prodType="+prodType;
	     				 }
		        	 }else{
		        		 var msg = res.message;
		     			 appSupport.cm.errorMessageShow(errorMsg, msg);
		     			 setTimeout(function() {
		     				 appSupport.cm.errorMessageHide(errorMsg);
		     			 }, 1000); 
		        	 }                                          
		        }
			})
		}
	}
}

function add(){
	 window.location.href ="addAddress.jsp"
}

function goback(){
	 window.history.back();
}
