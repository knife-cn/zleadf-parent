var QueryString = function (val) {
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
        type:"post",//提交请求的方式
        cache:true,//是否有缓存
        url: "/zlead/order/newConfirmOrder",//访问servlet的路径
        dataType:"json",//没有这个，将把后台放会的json解析成字符串
        async:true,//是否异步
        data:{
            goodsId:goodsId,
            buyNum:1
        },
        success:function(res) {
            if (res.code==1){
                var data= res.data
                var datasss = JSON.stringify(data)
                localStorage.setItem("datas",datasss)

            }
            window.location = "orderList.html"
        }
    })
}
//加入购物车
function gocart(goodsId,buyType) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/shopcart/addShoppingCart", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            goodsId:goodsId,
            count:1,
            buyType:buyType
        },

        async: true, //是否异步
        success: function(res) {

            if (res.code==1) {
                // alert("加入购物车成功！");
                window. location.reload();
            }
            else
            {
                alert("加入购物车失败！");
            }

        },
        error: function(request) { //请求出错

        }
    })
}

$("#head").load("head.html");
var b = QueryString("b");
var l = QueryString("l");
var m = QueryString("m");
var c = QueryString("c");
var key = QueryString("key");
var p = 1;
var s = 20;
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
    $("#chichi").html("关于“" + tip.join("+") + "”的搜索结果");
    $("#xinxi").html("非常抱歉没有找到" + tip.join("+") + "的相关信息");
}
var blist = [];//品牌
var llist = [];//系列
var mlist = [];//型号
var clist = [];//分类

var bids = [];
var lids = [];
var mids = [];
var cids = [];

//获取搜索条件
$.ajax({
    type: "get",
    cache: false,
    url: "/query/term?t_="+Math.random(),
    dataType: "json",
    success: function (res) {
        if (res.success) {
            blist = res.data.blist;//品牌
            llist = res.data.llist;//系列
            mlist = res.data.mlist;//型号
            clist = res.data.clist;//分类
            if (blist) {
                var html = "";
                for (var i = 0; i < blist.length; i++) {
                    blist[i].band_id = blist[i].band_id + "";
                    html += "<li bid='" + blist[i].band_id + "'><span>" + blist[i].name + "</span></li>";
                    bids.splice(-1, 0, blist[i].band_id.split("_"));
                }
                $("#ppl").html(html);
            }
            if (llist) {
                var html = "";
                for (var i = 0; i < llist.length; i++) {
                    llist[i].id = llist[i].id + "";
                    html += "<li bid='" + llist[i].band_id + "' lid='" + llist[i].id + "'><span>" + llist[i].name + "</span></li>";
                    lids.splice(-1, 0, llist[i].id.split("_"));
                }
                $("#xl").html(html);
            }
            if (mlist) {
                var html = "";
                for (var i = 0; i < mlist.length; i++) {
                    mlist[i].id = mlist[i].id + "";
                    html += "<li lid='" + mlist[i].list_id + "' mid='" + mlist[i].id + "'><span>" + mlist[i].name + "</span></li>";
                    mids.splice(-1, 0, mlist[i].id.split("_"));
                }
                $("#xhao").html(html);
            }
            if (clist) {
                var html = "";
                for (var i = 0; i < clist.length; i++) {
                    clist[i].id = clist[i].id + "";
                    html += "<li lid='" + clist[i].list_id + "' cid='" + clist[i].id + "'><span>" + clist[i].name + "</span></li>";
                    cids.splice(-1, 0, clist[i].id.split("_"));
                }
                $("#fnei").html(html);
                //根据url参数选中相应的搜索条件
                if (b) {
                    $("#ppl li").each(function (index, obj) {
                        var bid = $(obj).attr("bid");
                        var name = $(obj).text();
                        if (name === b) {
                            $(this).click();
                        }
                    })
                }
                if (l) {
                    $("#xl li").each(function (index, obj) {
                        var lid = $(obj).attr("lid");
                        var name = $(obj).text();
                        if (name === l) {
                            $(this).click();
                        }
                    })
                }
                if (m) {
                    $("#xhao li").each(function (index, obj) {
                        var mid = $(obj).attr("mid");
                        var name = $(obj).text();
                        if (name === b) {
                            $(this).click();
                        }
                    })
                }
                if (c) {
                    $("#fnei li").each(function (index, obj) {
                        var cid = $(obj).attr("cid");
                        var name = $(obj).text();
                        if (name === b) {
                            $(this).click();
                        }
                    })
                }
                if (!b && !l && !m && !c) {
                    queryGoods();
                }
            }
        } else {
            alert(res.message)
        }
    }
});

$(document).on("click", "#ppl li", function () {
    bids = [];
    lids = [];
    mids = [];
    cids = [];
    var bid = $(this).attr("bid");
    var bidArry = bid.split("_");
    bids = bidArry;
    $(this).removeAttr("style").siblings().attr("style", "color:red");
    var lidArry = [];
    $("#xl li").each(function (index, obj) {
        var l_lid = $(obj).attr("lid");
        var llidArry = l_lid.split("_");
        var l_bid = $(obj).attr("bid");
        var lbidArry = l_bid.split("_");
        var has = false;
        for (var i = 0; i < lbidArry.length; i++) {
            if ($.inArray(lbidArry[i], bidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < llidArry.length; i++) {
                if ($.inArray(llidArry[i], lidArry) === -1) {
                    lidArry.push(llidArry[i]);
                }
                if ($.inArray(llidArry[i], lids) === -1) {
                    lids.push(llidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#xhao li").each(function (index, obj) {
        var m_lid = $(obj).attr("lid");
        var m_mid = $(obj).attr("mid");
        var mlidArry = m_lid.split("_");
        var mmidArry = m_mid.split("_");
        var has = false;
        for (var i = 0; i < mlidArry.length; i++) {
            if ($.inArray(mlidArry[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < mmidArry.length; i++) {
                if ($.inArray(mmidArry[i], mids) === -1) {
                    mids.push(mmidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#fnei li").each(function (index, obj) {
        var c_lid = $(obj).attr("lid");
        var c_cid = $(obj).attr("cid");
        var clidArry = c_lid.split("_");
        var ccidArry = c_cid.split("_");
        var has = false;
        for (var i = 0; i < clidArry.length; i++) {
            if ($.inArray(clidArry[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < ccidArry.length; i++) {
                if ($.inArray(ccidArry[i], cids) === -1) {
                    cids.push(ccidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    queryGoods();
    $("#chichi").html("关于“" + $(this).text() + "”的搜索结果");
    $("#xinxi").html("非常抱歉没有找到" + $(this).text() + "的相关信息");
    // window.location.href = "/shopping/searchPage?key=" + key + "&b=" + b + "&l=" + l + "&m=" + m + "&c=" + c + "&s=1";
});
$(document).on("click", "#xl li", function () {
    bids = [];
    lids = [];
    mids = [];
    cids = [];
    var bid = $(this).attr("bid");
    var bidArry = bid.split("_");
    var lidArry = $(this).attr("lid").split("_");
    lids = lidArry;
    $(this).removeAttr("style").siblings().attr("style", "color:red");
    $("#ppl li").each(function (index, obj) {
        var b_bid = $(obj).attr("bid");
        var bbidArray = b_bid.split("_");
        var has = false;
        for (var i = 0; i < bbidArray.length; i++) {
            if ($.inArray(bbidArray[i], bidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < bbidArray.length; i++) {
                if ($.inArray(bbidArray[i], bids) === -1) {
                    bids.push(bbidArray[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#xhao li").each(function (index, obj) {
        var m_lid = $(obj).attr("lid");
        var m_mid = $(obj).attr("mid");
        var mlidArry = m_lid.split("_");
        var mmidArry = m_mid.split("_");
        var has = false;
        for (var i = 0; i < mlidArry.length; i++) {
            if ($.inArray(mlidArry[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < mmidArry.length; i++) {
                if ($.inArray(mmidArry[i], mids) === -1) {
                    mids.push(mmidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#fnei li").each(function (index, obj) {
        var c_lid = $(obj).attr("lid");
        var c_cid = $(obj).attr("cid");
        var clidArry = c_lid.split("_");
        var ccidArry = c_cid.split("_");
        var has = false;
        for (var i = 0; i < clidArry.length; i++) {
            if ($.inArray(clidArry[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < ccidArry.length; i++) {
                if ($.inArray(ccidArry[i], cids) === -1) {
                    cids.push(ccidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    queryGoods();
    $("#chichi").html("关于“" + $(this).text() + "”的搜索结果");
    $("#xinxi").html("非常抱歉没有找到" + $(this).text() + "的相关信息");
    // window.location.href = "/shopping/searchPage?key=" + key + "&b=" + b + "&l=" + l + "&m=" + m + "&c=" + c + "&s=1";
});
$(document).on("click", "#xhao li", function () {
    bids = [];
    lids = [];
    mids = [];
    cids = [];
    var lid = $(this).attr("lid");
    var mid = $(this).attr("mid");
    var lidArry = lid.split("_");
    var midArry = mid.split("_");
    mids = midArry;
    var bidArry = [];
    $(this).removeAttr("style").siblings().attr("style", "color:red");
    $("#xl li").each(function (index, obj) {
        var l_bid = $(obj).attr("bid");
        var lbidArry = l_bid.split("_");
        var l_lid = $(obj).attr("lid");
        var llidArray = l_lid.split("_");
        var has = false;
        for (var i = 0; i < llidArray.length; i++) {
            if ($.inArray(llidArray[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < lbidArry.length; i++) {
                if ($.inArray(lbidArry[i], bidArry) === -1) {
                    bidArry.push(lbidArry[i])
                }
            }
            for (var i = 0; i < llidArray.length; i++) {
                if ($.inArray(llidArray[i], lidArry) === -1) {
                    lidArry.push(llidArray[i])
                }
                if ($.inArray(llidArray[i], lids) === -1) {
                    lids.push(llidArray[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#ppl li").each(function (index, obj) {
        var b_bid = $(obj).attr("bid");
        var bbidArray = b_bid.split("_");
        var has = false;
        for (var i = 0; i < bbidArray.length; i++) {
            if ($.inArray(bbidArray[i], bidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < bbidArray.length; i++) {
                if ($.inArray(bbidArray[i], bids) === -1) {
                    bids.push(bbidArray[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#fnei li").each(function (index, obj) {
        var c_lid = $(obj).attr("lid");
        var c_cid = $(obj).attr("cid");
        var clidArry = c_lid.split("_");
        var ccidArry = c_cid.split("_");
        var has = false;
        for (var i = 0; i < clidArry.length; i++) {
            if ($.inArray(clidArry[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < ccidArry.length; i++) {
                if ($.inArray(ccidArry[i], cids) === -1) {
                    cids.push(ccidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    queryGoods();
    $("#chichi").html("关于“" + $(this).text() + "”的搜索结果");
    $("#xinxi").html("非常抱歉没有找到" + $(this).text() + "的相关信息");
});
$(document).on("click", "#fnei li", function () {
    bids = [];
    lids = [];
    mids = [];
    cids = [];
    var lid = $(this).attr("lid");
    var cid = $(this).attr("cid");
    var lidArry = lid.split("_");
    var cidArry = cid.split("_");
    cids = cidArry;
    var bidArry = [];
    $(this).removeAttr("style").siblings().attr("style", "color:red");
    $("#xl li").each(function (index, obj) {
        var l_bid = $(obj).attr("bid");
        var lbidArry = l_bid.split("_");
        var l_lid = $(obj).attr("lid");
        var llidArray = l_lid.split("_");
        var has = false;
        for (var i = 0; i < llidArray.length; i++) {
            if ($.inArray(llidArray[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < lbidArry.length; i++) {
                bidArry.push(lbidArry[i])
            }
            for (var i = 0; i < llidArray.length; i++) {
                if ($.inArray(llidArray[i], lidArry) === -1) {
                    lidArry.push(llidArray[i])
                }
                if ($.inArray(llidArray[i], lids) === -1) {
                    lids.push(llidArray[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#ppl li").each(function (index, obj) {
        var b_bid = $(obj).attr("bid");
        var bbidArray = b_bid.split("_");
        var has = false;
        for (var i = 0; i < bbidArray.length; i++) {
            if ($.inArray(bbidArray[i], bidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < bbidArray.length; i++) {
                if ($.inArray(bbidArray[i], bids) === -1) {
                    bids.push(bbidArray[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    $("#xhao li").each(function (index, obj) {
        var m_lid = $(obj).attr("lid");
        var m_mid = $(obj).attr("mid");
        var mlidArry = m_lid.split("_");
        var mmidArry = m_mid.split("_");
        var has = false;
        for (var i = 0; i < mlidArry.length; i++) {
            if ($.inArray(mlidArry[i], lidArry) !== -1) {
                has = true;
                break;
            }
        }
        if (has) {
            $(obj).removeAttr("style");
            for (var i = 0; i < mmidArry.length; i++) {
                if ($.inArray(mmidArry[i], mids) === -1) {
                    mids.push(mmidArry[i])
                }
            }
        } else {
            $(obj).attr("style", "color:red")
        }
    });
    queryGoods();
    $("#chichi").html("关于“" + $(this).text() + "”的搜索结果");
    $("#xinxi").html("非常抱歉没有找到" + $(this).text() + "的相关信息");
});

function queryGoods() {
    $.ajax({
        type: "get",
        cache: false,
        // url: "/query/goods?key=" + key + "&b=" + bids.join(",") + "&m=" + mids.join(",") + "&l=" + lids.join(",") + "&c=" + cids.join(",") + "&p=" + p + "&s=" + s +"&t_="+Math.random(),
        url: "/query/goods",
        data: {
            key: key,
            b: bids.join(","),
            m: mids.join(","),
            l: lids.join(","),
            c: cids.join(","),
            p: p,
            s: s
        },
        dataType: "json",
        success: function (res) {
            if (res.success) {
                if(res.data.count==0)
                {
                    //搜索没有数据！
                    $("#shulian").html("");
                    $(".tbpager").hide();
                    $(".spwu").show();

                }
                else {
                    $(".tbpager").show();
                    $(".spwu").hide();
                    var tpp = "";
                    $.each(res.data.goods, function (i, item) {
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
                        tpp += '<p class="porut_p">¥' + item.show_price.toFixed(2) + '</p>';
                        tpp+='<a href="details.html?id='+item.id+'"><p class="porut_pa">'+item.full_name+'</p></a>';
                        tpp += '</li>';
                    });
                    $("#shulian").html(tpp);

                    //立即购买
                    $(".porut_kua").click(function () {
                        var goodsId = $(this).attr("id").substring(3);
                        jiago(goodsId);
                    })
                    //加入购物车
                    $(".porut_kub").click(function () {
                        var gogo = $(this).attr("id").substring(3);
                        var buyType = 0;

                        gocart(gogo, buyType);
                    });
                    $(".tbapager").html(pagerUtil(s, res.data.count, p));
                }
            }
            else {
                // alert(res.message);
            }
        }
    });
}

function turnPage(currentPage) {
    p = currentPage;
    queryGoods();
}

$("#ttpid").on("keyup", function (event) {
    if (event.keyCode === 13) {
        key = $(this).val();
        window.location.href = "/shopping/searchPage?key=" + key + "&b=" + b + "&l=" + l + "&m=" + m + "&c=" + c + "&s=1";
    }
});
$("#search_iconxin").on("click", function () {
    key = $(this).val();
    window.location.href = "/shopping/searchPage?key=" + key + "&b=" + b + "&l=" + l + "&m=" + m + "&c=" + c + "&s=1";
});
$(".serp_gd .btn").click(function () {
    $(this).parent().siblings(".serch_mc").css("height", "auto");
    $(this).hide();
    $(this).next().show();
});

$(".serp_gd .btn1").click(function () {
    $(this).parent().siblings(".serch_mc").css("height", "61px");
    $(this).hide();
    $(this).prev().show();
})

$(".btnfan").click(function () {
    history.go(-1);
});
$(".btnshou").click(function () {
    window.location.href="index.html";
});
// 点击事件
$("#btn").click(function(){
//  		  $("#er").show()
    $('#mco').css('height','auto');
    $(this).hide();
    $(this).siblings("#btn1").show();


})

$("#btn1").click(function() {
    $('#mco').css('height','61px');


    $(this).siblings("#btn").show();

    $(this).hide();

})
$("#btna").click(function(){

    $('#mcone').css('height','auto');

    $(this).hide();

    $(this).siblings("#btna1").show();

})

$("#btna1").click(function() {
    $('#mcone').css('height','61px');


    $(this).siblings("#btna").show();

    $(this).hide();

})
$("#btnb").click(function(){

    $('#mctwo').css('height','auto');

    $(this).hide();

    $(this).siblings("#btnb1").show();

})

$("#btnb1").click(function() {
    $('#mctwo').css('height','61px');


    $(this).siblings("#btnb").show();

    $(this).hide();

})

$("#btnc").click(function(){

    $('#mcthree').css('height','auto');

    $(this).hide();

    $(this).siblings("#btnc1").show();

})

$("#btnc1").click(function() {
    $('#mcthree').css('height','61px');

    $(this).siblings("#btnc").show();

    $(this).hide();

})

