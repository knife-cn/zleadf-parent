$(function() {
	var countdown=60;
	$("#buttonya").click(function () {
		if ($("#phone").val()==""){
			alert("手机号不能为空")
		}else {
			var pt=$("#phone").val()




			$.ajax({
				type: "post", //提交请求的方式
				url: "/zlead/member/sendVerifyCode", //访问servlet的路径
				data:{
					phone:pt
				},
				dataType: "json", //没有这个，将把后台放会的json解析成字符串
				async:true,
				success:function (res) {

					if(res.success) {
						settime();
					}else{
						alert(res.message);
					}

				}
			})
		}

	})
	function settime() {


		//发送验证码倒计时
		if (countdown == 0) {
			$("#buttonya").attr('disabled',false);
			$("#buttonya").html("获取验证码");
			$("#buttonya").css("background","rgba(253,87,0,1)")
			countdown = 60;
			return;
		} else {

			$("#buttonya").attr('disabled',true);
			$("#buttonya").html("重新获取" + countdown + "S");
			countdown--;
			$("#buttonya").css("background","#ccc")
		}
		setTimeout(function() {
				settime()
			}
			,1000)

	}

	$("#ced").click(function () {
		$(".zhongtwo").hide();
		$(".zhong").show();
	})
	$("#phone").click(function(){
		$('.kuan').eq(0).addClass("yan").siblings().removeClass("yan");
	});
	$("#password").click(function(){
		$('.kuan').eq(1).addClass("yan").siblings().removeClass("yan");
	});
	$("#regin").click(function () {
		var phone=$("#phone").val();
		var paw=$("#password").val();
		var companyName=$("#companyName").val()
		var RegCellPhone = /^(1)([0-9]{10})?$/;
		var  falg=$("#phone").val().search(RegCellPhone);
		var aa=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
		var  falaa=paw.search(aa);
			var pt=$("#phone").val();
			var ma=$("#yanzhenm").val()
			if (pt==""){
				alert("请输入手机号")
			}
			else if(ma==""){
				alert("请输入验证码")
			}
			else {

							if(phone==""||phone==null||phone==undefined){
								alert("号码不能为空！")
							}
							else if(falg==-1){
								alert("账户不正确，请重新输入")
							}
							else if (paw==""||paw==null||paw==undefined){
								alert("密码不能为空！")
							}
							else if (!companyName){
								alert("公司名称不能为空！")
							}
							else if(falaa==-1){
								alert("密码请输入6-20数字和字母的组合");
							}
							else
							{
								$.ajax({
									type: "post",
									url:"/zlead/member/registered2",
									data: {
										phone: phone,
										passWord: paw,
										companyName:companyName,
										verifyCode:ma
									},
									dataType: "json",
									async: true,
									success: function(data) {
										if (data.code==1)
										{
											alert("注册成功！");
											$("#meng").hide();
										}
										else{
											alert(data.message);
										}
									},
									error: function () {
										alert(data.message);
									}
								});
							}

			}






	});

})
