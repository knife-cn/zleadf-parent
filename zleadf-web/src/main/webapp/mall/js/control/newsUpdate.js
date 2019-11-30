$(function () {
    init();
})
function init(){
    var idStr = getQueryString("id");//从页面href字符串中得到id值
    var id = '';
    if (idStr != undefined && idStr != '') {//对得到的id值进行非空判断
        id = eval('(' + idStr + ')');
    }

    $.ajax({
        type:'post',
        url:'../zlead/tarticleBg/articleInfo',
        data:{"id":id},
        success:function(res){
            $("#id").val(res.data.id);
            $("#seoTitle").val(res.data.seoTitle);
            $("#seoKeywords").val(res.data.seoKeyWords);
            $("#seoDescription").val(res.data.seoDescription);
            $("#title").val(res.data.title);
            $("#categoryid").val(res.data.categoryid);
            $("#content").val(res.data.content);
            $("#hits").val(res.data.hits);
            $("#author").val(res.data.author);
            $(".img_area_img").attr('src', 'http://116.62.124.171/group1/' + res.data.thumbnail);//缩略图赋值
            $("#categoryid").find("option[value='"+res.data.categoryid+"']").attr("selected",true);//下拉框被选中
            $("#isAudit").find("option[value='"+res.data.isAudit+"']").attr("selected",true);//下拉框被选中
            $("#isPublication").find("input[value='"+res.data.isPublication+"']").attr("checked",true);
            $("#isTop").find("input[value='"+res.data.isTop+"']").attr("checked",true);
            $("#publishType").find("input[value='"+res.data.publishType+"']").attr("checked",true);
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

function updateNews(){

    $.ajax({
        type:'post',
        url:'../zlead/enterpriseBg/enterpriseUpdateArticle',
        dataType: "json",
        data:$("#updateNews").serialize(),
        success:function(res){
            if (res.code == 1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.location.href = 'newsList1.jsp';
                }, 1500);
            } else {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        }
    })
}

/**
 * 上传图片
 * @returns {boolean}
 */
function uploadImg() {
    $.ajaxFileUpload({
        url: '../ajeasy/fdfs/',
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
