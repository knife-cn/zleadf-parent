$(function() {
    orderList();
})

//订单列表
function orderList() {
    var status=appSupport.cm.queryString("status");
    if(status==2){
        var id=appSupport.cm.queryString("id");
        var html='';
        $.ajax({
            url: '../zlead/torder/cartComfirmOrder',
            type: 'post',
            data: {
                "id":id
            },
            success: function(res) {
                if(res.code==1){
                    html+='<div class="orderTitle"><img src="img/icon-back.png" class="fl go-back-1" onclick="goback()"><span>确认订单</span></div>';
                    for(i = 0; i < res.data.length; i++){
                        if(res.data[i].defaultAddress!=null){
                            html+='<div class="address">'
                                + '<p><img src="img/con-ren.png" alt=""><span class="memberName">'+res.data[i].defaultAddress.memberName+'</span></p>'
                            if(res.data[i].defaultAddress.address.length>23){
                                html+='<p><img src="img/icon-dingweia.png" alt=""><span class="detailAddress">'+res.data[i].defaultAddress.address.substring(0, 23)+"..."+'</span></p>';
                            }else{
                                html+='<p><img src="img/icon-dingweia.png" alt=""><span class="detailAddress">'+res.data[i].defaultAddress.address+'</span></p>';
                            }
                            html+='<p><img src="img/icon-dianhua.png" alt=""><span class="phone">'+res.data[i].defaultAddress.phone+'</span></p>'
                            html+='<input class="addressId" type="hidden" value="'+res.data[i].defaultAddress.id+'">'
                                + '</div>';
                        }else{
                            html+='<div class="nullAddress" >'
                                + '<img src="img/img-tjdz.png" alt="">'
                                + '<p>您还没有添加地址哦</p>'
                                + '<div class="add" onclick="add()">'
                                + '<img src="img/btn-tjdzi.png" alt="">'
                                + '</div>'
                                + '</div>';
                        }

                        html+='<div class="order">'
                            + '<div class="order-1">'
                            + '<img src="img/img-quan.png" alt="">'
                            + '<span >'+res.data[i].shopName+'</span>'
                        html+='</div>';
                        for(j = 0; j < res.data[i].goodsList.length; j++) {
                            html+='<div class="order-2">'
                                + '<div class="order-2-img">'
                                + '<img src="http://116.62.124.171/group1/' + res.data[i].goodsList[j].goodsImg + '" alt="">'
                                + '</div>'
                                + '<div class="order-2-info">'
                                + '<p class="order-2-info1 mb" id="' + res.data[i].goodsList[j].goodsId + '">' + res.data[i].goodsList[j].goodsName + '</p>'
                                + '<p class="order-2-info2 mb">'
                                + '<img src="img/img-quan.png" alt="">'
                                + '<span>默认规格</span>'
                                + '</p>'
                                + '<p class="order-2-info2">'
                                + '<img src="img/img-quan.png" alt="">'
                                + '<span><label>' + res.data[i].goodsList[j].count + '</label>件</span>'
                                + '</p>'
                                + '<p class="order-2-info3">'
                                + '<label>' + res.data[i].goodsList[j].goodsPrice + '</label>'
                                + '<span>RMB</span>'
                                + '</p>'
                                + '</div>'
                                + '</div>'
                        }
                        + '</div>';
                        html+='<div class="total">'
                            +'<span class="totalText">您本次支付金额</span>'
                            +'<span class="totalSpan">RMB<label class="totalPrice">'+res.data[i].totalPrice+'</label></span>'
                            +'</div>'
                            +'<div class="buy">'
                            +'<img src="img/btn-lijijiesuan.png" alt="" onclick="goPay()">'
                            +'</div>';
                    }
                    $(".orderInfo").html(html);
                }
            }
        })
    }else if(status==1){
        var goodsId=appSupport.cm.queryString("goodsId");
        var buyNum=appSupport.cm.queryString("buyNum");
        var html='';
        $.ajax({
            url: '../zlead/torder/newConfirmOrder',
            type: 'post',
            data: {
                "goodsId":goodsId,
                "buyNum":buyNum
            },
            success: function(res) {
                if(res.data.code=1){
                    html+='<div class="orderTitle"><img src="img/icon-back.png" class="fl go-back-1" onclick="goback()"><span>确认订单</span></div>';
                    if(res.data.defaultAddress!=null){
                        html+='<div class="address">'
                            + '<p><img src="img/con-ren.png" alt=""><span class="memberName">'+res.data.defaultAddress.memberName+'</span></p>'
                        if(res.data.defaultAddress.address.length>23){
                            html+='<p><img src="img/icon-dingweia.png" alt=""><span class="detailAddress">'+res.data.defaultAddress.address.substring(0, 23)+"..."+'</span></p>';
                        }else{
                            html+='<p><img src="img/icon-dingweia.png" alt=""><span class="detailAddress">'+res.data.defaultAddress.address+'</span></p>';
                        }
                        html+='<p><img src="img/icon-dianhua.png" alt=""><span class="phone">'+res.data.defaultAddress.phone+'</span></p>'
                        html+='<input class="addressId" type="hidden" value="'+res.data.defaultAddress.id+'">'
                            + '</div>';
                    }else{
                        html+='<div class="nullAddress" >'
                            + '<img src="img/img-tjdz.png" alt="">'
                            + '<p>您还没有添加地址哦</p>'
                            + '<div class="add" onclick="add()">'
                            + '<img src="img/btn-tjdzi.png" alt="">'
                            + '</div>'
                            + '</div>';
                    }
                    html+='<div class="order">'
                        + '<div class="order-1">'
                        + '<img src="img/img-quan.png" alt="">'
                        + '<span >'+res.data.shopName+'</span>'
                        + '</div>'
                        + '<div class="order-2">'
                        + '<div class="order-2-img">'
                        + '<img src="http://116.62.124.171/group1/' + res.data.goods.firstImg +'" alt="">'
                        + '</div>'
                        + '<div class="order-2-info">'
                        + '<p class="order-2-info1 mb" id="'+res.data.goods.id+'">'+res.data.goods.fullName+'</p>'
                        + '<p class="order-2-info2 mb">'
                        + '<img src="img/img-quan.png" alt="">'
                        + '<span>默认规格</span>'
                        + '</p>'
                        + '<p class="order-2-info2">'
                        + '<img src="img/img-quan.png" alt="">'
                        + '<span><label>'+res.data.buyNum+'</label>件</span>'
                        + '</p>'
                        + '<p class="order-2-info3">'
                        + '<label>'+res.data.totalPrice+'</label>'
                        + '<span>RMB</span>'
                        + '</p>'
                        + '</div>'
                        + '</div>'
                        + '</div>';
                    html+='<div class="total">'
                        +'<span class="totalText">您本次支付金额</span>'
                        +'<span class="totalSpan">RMB<label class="totalPrice">'+res.data.totalPrice+'</label></span>'
                        +'</div>'
                        +'<div class="buy">'
                        +'<img src="img/btn-lijijiesuan.png" alt="" onclick="goPay()">'
                        +'</div>';
                    $(".orderInfo").html(html);
                }
            }
        })
    }
}

function goPay(){
    var addressId=$(".addressId").val();
    var buyNum=appSupport.cm.queryString("buyNum");
    var id=appSupport.cm.queryString("id");
    var goodsId=appSupport.cm.queryString("goodsId");
    var status=appSupport.cm.queryString("status");
    var channel=appSupport.cm.queryString("channel");
    var prodType=0;
    var memberName=$(".memberName").text();
    var detailAddress=$(".detailAddress").text();
    var phone=$(".phone").text();
    var totalPrice=$(".totalPrice").text();
    /*$(".buy img").attr("onclick","");*/
    if(addressId==null ||addressId==undefined){
        var msg = "请添加地址信息！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
    }
    if(channel==0){//pointType==0积分购
        orderType=0;
    }else if(channel==5){//pointType==1积分换
        orderType=11;
    }
    if(status==1){
        $.ajax({
            url: '../zlead/torder/addOrder',
            type: 'post',
            data: {
                "goodsId":goodsId,
                "buyNum":buyNum,
                "addressId":addressId,
                "buyType":"1",
                "storeBuyType":"1",
                "orderType":orderType
            },
            success: function(res) {
                if(res.code==1){
                    var msg = res.message;
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                        var orderNo=res.data.orderSn;
                        window.location.href = "agentOrderPay.jsp?orderNo="+orderNo+"&status="+1;
                    }, 1000);
                }else{
                    $(".buy img").attr("onclick","goPay()");
                }
            }
        })
    }else if(status==2){
        $.ajax({
            url: '../zlead/torder/cartAddOrder',
            type: 'post',
            data: {
                "cartIds":id,
                "addressId":addressId,
                "buyType":"1",
                "storeBuyType":"1",
                "orderType":orderType
            },
            success: function(res) {
                if(res.code==1){
                    var msg = res.message;
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                        var orderNo=res.data.orderSn;
                        window.location.href = "agentOrderPay.jsp?orderNo="+orderNo+"&status="+2;
                    }, 1000);
                }else{
                    $(".buy img").attr("onclick","goPay()");
                }
            }
        })
    }

}

function add(){
    window.location.href ="addAddress.jsp"
}

function goback(){
    window.history.back();
}
