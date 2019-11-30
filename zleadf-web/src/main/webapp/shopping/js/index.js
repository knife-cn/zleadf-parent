$(function() {
    $(".fgtwoCon").css("display", "none");
    window.sessionStorage.setItem("resou", "");
    var imger = ["sl1.png", "sl2.png", "sl3.png", "sl4.png", "sl5.png", "sl1.png", "sl2.png", "sl5.png"]
    var imgat = ["dianz.png", "ju.png", "quan.png", "ju.png", "quan.png", "ju.png", "quan.png", "ju.png", "quan.png",
        "ju.png", "quan.png"
    ]
    var one = getParam("login");

    if (one == "one") {
        $(".meng").show();
        $(".zhong").show();
        $(".zhongtwo").hide();
    } else if (one == "two") {
        $(".meng").show();
        $(".zhong").hide();
        $(".zhongtwo").show();
    }

    // 加载轮播图
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/fplat/adsList?adstype=1&t_=" + Math.random(), //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                var str = "";
                for (var i = 0; i < res.data.length; i++) {
                    str += '<a><img class="aaa" src="' + res.data[i].adsImg + '"></a>'
                }
                $("#lunbos").html(str);
            }

        },
        error: function(request) { //请求出错
        }
    });

    // 商品筛选+工厂列表
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/gateWay/navigation?t_=" + Math.random(), //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                var str = "";
                var list = "";
                var fl = "";
                var stra = "";

                //品牌
                if (res.data.screen.custbandList != null && res.data.screen.custbandList != undefined && res.data.screen.custbandList != "") {
                    for (var i = 0; i < res.data.screen.custbandList.length; i++) {
                        if (res.data.screen.custbandList[i].bandName != null) {
                            var code_bandName = encodeURIComponent(res.data.screen.custbandList[i].bandName);
                            var bandIds = encodeURIComponent(res.data.screen.custbandList[i].bandIds);
                            if (res.data.screen.custbandList[i].bandName.length > 4) {
                                str += '<a href="searchPage?sour=1&key=&f=&b=' + code_bandName + '&l=&m=&c=&brandids=' + bandIds + '&listids=&catids="><span title="' + res.data.screen
                                    .custbandList[i].bandName + '">' + res.data.screen.custbandList[i].bandName.substr(0, 3) + '...</span></a>'
                            } else {
                                str += '<a href="searchPage?sour=1&key=&f=&b=' + code_bandName + '&l=&m=&c=&brandids=' + bandIds + '&listids=&catids="><span>' + res.data.screen.custbandList[
                                    i].bandName + '</span></a>'
                            }
                        }
                    }
                }


                //系列
                if (res.data.screen.crmPrdListList != null && res.data.screen.crmPrdListList != undefined && res.data.screen.crmPrdListList != "") {
                    for (var i = 0; i < res.data.screen.crmPrdListList.length; i++) {
                        if (res.data.screen.crmPrdListList[i].listName != null) {
                            var code_listName = encodeURIComponent(res.data.screen.crmPrdListList[i].listName);
                            var listIds = encodeURIComponent(res.data.screen.crmPrdListList[i].listIds);
                            if (res.data.screen.crmPrdListList[i].listName.length > 4) {
                                list += '<a href="searchPage?sour=1&key=&f=&b=&l=' + code_listName + '&m=&c=&brandids=&listids=' + listIds + '&catids="><span title="' + res.data
                                    .screen.crmPrdListList[i].listName + '">' + res.data.screen.crmPrdListList[i].listName.substr(0, 3) +
                                    '...</span></a>'
                            } else {
                                list += '<a href="searchPage?sour=1&key=&f=&b=&l=' + code_listName + '&m=&c=&brandids=&listids=' + listIds + '&catids="><span>' + res.data.screen
                                    .crmPrdListList[i].listName + '</span></a>'
                            }
                        }

                    }
                }



                //分类
                if (res.data.screen.crmPrdCatList != null && res.data.screen.crmPrdCatList != undefined && res.data.screen.crmPrdCatList != "") {
                    for (var i = 0; i < res.data.screen.crmPrdCatList.length; i++) {
                        if (res.data.screen.crmPrdCatList[i].catName != null) {
                            var code_catName = encodeURIComponent(res.data.screen.crmPrdCatList[i].catName);
                            var catIds = encodeURIComponent(res.data.screen.crmPrdCatList[i].catIds);
                            if (res.data.screen.crmPrdCatList[i].catName.length > 4) {
                                fl += '<a href="searchPage?sour=1&key=&f=&b=&l=&c=' + code_catName + '&brandids=&listids=&catids=' + catIds + '"><span title="' + res.data.screen
                                    .crmPrdCatList[i].catName + '">' + res.data.screen.crmPrdCatList[i].catName.substr(0, 3) + '...</span></a>'
                            } else {
                                fl += '<a href="searchPage?sour=1&key=&f=&b=&l=&c=' + code_catName + '&brandids=&listids=&catids=' + catIds + '"><span>' + res.data.screen.crmPrdCatList[
                                    i].catName + '</span></a>'
                            }
                        }

                    }
                }

                var obj = res.data.factoryList.map;

                var otr = Object.keys(obj);
                //工厂列表
                if (otr.length == 0) {

                    stra += ' <li>';
                    stra += '<img src="../../shopping/img/index/ic_connect.png" style="margin-top: 129px;margin-left: 70px;" />';
                    stra += '<p style="" class="msaa">还没有工厂品牌</p>';
                    stra += '<p style="" class="msbb">请先关联工厂</p>';
                    stra += '<span class="mss">马上关联</span>';
                    stra += '</li>';
                    $("#gongc").addClass("active");
                    $("#shaixu").removeClass("active");
                    $("#indexsp").hide();
                    $("#indexgc").show();

                } else {
                    var stat = '<p class="gd">关联更多<i><img src="../../shopping/img/index/_Right.png"/></i></p>';
                    $("#gda").html(stat);
                    Object.keys(obj).forEach(function(key) {

                        stra += '<li class="ppa">';
                        stra += '<div class="pada">';

                        stra += '</div>';
                        stra += '<div class="ppa_a" id="indexa1">';
                        stra += '<span>' + key + '</span>';
                        stra += '</div>';
                        stra += '<div class="ppa_b" id="ppa_b">';
                        for (var i = 0; i < obj[key].length; i++) {
                            if (obj[key][i].factName.length > 4) {
                                stra += '<a href="storeDetails?factId=' + obj[key][i].factId + '"><span class="addCla" title="' + obj[key][i].factName +
                                    '">' + obj[key][i].factName + '</span></a>';
                            } else {
                                stra += '<a href="storeDetails?factId=' + obj[key][i].factId + '"><span>' + obj[key][i].factName +
                                    '</span></a>';
                            }
                        }
                        stra += '</div>';
                        stra += '</li>';

                    });
                }

                $("#ppa_b").html(str);
                $("#index_list").html(list);
                $("#feileiinde").html(fl);
                $("#ppa_b").on("click", "a", function() {

                    window.sessionStorage.setItem("ppname", $(this).children("span").text())
                })
                $("#index_list").on("click", "a", function() {

                    window.sessionStorage.setItem("xlname", $(this).children("span").text())
                })
                $("#feileiinde").on("click", "a", function() {

                    window.sessionStorage.setItem("fenname", $(this).children("span").text())
                })

                $("#stya").html(stra);

                $(".mss").click(function() {
                    window.location.href = "Associated_factory1";
                })
                $("#gda").click(function() {
                    window.location.href = "Associated_factory1";
                })
            }

        },
        error: function(request) { //请求出错
        }
    })

    // 限时活动
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/gateWay/actives?t_=" + Math.random(), //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {

                $("#activityDetails_id").click(function() {
                    // if (res.data) {

                    window.location.href = "activityDetails";
                    // }
                })

                var str = "";
                if (res.data != null) {

                    if (res.data.length == res.data.length) {
                        for (var i = 0; i < res.data.length; i++) {
                            str += '<a href="actDetails?actId=' + res.data[i].actId + '">';
                            str += '<li>';
                            str += '<div class="prout_ulpic"> <img src=" ' + res.data[i].actPic + '" />';
                            str += '</div>';
                            str += '<div class="prout_jg">';
                            if (res.data[i].contName.length > 29) {
                                str += '<p>' + res.data[i].contName.substr(0, 29) + '...</p>';
                            } else {
                                str += '<p>' + res.data[i].contName + '</p>';
                            }
                            str += '<p>' + res.data[i].contTitle + '</p>';

                            str += '</div>';
                            str += '</li>';
                            str += '</a>';
                        }
                        for (var i = 0; i < 4 - res.data.length; i++) {
                            str += '<a>';
                            str += '<li>';
                            str += '<div class="prout_ulpic"> <img src="../../shopping/img/index/' + imger[i] + '" style="width: 126px;height: 112px"/>';
                            str += '</div>';
                            str += '<div class="prout_jg">';
                            str += '<p>暂无活动</p>';
                            str += '<p></p>';

                            str += '</div>';
                            str += '</li>';
                            str += '</a>';
                        }

                    }
                } else if (res.data == null) {
                    for (var i = 0; i < 4; i++) {
                        str += '<a>';
                        str += '<li>';
                        str += '<div class="prout_ulpic"> <img src="../../shopping/img/index/' + imger[i] + '"  style="width: 126px;height: 112px"/>';
                        str += '</div>';
                        str += '<div class="prout_jg">';
                        str += '<p>暂无活动</p>';
                        str += '<p></p>';

                        str += '</div>';
                        str += '</li>';
                        str += '</a>';
                    }
                }
                $("#prout_ul").html(str);
            }

        },
        error: function(request) { //请求出错
        }
    })

    $("#heada").load("head.html");
    // $("#heada").load("head.html", function() {
    //     if (parseInt($('#cont').html()) > 0) {
    //         $('#gocar3').children('span').html(parseInt($('#cont').html()))
    //     } else {
    //         $('#gocar3').children('span').hide()
    //     }

    // });

    // 右侧的购物车数量

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



    $("#login").click(function() {
        $(".meng").show();
        $(".zhong").show();
        $(".zhongtwo").hide();

    })

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


    // 首页商品展示
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/gateWay/prdArea?t_=" + Math.random(), //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        success: function(res) {

            var a;
            var sat = "";
            var str = "";

            if (res.code == 1) {

                if (res.data.length == 0) {

                } else if (res.data.length == 1 || res.data.length == 2) {

                    for (var i = 0; i < res.data.length; i++) {

                        sat += '<div class="electrictopa">';
                        if (res.data[i].firstCrmPrdCat != null) {


                            sat += '<div class="elect_top">';
                            sat += '<div class="elect_ju"></div>';
                            sat += '<div class="elect_b">';
                            if (res.data[i].firstCrmPrdCat.catName.length > 4) {
                                sat += '' + res.data[i].firstCrmPrdCat.catName.substr(0, 4) + '...';
                            } else {
                                sat += '' + res.data[i].firstCrmPrdCat.catName + '';
                            }

                            sat += '</div>';
                            sat += '<div class="elect_title">';
                        } else {
                            sat += '<div class="elect_top">';
                            sat += '<div class="elect_ju"></div>';
                            sat += '<div class="elect_b">';
                            sat += '';
                            sat += '</div>';
                            sat += '<div class="elect_title">';
                        }
                        sat += '<ul>';
                        if (res.data[i].secondCrmPrdCat != null) {
                            for (var j = 0; j < res.data[i].secondCrmPrdCat.length; j++) {
                                if (res.data[i].secondCrmPrdCat[j].catName.length > 4) {
                                    sat += '<li><a href="searchPage?sour=1&brandids=&listids=&catids=' + encodeURIComponent(res.data[i].secondCrmPrdCat[
                                        j].catIds) + '&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].secondCrmPrdCat[j].catName) + '">' + res.data[i].secondCrmPrdCat[j].catName.substr(0, 3) + '...</a></li>';
                                } else {
                                    sat += '<li><a href="searchPage?sour=1&brandids=&listids=&catids=' + encodeURIComponent(res.data[i].secondCrmPrdCat[
                                        j].catIds) + '&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].secondCrmPrdCat[j].catName) + '">' + res.data[i].secondCrmPrdCat[j].catName + '</a></li>';
                                }

                            }
                        }


                        sat += '</ul>';
                        sat += '</div>';
                        sat += '</div>';
                        sat += '<div class="elect_prout">';
                        if (res.data[i].firstCrmPrdCat != null) {
                            sat += '<div class="elect_proutleft">';
                            if (res.data[i].firstCrmPrdCat.catName.length > 4) {
                                sat += '<p class="elect_proutlefta">' + res.data[i].firstCrmPrdCat.catName.substr(0, 4) + '...</p>';
                            } else {
                                sat += '<p class="elect_proutlefta">' + res.data[i].firstCrmPrdCat.catName + '</p>';
                            }


                            sat += '<div class="elect_proutleftpic">';
                            sat += '<img src="' + res.data[i].firstCrmPrdCat.picPath + '" />';
                            sat += '</div>';

                            sat += '<div class="fff"></div>';
                            sat += '</div>';
                        } else {
                            sat += '<div class="elect_proutleft">';
                            sat += '<p class="elect_proutlefta"></p>';

                            sat += '<div class="elect_proutleftpic">';
                            sat += '<img src="../../shopping/img/index/' + imgat[i] + '" />';
                            sat += '</div>';

                            sat += '<div class="fff"></div>';
                            sat += '</div>';
                        }
                        sat += '<div class="elect_proutright">';
                        sat += '<ul class="proutright_ul">';
                        if (res.data[i].goodsEntityList == null) {
                            for (var t = 0; t < 8; t++) {
                                sat += '<a>';
                                sat += '<li>';
                                sat += '<div class="proutright_ulpic">';
                                sat += '<img src="../../shopping/img/index/' + imger[t] + '" />';
                                sat += '</div>';
                                sat += '<div class="proutright_jg">';
                                sat += '<p>暂无价格</p>';
                                sat += '<p>当前还没有商品哦~</p>';

                                sat += '</div>';
                                sat += '</li>';
                                sat += '</a>';
                            }
                        } else if (res.data[i].goodsEntityList != null) {

                            if (res.data[i].goodsEntityList.length == res.data[i].goodsEntityList.length) {
                                for (var q = 0; q < res.data[i].goodsEntityList.length; q++) {

                                    // sat += '<a href="details?id=' + res.data[i].goodsEntityList[q].id + '">';
                                    sat += '<li class="toDetailst" data-id="' + res.data[i].goodsEntityList[q].id + '">';
                                    sat += '<div class="proutright_ulpic">';
                                    if (res.data[i].goodsEntityList[q].firstImg) {
                                        sat += '<img src="' + res.data[i].goodsEntityList[q].firstImg + '" />';
                                    } else {
                                        sat += '<img src="../../shopping/img/index/sl3.png" />';
                                    }
                                    sat += '</div>';
                                    sat += '<div class="proutright_jg">';
                                    if (res.data[i].goodsEntityList[q].ifShowPrice == 1) {
                                        sat += '<p>¥' + res.data[i].goodsEntityList[q].showPrice.toFixed(2) + '</p>';
                                    } else if (res.data[i].goodsEntityList[q].ifShowPrice == 0) {
                                        sat += '<p>¥--</p>';
                                    }
                                    if (res.data[i].goodsEntityList[q].fullName.length > 20) {
                                        sat += '<p>' + res.data[i].goodsEntityList[q].fullName.substr(0, 20) + '...</p>';
                                    } else {
                                        sat += '<p>' + res.data[i].goodsEntityList[q].fullName + '</p>';
                                    }


                                    sat += '</div>';
                                    sat += '</li>';
                                    // sat += '</a>';

                                }
                                for (var t = 0; t < 8 - res.data[i].goodsEntityList.length; t++) {
                                    sat += '<a>';
                                    sat += '<li>';
                                    sat += '<div class="proutright_ulpic">';
                                    sat += '<img src="../../shopping/img/index/' + imger[t] + '" />';
                                    sat += '</div>';
                                    sat += '<div class="proutright_jg">';
                                    sat += '<p>暂无价格</p>';
                                    sat += '<p>当前还没有商品哦~</p>';

                                    sat += '</div>';
                                    sat += '</li>';
                                    sat += '</a>';
                                }
                            }
                        }

                        sat += '</ul>';
                        sat += '</div>';

                        sat += '</div>';
                        sat += '</div>';
                    }
                } else if (res.data.length >= 3) {
                    $(".fgtwoCon").css("display", "block");
                    for (var i = 0; i < 2; i++) {

                        sat += '<div class="electrictopa">';
                        if (res.data[i].firstCrmPrdCat != null) {
                            sat += '<div class="elect_top">';
                            sat += '<div class="elect_ju"></div>';
                            sat += '<div class="elect_b">';
                            if (res.data[i].firstCrmPrdCat.catName.length > 4) {
                                sat += '' + res.data[i].firstCrmPrdCat.catName.substr(0, 4) + '...';
                            } else {
                                sat += '' + res.data[i].firstCrmPrdCat.catName + '';
                            }
                            sat += '</div>';
                        } else {
                            sat += '<div class="elect_top">';
                            sat += '<div class="elect_ju"></div>';
                            sat += '<div class="elect_b">';
                            sat += '';
                            sat += '</div>';
                        }
                        sat += '<div class="elect_title">';
                        sat += '<ul>';
                        if (res.data[i].secondCrmPrdCat != null) {
                            for (var j = 0; j < res.data[i].secondCrmPrdCat.length; j++) {
                                if (res.data[i].secondCrmPrdCat[j].catName.length > 4) {
                                    sat += '<li><a href="searchPage?sour=1&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].secondCrmPrdCat[j].catName) + '">' + res.data[i].secondCrmPrdCat[j].catName.substr(0, 3) + '...</a></li>';
                                } else {
                                    sat += '<li><a href="searchPage?sour=1&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].secondCrmPrdCat[j].catName) + '">' + res.data[i].secondCrmPrdCat[j].catName + '</a></li>';
                                }
                            }
                        } else {


                            sat += '<li></li>';

                        }


                        sat += '</ul>';

                        sat += '</div>';
                        sat += '</div>';

                        sat += '<div class="elect_prout">';
                        if (res.data[i].firstCrmPrdCat != null) {
                            sat += '<div class="elect_proutleft">';
                            if (res.data[i].firstCrmPrdCat.catName.length > 4) {
                                sat += '<p class="elect_proutlefta">' + res.data[i].firstCrmPrdCat.catName.substr(0, 4) + '...</p>';
                            } else {
                                sat += '<p class="elect_proutlefta">' + res.data[i].firstCrmPrdCat.catName + '</p>';
                            }

                            sat += '<div class="elect_proutleftpic">';
                            sat += '<img src="' + res.data[i].firstCrmPrdCat.picPath + '" />';
                            sat += '</div>';

                            sat += '<div class="fff"></div>';
                            sat += '</div>';
                        } else {
                            sat += '<div class="elect_proutleft">';
                            sat += '<p class="elect_proutlefta"></p>';

                            sat += '<div class="elect_proutleftpic">';
                            sat += '<img src="../../shopping/img/index/' + imgat[i] + '" />';
                            sat += '</div>';

                            sat += '<div class="fff"></div>';
                            sat += '</div>';
                        }

                        sat += '<div class="elect_proutright">';
                        sat += '<ul class="proutright_ul">';
                        if (res.data[i].goodsEntityList == null) {
                            for (var t = 0; t < 8; t++) {
                                sat += '<a>';
                                sat += '<li>';
                                sat += '<div class="proutright_ulpic">';
                                sat += '<img src="../../shopping/img/index/' + imger[t] + '" />';
                                sat += '</div>';
                                sat += '<div class="proutright_jg">';
                                sat += '<p>暂无价格</p>';
                                sat += '<p>当前还没有商品哦~</p>';

                                sat += '</div>';
                                sat += '</li>';
                                sat += '</a>';
                            }
                        } else if (res.data[i].goodsEntityList != null) {
                            if (res.data[i].goodsEntityList.length == res.data[i].goodsEntityList.length) {
                                for (var q = 0; q < res.data[i].goodsEntityList.length; q++) {

                                    // sat += '<a href="details?id=' + res.data[i].goodsEntityList[q].id + '">';
                                    sat += '<li class="toDetailst" data-id="' + res.data[i].goodsEntityList[q].id + '">';
                                    sat += '<div class="proutright_ulpic">';
                                    if (res.data[i].goodsEntityList[q].firstImg) {
                                        sat += '<img src="' + res.data[i].goodsEntityList[q].firstImg + '" />';
                                    } else {
                                        sat += '<img src="../../shopping/img/index/sl3.png" />';
                                    }

                                    sat += '</div>';
                                    sat += '<div class="proutright_jg">';
                                    if (res.data[i].goodsEntityList[q].ifShowPrice == 1) {
                                        sat += '<p>¥' + res.data[i].goodsEntityList[q].showPrice.toFixed(2) + '</p>';
                                    } else if (res.data[i].goodsEntityList[q].ifShowPrice == 0) {
                                        sat += '<p>¥--</p>';
                                    }
                                    if (res.data[i].goodsEntityList[q].fullName.length > 20) {
                                        sat += '<p>' + res.data[i].goodsEntityList[q].fullName.substr(0, 20) + '...</p>';
                                    } else {
                                        sat += '<p>' + res.data[i].goodsEntityList[q].fullName + '</p>';
                                    }

                                    sat += '</div>';
                                    sat += '</li>';
                                    // sat += '</a>';

                                }
                                for (var t = 0; t < 8 - res.data[i].goodsEntityList.length; t++) {
                                    sat += '<a>';
                                    sat += '<li>';
                                    sat += '<div class="proutright_ulpic">';
                                    sat += '<img src="../../shopping/img/index/' + imger[t] + '" />';
                                    sat += '</div>';
                                    sat += '<div class="proutright_jg">';
                                    sat += '<p>暂无价格</p>';
                                    sat += '<p>当前还没有商品哦~</p>';

                                    sat += '</div>';
                                    sat += '</li>';
                                    sat += '</a>';
                                }
                            }
                        }
                        sat += '</ul>';
                        sat += '</div>';

                        sat += '</div>';
                        sat += '</div>';
                    }

                    for (var i = 2; i < res.data.length; i++) {

                        str += '<div class="prountCon" id="nav' + (i + 1) + '">';
                        if (res.data[i].firstCrmPrdCat != null) {
                            str += '<div class="prountCount">';
                            str += '<div class="prountCount_top">';
                            str += '<div class="ku"></div>';

                            if (res.data[i].firstCrmPrdCat.catName.length > 4) {
                                str += '<p class="prountCount_title">' + res.data[i].firstCrmPrdCat.catName.substr(0, 4) + '...</p>';
                            } else {
                                str += '<p class="prountCount_title">' + res.data[i].firstCrmPrdCat.catName + '</p>';
                            }


                            str += '<div class="prountCount_pic">';
                            str += '<img src="../../shopping/img/index/ic_tng.png" />';

                            str += '<a href="searchPage?sour=1&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].firstCrmPrdCat.catName) + '&brandids=&listids=&catids=' + encodeURIComponent(res.data[i].firstCrmPrdCat.catIds) + '"><span>查看更多</span></a>';
                            str += '</div>';
                            str += '</div>';

                            str += '<div class="prountCount_left">';

                            if (res.data[i].firstCrmPrdCat.catName.length > 4) {
                                str += '<p>' + res.data[i].firstCrmPrdCat.catName.substr(0, 4) + '...</p>';
                            } else {
                                str += '<p>' + res.data[i].firstCrmPrdCat.catName + '</p>';
                            }


                            str += '<div class="prountCount_img">';
                            if (res.data[i].firstCrmPrdCat.picPath) {
                                str += '<img src="' + res.data[i].firstCrmPrdCat.picPath + '" />';
                            } else {
                                str += '<img src="../../shopping/img/index/sl3.png" />';
                            }

                            str += '</div>';

                        } else {
                            str += '<div class="prountCount">';
                            str += '<div class="prountCount_top">';
                            str += '<div class="ku"></div>';
                            str += '<p class="prountCount_title"></p>';
                            str += '<div class="prountCount_pic">';
                            str += '<img src="../../shopping/img/index/ic_tng.png" />';
                            str += '<span>查看更多</span>';
                            str += '</div>';
                            str += '</div>';

                            str += '<div class="prountCount_left">';
                            str += '<p></p>';

                            str += '<div class="prountCount_img">';
                            str += '<img src="../../shopping/img/index/' + imgat[i] + '" />';
                            str += '</div>';
                        }
                        str += '<ul class="prountCount_leftul">';
                        if (res.data[i].secondCrmPrdCat != null) {
                            for (var j = 0; j < res.data[i].secondCrmPrdCat.length; j++) {
                                if (res.data[i].secondCrmPrdCat[j].catName.length > 4) {
                                    var code_catName = encodeURIComponent(res.data[i].secondCrmPrdCat[j].catName);
                                    str += '<a href="' + 'searchPage?sour=1&brandids=&listids=&catids=' + encodeURIComponent(res.data[i].secondCrmPrdCat[
                                        j].catIds) + '&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].secondCrmPrdCat[
                                        j].catName) + '"><li><span>' + res.data[i].secondCrmPrdCat[
                                        j].catName.substr(0, 3) + '...</span></li></a>';
                                } else {
                                    str += '<a href="' + 'searchPage?sour=1&brandids=&listids=&catids=' + encodeURIComponent(res.data[i].secondCrmPrdCat[
                                        j].catIds) + '&key=&f=&b=&l=&c=' + encodeURIComponent(res.data[i].secondCrmPrdCat[
                                        j].catName) + '"><li><span>' + res.data[i].secondCrmPrdCat[
                                        j].catName + '</span></li></a>';
                                }

                            }
                        }


                        str += '</ul>';
                        str += '</div>';

                        str += '<div class="prountCount_right">';
                        str += '<ul class="prountCount_rightul">';
                        if (res.data[i].goodsEntityList == null) {
                            for (var t = 0; t < 8; t++) {
                                str += '<a>';
                                str += '<li>';
                                str += '<div class="pp_img">';
                                str += '<img src="../../shopping/img/index/' + imger[t] + '" style="width: 115px"/>';

                                str += '</div>';
                                str += '<p>暂无价格</p>';
                                str += '<p>当前还没有商品哦~</p>';
                                str += '</li>';
                                str += '</a>';
                            }
                        } else if (res.data[i].goodsEntityList != null) {
                            if (res.data[i].goodsEntityList.length == res.data[i].goodsEntityList.length) {
                                for (var q = 0; q < res.data[i].goodsEntityList.length; q++) {
                                    // str += '<a href="details?id=' + res.data[i].goodsEntityList[q].id + '">';
                                    str += '<li class="toDetailst" data-id="' + res.data[i].goodsEntityList[q].id + '">';
                                    str += '<div class="pp_img">';
                                    if (res.data[i].goodsEntityList[q].firstImg) {
                                        str += '<img src="' + res.data[i].goodsEntityList[q].firstImg + '" style="width: 115px;"/>';
                                    } else {

                                        str += '<img src="../../shopping/img/index/sl3.png" style="width: 115px;"/>';
                                    }


                                    str += '</div>';
                                    if (res.data[i].goodsEntityList[q].ifShowPrice == 1) {
                                        str += '<p>¥' + res.data[i].goodsEntityList[q].showPrice.toFixed(2) + '</p>';
                                    } else if (res.data[i].goodsEntityList[q].ifShowPrice == 0) {
                                        str += '<p>¥--</p>';
                                    } else {
                                        str += '<p>' + '</p>';
                                    }
                                    if (res.data[i].goodsEntityList[q].fullName.length > 20) {
                                        str += '<p>' + res.data[i].goodsEntityList[q].fullName.substr(0, 20) + '...</p>';
                                    } else {
                                        str += '<p>' + res.data[i].goodsEntityList[q].fullName + '</p>';
                                    }
                                    str += '</li>';
                                    // str += '</a>';
                                }
                                for (var t = 0; t < 8 - res.data[i].goodsEntityList.length; t++) {
                                    str += '<a>';
                                    str += '<li>';
                                    str += '<div class="pp_img">';
                                    str += '<img src="../../shopping/img/index/' + imger[t] + '" />';

                                    str += '</div>';
                                    str += '<p>暂无价格</p>';
                                    str += '<p>当前还没有商品哦~</p>';
                                    str += '</li>';
                                    str += '</a>';
                                }

                            }
                        }
                        str += '</ul>';
                        str += '</div>';
                        str += '</div>';
                        str += '</div>';
                    }

                }
            }
            $("#electrictop").html(sat)

            $("#conutpro").html(str);
            $(".prountCount_leftul").on("click", "li", function() {
                // alert($(this).children("span").text())
            })

            //锚点
            var cbl = " ";
            cbl += '<li>';
            cbl += '<a href="#nav1">';
            cbl += '<p id="na1" class="nat">导航</p>';
            cbl += '</a>';
            cbl += '</li>';
            if (res.data.length == 1) {
                if (res.data[0].firstCrmPrdCat != null) {
                    cbl += '<li>';
                    cbl += '<a href="#nav2">';
                    if (res.data[0].firstCrmPrdCat.catName.length > 5) {
                        cbl += '<p id="na2" class="nat">' + res.data[0].firstCrmPrdCat.catName.substr(0, 4) + '</p>';
                    } else {
                        cbl += '<p id="na2" class="nat">' + res.data[0].firstCrmPrdCat.catName + '</p>';
                    }

                    cbl += '</a>';
                    cbl += '</li>';
                }
            } else {
                for (var i = 0; i < res.data.length; i++) {
                    if (res.data[i].firstCrmPrdCat != null) {
                        if (i == 0 || i == 1) {
                            cbl += '<li>';
                            cbl += '<a href="#nav2' + '">';
                            cbl += '<p id="na2' + '" class="nat">' + res.data[i].firstCrmPrdCat.catName + '</p>';
                            cbl += '</a>';
                            cbl += '</li>';
                        } else {
                            cbl += '<li>';
                            cbl += '<a href="#nav' + (i + 1) + '">';
                            cbl += '<p id="na' + (i + 1) + '" class="nat">' + res.data[i].firstCrmPrdCat.catName + '</p>';
                            cbl += '</a>';
                            cbl += '</li>';
                        }

                    }

                }
            }
            cbl += '<li class="go_top"><img src="../../shopping/img/index/ic_zhiding.png" /></li>';
            $("#avu").html(cbl);
            $('.go_top').bind('click', function() {

                $(window).scrollTop(0);
            });

            $(window).scroll(function() {

                var wst = $(window).scrollTop()
                for (i = 1; i < res.data.length + 1; i++) {
                    if ($("#nav" + i).offset().top <= wst) {

                        $('#avu p').removeClass("rgcl");
                        if (i == 1 || i == 2) {
                            $("#na2").addClass("rgcl");
                        } else {
                            $("#na" + i).addClass("rgcl");
                        }

                        //给当前导航加c类
                    }
                }

            })

        },
        error: function(request) { //请求出错
        }
    });

    $(document).on("click", ".toDetailst", function() {
        var dataId = $(this).attr("data-id");
        //判断登陆状态
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/member/memberInfo", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            success: function(res) {
                if (res.code == 1) {
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
                } else {
                    window.location.href = "Receiving_address5";
                }
            }
        });




        return false;
    })


});