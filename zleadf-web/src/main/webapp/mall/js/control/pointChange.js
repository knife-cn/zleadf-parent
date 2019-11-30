$(function() {
	hotPrudict();
	bannerShow();
})
	
//banner图
function bannerShow() {
	$.ajax({
		url: '../ajeasy/ads/adsList',
        type: 'post',
        data: {
        	adstype:'4'
        },
        success: function(res) {
        	 if (res.data && res.data.length > 0) {
    	        var _banner = '';
    	        for (var i=0; i<res.data.length; i++) {
    	        	//获取跳转的url
    	        	var contentPath = res.data[i].contentPath;
    	        	//获取banner图的跳转属性
    	        	var contentType = res.data[i].contentType;
    	        	_banner += '<div class="swiper-slide" onclick=goToBannerDetail("'+res.data[i].contentPath+'")><img src="http://116.62.124.171'+ res.data[i].adsImg +'" alt=""></div>';
    	        }
    	        $('.swiper-wrapper').html(_banner);
        	 }
        	 var mySwiper3 = new Swiper('#swiper-container3',{
    			effect : 'coverflow',
    			slidesPerView: 3,
    			centeredSlides: true,
    			coverflowEffect: {
    			    rotate: 0,
    			    stretch: 0,
    			    depth: 100,
    			    modifier: 4,
    			    slideShadows : true
			    },
    		})
        }
	})
}

//热卖推荐
var page = 0;
var size = 6;
function hotPrudict(){
	var html='';
	$('.list').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	page++;
        // 拼接HTML
        var html = '';
        $.ajax({
            type: 'get',
            url: '../ajeasy/goods/goodsList?page='+page+'&size='+size,
            data: {
            	pointType:'1'
            },
            success: function(res){
            	if(res.data.length>0){
            		for(var i=0;i<res.data.length;i++){
    					html+='<li id="'+res.data[i].id+'" onclick="tabChange('+res.data[i].id+','+i+')">';
    					html+='<div class="list-info">';
                		html+='<div class="list-info-img">';
    	     		 		  html+='<img src="http://116.62.124.171'+res.data[i].img+'" alt=""/>';
    	     		 		  html+='</div>';
    	     		 		  html+='<div class="list-info-title">';
    	     		 		  if(res.data[i].fullName.length>24){
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
							  /*'<span class="list-price">RMB<label>'+ res.data[i].price+'</label></span>'+*/
							  '<span class="list-point-integral"><img class="list-point-img" src="img/icon-pointChange.png" alt=""/>'+
							  '<label class="point-integral">'+ res.data[i].pointPrice+'</label></span></p>'+
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

function goToNearShop(){
	window.location.href = "nearShop.jsp";
}
	
function tabChange(id){
	window.location.href = "goodsDetails.jsp?goodsId="+id;
}
	