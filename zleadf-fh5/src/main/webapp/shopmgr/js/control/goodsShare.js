$(function() {
    var baseUrl=localStorage.getItem("weburl");
    var shopId=appSupport.cm.queryString("shopId");
    var goodsId=appSupport.cm.queryString("goodsId");
    if(baseUrl==null || baseUrl=="null"){
		baseUrl=$("#weburl").val();
		if(baseUrl==null || baseUrl=="null")   	baseUrl="..";
    }
    var link = baseUrl+'/company/agentMallDetails.action?goodsId='+goodsId+"&shopId="+shopId;
    new QRCode(document.getElementById("qrcode"), {
        text : link
    });
    var _hmt = _hmt || [];
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?d69321757dcfbfbe09dbddd4dca87b28";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
    if(is_weixn()){
        $(".bdsharebuttonbox").hide();
        $("#fxtsy").html("长按二维码分享给好友")
    }
});

function gotoIndex(){
    var baseUrl=localStorage.getItem("weburl");
    var shopId=appSupport.cm.queryString("shopId");
    if(baseUrl==null || baseUrl=="null"){
		baseUrl=$("#weburl").val();
		if(baseUrl==null || baseUrl=="null")   	baseUrl="..";
    }
    window.location.href = baseUrl+"/company/index.action?shopId="+shopId;
}

function gotoDetail(){
    var baseUrl=localStorage.getItem("weburl");
    var shopId=appSupport.cm.queryString("shopId");
    var goodsId=appSupport.cm.queryString("goodsId");
    if(baseUrl==null || baseUrl=="null"){
		baseUrl=$("#weburl").val();
		if(baseUrl==null || baseUrl=="null")   	baseUrl="..";
    }
    window.location.href = baseUrl+'/company/agentMallDetails.action?goodsId='+goodsId+"&shopId="+shopId;
}

function call(command) {
    try {
        nativeShare.call(command)
    } catch (err) {
        // 如果不支持，你可以在这里做降级处理
        alert(err.message)
    }
}
function is_weixn(){
    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;
    if (isWeixin) {
        return true;
    }else{
        return false;
    }
}
function is_uc(){

    if(strpos($_SERVER['HTTP_USER_AGENT'],'UCBrowser')!==false||strpos($_SERVER['HTTP_USER_AGENT'],'UCWEB')!==false)
        return true;
    else
        return false;
}


// 判断是否是qq浏览器

function is_qq(){
    if(strpos($_SERVER['HTTP_USER_AGENT'],'MQQBrowser')!==false)
        return true;
    else
        return false;
}

