
(function() {
    // /zlead/shopmgr/shopOrderList
    // 商品数据
    var url_1 = '/zlead/shopmgr/shopOrderList';
    var data_1 = {
        pageNum: '1',
        status: '1',
        size: '2'
    }
    var ajax_1 = $.ajax({
        type: 'get',
        url: url_1,
        data: data_1,
        success: function(res) {
            console.log('首页商品列表数据', res)
            var num = 2; // 显示多少条，后期可修改
            var str = '';
            if (res.data.result && res.data.result.length > 0) {

                // 渲染页面：目前
                for (var i = 0; i < num && i < res.data.result.length; i++) {

                    // var status; 判断状态  目前不需要
                    // switch (res.data.OrderEntity[i].OrderGoods.status) {
                    //     case 0:
                    //         status = '待付款';
                    //         break;
                    //     case 1:
                    //         status = '待发货';
                    //         break;
                    //     case 2:
                    //         status = '已发货';
                    //         break;
                    //     case 3:
                    //         status = '已完成';
                    //         break;
                    //     default:
                    //         status = '已完成';
                    // }


                    str += `
                        <li>
                            <a href="javascript:;" target="_blank">
                                <div class="deliverStatusDv">
                                    <img src="../../../h5/images/pic_profile_photo@2x.png" alt="">${res.data.result[i].consignee} <span class="deliverStatus">待发货</span>
                                </div>
                                <div class="goodsImg clearfix">
                                    <div class="imgListDV">
                                        <ul class="clearfix">
                                            <li>
                                                <img src="${res.data.result[i].OrderGoods.goodsImg}" alt="">
                                            </li>

                                        </ul>
                                    </div>
                                    <div class="totalNum">
                                        共<span>${11}</span>件商品
                                    </div>

                                </div>
                                <div class="priceDv">
                                    <span>不含运费</span>
                                    <span>总计 ${res.data.result[i].goodsAmount}元</span>
                                </div>
                            </a>
                        </li>

                        `
                }
                $('.todoList').html(str);
            }else{
                $('.todoList').html("暂无");
            }
        },
        error: function(request) {},
        dataType: 'json'
    })



    // /zlead/shopmgr/orderTotalAmount 订单总金额
    var url_2 = '/zlead/shopmgr/orderTotalAmount';
    var ajax_2 = $.ajax({
        type: 'get',
        url: url_2,
        success: function(res) {
            console.log('订单总金额', res)
            if (res.data && res.data.totalAmount) {
                $('.orderNumDv').find('p').eq(1).html('<span>￥</span>' + res.data.totalAmount)
            }
        },
        error: function(request) {},
        dataType: 'json'
    })

    // /zlead/shopmgr/orderTotalCount 订单总数
    var url_3 = '/zlead/shopmgr/orderTotalCount';
    var ajax_3 = $.ajax({
        type: 'get',
        url: url_3,
        success: function(res) {
            console.log('订单总数', res)
            if (res.data && res.data.orderTotalCount) {
                $('.orderNumDv').find('p').eq(3).html(res.data.orderTotalCount)
            }
        },
        error: function(request) {},
        dataType: 'json'
    })


    // // 当多个ajax都执行完：第一个success，第二个error
    // $.when(ajax_1, ajax_2).then(function() {

    // })

})();


/*

待办事项：静态页面结构

<li>
    <a href="javascript:;" target="_blank">
        <div class="deliverStatusDv">
            <img src="../../../h5/images/pic_profile_photo@2x.png" alt="">肖生克 <span class="deliverStatus">待发货</span>
        </div>
        <div class="goodsImg clearfix">
            <div class="imgListDV">
                <ul class="clearfix">
                    <li>
                        <img src="../../../h5/images/home_ic_more_goods@2x.png" alt="">
                    </li>
                    <li>
                        <img src="../../../h5/images/home_ic_more_goods@2x.png" alt="">
                    </li>
                    <li>
                        <img src="../../../h5/images/home_ic_more_goods@2x.png" alt="">
                    </li>
                </ul>
            </div>
            <div class="totalNum">
                共<span>11</span>件商品
            </div>

        </div>
        <div class="priceDv">
            <span>不含运费</span>
            <span>总计 1.200.00元</span>
        </div>
    </a>
</li>



*/