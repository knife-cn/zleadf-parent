$(function () {
    init();
})

var pageNum = 0;
var size = 5;

function init() {
    var html = '';
    $('.list-2').dropload({
        scrollArea: window,
        loadDownFn: function (me) {
            pageNum++;
            // 拼接HTML
            $.ajax({
                //url: '../ajeasy/mgrGoods/goodsList',
                url: '../zlead/tgoods/goodsList',
                type: 'post',
                data: {
                    /*"pageNum":pageNum,
                    "size":size*/
                    "page": pageNum,
                    "prodType": 0
                },
                success: function (res) {
                    console.log(res)
                    if (res.code == 1) {
                        if (res.data.length > 0) {
                            for (var i = 0; i < res.data.length; i++) {

                                html += '<ul>';
                                html += '<li style="width:15%">' + res.data[i].fullName + '</li>';
                                if (res.data[i].prodType == 0) {
                                    html += '<li style="width:11%">普通</li>';
                                } else if (res.data[i].prodType == 1) {
                                    html += '<li style="width:11%">积分类</li>';
                                }
                                html += '<li style="width:13%">' + res.data[i].marketPrice + '</li>';
                                if (res.data[i].isMarket == 1) {
                                    html += '<li style="width:11%">上架</li>';
                                } else if (res.data[i].isMarket == 0) {
                                    html += '<li style="width:11%">未上架</li>';
                                }
                                html += '<li style="width:13%">' + res.data[i].stock + '</li>';
                                html += '<li style="width:13%">' + res.data[i].salesNum + '</li>';
                                html += '<li style="width:22%">';
                                html += '<span class="liAdit-1" onclick="updateGoods(' + res.data[i].id + ')">修改</span>';
                                html += '<span class="liDet"  onclick="deleteGoods(' + res.data[i].id + ')">删除</span>';
                                html += '</li>';
                                html += '</ul>';
                            }
                        } else {
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                        }
                        // 为了测试，延迟1秒加载
                        setTimeout(function () {
                            // 插入数据到页面，放到最后面
                            $("#list").children().remove();
                            $('#list').append(html);
                            // 每次数据插入，必须重置
                            me.resetload();
                        }, 1000);
                    } else if (res.code == 2) {
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                        $('#list').append("<p style='text-align:center;font-size:0.26rem;line-height:0.5rem;'>暂无更多商品信息</p>");
                    }

                },
                error: function (xhr, type) {
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            })
        }
    })
}

function gotoGoodsUpload() {
    window.location.href = "goodsAdd.jsp";
}

function gotoBack() {
    window.location.href = 'agentAccount.jsp';
}

/**
 * 删除商品
 */
function deleteGoods(id) {
    console.log(id);
    $.ajax({
        url: '../zlead/tgoodsBg/delete',
        type: 'post',
        data: {"id": id},
        success: function (res) {
            console.log(res)
            if (res.code == 1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.location.href = 'goodsQuery.jsp';
                }, 1500);
            } else {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function () {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        }
    })
}

/**
 * 商品修改
 * @param goods
 */
function updateGoods(id) {
    console.log(id);
    window.location.href = 'goodsUpdate.jsp?id=' + id;

}
