$(function() {
    var isClick = false;
    var shoppingid = getParam('id');
    var collectId = shoppingid; // 全局参数：收藏商品/取消收藏用到
    var actId = getParam('actId');
    $("#head").load("head.html");
    // 设置局部全局参数：mystore=res.data.attrVals;
    var mystore;


    // 去购物车
    $("#fo").click(function() {
        window.location = "buyCar.html"
    });

    //弹窗关闭按钮
    $(".know").click(function() {
        $(".meng2").hide();
        $(".popupMsg").hide();
        $("body").css('overflow-y', 'auto');
    });
    //弹窗关闭按钮--X
    $(".close").click(function() {
        $(".meng2").hide();
        $(".popupMsg").hide();
        $("body").css('overflow-y', 'auto');

    });

    // 立即支付
    function zhifu(goodsId, buyNum) {
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/order/newConfirmOrder", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            async: true, //是否异步
            data: {
                goodsId: goodsId,
                buyNum: buyNum
            },
            success: function(res) {
                // 后台判断：code=1，成功；其他均失败
                if (res.code == 1) {
                    var data = res.data
                    var datasss = JSON.stringify(data)
                    localStorage.setItem("datas", datasss)
                        //localStorage.buyCar为空标记不是从购物车进入支付页面
                    window.localStorage.setItem("buyCar", '');
                    window.location = "orderList.html"
                } else {
                    //    库存不足等错误信息
                    $(".msg2").html(res.message);
                    $(".meng2").show();
                    $(".popupMsg").show();
                }
            }
        })
    };

    //查看收藏
    $("#Collection").click(function() {
        window.location.href = "left#sc";
    });

    //加入购物车弹窗内按钮
    $("#tuicart").click(function() {
        $("#megou").hide();
        $("body").css('overflow-y', '');
    });
    // 收藏成功弹窗内按钮
    $("#catop").click(function() {
        $("#meng").hide();
        $("body").css('overflow-y', '');
    });

    //取消收藏按钮
    $("#catop1").click(function() {
        $("#meng").hide();
        $("body").css('overflow-y', '');
    });

    $('.cp_aul li a').click(function() {
        $('.cp_aul li a').removeClass("ave");
        $('.cp_aul li').removeClass("ave");
        $(this).addClass("ave");
    });

    // var ti = $("#quantity");
    // $("#add").click(function() {
    //     if (parseInt(ti.val()) < window.localStorage.getItem("stockNum")) {
    //         ti.val(parseInt(ti.val()) + 1);
    //         $("#min").removeAttr("disabled");
    //         if (parseInt(ti.val()) == window.localStorage.getItem("stockNum")) {
    //             $("#add").css("color", "#E5E5E5");
    //         }
    //         if (parseInt(ti.val()) > 1) {
    //             $("#min").css("color", "#4F5362")
    //         }
    //     } else {
    //         $("#add").css("color", "#E5E5E5");
    //         $("#add").attr("disabled", "disabled")
    //     };
    // });

    // $("#min").click(function() {
    //     if (parseInt(ti.val()) > 1) {
    //         ti.val(parseInt(ti.val()) - 1);
    //         if (parseInt(ti.val()) == 1) {
    //             $("#min").css("color", "#E5E5E5")
    //         } else if (parseInt(ti.val()) > 1) {
    //             $("#min").css("color", "#4F5362")
    //         }
    //     } else {
    //         $("#min").css("color", "#4F5362")
    //         $("#min").attr("disabled", "disabled")
    //     }
    // });


    // 商品数量加减：

    // 1.加

    $("#add").click(function() {
        var narr = [];
        if (mystore && mystore.length > 0) {
            for (var i = 0; i < mystore.length; i++) {
                var myarr = mystore[i].attr_val.split('_');
                if (myarr.length == $(".avtie").length) { // 过滤参数数量不等的型号
                    var flag = true;
                    for (var j = 0; j < $(".avtie").length; j++) {
                        var myflag = true;
                        var str = $('.avtie').eq(j).find('p').html();
                        var nstr = $('.avtie').eq(j).parents('.dt_gri').siblings().children().html() + ':';
                        for (var k = 0; k < myarr.length; k++) {
                            if (myarr[k] === nstr + str) {
                                myflag = false;
                            }
                        }
                        if (myflag) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        narr.push(mystore[i]);
                    }
                }
            }
        }
        // console.log(narr, '----------');
        if (narr.length > 0) { // 判断当前参数是否有该种情况（新增：参数可以不全选）
            var my_num = parseInt($('#quantity').val());
            var my_store = parseInt($('#dt_glespas').attr('goodsnum'));
            if (my_num <= my_store - 1) {
                $('#quantity').val(my_num + 1);
                if (my_num < my_store - 1) {
                    $(this).css('color', 'rgb(79, 83, 98)');
                } else {
                    $(this).css('color', 'rgb(229, 229, 229)')
                }

                if (my_num + 1 >= 2) {
                    $("#min").css('color', 'rgb(79, 83, 98)')
                } else {
                    $("#min").css('color', 'rgb(229, 229, 229)')
                }

            } else {
                $('#quantity').val(my_store);
                $(this).css('color', 'rgb(229, 229, 229)');

                if (my_store >= 2) {
                    $("#min").css('color', 'rgb(79, 83, 98)')
                } else {
                    $("#min").css('color', 'rgb(229, 229, 229)')
                }
            }

            if (parseInt($('#quantity').val()) <= 0) {
                $('#quantity').val(1)
            }
        }
    })

    // 2.减

    $("#min").click(function() {
        var narr = [];
        if (mystore && mystore.length > 0) {
            for (var i = 0; i < mystore.length; i++) {
                var myarr = mystore[i].attr_val.split('_');
                if (myarr.length == $(".avtie").length) { // 过滤参数数量不等的型号
                    var flag = true;
                    for (var j = 0; j < $(".avtie").length; j++) {
                        var myflag = true;
                        var str = $('.avtie').eq(j).find('p').html();
                        var nstr = $('.avtie').eq(j).parents('.dt_gri').siblings().children().html() + ':';
                        for (var k = 0; k < myarr.length; k++) {
                            if (myarr[k] === nstr + str) {
                                myflag = false;
                            }
                        }
                        if (myflag) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        narr.push(mystore[i]);
                    }
                }
            }
        }


        // console.log(narr, '----------');
        if (narr.length > 0) { // 判断当前参数是否有该种情况（新增：参数可以不全选）
            var my_num = parseInt($('#quantity').val());
            var my_store = parseInt($('#dt_glespas').attr('goodsnum'));
            if (my_num <= 2) {
                $('#quantity').val(1);
                $(this).css('color', 'rgb(229, 229, 229)');
                if (my_store > 1) {
                    $("#add").css('color', 'rgb(79, 83, 98)');
                } else {
                    $("#add").css('color', 'rgb(229, 229, 229)')
                }
            } else {
                $('#quantity').val(my_num - 1);
                $(this).css('color', 'rgb(79, 83, 98)');

                if (my_num - 1 >= 2) {
                    $("#add").css('color', 'rgb(79, 83, 98)');
                } else {
                    $("#add").css('color', 'rgb(229, 229, 229)')
                }
            }

            if (parseInt($('#quantity').val()) <= 0) {
                $('#quantity').val(1)
            }
        }
    });

    // 数量校验：键盘抬起事件，不考虑为空，为空的合并到失去焦点事件里面

    $('#quantity').keyup(function() {

        var val = parseInt($(this).val());
        var stock = parseInt($(this).parents('.dt_shu').find('#dt_glespas').attr('goodsnum'));
        if (val > stock) {
            if (stock > 0) {
                $(this).val(stock);
            } else {
                $(this).val(1);
            }
        }
        if ((val <= 1 || isNaN(val)) && $(this).val() != '') {
            $(this).val(1);
        }
        // 修正当前val值
        val = parseInt($(this).val());
        if (val >= stock) {
            $("#add").css('color', 'rgb(229, 229, 229)');
            if (stock > 1) {
                $("#min").css('color', 'rgb(79, 83, 98)')
            } else {
                $("#min").css('color', 'rgb(229, 229, 229)');
            }

        } else {
            $("#add").css('color', 'rgb(79, 83, 98)');
            if (val > 1) {
                $("#min").css('color', 'rgb(79, 83, 98)')
            } else {
                $("#min").css('color', 'rgb(229, 229, 229)');
            }
        }


    });
    // 数量校验：失去焦点事件

    $("#quantity").blur(function() {
        var stock = parseInt($(this).parents('.dt_shu').find('#dt_glespas').attr('goodsnum'));
        if ($(this).val() == "") {
            $(this).val(1);

            // 按钮颜色控制
            $("#min").css('color', 'rgb(229, 229, 229)');
            if (stock > 1) {
                $("#add").css('color', 'rgb(79, 83, 98)');
            } else {
                $("#add").css('color', 'rgb(229, 229, 229)');
            }

        }
    });

    var top = $('.cp_b').offset().top;

    $(document).scroll(function() {

        var scrTop = $(window).scrollTop();

        if (scrTop >= top) {
            $('.cp_a').css({
                'position': 'fixed',
                'top': '0',
                'box-shadow': '0px 1px 10px #ccc',
                'height': '46px'
            });
            $
            $(".ggw").show();

        } else {
            $('.cp_a').css({
                'position': '',
                'top': '',
                'box-shadow': 'none'
            });
            $(".ggw").hide()
        }
    });

    // 商品详情
    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/fplat/goodsDetail",
        // url: "/zlead/fplat/goodsDetail?goodsId=" + goodsId + "&actId=" + actId +"&t_=" + Math.random(),
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            goodsId: shoppingid,
            actId: actId,
            t_: Math.random()
        },
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                if (res.data != null) {
                    // 设置局部全局参数：mystore=res.data.attrVals;
                    mystore = res.data.attrVals;
                    var str = "";
                    var stra = "";
                    var strb = "";
                    var strc = "";
                    if (res.data.goodsDetailDto.imgs == null || res.data.goodsDetailDto.imgs == "") {
                        strb += '<li class="on">';
                        strb += '<img src="' + res.data.goodsDetailDto.firstImag + '" width="70" height="70">';
                        strb += '</li>';
                        strc += '<li>';
                        strc += '<img src="../../shopping/img/details/zoom-in.png" class="fada" />';
                        strc += '<img src="' + res.data.goodsDetailDto.firstImag + '" width="360" height="360">';
                        strc += '</li>';
                    } else {



                        var deimg = res.data.goodsDetailDto.imgs;
                        if (deimg) {
                            var deticimg = deimg.split(",");
                        } else {
                            var deticimg = ['../../shopping/img/index/sl3.png']
                        }
                        for (var i = 0; i < deticimg.length; i++) {
                            if (i == 0) {
                                strb += '<li class="on"><div></div>';
                                strb += '<img src="' + deticimg[i] + '" width="70" height="70">';
                                strb += '</li>';

                            } else {
                                strb += '<li class=""><div></div>';
                                strb += '<img src="' + deticimg[i] + '" width="70" height="70">';
                                strb += '</li>';
                            }
                            strc += '<li>';
                            strc += '<img src="../../shopping/img/details/zoom-in.png" class="fada" />';
                            strc += '<img src="' + deticimg[i] + '" width="360" height="360">';
                            strc += '</li>';
                        }
                    }

                    var k = 0;
                    //商品图片
                    $("#large_boxul").html(strb)
                    $("#qul").html(strc)

                    $(".large_boxul li").on("click", function() {
                            Img($(this).index())

                        })
                        /* 上按钮 */
                    $("#left_btn").click(function() {
                        var i;
                        var l = $(".small_list").find("ul li").length;
                        $(".small_list").find("ul li").each(function(index) {
                            if ($(this).hasClass("on")) {
                                i = index;
                            }
                        });
                        i--;
                        if (i < 0) {
                            i = 0;
                            $('#left_btn').attr('src', '../../shopping/img/details/Shape Copy2.png')
                        } else {
                            if (i < 1) {
                                $('#left_btn').attr('src', '../../shopping/img/details/Shape Copy2.png')
                            } else {
                                $('#left_btn').attr('src', '../../shopping/img/details/Shape Copy.png')
                                if (i > l - 1) {
                                    i = l - 1;
                                    $('#right_btn').attr('src', '../../shopping/img/details/Shape.png')
                                } else {
                                    $('#right_btn').attr('src', '../../shopping/img/details/Shape2.png')
                                }
                            }
                        }

                        if (i < 3) {
                            $(".small_list").find("ul li").stop().css({
                                transform: 'translate(0,0)'
                            })
                        } else {
                            k--;
                            $(".small_list").find("ul li").stop().css({
                                transform: 'translate(0,-' + k * 78 + 'px)'
                            })
                        }

                        t = i;
                        Img(i)

                    })

                    /* 下按钮 */
                    $("#right_btn").click(function() {
                        var i;
                        var l = $(".small_list").find("ul li").length;
                        $(".small_list").find("ul li").each(function(index) {
                            if ($(this).hasClass("on")) {
                                i = index;
                            }
                        });
                        i++;
                        if (i > 3 && i < l) {
                            k++;
                            $(".small_list").find("ul li").stop().css({
                                transform: 'translate(0,-' + k * 78 + 'px)'
                            })
                        }

                        if (i > l - 1) {
                            i = l - 1;
                            $('#right_btn').attr('src', '../../shopping/img/details/Shape.png')
                        } else {
                            if (i == l - 1) {
                                $('#right_btn').attr('src', '../../shopping/img/details/Shape.png')

                            } else {
                                $('#right_btn').attr('src', '../../shopping/img/details/Shape2.png')
                            }
                            if (i < 0) {
                                i = 0;
                                $('#left_btn').attr('src', '../../shopping/img/details/Shape Copy.png')
                            } else {
                                $('#left_btn').attr('src', '../../shopping/img/details/Shape Copy2.png')
                            }
                        }

                        if (k > 0) {
                            $('#left_btn').attr('src', '../../shopping/img/details/Shape Copy.png')
                        }

                        t = i;
                        Img(i);

                    })

                    // function changeImg() {
                    //     var k;
                    //     var length = $(".small_list").find("ul li").length;
                    //     $(".small_list").find("ul li").each(function(index) {
                    //         if ($(this).hasClass("on")) {
                    //             k = index;
                    //         }
                    //     });

                    //     if (k == 3 && length < 4) {

                    //     }
                    // }

                    /* 图片 */
                    function Img(i) {
                        var l = $(".small_list").find("ul li").length;
                        var l_mean;
                        if (l < 5) {
                            l_mean = 0;
                        } else {
                            l_mean = ((parseInt(l / 5) - 1) * 5) + (l % 5);
                        }
                        var w = 110;
                        $(".large_box").find("ul li").eq(i).fadeIn().siblings().hide();
                        $(".small_list").find("ul li").eq(i).addClass("on").siblings().removeClass("on");
                        var ml = i * w;
                        // if (ml <= l_mean * w) {
                        //     $(".small_list").find("ul").stop().animate({
                        //         marginLeft: -ml + "px"
                        //     })
                        // } else {
                        //     $(".small_list").find("ul").stop().animate({
                        //         marginLeft: -(l_mean * w) + "px"
                        //     })
                        // }
                    }

                    //无论点击哪一个img弹出层都会展示相应的图片。
                    $(".qul li .fada").on("click", function() {
                        $("body").css('overflow-y', 'hidden');
                        $(this).siblings().each(function() {
                            var $this = $(this);
                            var $img = $this.attr("src"); //获取当前点击img的src的值
                            $("#img-box").find("img").attr("src", $img); //将获取的当前点击img的src赋值到弹出层的图片的src
                            $("#dialog-bg").show(); //弹出层显示
                        });

                    });

                    //弹出层隐藏
                    $("#dialog-bg").on("click", function() {
                        $(this).hide(); //
                        $("body").css('overflow-y', '')
                    });
                    // 失去焦点事件写到上面鼠标抬起事件下面
                    // $("#quantity").blur(function() {
                    //     if ($(this).val() == "") {
                    //         $(this).val(this.defaultValue);
                    //     }
                    // });
                    if (res.data != null) {
                        str += '<p class="dt_ps">' + res.data.goodsDetailDto.goodsName + '</p>';

                        if (res.data.goodsDetailDto.prodType == 0) {
                            str += '<p class= "dt_pt" > <span> ¥' + res.data.goodsDetailDto.showPrice.toFixed(2) + ' </span>';
                        } else if (res.data.goodsDetailDto.prodType == 2) {
                            str += '<p class= "dt_pt" > <span> ¥' + res.data.goodsDetailDto.showPrice.toFixed(2) + ' </span>';
                            str += '<span><del>¥' + res.data.goodsDetailDto.price.toFixed(2) + '</del ></span>';
                        }
                        str += '</p >';
                    }
                    $("#shoppid_de").html(res.data.goodsDetailDto.id)
                        //商品名称价格
                    $("#dect_titile").html(str)

                    // 判断是否为活动商品

                    if (res.data.goodsDetailDto.prodType == 2) { //活动商品
                        var act_str = '';
                        act_str += '<span> ¥' + res.data.goodsDetailDto.showPrice.toFixed(2) + '</span>';
                        act_str += '<span> ¥' + res.data.goodsDetailDto.resultPrice.toFixed(2) + '</span>';
                        $('.dt_pt').html(act_str)

                    } else if (res.data.goodsDetailDto.prodType == 0) {

                        // 判断是否显示价格:ifShowPrice=1:展示
                        if (res.data.goodsDetailDto.ifShowPrice == 1) {
                            var act_str = '';
                            act_str += '<span> ¥' + res.data.goodsDetailDto.showPrice.toFixed(2) + '</span>';
                            $('.dt_pt').html(act_str)
                        } else {
                            var act_str = '';
                            act_str += '<span> ¥--</span>';
                            $('.dt_pt').html(act_str)
                        }
                    }





                    if (res.data != null) {
                        //商品品牌
                        if (res.data.goodsDetailDto.bandName == "" || res.data.goodsDetailDto.bandName == null) {
                            $("#dt_pbping").html("暂无");
                        } else {
                            $("#dt_pbping").html(res.data.goodsDetailDto.bandName);
                        }
                        //商品型号
                        if (res.data.goodsDetailDto.modelName == "" || res.data.goodsDetailDto.modelName == null) {
                            $("#dt_pbxin").html("暂无");
                        } else {
                            $("#dt_pbxin").html(res.data.goodsDetailDto.modelName);
                        }

                        //库存
                        window.localStorage.setItem("stockNum", res.data.goodsDetailDto.stock);

                        //库存
                        if (res.data.goodsDetailDto.ifShowStock == 1) {
                            $("#dt_glespas").html(res.data.goodsDetailDto.stock).attr('goodsnum', res.data.goodsDetailDto.stock);
                        } else if (res.data.goodsDetailDto.ifShowStock == 0) {
                            $("#dt_glespas").html("--").attr('goodsnum', res.data.goodsDetailDto.stock);
                        }

                        if (res.data.goodsDetailDto.stock <= 1) {
                            $("#add").css("color", "rgb(229, 229, 229)");
                            $("#min").css("color", "rgb(229, 229, 229)")
                        } else if (res.data.goodsDetailDto.stock > 1) {
                            $("#add").css("color", "#4F5362");
                            $("#min").css("color", "rgb(229, 229, 229)");
                        }
                        //商品介绍
                        $("#cp_tu").html(res.data.goodsDetailDto.intro);
                        //包装参数
                        var zsbz = res.data.goodsDetailDto.spec;
                    }

                    var csz = "";
                    if (zsbz == "" || zsbz == null) {
                        csz += '<li><span>暂无</span></li>';
                    } else {
                        var asw = zsbz.split(",");
                        for (var q = 0; q < asw.length; q++) {
                            csz += '<li><span>' + asw[q] + '</span></li>';
                        }
                    }

                    $("#cs_bz").html(csz)

                    var sc = "";
                    if (res.data != null) {
                        if (res.data.goodsDetailDto.isCollect == 1) {
                            sc += '<img class="star" id="scx" src="../../shopping/img/details/scx.png"/>';
                            sc += '<img class="scx"  id="star"  src="../../shopping/img/details/Star.png"/>';
                        } else {
                            sc += '<img class="star" id="star"  src="../../shopping/img/details/Star.png"/>';
                            sc += '<img class="scx" id="scx"  src="../../shopping/img/details/scx.png"/>';
                        }
                    }
                    $("#dt_scimg").html(sc);

                    //收藏商品
                    $(document).on('click', "#star", function() {

                        $.ajax({
                            type: "post", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/zlead/fplat/saveCollect/" + collectId + "", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            data: {

                            },
                            async: true, //是否异步
                            success: function(res) {
                                if (res.code == 1) {
                                    $("#star").hide();
                                    $("#scx").show();
                                    $("#meng").show();
                                    $(".guanlian1").hide();
                                    $(".guanlian").show()

                                }

                            },
                            error: function(request) { //请求出错
                            }
                        })

                    });

                    //取消商品
                    $(document).on('click', "#scx", function() {
                        $.ajax({
                            type: "post", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/zlead/fplat/deleteCollect/" + collectId + "", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            data: {

                            },
                            async: true, //是否异步
                            success: function(res) {
                                if (res.code == 1) {
                                    $("#scx").hide();
                                    $("#star").show();
                                    $("#meng").show();
                                    $(".guanlian").hide();
                                    $(".guanlian1").show();

                                }

                            },
                            error: function(request) { //请求出错
                            }
                        })

                    });


                    if (res.data != null) {
                        var obj = res.data.prodAttr.length;
                        for (var i = 0; i < obj; i++) {
                            var tsd = res.data.prodAttr[i];
                            Object.keys(tsd).forEach(function(key) {
                                stra += '<div class="dt_g">';
                                stra += '<div class="dt_gle">';
                                stra += '<span class="dt_glesp">' + key + '</span>';
                                stra += '</div>';
                                stra += '<div class="dt_gri">';
                                stra += '<ul class="dt_yul" id="dt_yul' + i + '">';
                                for (var j = 0; j < tsd[key].length; j++) {
                                    if (tsd[key][j].show == 1) {
                                        stra += '<li class="avtie dt_ulyli">';

                                        stra += '<div class="dt_liyz">';
                                        stra += '<p>' + tsd[key][j].content + '</p>';
                                        stra += '</div>';
                                        stra += '<div class="gocy">';
                                        stra += '<div class="gou">';
                                        stra += '<img src="../../shopping/img/details/goucon.png" />';
                                        stra += '</div>';
                                        stra += '</div>';

                                        stra += '</li>';
                                    } else {
                                        stra += '<li class="a dt_ulyli">';

                                        stra += '<div class="dt_liyz">';
                                        stra += '<p>' + tsd[key][j].content + '</p>';
                                        stra += '</div>';
                                        stra += '<div class="gocy">';
                                        stra += '<div class="gou none">';
                                        stra += '<img src="../../shopping/img/details/goucon.png" />';
                                        stra += '</div>';
                                        stra += '</div>';

                                        stra += '</li>';
                                    }

                                }

                                stra += '</ul> ';
                                stra += '</div>';
                                stra += '</div>';

                            })

                        }
                    }
                    $("#dt_yans").html(stra);
                    // changeColor();
                    //根据参数查询商品:每次点击查询一次最新值

                    $(".dt_yul").on("click", "li", function() {

                        if ($(this).hasClass('avtie')) {
                            $(this).removeClass('avtie').addClass("a");
                            $(this).find(".gou").hide();
                            changeColor();
                        } else {
                            var _this = $(this);
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                // url: "/zlead/fplat/goodsDetail?goodsId=" + shoppingid + "&actId=" + actId + "&t_=" + Math.random(),
                                url: "/zlead/fplat/goodsDetail",
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                data: {
                                    goodsId: shoppingid,
                                    actId: actId,
                                    t_: Math.random()
                                },
                                async: true, //是否异步
                                success: function(res) {
                                    // 更新参数库：mystore,后面点击支付和加入购物车需要用到
                                    mystore = res.data.attrVals;
                                    var myarr = res.data.attrVals; // 参数库

                                    // var leng = $('.avtie').length;
                                    var leng = $('.dt_yul').length;
                                    // --------------分割线---------------------------------------
                                    var index = _this.parents('.dt_g').index(); //-----当前父元素索引-------------
                                    var mystr = _this.find('p').html(); //-------------当前商品参数---------------
                                    var newArr = [];
                                    for (var i = 0; i < myarr.length; i++) {
                                        var flag = true;
                                        var arr = myarr[i].attr_val.split('_');
                                        // 当前之前的参数
                                        for (var j = 0; j < index; j++) {
                                            var myflag1 = true;
                                            if ($('.dt_yul').eq(j).find('.avtie').find('p').html()) {
                                                var str = $('.dt_yul').eq(j).find('.avtie').find('p').html();
                                                var nstr = $('.dt_yul').eq(j).parent().siblings().children().html() + ':';
                                                for (var k = 0; k < arr.length; k++) {
                                                    if (nstr + str == arr[k]) {
                                                        myflag1 = false;
                                                    }
                                                }
                                            } else {
                                                myflag1 = false;
                                            }

                                            if (myflag1) {
                                                flag = false;
                                            }
                                        }
                                        // 当前选中的参数
                                        var nstr = _this.parents('.dt_gri').siblings().children().html() + ':';
                                        var myflag2 = true;
                                        for (var k = 0; k < arr.length; k++) {
                                            if (nstr + mystr == arr[k]) {
                                                myflag2 = false;
                                            }
                                        }
                                        if (myflag2) {
                                            flag = false;
                                        }
                                        // 当前之后的参数
                                        for (var j = index + 1; j < leng; j++) {
                                            var myflag3 = true;
                                            if ($('.dt_yul').eq(j).find('.avtie').find('p').html()) {
                                                var str = $('.dt_yul').eq(j).find('.avtie').find('p').html();
                                                var nstr = $('.dt_yul').eq(j).parent().siblings().children().html() + ':';
                                                for (var k = 0; k < arr.length; k++) {
                                                    if (nstr + str == arr[k]) {
                                                        myflag3 = false;
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
                                    // console.log(newArr, '------------');
                                    // 当前可以点击
                                    if (newArr.length > 0) {
                                        var newArr2 = [];
                                        // console.log('可以点击');
                                        _this.find(".gou").toggle()
                                        _this.addClass("avtie").siblings().removeClass("avtie").addClass("a");
                                        _this.siblings().find(".gou").hide()
                                        if ($('.gou').is(':hidden')) {
                                            _this.find(".gou").show()
                                        }
                                    }
                                    changeColor()
                                }
                            })


                        }

                    });

                    // 改变【加入购物车】背景色
                    function changeColor(type) {
                        var newArr = [];
                        if (mystore && mystore.length > 0) {
                            for (var i = 0; i < mystore.length; i++) {
                                var myarr = mystore[i].attr_val.split('_');
                                if (myarr.length == $(".avtie").length) { // 过滤参数数量不等的型号
                                    var flag = true;
                                    for (var j = 0; j < $(".avtie").length; j++) {
                                        var myflag = true;
                                        var str = $('.avtie').eq(j).find('p').html();
                                        var nkey = $('.avtie').eq(j).parents('.dt_gri').siblings().children().html() + ':';
                                        for (var k = 0; k < myarr.length; k++) {
                                            if (myarr[k] == nkey + str) {
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
                        // console.log(newArr, '---------------');
                        if (newArr.length == 0) {
                            $("#dtgocart").css({
                                background: 'rgb(204, 204, 204)'
                            });
                            $('#payfor2').css({
                                background: 'rgb(204, 204, 204)'
                            });
                            $('.dt_pt').find('span').html('');
                            $('#dt_glespas').html('').attr('goodsNum', 0);
                            $("#min").css('color', 'rgb(229, 229, 229)');
                            $("#add").css('color', 'rgb(229, 229, 229)');
                            $('#quantity').val(1);
                        } else {
                            // 更改collectId为当前组合商品的id
                            collectId = newArr[0].goods_id;

                            // 获取当前参数组合下的收藏状态
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                // url: "/zlead/fplat/goodsDetail?goodsId=" + shoppingid + "&actId=" + actId + "&t_=" + Math.random(),
                                url: "/zlead/fplat/goodsDetail",
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                data: {
                                    goodsId: newArr[0].goods_id,
                                    actId: actId,
                                    t_: Math.random()
                                },
                                async: true, //是否异步
                                success: function(res) {
                                    // 更改收藏状态
                                    var sc = "";
                                    var type = res.data.goodsDetailDto.isCollect;
                                    if (type == 1) {
                                        sc += '<img class="star" id="scx" src="../../shopping/img/details/scx.png"/>';
                                        sc += '<img class="scx"  id="star"  src="../../shopping/img/details/Star.png"/>';
                                    } else if (type == 0) {
                                        sc += '<img class="star" id="star"  src="../../shopping/img/details/Star.png"/>';
                                        sc += '<img class="scx" id="scx"  src="../../shopping/img/details/scx.png"/>';
                                    }
                                    $("#dt_scimg").html(sc);
                                }
                            })

                            // 加减按钮颜色判断：改变参数后数量更改为1
                            $('#quantity').val(1);
                            $("#min").css('color', 'rgb(229, 229, 229)');
                            if (newArr[0].stock <= 1) {
                                $("#add").css('color', 'rgb(229, 229, 229)');
                            } else {
                                $("#add").css('color', 'rgb(79, 83, 98)');
                            }
                            if (parseInt($('#quantity').val()) > newArr[0].stock && parseInt($('#quantity').val()) > 0) { //库存不足
                                $("#dtgocart").css({
                                    background: 'rgb(204, 204, 204)'
                                });
                                $('#payfor2').css({
                                    background: 'rgb(204, 204, 204)'
                                });

                            } else { // 正常
                                $("#dtgocart").css({
                                    background: 'rgba(253, 87, 0, 1)'
                                });
                                $('#payfor2').css({
                                    background: 'rgba(255, 153, 1, 1)'
                                });
                            }
                            if (res.data.goodsDetailDto.prodType == 2) { // 走活动详情
                                // 3.25修改：后端不通过actId判断是否为活动商品了，而是根据res.data.goodsDetailDto.prodType
                                // 活动价格
                                if (newArr[0].if_show_price == 1) {
                                    $('.dt_pt').find('span').eq(0).html('¥' + newArr[0].show_price.toFixed(2)); // 当前参数价格-活动商品详情页
                                    $('.dt_pt').find('span').eq(1).html('¥' + newArr[0].resultPrice.toFixed(2)); // 当前参数价格-活动商品详情页

                                }

                                // 活动库存
                                if (newArr[0].if_show_stock == 1) {
                                    $('#dt_glespas').html(newArr[0].stock).attr('goodsNum', newArr[0].stock) //库存数量
                                } else {
                                    $('#dt_glespas').html("--").attr('goodsNum', newArr[0].stock) //库存数量
                                }

                            } else { // 普通商品
                                // 普通价格
                                if (newArr[0].if_show_price == 1) {
                                    $('.dt_pt').find('span').eq(0).html('¥' + newArr[0].show_price.toFixed(2)); // 当前参数价格-普通商品详情页
                                } else {
                                    $('.dt_pt').find('span').eq(0).html('¥--'); // 当前参数价格
                                }

                                // 普通库存
                                if (newArr[0].if_show_stock == 1) {
                                    $('#dt_glespas').html(newArr[0].stock).attr('goodsNum', newArr[0].stock) //库存数量
                                } else {
                                    $('#dt_glespas').html("--").attr('goodsNum', newArr[0].stock) //库存数量
                                }
                            }
                        }



                    }


                    // //加入购物车:参数可以不全选中
                    $("#dtgocart").click(function() {
                        if ($('#quantity').val() > 0) { //判断数量是否正确
                            var buyType = 0;
                            var count = $("#quantity").val();
                            var arr = [];
                            // console.log(mystore, '--------');
                            if (mystore && mystore.length > 0) {
                                for (var i = 0; i < mystore.length; i++) {
                                    var myarr = mystore[i].attr_val.split('_');
                                    if (myarr.length == $(".avtie").length) { // 过滤参数数量不等的型号
                                        var flag = true;
                                        for (var j = 0; j < $(".avtie").length; j++) {
                                            var myflag = true;
                                            var str = $('.avtie').eq(j).find('p').html();
                                            var nstr = $('.avtie').eq(j).parents('.dt_gri').siblings().children().html() + ':';
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
                                            arr.push(mystore[i]);
                                        }
                                    }
                                }
                            }
                            // console.log(arr, '----------');
                            for (var i = 0; i < arr.length; i++) {
                                if (arr[i].attr_val.split('_').length == $(".avtie").length) {
                                    // console.log(arr[i], '----------');
                                    // 当前数量,如果需要判断商品状态，请在这里获取商品状态
                                    var num = arr[i].stock;
                                    var goodsId = arr[i].goods_id;
                                }
                            }
                            if (num > 0) {
                                if (goodsId) {
                                    gocart(goodsId, count, buyType);
                                } else {
                                    if (($(".dt_yul").length == $(".avtie").length)) {
                                        gocart(shoppingid, count, buyType);

                                    }
                                }
                            }
                        }
                    })

                    // //加入购物车2:下拉滚动后出现的加入购物车
                    $("#ggw").click(function() {

                        if ($('#quantity').val() > 0) { //判断数量是否正确
                            var buyType = 0;
                            var count = $("#quantity").val();
                            var arr = [];
                            if (mystore && mystore.length > 0) {
                                for (var i = 0; i < mystore.length; i++) {
                                    var myarr = mystore[i].attr_val.split('_');
                                    if (myarr.length == $(".avtie").length) { // 过滤参数数量不等的型号
                                        var flag = true;
                                        for (var j = 0; j < $(".avtie").length; j++) {
                                            var myflag = true;
                                            var str = $('.avtie').eq(j).find('p').html();
                                            var nstr = $('.avtie').eq(j).parents('.dt_gri').siblings().children().html() + ':';
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
                                            arr.push(mystore[i]);
                                        }
                                    }
                                }
                            }
                            // console.log(arr, '----------');
                            for (var i = 0; i < arr.length; i++) {
                                if (arr[i].attr_val.split('_').length == $(".avtie").length) {
                                    // console.log(arr[i], '----------');
                                    // 当前数量,如果需要判断商品状态，请在这里获取商品状态
                                    var num = arr[i].stock;
                                    var goodsId = arr[i].goods_id;
                                }
                            }
                            if (num > 0) {
                                if (goodsId) {
                                    gocart(goodsId, count, buyType);
                                } else {
                                    if (($(".dt_yul").length == $(".avtie").length)) {
                                        gocart(shoppingid, count, buyType);
                                    }
                                }
                            }

                        }
                    })
                    if (res.data.goodsDetailDto.stock == 0) {

                        $(".dt_anj").css("background", "rgb(204, 204, 204)");
                        $(".dt_anz").css("background", "rgb(204, 204, 204)");
                    }
                    // 立即支付
                    $("#payfor2").click(function() {
                        if ($('#quantity').val() > 0) { //判断数量是否正确
                            var count = $("#quantity").val();
                            var arr = [];
                            if (mystore && mystore.length > 0) {
                                for (var i = 0; i < mystore.length; i++) {
                                    var myarr = mystore[i].attr_val.split('_');
                                    if (myarr.length == $(".avtie").length) { // 过滤参数数量不等的型号
                                        var flag = true;
                                        for (var j = 0; j < $(".avtie").length; j++) {
                                            var myflag = true;
                                            var str = $('.avtie').eq(j).find('p').html();
                                            var nstr = $('.avtie').eq(j).parents('.dt_gri').siblings().children().html() + ':';
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
                                            arr.push(mystore[i]);
                                        }
                                    }
                                }
                            }
                            // console.log(arr, '----------');
                            for (var i = 0; i < arr.length; i++) {
                                if (arr[i].attr_val.split('_').length == $(".avtie").length) {
                                    // console.log(arr[i], '---------');
                                    // 当前数量,如果需要判断商品状态，请在这里获取商品状态
                                    var num = arr[i].stock;
                                    var goodsId = arr[i].goods_id;
                                }
                            }
                            if (num > 0) {
                                if (goodsId) {
                                    zhifu(goodsId, count);
                                } else {
                                    if (($(".dt_yul").length == $(".avtie").length)) {
                                        zhifu(shoppingid, count);
                                    }
                                }
                            }

                        }
                    });
                }
                // } else if (res.code == 3) {
                //     $(".msg2").text("库存不足");
                //     $(".meng2").show();
                //     $(".popupMsg").show();
            } else if (res.code == 3) {
                // $(".msg2").text("商品已下架");
                // $(".meng2").show();
                // $(".popupMsg").show();
                // $('.know').click(function() {
                //     location.href = 'index.html';
                // })
                // location.href = 'index.html';
                history.go(-1);
            }

            // 面包屑导航部分
            $('.wz_conimg').css('cursor', 'pointer');
            $('.wz_conimg').click(function() {
                location.href = 'index.html'
            })

            $.ajax({
                type: "get", //提交请求的方式
                cache: true, //是否有缓存
                url: "/zlead/fplat/breadCrumbNavi?t_=" + Math.random(), //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                async: true, //是否异步
                success: function(res) {

                    // $('.my_name').html(res.data.goodsDetailDto.goodsName);

                    // $('.wz_dz').find('span').click(function() {
                    //     location.href = 'index.html'
                    // })
                    var length = res.data.length;

                    if (length == 1) { // 只有一条数据，表示从首页过来，直接渲染值即可
                        var str = '';
                        str += '<img src="../../shopping/img/details/arrow-scopy 2.png" />';
                        str += '<a href="javascript: ;" >' + res.data[0].nodeName + '</a>';
                        $('.wz_dz').html(str);
                    } else if (length == 2) { // 两条数据，表示从其他页面过来：flag :   0 收藏；1 搜索；2 活动； 3 购物车
                        var str = '';
                        str += '<img src="../../shopping/img/details/arrow-scopy 2.png" />';
                        // switch (res.data[0].flag) {
                        //     case '0':
                        //         str += '<a href="left.html#sc" >收藏</a>';
                        //         break;
                        //     case '1':
                        //         str += '<a href="' + res.data[0].nodeUrl + '" >搜索</a>';
                        //         break;
                        //     case '2':
                        //         str += '<a href="javascript:;" onclick="window.history.go(-1)">活动</a>';
                        //         break;
                        //     case '3':
                        //         str += '<a href="buyCar" >购物车</a>';
                        //         break;
                        //     case '4':
                        //         str += '<a href="left.html#dd" >我的订单</a>';
                        //         break;
                        //     case '5':
                        //         str += '<a href="javascript:;" onclick="window.history.go(-1)" >店铺</a>';
                        //         break;
                        //     default:
                        //         if (res.data[0].nodeName != '搜索') {
                        //             str += '<a href="javascript:;" onclick="window.history.go(-1)" >' + res.data[0].nodeName + '</a>';
                        //         } else {
                        //             str += '<a href="searchPage?key=&f=&b=&l=&m=&c=" >搜索</a>';
                        //         }
                        // }
                        switch (res.data[0].flag) {
                            case '0':
                                str += '<a href="left.html#sc" >收藏</a>';
                                break;
                            case '1':
                                str += '<a href="' + res.data[0].nodeUrl + '" >搜索</a>';
                                break;
                            case '2':
                                str += '<a href="' + res.data[0].nodeUrl + '">活动</a>';
                                break;
                            case '3':
                                str += '<a href="buyCar" >购物车</a>';
                                break;
                            case '4':
                                str += '<a href="left.html#dd" >我的订单</a>';
                                break;
                            case '5':
                                str += '<a href="' + res.data[0].nodeUrl + '" >店铺</a>';
                                break;
                            default:
                                if (res.data[0].nodeName != '搜索') {
                                    str += '<a href="' + res.data[0].nodeUrl + '">' + res.data[0].nodeName + '</a>';
                                } else {
                                    str += '<a href="searchPage?key=&f=&b=&l=&m=&c=" >搜索</a>';
                                }
                        }
                        str += '<img src="../../shopping/img/details/arrow-scopy 2.png" />';
                        str += '<a href="javascript: ;" >' + res.data[1].nodeName + '</a>';
                        $('.wz_dz').html(str);
                    }
                }
            })
        },
        error: function(request) { //请求出错
        }
    });

    // function keyva(jsonString, prodId) {
    //     $.ajax({
    //         type: "post", //提交请求的方式
    //         cache: true, //是否有缓存
    //         url: "/zlead/fplat/getGoods/" + prodId + "", //访问servlet的路径
    //         dataType: "json", //没有这个，将把后台放会的json解析成字符串
    //         data: jsonString,
    //         contentType: "application/json;",
    //         async: true, //是否异步
    //         success: function(res) {
    //             if (res.data.goodsDetailDto.ifShowStock == 1) {
    //                 $("#dt_glespas").html(res.data.goodsDetailDto.stock);
    //             } else if (res.data.goodsDetailDto.ifShowStock == 0) {
    //                 $("#dt_glespas").html("--");
    //             }
    //             $("#shoppid_de").html(res.data.goodsDetailDto.id)
    //         },
    //         error: function(request) { //请求出错
    //         }
    //     })
    // }

});

//加入购物车
function gocart(goodsId, count, buyType) {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/shopcart/addShoppingCart", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {
            goodsId: goodsId,
            count: count,
            buyType: buyType
        },
        async: true, //是否异步
        success: function(res) {
            if (res.code == 1) {
                $("#megou").show();
                $("body").css('overflow-y', 'hidden');
                shopCar(); //顶部购物车的接口
            } else {
                //库存不足和下架或其他信息
                $(".msg2").html(res.message);
                $(".meng2").show();
                $(".popupMsg").show();
                $("body").css('overflow-y', 'hidden');
            }
        },
        error: function(request) { //请求出错
        }
    })
}

//去登录
$(".gologin").click(function() {
    window.location.href = "Land.html"
});

//关闭去登录的弹窗
$("#tuLogin").click(function() {
    $("#goLogin").hide()
})