
//验证表单
function validateForm() {
    if($("#adstype").val() == null||$("#adstype").val() == ""){
        var msg ="请选择广告分类";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }else if($("#title").val() == null||$("#title").val() == ""){
        var msg ="请输入标题";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }else if($("#introduce").val() == null||$("#introduce").val() == ""){
        var msg ="请输入广告简介";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    else {
        return true;
    }

}

function uploadAdsImg() {
    $.ajaxFileUpload({
        url : '../ajeasy/fdfs/uploading',
        secureuri : false,
        fileElementId : 'demo_input',//上传控件的id
        dataType : 'json',
        success : function(res) {
            if(res.code==1){
                var msg = "上传图片成功！";
                appSupport.cm.errorMessageShow(errorMsg, msg)
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    $(".img_area_img").attr('src','http://116.62.124.171/group1/'+res.data);
                    console.log(res.data)
                    $(".adsImg").val(res.data);
                }, 1000);
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



function addAds() {
    var shopId = appSupport.cm.queryString("shopId");
    $("#shopId").val(shopId);
    if (validateForm()){
        $.ajax({
            url: "../zlead/tadsBg/save",
            type: "post",
            data: $("#adsForm").serialize(),
            dataType: "json",
            success: function(evt) {
                if(evt.code == 1) {
                    var msg=evt.message;
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                        window.location.href = "adsList.jsp";
                    }, 1000);
                }else{
                    var msg=evt.message;
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                    }, 1000);
                }
            }
    }
        )}
}