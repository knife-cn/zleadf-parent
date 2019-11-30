$(function() {
	bannerShow();
	hotPrudict();
	prudict();
})

//banner图
function bannerShow() {
	$.ajax({
		url: '../zlead/tads/adsList',
        type: 'post',
        data: {
        	adstype:'0'
        },
        success: function(res) {
        	 if (res.data && res.data.length > 0) {
        	        var _banner = '';
        	        for (var i=0; i<res.data.length; i++) {
        	        	//获取跳转的url
        	        	var contentPath = res.data[i].contentPath;
        	        	//获取banner图的跳转属性
        	        	var contentType = res.data[i].contentType;
        	        	_banner += '<li onclick=goToBannerDetail("'+res.data[i].contentPath+'")><img src="http://116.62.124.171/group1/'+ res.data[i].adsImg +'" alt=""></li>';
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
	
//商品推荐
function hotPrudict() {
	var html='<div id="errorMsg"/>';
	var showNum = 6;
    var memberType = localStorage.getItem("memberType");
	$.ajax({
		url: '../zlead/tgoods/homeGoodsList',
        type: 'get',
        data: {"showNum":showNum},
        success: function(res) {
        	if(res.code==1){
        		if(res.data.length>0){
    				for(var i=0;i<res.data.length;i++){
    					html+='<li id="storeType'+res.data[i].id+'" onclick="toDetails('+res.data[i].id+','+i+')">';
    					html+='<div class="list-info">';
                		html+='<div class="list-info-img">';
    	     		 		  html+='<img src="http://116.62.124.171/group1/'+res.data[i].img+'" alt=""/>';
    	     		 		  html+='</div>';
    	     		 		  html+='<div class="list-info-title">';
    	     		 		  if(res.data[i].fullName.length>10){
    	     		 			html+='<p>'+res.data[i].fullName.substring(0, 10)+"..."+'</p>';
    	     		 		  }else{
    	     		 			html+='<p>'+res.data[i].fullName+'</p>';
    	     		 		  }
               		 		  html+='</div>';
               		 		  html+='<div class="list-info-peole">';
                              html+='<img src="img/img-quan.png" alt=""/>';
                              html+='<span style="margin-left:0.12rem;">已有<label>'+res.data[i].sales+'</label>人购买</span>';
                              html+='</div>';
                              html+='<div class="list-info-peole">';
                              html+='<img src="img/img-quan.png" alt=""/>';
							  if(memberType==0){
                                  html+='<span style="margin-left:0.12rem;">售价<label style="color:#ff7019;font-size:0.26rem;">'+res.data[i].price+'</label></span>';
							  }else{
                                  html+='<span style="margin-left:0.12rem;">代理价<label style="color:#ff7019;font-size:0.26rem;">'+res.data[i].agentPrice+'</label></span>';
							  }
							  html+='</div>';
							  if(res.data[i].type==1){
								  html+='<div class="list-info-peole">';
								  html+='<img src="img/img-quan.png" alt=""/>';
								  html+='<span style="margin-left:0.12rem;">代理价<label style="color:#fc0741;font-size:0.26rem;">'+res.data[i].price+'</label></span>';
								  html+='</div>';
							  }
							 /* '<div class="list-info-price">'+
							  '<p>'+
							  '<span class="list-price">RMB<label>'+ res.data[i].price+'</label></span>'+
							  '<span class="list-integral"><img class="list-price-img" src="img/icon-jifen.png" alt=""/>'+
							  '<label class="integral">'+ res.data[i].price+'</label></span></p>'+
							  '</div>'+*/
							  html+='</div>';
							  html+='</li>';  
            		}
    				$(".listUl").html(html);
            	}else{
            	}
        	}else if(res.code==2){
        		return;
        	}
        }
	})
}

function prudict() {
	var html='';
	var showNum = 2;
    var memberType = localStorage.getItem("memberType");
	$.ajax({
		url: '../zlead/tgoods/homeGoodsList',
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

//跳转到企业入驻
function gotoCompanyEnter(){
    window.location.href = "companyEnter.jsp";
}
