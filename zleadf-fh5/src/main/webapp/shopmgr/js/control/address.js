$(function(){
	$("#loding").show();
	function init(){
		var url=baseUrl.formal+"user/query_address.html";
		var data={
			userId:localStorage.getItem("userId")
		};
		appSupport.cm.postAjaxFunction(url,data,showAddressList);
	}
	init();
	function showAddressList(evt){
		if(evt.success==false){
			window.location.href="login.html";
		}
		 else if(evt.code=="1"){
			var length=evt.data.addressList.length;
			var addressList=evt.data.addressList;
			var str="";
			for( var i=0;i<length;i++){
				str+="<li class='address_list'>";
					str+="<div>";
						str+="<p class='clearfix'>";
							str+="<span>"+addressList[i].name+"</span>";
							str+="<span>"+addressList[i].phone+"</span>";
						str+="</p>";
						str+="<p>"+addressList[i].provinceId+addressList[i].cityId+addressList[i].districtId+addressList[i].address+"</p>";
						str+="<div class='address_tab'>";
						if(addressList[i].id==evt.data.defaultAddress.id){
							str+="<img src='img/adress_08.png' class='default'>";
							str+="<p class='moren default'>设为默认</p>";
						}else{
							str+="<img src='img/adress_13.png' class='default'>";
							str+="<p style='color:#333333;' class='default'>设为默认</p>";
						}
							
							str+="<div class='clearfix fr'>";
								str+="<div class='clearfix fl edit' style='margin-right: 0.2rem;'>";
									str+="<img src='img/adress_03.png'>";
									str+="<p>编辑</p>";
								str+="</div>";
								str+="<div class='clearfix fl del'>";
									str+="<img src='img/adress_05.png'>";
									str+="<p>删除</p>";
								str+="</div>";
							str+="</div>";
						str+="</div>";
					str+="</div>";
				str+="</li>";
			};
			$("#userAddress").empty().append(str);
			$("#loding").hide();
			//设置默认地址
			$(".default").on("click",function(){
				$("#loding").show().css("background","none");
				var index=$(this).parent().parent().parent().index();
				var url=baseUrl.formal+"user/set_default_address.html";
				var rigon={
					addressId:addressList[index].id,
					userId:localStorage.getItem("userId")
				}
				appSupport.cm.postAjaxFunction(url,rigon,defaultAddress);
			});
			//删除地址
			$(".del").on("click",function(){
				$("#loding").show().css("background","none");
				var index=$(this).parent().parent().parent().parent().index();
				var url=baseUrl.formal+"user/remove_address.html";
				var rigon={
					addressId:addressList[index].id,
					userId:localStorage.getItem("userId")
				}
				appSupport.cm.postAjaxFunction(url,rigon,defaultAddress);
			});
			//	给予提交订单客户地址
			$(".address_list>div>p").on("click",function(){
				var type=localStorage.getItem("type");
				var isShopCar=localStorage.getItem("isShopCar");
				var index=$(this).parent().parent().index();
				if(type==0&&isShopCar==0){
					localStorage.setItem("type",index);
					window.location.href="submitorde.html";
				}else if(type==0&&isShopCar==1){
					localStorage.setItem("type",index);
					window.location.href="shopcarsubmitorde.html";
				}else{
					
				}
			});
		}
	}
//	设置默认地址
		function defaultAddress(evt){
			if(evt.code=="1"){
				$("#userAddress").empty();
				init();
			}else if(evt.code=="43"){
				$("#loding").hide();
				var msg = "默认地址不能删除";
				appSupport.cm.errorMessageShow(errorMsg, msg);
				setTimeout(function() {
					appSupport.cm.errorMessageHide(errorMsg);
				}, 500);
			}
		}
//	新增收货地址
	$(".add_title").on("click",function(){
		window.location.href="addadress.html";
	})
	
})

