$(function() {
	initAreaSelect('expressArea', function(expressArea) {
		$("#expressArea dl").html(expressArea);
	})
	var memberName = decodeURI(appSupport.cm.queryString("memberName"));
	var phone = appSupport.cm.queryString("phone");
	var detailAddress = decodeURI(appSupport.cm.queryString("detailAddress"));
	var addressId = decodeURI(appSupport.cm.queryString("addressId"));
	var provinceName = decodeURI(appSupport.cm.queryString("provinceName"));
	var cityName = decodeURI(appSupport.cm.queryString("cityName"));
	var countyName = decodeURI(appSupport.cm.queryString("countyName"));
	var address =provinceName+cityName+countyName;
	var type=appSupport.cm.queryString("type");
	var isDefault=appSupport.cm.queryString("isDefault");
	if(type==2) {
		$("#userName").val(memberName);
		$("#userPhone").val(phone);
		$("#expressArea dt").text(address);
		$("#userAress").val(detailAddress);
		$("#prive").val(provinceName);
		$("#city").val(cityName);
		$("#trxo").val(countyName);
		if(isDefault==1){
			$(".address-isDefault").html("<input name='radio' type='radio' id='isDefault' value='1' checked='checked'>");
		}else{
			$(".address-isDefault").html("<input name='radio' type='radio' id='isDefault' value='0'>");
		}
	}
	function checkPrive() {
		if($("#prive").val() == null||$("#prive").val() == "") {
			var msg ="请选择省市区";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 1000);
			return false;
		} else if($("#userName").val() == null||$("#userName").val() == ""){
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
		}else if($("#userAress").val() == null||$("#userAress").val() == ""){
			var msg ="请填写详细地址";
			appSupport.cm.errorMessageShow(errorMsg, msg);
			setTimeout(function() {
				appSupport.cm.errorMessageHide(errorMsg);
			}, 1000);
			return false;
		} else {
			return true;
		}
	}
	
	$("#preservation").on("click", function() {
		if(checkPrive() == true) {
			if(type==1 || type=="" || type==null) {
				var url = "../zlead/tmemberaddress/addOrUpdateMemberAddress";
				var isDefault=$('input:radio[name="radio"]').val();
				if($('input:radio[name="radio"]').is(':checked')==true){
					var isDefault ="1";
				}else{
					var isDefault ="0";
				}
				var data = {
					"provinceName": $("#prive").val(),
					"cityName": $("#city").val(),
					"countyName": $("#trxo").val(),
					"memberName": $("#userName").val(),
					"phone": $("#userPhone").val(),
					"detailAddress":$("#userAress").val(),
					"type": 1,
					"isDefault":isDefault
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
			    				window.location.href = "addressManage.jsp";
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
			} else {
				var url = "../zlead/tmemberaddress/addOrUpdateMemberAddress";
				var isDefault=$('input:radio[name="radio"]').val();
				if($('input:radio[name="radio"]').is(':checked')==true){
					var isDefault ="1";
				}else{
					var isDefault ="0";
				}
				var data = {
					"provinceName": $("#prive").val(),
					"cityName": $("#city").val(),
					"countyName": $("#trxo").val(),
					"memberName": $("#userName").val(),
					"phone": $("#userPhone").val(),
					"detailAddress":$("#userAress").val(),
					"addressId": addressId,
					"type":2,
					"isDefault":isDefault
				};
				$.ajax({
					url: url,
					type: "post",
					data: data,
					dataType: "json",
					success: function(evt) {
						if(evt.code == 1) {
							window.location.href = "addressManage.jsp";
						}
					}
				})
			}
		}
	})
})
		