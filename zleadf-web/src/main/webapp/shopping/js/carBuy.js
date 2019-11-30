$(function() {
    //	生成列表
    shoppingCartGoods();

    //全部，共3件
    $(document).on("click", ".cAll", function() {
        //所有商品的选择状态
        var arrCPA = [];
        var allC = $(".car_content").find(".choiceP");
        var allC2 = $(".content").find(".choiceC");
        var srcA = allC.attr("src");
        var srcAT = $(this).attr("src");

        if (srcAT.search(/Checked_default\.png/) > -1) { //已选中
            $(".btn_car2").css("background", "#EFEFEF");
            $(this).attr("src", srcAT.replace("Checked_default.png", "Checked_default2.png"));
            for (var i = 0; i < allC.length; i++) {
                allC.attr("src", srcA.replace("Checked_default.png", "Checked_default2.png"));
                allC.eq(i).attr("data-p", "no");
                arrCPA.push(allC.eq(i).attr("data-p"))
            }
            for (var g = 0; g < allC2.length; g++) {
                allC2.eq(g).attr("data-if", "no");
            }
        } else if (srcAT.search(/Checked_default2\.png/) > -1) { //未选中
            $(".btn_car2").css("background", "#FD5700");
            $(this).attr("src", srcAT.replace("Checked_default2.png", "Checked_default.png"));
            for (var i = 0; i < allC.length; i++) {
                allC.attr("src", srcA.replace("Checked_default2.png", "Checked_default.png"));
                allC.eq(i).attr("data-p", "yes");
                arrCPA.push(allC.eq(i).attr("data-p"))
            }
            for (var k = 0; k < allC2.length; k++) {
                allC2.eq(k).attr("data-if", "yes");
            }
        }

        //所有公司的选择状态
        var arrCPAE = [];
        var allCE = $(".kdef").find(".choiceC");
        var srcAE = allCE.attr("src");

        if (srcAT.search(/Checked_default\.png/) > -1) { //已选中
            $(this).attr("src", srcAT.replace("Checked_default.png", "Checked_default2.png"));
            for (var i = 0; i < allCE.length; i++) {
                allCE.attr("src", srcAE.replace("Checked_default.png", "Checked_default2.png"));
                allCE.eq(i).attr("data-c", "no");
                arrCPAE.push(allCE.eq(i).attr("data-c"))
            }
        } else if (srcAT.search(/Checked_default2\.png/) > -1) { //未选中
            $(this).attr("src", srcAT.replace("Checked_default2.png", "Checked_default.png"));
            for (var i = 0; i < allCE.length; i++) {
                allCE.attr("src", srcAE.replace("Checked_default2.png", "Checked_default.png"));
                allCE.eq(i).attr("data-c", "yes");
                arrCPAE.push(allCE.eq(i).attr("data-c"))
            }
        }

        calAll()
    });

    // //	点击跳转公司店铺
    $(document).on("click", ".conpany", function() {
        var dataSId = $(this).attr("data-shopId");
        window.location = "storeDetails.html?factId=" + dataSId
    });
    //	点击商品图片和名称跳转详情
    $(document).on("click", ".toDetails", function() {
        var dataId = $(this).attr("data-id");
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
                    window.location.href = "details.html?id=" + dataId + "";
                } else {
                    //window.location.reload()
                }
            }
        });
    });

    //单个公司所有产品选择按钮
    $(document).on("click", ".choiceC", function() {
        var src1 = $(this).attr("src");
        var oneCC = $(this).parent().parent().siblings(".everPro").find(".choiceP");
        var src3 = oneCC.attr("src");
        if (src1.search(/Checked_default\.png/) > -1) { //已选中
            $(this).attr("data-if", "no")
            $(this).attr("src", src1.replace("Checked_default.png", "Checked_default2.png"));
            $(this).attr("data-c", "no")
            for (var i = 0; i < oneCC.length; i++) {
                oneCC.attr("src", src3.replace("Checked_default.png", "Checked_default2.png"));
                oneCC.attr("data-p", "no")
            }

            //一个一个取消选中公司
            var arrCPC = [];
            var everyPC = $(this).parent().parent().parent().parent().find(".choiceC");
            for (var i = 0; i < everyPC.length; i++) {
                arrCPC.push(everyPC.eq(i).attr("data-c"))
            }
            //全部变成未选中
            var momPC = $(".cAll");
            var momsrcC = momPC.attr("src");
            for (var i = 0; i < arrCPC.length; i++) {
                if (arrCPC.indexOf("no") > -1) { //未全选
                    momPC.attr("src", momsrcC.replace("Checked_default.png", "Checked_default2.png"));
                } else { //全选
                    momPC.attr("src", momsrcC.replace("Checked_default2.png", "Checked_default.png"));
                }
                if (arrCPC.indexOf("yes") > -1) { //有选中
                    $(".btn_car2").css("background", "#FD5700");
                } else { //无选中
                    $(".btn_car2").css("background", "#EFEFEF");
                }
            }
        } else if (src1.search(/Checked_default2\.png/) > -1) { //未选中
            $(".btn_car2").css("background", "#FD5700");
            $(this).attr("data-if", "yes");
            $(this).attr("src", src1.replace("Checked_default2.png", "Checked_default.png"));
            $(this).attr("data-c", "yes");
            for (var i = 0; i < oneCC.length; i++) {
                oneCC.attr("src", src3.replace("Checked_default2.png", "Checked_default.png"));
                oneCC.attr("data-p", "yes")
            }

            //一个一个选中所有公司
            var arrCPC = [];
            var everyPC = $(this).parent().parent().parent().parent().find(".choiceC");
            for (var i = 0; i < everyPC.length; i++) {
                arrCPC.push(everyPC.eq(i).attr("data-c"))
            }
            //全部变成选中
            var momPC = $(".cAll");
            var momsrcC = momPC.attr("src");
            for (var i = 0; i < arrCPC.length; i++) {
                if (arrCPC.indexOf("no") > -1) { //未全选
                    momPC.attr("src", momsrcC.replace("Checked_default.png", "Checked_default2.png"));
                } else { //全选
                    momPC.attr("src", momsrcC.replace("Checked_default2.png", "Checked_default.png"));
                }
            }
        }

        calAll()
    });


    //单个产品选择按钮
    $(document).on("click", ".choiceP", function() {
        var src2 = $(this).attr("src");

        if (src2.search(/Checked_default\.png/) > -1) { //已选中
            $(this).attr("src", src2.replace("Checked_default.png", "Checked_default2.png"));
            $(this).attr("data-p", "no");

            //一个一个取消所有
            var arrCP = [];
            var everyP = $(this).parent().parent().parent().parent().find(".choiceP");
            for (var i = 0; i < everyP.length; i++) {
                if (everyP.eq(i).attr("data-is") != 0) {
                    arrCP.push(everyP.eq(i).attr("data-p"))
                }
            }
            //判断data-if
            var dataIf = [];
            for (var i = 0; i < arrCP.length; i++) {
                if (arrCP[i].indexOf("yes") > -1) {
                    // $(".btn_car2").css("background", "#FD5700");
                    $(this).parent().parent().parent().parent().siblings(".car_tit").find(".choiceC").attr("data-if", "yes")
                } else {
                    // $(".btn_car2").css("background", "#EFEFEF");
                    $(this).parent().parent().parent().parent().siblings(".car_tit").find(".choiceC").attr("data-if", "no")
                }
            }
            //公司变成未选中
            var momP = $(this).parent().parent().parent().parent().siblings(".car_tit").find(".choiceC");
            momP.attr("data-c", "no");
            momP.attr("src", momP.attr("src").replace("Checked_default.png", "Checked_default2.png"));
            //全部变成未选中
            var momA = $(this).parent().parent().parent().parent().parent().parent().siblings(".footer_car").find(".cAll");
            momA.attr("data-c", "yes");
            momA.attr("src", $(".cAll").attr("src").replace("Checked_default.png", "Checked_default2.png"));
            //    立即支付的状态
            var arrCPA = [];
            var everyPA = $(this).parent().parent().parent().parent().parent().parent().parent().find(".choiceP");
            for (var i = 0; i < everyPA.length; i++) {
                arrCPA.push(everyPA.eq(i).attr("data-p"))
            }
            var momPA = $(".cAll");
            for (var i = 0; i < arrCPA.length; i++) {
                if (arrCPA.indexOf("yes") > -1) { //有选中
                    $(".btn_car2").css("background", "#FD5700");
                } else { //全选
                    $(".btn_car2").css("background", "#EFEFEF");
                }
            }

        } else if (src2.search(/Checked_default2\.png/) > -1) { //未选中
            $(".btn_car2").css("background", "#FD5700");
            $(this).attr("src", src2.replace("Checked_default2.png", "Checked_default.png"));
            $(this).attr("data-p", "yes");
            //data-if---代表那些选中产品的公司被选中
            $(this).parent().parent().parent().parent().siblings(".car_tit").find(".choiceC").attr("data-if", "yes");
            //一个一个选中所有
            var arrCP = [];
            var everyP = $(this).parent().parent().parent().parent().find(".choiceP");
            for (var i = 0; i < everyP.length; i++) {
                if (everyP.eq(i).attr("data-is") != 0) {
                    arrCP.push(everyP.eq(i).attr("data-p"))
                }
            }

            //公司变成选中
            var momP = $(this).parent().parent().parent().parent().siblings(".car_tit").find(".choiceC");
            var momsrc = momP.attr("src");
            for (var i = 0; i < arrCP.length; i++) {
                if (arrCP.indexOf("no") > -1) { //未全选
                    momP.attr("data-c", "no");
                    momP.attr("src", momsrc.replace("Checked_default.png", "Checked_default2.png"));
                } else { //全选
                    momP.attr("data-c", "yes");
                    momP.attr("src", momsrc.replace("Checked_default2.png", "Checked_default.png"));
                }
            }
            //全部变成选中
            var arrCPA = [];
            var everyPA = $(this).parent().parent().parent().parent().parent().parent().parent().find(".choiceP");
            for (var i = 0; i < everyPA.length; i++) {
                arrCPA.push(everyPA.eq(i).attr("data-p"))
            }
            var momPA = $(".cAll");
            var momsrcA = momPA.attr("src");
            for (var i = 0; i < arrCPA.length; i++) {
                if (arrCPA.indexOf("no") > -1) { //未全选
                    momPA.attr("src", momsrcA.replace("Checked_default.png", "Checked_default2.png"));
                } else { //全选
                    momPA.attr("src", momsrcA.replace("Checked_default2.png", "Checked_default.png"));
                }
            }
        }

        calAll()

    });


    //输入框
    $(document).on("keyup", ".inputk2", function() {
        var stock = $(this).attr("data-stock");
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
        if (parseInt($(this).val()) > parseInt(stock)) {
            $(this).val(stock)
        }
    });
    $(document).on("blur", ".inputk2", function() {
        if ($(this).val() == 0 || $(this).val() == "" || $(this).val() == undefined) {
            $(this).val("1")
        }
        var thisN = $(this);

        var dataId = $(this).attr("data-id");
        var count = parseInt($(this).val());
        $.ajax({
            type: "get",
            url: "../zlead/shopcart/updateCount?cartId=" + dataId + "&type=" + 3 + "&count=" + count + "&t_=" + Math.random(),
            dataType: "json",
            async: true,
            success: function(res) {
                if (res.code == 1) {
                    var value1 = thisN.val();
                    thisN.attr("value", thisN.val());
                    thisN.attr("data-oldcount", thisN.val());
                    var onePri = thisN.parent().parent().siblings(".onePri").html();
                    thisN.parent().parent().siblings(".jine").find(".twoPri").html((onePri * value1).toFixed(2));
                    thisN.parent().parent().siblings(".jine").find(".twoPri").attr("data-twoprice", onePri * value1);

                    shopCar(); //顶部购物车的接口
                    // shoppingCartGoods();//用来更新页面数据
                    calAll()
                } else {
                    thisN.val(thisN.attr("data-oldcount"));
                    $(".msg").text(res.message);
                    $(".meng").show();
                    $(".popupMsg").show();
                }
            },
            error: function(request) { //请求出错
            }
        })
    });

    //加
    $(document).on("click", ".add", function() {
        var dataId = $(this).attr("data-id");
        var this2 = $(this);
        var count = $(this).parent().siblings('div').children('input').val();
        if (count == 0 || count == "" || count == undefined) {
            $(this).parent().siblings('div').children('input').val('1');
        }
        var count = parseInt($(this).parent().siblings('div').children('input').val());
        $.ajax({
            type: "get",
            url: "../zlead/shopcart/updateCount?cartId=" + dataId + "&type=" + 1 + "&count=" + count + "&t_=" + Math.random(),
            dataType: "json",
            async: true,
            success: function(res) {
                if (res.code == 1) {
                    var value1 = this2.parent().siblings(".inputk").children().val();
                    // this2.parent().siblings(".inputk").children().attr("value", ++value1);
                    this2.parent().siblings(".inputk").children().val(parseInt(value1) + 1);
                    this2.parent().siblings(".inputk").children().attr("data-oldcount", parseInt(value1) + 1);
                    var onePri = this2.parent().parent().siblings(".onePri").html();
                    // this2.parent().parent().siblings(".jine").find(".twoPri").html((onePri * value1).toFixed(2));
                    this2.parent().parent().siblings(".jine").find(".twoPri").html((onePri * (parseInt(value1) + 1)).toFixed(2));
                    this2.parent().parent().siblings(".jine").find(".twoPri").attr("data-twoprice", onePri * (parseInt(value1) + 1));

                    if (parseInt(this2.parent().siblings(".inputk").children().val()) > 1) {
                        this2.siblings().css("color", "#4F5362")
                    } else {
                        this2.siblings().css("color", "#E5E5E5")
                    }
                    shopCar(); //顶部购物车的接口
                    // shoppingCartGoods();//用来更新页面数据
                    calAll()
                } else {
                    this2.parent().siblings(".inputk").children().attr("value", $(this).attr("data-oldcount"));
                    this2.parent().siblings(".inputk").children().attr("data-oldcount", $(this).attr("data-oldcount"));
                    $(".msg").text(res.message);
                    $(".meng").show();
                    $(".popupMsg").show();
                }
            },
            error: function(request) { //请求出错
            }
        })

    });
    //减
    $(document).on("click", ".down", function() {
        var dataId = $(this).attr("data-id");
        var this3 = $(this);
        var count = $(this).parent().siblings('div').children('input').val();
        if (count == 0 || count == "" || count == undefined) {
            $(this).parent().siblings('div').children('input').val('1');
        }
        var count = parseInt($(this).parent().siblings('div').children('input').val());

        var value20 = $(this).parent().siblings(".inputk").children().val();
        if (parseInt(value20) <= 1) {
            $(this).parent().siblings(".inputk").children().attr("value", 1);
            $(this).css("color", "#E5E5E5")
        } else {
            $.ajax({
                type: "get",
                url: "../zlead/shopcart/updateCount?cartId=" + dataId + "&type=" + 2 + "&count=" + count + "&t_=" + Math.random(),
                dataType: "json",
                async: true,
                success: function(res) {
                    if (res.code == 1) {
                        var value2 = this3.parent().siblings(".inputk").children().val();
                        if (parseInt(value2) <= 2) {
                            // this3.parent().siblings(".inputk").children().attr("value", --value2);
                            this3.parent().siblings(".inputk").children().val(parseInt(value2) - 1);
                            this3.parent().siblings(".inputk").children().attr("data-oldcount", parseInt(value2) - 1);
                            this3.css("color", "#E5E5E5")
                        } else {
                            // this3.parent().siblings(".inputk").children().attr("value", --value2);
                            this3.parent().siblings(".inputk").children().val(parseInt(value2) - 1);
                            this3.parent().siblings(".inputk").children().attr("data-oldcount", parseInt(value2) - 1);
                            this3.css("color", "#4F5362")
                        }
                        var onePri = this3.parent().parent().siblings(".onePri").html();
                        // this3.parent().parent().siblings(".jine").find(".twoPri").html((onePri * value2).toFixed(2));
                        this3.parent().parent().siblings(".jine").find(".twoPri").html((onePri * (parseInt(value2) - 1)).toFixed(2));
                        this3.parent().parent().siblings(".jine").find(".twoPri").attr("data-twoprice", onePri * (parseInt(value2) - 1));
                        shopCar(); //顶部购物车的接口
                        // shoppingCartGoods();//用来更新页面数据
                        calAll();
                    } else {
                        this3.parent().siblings(".inputk").children().attr("value", $(this).attr("data-oldcount"));
                        this3.parent().siblings(".inputk").children().attr("data-oldcount", $(this).attr("data-oldcount"));
                        $(".msg").text(res.message);
                        $(".meng").show();
                        $(".popupMsg").show();
                    }
                },
                error: function(request) { //请求出错
                }
            })
        }


    });
    //类别-滑过
    $(document).on("mouseenter", ".type_car", function() {
        if ($(this).attr("data-ismarketable") != 0 && $(this).attr("data-stock") != 0) {
            $(this).css("border", "1px dashed rgba(253,87,0,1)");
            $(this).siblings(".type_car1").show();
        }
    });
    $(document).on("mouseleave", ".type_car", function() {
        if ($(this).attr("data-ismarketable") != 0 && $(this).attr("data-stock") != 0) {
            $(this).css("border", "none");
            $(".type_car1").hide();
        }
    });



    //	类别-点击
    $(document).on("click", ".type_car", function() {
        if ($(this).attr("data-ismarketable") != 0 && $(this).attr("data-stock") != 0) {
            $(this).siblings(".type_check").show();
            $(this).siblings(".type_check").show();
            $(".isShow").removeClass("isSel");
        }
        return false;
    });

    //	阻止冒泡
    $(document).on("click", ".type_check", function() {
        return false;
    });

    //	类别-选择某个-取消
    $(document).on("click", ".btn2", function() {
        $(".type_check").hide();
    });

    var allLi = "";
    //删除单件商品
    $(document).on("click", ".del", function() {
        var allLiP = $(this).parent().parent().parent();
        if (allLiP.find(".car_content")[1] == undefined) {
            //一个公司一个商品-需要删除公司名字
            allLi = $(this).parent().parent().parent().parent();
        } else {
            //一个公司多个商品
            allLi = $(this).parent().parent();
        }
        var ifDel = $(this).parent().parent().find(".choiceP").attr("data-p");
        var ifNoChoice = $(this).parent().parent().find(".choicePIS").css("display");
        if (ifNoChoice == "none") { //有效商品
            if (ifDel == "yes") { //选中才能删除
                $('.meng').show();
                $('.dialog').show();
                var dataId = $(this).attr("data-id");
                window.localStorage.setItem("oneDelProId", dataId);
                window.localStorage.setItem("oneORall", "one");
            } else {
                $(".msg").text("未选中要删除商品呢");
                $(".meng").show();
                $(".popupMsg").show();
            }
        } else if (ifNoChoice == "block") { //无效商品
            $('.meng').show();
            $('.dialog').show();
            var dataId = $(this).attr("data-id");
            window.localStorage.setItem("oneDelProId", dataId);
            window.localStorage.setItem("oneORall", "one");
        }
    });

    //删除商品--确认按钮
    $(document).on("click", ".confirm", function() {
        if (window.localStorage.getItem("oneORall") == "one") { //删除单件商品
            $.ajax({
                type: "get",
                // data: {
                //     cartIds: window.localStorage.getItem("oneDelProId"),
                // },
                url: "../zlead/shopcart/deleteShoppingCart?cartIds=" + window.localStorage.getItem("oneDelProId") + "&t_=" + Math.random(),
                dataType: "json",
                async: true,
                success: function(res) {
                    if (res.code == 1) {
                        $('.meng').hide();
                        $('.dialog').hide();
                        allLi.remove();
                        shopCar(); //顶部购物车的接口
                        // shoppingCartGoods();
                        calAll();
                    } else {
                        $(".msg").text(res.message);
                        $(".meng").show();
                        $(".popupMsg").show();
                    }
                },
                error: function(request) { //请求出错
                }
            })
        } else if (window.localStorage.getItem("oneORall") == "all") { //删除所有商品
            if (window.localStorage.getItem("allDelProId")) {
                $.ajax({
                    type: "get",
                    // data: {
                    //     cartIds: window.localStorage.getItem("allDelProId"),
                    // },
                    url: "../zlead/shopcart/deleteShoppingCart?cartIds=" + window.localStorage.getItem("allDelProId") + "&t_=" + Math.random(),
                    dataType: "json",
                    async: true,
                    success: function(res) {
                        if (res.code == 1) {
                            $('.meng').hide();
                            $('.dialog').hide();
                            shopCar(); //顶部购物车的接口
                            shoppingCartGoods()
                        } else {
                            $(".msg").text(res.message);
                            $(".meng").show();
                            $(".popupMsg").show();
                        }
                    },
                    error: function(request) { //请求出错
                    }
                })
            } else {
                $(".msg").text("未选中要删除商品呢");
                $(".meng").show();
                $(".popupMsg").show();
            }
        }

    });

    //删除单件商品--取消按钮
    $(document).on("click", ".cancel", function() {
        $('.meng').hide();
        $('.dialog').hide();
    });

    $(document).on("click", ".del_car", function() {
        //所有商品的选择状态
        var arrCPA1 = [];
        var arrCPA2 = [];
        var allC = $(".content").find(".choiceP");

        for (var i = 0; i < allC.length; i++) {
            if (allC.eq(i).attr("data-p") == "yes") {
                arrCPA1.push(allC.eq(i).attr("data-p"));
                arrCPA2.push(allC.eq(i).attr("data-id"));
            }
        }

        if (arrCPA1.indexOf("yes") > -1) { //选中才能删除
            window.localStorage.setItem("oneORall", "all");
            $(".dialog").find(".text").text("确定要批量删除商品？");
            $('.meng').show();
            $('.dialog').show();
            window.localStorage.setItem("allDelProId", arrCPA2.toString())
        } else {
            $(".msg").text("未选中要删除商品呢");
            $(".meng").show();
            $(".popupMsg").show();
        }
    });

    //	立即支付按钮
    $(document).on("click", ".btn_car2", function() {
        //所有公司的选择状态
        //所有商品的选择状态
        var arrCPA1C = [];
        var arrCPA2C = [];
        var arrCPA3C = []; //公司id
        var allCC = $(".content").find(".choiceC");

        for (var i = 0; i < allCC.length; i++) {
            arrCPA1C.push(allCC.eq(i).attr("data-c"));
            arrCPA2C.push(allCC.eq(i).attr("data-shopid"))
        }

        if (arrCPA1C.indexOf("yes") > -1) {
            for (var i = 0; i < arrCPA1C.length; i++) {
                if (arrCPA1C[i] == "yes") {
                    arrCPA3C.push(arrCPA2C[i]);
                }
            }
        }

        //所有商品的选择状态
        var arrCPA1 = [];
        var arrCPA2 = [];
        var arrCPA3 = [];
        var allC = $(".content").find(".choiceP");

        for (var i = 0; i < allC.length; i++) {
            if (allC.eq(i).attr("data-is") != 0) {
                arrCPA1.push(allC.eq(i).attr("data-p"));
                arrCPA2.push(allC.eq(i).attr("data-id"))
            }
        }

        //此处用列表数据，，根据每条商品数据里面有对应的shopid，来判断是否选择了失效的商品
        var oList = JSON.parse(window.localStorage.getItem("onlyList"));
        var oListArr = []; //所有单条数据
        if (oList != undefined && oList.length > 0) {
            for (var i = 0; i < oList.length; i++) {
                var oListArr = oListArr.concat(oList[i].goodsList)
            }
        } else {
            $(".msg").text("未选中要支付的商品呢");
            $(".meng").show();
            $(".popupMsg").show();
        }

        for (var i = 0; i < arrCPA1.length; i++) {
            if (arrCPA1[i] == "yes") {
                arrCPA3.push(arrCPA2[i]);
            }
        }

        var changeArr = []; //根据goodsid拿到对应的公司id，此处为公司id,,传参数用

        for (var i = 0; i < arrCPA3.length; i++) {
            for (var j = 0; j < oListArr.length; j++) {
                if (arrCPA3[i] == oListArr[j].id) {
                    var changeArr = changeArr.concat(oListArr[j].shopId);
                }
            }
        }

        if (arrCPA1.indexOf("yes") > -1) { //选中才能支付
            $.ajax({
                type: "get",
                // data: {
                //     cartIds: arrCPA3.toString(),
                // },
                url: "../zlead/order/cartComfirmOrder?cartIds=" + arrCPA3.toString() + "&t_=" + Math.random(),
                dataType: "json",
                async: true,
                success: function(res) {
                    if (res.code == 1) {
                        //跳转页面
                        var datas = JSON.stringify(res.data);
                        localStorage.setItem("buyCarP", arrCPA3.toString());
                        localStorage.setItem("buyCarPC", changeArr.toString());
                        localStorage.setItem("buyCar", "buyCar");
                        localStorage.setItem("datas", datas);
                        var priList = res.data;
                        var priM = 0;
                        var priM2 = 0;
                        var priM3 = 0;
                        var priM4 = 0;

                        // // 运费
                        // for (var i = 0; i < priList.length; i++) {
                        //     priM4 += priList[i].trans;
                        // }

                        // // 折扣后的价格
                        // for (var i = 0; i < res.data.length; i++) {
                        //     priM += res.data[i].discount * priList[i].totalPrice;
                        // }

                        // // 原价
                        // for (var i = 0; i < priList.length; i++) {
                        //     priM2 += priList[i].totalPrice;
                        // }
                        // // 优惠总额


                        // var haha = priM.toFixed(2);
                        // var ha = priM2.toFixed(2);
                        // priM3 = (ha - haha).toFixed(2);

                        // 原价：priM2;还需支付：priM
                        for (var i = 0; i < res.data.length; i++) {
                            // if(){
                            //
                            // }
                            priM += res.data[i].totalPrice;
                            priM2 += res.data[i].originalPrice;
                        }

                        var haha = priM.toFixed(2);
                        var ha = priM2.toFixed(2);
                        priM3 = (ha - haha).toFixed(2);

                        //priM使用，已经判断过是否活动商品，，，priM2，priM3，priM4暂不使用，未作判断
                        window.localStorage.setItem("priM", haha); //还需支付
                        window.localStorage.setItem("priM2", ha); //原价
                        window.localStorage.setItem("priM3", priM3); //优惠
                        window.localStorage.setItem("priM4", priM4); //运费

                        window.location = "orderList.html"
                    } else {
                        // $(".msg").text("库存不足");// $(".msg").text("商品已下架");
                        $(".msg").text(res.message);
                        $(".meng").show();
                        $(".popupMsg").show();
                    }
                },
                error: function(request) { //请求出错
                }
            })

        } else {
            $(".msg").text("未选中有效商品");
            $(".meng").show();
            $(".popupMsg").show();
        }
    });



}); //$


//列表
function shoppingCartGoods() {
    $.ajax({
        type: "get",
        data: {
            // prodType: 0
        },
        // url: "/zlead/tshoppingcart/shoppingCartGoods",
        // url: "/zlead/shopcart/shoppingCartGoods",
        url: "/zlead/shopcart/shoppingCartGoodsForNavi?t_=" + Math.random(),
        dataType: "json",
        async: true,
        beforeSend: function() {
            $('.car_box').css({
                width: '1200px',
                height: 'auto',
                minHight: '420px'
            });
            $('.loading').show();
        },
        complete: function() {
            $('.loading').hide();
        },
        success: function(res) {
            $('.loading').hide();
            var mymsg = res;
            if (res.code == 1) { //有数据
                var mystr = '';
                mystr += '<ul class="car_th">';
                mystr += '<li> <i class="checkbox"></i> 全选</li>';
                mystr += '<li>商品信息</li>';
                mystr += '<li>参数</li>';
                mystr += '<li>单价</li>';
                mystr += '<li>数量</li>';
                mystr += '<li>金额</li>';
                mystr += '<li></li>';
                mystr += '</ul>';
                mystr += '<ul class="content" id="list">';
                mystr += '</ul>';
                mystr += '<div class="footer_car">';
                mystr += '<span class="number">';
                mystr += '<i><img class="ft1 cAll" src="../../shopping/img/car/Checked_default2.png" alt=""></i>';
                mystr += '<span class="all">全部(共<span class="goodsNum" style="color:#FF7A47;font-weight: 700">0</span>件 )</span>';
                mystr += '<span class="del_car">批量删除</span>';
                mystr += '</span>';
                mystr += '<span class="btn_car2">立即支付</span>';
                mystr += '<span class="alls">合计：<span class="money_car">￥</span><span class="money_car allM">0.00</span>';
                mystr += ' </span></div>';
                $('.car_box').html(mystr);

                var list = res.data; //公司
                onlyList = res.data; //公司
                window.localStorage.setItem("onlyList", JSON.stringify(onlyList));
                //列表图片地址
                var srcImg = "";
                //单个公司名字
                var asd = "";
                //总件数
                var goodsNum = 0;
                var goodsNumTop = 0;
                //合计金额
                // var goodsMoney = 0;

                for (var i = 0; i < list.length; i++) {
                    asd += '<li class="kdef">';
                    asd += '<div class="car_tit">';
                    asd += '<span><img class="choiceC" data-c="no" data-if = "no" data-shopId ="' + list[i].shopId + '" src="../../shopping/img/car/Checked_default2.png" alt=""></span>';
                    asd += '<span><img src="../../shopping/img/car/home1.png" alt=""></span>';
                    asd += '<span class="conpany" data-shopId = "' + list[i].facId.factory_id + '">' + list[i].shopName + '</span>';
                    asd += '</div>';
                    asd += '<div class="everPro">';

                    for (var j = 0; j < list[i].goodsList.length; j++) {
                        $(".goodsNum").text(0); //全部件数--初始化为0
                        //顶部栏内容，保证不刷新页面数据统一
                        if (list[i].goodsList[j].isMarketable != 0 && list[i].goodsList[j].stock != 0) {
                            goodsNumTop += list[i].goodsList[j].count;
                        };
                        $("#cont").text(goodsNumTop); //顶部栏
                        $("#jiashu").text(goodsNumTop); //顶部栏

                        if (list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) {
                            asd += '<div class="car_content" mylist="' + JSON.stringify(list[i].goodsList[j]) + '" style= background:';
                        } else {
                            asd += '<div class="car_content myType" mylist="' + JSON.stringify(list[i].goodsList[j]) + '"style= background:';
                        }
                        asd += ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "#F4F6F7" : "#fff") + '>';
                        asd += '<span class="product_car use1">';
                        asd += '<i class="check_png check_png1"><img style= display:' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "none" : "block") + ' class="choiceP" data-p="no" data-id="' + list[i].goodsList[j].id + '" data-is = "' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? 0 : 1) + '" src="../../shopping/img/car/Checked_default2.png" alt=""></i>';
                        asd += '<i class="check_png check_png2"><img style= display:' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "block" : "none") + ' class="choicePIS" src="../../shopping/img/car/label_shixiao.png" alt=""></i>';
                        asd += '<i class="img_cars"><img class="toDetails" data-go="' + list[i].goodsList[j].isMarketable + '" data-id="' + list[i].goodsList[j].goodsId + '" src="' + list[i].goodsList[j].goodsImg + '" alt=""></i>';
                        asd += '<div class="clear"></div>';
                        asd += '</span>';
                        asd += '<span class="product_car use2">';
                        asd += '<i class="check_png"><img src="../../shopping/img/car/label_shixiao.png" alt=""></i>';
                        asd += '<i class="img_cars"><img src="../../shopping/img/car/1547285288(1).png" alt=""></i>';
                        asd += '<div class="clear"></div>';
                        asd += '</span>';
                        asd += '<span class="product_p">';
                        asd += '<p class="toDetails" data-go="' + list[i].goodsList[j].isMarketable + '" data-id="' + list[i].goodsList[j].goodsId + '">' + list[i].goodsList[j].goodsName + '</p>';
                        asd += '</span>';
                        asd += '<span class="type_car" mygoods="' + list[i].goodsList[j].goodsId + '" myid="' + list[i].goodsList[j].id + '" data-isMarketable = ' + list[i].goodsList[j].isMarketable + ' data-stock = ' + list[i].goodsList[j].stock + '>';
                        asd += list[i].goodsList[j].thisGoodsAttr.toString();
                        asd += '</span>';
                        asd += '<div class="type_check" id="type_check">';
                        asd += '<ul class="typeAll">';

                        asd += '</ul>';
                        asd += '<div class="clear"></div>';
                        asd += '<div class="btns">';
                        asd += '<span class="btn1">' + "确定" + '</span>';
                        asd += '<span class="btn2">' + "取消" + '</span>';
                        asd += '</div>';
                        asd += '</div>';
                        asd += '<div class="type_car1">';
                        asd += '<span class="type_reset">' + "修改" + '</span>';
                        asd += '</div>';
                        asd += '<span class="monet_car monet_car1">' + '￥' + '</span>';
                        asd += '<span class="monet_car monet_car2 onePri">' + (list[i].goodsList[j].prodType != 2 ? (list[i].goodsList[j].goodsPrice).toFixed(2) : list[i].goodsList[j].goodsPrice.toFixed(2)) + '</span>';
                        asd += '<span class="number_car" style= border:' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "none" : "") + '>';
                        asd += '<div class="inputk">';
                        asd += '<input class="num inputk2"  maxlength="7" type="text" data-oldcount="' + list[i].goodsList[j].count + '" data-stock="' + list[i].goodsList[j].stock + '" value="' + list[i].goodsList[j].count + '" style= background:' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "#F4F6F7" : "#fff");
                        if (list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) {
                            asd += ' disabled = "true"' + 'data-id="' + list[i].goodsList[j].id + '" >';
                        } else {
                            asd += ' data-id="' + list[i].goodsList[j].id + '">';
                        }
                        // asd += '<input class="num" type="text" data-stock="'+list[i].goodsList[j].stock +'" style= background:' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "#F4F6F7" : "#fff") + '>';
                        asd += '</div>';
                        asd += '<div class="btn_car" style= display:' + ((list[i].goodsList[j].isMarketable == 0 || list[i].goodsList[j].stock == 0) ? "none" : "block") + '>';
                        asd += '<i data-id="' + list[i].goodsList[j].id + '" class="add"> ' + "+" + '</i>';
                        asd += '<i data-id="' + list[i].goodsList[j].id + '" class="down" style = color:' + (list[i].goodsList[j].count > 0 ? "#4F5362" : "#E5E5E5") + '>' + "-" + '</i>';
                        asd += '</div>';
                        asd += '</span><div class="jine jine1">';
                        asd += '<span>' + "￥" + '</span>';
                        asd += '<span class=" twoPri" data-twoPrice="' + (list[i].goodsList[j].prodType != 2 ? (list[i].goodsList[j].count * list[i].goodsList[j].goodsPrice).toFixed(2) : (list[i].goodsList[j].count * list[i].goodsList[j].goodsPrice).toFixed(2)) + '" data-onePrice="' + (list[i].goodsList[j].prodType != 2 ? (list[i].goodsList[j].goodsPrice).toFixed(2) : list[i].goodsList[j].goodsPrice.toFixed(2)) + '">' + (list[i].goodsList[j].prodType != 2 ? (list[i].goodsList[j].count * list[i].goodsList[j].goodsPrice).toFixed(2) : (list[i].goodsList[j].count * list[i].goodsList[j].goodsPrice).toFixed(2)) + '</span>';
                        asd += '</div><span class="tsash_car">';
                        asd += '<img class="del" data-id="' + list[i].goodsList[j].id + '" src="../../shopping/img//car//Trash.png" alt="">';
                        asd += '</span>';
                        asd += '</div>';

                    }
                    asd += '</div>';
                    asd += '<div class="clear"></div>';
                    asd += '<div class="line_car"></div>';
                    asd += '</li>';

                    $("#list").html(asd);

                    // clearAll();
                    // 购物车--改变类型
                    $('.type_car').click(function() {
                        if ($(this).attr("data-ismarketable") != 0 && $(this).attr("data-stock") != 0) {
                            var _mythis = $(this);
                            $(this).parents('.car_content').find(".btn1").css({
                                background: 'rgba(253, 87, 0, 1)'
                            })
                            $(this).css("border", "1px dashed rgba(253,87,0,1)");
                            $(".type_check").hide();
                            $(this).siblings(".type_car1").show();
                            $('.typeAll').html('');

                            // 初始化数据 准备工作
                            var myfather = $(this).parents('.kdef').index(); // li 的下标
                            var myson = $(this).parents('.car_content').index(); // li>div>div的下标
                            var myurl_1 = '/zlead/fplat/getAttr?goodsId='; // 接口1
                            var myurl_2 = '/zlead/fplat/getGoodsById/'; // 接口2
                            // var myurl_2 = '/zlead/fplat/editModel?goodsId='; // 接口2

                            // var mygoodsId = mymsg.data[myfather].goodsList[myson].goodsId;
                            // var myId = mymsg.data[myfather].goodsList[myson].id;

                            var mygoodsId = $(this).attr('mygoods');
                            var myId = $(this).attr('myid');
                            myurl_1 += mygoodsId;
                            myurl_2 += myId;
                            var mydata = [];


                            // 忘记密码
                            // 第一次请求数据
                            $.ajax({
                                type: "get",
                                url: myurl_1,
                                dataType: "json",
                                async: true,
                                success: function(res) {
                                    newdata = res.data.thisGoodsAttr;
                                    // 参数库：mystore,后面点击更改参数用到
                                    var mystore = res.data.attrVals;
                                    var str = '';
                                    for (var i = 0; i < res.data.prodAttr.length; i++) {
                                        var s = '';
                                        for (var index in res.data.prodAttr[i]) {
                                            for (var j = 0; j < res.data.prodAttr[i][index].length; j++) {
                                                if (res.data.prodAttr[i][index][j].show == 1) {
                                                    s += '<p class="isSel" title="' + index + ':' + res.data.prodAttr[i][index][j].content + '"><span>' + index + '</span>';
                                                    s += '<span>' + res.data.prodAttr[i][index][j].content + '</span></p>';
                                                    mydata.push({
                                                        key: index,
                                                        value: res.data.prodAttr[i][index][j].content
                                                    })
                                                } else {
                                                    s += '<p title="' + index + ':' + res.data.prodAttr[i][index][j].content + '"><span>' + index + '</span>';
                                                    s += '<span>' + res.data.prodAttr[i][index][j].content + '</span></p>';
                                                }
                                            }
                                        }
                                        str += '<li>' + s + '</li>'
                                    }
                                    _mythis.parents('.car_content').find('.typeAll').html(str);


                                    /*
                                    备注：下面的结构是点击【修改】按钮后，根据请求的数据渲染的样式结构。
                                    	<ul class='typeAll'>
                                    		<li>
                                    			<p class='isSel'>
                                    				<span>颜色</span>
                                    				<span>白色</span>
                                    			</p>
                                    			<p>
                                    				<span>颜色</span>
                                    				<span>黑色</span>
                                    			</p>
                                    		</li>
                                    	</ul>

                                    */
                                    // 判断当前参数是否可以点
                                    _mythis.parents('.car_content').find('.typeAll').find('p').click(function() {
                                        if ($(this).hasClass('isSel')) {
                                            $(this).removeClass('isSel');
                                            changeColor(_mythis.parents('.car_content'))
                                        } else {
                                            var myarr = res.data.attrVals; // 参数库
                                            // var leng = _mythis.parents('.car_content').find('.typeAll').find('.isSel').length;
                                            var leng = _mythis.parents('.car_content').find('.typeAll').children('li').length;
                                            // --------------分割线---------------------------------------
                                            var index = $(this).parent('li').index(); //-----当前父元素索引-------------
                                            var mystr = $(this).children('span').eq(1).html(); //-------------当前商品参数---------------
                                            var newArr = [];
                                            // console.log(index, leng, '--------------');
                                            for (var i = 0; i < myarr.length; i++) {
                                                var flag = true;
                                                var arr = myarr[i].attr_val.split('_');
                                                // 当前之前的参数
                                                for (var j = 0; j < index; j++) {
                                                    var newflag = true;
                                                    var str = _mythis.parents('.car_content').find('.typeAll').children('li').eq(j).find('.isSel').children('span').eq(1).html();
                                                    var nstr = _mythis.parents('.car_content').find('.typeAll').children('li').eq(j).find('.isSel').children('span').eq(0).html() + ':';
                                                    if (str) {
                                                        for (var k = 0; k < arr.length; k++) {
                                                            if (nstr + str == arr[k]) {
                                                                newflag = false;
                                                                break;
                                                            }
                                                        }
                                                        if (newflag) {
                                                            flag = false;
                                                        }
                                                    }
                                                }
                                                // 当前选中的参数
                                                var newflag2 = true;
                                                var nstr = $(this).children('span').eq(0).html() + ':';
                                                for (var k = 0; k < arr.length; k++) {
                                                    if (nstr + mystr == arr[k]) {
                                                        newflag2 = false;
                                                        break;
                                                    }
                                                }
                                                if (newflag2) {
                                                    flag = false;
                                                }

                                                for (var j = index + 1; j < leng; j++) {
                                                    var myflag3 = true;
                                                    var str = _mythis.parents('.car_content').find('.typeAll').children('li').eq(j).find('.isSel').children('span').eq(1).html();
                                                    var nstr = _mythis.parents('.car_content').find('.typeAll').children('li').eq(j).find('.isSel').children('span').eq(0).html() + ':';
                                                    if (str) {
                                                        for (var k = 0; k < arr.length; k++) {
                                                            if (nstr + str == arr[k]) {
                                                                myflag3 = false;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        myflag3 = false;
                                                    }

                                                    if (myflag3) {
                                                        flag = false;
                                                    }
                                                }

                                                if (flag) {
                                                    newArr.push(myarr[i]);
                                                }
                                            }
                                            // console.log(newArr);
                                            // 当前可以点击
                                            if (newArr.length > 0) {
                                                $(this).addClass('isSel').siblings('p').removeClass('isSel');

                                            }
                                            changeColor(_mythis.parents('.car_content'))
                                            mydata = [];
                                            for (var i = 0; i < $(this).parents('ul').find('.isSel').length; i++) {
                                                mydata.push({
                                                    key: $(this).parents('ul').find('.isSel').eq(i).children('span').eq(0).html(),
                                                    value: $(this).parents('ul').find('.isSel').eq(i).children('span').eq(1).html()
                                                })
                                            }
                                        }
                                    });


                                    // 改变确认背景色
                                    function changeColor(options) {
                                        var newArr = [];
                                        if (mystore && mystore.length > 0) {
                                            for (var i = 0; i < mystore.length; i++) {
                                                var myarr = mystore[i].attr_val.split('_');
                                                if (myarr.length == options.find(".isSel").length) { // 过滤参数数量不等的型号
                                                    var flag = true;
                                                    for (var j = 0; j < options.find(".isSel").length; j++) {
                                                        var myflag = true;
                                                        var str = options.find(".isSel").eq(j).find('span').eq(1).html();
                                                        var nstr = options.find(".isSel").eq(j).find('span').eq(0).html() + ':';

                                                        for (var k = 0; k < myarr.length; k++) {
                                                            if (myarr[k] == nstr + str) {
                                                                myflag = false;
                                                            }
                                                        }
                                                        if (myflag) {
                                                            flag = false;
                                                        }
                                                    }
                                                    if (flag) {
                                                        newArr.push(mystore[i]);
                                                    }
                                                }
                                            }
                                        }

                                        // console.log(newArr, '-------------')
                                        if (newArr.length == 0) { // 判断当前是否所有参数都选中
                                            options.find(".btn1").css({
                                                background: 'rgb(204, 204, 204)'
                                            })
                                        } else { // 正常
                                            if (newArr[0].stock > 0) {
                                                options.find(".btn1").css({
                                                    background: 'rgba(253, 87, 0, 1)'
                                                })
                                            } else {
                                                options.find(".btn1").css({
                                                    background: 'rgb(204, 204, 204)'
                                                })
                                            }
                                        }

                                    }

                                    // 存储当前的产品参数
                                    var my_str = '';
                                    for (var i = 0; i < _mythis.parents('.car_content').find('.isSel').length; i++) {
                                        my_str += _mythis.parents('.car_content').find('.isSel').eq(i).children('span').eq(1).html()
                                    }


                                    // 第二次请求数据
                                    _mythis.parents('.car_content').find('.btn1').click(function() {
                                        // 获取点击时的产品参数，判断与之前的参数是否相等
                                        // $(this).unbind();
                                        var my_new = '';
                                        for (var i = 0; i < _mythis.parents('.car_content').find('.isSel').length; i++) {
                                            my_new += _mythis.parents('.car_content').find('.isSel').eq(i).children('span').eq(1).html()
                                        }
                                        if (my_str == my_new) {
                                            // alert('参数相等') // 此处展示弹窗，提醒参数没改
                                            $('.meng').show()
                                            $('.popupMsg').show().children('.msg').html('您修改后的购买信息和修改前一致');
                                        } else {
                                            var options = _mythis.parents('.car_content');
                                            var arr = [];
                                            var newArr = [];
                                            if (mystore && mystore.length > 0) {
                                                for (var i = 0; i < mystore.length; i++) {
                                                    var myarr = mystore[i].attr_val.split('_');
                                                    if (myarr.length == options.find(".isSel").length) { // 过滤参数数量不等的型号
                                                        var flag = true;
                                                        for (var j = 0; j < options.find(".isSel").length; j++) {
                                                            var myflag = true;
                                                            var str = options.find(".isSel").eq(j).find('span').eq(1).html();
                                                            var nstr = options.find(".isSel").eq(j).find('span').eq(0).html() + ':';

                                                            for (var k = 0; k < myarr.length; k++) {
                                                                if (myarr[k] == nstr + str) {
                                                                    myflag = false;
                                                                }
                                                            }
                                                            if (myflag) {
                                                                flag = false;
                                                            }
                                                        }
                                                        if (flag) {
                                                            newArr.push(mystore[i]);
                                                        }
                                                    }
                                                }
                                            }

                                            for (var i = 0; i < arr.length; i++) {
                                                if (arr[i].attr_val.split('_').length == options.find(".isSel").length) {
                                                    newArr.push(arr[i]);
                                                }
                                            }
                                            if (newArr.length > 0 && newArr[0].stock > 0) {
                                                // console.log(newArr);
                                                $(this).unbind();
                                                var _this = $(this);
                                                // console.log(mydata)
                                                mydata.push({
                                                    goodsId: newArr[0].goods_id
                                                });
                                                // console.log(mydata)

                                                var newdata = JSON.stringify(mydata);

                                                var myajax_2 = $.ajax({
                                                    type: "post",
                                                    data: newdata,
                                                    url: myurl_2,
                                                    dataType: "json",
                                                    async: true,
                                                    contentType: "application/json;",
                                                    success: function(res) {
                                                        // code 为1，重渲当前li>div>div
                                                        if (res.code == 1) {
                                                            _mythis.parents('.car_content').find(".type_check").hide();

                                                            // 改变goodsid值
                                                            _mythis.attr('mygoods', res.data.goodsDetailDto.id);

                                                            // 修改最大库存数量
                                                            _mythis.parents('.car_content').find('.num').attr('data-stock', res.data.goodsDetailDto.stock);

                                                            // 如果当前数量大于最大库存，则修改当前数量为最大库存，本期不做
                                                            // if (res.data.goodsDetailDto.stock < _mythis.parents('.car_content').find('.num').val()) {
                                                            //     _mythis.parents('.car_content').find('.num').val(res.data.goodsDetailDto.stock);
                                                            // }
                                                            // 备注：后端需要同步修改数量为最大库存数量。目前刷新后数量依然错误。

                                                            // 判断当前商品是否为失效商品
                                                            // 改变图片
                                                            if (res.data.goodsDetailDto.firstImag) {
                                                                _this.parents('.car_content').find('.img_cars').children('img').attr('src', res.data.goodsDetailDto.firstImag);
                                                            } else {
                                                                _this.parents('.car_content').find('.img_cars').children('img').attr('src', "../../shopping/img/index/sl3.png");
                                                            }

                                                            if (res.data.goodsDetailDto.prodType == 2) { // 活动商品：不需要乘折扣
                                                                // 改变单价
                                                                // if (res.data.goodsDetailDto.ifShowPrice) {
                                                                //     _this.parents('.car_content').find('.onePri').html(res.data.goodsDetailDto.price.toFixed(2));
                                                                // } else {
                                                                //     _this.parents('.car_content').find('.onePri').html('--');
                                                                // }
                                                                _this.parents('.car_content').find('.onePri').html(res.data.goodsDetailDto.price.toFixed(2));
                                                                // 改变总价
                                                                var my_allPrice = (_this.parents('.car_content').find('input').val() * (res.data.goodsDetailDto.price.toFixed(2))).toFixed(2)
                                                                _this.parents('.car_content').find('.twoPri').html(my_allPrice).attr('data-twoprice', my_allPrice).attr('data-oneprice', res.data.goodsDetailDto.price.toFixed(2))

                                                            } else if (res.data.goodsDetailDto.prodType == 0) { // 普通商品，需要乘折扣
                                                                // 改变单价
                                                                // if (res.data.goodsDetailDto.ifShowPrice) {
                                                                //     _this.parents('.car_content').find('.onePri').html((res.data.goodsDetailDto.price * res.data.discount).toFixed(2));
                                                                // } else {
                                                                //     _this.parents('.car_content').find('.onePri').html('--');
                                                                // }
                                                                _this.parents('.car_content').find('.onePri').html((res.data.goodsDetailDto.price * res.data.discount).toFixed(2));
                                                                // 改变总价
                                                                var my_allPrice = (_this.parents('.car_content').find('input').val() * (res.data.goodsDetailDto.price * res.data.discount).toFixed(2)).toFixed(2)
                                                                _this.parents('.car_content').find('.twoPri').html(my_allPrice).attr('data-twoprice', my_allPrice).attr('data-oneprice', (res.data.goodsDetailDto.price * res.data.discount).toFixed(2))
                                                            } else {
                                                                alert('prodType 为必填项，且值只能为0或者2.请联系后端修改数据。')
                                                            }



                                                            // 改变商品名字
                                                            _this.parents('.car_content').find('.product_p').children('p').html(res.data.goodsDetailDto.goodsName);
                                                            // 改变型号
                                                            var str = ''; // 型号
                                                            for (var i = 0; i < res.data.prodAttr.length; i++) {
                                                                if (typeof(res.data.prodAttr[i]) == 'object') {
                                                                    $.each(res.data.prodAttr[i], function(key, value) {
                                                                        for (var j = 0; j < value.length; j++) {
                                                                            if (value[j].show == 1) {
                                                                                str += value[j].content + ',';
                                                                            }
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                            str = str.substr(0, str.length - 1)

                                                            _this.parents('.car_content').find('.type_car').html(str);

                                                            // 当前商品为有效商品
                                                            if (res.data.goodsDetailDto.isMarket == 1) {
                                                                // 改变自身颜色
                                                                _this.parents('.car_content').css('background', '#fff').addClass('myType');
                                                                // 改变左侧选择按钮
                                                                // _this.parents('.car_content').find('.choiceP').css('display', 'block').attr('data-p', 'no').attr('src', '../../shopping/img/car/Checked_default2.png')
                                                                // _this.parents('.car_content').find('.choicePIS').hide();
                                                                // 改变数量框
                                                                _this.parents('.car_content').find('.btn_car').show()
                                                                _this.parents('.car_content').find('.number_car').css('border', '1px solid rgba(229, 229, 229, 1)')
                                                                _this.parents('.car_content').find('input').css('background', '#fff')

                                                                // 厂商：全选失效。
                                                                // _this.parents('li').find('.choiceC').attr({
                                                                //     dataC: "no",
                                                                //     dataIf: "no",
                                                                //     src: "../../shopping/img/car/Checked_default2.png"
                                                                // });
                                                                // 底部总全选失效
                                                                // $('.cAll').attr('src', "../../shopping/img/car/Checked_default2.png")

                                                            }
                                                            // 当前商品为无效商品
                                                            else {
                                                                // 改变自身颜色
                                                                _this.parents('.car_content').css('background', '#F4F6F7').removeClass('myType');
                                                                // 改变左侧选择按钮
                                                                _this.parents('.car_content').find('.choiceP').hide().attr('data-p', 'no');
                                                                _this.parents('.car_content').find('.choicePIS').show();
                                                                // 改变数量框
                                                                _this.parents('.car_content').find('.btn_car').hide();
                                                                _this.parents('.car_content').find('.number_car').css('border', 'none');
                                                                _this.parents('.car_content').find('input').css('background', '#F4F6F7');

                                                                // 判断当前厂商下是否有商品：原本只有一个有效商品，且现在变为无效

                                                                if (_this.parents('li').find('.myType').length == 0) {
                                                                    _this.parents('li').find('.choiceC').attr({
                                                                        dataC: "no",
                                                                        dataIf: "no",
                                                                        src: "../../shopping/img/car/Checked_default2.png"
                                                                    });
                                                                }
                                                            }
                                                            // mymsg.data[myfather].goodsList[myson].goodsId = res.data.goodsDetailDto.id;

                                                            // 计算总价+判断立即支付背景色
                                                            var goodsM = 0;
                                                            var arrAM = _this.parents(".car_box").find(".choiceP");
                                                            var arrTP = _this.parents(".car_box").find(".twoPri");
                                                            var goodsM2 = 0;
                                                            var k = 0;
                                                            for (var i = 0; i < arrAM.length; i++) {
                                                                if (arrAM.eq(i).attr("data-p") == "yes") {
                                                                    if (arrAM.eq(i).attr("data-is") == 1) {
                                                                        k++;
                                                                        goodsM2 += parseFloat(arrTP.eq(i).html());
                                                                        // $(".").css("background",)
                                                                    }
                                                                }
                                                            }
                                                            goodsM2 = goodsM2.toFixed(2);
                                                            _this.parents(".car_box").find(".allM").text(goodsM2);
                                                            if (k > 0) {
                                                                $('.btn_car2').css('background', 'rgb(253, 87, 0)')
                                                            } else {
                                                                $('.btn_car2').css('background', 'rgb(239, 239, 239)')
                                                            }



                                                        }
                                                        // code 为2，删除当前li>div>div，合并到指定id
                                                        else if (res.code == 2) {
                                                            // 获取当前li的num数量

                                                            var mynum = _this.parents('.car_content').find('.num').val();
                                                            // 删除dom
                                                            _this.parents('.car_content').remove();

                                                            // 给指定id的li的num加上上述值

                                                            // var myli = _this.parents('.everPro').children();
                                                            // var myId = mymsg.data[myfather].goodsList[myson].id;

                                                            for (var i = 0; i < _this.parents('.everPro').children().length; i++) {
                                                                var mylist = _this.parents('.everPro').children().eq(i).attr('mylist');
                                                                var myid = JSON.parse(mylist).id;
                                                                if (myid == res.data) {
                                                                    var a = parseInt(_this.parents('.everPro').children().eq(i).find('.num').val());
                                                                    var b = parseInt(mynum)
                                                                    _this.parents('.everPro').children().eq(i).find('.num').val(a + b);
                                                                }
                                                            }
                                                            window.location = 'buyCar.html';
                                                        }
                                                        // code 为3，请求错误，弹窗刷新
                                                        else {

                                                            // alert('无此商品，请重新选择！');
                                                            // console.log('error');
                                                            location.href = 'buyCar.html';
                                                        }
                                                    }
                                                })
                                            }
                                        }


                                    })

                                }
                            })
                        }
                    });

                } //for

            }
            // 无数据分情况：code=2：无数据；和code=3：没有关联工厂。暂时合并这两种提示。
            else { //无数据
                var str = '';
                str += '<div class="buyCar_zero">';
                str += '<img src="../../shopping/img/index/Grouping.png" />';
                str += '<h5>购物车还是空空的哦~</h5>';
                str += '<p>您可以';
                str += '<a href="index.html">去逛逛></a>';
                str += '</p>';
                str += '</div>';
                $('.car_box').html(str)
            }

        },
        error: function(request) { //请求出错
        }

    })
}

//弹窗关闭按钮
$(".know").click(function() {
    $(".meng").hide();
    $(".popupMsg").hide();
});
//弹窗关闭按钮--X
$(".close").click(function() {
    $(".meng").hide();
    $(".popupMsg").hide();
    $(".dialog").hide();
});

//    关闭参数弹窗
$(document).click(function() {
    $(".type_check").hide();
});


//	计算选中的数量--合计金额,合计数量
function calAll() {
    console.log("===")
    var goodsM = 0;
    var arrAM = $(".content").find(".choiceP");
    var arrTP = $(".content").find(".twoPri");
    var arrTPK = $(".content").find(".inputk2");
    var goodsM2 = 0; //总价格
    var goodsMnum = 0; //数量
    for (var i = 0; i < arrAM.length; i++) {
        if (arrAM.eq(i).attr("data-p") == "yes") {
            if (arrAM.eq(i).attr("data-is") == 1) {
                goodsM2 += parseFloat(arrTP.eq(i).attr("data-twoprice"));
                goodsMnum += parseFloat(arrTPK.eq(i).attr("data-oldcount"));
            }
        }
    }
    goodsM2 = goodsM2.toFixed(2);
    $(".allM").text(goodsM2);
    $(".goodsNum").text(goodsMnum);
}