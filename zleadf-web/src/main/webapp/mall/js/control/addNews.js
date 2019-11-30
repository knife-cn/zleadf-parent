$(function () {
    init();
})
function init(){};
function addNews() {
    if(validateForm() == true) {
            $.ajax({
                type:'post',
                url:'../zlead/enterpriseBg/enterpriseSaveArticle',
                dataType:'json',
                //contentType: "application/json",
                data:$("#addNews").serialize(),
                success: function(res) {
                    console.log(res);
                    if(res.code == 1) {
                        var msg = res.message;
                        appSupport.cm.errorMessageShow(errorMsg,msg);
                        setTimeout(function () {
                            appSupport.cm.errorMessageHide(errorMsg);

                            window.location.href = 'newsList1.jsp';
                        },2000);
                        return false;
                    }else{
                        var msg = res.message;
                        appSupport.cm.errorMessageShow(errorMsg, msg);
                        setTimeout(function () {
                            appSupport.cm.errorMessageHide(errorMsg);
                        }, 2000);
                    }
                }
            })
    }
    else {
        console.log("1234");
    }
}

/**
 * 输入信息非空校验
 * @returns {boolean}
 */
function validateForm() {
    var title = $("#title").val();
    console.log(title)
    var categoryid = $("#categoryid").val();
    var isTop = $('input[name="isTop"]:checked').val();
    var isPublication = $('input[name="isPublication"]:checked').val();
    var publishType = $('input[name="publishType"]:checked').val();
    if (title == '' || title == undefined) {
        var msg = "请输入新闻标题！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    } else if (categoryid == '' || categoryid ==undefined) {
        var msg = '请输入分类编号';
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        } , 1000);
        return false;
    } else if (isTop == "" || isTop == null || isTop == undefined) {
        var msg = "请选择是否首页！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    } else if (isPublication == ''|| isPublication ==undefined) {
         var  msg = "请选择是否发布";
         appSupport.cm.errorMessageShow(errorMsg, msg);
         setTimeout(function () {
             appSupport.cm.errorMessageHide(errorMsg);
         },1000);
         return false;
     } else if (publishType == '' || publishType ==undefined ){
         var msg = "请选择发布类型";
         appSupport.cm.errorMessageShow(errorMsg, msg);
         setTimeout(function () {
             appSupport.cm.errorMessageHide(errorMsg);
         }, 1000);
         return false;
     } else {
        return true;
    }
}

/**
 * 上传图片
 * @returns {boolean}
 */
function uploadImg() {
    $.ajaxFileUpload({
        url: '../ajeasy/fdfs/uploading',
        secureuri: false,
        fileElementId: 'demo_input',//上传控件的id
        dataType: 'json',
        success: function (res) {
            if (res.code == 1) {
                var msg = "上传图片成功！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                    $(".img_area_img").attr('src', 'http://116.62.124.171/group1/' + res.data);
                }, 1000);
                $(".thumbnail").val(res.data);
            } else if (res.code == 2) {
                var msg = "上传失败！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        },
    });
    return false;
}
