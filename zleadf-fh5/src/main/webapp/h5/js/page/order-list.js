mui.ready(function () {
    var api = '';
    var n = 0;
    var modelData = {
        "result": []
    }
    // if (!document.getElementById('searchInput').value) {
    //     localStorage.setItem('searchINputKey','');
    // }
    var _sn='';
    document.getElementById('searchInput').value='';

    // var _sn = localStorage.searchINputKey?localStorage.searchINputKey:'';
    // document.getElementById('searchInput').value=localStorage.searchINputKey;
    /**
     * pageNum  页码
     * size  每次加载条数
     * sn 订单号
     * */
    function search(key) {
        // mui.ajax(api + "/zlead/shopmgr/shopOrderList?pageNum=" + n + "&size=10&sn=" + _sn, {
        mui.ajax(api + "/zlead/shopmgr/shopOrderList", {
            type: "post",
            data: {
                pageNum: 1,
                size: 10,
                sn: key
            },
            dataType: "json",
            success: function (res) {
                console.log(res.message)
                if (res.message=="未登录") {
                    //未登陆
                    window.location.href="login.html"
                }else{
                    //已登陆
                    var resData = res.data;

                    if (res.data && resData.result.length > 0) {
                        // var modelData = resData;
                        modelData.result = resData.result;
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh(false);
                        var modelHtml = template("modelOrderList", modelData);
                        document.getElementById("orderListBox").innerHTML = modelHtml;
                    } else {
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);

                    }
                }

            },
            error: function (xhr, type, errorThrown) {

            }
        });
    }

    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            // down: {
            //     style: 'circle',
            //     callback: pulldownRefresh
            // },
            up: {
                auto: true,
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });

    // var count = 0;

    function pullupRefresh() {
        n += 1;
        // mui.ajax(api + "/zlead/shopmgr/shopOrderList?pageNum=" + n + "&size=10&sn=" + _sn, {
        mui.ajax(api + "/zlead/shopmgr/shopOrderList", {
            type: "post",
            data: {
                pageNum: n,
                size: 10
                // sn: _sn
            },
            dataType: "json",
            success: function (res) {
                console.log(res.message)
                if (res.message=="未登录") {
                    //未登陆
                    window.location.href="login.html"
                }else{
                    //已登陆

                var resData = res.data;

                    if (res.data && resData.result.length > 0) {
                        // var modelData = resData;
                        modelData.result = modelData.result.concat(resData.result);
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh(false);
                        var modelHtml = template("modelOrderList", modelData);
                        document.getElementById("orderListBox").innerHTML = modelHtml;
                    } else {
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);

                    }
                }

            },
            error: function (xhr, type, errorThrown) {

            }
        });
    }
    document.getElementById('searchInput').addEventListener('click', function (event) {
        document.getElementById('searchInput').value ='';

    })
    document.getElementById('searchInput').addEventListener('keyup', function (event) {
        // document.getElementById('searchInput').value ='';

        if (event.keyCode == 13) {
            var _input = document.getElementById('searchInput').value;
            if (_input) {
                // var arrUrl = window.location.href.split("?");
                // var para = arrUrl[0];
                localStorage.setItem('searchINputKey',_input)
                console.log(localStorage.searchINputKey);
            }else {
                localStorage.setItem('searchINputKey','')
            }
            // window.location.href = para + "?pageNum=1&size=10&sn=" + localStorage.searchINputKey;
            // window.location.reload();
            search(_input)


        }
    })

    /**
     * 下拉刷新具体业务实现
     */
    function pulldownRefresh() {

    }

    // mui('#orderListBox').on('tap', 'li', function () {
    //     console.log(this.getAttribute('data-id'))
    //     var id = this.getAttribute('data-id');
    //     mui.openWindow({
    //         url: 'order-detail.html?orderId=' + id
    //     });
    // });


    function deliverGoods(id) {
        mui.ajax(api + "/zlead/shopmgr/sendGoods", {
            type: "post",
            data: {
                orderId: id
            },
            dataType: "json",
            success: function (res) {
                console.log(res.message)
                alert(res.message);
                window.location.reload();
            },
            error: function (xhr, type, errorThrown) {

            }
        });
    }
    //点击每个列表跳详情页
    mui('#orderListBox').on('tap','.listItem', function () {
        var _id =this.parentNode.getAttribute('data-id');
        console.log(_id)
        localStorage.setItem('orderListId',_id);
        window.location.href="order-detail.html";
    });
    //当订单状态为代发货时点击发货按钮
    mui('#orderListBox').on('tap', '.fahuoBtn', function () {
        var _id =this.parentNode.parentNode.getAttribute('data-id');
        console.log(this.parentNode.parentNode.getAttribute('data-id'))
        deliverGoods(_id);
    });
})


