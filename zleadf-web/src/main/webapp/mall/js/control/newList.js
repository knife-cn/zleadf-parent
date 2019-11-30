$(function() {
	bannerShow();
	hotPrudict();
	newList();
})
//新闻页
function newList(){
	var html='';
	$.ajax({
		url: '../article/news',
        type: 'post',
        success: function(res) {
        	 if (res.data && res.data.length > 0) {
        		 for (var i=0; i<res.data.length; i++) {
        		html+='<li>';
 				html+='<div class="newsList-1" onclick="toDetailNews('+res.data[i].id+')">';
 				html+='<img src="http://116.62.124.171'+res.data[i].thumbnail+'" height="100%" alt=""/>';
 				html+='</div>';
 				html+='<div class="newsList-2">';
 				html+='<span>'+res.data[i].title.substring(0,20)+'</span>';
 				html+='</div>';
 				html+='</li>'
        		 }
        		 $("#newsList").html(html);
         }
        }
	})
}
function toDetailNews(id){
	var id=id;
	window.location.href = "newsDetails.jsp?id=" + id;
}
//banner图
function bannerShow() {
	$.ajax({
		url: '../ajeasy/ads/adsList',
        type: 'post',
        data: {
        	adstype:'1'
        },
        success: function(res) {
        	 if (res.data && res.data.length > 0) {
        	        var _banner = '';
        	        for (var i=0; i<res.data.length; i++) {
        	        	//获取跳转的url
        	        	var contentPath = res.data[i].contentPath;
        	        	//获取banner图的跳转属性
        	        	var contentType = res.data[i].contentType;
        	        	_banner += '<li onclick=goToBannerDetail("'+res.data[i].contentPath+'")><img src="http://116.62.124.171'+ res.data[i].adsImg +'" alt=""></li>';
        	        }
        	        $('#slider-inner').html(_banner);
        	        setTimeout(function () {
        	          $('.default-slider').unslider({
        	            autoplay: true
        	          });
        	        }, 10);
        	      }else {
        	        $('#slider-inner').hide();
        	      }
        }
	})
}
	
//热卖推荐
function hotPrudict() {
	var html='';
	var showNum = 2;
	$.ajax({
		url: '../ajeasy/goods/homeGoodsList',
        type: 'get',
        data: {showNum:showNum},
        success: function(res) {
        	if(res.code==1){
        		if(res.data.length>0){
    				for(var i=0;i<res.data.length;i++){
    					html+='<li onclick="toDetails('+res.data[i].id+')">';
    					html+='<div class="recommend-1">';
    	     		 	html+='<img src="http://116.62.124.171'+res.data[i].img+'" alt=""/>';
    	     		    html+='</div>';
    	     		 	html+='<div class="recommend-2">'+
               		 		  '<p class="recommend-2-1">'+res.data[i].fullName+'</p>'+
               		 		  '<p class="recommend-2-2">'+
               		 		  '<img src="img/img-quan.png" alt="">'+
               		 		  '<span>已有<label>'+res.data[i].sales+'</label>人购买</span>'+
               		 		  '</p>'+
							  '<p class="list-price">RMB<label>'+ res.data[i].price+'</label></p>'+
							  '</div>'+
               		 		  '</li>';  
            		}
    				$("#recommend").html(html);
            	}else{
            	}
        	}else if(res.code==2){
        		return;
        	}
        }
	})
}
	
function toDetails(id){
	var goodsId=id;
	window.location.href = "goodsDetails.jsp?goodsId=" + goodsId;
}

function gotoSeach(){
	window.location.href = "seach.jsp";
}

//跳转到指定的页面
function goToBannerDetail(contentPath){
	window.location.href = contentPath;
}
