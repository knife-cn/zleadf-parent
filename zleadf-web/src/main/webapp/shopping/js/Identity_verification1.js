$(function(){
			$(".hao").click(function(){
				$('.ma').removeClass("yankuan");
			});
			$("#mashu").click(function(){
				$(".ma").addClass("yankuan");
				$("#phone").css("border","1px solid rgb(229, 229, 229)");
			});


	$("#btninfo").click(function () {
		if ($("#phone").val()==""){
			alert("手机号不能为空")
		}else {
			var pt=$("#phone").val()


			var memberEntity=JSON.stringify({
				phone:pt

			})
		$.ajax({
			type: "post", //提交请求的方式
			url: "/zlead/api/msg", //访问servlet的路径
			data:memberEntity,
			dataType: "json", //没有这个，将把后台放会的json解析成字符串
			async:true,
			contentType:"application/json",
			success:function (res) {

				if(res.success) {
					settime();
				}
				else  {
						alert("你的手机号未被注册过")
				}
			}
		})
		}

	})
	function settime() {


			//发送验证码倒计时
			if (countdown == 0) {
				$("#btninfo").attr('disabled',false);
				$("#btninfo").html("重新获取验证码");
				$("#btninfo").css("background","rgba(253,87,0,1)")
				countdown = 60;
				return;
			} else {

				$("#btninfo").attr('disabled',true);
				$("#btninfo").html("重新获取" + countdown + "S");
				countdown--;
				$("#btninfo").css("background","#ccc")
			}
			setTimeout(function() {
					settime()
				}
				,1000)

	}

			// 下一步
			$("#next_step1").click(function () {
				var pt=$("#phone").val();
				var ma=$("#mashu").val()
				if (pt==""){
					alert("请输入手机号")
				}
				else if(ma==""){
					alert("请输入验证码")
				}
				else {
				$.ajax({
					type: "post", //提交请求的方式
					cache: true	, //是否有缓存
					url: "/zlead/api/msgcode", //访问servlet的路径
					dataType: "json", //没有这个，将把后台放会的json解析成字符串
					data:{
						phone:pt,
						msg:ma
					},

					success:function (res) {

						if(res.success) {
							window.sessionStorage.setItem("phone",$("#phone").val());
							window.location = "Identity_verification2.html"
						}
						else {
							alert("验证码不正确")
						}
					},
					error: function (request) { //请求出错
						alert("验证码已过期，请从新获取");
					}
				})
				}


			 })
		})