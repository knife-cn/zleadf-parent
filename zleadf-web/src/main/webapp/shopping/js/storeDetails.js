$(function() {
    //// 店铺页面--只能搜索出来店铺页面的数据
    var QueryString2 = function(val) {
        var reg = new RegExp("(^|&)" + val + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURIComponent(r[2]);
        }
        return "";
    };
    window.localStorage.setItem("sd_factId", QueryString2("factId"));


    var imger = ["sl1.png", "sl2.png", "sl3.png", "sl4.png", "sl5.png", "sl1.png", "sl2.png", "sl5.png"];
    $("#heada").load("head.html");
    var factId = getParam('factId');
    $("#login").click(function() {
        window.location = "Land.html"
    });
    $(".ppa_a").click(function() {
        window.location.href = "searchPage?sour=2&key=&f=" + encodeURIComponent(factId) + "&b=&l=&m=&c=&x=c"
    });

    $(document).on("click", ".toDetailsts", function() {
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
                    location.href = 'details.html?id=' + dataId
                } else {
                    window.location.reload();
                }
            }
        });
        return false;
    });

    $(window).bind("scroll", function() {
        var sTop = $(window).scrollTop();
        var sTop = parseInt(sTop);
        if (sTop >= 350) {
            if (!$(".ceb").is(":visible")) {
                try {
                    $(".ceb").slideDown();
                } catch (e) {
                    $(".ceb").show();
                }
            }
        } else {
            if ($(".ceb").is(":visible")) {
                try {
                    $(".ceb").slideUp();
                } catch (e) {
                    $(".ceb").hide();
                }
            }
        }
    });

    //购物车数据以及dom结构
    getCoun();

    function getCoun() {
        $.ajax({
            type: "POST", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/shopcart/shoppingCartGoods", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            data: {

            }, //把内容序列化
            async: true, //是否异步
            success: function(res) { //获得返回值
                var coun = 0;
                if (res.code == 1) {
                    if (res.data.length > 0) {
                        for (var i = 0; i < res.data.length; i++) {
                            for (var j = 0; j < res.data[i].goodsList.length; j++) {
                                if (res.data[i].goodsList[j].isMarketable == 0 || res.data[i].goodsList[j].stock == 0) {} else {
                                    coun += res.data[i].goodsList[j].count;
                                }
                            }
                        }
                        if (coun > 99) {
                            $('#gocar3').children('span').show().html("99+")
                        } else if (coun <= 99 && coun > 0) {
                            $('#gocar3').children('span').show().html(coun)
                        } else {
                            $('#gocar3').children('span').show().html("0")
                        }
                    }
                } else if (res.code == 2) {
                    $('#gocar3').children('span').show().html("0")
                }
            }
        })
    }

    //	工厂信息
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/factory/info?factoryId=" + factId + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                var str = "";
                var list = "";
                var fl = "";
                var strlun = "";

                if (res.data.info.shop_name) {
                    if (res.data.info.shop_name.length > 28) {
                        $("#mcname").html(res.data.info.shop_name.substr(0, 28) + "...")
                    } else {
                        $("#mcname").html(res.data.info.shop_name);
                    }
                }

                $("#shoplogin").attr('src', res.data.info.shop_logo);
                if (res.data.info.fact_desc) {
                    $("#gcjieji").html(matchReg(res.data.info.fact_desc));
                } else {
                    $("#sltujj").show()
                }
                if (res.data.info.powerpic_path) {
                    var rz = res.data.info.powerpic_path;
                    var powerpic_path = rz.split(",");
                    var rzpu = "";
                    for (var i = 0; i < powerpic_path.length; i++) {
                        rzpu += '<li><img src="' + powerpic_path[i] + '"></li>';

                    }
                    $("#spturz").html(rzpu);

                } else {
                    $("#slturz").show()
                }
                if (res.data.info.apapic_path) {
                    var yfm = res.data.info.apapic_path;
                    var apapic_path = yfm.split(",");

                    var fm = "";

                    for (var i = 0; i < apapic_path.length; i++) {
                        fm += '<li><img src="' + apapic_path[i] + '"></li>';
                    }
                    $("#sptufm").html(fm);
                } else {
                    $("#sltufm").show()
                }
                //无论点击哪一个img弹出层都会展示相应的图片。
                $("#sptufm li img").on("click", function() {
                    var $img = $(this).attr("src"); //获取当前点击img的src的值
                    $("#tuimgtu").attr("src", $img); //将获取的当前点击img的src赋值到弹出层的图片的src
                    $(".yltu").show(); //弹出层显示
                });
                $("#spturz li img").on("click", function() {
                    var $img = $(this).attr("src"); //获取当前点击img的src的值
                    $("#tuimgtu").attr("src", $img); //将获取的当前点击img的src赋值到弹出层的图片的src
                    $(".yltu").show(); //弹出层显示
                });
                //取消弹出框
                $("#quxiao").click(function() {
                    $(".yltu").hide();
                });

                //轮播图
                for (var i = 0; i < res.data.ads.length; i++) {
                    if (res.data.ads[i].content_type == 1) {
                        if (res.data.ads[i].sn) {
                            strlun += '<div class="swiper-slide"><a href="actDetails?actId=' + res.data.ads[i].sn + '"><img class="aaa" src="' + res.data.ads[i].ads_img + '"></a></div>'
                        } else {
                            strlun += '<div class="swiper-slide"><a href="javascript:;"><img class="aaa" src="' + res.data.ads[i].ads_img + '"></a></div>'
                        }

                    } else {
                        if (res.data.ads[i].sn) {
                            strlun += '<div class="swiper-slide"><a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=' + encodeURIComponent(res.data.ads[i].sn) + '&l=&m=&c="><img class="aaa" src="' + res.data.ads[i].ads_img + '"></a></div>'

                        } else {
                            strlun += '<div class="swiper-slide"><a href="javascript:;"><img class="aaa" src="' + res.data.ads[i].ads_img + '"></a></div>'
                        }
                    }
                }
                $(".swiper-wrapper").html(strlun);

                var mySwiper = new Swiper('.swiper-container', {
                    // direction: 'vertical',
                    loop: true,
                    autoplay: 3000, //可选选项，自动滑动
                });

                //品牌
                for (var i = 0; i < res.data.brands.length; i++) {
                    var code_brands = encodeURIComponent(res.data.brands[i].name);
                    var bandIds = encodeURIComponent(res.data.brands[i].bandIds);

                    if (res.data.brands[i].name.length > 4) {
                        str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=' + encodeURIComponent(code_brands) + '&l=&c=' + '&brandids=' + bandIds + '&listids=&catids=' + '"><span title="' + res.data.brands[i].name +
                            '">' + res.data.brands[i].name.substr(0, 3) + '...</span></a>'
                    } else {
                        str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=' + encodeURIComponent(code_brands) + '&l=&c=' + '&brandids=' + bandIds + '&listids=&catids=' + '"><span>' + res.data.brands[i].name +
                            '</span></a>'
                    }
                }
                //系列
                for (var i = 0; i < res.data.lists.length; i++) {
                    if (res.data.lists[i].name != null) {
                        var code_lists = encodeURIComponent(res.data.lists[i].name);
                        var listIds = encodeURIComponent(res.data.lists[i].listIds);
                        if (res.data.lists[i].name.length > 4) {
                            fl += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=' + encodeURIComponent(code_lists) + '&c=' + '&brandids=&listids=' + listIds + '&catids=' + '"><span title="' + res.data.lists[i].name + '">' +
                                res.data.lists[i].name.substr(0, 3) + '...</span></a>'
                        } else {
                            fl += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=' + encodeURIComponent(code_lists) + '&c=' + '&brandids=&listids=' + listIds + '&catids=' + '"><span>' + res.data.lists[i].name +
                                '</span></a>'
                        }
                    }
                }
                //分类
                for (var i = 0; i < res.data.cats.length; i++) {
                    var code_cats = encodeURIComponent(res.data.cats[i].catDesc);
                    var catIds = encodeURIComponent(res.data.cats[i].catIds);
                    if (res.data.cats[i].catDesc.length > 4) {
                        list += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=&c=' + encodeURIComponent(code_cats) + '&brandids=&listids=&catids=' + catIds + '"><span title="' + res.data.cats[i].catDesc + '">' +
                            res.data.cats[i].catDesc.substr(0, 3) + '...</span></a>'
                    } else {
                        list += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=&c=' + encodeURIComponent(code_cats) + '&brandids=&listids=&catids=' + catIds + '"><span>' + res.data.cats[i].catDesc +
                            '</span></a>'
                    }

                }

                $("#ppa_b").html(str);
                $("#index_list").html(fl);
                $("#feileiinde").html(list);

            }

        },
        error: function(request) { //请求出错
        }
    });

    //分类+商品
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/factory/shop/goods?factoryId=" + encodeURIComponent(factId) + "&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                var str = "";
                for (var i = 0; i < res.data.list.length; i++) {

                    str += '<div class="prountCon" id="nav' + (i + 2) + '">';
                    str += '<div class="prountCount">';
                    str += '<div class="prountCount_top">';
                    str += '<div class="ku"></div>';
                    str += '<p class="prountCount_title">' + res.data.list[i].name + '</p>';
                    str += '<div class="prountCount_pic">';
                    str += '<img src="../../shopping/img/index/ic_tng.png" />';
                    if (res.data.showType == 1) {
                        str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=' + encodeURIComponent(res.data.list[i].name) + '&l=&c=" + "&brandids=&listids=&catids="' + encodeURIComponent(res.data.list[i].catIds) + '"><span>查看更多</span></a>';
                    } else if (res.data.showType == 2) {
                        str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=' + encodeURIComponent(res.data.list[i].name) + '&c=" + "&brandids=&listids=&catids="' + encodeURIComponent(res.data.list[i].catIds) + '"><span>查看更多</span></a>';
                    } else if (res.data.showType == 3) {
                        str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=&c=' + encodeURIComponent(res.data.list[i].name) + '"&brandids=&listids=&catids="' + encodeURIComponent(res.data.list[i].catIds) + '"><span>查看更多</span></a>';
                    }
                    str += '</div>';
                    str += '</div>';

                    str += '<div class="prountCount_left">';
                    str += '<p>' + res.data.list[i].name + '</p>';

                    str += '<div class="prountCount_img">';
                    str += '<img src="' + res.data.list[i].pic_path + '" />';
                    str += '</div>';
                    str += '<ul class="prountCount_leftul">';
                    if (res.data.list[i].childCats) {
                        for (var j = 0; j < res.data.list[i].childCats.length; j++) {
                            if (res.data.list[i].childCats[j].catDesc.length > 4) {
                                str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=&c=' + encodeURIComponent(res.data.list[i].childCats[j].catDesc) + '&brandids=&listids=&catids=' + encodeURIComponent(res.data.list[i].childCats[j].catIds) + '"><li><span title="' + res.data.list[i].childCats[j].catDesc + '">' + res.data.list[i].childCats[j].catDesc.substr(0, 3) + '...</span></li></a>';
                            } else {
                                str += '<a href="searchPage?sour=2&key=&f=' + encodeURIComponent(factId) + '&b=&l=&c=' + encodeURIComponent(res.data.list[i].childCats[j].catDesc) + '&brandids=&listids=&catids=' + encodeURIComponent(res.data.list[i].childCats[j].catIds) + '"><li><span>' + res.data.list[i].childCats[j].catDesc + '</span></li></a>';
                            }
                        }
                    }
                    str += '</ul>';
                    str += '</div>';
                    str += '<div class="prountCount_right">';
                    str += '<ul class="prountCount_rightul">';
                    if (res.data.list[i].shopGoods) {
                        if (res.data.list[i].shopGoods.length == res.data.list[i].shopGoods.length) {

                            for (var a = 0; a < res.data.list[i].shopGoods.length; a++) {
                                // str += '<a href="details?id=' + res.data.list[i].shopGoods[a].id + '">';
                                str += '<li class="toDetailsts" data-id="' + res.data.list[i].shopGoods[a].id + '">';
                                str += '<div class="pp_img">';
                                if (res.data.list[i].shopGoods[a].firstImg) {
                                    str += '<img src="' + res.data.list[i].shopGoods[a].firstImg + '" />';
                                } else {
                                    str += '<img src="../../shopping/img/index/sl3.png" />';
                                }
                                str += '</div>';
                                if (res.data.list[i].shopGoods[a].ifShowPrice == true) {
                                    str += '<p>¥' + res.data.list[i].shopGoods[a].showPrice.toFixed(2) + '</p>';
                                } else {
                                    str += '<p>¥--</p>';
                                }
                                if (res.data.list[i].shopGoods[a].fullName.length > 20) {
                                    str += '<p> ' + res.data.list[i].shopGoods[a].fullName.substr(0, 20) + '...</p>';
                                } else {
                                    str += '<p> ' + res.data.list[i].shopGoods[a].fullName + '</p>';
                                }

                                str += '</li>';
                                // str += '</a>'
                            }
                            for (var a = 0; a < 8 - res.data.list[i].shopGoods.length; a++) {
                                str += '<a>';
                                str += '<li>';
                                str += '<div class="pp_img">';
                                str += '<img src="../../shopping/img/index/' + imger[a] + '" />';

                                str += '</div>';
                                str += '<p>暂无价格</p>';
                                str += '<p>当前还没有商品哦~</p>';
                                str += '</li>';
                                str += '</a>';
                            }

                        }
                    } else {
                        for (var a = 0; a < 8; a++) {
                            str += '<a>';
                            str += '<li>';
                            str += '<div class="pp_img">';
                            str += '<img src="../../shopping/img/index/' + imger[a] + '" />';

                            str += '</div>';
                            str += '<p>暂无价格</p>';
                            str += '<p>当前还没有商品哦~</p>';
                            str += '</li>';
                            str += '</a>';
                        }
                    }


                    str += '</ul>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
                }

                $("#contur").html(str);
                var cbl = " ";
                cbl += '<li>';
                cbl += '<a href="#nav1">';
                cbl += '<p id="na1" class="nat">导航</p>';
                cbl += '</a>';
                cbl += '</li>';
                for (var i = 0; i < res.data.list.length; i++) {


                    cbl += '<li>';
                    cbl += '<a href="#nav' + (i + 2) + '">';
                    cbl += '<p id="na' + (i + 2) + '" class="nat">' + res.data.list[i].name + '</p>';
                    cbl += '</a>';
                    cbl += '</li>';
                }
                cbl += '<li class="go_top"><img src="../../shopping/img/index/ic_zhiding.png" /></li>';
                $("#avu").html(cbl);
                $('.go_top').bind('click', function() {
                    $(window).scrollTop(0);
                });

                $(window).scroll(function() {

                    var wst = $(window).scrollTop();
                    for (i = 1; i < res.data.list.length + 2; i++) {
                        if ($("#nav" + i).offset().top <= wst) {

                            $('#avu p').removeClass("rgcl");
                            $("#na" + i).addClass("rgcl");
                            //给当前导航加c类
                        }
                    }

                })
            }
        },
        error: function(request) { //请求出错
        }
    })

});
// 过滤html标签
function matchReg(str) {
    var reg = /<\/?.+?\/?>/g;
    return str.replace(reg, '');
}