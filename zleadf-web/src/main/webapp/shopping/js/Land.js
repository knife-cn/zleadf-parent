
		$(function(){
			$("#cea").click(function () {
				$(".zhong").hide();
				$(".zhongtwo").show();
			});
			$(".img").click(function(){
				window.location.href="maLand.html";	
			});
			$("#one").click(function(){
				$('.kuan').eq(0).addClass("yan").siblings().removeClass("yan");
			});
			$("#tow").click(function(){
				$('.kuan').eq(1).addClass("yan").siblings().removeClass("yan");
			});

			$("#button").click(function () {
				var RegCellPhone = /^(1)([0-9]{10})?$/;
				var  falg=$("#one").val().search(RegCellPhone);

				if(!$("#one").val())
				{
					$(".tishi").show().delay(2000).hide(0);
					$(".tishi").html("请输入账号")
				}
				else if(falg==-1){
					$(".tishi").show().delay(2000).hide(0);
					$(".tishi").html("账户不正确，请重新输入")
				}else if(!$("#tow").val()){
					$(".tishi").show().delay(2000).hide(0);
					$(".tishi").html("请输入密码")
				}
				else if($("#tow").val().length<6 || $("#tow").val().length>20){
					$(".tishi").show().delay(2000).hide(0);
					$(".tishi").html("密码输入错误！请重新输入")
				}else{
					logina($("#one").val(),$("#tow").val())
				}

			});

			function logina(userId,pwd){
				$.ajax({
					type:"get",//提交请求的方式
					cache:true,//是否有缓存
					url: "/zlead/member/login?account=" + userId + "&password=" + pwd +"&t_="+Math.random(),//访问servlet的路径
					// data: {
					// 	account:userId,
					// 	password:pwd
					// },
					dataType:"json",//没有这个，将把后台放会的json解析成字符串
					async:true,//是否异步
					error:function(request) {//请求出错
					},
					success:function(data) {
						if (data.success==true){
							$(".meng").hide();
							sessionStorage.setItem("memberId",data.data.memberId);
							window.location.href="index";
						}else{
							$(".tishi").show().delay(2000).hide(0);
							$(".tishi").html(data.message)
						}

					}
				})
			}

		});