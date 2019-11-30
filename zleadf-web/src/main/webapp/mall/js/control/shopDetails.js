$(function() {
	hotPrudict();
	init();
})
	
function init(){
	var shopId=appSupport.cm.queryString("shopId");
	$.ajax({
        type: 'post',
        url: '../ajeasy/allianceBusiness/shopDetail',
        data: {
        	"shopId":shopId
        },
        success: function(res){
        	if(res.code==1){
        		$(".addressInfo-1-1-img").attr("src","http://116.62.124.171"+res.data.logoImg);
        		$(".addressInfo-1-2-p1").html(res.data.name);
        		$(".companyPhone").html(res.data.companyPhone);
        		$(".companyAddress").html(res.data.companyAddress);
        	}
        }
	})
}

//热卖推荐
var page = 0;
var size = 6;
function hotPrudict(){
	var html='';
	var shopId=appSupport.cm.queryString("shopId");
	$('.list').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	page++;
        // 拼接HTML
        var html = '';
        $.ajax({
            type: 'get',
            url: '../ajeasy/allianceBusiness/allianceShopGoodsList?page='+page+'&size='+size,
            data: {
            	"shopId":shopId
            },
            success: function(res){
            	if(res.data.length>0){
            		for(var i=0;i<res.data.length;i++){
    					html+='<li id="'+res.data[i].id+'" onclick="toDetails('+res.data[i].id+')">';
    					html+='<div class="list-info">';
                		html+='<div class="list-info-img">';
    	     		 		  html+='<img src="http://116.62.124.171'+res.data[i].img+'" alt=""/>';
    	     		 		  html+='</div>';
    	     		 		  html+='<div class="list-info-title">';
    	     		 		  if(res.data[i].fullName.length > 24){
    	     		 			html+='<p>'+res.data[i].fullName.substring(0, 24)+"..."+'</p>';  
    	     		 		  }else{
    	     		 			html+='<p>'+res.data[i].fullName+'</p>';
    	     		 		  }
               		 		  html+='</div>';
               		 		  html+='<div class="list-info-peole">'+
               		 		  '<img src="img/img-quan.png" alt=""/>'+
               		 		  '<span style="margin-left:0.12rem;">已有<label>'+res.data[i].sales+'</label>人购买</span>'+
							  '</div>'+
							  '<div class="list-info-price">'+
							  '<p>'+
							  '<span class="list-price">RMB<label>'+ res.data[i].price+'</label></span>'+
							  '</p>'+
							  '</div>'+
               		 		  '</div>'+
               		 		  '</li>';  
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
                    $('.listUl').append(html);
                    // 每次数据插入，必须重置
                    me.resetload();
                },1000);
            },
            error: function(xhr, type){
                alert('加载错误');
                // 即使加载出错，也得重置
                me.resetload();
            }
        })
    	}
    })
}
	
function toDetails(id){
	window.location.href = "goodsDetails.jsp?goodsId="+id;
}
	
function gotoSeach(){
	window.location.href = "seach.jsp";
}