$(function() {
	$("#preservation").click(function(){
		if($("#money").val() == null||$("#money").val() == ""){
			var msg ="提现金额不能为空";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 1000);
			return false;
		}else if(!($("#money").val() % 100==0 && $("#money").val()>0)){
			var msg ="提现金额必须为100的整数倍";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 1000);
			return false;
		}else {
			$.ajax({
				url: '../ajeasy/user/doWithdraws',
				type: "post",
				data: {
					"totalAmount": $("#money").val(),
					"txType": 3
				},
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
		