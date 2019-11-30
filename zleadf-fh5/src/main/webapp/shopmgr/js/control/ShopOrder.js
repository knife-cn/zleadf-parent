$(function(){
	init(-1);
})
function changeTab(type) {
	$(".chooseType li").removeClass("active");
	$("#type"+type).addClass("active");
	$("#orderList").empty();
	pageNum=0;
	init(type);
}

var pageNum = 0;
var size = 5;
function init(val){
	var status=val;
	var html='';
	var shopId=localStorage.getItem("shopId");
	$('.orderInfoList').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	pageNum++;
        // 拼接HTML
		$.ajax({
			url: '../../zlead/torder/shopOrderList',
	        type: 'post',
	        data: {
                "isPointOrder":"false",
                "status":status,
                "pageNum":pageNum,
                "size":size,
				"orderType":11,
				"shopId":shopId
	        },
	        success: function(res) {
	        	if(res.code==1){
	        		if(res.data.result.length>0){
		        		for(var i=0;i<res.data.result.length;i++){
		        			html+='<ul class="orderList">';
		        			html+='<li class="list">';
		        			html+='<div class="listName">';
		        			html+='<img class="listNameImg1" src="img/img-quan.png" alt="">';
		        			html+='<span>【<a class="sn">'+res.data.result[i].sn+'</a>】</span>';
		        			if(res.data.result[i].shopName.length>9){
		        				html+='<span>'+res.data.result[i].shopName.substring(0, 9)+"..."+'</span>';
		        			}else{
		        				html+='<span>'+res.data.result[i].shopName+'</span>';
		        			}
		        			
		        			if(res.data.result[i].status==0){
		        				html+='<label>待付款</label>';
		        			}else if(res.data.result[i].status==1){
		        				html+='<label>待发货</label>';
		        			}else if(res.data.result[i].status==2){
		        				html+='<label>待收货</label>';
		        			}else if(res.data.result[i].status==3){
		        				html+='<label>已完成</label>';
		        			}else if(res.data.result[i].status==4){
		        				html+='<label>已取消</label>';
		        			}
		        			html+='<img class="listNameImg2" src="img/img-quan.png" alt="">';
		        			html+='</div>';
		        			for(j=0;j<res.data.result[i].orderGoodsList.length;j++){
		        				html+='<div class="listINfo">';
			        			html+='<div class="listINfo-1">';
			        			html+='<img src="http://116.62.124.171/group1/'+res.data.result[i].orderGoodsList[j].goodsImg+'" alt="">';
			        			html+='</div>';
			        			html+='<div class="listINfo-2">';
			        			html+='<p class="listINfo-2-p mt">'+res.data.result[i].orderGoodsList[j].goodsName+'</p>';
			        			html+='<input class="orderId" type="hidden" value="'+res.data.result[i].orderGoodsList[j].orderId+'">';
			        			html+='<p class="mt">';
			        			html+='<img src="img/img-quan.png" alt="">';
			        			html+='<span class="listINfo-2-span">默认规格</span>';
			        			html+='</p>';
			        			html+='<p class="mt">';
			        			html+='<img src="img/img-quan.png" alt="">';
			        			html+='<span class="listINfo-2-span"><label>'+res.data.result[i].orderGoodsList[j].count+'</label>件</span>';
			        			html+='</p>';
			        			html+='<p>';
			        			html+='<img src="img/img-quan.png" alt="">';
			        			html+='<span class="listINfo-2-span">需支付金额</span>';
			        			html+='<span class="listINfo-2-price">RMB<label>'+res.data.result[i].orderGoodsList[j].price+'</label></span>';
			        			html+='</p>';			
			        			html+='</div>';			
			        			html+='</div>';
		        			}
		        			html+='<div class="listBtn">';
		        			if(res.data.result[i].status==0){
		        				html+='<div class="listBtn-btn">';				
			        			html+='<img src="img/btn-lijizhifu.png" alt="" onclick="buy('+res.data.result[i].id+','+i+')">';
			        			html+='</div>';		
			        			html+='<div class="listBtn-btn">';	
			        			html+='<img src="img/btn-quxiao.png" alt="" onclick="cancel('+res.data.result[i].id+','+i+')">';
			        			html+='</div>';		
		        			}else if(res.data.result[i].status==1){
			        			html+='<div class="listBtn-btn">';	
			        			html+='<img src="img/btn-quxiao.png" alt="" onclick="cancel('+res.data.result[i].id+','+i+')">';
			        			html+='</div>';		
		        			}else if(res.data.result[i].status==2){
                                html+='<div class="listBtn-btn">';
                                html+='<img src="img/btn-ckwl.png" alt="" onclick="logistics('+res.data.result[i].sn+','+i+')">';
                                html+='</div>';
                            }else if(res.data.result[i].status==3){
		        				html+='<div class="listBtn-btn">';				
			        			html+='<img src="img/btn-scdd.png" alt="" onclick="cancel('+res.data.result[i].id+','+i+')">';
			        			html+='</div>';		
		        			}else if(res.data.result[i].status==4){
		        				html+='<div class="listBtn-btn">';				
			        			html+='<img class="cancel" src="img/btn-scdd.png" alt="">';			
			        			html+='</div>';		
		        			}
		        			html+='</div>';			
		        			html+='</li>';		
		        			html+='</ul>';		
		        		}
		        	}
	        		// 插入数据到页面，放到最后面
		            $('#orderList').html(html);
		            // 每次数据插入，必须重置
		            me.resetload();
	        	}else if(res.code==2){
                    // 锁定
                    me.lock();
                    // 无数据
                    me.noData();
                    $('#orderList').append("<p style='text-align:center;font-size:0.26rem;line-height:0.5rem;'>暂无更多订单信息</p>");
                }
            },
            error: function(xhr, type){
                // 即使加载出错，也得重置
                me.resetload();
            }
		})
    }
	})
}

function buy(id,index){
	var orderNo=$(".sn").eq(index).text();
	window.location.href = "agentOrderPay.jsp?orderNo="+orderNo;
}

function cancel(id,index){
	var orderId=id;
	$.ajax({
		url: '../../zlead/torder/cancelOrder',
        type: 'post',
        data: {
        	"orderId":orderId
        },
        success: function(res) {
        	if(res.code==1){
        		var msg = res.message;
    			 appSupport.cm.errorMessageShow(errorMsg, msg);
    			 setTimeout(function() {
    				 appSupport.cm.errorMessageHide(errorMsg);
                     location.reload();
    			 }, 1000);
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

function logistics(sn,index){
    var orderSn = sn;
    window.location.href="logistics.jsp?orderSn="+orderSn;
}