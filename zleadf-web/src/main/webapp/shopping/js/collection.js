$(function() {
    $("#tuicart").click(function() {
        $("#megou").hide();
        $("body").css('overflow-y', '');
        $(".meng").hide();
    });
    $("#fo").click(function() {
        window.location.href = "buyCar";
    });

    //弹窗关闭按钮
    $(".know").click(function() {
        $(".meng4").hide();
        $(".popupMsg4").hide();
    });
    //弹窗关闭按钮--X
    $(".close").click(function() {
        $(".meng4").hide();
        $(".popupMsg4").hide();
    });

    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/fplat/goodsCollect/1/5?t_=" + Math.random(), //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        async: true, //是否异步
        beforeSend: function() {
            $('.loading').show();
        },
        // complete: function() {
        //     $('.loading').hide();
        // },
        error: function(request) { //请求出错
        },
        success: function(res) {
            $('.loading').hide();
            if (res.code == 2) {
                $('.collection_no').show();
            } else {
                if (!res.data instanceof Object) {
                    alert('请联系后端修改接口：/zlead/fplat/goodsCollect/1/5 的数据')
                }
                $('.collection_no').hide();
                if (res.code == 1) {
                    var data = res.data;
                    var resulte = template("collections", data);
                    $("#allProduct").append(resulte);
                    var shouId;

                    // 跳转详情页面

                    $('.serProut_con').find('li').click(function() {
                        if ($(this).attr('ismarket') == 1) {
                            shouId = $(this).find('.porut_kuc').attr("data-id9");
                            $.ajax({
                                type: "get", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/query/goodsIsMarket", //访问servlet的路径
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                async: true, //是否异步
                                data: {
                                    goodsId: shouId,
                                    t_: Math.random()
                                },
                                success: function(res) {
                                    if (res.code == 1) {
                                        location.href = 'details.html?id=' + shouId
                                    }
                                }
                            });
                        }
                        return false;
                    });

                    //立即购买
                    $(".porut_kua").on("click", function() {
                        $('.popBox').hide(); // 关闭其他收藏信息里面的弹出框
                        shouId = $(this).attr("data-id9");
                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/query/goodsIsMarket", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            async: true, //是否异步
                            data: {
                                goodsId: shouId,
                                t_: Math.random()
                            },
                            success: function(res) {
                                if (res.code == 1) {
                                    $.ajax({
                                        type: "post", //提交请求的方式
                                        cache: true, //是否有缓存
                                        url: "/zlead/order/newConfirmOrder", //访问servlet的路径
                                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                        async: true, //是否异步
                                        data: {
                                            goodsId: shouId,
                                            buyNum: '1'
                                        },
                                        success: function(res) {
                                            if (res.code == 1) {
                                                var data = res.data;
                                                var datasss = JSON.stringify(data);
                                                localStorage.setItem("datas", datasss);
                                                window.location = "orderList.html";
                                            } else {
                                                $(".msg4").html(res.message);
                                                $(".meng4").show();
                                                $(".popupMsg4").show();
                                            }

                                        }
                                    });
                                } else {
                                    window.location.reload();
                                }
                            }
                        });
                        return false;
                    });

                    //加入购物车
                    $(".porut_kub").on("click", function() {
                        shouId = $(this).attr("data-id9");
                        $('.popBox').hide(); // 关闭其他收藏信息里面的弹出框

                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/query/goodsIsMarket", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            async: true, //是否异步
                            data: {
                                goodsId: shouId,
                                t_: Math.random()
                            },
                            success: function(res) {
                                if (res.code == 1) {
                                    $.ajax({
                                        type: "post", //提交请求的方式
                                        cache: true, //是否有缓存
                                        url: "/zlead/shopcart/addShoppingCart", //访问servlet的路径
                                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                        async: true, //是否异步
                                        data: {
                                            goodsId: shouId,
                                            count: '1',
                                            buyType: '0'
                                        },
                                        success: function(res) {
                                            if (res.code == 1) {
                                                $("#megou").show();
                                                $('body').css('overflow', 'hidden');
                                                shopCar(); //顶部购物车的接口
                                            } else {
                                                $(".msg4").html(res.message);
                                                $(".meng4").show();
                                                $(".popupMsg4").show();
                                            }
                                        }
                                    });
                                } else {
                                    window.location.reload();
                                }
                            }
                        });
                        return false;
                    });

                    //删除商品
                    $(".porut_kuc").on("click", function() {
                        var _this = $(this);
                        var shouId = $(this).attr("data-id9")

                        // 弹出确认框
                        $('.meng4').show();
                        $('.popupMsg5').show();

                        $('.popupMsg5').find('.close').click(function() {
                            // 弹窗隐藏
                            $('.meng4').hide();
                            $('.popupMsg5').hide();
                            // _this.parents('li').remove();

                            $.ajax({
                                type: "post", //提交请求的方式
                                cache: true, //是否有缓存
                                url: "/zlead/fplat/deleteCollect/" + shouId + "", //访问servlet的路径
                                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                                async: true, //是否异步
                                success: function(res) {
                                    if (res.code == 1) {
                                        window.location.reload();
                                    } else {
                                        $(".msg4").html(res.message);
                                        $(".meng4").show();
                                        $(".popupMsg4").show();
                                    }
                                },
                            });
                            
                            window.location.reload();
                            return true;
                        });
                        //取消弹窗 
                        $('.popupMsg5').find('.cancel').click(function() {
                            $('.meng4').hide();
                            $('.popupMsg5').hide();
                        });
                        // 阻止冒泡
                        $('.popupMsg5').click(function() {
                            return false;
                        });
                        return false;
                    });

                    //取消弹窗 
                    $(document).click(function() {
                        $('.meng4').hide();
                        $('.popupMsg5').hide();
                    });

                    // 批量管理-显示按钮
                    $('.all_control').click(function() {
                        if ($(this).hasClass('active')) {
                            $(this).removeClass('active').html('批量管理');
                            $(this).siblings('div').css('display', 'none').children('span').removeClass('active');
                            $('#allProduct').find('.det_all').removeClass('active').hide().css({
                                background: 'rgba(0,0,0,0.3)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/weis.png');
                            $('#allProduct').find('li').css({
                                    border: '1px solid rgba(253, 87, 0, 0)'
                                })
                                // 还原鼠标经过效果
                            $('#allProduct').find('li').hover(function() {
                                $(this).css({
                                    border: '1px solid rgba(253, 87, 0, 1)'
                                }).find('.hre').show().end().find('.porut_mo').show()
                            }, function() {
                                $(this).css({
                                    border: '1px solid rgba(253, 87, 0, 0)'
                                }).find('.hre').hide().end().find('.porut_mo').hide()
                            })

                        } else {
                            $(this).addClass('active').html('取消管理');
                            $(this).siblings('div').css('display', 'flex');
                            $('#allProduct').find('.det_all').show();
                            $('#allProduct').find('li').css({
                                border: '1px solid rgba(253, 87, 0, 1)'
                            });

                            // 取消鼠标经过的其他样式影响
                            $('#allProduct').find('li').hover(function() {
                                $(this).css({
                                    border: '1px solid rgba(253, 87, 0, 1)'
                                }).find('.hre').hide().end().find('.porut_mo').hide()
                            }, function() {
                                $(this).css({
                                    border: '1px solid rgba(253, 87, 0, 1)'
                                }).find('.hre').hide()
                            })
                        }
                    });
                    // 批量管理-全选按钮        
                    $('.ct_lt').children('div').children('span').click(function() {
                        if ($(this).hasClass('active')) {
                            $(this).removeClass('active');
                            $('#allProduct').find('.det_all').removeClass('active').css({
                                background: 'rgba(0,0,0,0.3)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/weis.png')

                        } else {
                            $(this).addClass('active');
                            $('#allProduct').find('.det_all').addClass('active').css({
                                background: 'rgba(0,0,0,0.6)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/gx.png')
                        }
                    });

                    // 批量管理-全选按钮--俩字
                    $('.ct_lt').children('div').children('b').click(function() {
                        if ($(this).hasClass('active')) {
                            $(this).parent().find("span").removeClass('active');
                            $(this).removeClass('active');
                            $('#allProduct').find('.det_all').removeClass('active').css({
                                background: 'rgba(0,0,0,0.3)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/weis.png')
                        } else {
                            $(this).parent().find("span").addClass('active');
                            $(this).addClass('active');
                            $('#allProduct').find('.det_all').addClass('active').css({
                                background: 'rgba(0,0,0,0.6)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/gx.png')
                        }
                    });
                    // 取消鼠标经过的其他样式影响
                    // $('#allProduct').find('.det_all').hover(function() {
                    //     $(this).siblings('.hre').hide().end().siblings('.porut_img').find('.porut_mo').hide()
                    // })

                    $('#allProduct').find('.det_all').click(function() {
                        if ($(this).hasClass('active')) {
                            $(this).removeClass('active').css({
                                background: 'rgba(0,0,0,0.3)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/weis.png')
                            $('.ct_lt').children('div').children('span').removeClass('active')
                        } else {
                            $(this).addClass('active').css({
                                background: 'rgba(0,0,0,0.6)'
                            }).find('img').attr('src', '../../shopping/img/searchPage/gx.png')

                            var flag = true;
                            for (var i = 0; i < $('#allProduct').find('.det_all').length; i++) {
                                if (!$('#allProduct').find('.det_all').eq(i).hasClass('active')) {
                                    flag = false;
                                }
                            }
                            if (flag) {
                                $('.ct_lt').children('div').children('span').addClass('active')
                            }
                        }
                        return false;
                    })

                    // 批量管理-删除      

                }
            }
        }
    });


    $("#gpl").click(function() {
        $(this).hide();
        $(".gl").show();
        $(".serProut_con").hide();
        $(".serProut_conb").show();
        $(".porut_mob").show()
    });
    $(".quo").click(function() {
        $(".gl").hide();
        $("#gpl").show();
        $(".serProut_con").show();
        $(".serProut_conb").hide();
        $(".porut_mob").hide();
        $(".com").removeClass("biank");
        $(".porut_mobgimg").hide();
        $(".quxn img").hide()
    });
    $(".com").click(function() {
        $(this).toggleClass("biank")
    });
    $(".yiyan").click(function() {
        window.location.href = "buyCar.html"
    });
    $(".com").click(function() {
        $(this).toggleClass("biank")
    });
    $(".quxaa").click(function() {
        $(".com").toggleClass("biank");
        $(".porut_mobgimg").toggle();
        $(".quxn img").toggle();
    });
    $(".porut_mob").click(function() {
        $(this).find(".porut_mobgimg").toggle();
    });
    $("#gpl").click(function() {
        $(this).hide();
        $(".gl").show();
        $(".serProut_con").hide();
        $(".serProut_conb").show();
        $(".porut_mob").show()
    })

});