$(function() {

    var actId = getParam("actId")

    $.ajax({
        type: "get", //提交请求的方式
        cache: true, //是否有缓存
        url: "/zlead/attr/queryDetail/" + actId + "?_time=" + new Date().getTime(), //访问servlet的路径
        dataType: "json", //没有这个，将把后台放会的json解析成字符串
        data: {

        }, //把内容序列化
        async: true, //是否异步
        error: function(request) { //请求出错
        },
        success: function(res) { //获得返回值
            if (res.code == 1) {
                var str = "";
                str += '<p class="houd_conp">' + res.data.contName + '</p>';
                str += '<span class = "houd_conspan" >' + res.data.contTitle + '</span>';
                // str += '<div class = "houd_img">';
                // str += '<img style="width: 1000px;height: 400px" src="' + res.data.atrPic + '"/>';
                // str += '</div>';
                str += '<p class = "acsui" > 活动说明 </p>';
                // str += '<span class = "scspan">' + matchReg(res.data.staticCont) + '</span>';
                str += '<span class = "scspan">' + res.data.staticCont + '</span>';
                str += '<p class="acsui_time" >活动时间：<span>' + res.data.attrDate + '</span></p>';

                $("#houd_con").html(str);

                // 限制图片大小
                $('.scspan').find('img').css({
                    maxWidth: 1100,
                    height: 'auto'
                })
                jiazaip(page, 10, actId);
            } else if (res.code == 2) {
                $('.sp').hide();
                $('.ta3').hide();

                $('.meng5').show();
                $('.popupMsg6').show();
                $('.msg6').html(res.message);
                $('.dt_top').css('marginBottom', $('body').height() - 233);
                $('.know6').click(function() {
                    window.history.go(-1);
                })

                $('.close6').click(function() {
                    window.history.go(-1);
                })
            }

        }
    })


})


function matchReg(str) {
    var reg = /<\/?.+?\/?>/g;
    return str.replace(reg, '');
}