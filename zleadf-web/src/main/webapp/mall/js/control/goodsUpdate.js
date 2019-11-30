$(function () {
    init();
})
/**
 * 初始化页面得到商品信息
 */
var init = function () {
    var idStr = getQueryString("id");//从页面href字符串中得到id值
    var id = '';
    if (idStr != undefined && idStr != '') {//对得到的id值进行非空判断
        id = eval('(' + idStr + ')');
    }

    $.ajax({
        type: 'post',
        url: '../zlead/tgoodsBg/info',
        data: {"id": id},
        success: function (res) {
            if (res.code == 1) {
                $("#fullName").val(res.data.fullName);
                $("#marketPrice").val(res.data.marketPrice);
                $("#supplyPrice").val(res.data.supplyPrice);
                $("#stock").val(res.data.stock);
                $("#salesNum").val(res.data.salesNum);
                $("#ismark").val(res.data.ismark);
                $("#intro").val(res.data.intro);
                $("#id").val(res.data.id);
                $("#price").val(res.data.price);
                $("#imgs").val(res.data.price);
                $("#prodType").find("option[value="+res.data.prodType+"]").attr("selected",true);
                $("#isMarket").find("input[value="+res.data.isMarket+"]").attr("checked",true);
                $("#channel").find("option[value="+res.data.channel+"]").attr("selected",true);
                $("#isHome").find("input[value="+res.data.isHome+"]").attr("checked",true);
                $("#spec").val(res.data.spec);
                $("#catagoryId").val(res.data.catagoryId);
                $("#agentPrice").val(res.data.agentPrice)
                $("#pointPrice").val(res.data.pointPrice);
                $("#point").val(res.data.point);
                $("#podiscountint").val(res.data.discount);
                $("#clickNum").val(res.data.clickNum);
                $("#solrName").val(res.data.solrName);
                $("#remark").val(res.data.remark);
                $(".img_area_img").attr('src', 'http://116.62.124.171/group1/' + res.data.firstImg);
            }
        }
    })
}

/**
 * 点击提交时商品信息修改
 * @returns {boolean}
 */
var validateForm = function () {
    var fullName = $("#fullName").val();
    if (fullName == '') {
        var msg = "请输入商品名称！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var catagoryId = $("#catagoryId").val();
    if(catagoryId == ''){
        var msg = "请输入分类ID";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
    }

    var channel = $("#channel").val();
    if (channel == '') {
        var msg = "请输入商品发布区域！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var price = $("#price").val();
    if (price == '') {
        var msg = "请输入价格！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var marketPrice = $("#marketPrice").val();
    if (marketPrice == '') {
        var msg = "请输入市场价格！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var agentPrice = $("#agentPrice").val();
    if (agentPrice == '') {
        var msg = "请输入商品会员价！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var supplyPrice = $("#supplyPrice").val();
    if (supplyPrice == '') {
        var msg = "请输入采购价格！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var stock = $("#stock").val();
    if (stock == '') {
        var msg = "请输入库存！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var salesNum = $("#salesNum").val();
    if (salesNum == '') {
        var msg = "请输入销量！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var clickNum = $("#clickNum").val();
    if (clickNum == '') {
        var msg = "请输入商品被收藏数量！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

   /* var firstImg = $(".img_area_img").val();
    if (firstImg == '') {
        var msg = "请上传图片！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
*/
    var isHome = $("input[name='isHome']:checked").val();
    if (isHome == "" || isHome == null || isHome == undefined) {
        var msg = "请选择是否显示首页！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var isMarket = $('input[name="isMarket"]:checked').val();
    if (isMarket == "" || isMarket == null || isMarket == undefined) {
        var msg = "请选择是否上架！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var intro = $("#intro").val();
    var ismark = $("#ismark").val();
    $.ajax({
        url: '../zlead/enterpriseBg/enterpriseGoodUpdate',
        type: "post",
        dataType: "json",
        data:$('#updateGoods').serialize(),
        success: function (res) {
            if (res.code == 1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.history.back();
                }, 1500);
            } else {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
                $("#validate").attr("onclick", "validateForm()");
            }
        }
    });
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
                $(".imgs").val(res.data);
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

