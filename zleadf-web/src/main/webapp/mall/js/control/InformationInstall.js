$(function () {
    init();
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
    var idStr = getQueryString("sid");//从页面href字符串中得到id值
    var shopId = '';
    if (idStr != undefined && idStr != '') {//对得到的id值进行非空判断
        shopId = eval('(' + idStr + ')');
    }
    // alert(idStr);
    // $.post("../zlead/template/templateList",{},function (res) {
    //     console.log(res);
    //     for (var i=0;i<res.data.length;i++){
    //         alert("111");
    //         var op = $("<option value='0' style='font-size: 10px;'>"+res.data.tempName+"</option>")
    //         $("#adstype").append(op)
    //     }
        // for (var i=0;i<res.data.length;i++){
        //
        //     $(".image-box clear").after("<section class='image-section'> <div class='image-shade'></div>" +
        //         "<div class='image-delete'></div><div class='image-zoom'></div><img src='http://116.62.124.171/group1/"+res.data[i].adsImg+"'>   </section>");
        // }
    // })

    $.ajax({
        type: 'post',
        url: '../zlead/tshopBg/info',
        data: {"shopId": shopId},
        success: function (res) {
            $("#contactName").val(res.data.shopName);
            $("#contactPhone").val(res.data.contactPhone);
            $("#companyName").val(res.data.companyName);
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
            $("#bannerImg").val(res.data.bannerImg);
            $("#template").val(res.data.template);
            // alert(idStr);
            $("#id").val(idStr);
            $(".img_area_img").attr('src','http://116.62.124.171/group1/'+res.data.shopLogo);
            var bannerImg = res.data.bannerImg.split('_');
            var num = bannerImg.length;
            // alert(num);
            var imgbox = $("#imgbox");
            for (var i = 0 ; i<num-1 ;i++){
                var inputName = "demo_input";

                var imageSection = $("<section class='image-section image-loading'></section>");
                var imageShade   = $("<div class='image-shade'></div>");
                var imageShow    = $("<img  class='image-show' src='"+"http://116.62.124.171/group1/"+bannerImg[i]+"'/>");
                var imageZoom    = $("<div class='image-zoom'></div>");
                var imageDelete  = $("<div class='image-delete'></div>");
                imgbox.prepend(imageSection);
                imageShade.appendTo(imageSection);
                imageDelete.appendTo(imageSection);
                imageZoom.appendTo(imageSection);
                imageShow.appendTo(imageSection);
            }

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
                var op = $("<option value='0' style='font-size: 10px;'>"+res.data[i].tempName+"</option>")
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