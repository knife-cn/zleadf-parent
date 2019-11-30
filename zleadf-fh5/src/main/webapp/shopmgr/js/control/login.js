$(function() {
	/*$("#loginBtn").on("touchstart", function() {
		$(this).css("background", "#2fa9b6");
	})*/
	//点击登录发送请求
    var shopId = appSupport.cm.queryString("shopId");
    if (!shopId){
    	shopId=10;
	}
	var msg=localStorage.getItem("msg");
    //alert(msg)
	if(msg){
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 500);
	}
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    if (projectName==="/company"||projectName==="/member"){
        projectName=""
	}
    var protocol = window.location.protocol;
    var host = window.location.host;
    var projectPath = protocol+'//'+host+projectName;
    var baseUrl=localStorage.getItem("weburl");
    if(!baseUrl){
        baseUrl="..";
    }
    $.ajax({
        type : "post",
        url : baseUrl+"/cert/wxlogin",
        dataType : "json",
        async : false,
		data:{
        	"projectPath":projectPath,
            "shopId":shopId
		},
        success : function(data) {
            $(".wxLogin").click(function () {
                window.location.href=data.wxUrl;
            })
            $(".qqLogin").click(function () {
                window.location.href=data.qqUrl;
            })
            $(".wbLogin").click(function () {
                window.location.href=data.wbUrl;
            })
        }})



	$("#loginBtn").on("touchend", function() {
		var account =$("#telNumber").val();
		var password =$("#password").val();
		var returnurl=$("#returnurl").val();
		/*$(this).css("background", "#3dc5d3");*/
		if(loginverify() == true) {
			$(this).css("background", "url(img/btn-nexthightlight.png)");
			$(this).css("background-size", "3.25rem 0.8rem");
			var msg = "登陆中...";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			var data = {
				account: account,
				password:password,
				returnurl:returnurl
			}
			var url = "../zlead/login/login";
			login();
			//appSupport.cm.postAjaxFunction(url,data,callback);
			/*console.log("输入正确");*/
		}
	})
	//处理返回数据
	function callback(evt) {
		if(evt.code=="1"){
			localStorage.setItem("memberId",evt.data.memberId);
			localStorage.setItem("storeType",evt.data.storeType);
            localStorage.setItem("memberType",evt.data.memberType);
			/*localStorage.setItem("hasPayPassword",evt.data.hasPayPassword);
			localStorage.setItem("userPhone",evt.data.phone);
			localStorage.setItem("userNo",evt.data.userNo);
			localStorage.setItem("level",evt.data.level);*/
			 
			if(evt.data.returnurl)
				window.location.href=evt.data.returnurl+"?shopId="+shopId;
			else
				window.location.href="../member/agentAccount.jsp";
						
		}else if(evt.code!="1"){
			var msg = evt.message;
				appSupport.cm.errorMessageShow(errorMsg, msg);
				setTimeout(function() {
					appSupport.cm.errorMessageHide(errorMsg);
				}, 500);
		}
	}
})
//	验证输入信息
	function loginverify() {
		var phoneNumber = $("#telNumber").val();
		var passWord = $("#passWord").val();
		var re = /^1\d{10}$/;
		if(phoneNumber == "") {
			var msg = "请输入登陆账号！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else if(passWord == "") {
			var msg = "请输入密码！";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 500);
			return false;
		} else {
			return true;
		}
	}

	function login(){
		frmLogin.action="../zlead/login/memberlogin";
		frmLogin.submit();
	}
	
	function gotoPassword(){
		window.location.href="forgetPassword.jsp";
	}
	function gotoRegister(){
        var shopId = appSupport.cm.queryString("shopId");
        var baseUrl=localStorage.getItem("weburl");
        if(baseUrl==null || baseUrl=="null"){
			baseUrl=$("#weburl").val();
			if(baseUrl==null || baseUrl=="null")   	baseUrl="..";
    	}
        window.location.href=baseUrl+'/member/register.jsp?shopId='+shopId;
	}