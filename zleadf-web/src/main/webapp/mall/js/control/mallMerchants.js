$(function() {
	bannerShow();
	hotPrudict();
	newGoods();
})

//banner图
function bannerShow() {
	$.ajax({
		url: '../ajeasy/ads/adsList',
        type: 'post',
        data: {
        	adstype:'9'
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
	
function newGoods(){
	var showNum = 4;
	var html = "";
	$.ajax({
		url: '../ajeasy/merchantGoods/recommendGoodsList',
        type: 'get',
        data: {"showNum":showNum},
        success: function(res) {
        	if(res.code==1){
        	
        		if(res.data.length>=1){
        			html+='<div class="dailySpecial-1">';
        			if(res.data[0]!=null){
        				html+='<img onclick="toDetails('+res.data[0].id+','+0+')" class="dailySpecial-1-img" src="http://116.62.124.171'+res.data[0].img+'" alt="">';
        			}else{
        				html+='<img class="dailySpecial-1-img" src="" alt="">';
        			}
        			html+='</div>';
        			html+='<div class="dailySpecial-2">';
        			html+='<div class="dailySpecial-2-1">';
        			if(res.data[1]!=null){
        				html+='<img onclick="toDetails('+res.data[1].id+','+1+')" class="dailySpecial-2-1-img" src="http://116.62.124.171'+res.data[1].img+'" alt="">';
        			}else{
        				html+='<img class="dailySpecial-2-1-img" src="" alt="">';
        			}
        			html+='</div>';
        			html+='<div class="dailySpecial-2-2">';
        			html+='<div class="dailySpecial-2-2-div">';
        			if(res.data[2]!=null){
        				html+='<img onclick="toDetails('+res.data[2].id+','+2+')" class="dailySpecial-2-2-div-img" src="http://116.62.124.171'+res.data[2].img+'" alt="">';
        			}else{
        				html+='<img class="dailySpecial-2-2-div-img" src="" alt="">';
        			}
        			html+='</div>';
        			html+='<div class="dailySpecial-2-2-div" style="margin-left:0.1rem;">';
        			if(res.data[3]!=null){
        				html+='<img onclick="toDetails('+res.data[3].id+','+3+')" class="dailySpecial-2-2-div-img-1" src="http://116.62.124.171'+res.data[3].img+'" alt="">';
        			}else{
        				html+='<img class="dailySpecial-2-2-div-img-1" src="" alt="">';
        			}
        			html+='</div>';
        			html+='</div>';
        			html+='</div>';
        			$(".dailySpecial").html(html);
        		}
        	}
        }
	})
}

//热卖推荐
var page = 0;
function hotPrudict() {
	$('.list').dropload({
	    scrollArea : window,
	    loadDownFn : function(me){
	    	page++;
	        // 拼接HTML
	        var html = '';
			$.ajax({
				url: '../ajeasy/merchantGoods/merchantGoodsList?page='+page,
		        type: 'post',
		        success: function(res) {
	        		if(res.data.length>0){
	    				for(var i=0;i<res.data.length;i++){
	    					html+='<li id="storeType'+res.data[i].id+'" onclick="toDetails('+res.data[i].id+','+i+')">';
	    					html+='<div class="list-info">';
	                		html+='<div class="list-info-img">';
	     		 		    html+='<img src="http://116.62.124.171'+res.data[i].img+'" alt=""/>';
	     		 		    html+='</div>';
	     		 		    html+='<div class="list-info-title">';
	     		 		    if(res.data[i].fullName.length>10){
	     		 			  html+='<p>'+res.data[i].fullName.substring(0, 10)+"..."+'</p>';
	     		 		    }else{
	     		 			  html+='<p>'+res.data[i].fullName+'</p>';
	     		 		    }
           		 		    html+='</div>';
           		 		    html+='<div class="list-info-peole">'+
						    '<img src="img/img-quan.png" alt=""/>'+
						    '<span style="margin-left:0.12rem;">已有<label>'+res.data[i].sales+'</label>人购买</span>'+
						    '</div>'+
						    '<div class="list-info-peole">'+
						    '<img src="img/img-quan.png" alt=""/>'+
						    '<span style="margin-left:0.12rem;">商城价<label style="color:#ff7019;font-size:0.26rem;">'+res.data[i].marketPrice+'</label></span>'+
						    '</div>'+
						    '<div class="list-info-peole">'+
						    '<img src="img/img-quan.png" alt=""/>'+
						    '<span style="margin-left:0.12rem;">代理价<label style="color:#fc0741;font-size:0.26rem;">'+res.data[i].price+'</label></span>'+
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
	                // 即使加载出错，也得重置
	                me.resetload();
	            }
	        })
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
