$(function(){
			
			$("input[type=password]").click(function(){
				$('.xia').addClass("yan");
			});
			if ($("#xinpwdd").val()=="" && $("#qdxinpwdd").val()=="" ){
				$('#next_step2').removeClass("yan");
			}
			else {
				$('#next_step2').addClass("yan");
			}
			var phone=window.sessionStorage.getItem("phone");

			// 下一步
			$("#next_step2").click(function () {
				var aa=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
				var  falg=$("#xinpwdd").val().search(aa);

				if ($("#xinpwdd").val()==""){
					alert("请设置登录密码")
				}
				else if (falg==-1){

					this.focus();
					$("#xinpwdd").css({
						"border-radius":"4px",
						"border":"1px solid red"
					})
					$("#as").text("6-20个字符，由字母数字，和符号两种以上组合")

				}
				else if ($("#qdxinpwdd").val()==""){
					alert("请确认密码")
				}
				else if ($("#xinpwdd").val()==$("#qdxinpwdd").val()){
					var pwd=$("#xinpwdd").val()
					var memberEntity=JSON.stringify({
						phone:phone,
						password:pwd

					})
					$.ajax({
						type: "post", //提交请求的方式
						cache: true	, //是否有缓存
						url: "/zlead/api/updatepwd", //访问servlet的路径
						dataType: "json", //没有这个，将把后台放会的json解析成字符串
						data:memberEntity,
						contentType:"application/json",

						success:function (res) {

							if(res.success) {
								window.location = "Identity_verification3.html"
							}
						}
					})
				}
				else {
					alert("两次密码不一致")
				}


			 })
		});