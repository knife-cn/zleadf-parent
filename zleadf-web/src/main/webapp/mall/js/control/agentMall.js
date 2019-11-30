$(function() {
	bannerShow();
	hotPrudict();
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
	
//热卖推荐
var page = 0;
var size = 6;
function hotPrudict(){
	var html='';
    var shopId = appSupport.cm.queryString("shopId");
	$('.list').dropload({
    scrollArea : window,
    loadDownFn : function(me){
    	page++;
        // 拼接HTML
        var html = '';
        $.ajax({
            type: 'post',
            url: '../zlead/tgoods/queryAgentgoodsList?page='+page+'&size='+size,
            data: {
            },
            success: function(res){
            	if(res.data.length>0){
            		for(var i=0;i<res.data.length;i++){
    					html+='<li id="'+res.data[i].id+'" onclick="tabChange('+res.data[i].id+','+i+')">';
    					html+='<div class="list-info">';
                		html+='<div class="list-info-img">';
    	     		 		  html+='<img src="http://116.62.124.171/group1/'+res.data[i].firstImg+'" alt=""/>';
    	     		 		  html+='</div>';
    	     		 		  html+='<div class="list-info-title">'+
               		 		  '<p>'+res.data[i].fullName+'</p>'+
               		 		  '</div>'+
               		 		  '<div class="list-info-peole">'+
               		 		  '<img src="img/img-quan.png" alt=""/>'+
               		 		  '<span style="margin-left:0.12rem;">已有<label>'+res.data[i].salesNum+'</label>人购买</span>'+
							  '</div>'+
							  '<div class="list-info-peole">'+
               		 		  '<img src="img/img-quan.png" alt=""/>'+
               		 		  '<span style="margin-left:0.12rem;">商城价<label style="color:#ff8a22;margin-left:0.12rem;">'+res.data[i].marketPrice+'</label></span>'+
							  '</div>'+
							  '<div class="list-info-peole">'+
               		 		  '<img src="img/img-quan.png" alt=""/>'+
               		 		  '<span style="margin-left:0.12rem;">代理价</span>'+
               		 		  '<span style="float:right">RMB<label style="font-size:0.41rem;color:#fc0741;margin-left:0.12rem;">'+res.data[i].price+'</label></span>'+
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
                console.log('加载错误');
                // 即使加载出错，也得重置
                me.resetload();
            }
        })
    	}
    })
}
	
function tabChange(id){
	window.location.href = "agentMallDetails.jsp?goodsId="+id;
}

//跳转到指定的页面
function goToBannerDetail(contentPath){
    window.location.href = contentPath;
}