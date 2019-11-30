$(function () {
    init();
    $(".goBack").on("click", function() {
        window.history.back();
    })
    $("#update").click(function () {
        var shopId=appSupport.cm.queryString("shopId");
        var data = $('#form1').serialize();
        $.ajax({
            type: 'post',
            url: '../zlead/tshop/shopUpdate',
            data: data,
            success: function (res) {
                if (res.code==1) {
                    var msg = "用户信息修改成功！";
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                    }, 500);
                    window.history.back();
                } else {
                    var msg = "用户信息修改失败！";
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                    }, 500);
                }
            }
        })
        var articleImg =$(".articleImg");
        articleImg.each(function () {
            $.ajax({
                type:'post',
                url:'../zlead/enterpriseBg/enterpriseArticle',
                dataType:'json',
                //contentType: "application/json",
                data:{
                    "thumbnail":this.value,
                    "shopId":shopId,
                    "isTop":1,
                    "title":"企业风采",
                    "categoryid":1,
                    "isPublication":1,
                    "publishType":1,
                    "content":"企业风采"
                },
                success: function(res) {
                    console.log(res);
                }
            })
        })
    })
    $("#view").click(function () {
        var shopId=appSupport.cm.queryString("shopId");
        var data = $('#form1').serialize();
        $.ajax({
            type: 'post',
            url: '../zlead/tshop/shopUpdate',
            data: data,
            success: function (res) {
                if (res.code==1) {
                    var msg = "用户信息修改成功！";
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                    }, 500);
                    window.location.href = "../company/businessCard.action?shopId="+shopId;
                } else {
                    var msg = "用户信息修改失败！";
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                    }, 500);
                }
            }
        })
    })
});
function templateSelect() {
    var template = $("#template");
    var op = $("#adstype option:selected");
    template.val(op.text());
}
/**
 * 初始化页面得到商品信息
 */
var init = function () {
    var shopId=appSupport.cm.queryString("shopId");
    var idStr = shopId;
    $.ajax({
        type: 'post',
        url: '../zlead/tshopBg/info',
        data: {"shopId": shopId},
        success: function (res) {
            $("#contactName").val(res.data.contactName);
            $("#contactPhone").val(res.data.contactPhone);
            $("#shopName").val(res.data.shopName);
            var provinceId=res.data.provinceId;//省
            var cityId=res.data.cityId;//市
            var regionId=res.data.regionId;//区and县
            var allregin="";
            $("#companyAddress").val(res.data.companyAddress);
            $("#position").val(res.data.position);
            $("#legalName").val(res.data.legalName);
            $("#contactQQ").val(res.data.contactQQ);
            $("#contactWeixin").val(res.data.contactWeixin);
            $("#businessSn").val(res.data.businessSn);
            $("#contactEmail").val(res.data.contactEmail);
            $("#shopLogo").val(res.data.shopLogo);
            $("#qrCode").val(res.data.qrCode);
            $("#bannerImg").val(res.data.bannerImg);
            $("#template").val(res.data.template);
            // alert(idStr);
            $("#id").val(idStr);
            $(".img_area_img").attr('src','http://116.62.124.171/group1/'+res.data.shopLogo);
            $(".qr_img_area").attr('src','http://116.62.124.171/group1/'+res.data.qrCode);
            $.ajax({
                url: '../zlead/enterprise/enterpriseArticle',
                type: 'post',
                data: {
                    "shopId":shopId,
                    "categoryid":1,
                    "isTop":1
                },
                success: function(res) {
                    if (res.data && res.data.length > 0) {
                        var imgbox = $("#imgbox");
                        var banner = '';
                        for (var i=0; i<res.data.length; i++) {
                            var imageSection = $("<section class='image-section image-loading'></section>");
                            var imageShade   = $("<div class='image-shade'></div>");
                            var imageShow    = $("<img  class='image-show' src='"+"http://116.62.124.171/group1/"+res.data[i].thumbnail+"'/>");
                            var imageZoom    = $("<div class='image-zoom'></div>");
                            var imageDelete  = $("<div class='image-delete'></div>");
                            imgbox.prepend(imageSection);
                            imageShade.appendTo(imageSection);
                            imageDelete.appendTo(imageSection);
                            imageZoom.appendTo(imageSection);
                            imageShow.appendTo(imageSection);
                        }
                    }

                }
            })


$.post("../zlead/tads/QueryAdsList",{"shopid":res.data.id},function (res) {

   for (var i=0;i<res.data.length;i++){

$(".image-box clear").after("<section class='image-section'> <div class='image-shade'></div>" +
    "<div class='image-delete'></div><div class='image-zoom'></div><img src='http://116.62.124.171/group1/"+res.data[i].adsImg+"'>   </section>");
   }
})
            var arr2 = new Array(2);
            arr2[0] = provinceId;
            arr2[1] = cityId;
            arr2[2] = regionId;
            for (var i=0;i<arr2.length;i++){
                $.ajaxSettings.async = false;
                $.post("../zlead/region/lookupRegion",{"region_code":arr2[i]},function (rs) {
                    allregin+=rs.data;
                    $.ajaxSettings.async = true;
                })
            }
            $("#regionall_span").html(allregin.toString());
        }
    })

    $.ajax({
        type: 'post',
        url: '../zlead/template/templateList',
        data: {},
        success: function (res) {
            for (var i=0;i<res.data.length;i++){
                // alert(res.data[i].tempName);
                var tempName= res.data[i].tempName;
                if (tempName === $("#template").val()){
                    var op = $("<option value='0' style='font-size: 10px;' selected='selected'>"+tempName+"</option>")
                }else {
                    var op = $("<option value='0' style='font-size: 10px;'>"+tempName+"</option>")
                }
                $("#adstype").append(op)
            }
        }
    })

}
var getQueryString = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    } else {
        return null;
    }
}
//上传图片
function uploadImg() {
    $.ajaxFileUpload({
        url : '../ajeasy/fdfs/uploading',
        secureuri : false,
        fileElementId : 'demo_input',//上传控件的id
        dataType : 'json',
        success : function(res) {
            if(res.code==1){
                var msg = "上传图片成功！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    $(".img_area_img").attr('src','http://116.62.124.171/group1/'+res.data);
                }, 1000);
				$(".shopLogo").val(res.data);
                $(".backDate").val(res.data);
            }else if(res.code==2){
                var msg = "上传失败！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        },
    });
    return false;
}
function uploadQrcode() {
    $.ajaxFileUpload({
        url : '../ajeasy/fdfs/uploading',
        secureuri : false,
        fileElementId : 'qr_input',//上传控件的id
        dataType : 'json',
        success : function(res) {
            if(res.code==1){
                var msg = "上传图片成功！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    $(".qr_img_area").attr('src','http://116.62.124.171/group1/'+res.data);
                }, 1000);
                $(".qrCode").val(res.data);
                $(".backDate").val(res.data);
            }else if(res.code==2){
                var msg = "上传失败！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        },
    });
    return false;
}
function validateForm(){
    var contactName = $("#addMemebrId").val();
    if(contactName==''){
        var msg = "请输入加盟商编号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var contactName = $("#contactName").val();
    if(contactName==''){
        var msg = "请输入联系人姓名！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var contactPhone = $("#contactPhone").val();
    if(contactPhone==''){
        var msg = "请输入联系人电话！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var companyName = $("#companyName").val();
    if(companyName==''){
        var msg = "请输入公司名称！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var province_name = $("#prive").val();
    var city_name = $("#city").val();
    var county_name = $("#trxo").val();

    if($("#prive").val() == null||$("#prive").val() == "") {
        var msg ="请选择省市区";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var companyAddress = $("#companyAddress").val();
    if(companyAddress==''){
        var msg = "请输入详细地址！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var legalName = $("#legalName").val();
    if(legalName==''){
        var msg = "请输入公司法人名字！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var businessSn = $("#businessSn").val();
    if(businessSn==''){
        var msg = "请输入营业执照编号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    $("#validate").attr("onclick","");
    $.ajax({
        url:$("#form1").attr("action"),
        type:"post",
        dataType:"json",
        data:$("#form1").serialize(),
        success : function(res) {
            if(res.code==1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.location.href = "agentAccount.jsp";
                }, 2000);
            }else{
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 2000);
                $("#validate").attr("onclick","validateForm()");
            }
        }
    });


}
