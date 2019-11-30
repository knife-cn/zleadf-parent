//获取url中的值
var getParam = function(name) {
    var search = document.location.search;
    var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if (null != matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    items = items ? encodeURIComponent(items) : '';

    return items;
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
                var data = res.data
                var datasss = JSON.stringify(data)
                localStorage.setItem("datas", datasss)

            }
            window.location = "orderList.html"
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
                window.location.reload();
            } else {
            }

        },
        error: function(request) { //请求出错

        }
    })
}
// 什么也没有，查所有
function Nochaxun(ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?p=" + ye + "&s=" + zong + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有关键字 当前页 显示条数
function chaxunkey(resou, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) +   "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);
                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有分类 当前页 显示条数
function chaxunfen(fen, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?c=" + encodeURIComponent(fen) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) +   "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有系列 当前页 显示条数
function chaxunxl(xl, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?l=" + encodeURIComponent(xl) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) +   "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有型号 当前页 显示条数
function chaxunxh(xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) +  "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId = $(this).attr("id").substring(3);

                jiago(goodsId);
            })
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有品牌 当前页 显示条数
function chaxunpp(pp, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?b=" + encodeURIComponent(pp) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                var goodsId = $(this).attr("id").substring(3);

                jiago(goodsId);
            })
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有品牌与系列
function chaxunppxl(pp, xl, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) +  "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
//只有品牌与型号
function chaxunppxh(pp, xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?b=" + encodeURIComponent(pp) + "&m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
//只有系列与型号
function chaxunxlxh(xl, xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 只有关键词与品牌
function chaxunkeypp(resou, pp, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
//只有关键词与型号
function chaxunkeyxh(resou, xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true,
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    });
}
// 只有关键词与系列
function chaxunkeyxl(resous, xl, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&l=" + encodeURIComponent(xl) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true,
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    });
}
// 只有关键词与品牌和型号
function chaxunkeyppxh(resous, pp, xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resous) + "&b=" + encodeURIComponent(pp) + "&m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong)  +  "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 查询 关键词，品牌，系列
function chaxunkeyppxl(resou, pp, xl, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 查询 关键词，品牌，系列，型号
function chaxunkeyppxlxh(resou, pp, xl, xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 查询 关键词，品牌，系列，型号，分类
function chaxunkeyppxlxhfen(resou, pp, xl, xh, fen, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&c=" + encodeURIComponent(fen) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);
                var buyType = 0;
                gocart(gogo, buyType);
            })

        }
    })
}
// 查询 关键词，品牌，系列，型号，分类
function chaxunkeyppxlxhfen(resou, pp, xl, xh, fen, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&c=" + encodeURIComponent(fen) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 查询 关键词，品牌，系列，分类
function chaxunkeyppxlfen(resou, pp, xl, fen, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&c=" + encodeURIComponent(fen) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}
// 查询品牌，系列，型号
function chaxunppxlxh(pp, xl, xh, ye, zong) {
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/goods?b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&p=" + encodeURIComponent(ye) + "&s=" + encodeURIComponent(zong) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错

        },
        success: function(res) { //获得返回值
            var tpp = "";
            $.each(res.data.goods, function(i, item) {
                tpp += '<li>';
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
                // tpp+='<p class="porut_p">¥'+item.show_price+'</p>';
                tpp += '<p class="porut_p" style = visibility:' + (item.if_show_price == 1 ? "visible" : "hidden") + '>¥' + item.show_price + '</p>';
                tpp += '<a href="details.html?id=' + item.id + '"><p class="porut_pa">' + item.full_name + '</p></a>';
                tpp += '</li>';

            });
            $("#shulian").html(tpp);

            //立即购买
            $(".porut_kua").click(function() {
                    var goodsId = $(this).attr("id").substring(3);

                    jiago(goodsId);
                })
                //加入购物车
            $(".porut_kub").click(function() {
                var gogo = $(this).attr("id").substring(3);

                var buyType = 0;

                gocart(gogo, buyType);
            })

        }
    })
}

window.onload = (function() {
    window.sessionStorage.setItem("ppname", "");
    window.sessionStorage.setItem("xlname", "");
    window.sessionStorage.setItem("xhname", "");
    window.sessionStorage.setItem("fenname", "");
    $("#head").load("head.html");
    //加载品牌，系列，分页，类型
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/query/term?t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        error: function(request) { //请求出错
        },
        success: function(res) { //获得返回值
            if (res.code == 1 || res.success == true) {
                var tpl = "";
                $.each(res.data.brands, function(i, item) {
                    tpl += '<li id=tp' + item.id + '>';
                    tpl += '<span>' + item.name + '</span>';
                    tpl += '</li>';
                });
                $("#ppl").html(tpl);
                var apl = "";
                $.each(res.data.crmPrdLists, function(i, item) {
                    apl += '<li id=ap' + item.id + '>';
                    apl += '<span>' + item.name + '</span>';
                    apl += '</li>';
                });
                $("#xl").html(apl);
                var spl = "";
                $.each(res.data.crmPrdModels, function(i, item) {
                    spl += '<li id=sp' + item.id + '>';
                    spl += '<span>' + item.name + '</span>';
                    spl += '</li>';
                });
                $("#xhao").html(spl);
                var fpl = "";
                $.each(res.data.cats, function(i, item) {
                    fpl += '<li id=fp' + item.id + '>';
                    fpl += '<span>' + item.name + '</span>';
                    fpl += '</li>';
                });
                $("#fnei").html(fpl);
                // 点击品牌
                $("#ppl li").click(function() {
                    $(".spwu").hide();
                    $("#chichi").html("");
                    pp = $(this).children().html();
                    $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(pp) + "的相关信息");
                    if (resou == "" || resou == null || resou == undefined) {
                        //只查询品牌
                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/query/goods?b=" + encodeURIComponent(pp) + "&t_=" + Math.random(),
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            async: false,
                            error: function(request) { //请求出错

                            },
                            success: function(res) { //获得返回值
                                if (res.code == 1) {
                                    if (res.data.count == 0) {
                                        //搜索没有数据！

                                        $(".spwu").show();

                                    } else {
                                        yeshu = Math.ceil(res.data.count / 10);
                                    }
                                } else {


                                    if (res.message == "搜索商品数据失败") {
                                        //搜索搜索商品数据失败

                                        $(".spwu").show();

                                    } else if (res.message == "用户未登录") {
                                        //用户未登录
                                        // window.location.href="Land.thml";
                                    } else {
                                        //用户未关联工厂！
                                        window.location.href = "Associated_factory1.html";
                                    }
                                }
                            }
                        });
                        //optional set
                        pageNav.pre = "上一页";
                        pageNav.next = "下一页";

                        pageNav.fn = function(p, pn) {

                            chaxunpp(pp, p, 10)

                        };
                        if (yeshu == 1) {
                            pageNav.go(1, yeshu);
                            $("#tbpager").show();
                            $("#tz").css("background", "silver");
                        } else if (yeshu > 1) {
                            pageNav.go(1, yeshu);
                            $("#tbpager").show();
                        }

                        $("#tz").click(function() {
                            if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                            } else {

                                pageNav.go($("#tiaozhuang").val(), yeshu);
                            }

                        });


                    } else {
                        // 查询了关键词与品牌
                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&t_=" + Math.random(),
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            async: false,
                            error: function(request) { //请求出错

                            },
                            success: function(res) { //获得返回值
                                if (res.code == 1) {
                                    if (res.data.count == 0) {
                                        //搜索没有数据！
                                        $(".spwu").show();
                                    } else {
                                        yeshu = Math.ceil(res.data.count / 10);
                                    }
                                } else {
                                    if (res.message == "搜索商品数据失败") {
                                        //搜索搜索商品数据失败
                                        $(".spwu").show();
                                    } else if (res.message == "用户未登录") {
                                        //用户未登录
                                        // window.location.href="Land.thml";
                                    } else {
                                        //用户未关联工厂！
                                        window.location.href = "Associated_factory1.html";
                                    }
                                }
                            }
                        });
                        //optional set
                        pageNav.pre = "上一页";
                        pageNav.next = "下一页";

                        pageNav.fn = function(p, pn) {
                            chaxunkeypp(resou, pp, p, 10);
                        };
                        if (yeshu == 1) {
                            pageNav.go(1, yeshu);
                            $("#tbpager").show();
                            $("#tz").css("background", "silver");
                        } else if (yeshu > 1) {
                            pageNav.go(1, yeshu);
                            $("#tbpager").show();
                        }
                        $("#tz").click(function() {
                            if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                            } else {
                                pageNav.go($("#tiaozhuang").val(), yeshu);
                            }

                        });

                    }

                });

                /// 点击系列
                $("#xl li").click(function() {
                    $(".spwu").hide();
                    $("#chichi").html("");
                    xl = $(this).children().html();
                    $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(xl) + "的相关信息");
                    //判断是否有关键词
                    if (resou == "" || resou == undefined || resou == null) {
                        //判断是否有品牌
                        if (pp == "" || pp == undefined || pp == null) {
                            //没有关键词，没有品牌 只查系列
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/query/goods?l=" + encodeURIComponent(xl) + "&t_=" + Math.random(),
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                async: false,
                                error: function(request) { //请求出错

                                },
                                success: function(res) { //获得返回值
                                    if (res.code == 1) {
                                        if (res.data.count == 0) {
                                            //搜索没有数据！
                                            $(".spwu").show();
                                        } else {
                                            yeshu = Math.ceil(res.data.count / 10);
                                        }
                                    } else {
                                        if (res.message == "搜索商品数据失败") {
                                            //搜索搜索商品数据失败
                                            $(".spwu").show();
                                        } else if (res.message == "用户未登录") {
                                            //用户未登录
                                            // window.location.href="Land.thml";
                                        } else {
                                            //用户未关联工厂！
                                            window.location.href = "Associated_factory1.html";
                                        }
                                    }
                                }
                            });

                            //optional set
                            pageNav.pre = "上一页";
                            pageNav.next = "下一页";

                            pageNav.fn = function(p, pn) {
                                chaxunxl(xl, p, 10);
                            };
                            if (yeshu == 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                                $("#tz").css("background", "silver");
                            } else if (yeshu > 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                            }

                            $("#tz").click(function() {
                                if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                } else {

                                    pageNav.go($("#tiaozhuang").val(), yeshu);
                                }

                            });


                        } else {
                            //没有关键词，有品牌，查品牌和系列
                            //chaxunppxl
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/query/goods?b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&t_=" + Math.random(),
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                async: false,
                                error: function(request) { //请求出错

                                },
                                success: function(res) { //获得返回值
                                    if (res.code == 1) {
                                        if (res.data.count == 0) {
                                            //搜索没有数据！

                                            $(".spwu").show();

                                        } else {
                                            yeshu = Math.ceil(res.data.count / 10);
                                        }
                                    } else {


                                        if (res.message == "搜索商品数据失败") {
                                            //搜索搜索商品数据失败

                                            $(".spwu").show();

                                        } else if (res.message == "用户未登录") {
                                            //用户未登录
                                            // window.location.href="Land.thml";
                                        } else {
                                            //用户未关联工厂！
                                            window.location.href = "Associated_factory1.html";
                                        }
                                    }
                                }
                            });

                            //optional set
                            pageNav.pre = "上一页";
                            pageNav.next = "下一页";

                            pageNav.fn = function(p, pn) {

                                chaxunppxl(pp, xl, p, 10);

                            };
                            if (yeshu == 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                                $("#tz").css("background", "silver");
                            } else if (yeshu > 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                            }

                            $("#tz").click(function() {
                                if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                } else {

                                    pageNav.go($("#tiaozhuang").val(), yeshu);
                                }

                            });

                        }
                    } else {
                        //判断是否有品牌
                        if (pp == "" || pp == undefined || pp == null) {
                            // 有关键字，没有品牌  只查关键字和系列
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/query/goods?key=" + encodeURIComponent(resou) + "&l=" + encodeURIComponent(xl) + "&t_=" + Math.random(),
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                async: false,
                                error: function(request) { //请求出错

                                },
                                success: function(res) { //获得返回值
                                    if (res.code == 1) {
                                        if (res.data.count == 0) {
                                            //搜索没有数据！


                                            $(".spwu").show();

                                        } else {
                                            yeshu = Math.ceil(res.data.count / 10);

                                        }
                                    } else {


                                        if (res.message == "搜索商品数据失败") {
                                            //搜索搜索商品数据失败

                                            $(".spwu").show();

                                        } else if (res.message == "用户未登录") {
                                            //用户未登录
                                            // window.location.href="Land.thml";
                                        } else {
                                            //用户未关联工厂！
                                            window.location.href = "Associated_factory1.html";
                                        }
                                    }
                                }
                            });

                            //optional set
                            pageNav.pre = "上一页";
                            pageNav.next = "下一页";

                            pageNav.fn = function(p, pn) {

                                chaxunkeyxl(xl, p, 10);
                            };
                            if (yeshu == 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                                $("#tz").css("background", "silver");
                            } else if (yeshu > 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                            }
                            $("#tz").click(function() {
                                if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                } else {

                                    pageNav.go($("#tiaozhuang").val(), yeshu);
                                }

                            });
                        } else {
                            // 有关键字，有品牌  要查关键字，品牌和系列
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&t_=" + Math.random(),
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                async: false,
                                error: function(request) { //请求出错

                                },
                                success: function(res) { //获得返回值
                                    if (res.code == 1) {
                                        if (res.data.count == 0) {
                                            //搜索没有数据！

                                            $(".spwu").show();

                                        } else {
                                            yeshu = Math.ceil(res.data.count / 10);

                                        }
                                    } else {


                                        if (res.message == "搜索商品数据失败") {
                                            //搜索搜索商品数据失败

                                            $(".spwu").show();

                                        } else if (res.message == "用户未登录") {
                                            //用户未登录
                                            // window.location.href="Land.thml";
                                        } else {
                                            //用户未关联工厂！
                                            window.location.href = "Associated_factory1.html";
                                        }
                                    }
                                }
                            });

                            // chaxunkeyppxl
                            //optional set
                            pageNav.pre = "上一页";
                            pageNav.next = "下一页";

                            pageNav.fn = function(p, pn) {

                                chaxunkeyppxl(resou, pp, xl, p, 10);
                            };
                            if (yeshu == 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                                $("#tz").css("background", "silver");
                            } else if (yeshu > 1) {
                                pageNav.go(1, yeshu);
                                $("#tbpager").show();
                            }
                            $("#tz").click(function() {
                                if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                } else {
                                    pageNav.go($("#tiaozhuang").val(), yeshu);
                                }

                            });


                        }
                    }

                });

                //点击型号
                $("#xhao li").click(function() {
                    $(".spwu").hide();
                    $("#chichi").html("");
                    xh = $(this).children().html();
                    $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(xh) + "的相关信息");
                    if (resou == "" || resou == undefined || resou == null) {
                        //没有关键词
                        if (pp == "" || pp == undefined || pp == null) {
                            //没有品牌
                            if (xl == "" || xl == undefined || xl == null) {
                                //没有关键词 没有品牌,没系列，只有型号  只查型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据！
                                                $(".spwu").show();
                                            } else {
                                                yeshu = Math.ceil(res.data.count / 10);
                                            }
                                        } else {
                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败
                                                $(".spwu").show();
                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {

                                    chaxunxh(xh, p, 10)

                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }
                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {
                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });
                            } else {
                                //没有关键词 没有品牌,有系列，型号  只查系列，型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据！
                                                $(".spwu").show();
                                            } else {
                                                yeshu = Math.ceil(res.data.count / 10);
                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {

                                    chaxunxlxh(xl, xh, p, 10)

                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }
                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {

                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });
                            }
                        } else {
                            //有品牌
                            if (xl == "" || xl == undefined || xl == null) {
                                //没系列
                                //没系列，有品牌,有型号  只查品牌,型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?b=" + encodeURIComponent(pp) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据！

                                                $(".spwu").show();

                                            } else {
                                                yeshu = Math.ceil(res.data.count / 10);
                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {

                                    chaxunppxh(pp, xh, p, 10)

                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }
                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {

                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });


                            } else {
                                //有系列

                                // 查品牌,系列,型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据！

                                                $(".spwu").show();

                                            } else {
                                                yeshu = Math.ceil(res.data.count / 10);
                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {
                                    chaxunppxlxh(pp, xl, xh, p, 10);
                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }
                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {
                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });

                            }
                        }
                    } else {
                        //有关键词
                        if (pp == "" || pp == undefined || pp == null) {
                            //没有品牌
                            if (xl == "" || xl == undefined || xl == null) {
                                //有关键词 没有品牌，没有系列 有型号  只查关键词，型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?key=" + encodeURIComponent(resou) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据！

                                                $(".spwu").show();

                                            } else {

                                                yeshu = Math.ceil(res.data.count / 10);

                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {
                                    chaxunkeyxh(resou, xh, p, 10)
                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }

                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {

                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });
                            } else {
                                //有关键词 没有品牌，有系列 有型号  只查关键词，系列，型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?key=" + encodeURIComponent(resou) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {

                                                //搜索没有数据！

                                                $(".spwu").show();

                                            } else {

                                                yeshu = Math.ceil(res.data.count / 10);

                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {

                                    chaxunxh(xh, p, 10)

                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }

                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {

                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });
                            }

                        } else {
                            //有品牌
                            if (xl == "" || xl == undefined || xl == null) {
                                //没系列
                                //有关键词,有品牌,没系列，有型号  只关键词,品牌,查型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据;

                                                $(".spwu").show();

                                            } else {
                                                yeshu = Math.ceil(res.data.count / 10);

                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {

                                    chaxunkeyppxh(xh, p, 10)
                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }
                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {

                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });
                            } else {
                                //有系列
                                // 查关键词,品牌,系列,型号
                                $.ajax({
                                    type: "get", //提交请求的方式
                                    cache: true, //是否有缓存
                                    url: "/query/goods?key=" + encodeURIComponent(resou) + "&b=" + encodeURIComponent(pp) + "&l=" + encodeURIComponent(xl) + "&m=" + encodeURIComponent(xh) + "&t_=" + Math.random(),
                                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                    async: false,
                                    error: function(request) { //请求出错

                                    },
                                    success: function(res) { //获得返回值
                                        if (res.code == 1) {
                                            if (res.data.count == 0) {
                                                //搜索没有数据！

                                                $(".spwu").show();

                                            } else {
                                                yeshu = Math.ceil(res.data.count / 10);
                                            }
                                        } else {


                                            if (res.message == "搜索商品数据失败") {
                                                //搜索搜索商品数据失败

                                                $(".spwu").show();

                                            } else if (res.message == "用户未登录") {
                                                //用户未登录
                                                // window.location.href="Land.thml";
                                            } else {
                                                //用户未关联工厂！
                                                window.location.href = "Associated_factory1.html";
                                            }
                                        }
                                    }
                                });
                                //optional set
                                pageNav.pre = "上一页";
                                pageNav.next = "下一页";

                                pageNav.fn = function(p, pn) {

                                    chaxunkeyppxlxh(resou, pp, xl, xh, p, 10);

                                };
                                if (yeshu == 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                    $("#tz").css("background", "silver");
                                } else if (yeshu > 1) {
                                    pageNav.go(1, yeshu);
                                    $("#tbpager").show();
                                }
                                $("#tz").click(function() {
                                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                                    } else {

                                        pageNav.go($("#tiaozhuang").val(), yeshu);
                                    }

                                });


                            }
                        }
                    }
                });

                //点击分类
                $("#fnei li").click(function() {
                    $(".spwu").hide();
                    $("#chichi").html("");
                    fen = $(this).children().html();
                    $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(fen) + "的相关信息");
                    $.ajax({
                        type: "get", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/query/goods?c=" + encodeURIComponent(fen) + "&t_=" + Math.random(),
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        async: false,
                        error: function(request) { //请求出错
                        },
                        success: function(res) { //获得返回值
                            if (res.code == 1) {
                                if (res.data.count == 0) {
                                    //搜索没有数据！

                                    $(".spwu").show();

                                } else {
                                    yeshu = Math.ceil(res.data.goods.length / 10);
                                }
                            } else {
                                if (res.message == "搜索商品数据失败") {
                                    //搜索搜索商品数据失败
                                    $(".spwu").show();
                                } else if (res.message == "用户未登录") {
                                    //用户未登录
                                    // window.location.href="Land.thml";
                                } else {
                                    //用户未关联工厂！
                                    window.location.href = "Associated_factory1.html";
                                }
                            }
                        }
                    });
                    //optional set
                    pageNav.pre = "上一页";
                    pageNav.next = "下一页";
                    pageNav.fn = function(p, pn) {
                        chaxunfen(fen, p, 10)
                    };
                    if (yeshu == 1) {
                        pageNav.go(1, yeshu);
                        $("#tbpager").show();
                        $("#tz").css("background", "silver");
                    } else if (yeshu > 1) {
                        pageNav.go(1, yeshu);
                        $("#tbpager").show();
                    }
                    $("#tz").click(function() {
                        if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                        } else {

                            pageNav.go($("#tiaozhuang").val(), yeshu);
                        }

                    });
                });
            }
        }
    });
    var resou = window.sessionStorage.getItem("resou");
    var yeshu = "";
    var pp = "";
    var xl = "";
    var xh = "";
    var fen = "";
    var ye = 1;
    var zong = 10;

    //判断是从哪调过来的
    //resou没有值，代表从url或者是跳转页面
    if (resou == "" || resou == undefined || resou == null) {
        //判断是不是选择了品牌
        pp = getParam("b");
        if (pp == "" || pp == undefined || pp == null) {
            //判断是不是选择了系列
            xl = getParam("l");
            if (xl == "" || xl == undefined || xl == null) {
                //判断是不是选择了分类
                fen = getParam("c");
                if (fen == "" || fen == undefined || fen == null) {
                    //是跳转页面,什么也没传,要查所有
                    $.ajax({
                        type: "get", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/query/goods?t_="+Math.random(), //访问servlet的路径
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        async: false,
                        error: function(request) { //请求出错
                        },
                        success: function(res) { //获得返回值
                            if (res.code == 1) {
                                if (res.data.count == 0) {
                                    //搜索没有数据！
                                    $("#xinxi").html("非常抱歉没有找到相关信息");
                                    $(".spwu").show();
                                } else {
                                    yeshu = Math.ceil(res.data.count / 10);

                                }
                            } else {
                                if (res.message == "搜索商品数据失败") {
                                    //搜索搜索商品数据失败
                                    $("#xinxi").html("非常抱歉没有找到相关信息");
                                    $(".spwu").show();
                                } else if (res.message == "用户未登录") {
                                    //用户未登录
                                    // window.location.href="Land.thml";
                                } else {
                                    //用户未关联工厂！
                                    window.location.href = "Associated_factory1.html";
                                }
                            }
                        }
                    })

                    //optional set
                    pageNav.pre = "上一页";
                    pageNav.next = "下一页";

                    pageNav.fn = function(p, pn) {
                        Nochaxun(p, 10);
                    };
                    if (yeshu == 1) {
                        pageNav.go(1, yeshu);
                        $("#tbpager").show();
                        $("#tz").css("background", "silver");
                    } else if (yeshu > 1) {
                        pageNav.go(1, yeshu);
                        $("#tbpager").show();
                    }

                    $("#tz").click(function() {
                        if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                        } else {

                            pageNav.go($("#tiaozhuang").val(), yeshu);
                        }

                    });

                } else {
                    //选择了分类
                    $.ajax({
                        type: "get", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/query/goods?c=" + encodeURIComponent(fen) + "&t_=" + Math.random(),
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        async: false,
                        error: function(request) { //请求出错
                        },
                        success: function(res) { //获得返回值
                            if (res.code == 1) {
                                if (res.data.count == 0) {
                                    //搜索没有数据！
                                    $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(fen) + "相关信息");
                                    $(".spwu").show();

                                } else {
                                    yeshu = Math.ceil(res.data.count / 10);
                                }
                            } else {
                                if (res.message == "搜索商品数据失败") {
                                    //搜索搜索商品数据失败

                                    $(".spwu").show();

                                } else if (res.message == "用户未登录") {
                                    //用户未登录
                                    // window.location.href="Land.thml";
                                } else {
                                    //用户未关联工厂！
                                    window.location.href = "Associated_factory1.html";
                                }
                            }
                        }
                    });

                    //optional set
                    pageNav.pre = "上一页";
                    pageNav.next = "下一页";

                    pageNav.fn = function(p, pn) {

                        chaxunfen(fen, p, 10);

                    };

                    if (yeshu == 1) {
                        pageNav.go(1, yeshu);
                        $("#tbpager").show();
                        $("#tz").css("background", "silver");
                    } else if (yeshu > 1) {
                        pageNav.go(1, yeshu);
                        $("#tbpager").show();
                    }

                    $("#tz").click(function() {
                        if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                        } else {

                            pageNav.go($("#tiaozhuang").val(), yeshu);
                        }

                    });
                }
            } else {
                //选择了系列

                $.ajax({
                    type: "get", //提交请求的方式
                    cache: true, //是否有缓存
                    url: "/query/goods?l=" + encodeURIComponent(xl) + "&t_=" + Math.random(),
                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                    async: false,
                    error: function(request) { //请求出错
                    },
                    success: function(res) { //获得返回值
                        if (res.code == 1) {
                            if (res.data.count == 0) {
                                //搜索没有数据！
                                $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(xl) + "相关信息");
                                $(".spwu").show();

                            } else {
                                yeshu = Math.ceil(res.data.count / 10);

                            }
                        } else {
                            if (res.message == "搜索商品数据失败") {
                                //搜索搜索商品数据失败

                                $(".spwu").show();

                            } else if (res.message == "用户未登录") {
                                //用户未登录
                                // window.location.href="Land.thml";
                            } else {
                                //用户未关联工厂！
                                window.location.href = "Associated_factory1.html";
                            }
                        }
                    }
                });

                //optional set
                pageNav.pre = "上一页";
                pageNav.next = "下一页";

                pageNav.fn = function(p, pn) {
                    chaxunxl(xl, p, 10);

                };
                if (yeshu == 1) {
                    pageNav.go(1, yeshu);
                    $("#tbpager").show();
                    $("#tz").css("background", "silver");
                } else if (yeshu > 1) {
                    pageNav.go(1, yeshu);
                    $("#tbpager").show();
                }
                $("#tz").click(function() {
                    if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                    } else {

                        pageNav.go($("#tiaozhuang").val(), yeshu);
                    }

                });
            }
        } else {
            //选择了品牌
            $.ajax({
                type: "get", //提交请求的方式
                cache: true, //是否有缓存
                url: "/query/goods?b=" + encodeURIComponent(pp) + "&t_=" + Math.random(),
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                async: false,
                error: function(request) { //请求出错
                },
                success: function(res) { //获得返回值
                    if (res.code == 1) {
                        if (res.data.count == 0) {
                            //搜索没有数据！
                            $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(pp) + "相关信息");
                            $(".spwu").show();

                        } else {
                            yeshu = Math.ceil(res.data.count / 10);
                        }
                    } else {
                        if (res.message == "搜索商品数据失败") {
                            //搜索搜索商品数据失败

                            $(".spwu").show();


                        } else if (res.message == "用户未登录") {
                            //用户未登录
                            // window.location.href="Land.thml";
                        } else {
                            //用户未关联工厂！
                            window.location.href = "Associated_factory1.html";
                        }
                    }
                }
            });



            //optional set
            pageNav.pre = "上一页";
            pageNav.next = "下一页";


            pageNav.fn = function(p, pn) {

                chaxunpp(pp, p, 10);

            };
            if (yeshu == 1) {
                pageNav.go(1, yeshu);
                $("#tbpager").show();
                $("#tz").css("background", "silver");
            } else if (yeshu > 1) {
                pageNav.go(1, yeshu);
                $("#tbpager").show();
            }

            $("#tz").click(function() {
                if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                } else {

                    pageNav.go($("#tiaozhuang").val(), yeshu);
                }

            });
        }
    } else {
        //resou有值，代表是热搜
        $("#chichi").html("关于“" + decodeURIComponent(resou) + "”的搜索结果");
        $("#ttpid").val(decodeURIComponent(resou));
        $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(resou) + "的相关信息");
        $.ajax({
            type: "get", //提交请求的方式
            cache: true, //是否有缓存
            url: "/query/goods?key=" + encodeURIComponent(resou) + "&t_=" + Math.random(),
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            async: false,
            error: function(request) { //请求出错
            },
            success: function(res) { //获得返回值
                if (res.code == 1) {
                    if (res.data.count == 0) {

                        //搜索没有数据！
                        $(".spwu").show();

                    } else {

                        yeshu = Math.ceil(res.data.count / 10);
                        $.ajax({
                            type: "post", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/hotWords/update", //访问servlet的路径
                            data: JSON.stringify({ wordName: resou }),
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            contentType: "application/json",
                            async: true, //是否异步
                            error: function(request) { //请求出错
                            },
                            success: function(res) { //获得返回值
                            }
                        });
                    }
                } else {
                    if (res.message == "搜索商品数据失败") {
                        //搜索搜索商品数据失败
                        $(".spwu").show();

                    } else if (res.message == "用户未登录") {
                        //用户未登录
                        // window.location.href="Land.thml";
                    } else {
                        //用户未关联工厂！
                        window.location.href = "Associated_factory1.html";
                    }
                }
            }
        });

        //optional set
        pageNav.pre = "上一页";
        pageNav.next = "下一页";

        pageNav.fn = function(p, pn) {

            chaxunkey(resou, p, 10);
        };
        if (yeshu == 1) {
            pageNav.go(1, yeshu);
            $("#tbpager").show();
            $('#tz').css('background', "silver")
        } else if (yeshu > 1) {
            pageNav.go(1, yeshu);
            $("#tbpager").show();
        }
        $("#tz").click(function() {
            if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

            } else {

                pageNav.go($("#tiaozhuang").val(), yeshu);
            }

        });

    }

    // 点击搜索
    $("#search_iconxin").click(function() {
        $(".spwu").hide();
        resou = $("#ttpid").val();
        if (resou == "" || resou == undefined || resou == null) {
        } else {
            $("#chichi").html("关于“" + decodeURIComponent(resou) + "”的搜索结果");
            $("#xinxi").html("非常抱歉没有找到" + decodeURIComponent(resou) + "的相关信息");
            $.ajax({
                type: "get", //提交请求的方式
                cache: true, //是否有缓存
                url: "/query/goods?key=" + encodeURIComponent(resou) + "&t_=" + Math.random(),
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                async: false,
                error: function(request) { //请求出错
                },
                success: function(res) { //获得返回值
                    if (res.code == 1) {
                        if (res.data.count == 0) {
                            //搜索没有数据！
                            $(".spwu").show();

                        } else {

                            yeshu = Math.ceil(res.data.count / 10);
                            $.ajax({
                                type: "post", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/hotWords/update", //访问servlet的路径
                                data: JSON.stringify({ wordName: resou }),
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                contentType: "application/json",
                                async: true, //是否异步
                                error: function(request) { //请求出错
                                },
                                success: function(res) { //获得返回值
                                }
                            });
                        }
                    } else {

                        if (res.message == "搜索商品数据失败") {
                            //搜索搜索商品数据失败
                            $(".spwu").show();

                        } else if (res.message == "用户未登录") {
                            //用户未登录
                            // window.location.href="Land.thml";
                        } else {
                            $(".spwu").show();

                        }
                    }
                }
            });
            //optional set
            pageNav.pre = "上一页";
            pageNav.next = "下一页";

            pageNav.fn = function(p, pn) {

                chaxunkey(resou, p, 10);

            };
            if (yeshu != 0) {
                pageNav.go(1, yeshu);
                $("#tbpager").show();
            }
            $("#tz").click(function() {
                if ($("#tiaozhuang").val() > yeshu || $("#tiaozhuang").val() == 0 || $("#tiaozhuang").val() == "" || $("#tiaozhuang").val() == null || $("#tiaozhuang").val() == undefined) {

                } else {

                    pageNav.go($("#tiaozhuang").val(), yeshu);
                }

            });

        }

    });

    $(".btnfan").click(function() {
        history.go(-1);
    });
    $(".btnshou").click(function() {
        window.location.href = "index.html";
    });
    // 点击事件
    $("#btn").click(function() {
        //  		  $("#er").show()
        $('#mco').css('height', 'auto');
        $(this).hide();
        $(this).siblings("#btn1").show();


    })

    $("#btn1").click(function() {
        $('#mco').css('height', '61px');


        $(this).siblings("#btn").show();

        $(this).hide();

    })
    $("#btna").click(function() {

        $('#mcone').css('height', 'auto');

        $(this).hide();

        $(this).siblings("#btna1").show();

    })

    $("#btna1").click(function() {
        $('#mcone').css('height', '61px');


        $(this).siblings("#btna").show();

        $(this).hide();

    })
    $("#btnb").click(function() {

        $('#mctwo').css('height', 'auto');

        $(this).hide();

        $(this).siblings("#btnb1").show();

    })

    $("#btnb1").click(function() {
        $('#mctwo').css('height', '61px');


        $(this).siblings("#btnb").show();

        $(this).hide();

    })

    $("#btnc").click(function() {

        $('#mcthree').css('height', 'auto');

        $(this).hide();

        $(this).siblings("#btnc1").show();

    })

    $("#btnc1").click(function() {
        $('#mcthree').css('height', '61px');

        $(this).siblings("#btnc").show();

        $(this).hide();

    })
});