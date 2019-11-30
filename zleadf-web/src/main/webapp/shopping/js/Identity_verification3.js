$(function(){
		
//				$(".meng").show();
				var t=setTimeout("$('.meng').hide();",1000);
				
			//   去登录
			    $("#next_login").click(function () {
			    	sessionStorage.setItem("phone","");
					window.location = "index.html?login=one"

				 })
		});