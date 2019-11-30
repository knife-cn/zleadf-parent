$(function() {
    var arrPhone = [];

    $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/memaddr/getAllAddress", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            async: true, //是否异步
            success: function(res) {
                if (res.code == 1) {
                    var datas = res.data;
                    var addressList = template("tel_address5", res);
                    $("#addressList").append(addressList);
                    for(var i=0;i<datas.length;i++){
                        arrPhone.push(datas[i].phone);
                    }
                } else if (res.code == 2) {
                    var str = '<div class="tian"><img src="../../shopping/img/lhpimg/Fill_103.png" /><div>添加新地址</div></div>';
                    $("#addressList").html(str);
                }
            },
            error: function(request) { //请求出错
            }
        });
        //删除地址信息
    $("#addressList").on("click", "#delete_address", function() {
        var id = $(this).attr("data-id")
        window.location.href = "Receiving_address3.html?id=" + id
    });

    //编辑修改
    $("#addressList").on("click", ".edit_address", function() {
        var list = $(this).attr("data-list")
        // 新增参数：打开编辑页显示当前是否为默认地址：1是默认地址，0是非默认
        if ($(this).parent().hasClass('mo1')) {
            var type = 1;
        } else {
            var type = 0;
        }
        localStorage.setItem("lists", list);
        localStorage.setItem("type", type);
        window.location.href = "editDetail.html"
    });

    // 设置默认地址
    $("#addressList").on("click", ".set_address", function() {
        var list = JSON.parse($(this).attr("data-list"))
        $.ajax({
            type: "post", //提交请求的方式
            cache: true, //是否有缓存
            url: "/zlead/memaddr/setDefaultAddress", //访问servlet的路径
            dataType: "json", //没有这个，将把后台放会的json解析成字符串
            data: {
                memberName: list.memberName,
                phone: list.phone,
                provinceName: list.provinceName,
                cityName: list.cityName,
                countyName: list.countyName,
                detailAddress: list.detailAddress,
                isDefault: '1',
                type: '2',
                addressId: list.id
            },
            success: function(res) {
                if (res.code == 1) {
                    location.reload()
                }
            }
        })
    });

    //新增地址
    $("#addressList").on("click", ".tian", function() {
        //防止新加手机号与已存在手机号相同
        window.location.href = "Receiving_address2.html"
    });

    $(".zhi").click(function() {
        $(".tiao1").addClass("tiao");
        $(".tiao1").removeClass("tiao1");
        $(".mo").addClass("mo1");
        $(".mo").removeClass("mo");
        $(this).siblings().children(".tiao").addClass("tiao1").removeClass("tiao");
        $(this).siblings().children(".mo1").addClass("mo").removeClass("mo1");
    });
    $(".towdao li").click(function() {
        $("li").removeClass("yan");
        $(this).addClass("yan");
        var name = $(this).html();
        $("#span").html(name);
    });

});