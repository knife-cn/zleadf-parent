$(function() {
    // var oldAddressPhone = window.localStorage.getItem("oldAddressPhone");
    // var oldAddressPhoneArr = oldAddressPhone.split(",");

    $(".tipsN").text("");

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
    $("#qu").click(function() {
        $(".meng").hide();
        $(".xian").hide();
        window.location.href = "left.html#dz"
    });
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
                // var data2 = {
                //     list: data
                // };
                // var html = template("tel", data2);

                var str = "";
                for(var i =0;i<data.length;i++){
                    str += '<option data-id="'+data[i].id+'" className="option" id="sheng">'+data[i].regionName+'</option>'
                }
                str = ('<option value="请选择" selected="selected">' + "请选择" + '</option>') + str;
                $("#shengs").html(str)
                $("#shis").html('<option value="请选择" selected="selected">' + "请选择" + '</option>');
                $("#quyus").html('<option value="请选择" selected="selected">' + "请选择" + '</option>')
            }
        }
    });

    //获取市
    $("#shengs").change(function() {
        if($(this).children('option:selected').val() == "请选择"){
            $("#shis").html('<option value="请选择" selected="selected">' + "请选择" + '</option>')
            $("#quyus").html('<option value="请选择" selected="selected">' + "请选择" + '</option>')
        }else{
            var id = $(this).children('option:selected').attr("data-id");
            var sheng = $(this).children('option:selected').val();
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
        }

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
    });

    $("#quyus").change(function() {
        var qu = $(this).children('option:selected').val();
    });

    $("#phone").keyup(function () {
        if ($(this).val().length >= 11) {
            $(this).val($(this).val().substr(0, 11));
        }
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
    });

    //  提交保存收货地址
    $("#fo2").click(function() {
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
            //判断手机号是否重复交给后端
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
                    type: '1'
                },
                success: function(res) {
                    if (res.code == 1) {
                        window.location.href = "left#dz"
                    }else{
                        alert(res.message);
                    }
                }
            })

        }
    })

});