var data666;
var lengths;
$(function() {
    window.localStorage.removeItem("loadImg");
    //獲取頁面內容
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/order/getAllnoVoucherOrderList?t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        beforeSend: function() {
            $('.loading').show();
        },
        async: true, //是否异步
        success: function(res) {
            $('.loading').hide();
            if (res.code == 1) {
                $('.uploadFile').show();
                $('.upload_no').hide();
                data666 = res.data.result;
                lengths = data666.length;
                var datas = {
                    list: data666
                };
                //勿删
                function dateFormat(date, format) {
                    date = new Date();
                    if (date.getFullYear()) {
                        date = new Date();
                    } else {
                        date = new Date(Date.parse(date.replace(/-/g, "/")));
                    }
                    var map = {
                        "M": date.getMonth() + 1, //月份
                        "d": date.getDate(), //日
                        "h": date.getHours(), //小时
                        "m": date.getMinutes(), //分
                        "s": date.getSeconds(), //秒
                        "q": Math.floor((date.getMonth() + 3) / 3), //季度
                        "S": date.getMilliseconds() //毫秒
                    };
                    format = format.replace(/([yMdhmsqS])+/g, function(all, t) {
                        var v = map[t];
                        if (v !== undefined) {
                            if (all.length > 1) {
                                v = '0' + v;
                                v = v.substr(v.length - 2);
                            }
                            return v;
                        } else if (t === 'y') {
                            return (date.getFullYear() + '').substr(4 - all.length);
                        }
                        return all;
                    });
                    return format;
                };

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
                var html = template("tel_upload", datas)
                $(".list_sty").append(html)
            } else {
                $('.upload_no').show();
                $('.uploadFile').hide();
            }
        }
    });

    //付款金额输入框校验
    $("#inputs").keyup(function() {
        var reg = /^\d+\.?(\d{1,2})?$/;
        if (!reg.test($(this).val()) && $(this).val() != "") {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
            return;
        } else if (($(this).val().length == 2) && ($(this).val().indexOf("0") == 0) && !($(this).val().indexOf(".") == 1)) {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
            return;
        }
    });

    var arr = [];
    var bool;
    var dataid;
    var Ids;

    //点击li标签
    $(".list_sty").on("click", "li", function() {
        $(this).find(".sty_img2").toggle();
        dataid = $(this).attr("data-id");

        $(this).toggleClass("active1");
        bool = $(this).hasClass("active1");
        if (bool) {
            arr.push(dataid);
            if ($(this).siblings().hasClass("active1")) {
                $(".all").show();
                $(".img_show").hide();
            }
        } else {
            $(".all").hide();
            $(".img_show").show();
            arr.forEach(function(item, index) {
                if (dataid == item) {
                    arr.splice(index, 1)
                }
            })
        }
        $("#allNum").html(arr.length);
        if (arr.length < lengths) {
            $(".img_show").hide();
            $(".all").show()
        } else {
            $(".img_show").show();
            $(".all").hide()
        }

        // 上传凭证按钮点击
        myUpload($('.active1').length);
    });

    //点击订单号
    $(document).on("click", ".goDetails", function() {
        var id = $(this).attr("data-id");
        window.location.href = "User_center2.html?id=" + id
        return false;
    });

    //点击全选
    var bool2;
    $(".all").on("click", function() {
        $(".sty_img2").show();
        $(".sty_img").hide();
        $(this).hide();
        $(".img_show").show();
        $(".list_sty li").addClass("active1");
        bool2 = $(".list_sty li").hasClass("active1");
        if (bool2) {
            arr = [];
            data666.forEach(function(item) {
                arr.push(item.id)
            })
        }
        $("#allNum").html(arr.length);
        $("#imgsrc2").attr("src", "");
        $(".tipsN").text("");
        myUpload($('.active1').length);
    });
    $(".img_show").on("click", function() {
        arr = [];
        $(".sty_img2").hide();
        $(".sty_img").show();
        $(this).hide();
        $(".all").show();
        $(".list_sty li").removeClass("active1");
        $("#allNum").html(arr.length);
        $("#imgsrc2").attr("src", "");
        $(".tipsN").text("");
        myUpload($('.active1').length);
    });

    var xhr;
    var fmt1;
    var images;
    var uploadcallback;

    //上传图片
    function bo_upload_complete2(data) {
        uploadcallback(data);
    }

    function bo_upload2(file, callback) {
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
                bo_upload_complete2(ret);
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

    $('.fileu2').change(function(e) {
        var imgName = e.target.files[0].name;
        //限制图片上传类型
        var ext;
        var idx = imgName.lastIndexOf(".");

        if (imgName == "") {
            $(".tipsN").text("请上传图片");
        } else {
            if (idx != -1) {
                ext = imgName.substr(idx + 1).toUpperCase();
                ext = ext.toLowerCase();
                if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "bmp") {
                    $(".tipsN").text("请上传JPG/PNG/JPEG/BMP格式的图片");
                    $("#imgsrc2").attr("src", "");
                    $("#imgsrc22").css("visibility", "hidden");
                    window.localStorage.removeItem("loadImg");
                    return;
                } else {
                    $(".tipsN").text();
                    bo_upload2(e.target.files[0], function(data) {
                        if (data.err) {
                            return
                        } else {
                            $(this).val("");
                            if (data.code == 1) {
                                images = data.data;
                                var src = images;
                                $("#imgsrc2").css("visibility", "visible");
                                window.localStorage.setItem("loadImg", src);
                                $(".tipsN").html('');
                                $('#imgsrc2').attr('src', src)
                            } else {
                                //    上传失败
                                alert(data.message)
                            }
                        }
                    })
                }
            }
        }

    });

    var amount;
    var payTime;
    var payWay;
    //点击上传付款凭证
    $("#fo8").click(function() {
        var loadImg = window.localStorage.getItem("loadImg");
        payTime = $("#h3Ele2").val();
        amount = $("#inputs").val();
        payWay = $("#payWay").val();
        Ids = arr.join(",");
        if (amount == "") {
            $("#inputs").css("border-color", "rgb(253, 87, 0)");
            $("#payWay").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele2").css("border-color", "rgb(229, 229, 229)");
            $(".tipsN").text("请输入付款金额");
            $('#inputs').on('input', function() {
                if ($(this).val() != '') {
                    $(".tipsN").html('');
                    $(this).css("border-color", "rgb(229, 229, 229)");
                }
            })
        } else if ((/^\d+\.?(\d{1,2})?$/).test(amount) == false) {
            $("#inputs").css("border-color", "rgb(253, 87, 0)");
            $("#payWay").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele2").css("border-color", "rgb(229, 229, 229)");
            $(".tipsN").text("请输入最多两位小数的数字");
        } else if (payWay == "") {
            $("#inputs").css("border-color", "rgb(229, 229, 229)");
            $("#payWay").css("border-color", "rgb(253, 87, 0");
            $("#h3Ele2").css("border-color", "rgb(229, 229, 229)");
            $(".tipsN").text("请选择付款方式");
            $('#payWay').on('input', function() {
                if ($(this).val() != '') {
                    $(".tipsN").html('');
                    $(this).css("border-color", "rgb(229, 229, 229)");
                }
            })

        } else if (payTime == "") {
            $("#inputs").css("border-color", "rgb(229, 229, 229)");
            $("#payWay").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele2").css("border-color", "rgb(253, 87, 0");
            $(".tipsN").text("请选择付款时间");
            // 日期部分检测改动需要写在日期插件的配置里面
        } else if (loadImg == "" || loadImg == "undefined" || loadImg == null) {
            $("#inputs").css("border-color", "rgb(229, 229, 229)");
            $("#payWay").css("border-color", "rgb(229, 229, 229)");
            $("#h3Ele2").css("border-color", "rgb(229, 229, 229)");
            $(".tipsN").text("请上传图片");
        } else {
            $.ajax({
                type: "post", //提交请求的方式
                cache: true, //是否有缓存
                url: "/zlead/voucher/addVoucher", //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                async: true, //是否异步
                data: {
                    amount: amount,
                    img: window.localStorage.getItem("loadImg"),
                    payType: '1',
                    payTime: payTime,
                    payRemark: payWay,
                    // uploadTime: fmt1,
                    // remark: '454',//可不传
                    orderIds: Ids
                },
                success: function(res) {
                    if (res.code == 1) {
                        $(".tishi3").show();
                        $(".tishi3").show().delay(3000).hide(0);
                        $("#dialog_upload8").hide();
                        window.location.reload();
                    } else {
                        // alert("没有上传成功！")
                        $(".msg2").html("没有上传成功！");
                        $(".meng21").show();
                        $(".popupMsg21").show();
                    }
                }
            })
        }

    });

    $("#h3Ele2").bind("input propertychange", function(event) {
        var val = parseInt($("#h3Ele2").val());
        if (isNaN(val)) {
            alert("付款金额应为数字！");
            $("#h3Ele2").val("");
            return
        }
    });

    //  关闭弹窗
    $("#qu2").click(function() {
        $("body").css('overflow-y', '');
        $("#dialog_upload8").hide()
    });

    //  关闭弹窗
    $("#qu8").click(function() {
        $("body").css('overflow-y', '');
        $("#dialog_upload8").hide()
    });

    $(".btn").on("click", function() {
        // $(".dialog").show()
    });

    $(".con").on("click", function() {
        $(".dialog").show()
    });

    $(".cov").on("click", function() {
        $(".dialog").hide()
    });

    $('#cat').click(function() { //点击a标签
        if ($('.cart_xi').is(':hidden')) { //如果当前隐藏
            $('.cart_xi').show(); //那么就显示div
            $('#xt').show();
            $('#st').hide();
        } else { //否则
            $('.cart_xi').hide(); //就隐藏div
            $('#xt').hide();
            $('#st').show();
        }
    });

    $('#gen_b').click(function() { //点击a标签
        if ($('.info_xione').is(':hidden')) { //如果当前隐藏
            $('.info_xione').show(); //那么就显示div
        } else { //否则
            $('.info_xione').hide(); //就隐藏div
        }
    });
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
    $('#ino').click(function() { //点击a标签
        if ($('.info_xi').is(':hidden')) { //如果当前隐藏
            $('.info_xi').show(); //那么就显示div
            $('#xo').show();
            $('#so').hide();
        } else { //否则
            $('.info_xi').hide(); //就隐藏div
            $('.info_xione').hide();
            $('#xo').hide();
            $('#so').show();
        }
    });
    $('#ino').click(function() { //点击a标签
        $('.cart_xi').hide(); //就隐藏div
    });
    $('#cat').click(function() { //点击a标签
        $('.info_xi').hide(); //就隐藏div
        $('.info_xione').hide();

    });

    //点击可放大
    $("#imgsrc2").click(function() {
        $("#dialog_upload8").css("opacity", '0');
        $("#dialog-bg2").show();
        var imgs = $(this).attr("src");
        $("#img-box2 img").attr("src", imgs)
    });

    //放大
    $("#fangda").click(function() {
        $(".moban_img2").css("transform", "scale(1.3)");
        $("#huanyuan").text("130%");
        $(this).hide();
        $("#fangda2").show()
    });
    $("#fangda2").click(function() {
        $(".moban_img2").css("transform", "scale(1.6)");
        $("#huanyuan").text("160%");
        $(this).hide();
        $("#fangda3").show()
    });
    $("#fangda3").click(function() {
        $(".moban_img2").css("transform", "scale(2.2)");
        $("#huanyuan").text("220%");
        $(this).hide();
        $("#fangda4").show()
    });
    $("#fangda4").click(function() {
        $(".moban_img2").css("transform", "scale(2.5)");
        $("#huanyuan").text("250%");
        $(this).hide();
        $("#fangda").show()
    });

    //还原
    $("#huanyuan").click(function() {
        $(".moban_img2").css("transform", "scale(1.0)");
        $("#huanyuan").text("100%");
    });
    //缩小
    $("#suoxiao").click(function() {
        $(".moban_img2").css("transform", "scale(0.8)");
        $("#huanyuan").text("80%");
        $(this).hide();
        $("#suoxiao2").show()
    });
    $("#suoxiao2").click(function() {
        $(".moban_img2").css("transform", "scale(0.6)");
        $("#huanyuan").text("60%");
        $(this).hide();
        $("#suoxiao3").show()
    });
    $("#suoxiao3").click(function() {
        $(".moban_img2").css("transform", "scale(0.5)");
        $("#huanyuan").text("50%");
        $(this).hide();
        $("#suoxiao4").show()
    });
    $("#suoxiao4").click(function() {
        $(".moban_img2").css("transform", "scale(0.2)");
        $("#huanyuan").text("20%");
        $(this).hide();
        $("#suoxiao").show()
    });
    //旋转
    $("#xuanzhuan").click(function() {
        $(".moban_img2").css({ transform: "rotate(90deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan2").show()
    });
    $("#xuanzhuan2").click(function() {
        $(".moban_img2").css({ transform: "rotate(180deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan3").show()
    });
    $("#xuanzhuan3").click(function() {
        $(".moban_img2").css({ transform: "rotate(270deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan4").show()
    });
    $("#xuanzhuan4").click(function() {
        $(".moban_img2").css({ transform: "rotate(360deg)", "transform-origin": "50% 50%" });
        $(this).hide();
        $("#xuanzhuan").show()
    });

    //关闭放大效果
    $(".closeImg img").click(function() {
        $("#dialog_upload8").css("opacity", '1');
        $("#dialog-bg2").hide()
    });

    //弹窗关闭按钮
    $(".know").click(function() {
        $(".meng21").hide();
        $(".popupMsg21").hide();
    });
    //弹窗关闭按钮--X
    $(".close").click(function() {
        $(".meng21").hide();
        $(".popupMsg21").hide();
    });

}); //

// 上传凭证按钮点击
function myUpload(len) {
    if (len > 0) {
        $("#upload_btns").css({
            background: 'rgba(253,87,0,1)'
        });

        $("#upload_btns").click(function() {
            // 初始化状态
            $("#dialog_upload8").find('input').css('border', '1px solid rgba(229, 229, 229, 1)');
            $('.tipsN').html('');
            $("#imgsrc2").attr("src", "");
            window.localStorage.removeItem("loadImg");
            var activeC = $(".list_sty").find("li");
            var arrId = [];
            for (var i = 0; i < activeC.length; i++) {
                if ((activeC.eq(i)).hasClass("active1")) {
                    arrId.push(activeC.eq(i).attr("data-shopId"))
                }
            }
            if (arrId.length == 0) { //未选中

            } else if (arrId.length > 0) { //选中一个，工厂不会重复
                var isTrue = true;
                for (var i = 0; i < arrId.length; i++) {
                    if (arrId.indexOf(arrId[i]) != 0) {
                        isTrue = false;
                    }
                }
                if (isTrue == true) { //工厂不会重复
                    $("body").css('overflow-y', 'hidden');
                    $("#dialog_upload8").show();
                    $('.order_input8').val(''); // 初始化表单数据为空
                    $(document).click(function() {
                        $('#schedule-box1').hide();
                    });
                    // $('.date_up').click(function() {
                    //     $(".boxshaw1").show();
                    //     return false;
                    // })
                } else { //工厂id不完全重复，多家工厂无法上传
                    $(".meng21").show();
                    $(".popupMsg21").show();
                }
            }
        })
    } else {
        $("#upload_btns").css({
            background: 'rgba(201, 201, 201, 1)'
        });
        $("#upload_btns").off("click")
    }
}

// 日期控件
laydate.path = '../../shopping/css/laydate.css';
laydate.render({
    elem: '#h3Ele2',
    format: 'yyyy/MM/dd',
    done: function() {
        if ($('#h3Ele2').val() != '') {
            $(".tipsN").html('');
            $('#h3Ele2').css("border-color", "rgb(229, 229, 229)");
        }
    }
});