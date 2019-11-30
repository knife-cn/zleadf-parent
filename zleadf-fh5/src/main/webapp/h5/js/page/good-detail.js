$(function () {
    var _host = '';
    var _data = {
        "orderId": common.getQueryString("orderId")
    }

    $.ajax({
        url: _host+"/zlead/order/getOrderInfodetails",
        type: "post",
        data: _data,
        dataType: "json",
        success: function (res) {
            var arr = res.data;
            if (res.data.length>0) {
                var modelData = arr.orderEntity[0];
                // modelData.createDate = getdate(modelData.createDate);
                modelData.orderGoodsList = arr.orderGoods;
                var  modelHtml = template("modelGoodDetail",modelData);
                document.getElementById("goodDetail").innerHTML = modelHtml;
            }else {
                console.log("数据为空")
            }
        }
    });

})