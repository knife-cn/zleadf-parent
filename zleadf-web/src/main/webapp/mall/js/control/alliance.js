$(function() {
	bannerShow();
	hotPrudict();
})

//banner图
function bannerShow() {
	$.ajax({
		url: '../ajeasy/ads/adsList',
        type: 'post',
        data: {
        	adstype:'6'
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
	
var page = 0;
function hotPrudict() {
	$('.allianceList').dropload({
	    scrollArea : window,
	    loadDownFn : function(me){
	    	page++;
	        // 拼接HTML
	        var html = '';
			$.ajax({
				url: '../ajeasy/allianceBusiness/goodsList?page='+page,
		        type: 'post',
		        data: {},
		        success: function(res) {
		        	if(res.code==1){
		        		if(res.data.length>0){
			        		for(var i=0; i<res.data.length; i++){
			        			html+='<li>';
			        			html+='<div class="allianceList-title" onclick="gotoShopDetalis('+res.data[i].shopId+')">';
			        			html+='<img src="img/icon-shangjia.png" alt="">';
			        			html+='<span>'+res.data[i].shopName+'</span>';
			        			html+='</div>';
			        			html+='<div class="allianceList-info">';
			        			html+='<div class="allianceList-info-1" onclick="gotoDetalis('+res.data[i].id+')">';
			        			html+='<img src="http://116.62.124.171'+res.data[i].img+'" alt="">';
			        			html+='</div>';
			        			html+='<div class="allianceList-info-2">';
			        			html+='<p class="allianceList-p1">'+res.data[i].fullName+'</p>';
			        			html+='<p class="allianceList-p2">';
			        			html+='<img src="img/img-quan.png" alt="">';
			        			html+='<span>已有<label>'+res.data[i].sales+'</label>人购买</span>';
			        			html+='</p>';
			        			html+='<p class="allianceList-p3">';
			        			html+='<span><label>'+res.data[i].price+'</label>RMB</span>';			
			        			html+='</p>';
			        			/*html+='<div class="allianceList-btn">';
			        			html+='<img class="allianceList-btn1" src="img/btn-jrgwc.png" alt="">';
			        			html+='<img class="allianceList-btn2" src="img/btn-ljgm.png" alt="">';
			        			html+='</div>';*/
			        			html+='</div>';
			        			html+='</div>';		
			        			html+='</li>';			
			        		}
		        		}
		        	}else if(res.code==2){
	                    // 锁定
	                    me.lock();
	                    // 无数据
	                    me.noData();
	                }
	                // 为了测试，延迟1秒加载
	                setTimeout(function(){
	                    // 插入数据到页面，放到最后面
	                    $('.list').append(html);
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

function gotoShopDetalis(shopId){
	window.location.href = "shopDetails.jsp?shopId=" + shopId;
}

function gotoDetalis(id){
	var goodsId=id;
	window.location.href = "goodsDetails.jsp?goodsId=" + goodsId;
}

function gotoSeach(){
	window.location.href = "seach.jsp";
}

function goback(){
	 window.history.back();
}

function gotoShopCar(){
	window.location.href = "shopCar.jsp";
}
//跳转到指定的页面
function goToBannerDetail(contentPath){
	window.location.href = contentPath;
}
