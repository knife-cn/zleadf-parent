$(function() {
    init();
    var shopId=appSupport.cm.queryString("shopId");
    var protocol = window.location.protocol;
    var host = window.location.host;
    //获取路径
    var pathName=window.document.location.pathname;
    //截取，得到项目名称
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var codeLink1 = protocol+'//'+host+projectName+'/company/index.action?shopId='+shopId;
    var codeLink2 = protocol+'//'+host+projectName+'/company/businessCard.action?shopId='+shopId;
    new QRCode(document.getElementById("qrcode1"), {
        text:codeLink1
    });
      new QRCode(document.getElementById("qrcode2"), {
        text:codeLink2
    });

    hotPrudict();
    useInfo();
});
function init(){
    var shopId=appSupport.cm.queryString("shopId");
    $.ajax({
        url: '../zlead/enterprise/shopInfo',
        type: 'post',
        data: {
            "shopId":shopId
        },
        success: function(res) {
            if(res.code==1){
                $("#id").val(res.data.shop.id);
                $("#logo").attr("arc","http://116.62.124.171/"+res.data.shop.shopLogo);
                $("#mien").attr("arc","http://116.62.124.171/"+res.data.shop.mien);
                $("#companyName").html(res.data.shop.companyName);
                $("#province").html(res.data.provinceName+res.data.cityName+res.data.countyName);
                $("#fc").attr("arc","http://116.62.124.171/"+res.data.shop.mien);
                $("#companyAddress").html(res.data.shop.companyAddress);
                $("#businessSn").html(res.data.shop.businessSn);
                $("#contactName").html(res.data.shop.contactName);
                $("#contactPhone").html(res.data.shop.contactPhone);
                $("#contactWeixin").html(res.data.shop.contactWeixin);
                $("#legalName").html(res.data.shop.legalName);
                $("#position").html(res.data.shop.position);
                $("#contactQQ").html(res.data.shop.contactQQ);
                $("#contactEmail").html(res.data.shop.contactEmail);
                $("#introduce").html(res.data.shop.introduce);
                $("#service").html(res.data.shop.service);
                var bannerImg = res.data.bannerImg.split('_');
                var num = bannerImg.length;
                for (var i = 0 ; i<num-1 ;i++){
                    var banner = '<img src="http://116.62.124.171/group1/'+ res.data.shop.bannerImg[i] +'" alt=""/>';
                }

                $("#banner").html(banner);
                $(".go-back-1").on("click", function() {
                    window.history.back();
                })
            }else if(res.code==2){
                window.location="index.action?shopId=10"
            }
        }
    })
}
function hotPrudict() {
    var shopId=appSupport.cm.queryString("shopId");
    var html='';
    var showNum = 2;
    $.ajax({
        url: '../zlead/enterprise/homeGoodsList',
        type: 'get',
        data: {"showNum":showNum,
                "shopId": shopId
        },
        success: function(res) {
            if (res.code == 1) {
                if (res.data.length > 0) {
                    if (res.data.length > 0) {
                        for (var i = 0; i < res.data.length; i++) {
                            html += ' <div class="goodsBox">';
                            html += '<div class="goodsImg">';
                            html += '<img src="http://116.62.124.171/group1/' + res.data[i].firstImg + '"  alt=""/>';
                            html += '</div>';
                            html += '<div class="goodsDetailTitle">';
                            html += '<ul>';
                            html += '<li><span class="color">商品名:</span></li>';
                            html += '<li><span class="color">市场价:</span></li>';
                            html += '<li><span class="color">会员价:</span></li>';
                            html += '</ul>'
                            html += '</div>'
                            html += '<div class="goodsDetail">'
                            html += '<p>' + res.data[i].fullName + '</p>'
                            html += '<p>' + res.data[i].marketPrice + '</p>'
                            html += '<p>' + res.data[i].price + '<span onclick="gotoDetail()">订购</span></p>'
                            html += '</div>';
                            html += '</div>';
                            html += '</div>';
                        }
                        $("#goods").html(html);
                    } else {
                    }
                } else if (res.code == 2) {
                    return;
                }
            }
        }})
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
                var banner = '';
                for (var i=0; i<3 && i<res.data.length; i++) {
                    banner += '<img src="http://116.62.124.171/group1/'+ res.data[i].thumbnail +'" alt=""/>';
                }
                $('#banner').html(banner);
            }

        }
    })
}

function gotoDetail(){
    var shopId=appSupport.cm.queryString("shopId");
    window.location.href = "agentMall.action?shopId="+shopId;
}
function gotoFx(){
    var shopId=appSupport.cm.queryString("shopId");
    window.location.href = "share.jsp?shopId="+shopId;
}
function gotoIndex(){
    var shopId=appSupport.cm.queryString("shopId");
    window.location.href = "index.action?shopId="+shopId;
}
function gotoRegister(){
    var shopId=appSupport.cm.queryString("shopId");
    window.location.href = "/company/zlead/InformationInstall.jsp?shopId="+shopId;
}



