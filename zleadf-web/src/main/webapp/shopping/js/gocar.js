$(function() {
    shopCar();

    $.ajax({
        type: "get",
        url: "/zlead/sys/message/notice/1/20/0" + "?t_=" + Math.random(),
        data: {},
        dataType: "json",
        async: true,
        success: function(data) {
            if (data.code == 1) {
                if (data.data.data) {
                    var tpl = "";
                    $.each(data.data.data, function(i, item) {

                        if (item.content.length >= 20) {
                            tpl += '<span>' + item.content.substr(0, 40) + '...</span>';
                        } else {
                            tpl += '<span>' + item.content + '</span>';
                        }
                    });
                    $("#info_ul").html(tpl);

                    // for (var i = 0; i < $("#info_ul").find('span').length; i++) {
                    //     var str = mitulineHide(40, $("#info_ul").find('span').eq(i).html()) // 设置最大字数
                    //     $("#info_ul").find('span').eq(i).html(str)
                    // }
                    //     // 消息栏最多两行，多余省略号
                    // function mitulineHide(num, str) {
                    //     if (str.length > num) {
                    //         var txt = str.substring(0, num - 1) + "...";
                    //         return txt
                    //     } else {
                    //         return str;
                    //     }
                    // };

                    // if (data.data.data.length == 0) {
                    //     $("#ss1").html(0);
                    // } else {
                    //     $("#ss1").html(data.data.data.length);
                    // }
                    // 渲染顶部消息栏
                    if (data.data.count > 99) {
                        $('#ss1').html('99+')
                    } else if (data.data.count <= 0) {
                        $('#ss1').hide()
                    } else {
                        $('#ss1').html(data.data.count)
                    }
                }
            }
        }
    });
    $(".info_xi").click(function() {
        window.location.href = "xiaoxi.html";
    });

    //删除商品
    $(document).on("click", ".delete", function() {
        // $(this).parent(".cart_zi").parent("li").hide();
        var cartIds = $(this).attr("data-id");
        deletcar(cartIds);
        return false;
    })

    // 跳转详情页

    $(document).on("click", "#cart_ul li", function() {
        var id = $(this).attr('goodsid');
        // 添加判断是否跳转详情页
        $.ajax({
            type: "get", //提交请求的方式
            cache: true, //是否有缓存
            url: "/query/goodsIsMarket", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            async: true, //是否异步
            data: {
                goodsId: id,
                t_: Math.random()
            },
            success: function(res) {
                if (res.code == 1) {
                    location.href = 'details.html?id=' + id;
                } else {
                    //window.location.reload();
                }
            }
        });
        return false;
    })
});

//购物车数据以及dom结构
function shopCar() {
    $.ajax({
        type: "POST", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/shopcart/shoppingCartGoods", //访问servlet的路径
        // url: '/zlead/shopcart/shoppingCartGoodsForNavi',
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {

        }, //把内容序列化
        async: true, //是否异步
        error: function(request) { //请求出错
        },
        success: function(res) { //获得返回值
            var str = "";
            var coun = 0;
            var jake = 0;
            var sta = "";
            if (res.code == 1) {
                if (res.data.length > 0) {
                    for (var i = 0; i < res.data.length; i++) {
                        for (var j = 0; j < res.data[i].goodsList.length; j++) {
                            //商品全部件数--不包含失效商品，isMarketable=0,已下架
                            if (res.data[i].goodsList[j].isMarketable == 0 || res.data[i].goodsList[j].stock == 0) {} else {
                                coun += res.data[i].goodsList[j].count;
                                jake += res.data[i].goodsList[j].count * (res.data[i].goodsList[j].goodsPrice).toFixed(2);
                            }
                            if (res.data[i].goodsList[j].isMarketable == 0 || res.data[i].goodsList[j].stock == 0) {
                                str += '<li style="display:none;" goodsId="' + res.data[i].goodsList[j].goodsId + '">';
                            } else {
                                str += '<li  goodsId="' + res.data[i].goodsList[j].goodsId + '">';
                            }
                            str += '<div class="cart_img">';
                            if (res.data[i].goodsList[j].goodsImg) {
                                str += '<img src="' + res.data[i].goodsList[j].goodsImg + '" style="width: 60px;height: 60px;"/>';
                            } else {
                                str += '<img src="../../shopping/img/index/sl3.png" style="width: 60px;height: 60px;"/>';
                            }

                            str += '</div>';
                            str += '<div class="cart_zi">';
                            if (res.data[i].goodsList[j].goodsName.length > 20) {
                                str += '<p class="cart_zip">' + res.data[i].goodsList[j].goodsName.substr(0, 20) + '...</p>';
                            } else {
                                str += '<p class="cart_zip">' + res.data[i].goodsList[j].goodsName + '</p>';
                            }

                            str += '<img data-id="' + res.data[i].goodsList[j].id + '" class="delete" src="../../shopping/img/head/File add (2).png" />';
                            str += '<p class="cart_money">¥<span id="jk">' + (res.data[i].goodsList[j].count * res.data[i].goodsList[j].goodsPrice).toFixed(2) + '</span></p>';
                            str += '</div>';

                            str += '</li>';
                        }
                    }
                }

                sta += '<span class="firsts">共<span id="jiashu" title="' + coun + '" style="margin-right: 0">' + coun + '</span>件商品</span>';
                sta += '<span class="firsts">合计: ¥<span id="jiake" title="' + jake.toFixed(2) + '" style="margin:0">' + jake.toFixed(2) + '</span></span>';

                $("#cart_shu").html(sta);
                $("#cart_ul").html(str);

                //数值
                $("#jiake").text(jake.toFixed(2));
                $("#jiashu").text(coun);
                if (parseInt(coun) > 99) {
                    $("#cont").text("99+");
                } else {
                    $("#cont").text(coun);
                }
            } else if (res.code == 2) {
                $("#jiake").text();
                $("#jiashu").text();
                $("#cont").text(0);
                $(".cart_shu").css("visibility", "hidden");
            }
        }
    })
}

//删除商品
function deletcar(cartIds) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/shopcart/deleteShoppingCart?cartIds=" + cartIds + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        // data: {
        //     cartIds: cartIds
        // }, //把内容序列化
        async: false, //是否异步
        error: function(request) { //请求出错
        },
        success: function(res) { //获得返回值
            window.location.reload(); //同步购物车页面和头部数据
            shopCar()
        }
    })
}