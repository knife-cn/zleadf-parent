var voucherId;

$(function() {
    $(".towdao li").click(function() {
        $("li").removeClass("yan");
        $(this).addClass("yan");

    });

    //获取url中的值
    var getParam = function(name) {
        var search = document.location.search;
        var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
        var matcher = pattern.exec(search);
        var items = null;
        if (null != matcher) {
            try {
                items = decodeURIComponent(decodeURIComponent(matcher[1]));
            } catch (e) {
                try {
                    items = decodeURIComponent(matcher[1]);
                } catch (e) {
                    items = matcher[1];
                }
            }
        }
        return items;
    };

    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/order/getOrderInfodetails", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        data: {
            orderId: getParam("id"),
        },
        success: function(res) {
            if (res.code == 1) {
                var data = res.data.orderEntity;
                var html = template("detailTel", data);
                $(".left").html(html);
                $(".sn").html(data.sn);

                var product = res.data.orderGoods;
                var datas = {
                    list: product
                };
                var html2 = template("telPro", datas);
                $(".nei").html(html2);

                var number = data.goodsAmount;
                $(".all").html(number.toFixed(2));
                var cost = data.shippingCost;
                $(".cost").html(cost.toFixed(2));
                var payableAmount = data.payableAmount;
                $(".payableAmount").html(payableAmount.toFixed(2));
                var cha = payableAmount - number;
                $(".cha").html(cha.toFixed(2));
                //提交订单的时间

                var myobj = {
                    mydata: res.data.listOpLogs,
                    leng: res.data.orderEntity.status
                };
                for (var i = 0; i < myobj.mydata.length; i++) {
                    myobj.mydata[i].createDate = getTime(myobj.mydata[i].createDate);
                }

                var ding = template("myding", myobj);
                $('.righttop').html(ding);

                var allTime = res.data.opTime;

                //以下4个状态各自独立
                if(allTime.CREATE){
                    var path = "../../shopping/img/lhpimg/juxin8.png";
                    $(".createTimg").attr("src",path);
                    $(".createT").text(getTime(allTime.CREATE));
                    $(".createTcol").css("color","rgba(79, 83, 98, 1)");
                    $(".createTcol").css("fontWeight","bold");
                }else{
                    var path = "../../shopping/img/lhpimg/juxin6.png";
                    $(".createTimg").attr("src",path);
                    $(".createT").text();
                    $(".createTcol").css("color","rgba(167, 167, 172, 1)");
                    $(".createTcol").css("fontWeight","normal");
                }

                if(allTime.PAY){
                    var path = "../../shopping/img/lhpimg/juxin7.png";
                    $(".payTimg").attr("src",path);
                    $(".payT").text(getTime(allTime.PAY));
                    $(".payTcol").css("color","rgba(79, 83, 98, 1)");
                    $(".payTcol").css("fontWeight","bold");
                }else{
                    var path = "../../shopping/img/lhpimg/juxin4.png";
                    $(".payTimg").attr("src",path);
                    $(".payT").text();
                    $(".payTcol").css("color","rgba(167, 167, 172, 1)");
                    $(".payTcol").css("fontWeight","normal");
                }

                if(allTime.SEND){
                    var path = "../../shopping/img/lhpimg/juxin10.png";
                    $(".goTimg").attr("src",path);
                    $(".goT").text(getTime(allTime.SEND));
                    $(".goTcol").css("color","rgba(79, 83, 98, 1)");
                    $(".goTcol").css("fontWeight","bold");
                }else{
                    var path = "../../shopping/img/lhpimg/juxin3.png";
                    $(".goTimg").attr("src",path);
                    $(".goT").text();
                    $(".goTcol").css("color","rgba(167, 167, 172, 1)");
                    $(".goTcol").css("fontWeight","normal");
                }

                if(allTime.RECEIVE){
                    var path = "../../shopping/img/lhpimg/juxin11.png";
                    $(".yesTimg").attr("src",path);
                    $(".yesT").text(getTime(allTime.RECEIVE));
                    $(".yesTcol").css("color","rgba(79, 83, 98, 1)");
                    $(".yesTcol").css("fontWeight","bold");
                }else{
                    var path = "../../shopping/img/lhpimg/juxin5.png";
                    $(".yesTimg").attr("src",path);
                    $(".yesT").text();
                    $(".yesTcol").css("color","rgba(167, 167, 172, 1)");
                    $(".yesTcol").css("fontWeight","normal");
                }

                voucherId = res.data.orderEntity.voucherId;
                if (voucherId > 0) {
                    $(".watchPic").show();
                } else {
                    $(".watchPic").hide();
                }

            }
        }
    });

    $(".watchPic").click(function() {
        $.ajax({
            type: "post",
            cache: true,
            url: "/zlead/voucher/findById",
            dataType: "json",
            async: true,
            data: {
                voucherId: voucherId,
            },
            success: function(res) {
                if (res.code == 1) {
                    var srcImg = res.data.img;
                    $(".pic").attr("src", srcImg);
                    $(".picBg").show();
                }
            }
        })
    });

    //	点击商品图片和名称跳转详情
    $(document).on("click", ".goDetail", function() {
        var dataId = $(this).attr("data-gId");
        $.ajax({
            type: "get", //提交请求的方式
            cache: true, //是否有缓存
            url: "/query/goodsIsMarket", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            async: true, //是否异步
            data: {
                goodsId: dataId,
                t_: Math.random()
            },
            success: function(res) {
                if (res.code == 1) {
                    window.location = "details.html?id=" + dataId
                } else {
                    $(".msg4").html(res.message);
                    $(".meng4").show();
                    $(".popupMsg4").show();
                }
            }
        })
    });

    $(".picBg").click(function () {
        $(".picBg").hide()
    });

    //弹窗关闭按钮--X
    $(".closeF").click(function() {
        $(".meng4").hide();
        $(".popupMsg4").hide();
    });

});

function getTime(time) {
    if (time == null || time == undefined || time == "") {
        str = ""
    } else {
        var t = new Date(time);
        var str = '';
        var y = t.getFullYear();
        var month = t.getMonth() + 1;
        var d = t.getDate();
        var h = t.getHours() < 10 ? '0' + t.getHours() : t.getHours();
        var m = t.getMinutes() < 10 ? '0' + t.getMinutes() : t.getMinutes();
        var s = t.getSeconds() < 10 ? '0' + t.getSeconds() : t.getSeconds();

        str += y + '/' + month + '/' + d + ' ' + h + ':' + m + ":" + s;
    }
    return str;
}

// 面包屑导航跳转首页 
$('.wz_conimg').css('cursor', 'pointer');
$('.wz_conimg').click(function() {
    location.href = 'index.html'
})