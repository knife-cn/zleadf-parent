$(function() {
    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/member/memberInfo", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        success: function(res) {
            if (res.code == 1) {
                var data1 = res.data.member.phone;
                if (data1 != null && data1 != undefined) {
                    var nphone = data1.substr(0, 3) + '****' + data1.substring(7, 11);
                    var list = {
                        number: nphone
                    }
                    var html = template("users", list)
                    $("#yh").append(html)
                } else {
                    $("#yh").append("")
                }
                var data2;
                if (res.data.member.headImg == null || res.data.member.headImg == "") {
                    data2 = "../../shopping/img/index/ic_morentouxiang.png"
                } else {
                    data2 = res.data.member.headImg
                }
                var list2 = {
                    number2: data2
                }
                var html2 = template("touImg", list2)
                $(".xk_tou").html(html2)

                var html3 = template("haomas", list)
                $(".xk_phone").html(html3)


                //    退出

                $(".xk_phone").on("click", "#out", function() {
                    $.ajax({
                        type: "post", //提交请求的方式
                        cache: true, //是否有缓存
                        url: "/zlead/member/logout?account=" + data1 + "", //访问servlet的路径
                        data: {

                        },
                        dataType: "json", //没有这个，将把后台放会的json解析成字符串
                        async: true, //是否异步
                        error: function(request) { //请求出错
                        },
                        success: function(data) {

                            if (data.code == 1) {
                                // window.location.reload();
                                location.href = 'index.html'
                            }

                        }
                    })

                })
            }
        }



    })

    $("#goHome").click(function() {
        window.location.href = "index"
    })


})