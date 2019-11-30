$(function () {
    init();
    /*var input = document.getElementById("demo_input");
    var result= document.getElementById("result");
    if ( typeof(FileReader) === 'undefined' ){
        var msg = "抱歉，你的浏览器不支持 FileReader，请使用现代浏览器操作！";
         appSupport.cm.errorMessageShow(errorMsg, msg);
         setTimeout(function() {
             appSupport.cm.errorMessageHide(errorMsg);
         }, 1000);
         return false;
        input.setAttribute('disabled','disabled');
    }else{
        input.addEventListener('change',readFile,false);
    } */
})

function readFile(){
    var file = this.files[0];
    //判断是否为图片
    if(!/image\/\w+/.test(file.type)){
        var msg = "请确保文件为图像类型！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
            $(".img_area_img").attr('src',this.result);
            uploadImg();
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

function init() {
    var url = '../ajeasy/goods/catList';
    var html = '';
    $.ajax({
        url: url,
        type: 'post',
        success: function (res) {
            if (res.code == 1) {
                if (res.data.length > 0) {
                    for (var i = 0; i < res.data.length; i++) {
                        html += '<option value="' + res.data[i].id + '">' + res.data[i].name + '</option>';
                    }
                    $(".allianceDiv-input").html(html)
                }
            }
        }
    })
}

function validateForm() {
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

    var firstImg = $(".firstImg").val();
    if (firstImg == '') {
        var msg = "请上传图片！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function () {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

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



    var prodType = $("#prodType").val();
    var intro = $("#intro").val();
    var ismark = $("#ismark").val();
    $.ajax({
        //url:'../ajeasy/goods/addGoods',
        url: '../zlead/enterpriseBg/enterpriseGoodsSave',
        type: "post",
        dataType: "json",
        data: $("#addGoods").serialize(),
        success: function (res) {
            if (res.code == 1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.location.href = 'goodsQuery.jsp';
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

