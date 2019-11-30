$(function() {
	localStorage.setItem("memberId",null);
	localStorage.removeItem("memberId");
	//localStorage.clear(); 
	var memberId = localStorage.getItem("memberId");
	if(memberId == null) {
		window.location.href = "login.jsp";
	} else {
		init();
	}
})
	
function logout(){
	var account = localStorage.getItem("account");
	localStorage.setItem("memberId",null);
	$.ajax({
		url: '../ajeasy/login/logout',
        type: 'post',
        data: {
        	"account":account
        },
        success: function(res) {
        	if(res.code==1){
        		$(".headImg").attr('src',res.data.headImg);
        		$("#account").html(res.data.account);
        		$("#point").html(res.data.point);
        	}
        }
	})
}
 
 