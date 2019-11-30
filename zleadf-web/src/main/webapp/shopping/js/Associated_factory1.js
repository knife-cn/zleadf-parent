$(function() {
    //继续关联
    // $(".close2").click(function () {
    //    $(".popBox").hide();
    // });
    //获取未关联的工厂
    $(".img").click(function() {
        window.location.href = "index";
    });

    $("#fo").click(function() {
        // window.location.href="index";
        $('.popBox').show();
        $('.popBox').find('span').eq(0).click(function() {
            window.location.href = "index";
        });
        $('.popBox').find('span').eq(1).click(function() {
            $('.popBox').hide();
        })
    });

    $('#band').select2({
        placeholder: '请选择想关联的品牌',
        language: {
            noResults: function(params) {
                return "暂时无法关联，请联系厂家管理员";
            }
        }
    });

    $('#list').select2({
        placeholder: '请选择想关联的系列',
        language: {
            noResults: function(params) {
                return "暂时无法关联，请联系厂家管理员";
            }
        }
    });

    var factoryId2 = ""; //工厂名称id

    $("#factoryD,#vcode").on("change", function() {
        $("#band").html("");
        $("#list").html("");
        //苏州富源工具经销
        var vcodeVal = $("#vcode").val();
        var factoryVal = $("#factoryD").val();

        if (vcodeVal != "" && factoryVal != "") {
            $.ajax({
                type: "GET",
                cache: false,
                url: "../factory/facRelevancy?vcode=" + vcodeVal + "&factoryName=" + factoryVal + "&t_=" + Math.random(),
                dataType: "json",
                contentType: "application/json",
                success: function(res) {
                    if (res.code == 1) {
                        factoryId2 = res.data;
                        // //品牌
                        $.ajax({
                            type: "GET",
                            cache: false,
                            url: "/factory/checkvcode?vcode=" + vcodeVal + "&factoryId=" + factoryId2 + "&t_=" + Math.random(),
                            dataType: "json",
                            contentType: "application/json",
                            success: function(res) {
                                if (res.code == 1) {
                                    brandList(factoryId2)
                                } else {
                                    $("#band").html("");
                                    $("#list").html("");
                                    alert(res.message)
                                }
                            }
                        })
                    } else {
                        $("#band").html("");
                        $("#list").html("");
                        alert(res.message)
                    }
                }
            })
        }


    });

    var brandList = function(factoryId) {
        $.ajax({
            type: "GET",
            cache: false,
            url: "/factory/prdbrand?factoryId=" + factoryId + "&t_=" + Math.random(),
            dataType: "json",
            contentType: "application/json",
            success: function(res) {
                if (res.success) {
                    var html = "<option value=''></option>";
                    for (var i = 0; i < res.data.length; i++) {
                        html += "<option value='" + res.data[i].id + "'>" + res.data[i].name + "</option>";
                    }
                    $("#band").html(html).select2({
                        placeholder: '请选择想关联的品牌',
                        allowClear: true,
                        minimumResultsForSearch: -1
                    });
                } else {
                    alert(res.message)
                }
            }
        })
    };

    $("#band").on("change", function() {
        var bandIds = $("#band").val();
        if (bandIds == null || bandIds.length == 0 || bandIds == "") {
            $("#band").val()
        } else {
            if (bandIds[0] == "") {
                bandIds.splice(0, 1);
            }
            var bids = bandIds.join(",");
            var factoryId = $("#factoryD").val();
            if (bandIds && factoryId) {
                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "/factory/prdlist?bids=" + bids + "&factoryId=" + factoryId2 + "&t_=" + Math.random(),
                    dataType: "json",
                    contentType: "application/json",
                    success: function(res) {
                        if (res.success) {
                            var listData = [];
                            for (var i = 0; i < res.data.length; i++) {
                                listData.push({
                                    id: res.data[i].id,
                                    text: res.data[i].name,
                                    bandId: res.data[i].band_id
                                })
                            }
                            //$('#list')先清空再初始化  不然会有缓存
                            $("#list").html('');
                            $("#list").select2({
                                data: listData,
                                placeholder: '请选择想关联的系列',
                                allowClear: true,
                                minimumResultsForSearch: -1
                            });
                        } else {
                            alert(res.message)
                        }
                    }
                })
            }
        }


    });

    //点击提交申请按钮
    $("#qu").on("click", function() {
        var vcode = $("#vcode").val();
        var factoryId = $("#factoryD").val();
        var barry = $("#band").select2('data');
        var larry = $("#list").select2('data');
        if (!vcode) {
            alert("请输入关联码");
            return false;
        }
        if (!factoryId) {
            alert("请选择工厂");
            return false;
        }
        if (barry.length == 0) {
            alert("请选择品牌");
            return false;
        }
        if (larry.length == 0) {
            alert("请选择系列");
            return false;
        }
        var data = {
            vcode: vcode,
            factoryId: factoryId2
        };
        var agentbands = [];
        for (var i = 0; i < barry.length; i++) {
            var ab = {};
            ab.bandId = parseInt(barry[i].id);
            ab.bandName = barry[i].text;
            var arryids = [];
            var arryNames = [];
            for (var j = 0; j < larry.length; j++) {
                var bid = parseInt(larry[j].bandId);
                var lid = parseInt(larry[j].id);
                var lname = larry[j].text;
                if (bid === ab.bandId) {
                    arryids.push(lid);
                    arryNames.push(lname);
                }
            }
            ab.listIds = arryids.join(",");
            ab.listName = arryNames.join(",");
            agentbands.push(ab);
        }
        data.agentbands = agentbands;
        $.ajax({
            type: "POST",
            url: "/factory/relationapply",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function(res) {
                if (res.success) {
                    alert(res.message);
                    window.location.href = "index";
                } else {
                    alert(res.message);
                    window.location.href = "index";
                }
            }
        })
    });

    // 新增获取焦点判断是否有关联码
    $('#vcode').focus();
    $('#factoryD').focus(function() {
        if ($('#vcode').val() == '') {
            alert('请输入关联码');
            $(this).blur();
            window.location  = 'Associated_factory1'
        }
    });

    $('.select2').click(function() {
        if ($('#vcode').val() == '') {
            $('.select2').removeClass('select2-container--focus');
            $('.select2-container').hide();
            $(this).parents('.scroll-son').find('.select2').show();
            alert('请输入关联码');
            window.location = 'Associated_factory1'
        }
    })

});

// 页面离开
window.onbeforeunload = function(e) {　　
    var e = window.event || e;　
    if ($('#vcode').val() != '' || $('#factoryD').val() != '') {
        e.returnValue = ("您所查找的网页要使用已输入的信息。返回此页可能需要重复已进行的所有操作。是否要继续操作？");
    }　
};
