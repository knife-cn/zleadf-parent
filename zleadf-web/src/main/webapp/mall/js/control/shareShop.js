$(function() {
	bannerShow();
	hotPrudict();
	loadNews();
})
//banner图
function bannerShow() {
	$.ajax({
		url: '../ajeasy/ads/adsList',
        type: 'post',
        data: {
        	adstype:'2'
        },
        success: function(res) {
        	 if (res.data && res.data.length > 0) {
        	        var _banner = '';
        	        for (var i=0; i<res.data.length; i++) {
        	          _banner += '<li><img src="http://116.62.124.171'+ res.data[i].adsImg +'" alt=""></li>';
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
            	pointType:'2'
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
							  /*'<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>'+
							  '<label class="integral">'+ res.data[i].price+'</label>'+
							  '</span>'+*/
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

function loadNews() {
	var html='';
    $.ajax({
        url: '../article/list',
        type: 'post',
        data: {
        	categoryid: "2",
        	startRow:"0",
        	pageSize:"10"
        	},
    	success:function(res) {
    		if(res.code==1){
    			if(res.data.length>0){
    				for(var i=0;i<res.data.length;i++){
    					if(res.data[i].title.length>8){
    						html+='<a href="javascript:article/detail?id='+res.data[i].id+'">'+res.data[i].title.substring(0, 18)+"..."+'</a>';
    					}else{
    						html+='<a href="javascript:article/detail?id='+res.data[i].id+'">'+res.data[i].title+'</a>';
    					}
    				}
    				$("#new").html(html);
    			}
    		}
    	}
    })
}
/*
//	跳转分类页面
	$(".productlist ul li").on("click", function() {
		var p_type = $(this).index() + 1;
		window.location.href = "class.html?p_type=" + p_type;
	});
	$("#hotMore").on("click", function() {
		var p_type = "0";
		window.location.href = "class.html?p_type=" + p_type;
	});
	//跳转搜索页面
	$(".search").on("click", function() {
		window.location.href = "search.html";
	})*/
	
function goToNearShop(){
	window.location.href = "nearShop.jsp";
}
	
function tabChange(id){
	window.location.href = "goodsDetails.jsp?goodsId="+id;
}
	