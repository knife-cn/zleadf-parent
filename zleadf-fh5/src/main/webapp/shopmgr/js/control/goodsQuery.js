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
        var shopId=appSupport.cm.queryString("shopId");
        // var baseUrl=localStorage.getItem("weburl");
        pageNum++;
        // 拼接HTML
		$.ajax({
			url: '/zlead/tgoods/goodsList',
	        type: 'post',
	        data: {
	        	"page":pageNum,
                "prodType": 0,
				"shopId":shopId
	        },
	        success: function(res) {
	        	if(res.code==1){
	        		if(res.data.length>0){
		        		for(var i=0;i<res.data.length;i++){
		        			html+='<ul>';
		        			if(res.data[i].fullName.length>3){
                                html+='<li style="width:15%">'+res.data[i].fullName.substring(0, 3)+"..."+'</li>';
							}else{
                                html+='<li style="width:15%">'+res.data[i].fullName+'</li>';
							}
		        			if(res.data[i].pointType==0){
		        				html+='<li style="width:13%">积分购</li>';
		        			}else if(res.data[i].pointType==1){
		        				html+='<li style="width:13%">积分换</li>';
		        			}else if(res.data[i].pointType==2){
		        				html+='<li style="width:13%">共享商城</li>';
		        			}else if(res.data[i].pointType==3){
		        				html+='<li style="width:13%">代理商城</li>';
		        			}else if(res.data[i].pointType==4){
		        				html+='<li style="width:13%">加盟商商品</li>';
		        			}else if(res.data[i].pointType==5){
		        				html+='<li style="width:13%">附近商家</li>';
		        			}else if(res.data[i].pointType==6){
		        				html+='<li style="width:13%">会员订购</li>';
		        			}
		        			html+='<li style="width:13%">'+res.data[i].price+'</li>';
		        			if(res.data[i].isMarketable==1){
		        				html+='<li style="width:11%">上架</li>';
		        			}else{
		        				html+='<li style="width:11%">未上架</li>';
		        			}
		        			html+='<li style="width:13%">'+res.data[i].stock+'</li>';
		        			html+='<li style="width:13%">'+res.data[i].sales+' </li>';
		        			html+='<li style="width:22%">';
		        			html+='<span class="liAdit-1">修改</span>';
		        			html+='<span class="liDet">删除</span>';
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
	                    $('#list').append(html);
	                    // 每次数据插入，必须重置
	                    me.resetload();
	                },1000);
	            }else if(res.code==2){
                    // 锁定
                    me.lock();
                    // 无数据
                    me.noData();
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

function gotoGoodsUpload(){
	window.location.href = "goodsUpload.jsp";
}
