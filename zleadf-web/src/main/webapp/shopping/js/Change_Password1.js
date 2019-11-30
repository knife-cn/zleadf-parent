$(function(){
	var aa=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
	var  falg=$("#npad").val().search(aa);
		$("#npad").blur(function(){
			var bb=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
			var  falga=$("#npad").val().search(aa);
			if(falga!=-1){
				$("#npad").css({
					"border-radius":"4px",
					"border":"1px solid rgba(229,229,229,1)"
				})
				$("#as").text("")
			}
		})
		$(".confirm[type=button]").click(function () {
			$(".mmx").html("");
			$(".xmmx").html("");
			$(".qmx").html("");
			var npad=$("#npad").val();
			var pad=$("#pad").val();
			var jpad=$("#jpad").val();//当前密码
			if (!jpad)
			{
				$(".mmx").html("请输入原密码!")
				return false;

			}
			var aa=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
			var  falg=$("#npad").val().search(aa);
			if(!npad){
				$(".xmmx").html("密码不能为空!")
				return false;
			}else if(falg==-1){
				this.focus();
				$("#npad").css({
					"border-radius":"4px",
					"border":"1px solid red"
				})
				$("#as").text("6-20个字符，由字母数字，和符号两种以上组合")
				return false;
			}
			if(!pad){
				$(".qmx").html("请确认密码!");
				return false;

			}
			if(npad==pad) {
					var em = {
						Password: npad,
						Npassword: jpad
					}

					$.ajax({
						type: "post",
						url: "/zlead/login/updatepaw",
						data: em,
						dataType: "json",
						async: true,
						success: function (data) {
							if (data.success == true) {
								$(".tsk").show();
								$(".rdao").hide();
								$(".mima").hide();
								$(".xinmima").hide();
								$(".quemima").hide();
								$(".confirm").hide();
								var npad = $("#npad").val(null);
								var pad = $("#pad").val(null);
								var jpad = $("#jpad").val(null);

							} else {
								$(".mmx").html("原密码不正确")
							}
						}
					});

			}
			else
			{
				$(".qmx").html("两次密码输入不一致！")
			}
		});

});