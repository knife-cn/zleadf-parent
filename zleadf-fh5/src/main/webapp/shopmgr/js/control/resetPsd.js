$(function() {
    var shopId=appSupport.cm.queryString("shopId");
	var memberId = localStorage.getItem("memberId");
	if(memberId == null) {
		window.location.href = "login.jsp?shopId="+shopId;
	}
})

function resetPsd(){
    var shopId=appSupport.cm.queryString("shopId");
	var memberId = localStorage.getItem("memberId");
	var oldPass=$("#oldPass").val();
	var newPass=$("#newPass").val();
	if(oldPass == '') {
		var msg = "请输入旧密码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(newPass == '') {
		var msg = "请输入新密码！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}else if(oldPass == newPass) {
		var msg = "旧密码和新密码不能相同！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 500);
		return false;
	}
	$.ajax({
		url: '../ajeasy/user/updPassWord',
        type: 'post',
        data: {
        	"memberId":memberId,
        	"oldPass":oldPass,
        	"newPass":newPass
        },
        success: function(res) {
        	if(res.code==1){
        		var msg ="重置密码成功！";
        		appSupport.cm.errorMessageShow(errorMsg, msg);
        		setTimeout(function() {
        			appSupport.cm.errorMessageHide(errorMsg);
        			window.location.href = "login.jsp?shopId="+shopId;
        		}, 1000);
        	}else{
        		var msg =res.message;
        		appSupport.cm.errorMessageShow(errorMsg, msg);
        		setTimeout(function() {
        			appSupport.cm.errorMessageHide(errorMsg);
        		}, 1000);
        	}
        }
	})
}

function fanhui(){
	window.history.back();
}