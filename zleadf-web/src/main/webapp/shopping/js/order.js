var dAddressId = ""; //用于提交订单，点击li变色，这个地址就用于提交订单用

$(function() {
    var list02;
    var vType;
    var id; //省-id

    if (window.localStorage.getItem("buyCar") == "buyCar") { //购物车进入本页面
        var priM = window.localStorage.getItem("priM"); //还需支付
        var priM2 = window.localStorage.getItem("priM2"); //原价
        var priM3 = window.localStorage.getItem("priM3"); //优惠
        var priM4 = window.localStorage.getItem("priM4"); //运费
        var priM5 = 0; //运费----本版本0

        if (priM2 != undefined) {
            $(".oldPrice").text(priM2) //原价
        }
        // if (priM != undefined) {
        //     $(".all_order").text(priM) //还需支付
        // }
        if (priM3 != undefined) {
            $(".discount").text(priM3) //优惠
        }
        $(".goFare").text(priM5); //运费（暂定为0）

        //获取本地存储数据
        var list = localStorage.getItem("datas");
        var list2 = JSON.parse(list);
        var obj = {
            objA: list2
        };
        var res = template("tel22", obj);
        $("#order_pay").append(res);

    } else { //商品详情页面进入本页面
        var list = localStorage.getItem("datas");
        list02 = JSON.parse(list);
        var obj1 = list02;
        obj1.thisGoodsAttrs = "";
        obj1.thisGoodsAttrArr = [];
        if (obj1.thisGoodsAttr.length > 0) {
            for (var i = 0; i < obj1.thisGoodsAttr.length; i++) {
                obj1.thisGoodsAttrArr.push(obj1.thisGoodsAttr[i].attrValue);
            }
            obj1.thisGoodsAttrs = obj1.thisGoodsAttrArr.join(",");
        } else {
            obj1.thisGoodsAttrs = "";
        }
        var res = template("tel23", obj1);
        $("#order_pay").append(res);

        //    最底部价格赋值
        // var price = Math.abs(list02.discount)
        // price = Math.round(price)

        // 优惠额度=原价-折后价
        var price = (list02.originalPrice - list02.totalPrice).toFixed(2);

        // 还需支付=折后价,本期没有运费
        var price_1 = list02.totalPrice.toFixed(2);

        //勿删
        // $(".oldPrice").text(list02.originalPrice); //原价
        // $(".discount").text(price); //优惠
        // $(".goFare").text(list02.trans) //运费（暂定为0）
        // $(".all_order").text(price_1); //还需支付


    };
    // 总价格
    var myall = 0;
    for (var i = 0; i < $('.my_allPrice').length; i++) {
        // console.log($('.my_allPrice').eq(i).html())
        myall += parseFloat($('.my_allPrice').eq(i).html());
    }

    $(".all_order").text(myall.toFixed(2));

    //点击logo回去首页
    $(".logo").click(function() {
        window.location.href = "index.html"
    });

    $("#phone").keyup(function() {
        if ($(this).val().length >= 11) {
            $(this).val($(this).val().substr(0, 11));
        }
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
    });

    // 添加地址
    $(".add_price2").click(function() {
        $("#phone").val("");
        $("#name").val("");
        $("#detail_add").val("");
        $("#dialogs").show();
        $(".input_addr").show();
        $("#dialog").hide();
        $("#shengs").val("请选择");
        $("#shis").val("请选择");
        $("#quyus").val("请选择");
        vType = 1;
        $(".tipsN2").text("");
    });

    // 添加地址
    $(".address_list").on('click', '.add_price1', function() {
        $("#phone").val("");
        $("#name").val("");
        $("#detail_add").val("");
        $("#dialogs").show();
        $(".input_addr").show();
        $("#dialog").hide();
        $("#shengs").val("请选择");
        $("#shis").val("请选择");
        $("#quyus").val("请选择");
        vType = 1;
        $(".tipsN2").text("");
    });

    //编辑地址
    var data122 = "";
    var datas;
    var editID = "";
    var defaults = "";
    $(".address_list").on("click", ".editNew", function() {
        getSheng();
        datas = $(this).attr("data-datas");
        data122 = JSON.parse(datas);
        id = data122.id;
        $("#dialogs").show();
        $(".input_addr").show();
        $("#dialog").hide();
        $("#phone").val(data122.phone);
        $("#name").val(data122.memberName);
        $("#shengs").val(data122.provinceName);
        $("#shis").val(data122.cityName);
        $("#quyus").val(data122.countyName);
        $("#detail_add").val(data122.detailAddress);
        vType = 2;
        $(".tipsN2").text("");
        editID = $(this).attr("data-id");
        defaults = $(this).attr("data-default");
        if (defaults == 1) {
            $(".pandan").addClass("mr").removeClass("nomr")
        } else if (defaults == 0) {
            $(".pandan").addClass("nomr").removeClass("mr")
        }
        return false;
    });

    //添加或编辑地址
    $(".makeSure").click(function() {
        $(this).unbind('click');
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
        if (memberName == "") {
            $(".tipsN2").html("请输入收货人姓名");
            $(".tipsN2").show();
            $("#name").css("border", "1px solid #CC5B4D");
            $("#phone").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border", "1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border", "1px solid rgba(229, 229, 229, 1)")
        } else if (phone == "") {
            $(".tipsN2").html("请输入收货人联系方式");
            $(".tipsN2").show();
            $("#name").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border", "1px solid #CC5B4D");
            $(".select").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border", "1px solid rgba(229, 229, 229, 1)")
        } else if (phone.length != 11) {
            $(".tipsN2").html("请输入正确的收货人联系方式");
            $(".tipsN2").show();
            $("#name").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border", "1px solid #CC5B4D");
            $(".select").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border", "1px solid rgba(229, 229, 229, 1)")
        } else if (sheng0 == "" || shi0 == "" || qu0 == "" || sheng0 == "请选择" || shi0 == "请选择" || qu0 == "请选择") {
            $(".tipsN2").html("请输入收货人所在区域");
            $(".tipsN2").show();
            $("#name").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border", "1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border", "1px solid #CC5B4D");
            $("#detail_add").css("border", "1px solid rgba(229, 229, 229, 1)")
        } else if (detail_add == "") {
            $(".tipsN2").html("请输入详细地址");
            $(".tipsN2").show();
            $("#name").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border", "1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border", "1px solid #CC5B4D")
        } else {
            $("#name").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#phone").css("border", "1px solid rgba(229, 229, 229, 1)");
            $(".select").css("border", "1px solid rgba(229, 229, 229, 1)");
            $("#detail_add").css("border", "1px solid rgba(229, 229, 229, 1)");
            $(".tipsN2").html();
            var vData = {};
            if (vType == 1) {
                vData = {
                    memberName: memberName,
                    phone: phone,
                    provinceName: sheng0,
                    cityName: shi0,
                    countyName: qu0,
                    detailAddress: detail_add,
                    isDefault: isDefault, //默认地址 '1' ,非默认 ‘0’
                    type: vType, //编辑为2，新增为1
                }
            } else if (vType == 2) {
                vData = {
                    memberName: memberName,
                    phone: phone,
                    provinceName: sheng0,
                    cityName: shi0,
                    countyName: qu0,
                    detailAddress: detail_add,
                    isDefault: isDefault, //默认地址 '1' ,非默认 ‘0’
                    type: vType, //编辑为2，新增为1
                    addressId: editID
                }
            }
            $.ajax({
                type: "post", //提交请求的方式
                cache: true, //是否有缓存
                url: "/zlead/memaddr/addOrUpdateMemberAddress", //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                data: vData,
                success: function(res) {
                    if (res.code == 1) {
                        window.location.href = "orderList.html"
                    } else {
                        $("body").css('overflow-y', '');
                        $("#dialogs").hide();
                        $(".input_addr").hide()
                        $(".msg2").text(res.message);
                        $(".meng2").show();
                        $(".popupMsg").show();
                    }
                }
            })
        }
    });

    $(".pandan").click(function() {
        if ($(this).attr("class") == "pandan nomr") {
            $(this).removeClass("nomr").addClass("mr");
        } else {
            $(this).addClass("nomr").removeClass("mr");
        }
    });

    //  更多支付类型
    $("#more_type").click(function() {
        $("#box").css("width", "1000px");
        $(this).hide();
        $(this).siblings().show()
    });
    //  收起
    $("#more_type1").click(function() {
        $("#box").css("width", "515px");
        $(this).hide();
        $(this).siblings().show()
    });

    //更多地址
    $(".more_ad").click(function() {
        $("#address_list").css("height", "auto");
        $(this).hide();
        $(this).siblings().show()
    });
    //  收起
    $(".more_ad1").click(function() {
        $("#address_list").css("height", "120px");
        $(this).hide();
        $(this).siblings().show()
    });
    //  选择扫码支付
    $("#saoma").click(function() {
        $("body").css('overflow-y', 'hidden');
        $("#dialogs").show();
        $("#dialog").show();
        $(".input_addr").hide()
    });
    //  关闭弹窗
    $("#qu").click(function() {
        $("body").css('overflow-y', '');
        $("#dialogs").hide()
    });
    //关闭弹窗
    $("#qu2").click(function() {
        $("body").css('overflow-y', '');
        $("#dialogs").hide();
        $(".input_addr").hide()
    });

    $('.input_addr .img').on('click', function() {
        $("body").css('overflow-y', '');
        $("#dialogs").hide();
        $(".input_addr").hide()
    });

    $('.bottom_b #qu').on('click', function() {
        $("body").css('overflow-y', '');
        $("#dialogs").hide();
        $(".input_addr").hide()
    });

    //  确定
    $("#fo").click(function() {
        // $("#dialogs").hide()
    });

    //  更多地址切换背景
    $("#address_list").on("click", "li", function(e) {
        $(this).css("background-color", "rgba(244,246,249,1)");
        $(this).siblings().css("background-color", "#fff");
        $("#active_line").show();
        dAddressId = $(this).find(".tel_msg").attr("data-id");
        $(this).addClass("mActive").siblings().removeClass("mActive");
    });

    //一期单一支付
    $("#enterLi").on("click", function(e) {
        $(this).css({ "border-color": "rgba(253,87,0,1)", "background-image": "url(../../shopping/img/order/zhiwei_ic_choose.png)", "background-repeat": "no-repeat", "backgroundPosition": "right bottom" });
        $(this).css("background-image", "url(../../shopping/img/order/zhiwei_ic_choose.png)");
        $(this).siblings().css("background-image", "");
        $(this).siblings().css("border-color", "rgba(206,208,218,1)");
        $(this).addClass("choice");
    });

    //线下支付鼠标移入
    $("#enterLi").mouseenter(function() {
        $("#pShow").show()
    });

    //线下支付鼠标移出
    $("#enterLi").mouseleave(function() {
        $("#pShow").hide()
    });

    //获取所有地址
    function getAll() {
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/memaddr/getAllAddress", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            async: true, //是否异步
            success: function(res) {
                if (res.data != "" && res.data != null && res.data != undefined) {
                    window.localStorage.setItem("addressLength", res.data.length); //有收货人信息
                } else {
                    window.localStorage.setItem("addressLength", "0"); //有收货人信息
                }
                if (res.code == 1) {
                    var datas = res.data;
                    if (datas.length > 3) {
                        $(".more_ad").show()
                    } else {
                        $(".more_ad").hide()
                    }
                    window.localStorage.setItem("allAddressList", JSON.stringify(res.data));

                    var str = template("tel", res);
                    $("#address_list").append(str)
                } else {
                    var res = "";
                    var str = template("tel", res);
                    $("#address_list").append(str)
                }
            },
            error: function(request) { //请求出错
            }
        })
    }
    getAll();



    // 获取省  对于编辑地址需要根据后台返的code码匹配回显相应的省市区
    function getSheng() {
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/region/findprovince", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            success: function(res) {
                if (res.code == 1) {
                    var data = res.data
                    var str = "";
                    for (var i = 0; i < data.length; i++) {
                        str += '<option data-id="' + data[i].id + '" className="option" id="sheng">' + data[i].regionName + '</option>'
                    }
                    str = ('<option value="请选择" selected="selected">' + "请选择" + '</option>') + str;
                    $("#shengs").html(str);
                    $("#shis").html('<option value="请选择" selected="selected">' + "请选择" + '</option>');
                    $("#quyus").html('<option value="请选择" selected="selected">' + "请选择" + '</option>')
                }

                if (data122 !== "") {
                    data.forEach(function(ele, index) {
                        if (ele.regionCode == data122.provinceId) { //根据code码匹配
                            $("#shengs").val(ele.regionName) //输入框回显省
                            var idi = ele.id //接收省对应的id,查询出这个省下面的所有市
                            $.ajax({
                                type: "post", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/zlead/region/findid", //访问servlet的路径
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                data: {
                                    parentId: idi //这里id为上面接收到的
                                },
                                success: function(res) {
                                    if (res.code == 1) {
                                        var data5 = res.data
                                        var ids = data5[0].id
                                        var data3 = {
                                            list2: data5
                                        }
                                        var html = template("tel12", data3)
                                        $("#shis").html(html) // 下拉框市的所有数据
                                    }
                                    data5.forEach(function(ele, index) {
                                        if (ele.regionCode == data122.cityId) { //同上。拿到的code码匹配
                                            $("#shis").val(ele.regionName) //根据code匹配到市的名称

                                            var idsi = ele.id //根据这个id查询所有区的数据
                                            $.ajax({
                                                type: "post", //提交请求的方式
                                                cache: true, //是否有缓存
                                                url: "/zlead/region/findid", //访问servlet的路径
                                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                                data: {
                                                    parentId: idsi //  上面匹配的市的id
                                                },
                                                success: function(res) {
                                                    if (res.code == 1) {
                                                        var data6 = res.data
                                                        var data7 = {
                                                            list3: data6
                                                        }
                                                        var html = template("tel13", data7)
                                                        $("#quyus").html(html) // 区的下拉框所有选项
                                                    }
                                                    data6.forEach(function(ele, index) {
                                                        if (ele.regionCode == data122.countyId) {
                                                            $("#quyus").val(ele.regionName) //根据code匹配到的区名称
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
            }
        });
    }

    getSheng(); //页面一加载调用上面的方法，
    //获取市
    $("#shengs").change(function() { //选择省时给他加的change事件监听。
        if ($(this).children('option:selected').val() == "请选择") {
            $("#shis").html('<option value="请选择" selected="selected">' + "请选择" + '</option>')
            $("#quyus").html('<option value="请选择" selected="selected">' + "请选择" + '</option>')
        } else {
            id = $(this).children('option:selected').attr("data-id") // 获取选中的id  ，data-id为自定义属性、页面中绑定到的id值
            var sheng = $(this).children('option:selected').val() //获取选择的省的值
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
                        var ids = data5[0].id //查询到的市的第一条数据的id，便于在不选择市的情况下，加载区的值。
                        var data3 = {
                            list2: data5 //将data5这个数组赋值给一个对象的属性、这是模板渲染的规则
                        }
                        var html = template("tel12", data3)
                        $("#shis").html(html) //模板数据渲染市
                    }
                    $.ajax({
                        type: "post", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/zlead/region/findid", //访问servlet的路径
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        data: {
                            parentId: ids //这个ids为存的 ，这个ajax就是查询到区的数据
                        },
                        success: function(res) {
                            if (res.code == 1) {
                                var data6 = res.data
                                var data7 = {
                                    list3: data6
                                }
                                var html = template("tel13", data7)
                                $("#quyus").html(html) //区域的模板数据
                            }
                        }
                    })
                }
            })
        }

    });
    //获取区  这里是在选择市的时候给select加的change事件，监听选择的市
    $("#shis").change(function() {
        var id2 = $(this).children('option:selected').attr("data-id2") //选中市的id。
        var shi = $(this).children('option:selected').val() //选中的市的值

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
                    };
                    var html = template("tel13", data7);
                    $("#quyus").html(html) //区域的数据模板
                }
            }
        })
    });

    //监听区域的select
    $("#quyus").change(function() {
        var qu = $(this).children('option:selected').val()
    });

    //弹窗关闭按钮
    $(".know").click(function() {
        $(".meng2").hide();
        $(".popupMsg").hide();
    });
    //弹窗关闭按钮--X
    $(".close").click(function() {
        $(".meng2").hide();
        $(".popupMsg").hide();
    });

    // 提交订单
    $("#sub_btn").click(function() {
        //逻辑--如果有默认地址，可以不选择；没有默认地址，必须选择一个



        var allA = JSON.parse(window.localStorage.getItem("allAddressList"));


        if (allA == "") { //没有地址
            $(".msg2").text("请设置收货人地址");
            $(".meng2").show();
            $(".popupMsg").show();
        } else { //有地址
            var ifT = false; //是否有默认地址
            for (var i = 0; i < allA.length; i++) {
                if (allA[i].isDefault == 1) {
                    ifT = true;
                    dAddressId = allA[i].id;
                }
            }
            //是否选择地址
            var allA2 = $("#address_list").find(".sAddress");
            var ifT2 = false;
            for (var j = 0; j < allA2.length; j++) {
                if (allA2.eq(j).hasClass("mActive") == true) {
                    ifT2 = true;
                    dAddressId = allA2.eq(j).find(".tel_msg").attr("data-id");
                }
            }
        }

        if (window.localStorage.getItem("addressLength") < 1) {
            $(".msg2").text("请添加收货人信息");
            $(".meng2").show();
            $(".popupMsg").show();
        } else if (ifT == false && ifT2 == false) { //无默认地址
            $(".msg2").text("请选择收货人地址");
            $(".meng2").show();
            $(".popupMsg").show();
        } else if ($("#enterLi").hasClass("choice") != true) {
            $(".msg2").text("请选择支付方式");
            $(".meng2").show();
            $(".popupMsg").show();
        } else {
            if (window.localStorage.getItem("buyCar") == "buyCar") { //购物车进来
                var cartIds = window.localStorage.getItem("buyCarP");
                var shopIds = window.localStorage.getItem("buyCarPC");
                $.ajax({
                    type: "post", //提交请求的方式
                    cache: true, //是否有缓存
                    data: {
                        shopIds: shopIds,
                        addressId: dAddressId,
                        cartIds: cartIds,
                        buyType: "1",
                        storeBuyType: "1",
                        orderType: "0",
                    },
                    url: "/zlead/order/cartAddOrder", //访问servlet的路径
                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                    async: true, //是否异步
                    success: function(res) {
                        if (res.code == 1) {
                            window.location = "orderSuccess.html"
                        } else {
                            $(".msg2").text(res.message);
                            $(".meng2").show();
                            $(".popupMsg").show();
                        }
                    },
                    error: function(request) { //请求出错
                    }
                })
            } else { //其余页面,譬如详情页，
                if (window.localStorage.getItem("actIdN")) { //活动商品详情页
                    //活动商品详情页页面跳转过来
                    $.ajax({
                        type: "post", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/zlead/order/addActOrder", //访问servlet的路径
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        async: true, //是否异步
                        data: {
                            // shopId:list2.shopId,
                            buyNum: list02.buyNum,
                            goodsId: list02.goods.id,
                            addressId: dAddressId,
                            buyType: "1", //购买方式 目前只传 1
                            storeBuyType: "1", //取货类型  0自提 1送货
                            orderType: '0', //订单类型 订单类型：0商品订单，1会员卡订单，2积分商品订单，3实体商家入驻订单，4积分换订单，5积分购订单，6虚拟商家入驻订单,7附近商家订单 （目前只传0）
                            actId: window.localStorage.getItem("actIdN")
                        },
                        success: function(res) {
                            if (res.code == 1) {
                                window.location = "orderSuccess.html"
                            } else {
                                $(".msg2").text(res.message);
                                $(".meng2").show();
                                $(".popupMsg").show();
                            }
                        },
                        error: function(request) { //请求出错
                        }
                    })
                } else { //正常商品详情页
                    $.ajax({
                        type: "post", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/zlead/order/addOrder", //访问servlet的路径
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        async: true, //是否异步
                        data: {
                            // shopId:list2.shopId,
                            buyNum: list02.buyNum,
                            goodsId: list02.goods.id,
                            addressId: dAddressId,
                            buyType: "1", //购买方式 目前只传 1
                            storeBuyType: "1", //取货类型  0自提 1送货
                            orderType: '0' //订单类型 订单类型：0商品订单，1会员卡订单，2积分商品订单，3实体商家入驻订单，4积分换订单，5积分购订单，6虚拟商家入驻订单,7附近商家订单 （目前只传0）
                        },
                        success: function(res) {
                            if (res.code == 1) {
                                window.location = "orderSuccess.html"
                            } else {
                                $(".msg2").text(res.message);
                                $(".meng2").show();
                                $(".popupMsg").show();
                            }
                        },
                        error: function(request) { //请求出错
                        }
                    })
                }


            }
        }
    });


}); //$

function moneyCal() {
    //获取计算后的金额
    $.ajax({
        type: "post",
        cache: true,
        url: "../zlead/tmembera",
        dataType: "json",
        async: true,
        success: function(res) {
            if (res.code == 1) {

            }
        },
        error: function(request) { //请求出错
        }
    })
}