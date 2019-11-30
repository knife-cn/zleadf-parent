var status = 0;
var maxNum;
// 限制订单字数为40字，多余省略号。如果需要修改字数，可在这里改。
var countNum = 40;
// var s = '飞拓 飞拓系列 FT-001 小麦秸秆牙刷情侣超细竹炭抑菌小头软毛成人 黄';
$(function() {
    window.localStorage.removeItem("loadImg2");
    //跳转页数
    $(".goNum").val("");
    // 设置全局参数
    var pageNum = 1;
    var active5 = 0;
    window.localStorage.setItem("active5", active5);
    if (window.localStorage.getItem("active5") != undefined) {
        active5 = window.localStorage.getItem("active5");
        $('.navt').children('ul').children('li').eq(active5).addClass("active").siblings().removeClass("active");
        switch (active5) {
            case "0":
                findPageZero(pageNum);
                break;
            case "1":
                findPageZero(pageNum, active5 - 1);
                break;
            case "2":
                findPageZero(pageNum, active5 - 1);
                break;
            case "3":
                findPageZero(pageNum, active5 - 1);
                break;
            case "4":
                findPageZero(pageNum, active5 - 1);
                break;
        }
    } else {
        // 不传递-全部订单，0-待付款，1-待发货，2-待收货,3已完成
        // 参数：1,0;默认显示全部订单第一页（0）
        findPageZero(pageNum);
    }

    //弹窗关闭按钮--X
    $(".closeF").click(function() {
        $(".meng4").hide();
        $(".popupMsg4").hide();
    });

    //顶部退出
    $('#yh').click(function() { //点击a标签
        if ($('.yxi').is(':hidden')) { //如果当前隐藏
            $('.yxi').show(); //那么就显示div
            $('#yho').hide();
            $('#yht').show();
        } else { //否则
            $('.yxi').hide(); //就隐藏div
            $('#yho').show();
            $('#yht').hide();
        }
        return false;
    });

    // 顶部订单查询：全部订单(默认)，待付款，待发货，待收货，已完成
    $('.navt').children('ul').children('li').click(function() {
        $(this).addClass('active').siblings('li').removeClass('active');
        window.localStorage.setItem("active5", $(this).index());
        switch ($(this).index()) {
            case 0:
                findPageZero(pageNum);
                break;
            case 1:
                findPageZero(pageNum, $(this).index() - 1);
                break;
            case 2:
                findPageZero(pageNum, $(this).index() - 1);
                break;
            case 3:
                findPageZero(pageNum, $(this).index() - 1);
                break;
            case 4:
                findPageZero(pageNum, $(this).index() - 1);
                break;
        }

    });

    //	点击商品图片和名称跳转详情
    $(document).on("click", ".toDetails", function() {
        var dataId = $(this).attr("data-id");
        var dataGo = $(this).attr("data-go");
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
        });
        return false;
    });

    //确认收货
    $(document).on("click", ".confirmGot", function() {
        var dataId3 = $(this).attr("data-idp3");
        var datas = {
            orderId: dataId3
        };
        $.ajax({
            type: "post",
            data: datas,
            url: "/zlead/order/orderovid",
            dataType: "json",
            async: true,
            success: function(res) {
                active5 = window.localStorage.getItem("active5");
                var pageNum = 1;
                switch (active5) {
                    case "0":
                        findPageZero(pageNum);
                        break;
                    case "1":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "2":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "3":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "4":
                        findPageZero(pageNum, active5 - 1);
                        break;
                }
            }
        })
    });

    //    上传凭证--按钮
    $(document).on("click", ".wdetail2", function() {
        // 初始化状态
        $("#dialog_upload82").find('input').css('border', '1px solid rgba(229, 229, 229, 1)');
        $("#dialog_upload82").find('.tipsNo').html('');
        window.localStorage.removeItem("loadImg2");
        var vstatus = $(this).attr("data-vstatus");
        var vid = $(this).attr("data-vid");
        if (vid > 0) {
            //    已经上传过凭证不让点击
        } else {
            window.localStorage.setItem("uploadId", $(this).attr("data-idp2"));
            window.localStorage.setItem("uploadSucStatus", $(this).attr("data-vstatus"));
            $("body").css('overflow-y', 'hidden');
            $("#dialog_upload82").show();
            $("#imgsrc22").attr("src", "");
            $('.order_input8').val(''); // 初始化表单数据为空
            $(document).click(function() {
                $('#schedule-box12').hide();
            });
            $('.date_up').click(function() {
                return false;
            })
        }
    });
    //  关闭弹窗
    $("#qu22").click(function() {
        $("body").css('overflow-y', '');
        $("#dialog_upload82").hide()
    });
    //  关闭弹窗
    $("#qu82").click(function() {
        $("body").css('overflow-y', '');
        $("#dialog_upload82").hide()
    });
    //付款金额输入框校验
    $("#inputs2").keyup(function() {
        var reg = /^\d+\.?(\d{1,2})?$/;
        if (!reg.test($(this).val()) && $(this).val() != "") {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
            return;
        } else if (($(this).val().length == 2) && ($(this).val().indexOf("0") == 0) && !($(this).val().indexOf(".") == 1)) {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
            return;
        }
    });
    //时间日历插件-按钮
    $(".date_up").click(function() {
        $(".boxshaw1").show();
    });
    //日历
    function dateFormat(time) {
        var t = new Date(time);
        var str = '';
        var y = t.getFullYear();
        var month = t.getMonth() + 1;
        var d = t.getDate();
        var h = t.getHours() < 10 ? '0' + t.getHours() : t.getHours();
        var m = t.getMinutes() < 10 ? '0' + t.getMinutes() : t.getMinutes();
        var s = t.getSeconds() < 10 ? '0' + t.getSeconds() : t.getSeconds();

        str += y + '/' + month + '/' + d + '    ' + h + ':' + m + ":" + s;
        return str;
    }
    template.defaults.imports.dateFormat = dateFormat;

    //    上传图片--按钮
    var xhr;
    var fmt1;
    var images;
    var uploadcallback;

    $('.fileu').change(function(e) {
        var imgName2 = e.target.files[0].name;
        //限制图片上传类型
        var ext2;
        var idx2 = imgName2.lastIndexOf(".");

        if (imgName2 == "") {
            $(".tipsNo").text("请上传图片");
        } else {
            if (idx2 != -1) {
                ext2 = imgName2.substr(idx2 + 1).toUpperCase();
                ext2 = ext2.toLowerCase();
                if (ext2 != "jpg" && ext2 != "png" && ext2 != "jpeg" && ext2 != "bmp") {
                    $(".tipsNo").text("请上传JPG/PNG/JPEG/BMP格式的图片");
                    $("#imgsrc22").css("visibility", "hidden");
                    $("#imgsrc22").attr("src", "");
                    window.localStorage.removeItem("loadImg2");
                    return;
                } else {
                    $(".tipsNo").text();
                    bo_upload(e.target.files[0], function(data) {
                        if (data.err) {
                            return
                        } else {
                            if (data.code == 1) {
                                $(this).val("");
                                images = data.data;
                                var src = images;
                                window.localStorage.setItem("loadImg2", src);
                                $(".tipsNo").html('');
                                $("#imgsrc22").css("visibility", "visible");
                                $('#imgsrc22').attr('src', src)
                            } else {
                                $(".msg4").html(data.message);
                                $(".meng4").show();
                                $(".popupMsg4").show();
                            }

                        }
                    })
                }
            }
        }

    });
    //上传图片
    function bo_upload_complete(data) {
        uploadcallback(data);
    }

    function bo_upload(file, callback) {
        //限制图片大小
        var num = file.size / 1024 / 1024;
        if (num > 2) {
            alert("上传图片不能大于2M!");
            return
        }

        if (!window.FormData || !window.FileList) {
            throw ('您的浏览器不支持ajax upload');
        }
        uploadcallback = callback;
        if (xhr == null) {
            xhr = new XMLHttpRequest();
            xhr.upload.addEventListener("progress", function(evt) {}, false);
            xhr.addEventListener("load", function(evt) {
                var ret = eval('(' + evt.target.responseText + ')');
                bo_upload_complete(ret);
            }, false);
            xhr.addEventListener("error", function(evt) {
                alert("上传出错" + evt.toString());
            }, false);
            xhr.addEventListener("abort", function(evt) {
                alert("用户取消上传");
            }, false);
        }

        var formData = new FormData();

        formData.append('demo_input', file);
        xhr.open('POST', '/ajeasy/fdfs/uploading');
        xhr.send(formData);
    }

    //点击可放大
    $("#imgsrc22").click(function() {
        $("#dialog_upload82").css("opacity", '0');
        $("#dialog-bg22").show();
        var imgs = $(this).attr("src");
        $("#img-box22 img").attr("src", imgs)
    });

    //放大
    $("#fangda21").click(function() {
        $(".moban_img20").css("transform", "scale(1.3)");
        $("#huanyuan2").text("130%");
        $(this).hide();
        $("#fangda22").show()
    });
    $("#fangda22").click(function() {
        $(".moban_img20").css("transform", "scale(1.6)");
        $("#huanyuan2").text("160%");
        $(this).hide();
        $("#fangda32").show()
    });
    $("#fangda32").click(function() {
        $(".moban_img20").css("transform", "scale(2.2)");
        $("#huanyuan2").text("220%");
        $(this).hide();
        $("#fangda42").show()
    });
    $("#fangda42").click(function() {
        $(".moban_img20").css("transform", "scale(2.5)");
        $("#huanyuan2").text("250%");
        $(this).hide();
        $("#fangda21").show()
    });

    //还原
    $("#huanyuan2").click(function() {
        $("#huanyuan2").text("100%");
        $(".moban_img20").css("transform", "scale(1.0)")
    });
    //缩小
    $("#suoxiao21").click(function() {
        $(".moban_img20").css("transform", "scale(0.8)");
        $("#huanyuan2").text("80%");
        $(this).hide();
        $("#suoxiao22").show();
    });
    $("#suoxiao22").click(function() {
        $(".moban_img20").css("transform", "scale(0.6)");
        $("#huanyuan2").text("60%");
        $(this).hide();
        $("#suoxiao32").show()
    });
    $("#suoxiao32").click(function() {
        $(".moban_img20").css("transform", "scale(0.5)");
        $("#huanyuan2").text("50%");
        $(this).hide();
        $("#suoxiao42").show()
    });
    $("#suoxiao42").click(function() {
        $(".moban_img20").css("transform", "scale(0.2)");
        $("#huanyuan2").text("20%");
        $(this).hide();
        $("#suoxiao21").show()
    });
    //旋转
    $("#xuanzhuan21").click(function() {
        $(".moban_img20").css({ transform: "rotate(90deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan22").show()
    });
    $("#xuanzhuan22").click(function() {
        $(".moban_img20").css({ transform: "rotate(180deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan32").show()
    });
    $("#xuanzhuan32").click(function() {
        $(".moban_img20").css({ transform: "rotate(270deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan42").show()
    });
    $("#xuanzhuan42").click(function() {
        $(".moban_img20").css({ transform: "rotate(360deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan21").show()
    });

    //关闭放大效果
    $(".closeImg img").click(function() {
        $("#dialog_upload82").css("opacity", '1');
        $("#huanyuan").text("100%");
        $("#dialog-bg22").hide()
    });

    //    上传凭证--确定--按钮
    var amount;
    var payTime;
    var payWay2;
    var arr = [];
    //点击上传付款凭证
    $("#fo82").click(function() {
        var loadImg2 = window.localStorage.getItem("loadImg2");
        var uploadSucStatus = window.localStorage.getItem("uploadSucStatus");
        payTime = $("#h3Ele22").val();
        amount = $("#inputs2").val();
        payWay2 = $("#payWay2").val();

        if (amount == "") {
            $("#inputs2").css("border-color", "rgb(253, 87, 0)");
            $("#payWay2").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele22").css("border-color", "rgb(229, 229, 229)");
            $(".tipsNo").text("请输入付款金额");
            $(".tipsNo").show();
            $('#inputs2').on('input', function() {
                if ($(this).val() != '') {
                    $(".tipsNo").html('');
                    $(this).css("border-color", "rgb(229, 229, 229)");
                }
            })
        } else if ((/^\d+\.?(\d{1,2})?$/).test(amount) == false) {
            $("#inputs2").css("border-color", "rgb(253, 87, 0)");
            $("#payWay2").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele22").css("border-color", "rgb(229, 229, 229)");
            $(".tipsNo").text("请输入最多两位小数的数字");
            $(".tipsNo").show();
        } else if (payWay2 == "") {
            $("#inputs2").css("border-color", "rgb(229, 229, 229)");
            $("#payWay2").css("border-color", "rgb(253, 87, 0");
            $("#h3Ele22").css("border-color", "rgb(229, 229, 229)");
            $(".tipsNo").text("请选择付款方式");
            $(".tipsNo").show();
            $('#payWay2').on('input', function() {
                if ($(this).val() != '') {
                    $(".tipsNo").html('');
                    $(this).css("border-color", "rgb(229, 229, 229)");
                }
            })
        } else if (payTime == "") {
            $("#inputs2").css("border-color", "rgb(229, 229, 229)");
            $("#payWay2").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele22").css("border-color", "rgb(253, 87, 0");
            $(".tipsNo").text("请选择付款时间");
            $(".tipsNo").show();
        } else if (loadImg2 == "" || loadImg2 == "undefined" || loadImg2 == null) {
            $("#inputs2").css("border-color", "rgb(229, 229, 229)");
            $("#payWay2").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele22").css("border-color", "rgb(229, 229, 229)");
            $(".tipsNo").text("请上传图片");
            $(".tipsNo").show();
        } else {
            $.ajax({
                type: "post", //提交请求的方式
                cache: true, //是否有缓存
                url: "/zlead/voucher/addVoucher", //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                async: true, //是否异步
                data: {
                    amount: amount,
                    img: window.localStorage.getItem("loadImg2"),
                    payType: '1',
                    payTime: payTime,
                    payRemark: payWay2,
                    // uploadTime: fmt1,
                    // remark: '454',//可不传
                    orderIds: window.localStorage.getItem("uploadId")
                },
                success: function(res) {
                    if (res.code == 1) {
                        $(".tishi3").show();
                        $(".tishi3").show().delay(3000).hide(0);
                        $("#dialog_upload8").hide();
                        var id = window.localStorage.getItem("uploadId");
                        window.location.href = "User_center2.html?id=" + id;
                    } else {
                        alert("没有上传成功！")
                    }
                }
            })
        }

    });

}); //$

// num:页码；status:订单状态；size：每页显示数量
function findPageZero(pageNum, status) {
    if (status || status == 0) {
        var mydata = {
            pageNum: pageNum,
            status: status
        }
    } else {
        var mydata = {
            pageNum: pageNum
        }
    }
    $.ajax({
        type: "post",
        data: mydata,
        url: "/zlead/order/findPageZero",
        dataType: "json",
        beforeSend: function() {
            $('.mloading').show();
        },
        complete: function() {
            //ajax请求完成，不管成功失败
            $('.mloading').hide();

        },
        async: false,
        success: function(res) {
            $('.mloading').hide();
            maxNum = res.code;
            mydata = res.data;
            var list001 = []; //订单-每个订单可能有多条数据
            var list002 = []; //所有多条数据
            var list003 = res.data.OrderEntity;

            for (var i = 0; i < list003.length; i++) {
                var list001 = list001.concat(list003[i].OrderGoods);
            }
            var list = list001;
            var listNum = res.data.ids;

            // 接口新增字段status，表示不同状态订单的数量
            $(".n1").text(res.data.status[0]);
            $(".n2").text(res.data.status[1]);
            $(".n3").text(res.data.status[2]);
            $(".n4").text(res.data.status[3]);
            $(".n5").text(res.data.status[4]);

            if (listNum > 0) {
                $(".ta2").show();
                $(".taNo").hide();
                $(".ta3").show();
            } else {
                $(".ta2").hide();
                $(".taNo").show();
                $(".ta3").hide();
            }

            $(window).scrollTop(0);
            Page({
                num: parseInt((listNum + 10 - 1) / 10), //页码数
                startnum: pageNum, //指定页码
                elem: $('#page1'), //指定的元素
                callback: function(n) { //回调函数
                    $(window).scrollTop(0);
                    findPageZero(n, status)
                }
            });

            // 跳转指定页码：
            $('.button').unbind();
            $('.button').click(function() {
                pageNum = parseInt($(this).siblings('input').val());
                if (pageNum < 1) {
                    pageNum = 1;
                    $(this).siblings('input').val(pageNum);
                    $(window).scrollTop(0);
                }
                if (pageNum > parseInt((listNum + 10 - 1) / 10)) {
                    pageNum = parseInt((listNum + 10 - 1) / 10);
                    $(this).siblings('input').val(pageNum);
                    $(window).scrollTop(0);
                }
                findPageZero(pageNum, status)
            });
            if ($('.button').siblings('input').val() == '') {
                $('.button').siblings('input').val(1);
            }
            $('.button').siblings('input').on('blur', function() {
                if ($(this).val() == '') {
                    $(this).val(1);
                }
                if (isNaN(Number($(this).val()))) {
                    $(this).val(1);
                }
            });

            // 根据订单号渲染页面
            if (status || status == 0) {
                $(".ta2").html(createE(res.data.OrderEntity, status))
            } else {
                $(".ta2").html(createE(res.data.OrderEntity))
            }

            // 立即支付
            $('.wpayNow').click(function() {
                confirmD($(this).attr("data-idP"));
            });

            //关闭弹窗
            $(".closeN").click(function() {
                $(".collection_popbox2").hide();
                $(".collection_popbox_content2").hide();
            });

            //查看订单进度
            $('.wdetail3').click(function() {
                var id = $(this).attr("data-idP2");
                var vid = $(this).attr("data-vid");
                var vStatus = $(this).attr("data-vStatus");
                window.location.href = "User_center2.html?id=" + id;
            })
        },
        error: function(request) { //请求出错
        }

    })
}

//删除订单-确认弹窗
$(document).on("click", ".confirmDel", function() {
    deleteOrder(window.localStorage.getItem("deleteId"))
});

//删除订单
$(document).on("click", ".deleteOrder", function() {
    window.localStorage.setItem("deleteId", $(this).attr("data-orderId"));
    $(".collection_popbox2").show();
    $(".collection_popbox_content2").show();
});

// 确定订单：
function confirmD(id) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/order/payOrder", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        data: {
            orderId: id,
        },
        success: function(res) {
            if (res.code == 1) {
                var data = res.data;
                var datas = JSON.stringify(data);
                localStorage.setItem("data3", datas);
                active5 = window.localStorage.getItem("active5");
                var pageNum = 1;
                switch (active5) {
                    case "0":
                        findPageZero(pageNum);
                        break;
                    case "1":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "2":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "3":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "4":
                        findPageZero(pageNum, active5 - 1);
                        break;
                }
            }

        }
    })
}

//删除订单
function deleteOrder(id) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/order/updateorder", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        data: {
            orderId: id,
        },
        success: function(res) {
            if (res.code == 1) {
                $(".collection_popbox2").hide();
                $(".collection_popbox_content2").hide();
                active5 = window.localStorage.getItem("active5");
                var pageNum = 1;
                switch (active5) {
                    case "0":
                        findPageZero(pageNum);
                        break;
                    case "1":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "2":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "3":
                        findPageZero(pageNum, active5 - 1);
                        break;
                    case "4":
                        findPageZero(pageNum, active5 - 1);
                        break;
                }
            }
        }
    })
}


// 创建页面： null-全部订单，0-待付款，1-待发货，2-待收货,3已完成
// 状态不一样，渲染的结构不一样。
function createE(res, type) {
    var str = '';
    // 待付款 type = 0
    // 待发货 type = 1
    // 待收货 type = 2
    // 已完成 type = 3
    // 已取消 type = 4
    // 全部订单 type 没传
    switch (type) {
        case 0:
            for (var i = 0; i < res.length; i++) {
                str = str + Status_0(res[i]);
            }
            break;
        case 1:
            for (var i = 0; i < res.length; i++) {
                str = str + Status_1(res[i]);
            }
            break;
        case 2:
            for (var i = 0; i < res.length; i++) {
                str = str + Status_2(res[i]);
            }
            break;
        case 3:
            for (var i = 0; i < res.length; i++) {
                str = str + Status_3(res[i]);
            }
            break;
        case 4:
            for (var i = 0; i < res.length; i++) {
                str = str + Status_4(res[i]);
            }
            break;
        default:
            for (var i = 0; i < res.length; i++) {
                switch (res[i].status) {
                    case 0:
                        str = str + Status_0(res[i]);
                        break;
                    case 1:
                        str = str + Status_1(res[i]);
                        break;
                    case 2:
                        str = str + Status_2(res[i]);
                        break;
                    case 3:
                        str = str + Status_3(res[i]);
                        break;
                    case 4:
                        str = str + Status_4(res[i]);
                        break;
                }
            };
    }

    return str;
}

// 0-待付款，1-待发货，2-待收货,3-已完成，4-已取消
// status ==0
function Status_0(res) {
    var str = '';
    var s1 = '';
    s1 = '<div class="ding"><div class="bian"><div class="biao"><img src="../../shopping/img/lhpimg/ic_date.png"></div><div class="shi">';
    s1 += getTime(res.createDate);
    s1 += '</div><div class="hao"><span>订单号:</span><span>';
    s1 += res.sn;
    s1 += '</span></div><div class="hao">' + res.shopName + '</div></div>';
    var s2 = '';
    for (var j = 0; j < res.OrderGoods.length; j++) {
        // 限制订单字数为40字，多余省略号。如果需要修改字数，可在顶部修改countNum值。
        if (res.OrderGoods[j].goodsName.length > countNum) {
            res.OrderGoods[j].goodsName = res.OrderGoods[j].goodsName.slice(0, countNum) + '...';
        }
        if (j == 0) { // 每笔订单只生效一次创建：支付，取消，详情功能
            s2 += '<div class="nei"><div class="img"><img  data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div><div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            // s2 += '</span><span>参数&nbsp;:&nbsp;' + (res.OrderGoods[j].CrmPrdModel ? res.OrderGoods[j].CrmPrdModel.modelName : '') + '</span></div> <div class="shulian">';
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            if (res.OrderGoods[j].goodsTotalPrice) {
                s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            } else {
                s2 += '0.00';
            }
            s2 += '</span></div><div class="shulian yan">待支付</div><div class="onetai"><div class="wpayNow watchPay" data-idp="';
            s2 += res.id;
            s2 += '" id="payNow">立即支付</div>';
            s2 += '<div class="wdetail3 watchDetail" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">查看订单进度</div>';
            s2 += '</div></div>';
        } else {
            s2 += '<div class="nei"><div class="img"><img  data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"   class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div> <div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            s2 += res.OrderGoods[j].goodsTotalPrice;
            s2 += '</span></div></div>';
        }
    }
    str = str + s1 + s2 + '</div>';
    return str;
}

// 0-待付款，1-待发货，2-待收货,3-已完成，4-已取消
// status ==1
function Status_1(res) {
    var str = '';
    var s1 = '';
    s1 += ' <div class="ding"> <div class="bian"> <div class="biao"><img src="../../shopping/img/lhpimg/ic_date.png"></div> <div class="shi">';
    s1 += getTime(res.createDate);
    s1 += '</div> <div class="hao"><span>订单号:</span> <span>';
    s1 += res.sn;
    s1 += '</span></div><div class="hao">' + res.shopName + '</div>';
    s1 += '</div>';
    var s2 = '';
    for (var j = 0; j < res.OrderGoods.length; j++) {
        // 限制订单字数为40字，多余省略号。如果需要修改字数，可在顶部修改countNum值。
        if (res.OrderGoods[j].goodsName.length > countNum) {
            res.OrderGoods[j].goodsName = res.OrderGoods[j].goodsName.slice(0, countNum) + '...';
        }
        if (j == 0) { // 每笔订单只生效一次创建：支付，取消，详情功能 
            s2 += '<div class="nei"> <div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div> <div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            if (res.OrderGoods[j].goodsTotalPrice) {
                s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            } else {
                s2 += '0.00';
            }
            s2 += '</span></div> <div class="shulian yan">待发货</div>';
            s2 += '<div class="towtai">';
            s2 += '<div class="wdetail3 watchDetail" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">查看订单进度</div>';
            s2 += '<div class="wdetail2 watchPro" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">上传凭证</div>';
            s2 += '</div></div>';
        } else {
            s2 += '<div class="nei"><div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div><div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            s2 += '</span></div></div>';
        }
    }
    str = str + s1 + s2 + '</div>';
    return str;
}

// 0-待付款，1-待发货，2-待收货,3-已完成，4-已取消
// status ==2
function Status_2(res) {
    var str = '';
    var s1 = '';
    s1 += ' <div class="ding"> <div class="bian"> <div class="biao"><img src="../../shopping/img/lhpimg/ic_date.png"></div> <div class="shi">';
    s1 += getTime(res.createDate);
    s1 += '</div> <div class="hao"><span>订单号:</span> <span>';
    s1 += res.sn;
    s1 += '</span></div><div class="hao">' + res.shopName + '</div>';
    s1 += '</div>';
    var s2 = '';
    for (var j = 0; j < res.OrderGoods.length; j++) {
        // 限制订单字数为40字，多余省略号。如果需要修改字数，可在顶部修改countNum值。
        if (res.OrderGoods[j].goodsName.length > countNum) {
            res.OrderGoods[j].goodsName = res.OrderGoods[j].goodsName.slice(0, countNum) + '...';
        }
        if (j == 0) { // 每笔订单只生效一次创建：支付，取消，详情功能
            s2 += '<div class="nei"> <div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div> <div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            if (res.OrderGoods[j].goodsTotalPrice) {
                s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            } else {
                s2 += '0.00';
            }
            s2 += '</span></div> <div class="shulian yan">待收货</div> <div class="towtai">';
            s2 += '<div class="confirmGot watchGoods"  data-idp3="';
            s2 += res.id;
            s2 += '">确认收货</div>';
            s2 += '<div class="wdetail3 watchDetail" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">查看订单进度</div>';
            s2 += '<div class="wdetail2 watchPro" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">上传凭证</div>';
            s2 += '</div></div>';
        } else {
            s2 += '<div class="nei"><div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div><div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            s2 += '</span></div></div>';
        }
    }
    str = str + s1 + s2 + '</div>';
    return str;
}

// 0-待付款，1-待发货，2-待收货,3-已完成，4-已取消
// status ==3
function Status_3(res) {
    var str = '';
    var s1 = '';
    s1 += ' <div class="ding"> <div class="bian"> <div class="biao"><img src="../../shopping/img/lhpimg/ic_date.png"></div> <div class="shi">';
    s1 += getTime(res.createDate);
    s1 += '</div> <div class="hao"><span>订单号:</span> <span>';
    s1 += res.sn;
    // s1 += '</span></div><div class="hao">'+res.shopName+'</div> <div class="shan"><img src="../../shopping/img/lhpimg/Edit_Copy1.png" /></div> </div>';
    s1 += '</span></div><div class="hao">' + res.shopName + '</div>';
    s1 += ' <div class="shan"><img class="deleteOrder" data-orderId="' + res.id + '" src="../../shopping/img/lhpimg/Edit_Copy1.png" /></div> ';
    s1 += '</div>';
    var s2 = '';
    for (var j = 0; j < res.OrderGoods.length; j++) {
        // 限制订单字数为40字，多余省略号。如果需要修改字数，可在顶部修改countNum值。
        if (res.OrderGoods[j].goodsName.length > countNum) {
            res.OrderGoods[j].goodsName = res.OrderGoods[j].goodsName.slice(0, countNum) + '...';
        }
        if (j == 0) { // 每笔订单只生效一次创建：支付，取消，详情功能
            s2 += '<div class="nei"> <div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div> <div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            if (res.OrderGoods[j].goodsTotalPrice) {
                s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            } else {
                s2 += '0.00';
            }
            s2 += '</span></div> <div class="shulian yan">已完成</div><div class="towtai">';
            s2 += '<div class="wdetail3 watchDetail" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">查看订单进度</div>';
            s2 += '<div class="wdetail2 watchPro" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">上传凭证</div>';
            s2 += '</div></div>';
        } else {
            s2 += '<div class="nei"><div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div><div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            s2 += '</span></div></div>';
        }
    }
    str = str + s1 + s2 + '</div>'
    return str;
}

// 0-待付款，1-待发货，2-待收货,3-已完成，4-已取消
// status ==4
function Status_4(res) {
    var str = '';
    var s1 = '';
    s1 += ' <div class="ding"> <div class="bian"> <div class="biao"><img src="../../shopping/img/lhpimg/ic_date.png"></div> <div class="shi">';
    s1 += getTime(res.createDate);
    s1 += '</div> <div class="hao"><span>订单号:</span> <span>';
    s1 += res.sn;
    // s1 += '</span></div><div class="hao">'+res.shopName+'</div> <div class="shan"><img src="../../shopping/img/lhpimg/Edit_Copy1.png" /></div> </div>';
    s1 += '</span></div><div class="hao">' + res.shopName + '</div>';
    // s1 += '<div class="shan"><img src="../../shopping/img/lhpimg/Edit_Copy1.png" /></div>' +
    s1 += '</div>';
    var s2 = '';
    for (var j = 0; j < res.OrderGoods.length; j++) {
        // 限制订单字数为40字，多余省略号。如果需要修改字数，可在顶部修改countNum值。
        if (res.OrderGoods[j].goodsName.length > countNum) {
            res.OrderGoods[j].goodsName = res.OrderGoods[j].goodsName.slice(0, countNum) + '...';
        }
        if (j == 0) { // 每笔订单只生效一次创建：支付，取消，详情功能
            s2 += '<div class="nei"> <div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div> <div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            if (res.OrderGoods[j].goodsTotalPrice) {
                s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            } else {
                s2 += '0.00';
            }
            s2 += '</span></div> <div class="shulian yan">已取消</div> <div class="towtai">';
            s2 += '<div class="wdetail2 watchPro" data-idp2="';
            s2 += res.id;
            s2 += '"data-vid ="';
            s2 += res.voucherId;
            s2 += '"data-vStatus ="';
            s2 += res.status;
            s2 += '">上传凭证</div>';
            s2 += '</div></div>';
        } else {
            s2 += '<div class="nei"><div class="img"><img data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails" src="';
            s2 += res.OrderGoods[j].goodsImg;
            s2 += '"></div><div class="miao"><span data-id="' + res.OrderGoods[j].goodsId + '"  data-go="' + res.buyType + '"  class="toDetails">';
            s2 += res.OrderGoods[j].goodsName;
            s2 += '</span><span>参数&nbsp;:&nbsp;' + res.OrderGoods[j].goodsAttriValue + '</span></div> <div class="shulian">';
            s2 += res.OrderGoods[j].count;
            s2 += '</div> <div class="shulian"><span>¥</span><span class="money">';
            s2 += res.OrderGoods[j].goodsTotalPrice.toFixed(2);
            s2 += '</span></div></div>';
        }
    }
    str = str + s1 + s2 + '</div>';
    return str;
}

function getTime(time) {
    var t = new Date(time);
    var str = '';

    var y = t.getFullYear();
    var month = t.getMonth() + 1;
    var d = t.getDate();
    var h = t.getHours() < 10 ? '0' + t.getHours() : t.getHours();
    var m = t.getMinutes() < 10 ? '0' + t.getMinutes() : t.getMinutes();
    var s = t.getSeconds() < 10 ? '0' + t.getSeconds() : t.getSeconds();

    // str += y + '年' + month + '月' + d + '日&nbsp;' + h + ':' + m + ":" + s;
    str += y + '/' + month + '/' + d;
    return str;
}

// 日期控件
laydate.path = '../../shopping/css/laydate.css';
laydate.render({
    elem: '#h3Ele22',
    format: 'yyyy/MM/dd',
    done: function() {
        if ($('#h3Ele22').val() != '') {
            $(".tipsNo").html('');
            $('#h3Ele22').css("border-color", "rgb(229, 229, 229)");
        }
    }
});