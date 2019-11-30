$(function () {
    // console.log{}
    
    var _data = {
        // "orderId": common.getQueryString("orderId"),
        "sn" : localStorage.orderListId
    };
    $.ajax({
        url: "/zlead/shopmgr/orderInfo",
        type: "GET",
        data: {
            pageNum:1,
            size:10,
            orderSn:_data.sn,
        },
        dataType: "json",
        success: function (res) {
            var arr = res.data.result;
            console.log(arr);
            if (arr.length>0) {
                var modelData = arr[0];
                modelData.createDate = common.getdate(modelData.createDate);
                modelData.orderGoodsList = arr.orderGoods;
                //
                var  modelHtml = template("modelOrderDetails",modelData);
                document.getElementById("orderDetails").innerHTML = modelHtml;
            }else{
                console.log("数据为空")
            }
        }
    });

})