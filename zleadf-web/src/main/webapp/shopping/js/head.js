$(function() {
        $("#caigo").click(function() {
            window.location.href = "buyCar";
        });
        $(".logo").click(function() {
            window.location.href = "index.html"
        });

        $('#cat').click(function() {
            if ($('.cart_xi').is(':hidden')) {
                $('.cart_xi').show();
                $('#xt').show();
                $('#st').hide();
            } else {
                $('.cart_xi').hide();
                $('#xt').hide();
                $('#st').show();
            }
        });

        $('#gen_b').click(function() { //点击a标签
            if ($('.info_xione').is(':hidden')) { //如果当前隐藏
                $('.info_xione').show(); //那么就显示div
            } else { //否则
                $('.info_xione').hide(); //就隐藏div
            }
        });
        $('#yh').click(function() { //点击a标签
            if ($('.yxi').is(':hidden')) { //如果当前隐藏
                $('.yxi').show(); //那么就显示div
                $('#yho').hide();
                $('#yht').show();
            } else { //否则
                $('.yxi').hide(); //就隐藏div
                $('#yho').show();
                $('#yht').hide();
            }
            return false;
        });
        $(document).click(function() {
            $('.yxi').hide(); //就隐藏div
            $('#yho').show();
            $('#yht').hide();
        });

        $('#ino').click(function() { //点击a标签
            window.location.href = "xiaoxi.html";
            // $('.cart_xi').hide();//就隐藏div
        });

        $('#cat').click(function() { //点击a标签
            $('.info_xi').hide(); //就隐藏div
            $('.info_xione').hide();
        });

        // 去购物车
        $("#gocart").click(function() {
            window.location = "buyCar.html"
        });
        
        // 去用户中心
        $("#goPerson").click(function() {
            if(window.localStorage.getItem("active5")){
                window.localStorage.removeItem("active5");
            }
            window.location = "left.html";
        });
        //  去注册
        $("#free_register").click(function() {
            window.location.href = "index?login=two"
            // $(".meng").show();
            // $(".zhongtwo").show();
            // $(".zhong").hide();
        });
        // 去登录
        $("#load_self").click(function() {
            window.location.href = "index?login=one"
            // $(".meng").show();
            // $(".zhong").show();
            // $(".zhongtwo").hide();
        });

        hotserch();

        function hotserch() {
            $.ajax({
                type: "get", //提交请求的方式
                cache: true, //是否有缓存
                url: "/hotWords/find?t_" + Math.random(), //访问servlet的路径
                dataType: "json", //没有这个，将把后台放会的json解析成字符串
                async: true, //是否异步
                error: function(request) { //请求出错
                },
                success: function(data) {
                    var str = "";
                    if (data.code == 1) {

                        for (var i = 0; i < data.data.length; i++) {
                            str += '<li>' + data.data[i] + '</li>';
                        }

                        $("#search_hot_ul").html(str);

                        $("#search_hot_ul li").click(function() {
                            window.location.href = "searchPage.html?key=" + encodeURIComponent($(this).html()) + "&b=&l=&m=&c=";
                        });
                    }

                }
            })
        }
        //搜索输入框,输入就调用接口查询
        $("#resou").keyup(function() {
            var resouVal = $("#resou").val();
            if ($("#resou").val() != "") {
                //输入中文、数字、英文、下划线
                var lastStr = resouVal.substr(resouVal.length - 1,1);
                if (!lastStr.match( /^[\u4E00-\u9FA5a-zA-Z0-9_]$/)) {
                    $("#resou").val(resouVal.substr(0,resouVal.length-1))
                } else {
                    $("#resou").val(resouVal)
                }

                var data_go = {};
                if (location.pathname.indexOf("searchPage") > -1) { //搜索页面
                    data_go = {
                        key: $("#resou").val(),
                        factId: GetRequest().f
                    }
                } else if (location.pathname.indexOf("storeDetails") > -1) { //店铺页面
                    data_go = {
                        key: $("#resou").val(),
                        factId: GetRequest().factId
                    }
                } else {
                    data_go = {
                        key: $("#resou").val(),
                    }
                }
                $.ajax({
                    type: "GET", //提交请求的方式
                    cache: true, //是否有缓存
                    data: data_go,
                    url: "/zlead/fplat/queryNameKey", //访问servlet的路径
                    dataType: "json", //没有这个，将把后台放会的json解析成字符串
                    success: function(res) {
                        if (res.code == 1) {
                            if (res.data.length > 0) {
                                var str = "";
                                var arr = res.data;
                                for (var i = 0; i < arr.length; i++) {
                                    str += "<li>";
                                    str += arr[i];
                                    str += "</li>";
                                }
                                $(".proCon").html(str);
                                $(".pro").show();
                            } else {
                                $(".pro").hide();
                            }
                        }
                    }
                })

            }

        });

        // $("#resou").blur(function(){
        //     $(".pro").hide();
        //     return false;
        // });

        $(document).on("click", ".proCon li", function() {
            $(".pro").hide();
            window.location.href = "searchPage.html?key=" + encodeURIComponent($(this).html()) + "&b=&l=&m=&c=";
        });


        $("#search_icon").click(function() {
            var reshou = $("#resou").val();

            if (reshou == "" || reshou == undefined || reshou == null) {
                alert("请输入热搜词！");
            } else {
                $("#resou").val(''); // 取消跳转前页面的搜索框内容，这样返回到本页的时候，搜索框值才能为空
                window.location.href = "searchPage.html?key=" + encodeURIComponent(reshou) + "&b=&l=&m=&c=";
            }
        });

        // 店铺页面--只能搜索出来店铺页面的数据
        $("#search_icon_sd").click(function() {
            var reshou = $("#resou").val();
            var sd_factId = window.localStorage.getItem("sd_factId")

            if (reshou == "" || reshou == undefined || reshou == null) {
                alert("请输入热搜词----！");
            } else {
                window.location.href = "searchPage.html?key=" + encodeURIComponent(reshou) + "&b=&l=&m=&c=" + "&f=" + sd_factId;
            }
        });

        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/member/memberInfo", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            success: function(res) {
                if (res.code == 1) {

                } else {
                    $(".banner_atwo").show();
                    $(".banner_aone").css("width", "800px")
                    $(".con_aa").show();
                    $(".con_a").hide();
                    $(".con_bbul li").show();
                    $(".con_bul li").hide();
                    $("#gongc").hide();
                    $("#shaixu").css("width", "200px");
                }

            }
        })

    }) //


//获取url？号后面的参数
function GetRequest() {
    var url = window.location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        };
    };
    return theRequest;
};