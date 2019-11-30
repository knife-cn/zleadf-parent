$(function(){
	init();
})

var pageNum = 0;
var size = 5;
function init(){
	var html='';
	$('.list-2').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	pageNum++;
        // 拼接HTML
		$.ajax({
			url: '../joinShop/joinShopList',
	        type: 'post',
	        data: {
	        	"pageNum":pageNum,
	        	"size":size
	        },
	        success: function(res) {
        		if(res.data.length>0){
	        		for(var i=0;i<res.data.length;i++){
	        			html+='<ul>';
	        			html+='<li style="width:15%">'+res.data[i].name+'</li>';
	        			html+='<li style="width:18%">'+res.data[i].contactName+'</li>';
	        			html+='<li style="width:20%">'+res.data[i].contactPhone+'</li>';
	        			if(res.data[i].status==2){
	        				html+='<li style="width:20%">审核失败</li>';
	        			}else if(res.data[i].status==3){
	        				html+='<li style="width:20%">审核通过</li>';
	        			}
	        			html+='<li style="width:27%">';
	        			html+='<span class="liAdit">修改</span>';
	        			html+='<span class="liClose">关闭</span>';
	        			html+='</li>';
	        			html+='</ul>';
	        		}
	        	}else{
	        		// 锁定
                    me.lock();
                    // 无数据
                    me.noData();
	        	}
        		// 为了测试，延迟1秒加载
                setTimeout(function(){
                    // 插入数据到页面，放到最后面
                    $('#list').html(html);
                    // 每次数据插入，必须重置
                    me.resetload();
                },1000);
	        },
	        error: function(xhr, type){
                // 即使加载出错，也得重置
                me.resetload();
            }
		})
    }
	})
}

function cancel(id,index){
	var orderId=id;
	$.ajax({
		url: '../ajeasy/order/cancelOrder',
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
    				 init(-1);
    			 }, 1000);
        	}
        }
	})
}

function gotoAllianceEnter(){
	window.location.href = "allianceEnter.jsp";
}