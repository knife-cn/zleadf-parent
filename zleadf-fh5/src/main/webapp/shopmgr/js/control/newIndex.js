$(function() {
	bannerShow();
	hotPrudict();
	/*prudict();*/
    useInfo()
})

function bannerShow() {
    var shopId=appSupport.cm.queryString("shopId");
    localStorage.setItem("shopId",shopId);
    $.ajax({
        url: '../../zlead/enterprise/enterpriseAdsInfo',
        type: 'post',
        data: {
            "shopId":shopId,
            "userId": localStorage.getItem("userId")
        },
        success: function(res) {
            if (res.data && res.data.length > 0) {
                var _banner = '';
                for (var i=0; i<res.data.length; i++) {
                    _banner += '<div class="swiper-slide"><img src="http://116.62.124.171/group1/'+ res.data[i].adsImg +'" alt=""></div>';
                }
                $('.banner .swiper-wrapper').html(_banner);
            }
            var mySwiper2 = new Swiper('#swiper-container2',{
                loop : false,
                autoplay: 2000,
                touchMoveStopPropagation : false,
                autoplayDisableOnInteraction: false
            });
        }
    })
}

//商品推荐
function hotPrudict() {
	var shopId=appSupport.cm.queryString("shopId");
	$.ajax({
		url: '../../zlead/enterprise/homeGoodsList',
        type: 'get',
        data: {
			"showNum":1,
			"shopId":shopId
		},
        success: function(res) {
        	if(res.code==1){
        		for(var i=0;i<res.data.length;i++){
                    $(".recommended-1").html('<img src="http://116.62.124.171/group1/'+ res.data[0].firstImg +'">');
                    $(".recommendedId").val(res.data[0].id);
				}
        	}
        }
	})
}

function prudict() {
	var html='';
	var showNum = 2;
    var memberType = localStorage.getItem("memberType");
	$.ajax({
		url: '../../zlead/tgoods/homeGoodsList',
        type: 'get',
        data: {"showNum":showNum},
        success: function(res) {
        	if(res.code==1){
        		if(res.data.length>0){
    				for(var i=0;i<res.data.length;i++){
    					html+='<li onclick="toDetails('+res.data[i].id+','+i+')">';
    					html+='<div class="recommend-1">';
    					html+='<img src="http://116.62.124.171/group1/'+res.data[i].img+'" alt="">';
    					html+='</div>';
    					html+='<div class="recommend-2">';
    					if(res.data[i].fullName.length>26){
    						html+='<p class="recommend-2-1">'+res.data[i].fullName.substring(0, 26)+"..."+'</p>';
    					}else{
    						html+='<p class="recommend-2-1">'+res.data[i].fullName+'</p>';
    					}
    					html+='<p class="recommend-2-2">';
    					html+='<img src="img/img-quan.png" alt="">';
    					html+='<span>已有<label>'+res.data[i].sales+'</label>人购买</span>';
    					html+='</p>';
    					if(memberType==0){
                            html+='<p class="list-price">RMB<label>'+res.data[i].price+'</label></p>';
                        }else{
                            html+='<p class="list-price">RMB<label>'+res.data[i].agentPrice+'</label></p>';
						}
    					html+='</div>';
    					html+='</li>';
            		}
    				$("#recommendList").html(html);
            	}else{
            	}
        	}else if(res.code==2){
        		return;
        	}
        }
	})
}

function useInfo() {
    var shopId=appSupport.cm.queryString("shopId");
    $.ajax({
        url: '../../zlead/enterprise/enterpriseArticleInfo',
        type: 'post',
        data: {
            "shopId":shopId,
            "categoryid":2
        },
        success: function(res) {
            if (res.data && res.data.length > 0) {
                var _banner = '';
                for (var i=0; i<res.data.length; i++) {
                    _banner += '<div class="swiper-slide"><img src="http://116.62.124.171/group1/'+ res.data[i].thumbnail +'" alt=""></div>';
                }
                $('.useInfo .swiper-wrapper').html(_banner);
            }
            var mySwiper3 = new Swiper('#swiper-container3',{
                effect : 'coverflow',
                slidesPerView: 2,
                initialSlide:1,
                centeredSlides: true,
                coverflowEffect: {
                    rotate: 0,
                    stretch: 0,
                    depth: 100,
                    modifier: 4,
                    slideShadows : true
                }
            })
        }
    })
}

function gotoDetails(){
    var goodsId=$(".recommendedId").val();
    window.location.href = "agentMallDetails.jsp?goodsId=" + goodsId;
}

function toDetails(id){
	var goodsId=id;
	window.location.href = "agentMallDetails.jsp?goodsId=" + goodsId;
}

function gotoSeach(){
	window.location.href = "seach.jsp";
}

//跳转到指定的页面
function goToBannerDetail(contentPath){
	window.location.href = contentPath;
}
