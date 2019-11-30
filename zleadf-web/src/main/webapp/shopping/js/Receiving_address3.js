$(function(){

		//	获取url的参数
		(function ($) {
			$.getUrlParam = function (name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = window.location.search.substr(1).match(reg);
				if (r != null) return unescape(r[2]); return null;
			}
		})(jQuery);

             $("#qu").click(function () {
				 var id = $.getUrlParam("id");

				 $.ajax({
					 type:"post",//提交请求的方式
					 cache:true,//是否有缓存
					 url:"/zlead/memaddr/deleteMemberAddress",//访问servlet的路径
					 dataType:"json",//没有这个，将把后台放会的json解析成字符串
					 async:true,//是否异步
					 data:{
						 addressId:id
					 },
					 success:function(res) {
						 if (res.code==1){
						 	alert(res.message)
							window.location.href="left.html#dz"
						 }
					 },
					 error:function(request) {//请求出错
					 }
				 })
			 })

			var tpl='';
			var width=document.documentElement.clientWidth 
		
			document.getElementById("top3").style.width=width+"px"; 
			
			
			$(".img").click(function(){
				window.location.href="left.html#dz"
			});
			$("#fo").click(function(){
				window.location.href="left.html#dz"
			});

		});