$(function() {
    var orderNo=localStorage.getItem("orderNo");
    $("#orderNo").html("订单号:"+orderNo+"已经支付成功")
    var shopId=localStorage.getItem("shopId");
    jump(3,shopId);
})


function jump(count,shopId) {
    window.setTimeout(function(){
        count--;
        if(count > 0) {
            $('#num').html(count);
            jump(count,shopId);
        } else {
            var baseUrl=localStorage.getItem("weburl");
            if(baseUrl==null || baseUrl=="null"){
                baseUrl=$("#weburl").val();
                if(baseUrl==null || baseUrl=="null")   	baseUrl="..";
            }
            location.href=baseUrl+"/company/index.action?shopId="+shopId;
        }
    }, 1000);
}
