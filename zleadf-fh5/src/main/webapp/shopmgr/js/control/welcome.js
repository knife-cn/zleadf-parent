$(function() {
    var username = $("#username").val();
    var password = $("#password").val();
    var memberId = $("#memberId").val();
    var memberType = $("#memberType").val();
    var shopId = $("#shopId").val();
    if (memberId){
        localStorage.setItem("memberId",memberId);
    }
    if (memberType){
        localStorage.setItem("memberType",memberType);
    }
    jump(10,shopId);
})


function jump(count,shopId) {
    window.setTimeout(function(){
        count--;
        if(count > 0) {
            $('#num').html(count);
            jump(count,shopId);
        } else {
            location.href="index.action?shopId="+shopId;
        }
    }, 1000);
}
