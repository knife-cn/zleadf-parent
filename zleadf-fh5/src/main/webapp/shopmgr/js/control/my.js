$(function() {
	var userId = localStorage.getItem("userId");
	if(userId == null) {
		window.location.href = "login.html";
	} else {
		init();
	}

	function init() {
		//$(".name").html(localStorage.getItem("userPhone"));
		var memberType=localStorage.getItem("memberType");
		var level=localStorage.getItem("level");
		if(memberType=="2"){
			$(".vip").text("分享会员");
		}else if(memberType=="1"){
			if(level==0){
				$(".vip").text("内部会员");
			}else if(level==10){
				$(".vip").text("区域经理");
				$(".my_list li").eq(0).hide();
				$(".my_list li").eq(1).hide();
			}else if(level==20){
				$(".vip").text("加盟商-线下体验店");
				$(".my_list li").eq(0).hide();
			}else if(level==30){
				$(".vip").text("社交分享商");
				$(".my_list li").eq(0).hide();
			}else if(level==40){
				$(".vip").text("业务经理");
				$(".my_list li").eq(0).hide();
				$(".my_list li").eq(1).hide();
			}
		}
		var url = baseUrl.formal + "query_account.html";
		var data = {
			userno: localStorage.getItem('userNo')
		};
		appSupport.cm.postAjaxFunction(url, data, callback);
		$(".order_list ul li").on("click", function() {
			var index = $(this).index();
			window.location.href = "ordermanage.html?order_ty=" + index;
		})
	}

	function callback(evt) {
		if(evt.code == "1") {
			if(evt.data.daifaNum > 0) {
				$(".daifa").show().html(evt.data.daifaNum);
			}
			if(evt.data.daifuNum > 0) {
				$(".daifu").show().html(evt.data.daifuNum);
			}
			if(evt.data.daishouNum > 0) {
				$(".daishou").show().html(evt.data.daishouNum);
			}
			if(evt.data.handPic!=""){
				$(".logo img").attr("src",evt.data.handPic);
			}else{
				$(".logo img").attr("src","img/logo_03.png");
			}
			if(evt.data.nickName!=""){
				$(".name").text(evt.data.nickName);
			}else{
				$(".name").text(localStorage.getItem("userPhone"));
			}
			$("#loding").hide();
			
		}
	}
	$(".my_list li").on("click",function(){
		var index=$(this).index();
		if(index==0){
			window.location.href="my/prooerty.html";
		}else if(index==1){
			window.location.href="my/invite-friend.html";
		}else if(index==2){
			localStorage.setItem("type","defelet");
			window.location.href="managementaddress.html";			
		}else if(index==3){
			window.location.href="my/change-passwd.html";
		}else if(index==4){
			window.location.href="my/contact-us.html";
		}
	})
})