$(function () {
    getData()

    function getQueryString(name, needdecoed) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var lh = window.location.search;
        if (needdecoed) {
            lh = decodeURI(window.location.search)
        }
        var r = lh.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function getData() {
        var api = "";
        var shopId = getQueryString("shopId");

        $.ajax({
            type: "GET",
            dataType: 'json',
            url: api + "/zlead/shopmgr/shopGoodsDetail?goodsId=" + shopId,
            // url: api + "/zlead/fplat/goodsDetail?goodsId=" + shopId,

            success: function (res) {
                var data = res.data;
                console.log(data);
                if (data) {

                    if (data.imgs) {
                        var imgsString = data.imgs;
                        data.imgArr = imgsString.split(',');
                        console.log('imgs', data.imgArr.length);
                    }


                    var templateResult = template("modelGoodsDetails", data);
                    document.getElementById("goodsDetails").innerHTML = templateResult;

                    var mySwiper = new Swiper('#goodDetailSwiper', {
                        loop: true,
                        pagination: '.goodDetail-pagination',
                    })
                    $(".infoNavUl li").click(function () {
                        $(this).addClass('active').siblings().removeClass('active');
                        var $type = $(this).data('type');
                        $('.infoContUl li').each(function () {
                            if ($(this).data('type') == $type) {
                                $("html, body").animate({
                                    scrollTop: $(this).offset().top - 46 / 50 + "rem"
                                }, 400);
                            }

                        })
                    })

                    $(window).scroll(function () {
                        if ($(window).scrollTop() >= $('.goodsInfoPart').offset().top) {
                            $('.infoNavUlDv').addClass('fixed')
                        } else {
                            $('.infoNavUlDv').removeClass('fixed')
                        }
                    });

                    //加入购物车面板显示隐藏
                    $('.chooseTypePart').click(function () {
                        $(".addShopCarPart").addClass("active")
                    })
                    $(".closeBtn").click(function () {
                        $(".addShopCarPart").removeClass("active")
                    })
                    $('#minusBtn').click(function () {
                        var val = Number($('#orderNum').val());
                        $('#addBtn').addClass('active')

                        if (val == 1) {
                            return false;
                        } else {
                            $('#orderNum').val(val - 1);
                            $(this).addClass('active');
                            if (Number($('#orderNum').val()) == 1) {
                                $(this).removeClass('active');
                            }
                        }
                    })
                    $('#addBtn').click(function () {
                        var val = Number($('#orderNum').val());
                        var goodsStoreNum = Number($('#goodsStoreNum').text());
                        $('#minusBtn').addClass('active')
                        if (val == goodsStoreNum) {
                            return false;
                        } else {
                            $('#orderNum').val(val + 1);
                            $(this).addClass('active');

                            if (Number($('#orderNum').val()) == goodsStoreNum) {
                                $(this).removeClass('active');

                            }
                        }
                    })


                    //立即上架
                    $("#btnshang").click(function(){
                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/zlead/shopmgr/setMarket", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            data: {
                                goodsId:data.id,
                                isMarket:1,
                                stocknum:1
                            }, //把内容序列化
                            async: true, //是否异步
                            error: function(request) { //请求出错
                                alert("出错");
                            },
                            success: function(res) { //获得返回值
                                console.log(res.data);
                                if(res.code == 1) {
                                    alert("上架成功");
                                    window.location.reload();
                                }
                                else {
                                    alert("上架失败");
                                }

                            }
                        })
                    })

                    //立即下架
                    $("#btnxia").click(function(){

                        $.ajax({
                            type: "get", //提交请求的方式
                            cache: true, //是否有缓存
                            url: "/zlead/shopmgr/setMarket", //访问servlet的路径
                            dataType: "json", //没有这个，将把后台放会的json解析成字符串
                            data: {
                                goodsId:data.id,
                                isMarket:0,
                                stocknum:1
                            }, //把内容序列化
                            async: true, //是否异步
                            error: function(request) { //请求出错
                                alert("出错");
                            },
                            success: function(res) { //获得返回值
                                console.log(res.data);
                                if(res.code == 1) {
                                    alert("下架成功");
                                    window.location.reload();
                                }
                                else {
                                    alert("下架失败");
                                }

                            }
                        })
                    })

                }else {
                    console.log('数据为空')
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
})