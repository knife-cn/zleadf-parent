// 设置全局参数
var pageNum = 1;
var maxCount = 0;

var QueryString = function(val) {
    var reg = new RegExp("(^|&)" + val + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return "";
};

// 立即够买
function jiago(goodsId) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/order/newConfirmOrder", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        data: {
            goodsId: goodsId,
            buyNum: 1
        },
        success: function(res) {
            if (res.code == 1) {
                var data = res.data;
                var datasss = JSON.stringify(data);
                localStorage.setItem("datas", datasss)
                    //localStorage.buyCar为空 标记不是从购物车进入支付页面
                window.localStorage.setItem("buyCar", '');
                window.location = "orderList.html"
            } else {
                $(".msg3").html(res.message);
                $(".meng3").show();
                $(".popupMsg3").show();
            }
            // 备注：res.code=1,成功，res.code=4,库存不足，暂时不区分非1情况，后期可以根据需要加
        }
    })
}

//加入购物车
function gocart(goodsId, buyType) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/shopcart/addShoppingCart", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            goodsId: goodsId,
            count: 1,
            buyType: buyType
        },
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                // alert("加入购物车成功！");
                $(".meng").show();
                shopCar(); //顶部购物车的接口
            } else {
                $(".msg3").html(res.message);
                $(".meng3").show();
                $(".popupMsg3").show();
            }
        },
        error: function(request) { //请求出错

        }
    })
}

//弹窗关闭按钮
$(".know").click(function() {
    $(".meng3").hide();
    $(".popupMsg3").hide();
});
//弹窗关闭按钮--X
$(".close").click(function() {
    $(".meng3").hide();
    $(".popupMsg3").hide();
});

//加入购物车弹窗内按钮
$("#tuicart").click(function() {
    $("#megou").hide();
    $("body").css('overflow-y', '');
});

// 去购物车
$("#fo").click(function() {
    window.location = "buyCar.html"
});

$("#head").load("head.html");

var sour = QueryString("sour");
var f = decodeURIComponent(QueryString("f"));
var b = decodeURIComponent(QueryString("b"));
var l = decodeURIComponent(QueryString("l"));
var m = decodeURIComponent(QueryString("m"));
var c = decodeURIComponent(QueryString("c"));
var x = decodeURIComponent(QueryString("x"));
var brandids = decodeURIComponent(QueryString("brandids"));
var listIds = decodeURIComponent(QueryString("listids"));
var catids = decodeURIComponent(QueryString("catids"));
var key = decodeURIComponent(QueryString("key"));
var p = 1;
var s = 10;
// //条件
var tip = [];
if (key) {
    tip.push(key);
}
if (b) {
    tip.push(b);
}
if (l) {
    tip.push(l);
}
if (m) {
    tip.push(m);
}
if (c) {
    tip.push(c);
}

if (tip && tip.length > 0) {
    $("#term").show();
    $("#chichi").html("关于“" + decodeURIComponent(tip.join("+")) + "”的搜索结果");

} else {
    $("#term").hide();
}
$("#xinxi").html("非常抱歉没有找到" + tip.join("+") + "的相关信息");
$("#ttpid").val(key);

function queryGoods(key, page) {
    $.ajax({
        type: "get",
        cache: false,
        // url: "/query/goods?sour=" + sour + "&key=" + key + "&f=" + f + "&b=" + b + "&m=" + m + "&l=" + l + "&c=" + c + "&brandids=" + brandids + "&listIds=" + listIds + "&catids=" + catids + "&x=" + x + "&p=" + page + "&s=" + s + "&t_=" + Math.random(),
        url: "/query/goods",
        data: {
            sour: sour,
            key: key,
            f: f,
            b: b,
            m: m,
            l: l,
            c: c,
            brandids: brandids,
            listIds: listIds,
            catids: catids,
            x: x,
            p: page,
            s: s
        },
        beforeSend: function() {
            $('.loading').show();
            $('.serp').hide()
            $('.serProut').hide()
        },
        complete: function() {
            $('.loading').hide();
            // $('.serp').show()
            // $('.serProut').show()
        },
        dataType: "json",
        success: function(res) {
            $("#resou").val(key);
            if (res.success) {
                maxCount = res.data.count;
                if (res.data.count == 0) {
                    //搜索没有数据！
                    /*$("#serp").hide();*/
                    $("#shulian").html("");
                    $(".tbpager").hide();
                    $('.serp').hide()
                    $('.serProut').hide()
                    $(".spwu").show();

                } else {
                    $('.serp').show()
                    $('.serProut').show()
                    $('.ta3').show() // 分页
                    if (key) {
                        $.ajax({
                            type: "post", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/hotWords/update", //访问servlet的路径
                            data: JSON.stringify({ wordName: key }),
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            contentType: "application/json",
                            async: true, //是否异步
                            error: function(request) { //请求出错
                                alert("添加出错");
                            },
                            success: function(res) { //获得返回值

                            }
                        });
                    }

                    $(".tbpager").show();
                    $(".spwu").hide();
                    var blist = res.data.term.blist; //品牌
                    var llist = res.data.term.llist; //系列
                    var mlist = res.data.term.mlist; //型号
                    var clist = res.data.term.clist; //分类
                    if (blist) {
                        var html = "";
                        for (var i = 0; i < blist.length; i++) {
                            html += "<li><span>" + blist[i].band_name + "</span></li>";
                        }
                        $("#ppl").html(html);
                    }
                    if (llist) {
                        var html = "";
                        for (var i = 0; i < llist.length; i++) {
                            html += "<li><span>" + llist[i].name + "</span></li>";
                        }
                        $("#xl").html(html);
                    }
                    if (mlist) {
                        var html = "";
                        for (var i = 0; i < mlist.length; i++) {
                            html += "<li><span>" + mlist[i].name + "</span></li>";
                        }
                        $("#xhao").html(html);
                    }
                    if (clist) {
                        var html = "";
                        for (var i = 0; i < clist.length; i++) {
                            html += "<li><span>" + clist[i].name + "</span></li>";
                        }
                        $("#fnei").html(html);
                    }
                    var tpp = "";
                    $.each(res.data.goods, function(i, item) {
                        tpp += '<li id= xsp' + item.id + '>';
                        tpp += '<div class="porut_img">';
                        tpp += '<img src="' + item.first_img + '" />';
                        tpp += '<div class="porut_mo">';
                        tpp += '<div class="porut_ku">';
                        tpp += '<div class="porut_kua" id="mam' + item.id + '">';
                        tpp += '<img class="porut_kuaimg" src="../../shopping/img/searchPage/gmai.png" />';
                        tpp += '<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgmai.png" />';
                        tpp += '<div class="gmtk">';
                        tpp += '<div class="gma">';
                        tpp += '<span class="maima">立即购买</span>';
                        tpp += '</div>';
                        tpp += '<div class="xjt">';
                        tpp += '<img src="../../shopping/img/searchPage/xjt.png" />';
                        tpp += '</div>';
                        tpp += '</div>';
                        tpp += '</div>';
                        tpp += '<div class="porut_kub" id="jgo' + item.id + '">';
                        tpp += '<img class="porut_kuaimg" src="../../shopping/img/searchPage/gwc.png" />';
                        tpp += '<img class="porut_hkuaimg" src="../../shopping/img/searchPage/hgwc.png" />';
                        tpp += '<div class="gmtk">';
                        tpp += '<div class="gma">';
                        tpp += '<span class="gogo">加入购物车</span>';
                        tpp += '</div>';
                        tpp += '<div class="xjt">';
                        tpp += '<img src="../../shopping/img/searchPage/xjt.png" />';
                        tpp += '</div>';
                        tpp += '</div>';
                        tpp += '</div>';
                        tpp += '</div>';
                        tpp += '</div>';
                        tpp += '</div>';
                        if (item.if_show_price == 1) {
                            tpp += '<p class="priceN">¥' + item.show_price.toFixed(2) + '</p>';
                        } else if (item.if_show_price == 0) {
                            tpp += '<p class="priceN">¥--</p>';
                        }
                        if (item.full_name.length > 20) {
                            tpp += '<p class="porut_pa">' + item.full_name.substring(0, 20) + '...</p>';
                        } else {
                            tpp += '<p class="porut_pa">' + item.full_name + '</p>';
                        }
                        tpp += '</li>';
                    });
                    $("#shulian").html(tpp);
                    //立即购买
                    $(".porut_kua").click(function() {
                        var goodsId = $(this).attr("id").substring(3);
                        // 添加判断是否跳转详情页
                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/query/goodsIsMarket", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            async: true, //是否异步
                            data: {
                                goodsId: goodsId,
                                t_: Math.random()
                            },
                            success: function(res) {
                                if (res.code == 1) {
                                    window.location.href = "details.html?id=" + goodsId + "";
                                } else {
                                    $(".msg3").html(res.message);
                                    $(".meng3").show();
                                    $(".popupMsg3").show();
                                }
                            }
                        });
                        return false;
                    });
                    //加入购物车
                    $(".porut_kub").click(function() {
                        var gogo = $(this).attr("id").substring(3);
                        var buyType = 0;
                        gocart(gogo, buyType);
                        return false;
                    });
                    $("#shulian li").click(function() {
                        var xsp = $(this).attr("id").substring(3);
                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/query/goodsIsMarket", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            async: true, //是否异步
                            data: {
                                goodsId: xsp,
                                t_: Math.random()
                            },
                            success: function(res) {
                                if (res.code == 1) {
                                    window.location.href = "details.html?id=" + xsp + "";
                                } else {
                                    window.location.reload()
                                }
                            }
                        });
                    });
                    // $(".tbapager").html(pagerUtil(s, res.data.count, p));

                    Page({
                        num: parseInt((maxCount + 10 - 1) / 10), //页码数
                        startnum: pageNum, //指定页码
                        elem: $('#page1'), //指定的元素
                        callback: function(n) { //回调函数,n是当前页码
                            pageNum = n;
                            queryGoods(key, n)
                        }
                    });

                    // 跳转指定页码：
                    $('.button').unbind();
                    $('.button').click(function() {
                        pageNum = parseInt($(this).siblings('input').val());
                        if (pageNum < 1) {
                            pageNum = 1;
                            $(this).siblings('input').val(pageNum);
                        }
                        if (pageNum > parseInt((maxCount + 10 - 1) / 10)) {
                            pageNum = parseInt((maxCount + 10 - 1) / 10);
                            $(this).siblings('input').val(pageNum);
                        }
                        queryGoods(key, pageNum)

                    })
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
                    })



                }
            } else {
                $(".spwu").show();
            }
        }
    });
}

queryGoods(key);


//分页
function turnPage(currentPage) {
    p = currentPage;
    queryGoods();
}

//点击品牌
$(document).on("click", "#ppl li", function() {
    b = $(this).text();
    window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + encodeURIComponent(f) + "&b=" + encodeURIComponent(b) + "&l=" + "&m=" + "&c=" ;
});
//点击系列
$(document).on("click", "#xl li", function() {
    l = $(this).text();
    window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + encodeURIComponent(f) + "&b=" + encodeURIComponent(b) + "&l=" + encodeURIComponent(l) + "&m=" + "&c=";
});
//点击型号
$(document).on("click", "#xhao li", function() {
    m = $(this).text();
    window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + encodeURIComponent(f) + "&b=" + encodeURIComponent(b) + "&l=" + encodeURIComponent(l) + "&m=" + encodeURIComponent(m) + "&c=";
});
//点击分类
$(document).on("click", "#fnei li", function() {
    c = $(this).text();
    window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + encodeURIComponent(f) + "&b=" + encodeURIComponent(b) + "&l=" + encodeURIComponent(l) + "&m=" + encodeURIComponent(m) + "&c=" + encodeURIComponent(c);
});
//搜索框enter事件
$("#ttpid").on("keyup", function(event) {
    if (event.keyCode === 13) {
        key = $("#ttpid").val();
        window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + encodeURIComponent(f) + "&b=" + encodeURIComponent(b) + "&l=" + encodeURIComponent(l) + "&m=" + encodeURIComponent(m) + "&c=" + encodeURIComponent(c);
    }
});
//点击搜索框
$("#search_iconxin").on("click", function() {
    key = $("#resou").val();
    if (key != '') {
        // window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + f + "&b=" + encodeURIComponent(b) + "&l=" + encodeURIComponent(l) + "&m=" + encodeURIComponent(m) + "&c=" + encodeURIComponent(c);
        window.location.href = "/shopping/searchPage?sour=3&key=" + encodeURIComponent(key) + "&f=" + encodeURIComponent(f) + "&b=" + "&l=" + "&m=" + "&c=";
    } else {
        alert('请输入热搜词！')
    }
});
//点击更多
$(".serp_gd .btn").click(function() {
    $(this).parent().siblings(".serch_mc").css("height", "auto");
    $(this).hide();
    $(this).next().show();
});
//点击收起
$(".serp_gd .btn1").click(function() {
    $(this).parent().siblings(".serch_mc").css("height", "61px");
    $(this).hide();
    $(this).prev().show();
});

$(".btnfan").click(function() {
    history.go(-1);
});
$(".btnshou").click(function() {
    window.location.href = "index.html";
});