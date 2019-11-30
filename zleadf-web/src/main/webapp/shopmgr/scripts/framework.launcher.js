// JavaScript Document

$(window).load(function() { // makes sure the whole site is loaded
	$("#status").fadeOut(); // will first fade out the loading animation
	$("#preloader").delay(350).fadeOut("slow"); // will fade out the white DIV that covers the website.
})

$(document).ready(function(){
	//Detect if iOS WebApp Engaged and permit navigation without deploying Safari
	(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")
    // logoImg();
    var username = $("#username").val();
    var password = $("#password").val();
    if (username){
        alert("您的的账号为"+username+",密码为"+username);
    }
    var memberId = $("#memberId").val();
    var memberType = $("#memberType").val();
    if (memberId){
        localStorage.setItem("memberId",memberId);
    }
    if (memberType!=null){
        localStorage.setItem("memberType",memberType);
    }else {
        memberType = 0;
        localStorage.setItem("memberType",memberType);
    }
    $('#close_im').bind('click',function(){
        $('#main-im').css("height","0");
        $('#im_main').hide();
        $('#open_im').show();
    });
    $('#open_im').bind('click',function(e){
        $('#main-im').css("height","272");
        $('#im_main').show();
        $(this).hide();
    });
    $('.go-top').bind('click',function(){
        $(window).scrollTop(0);
    });
    $(".weixing-container").bind('mouseenter',function(){
        $('.weixing-show').show();
    })
    $(".weixing-container").bind('mouseleave',function(){
        $('.weixing-show').hide();
    });

    $('#close_im').bind('click',function(){
        $('#main-im').css("height","0");
        $('#im_main').hide();
        $('#open_im').show();
    });
    $('#open_im').bind('click',function(e){
        $('#main-im').css("height","272");
        $('#im_main').show();
        $(this).hide();
    });
    $('.go-top').bind('click',function(){
        $(window).scrollTop(0);
    });
    $(".weixing-container").bind('mouseenter',function(){
        $('.weixing-show').show();
    })
    $(".weixing-container").bind('mouseleave',function(){
        $('.weixing-show').hide();
    });
    qq();
    bannerShow();
    newList();
    useInfo();
	var owl = $(".slider-controls");
	// Custom Navigation Events
	// $(".next-slider").click(function(){
	//   owl.trigger('owl.next');
	//   return false;
	// });
	// $(".prev-slider").click(function(){
	//   owl.trigger('owl.prev');
	//   return false;
	// });
    var owlQuoteControls = $(".quote-slider");

    var owlQuoteNoControls = $(".quote-slider-no-controls");
    owlQuoteNoControls.owlCarousel({
        //Basic Stuff
        singleItem:true,
        slideSpeed : 250,
        paginationSpeed : 250,
        rewindSpeed : 250,
        pagination:false,
        autoPlay : true,
        autoHeight: true,
    });

    // Custom Navigation Events
    $(".next-quote").click(function(){
        owlQuoteControls.trigger('owl.next');
        return false;
    });
    $(".prev-quote").click(function(){
        owlQuoteControls.trigger('owl.prev');
        return false;
    });



    $('.slider-two-thumbs').owlCarousel({
        singleItem:true,
    });

    $(".slider-no-controls").owlCarousel({
        //Basic Stuff
        singleItem:true,
        slideSpeed : 250,
        paginationSpeed : 250,
        rewindSpeed : 250,
        pagination:false,
        autoHeight:true,

        //Autoplay
        autoPlay : true,
        stopOnHover : true,

        //Mouse Events
        dragBeforeAnimFinish : true,
        mouseDrag : true,
        touchDrag : true,

        //Transitions
        transitionStyle : false,
    });
    var shopId=appSupport.cm.queryString("shopId");
    $(".home-nav").attr("href","index.action?shopId="+shopId);
    $(".features-nav").attr("href","agentMall.action?shopId="+shopId);
    $(".media-nav").attr("href","newsList.action?shopId="+shopId);
    $(".contact-nav").attr("href","aboutUs.action?shopId="+shopId);
    $(".my-nav").attr("href","agentAccount.action?shopId="+shopId);
    hotPrudict();

});

function hotPrudict() {
    var shopId=appSupport.cm.queryString("shopId");
    $.ajax({
        url: '../zlead/enterprise/homeGoodsList',
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

function logoImg() {
    var shopId=appSupport.cm.queryString("shopId");
    $.ajax({
        url: '../zlead/enterprise/shopInfo',
        type: 'post',
        data: {
            "shopId":shopId
        },
        success: function(res) {
            if (res.data && res.data.length > 0) {
                var logoImg =  res.data.map.shopLogo;
                $(".sidebar-logo").css("background-image","http://116.62.124.171/group1/"+logoImg);
            }

        }
    })
}
function gotoDetails(){
    var goodsId=$(".recommendedId").val();
    window.location.href = "agentMallDetails.jsp?goodsId=" + goodsId;
}
function useInfo() {
    var shopId=appSupport.cm.queryString("shopId");
    $.ajax({
        url: '../zlead/enterprise/enterpriseArticleInfo',
        type: 'post',
        data: {
            "shopId":shopId,
            "categoryid":2
        },
        success: function(res) {
            if (res.data && res.data.length > 0) {
                var _banner = '';
                for (var i=0; i<res.data.length; i++) {
                    _banner += '<div><div class="services-item"><a href="aboutUsDetails.jsp?id='+res.data[i].id+'"><img src="http://116.62.124.171/group1/'+ res.data[i].thumbnail+'" alt="img"></a><h4>'+res.data[i].title+'</h4><strong>'+res.data[i].content.substring(0,30)+'</strong></div></div>'
                }
            // <div>
            //     <div class="services-item">
            //         <img src="images/general-nature/3s.jpg" alt="img">
            //         <h4>Mobile Web</h4>
            //     <em>for your device</em>
            //     <strong>We love quotes, and sometimes it's annoying to see tons of them that you need to scroll to!</strong>
            //     <a href="#" class="button button-blue center-button">Mail</a>
            //         </div>
            //         </div>
                $('.quote-slider').html(_banner);
                $('.quote-slider').owlCarousel({
                    //Basic Stuff
                    items : 4,
                    itemsDesktop : [1199,4],
                    itemsDesktopSmall : [980,3],
                    itemsTablet: [768,3],
                    itemsTabletSmall: [330,2],
                    itemsMobile : [320,2],
                    singleItem : false,
                    itemsScaleUp : false,
                    slideSpeed : 250,
                    paginationSpeed : 250,
                    rewindSpeed : 250,
                    pagination:false,
                    autoPlay : false,
                    autoHeight: true,
                });
            }

        }
    })
}

function bannerShow() {
    var shopId=appSupport.cm.queryString("shopId");
    localStorage.setItem("shopId",shopId);
    $.ajax({
        url: '../zlead/enterprise/enterpriseAdsInfo',
        type: 'post',
        data: {
            "shopId":shopId,
            "userId": localStorage.getItem("userId")
        },
        success: function(res) {
            if (res.data && res.data.length > 0) {
                var _banner = '';
                for (var i=0; i<res.data.length; i++) {
                    _banner += '<div><img src="http://116.62.124.171/group1/'+ res.data[i].adsImg +'" class="responsive-image" alt="img"></div>';
                }
                $('.slider-controls').html(_banner);
                $('.slider-controls').owlCarousel({
                    //Basic Stuff
                    singleItem:true,
                    slideSpeed : 250,
                    paginationSpeed : 250,
                    rewindSpeed : 250,
                    pagination:false,
                    autoPlay : true
                });
            }
        }
    })
}

function newList(){
    var shopId=localStorage.getItem("shopId");
    $.ajax({
        url: '../zlead/enterprise/enterpriseArticleInfo',
        type: 'post',
        data:{
            "categoryid":3,
            "shopId":shopId
        },
        success: function(res) {
            if (res.data && res.data.length > 0) {
                var title = res.data[0].title.substring(0,20);
                var content = res.data[0].content;
                $(".abtitle").html(title);
                $(".abcontent").html(content);
                $(".no-bottom").on("click", function() {
                    window.location.href ="aboutUsDetails.jsp?id="+res.data[0].id;
                })
            }
        }
    })
}

function qq() {
    var shopId=appSupport.cm.queryString("shopId");
    $.ajax({
        url: '../zlead/enterprise/shopInfo',
        type: 'post',
        data: {
            "shopId":shopId
        },
        success: function (res) {
            if (res.success) {
                $(".hqq").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+res.data.shop.contactQQ+"&site=qq&menu=yes")

            }
        }
    })
}

