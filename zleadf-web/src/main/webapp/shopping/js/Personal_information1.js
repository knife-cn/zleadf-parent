$(function() {
    $(".dian5").click(function() {

        if ($(this).attr("class") == "dian5 huay5") {
            $(this).addClass("huaw5").siblings(".dian5").removeClass("huaw5");
            $(this).removeClass("huay5").siblings(".dian5").addClass("huay5");
        } else {
            $(this).addClass("huay5").siblings(".dian5").removeClass("huay5");
            $(this).removeClass("huaw5").siblings(".dian5").addClass("huaw5");
        }

    });

    function disposeDate(dateTime) {
        var getTime = new Date(dateTime);
        dateTime = getTime.toLocaleDateString();
        return dateTime;
    }


    //后台获取的id
    var shengId
    var shiId
    var quyuId

    //提交保存时的字段
    var codeS
    var codeC
    var codeQ


    var formatTime
        //获取 详情
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/member/memberInfo", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        success: function(res) {
            if (res.code == 1) {
                var data = res.data
                    // var shijij=data.member.birthday.time;
                if (data.member.birthday == null) {

                } else {
                    formatTime = disposeDate(data.member.birthday.time);
                    var ff = formatTime.split("/");
                    var fffshijian = '';
                    if (ff.length > 1) {
                        fffshijian = ff[0] + "-" + ff[1] + "-" + ff[2];
                        $("#h3Ele").val(fffshijian);
                    } else if (ff.length == 1) {
                        $("#h3Ele").val(ff[0]);
                    }

                }

                $("#hao").html(data.member.phone);
                $("#xin").val(data.member.nickName);
                $("#dizhi").val(data.member.address);



                codeS = data.member.provinceId
                codeC = data.member.cityId
                codeQ = data.member.regionId


                if (data.member) {
                    if (data.member.gender == 0) {

                        $(".dian5").eq(0).addClass("huaw5");
                        $(".dian5").eq(0).removeClass("huay5");

                    } else if (data.member.gender == 1) {


                        $(".dian5").eq(0).addClass("huay5").siblings(".dian5").removeClass("huay5");
                        $(".dian5").eq(0).removeClass("huaw5").siblings(".dian5").addClass("huaw5");

                    } else if (data.member.gender == 2) {

                        $(".dian5").eq(0).addClass("huaw5").siblings(".dian5").removeClass("huaw5");
                        $(".dian5").eq(0).removeClass("huay5").siblings(".dian5").addClass("huay5");

                    }
                }
                //调取获取省市区代码
                shengId = data.member.provinceId;
                shiId = data.member.cityId;
                quyuId = data.member.regionId;
                getSheng(shengId, shiId, quyuId)

            }
        }
    })

    function getP(shengId, shiId, quyuId) {

        //获取省
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findprovince", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            success: function(res) {
                if (res.code == 1) {
                    var data = res.data
                    var data2 = {
                        list: data
                    }
                    var html = template("tel11", data2)
                    $("#shengs").html(html)
                }

                data.forEach(function(ele, index) {
                    //遍历获取对应的省市区回显到页面
                    if (ele.regionCode == shengId) {
                        $("#shengs").val(ele.regionName)
                        var idi = ele.id
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
                                    var data5 = res.data
                                    var ids = data5[0].id
                                    var data3 = {
                                        list2: data5
                                    }
                                    var html = template("tel12", data3)
                                    $("#shis").html(html)
                                }
                                data5.forEach(function(ele, index) {
                                    if (ele.regionCode == shiId) {
                                        $("#shis").val(ele.regionName)

                                        var idsi = ele.id
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
                                                    var data6 = res.data
                                                    var data7 = {
                                                        list3: data6
                                                    }
                                                    var html = template("tel13", data7)
                                                    $("#quyus").html(html)
                                                }
                                                data6.forEach(function(ele, index) {
                                                    if (ele.regionCode == quyuId) {
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
            }
        })
    }



    //获取市
    $("#shengs").change(function() {
        codeS = $(this).children('option:selected').attr("data-code")
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
                    var data5 = res.data
                    codeC = data5[0].regionCode
                    var ids = data5[0].id
                    var data3 = {
                        list2: data5
                    }
                    var html = template("tel12", data3)
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
                            var data6 = res.data
                            codeQ = data6[0].id
                            var data7 = {
                                list3: data6
                            }
                            var html = template("tel13", data7)
                            $("#quyus").html(html)
                        }
                    }
                })
            }
        })


    })


    //获取区
    $("#shis").change(function() {
        var id2 = $(this).children('option:selected').attr("data-id2")
        var shi = $(this).children('option:selected').val()
        codeC = $(this).children('option:selected').attr("data-code")
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
                    var data6 = res.data
                    var data7 = {
                        list3: data6
                    }
                    var html = template("tel13", data7)
                    $("#quyus").html(html)
                }
            }
        })
    })

    function getSheng(shengId, shiId, quyuId) {
            //获取省
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findprovince", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            success: function(res) {
                if (res.code == 1) {
                    var data = res.data
                    var data2 = {
                        list: data
                    }
                    var html = template("tel11", data2)
                    $("#shengs").html(html)

                    if (shengId) {
                        for (var i = 0; i < res.data.length; i++) {
                            if (shengId == res.data[i].regionCode) {
                                shengId = res.data[i].id;
                            }
                        }
                    } else {
                        shengId = 2; // 默认北京
                    }

                    getQu(shengId, shiId, quyuId);
                }

            }
        })
    }

    function getQu(shengId, shiId, quyuId) {
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findid", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            data: {
                parentId: shengId
            },
            success: function(res) {
                if (res.code == 1) {
                    var data5 = res.data
                    codeC = data5[0].regionCode
                    var ids = data5[0].id
                    var data3 = {
                        list2: data5
                    }
                    var html = template("tel12", data3)
                    $("#shis").html(html);

                    if (!shiId) {
                        shiId = res.data[0].id;
                    } else {
                        for (var i = 0; i < res.data.length; i++) {
                            if (shiId == res.data[i].regionCode) {
                                shiId = res.data[i].id;
                            }
                        }
                    }
                    getShi(shengId, shiId, quyuId);
                }

            }
        })
    }

    function getShi(shengId, shiId, quyuId) {
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findid", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            data: {
                parentId: shiId
            },
            success: function(res) {
                if (res.code == 1) {
                    var data6 = res.data
                    var data7 = {
                        list3: data6
                    }
                    var html = template("tel13", data7)
                    $("#quyus").html(html)
                }
            }
        })
    }

    $("#quyus").change(function() {
        codeQ = $(this).children('option:selected').attr("data-code")
        var qu = $(this).children('option:selected').val()
    })

    $(".bao5").click(function() {
        var get = 0;

        if ($(".dian5").eq(0).attr("class") == "dian5 huay5") {
            get = 1;
        } else if ($(".dian5").eq(1).attr("class") == "dian5 huay5") {
            get = 2;
        }

        var username = $("#xin").val();
        var birthday = $("#h3Ele").val();

        var phone = $("#hao").val();
        var address = $("#dizhi").val();
        var sheng0 = $("#shengs").val()
        var shi0 = $("#shis").val()
        var qu0 = $("#quyus").val()
        var em = {
                username: '小花',
                NickName: username,
                Gender: get,
                Birthday: birthday,
                phone: phone,
                // provinceName:sheng0,
                // cityName:shi0,
                // regionName:qu0,
                Address: address,
                ProvinceId: codeS,
                cityId: codeC,
                RegionId: codeQ

            }
        $.ajax({
            type: "post",
            url: "/zlead/member/updateVipe",
            data: em,
            dataType: "json",
            async: true,
            success: function(data) {
                if (data.success == true) {
                    $(".tishi2").show().delay(3000).hide(0)
                        // window.location.reload();
                } else {
                    $(".tishi3").show().delay(3000).hide(0)
                }
            }
        });



    })





})