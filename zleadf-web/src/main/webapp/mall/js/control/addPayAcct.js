$(function() {
	function checkPrive() {
		 if($("#userName").val() == null||$("#userName").val() == ""){
			var msg ="请填写姓名";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 1000);
			return false;
		}else if($("#userPhone").val() == null||$("#userPhone").val() == ""){
			var msg ="请填写手机号";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 1000);
			return false;
		}else {
			return true;
		}
	}
	
	$("#preservation").on("click", function() {
		if(checkPrive() == true) {
				var url = "../ajeasy/user/addPayAcct";
				var data = {
					"memberName": $("#userName").val(),
					"phone": $("#userPhone").val(),
				};
				$.ajax({
					url: url,
					type: "post",
					data: data,
					dataType: "json",
					success: function(evt) {
						if(evt.code == 1) {
							var msg=evt.message;
			        		appSupport.cm.errorMessageShow(errorMsg, msg);
			    			setTimeout(function() {
			    				appSupport.cm.errorMessageHide(errorMsg);
			    				window.location.href = "withdrawList.jsp";
			    			}, 1000);
						}else{
							var msg=evt.message;
			        		appSupport.cm.errorMessageShow(errorMsg, msg);
			    			setTimeout(function() {
			    				appSupport.cm.errorMessageHide(errorMsg);
			    			}, 1000);
						}
					}
				})
		}
	})
})
		