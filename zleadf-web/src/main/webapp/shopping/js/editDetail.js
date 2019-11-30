$(function() {
    var tpl = '';
    var width = document.documentElement.clientWidth
    var height = document.documentElement.clientHeight

    document.getElementById("top3").style.width = width + "px";
    $(".pinpai").click(function() {
        if ($('.pinpai2').css("display") == "block") {
            $('.pinpai2').hide();
        } else {
            $('.pinpai2').show();
        }
    });

    $(".pinpai2 li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        var div = $(this).html();

        tpl += '<div><img src="img/86ec25157e426b38304fea279d7cade.png"/>&nbsp;' + div + '</div>';
        $(".pinpai").html(tpl);

    });
    $(".img").click(function() {
        $(".meng").hide();
        $(".xian").hide();

        window.location.href = "left.html#dz"
    });


    var lists1 = localStorage.getItem("lists");
    var list2 = JSON.parse(lists1);
    $("#phone").val(list2.phone);
    $("#name").val(list2.memberName);
    $("#shengs").val(list2.provinceName);
    $("#shis").val(list2.cityName);
    $("#quyus").val(list2.countyName);
    $("#detail_add").val(list2.address);
    var addressId = list2.id;
    var defaults = list2.isDefault;
    if(defaults == 1){
        $(".pandan").addClass("mr").removeClass("nomr")
    }else if(defaults ==0){
        $(".pandan").addClass("nomr").removeClass("mr")
    }
    // 初始化是否为默认地址：1是默认地址，0是非默认
    var type = localStorage.getItem("type");
    if (type == 1) {
        $(".pandan").addClass('mr').removeClass("nomr")
    } else {
        $(".pandan").addClass('nomr').removeClass("mr")
    }

    $("#qu").click(function() {
        var flag1 = $("#phone").val() != list2.phone || $("#name").val() != list2.memberName || $("#shengs").val() != list2.provinceName || $("#shis").val() != list2.cityName || $("#quyus").val() != list2.regionName || $("#detail_add").val() != list2.address;
        var flag2 = ($(".pandan").hasClass('mr') ? 1 : 0) == type ? false : true;
        if (flag1 || flag2) {
            // 二次确认弹窗
            $('.popupMsg5').show();
            $('.input_addr').hide();
            $('.popupMsg5').find('.close').click(function() {
                window.location.href = "left.html#dz"
            })
            $('.popupMsg5').find('.cancel').click(function() {
                $('.popupMsg5').hide();
                $('.input_addr').show();
            })
            $('.popupMsg5').click(function() {
                return false;
            })

            $(document).click(function() {
                $('.popupMsg5').hide();
                $('.input_addr').show();
            })

        } else {
            window.location.href = "left.html#dz"
        }
        return false;
    });
    // 页面离开--提示信息出错--勿删
    // window.onbeforeunload = function(e) {　　
    //     var e = window.event || e;　
    //     var flag1 = $("#phone").val() != list2.phone || $("#name").val() != list2.memberName || $("#shengs").val() != list2.provinceName || $("#shis").val() != list2.cityName || $("#quyus").val() != list2.countyName || $("#detail_add").val() != list2.detailAddress
    //     var flag2 = ($(".pandan").hasClass('mr') ? 1 : 0) == type ? false : true;
    //     if (flag1 || flag2) {
    //         var msg = '您所查找的网页要使用已输入的信息。返回此页可能需要重复已进行的所有操作。是否要继续操作？';
    //         e.returnValue = msg;
    //         return msg;
    //     }　
    // };

    //设置默认地址：
    $(".pandan").click(function() {
        if ($(this).attr("class") == "pandan nomr") {
            $(this).removeClass("nomr").addClass("mr");
        } else {
            $(this).addClass("nomr").removeClass("mr");
        }
    });

    // 获取省
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/region/findprovince", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        success: function(res) {
            if (res.code == 1) {
                var data = res.data;
                var data2 = {
                    list: data
                };
                var html = template("tel", data2);
                $("#shengs").html(html)
            }
            data.forEach(function(ele, index) {
                if (ele.regionCode == list2.provinceId) {
                    $("#shengs").val(ele.regionName);
                    var idi = ele.id;
                    $.ajax({
                        type: "post", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/zlead/region/findid", //访问servlet的路径
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        data: {
                            parentId: idi
                        },
                        success: function(res) {
                            if (res.code == 1) {
                                var data5 = res.data;
                                var ids = data5[0].id;
                                var data3 = {
                                    list2: data5
                                };
                                var html = template("tel2", data3);
                                $("#shis").html(html)
                            }
                            data5.forEach(function(ele, index) {
                                if (ele.regionCode == list2.cityId) {
                                    $("#shis").val(ele.regionName);
                                    var idsi = ele.id;
                                    $.ajax({
                                        type: "post", //提交请求的方式
                                        cache: true, //是否有缓存
                                        url: "/zlead/region/findid", //访问servlet的路径
                                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                        data: {
                                            parentId: idsi
                                        },
                                        success: function(res) {
                                            if (res.code == 1) {
                                                var data6 = res.data;
                                                var data7 = {
                                                    list3: data6
                                                };
                                                var html = template("tel3", data7);
                                                $("#quyus").html(html)
                                            }
                                            data6.forEach(function(ele, index) {
                                                if (ele.regionCode == list2.countyId) {
                                                    $("#quyus").val(ele.regionName)
                                                }
                                            })
                                        }
                                    })

                                }
                            })


                        }
                    })
                }

            })

            // $("#shengs").val(list2.provinceName);
            // $("#shis").val(list2.cityName);
            // $("#quyus").val(list2.countyName);
            for (var i = 0; i < $('#shengs').children().length; i++) {
                if ($('#shengs').children().eq(i).html() == list2.provinceName) {
                    $('#shengs').children().eq(i).attr('selected', true);
                    Getcity($('#shengs').children().eq(i).attr('data-id'), list2);
                }
            }

        }
    });
    //获取市
    $("#shengs").change(function() {
        var id = $(this).children('option:selected').attr("data-id")
        var sheng = $(this).children('option:selected').val()
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findid", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            data: {
                parentId: id
            },
            success: function(res) {
                if (res.code == 1) {
                    var data5 = res.data;
                    var ids = data5[0].id;
                    var data3 = {
                        list2: data5
                    };
                    var html = template("tel2", data3);
                    $("#shis").html(html)
                }
                $.ajax({
                    type: "post", //提交请求的方式
                    cache: true, //是否有缓存
                    url: "/zlead/region/findid", //访问servlet的路径
                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                    data: {
                        parentId: ids
                    },
                    success: function(res) {
                        if (res.code == 1) {
                            var data6 = res.data;
                            var data7 = {
                                list3: data6
                            };
                            var html = template("tel3", data7);
                            $("#quyus").html(html)
                        }
                    }
                })
            }
        })
    });
    //获取区
    $("#shis").change(function() {
        var id2 = $(this).children('option:selected').attr("data-id2");
        var shi = $(this).children('option:selected').val();
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findid", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            data: {
                parentId: id2
            },
            success: function(res) {
                if (res.code == 1) {
                    var data6 = res.data;
                    var data7 = {
                        list3: data6
                    };
                    var html = template("tel3", data7);
                    $("#quyus").html(html)
                }
            }
        })
    })

    $("#quyus").change(function() {
        var qu = $(this).children('option:selected').val();
    });

    $("#phone").keyup(function() {
        if ($(this).val().length >= 11) {
            $(this).val($(this).val().substr(0, 11));
        }
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
    });

    //  提交保存收货地址
    $("#fo").click(function() {
        var memberName = $("#name").val();
        var phone = $("#phone").val();
        var detail_add = $("#detail_add").val();
        var sheng0 = $("#shengs").val();
        var shi0 = $("#shis").val();
        var qu0 = $("#quyus").val();
        // 判断当前是否设置默认地址hasClass('mr')
        var flag = $(this).parent().parent().find('.pandan').hasClass('mr');
        if (flag) {
            var isDefault = '1';
        } else {
            var isDefault = '0';
        }

        if(memberName == ""){
            $(".tipsN").html("请输入收货人姓名");
            $(".tipsN").show();
            $("#name").css("border","1px solid #CC5B4D");
            $("#phone").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border","1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border","1px solid rgba(229, 229, 229, 1)")
        }else if(phone == ""){
            $(".tipsN").html("请输入收货人联系方式");
            $(".tipsN").show();
            $("#name").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border","1px solid #CC5B4D");
            $(".select").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border","1px solid rgba(229, 229, 229, 1)")
        }else if(phone.length != 11){
            $(".tipsN").html("请输入正确的收货人联系方式");
            $(".tipsN").show();
            $("#name").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border","1px solid #CC5B4D");
            $(".select").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border","1px solid rgba(229, 229, 229, 1)")
        }else if(sheng0 == "" || shi0 == "" ||qu0 == "" || sheng0 == "请选择" || shi0 == "请选择" ||qu0 == "请选择"){
            $(".tipsN").html("请输入收货人所在区域");
            $(".tipsN").show();
            $("#name").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border","1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border","1px solid #CC5B4D");
            $("#detail_add").css("border","1px solid rgba(229, 229, 229, 1)")
        }else if(detail_add == ""){
            $(".tipsN").html("请输入详细地址");
            $(".tipsN").show();
            $("#name").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border","1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border","1px solid #CC5B4D")
        }else {
            $("#name").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border","1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border","1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border","1px solid rgba(229, 229, 229, 1)");
            $(".tipsN").html();
            $.ajax({
                type: "post", //提交请求的方式
                cache: true, //是否有缓存
                url: "/zlead/memaddr/addOrUpdateMemberAddress", //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                data: {
                    memberName: memberName,
                    phone: phone,
                    provinceName: sheng0,
                    cityName: shi0,
                    countyName: qu0,
                    detailAddress: detail_add,
                    isDefault: isDefault,
                    type: '2',
                    addressId: list2.id
                },
                success: function(res) {
                    if (res.code == 1) {
                        window.location.href = "left.html#dz"
                    }
                }
            })
        }
    })




});


function Getcity(id, list2) {

    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/region/findid", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            parentId: id
        },
        success: function(res) {
            if (res.code == 1) {
                var data5 = res.data;
                var ids = data5[0].id;
                var data3 = {
                    list2: data5
                };
                var html = template("tel2", data3);
                $("#shis").html(html)
            }
            $.ajax({
                type: "post", //提交请求的方式
                cache: true, //是否有缓存
                url: "/zlead/region/findid", //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                data: {
                    parentId: ids
                },
                success: function(res) {
                    if (res.code == 1) {
                        var data6 = res.data;
                        var data7 = {
                            list3: data6
                        };
                        var html = template("tel3", data7);
                        $("#quyus").html(html)
                    }
                }
            })

            // $("#shengs").val(list2.provinceName);
            // $("#shis").val(list2.cityName);
            // $("#quyus").val(list2.countyName);
            for (var i = 0; i < $('#shis').children().length; i++) {
                if ($('#shis').children().eq(i).html() == list2.cityName) {
                    $('#shis').children().eq(i).attr('selected', true);
                    Getqu($('#shis').children().eq(i).attr('data-id2'), list2);
                }
            }
        }
    })

}

function Getqu(id, list2) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/region/findid", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            parentId: id
        },
        success: function(res) {
            if (res.code == 1) {
                var data6 = res.data;
                var data7 = {
                    list3: data6
                };
                var html = template("tel3", data7);
                $("#quyus").html(html)
            }

            // $("#shengs").val(list2.provinceName);
            // $("#shis").val(list2.cityName);
            // $("#quyus").val(list2.countyName);
            for (var i = 0; i < $('#quyus').children().length; i++) {
                if ($('#quyus').children().eq(i).html() == list2.regionName) {
                    // console.log('success')
                    $('#quyus').children().eq(i).attr('selected', true);
                }
            }
        }
    })

}