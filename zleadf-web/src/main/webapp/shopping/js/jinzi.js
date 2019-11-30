$(function() {
    if (location.href.indexOf('orderList') == -1) {
        //活动页详情
        if (window.localStorage.getItem("actIdN")) {
            window.localStorage.removeItem("actIdN")
        }
    }

    if (location.href.indexOf('left.html#dd') > -1 || location.href.indexOf('left.html') > -1) {

    }else{
        if (window.localStorage.getItem("active5")) {
            window.localStorage.removeItem("active5")
        }
    }

    $.ajax({
        type: "post", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/member/memberInfo", //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        success: function(res) {

            if (res.code != 1) {

                window.location.href = "Receiving_address5";
                // window.location.href = "index.html";

            }
        }
    })

    // if (!sessionStorage.getItem("memberId")) {
    //     window.location.href = "Receiving_address5";
    // }
})