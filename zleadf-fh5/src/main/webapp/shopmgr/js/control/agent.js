$(function() {
	initAreaSelect('expressArea', function(expressArea) {
		$("#expressArea dl").html(expressArea);
	})
    agentEnterMoney=appSupport.cm.queryString("agentEnterMoney");
    $("#agentEnterMoney").attr("value",agentEnterMoney);
})	
function validateForm(){

    var contactName = $("#contactName").val();
    if(contactName==''){
    	var msg = "请输入联系人姓名！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var contactPhone = $("#contactPhone").val();
    if(contactPhone==''){
    	var msg = "请输入联系人电话！";
		appSupport.cm.errorMessageShow(errorMsg, msg);
		setTimeout(function() {
			appSupport.cm.errorMessageHide(errorMsg);
		}, 1000);
		return false;
    }
    var province_id = $("#areaResult").attr("province_id");
    $("#provinceId").val(province_id);
    var city_id = $("#areaResult").attr("city_id");
    $("#cityId").val(city_id);
    var county_id = $("#areaResult").attr("county_id");
    $("#countryId").val(county_id);
    var companyAddress = $("#companyAddress").val();
    if(companyAddress==''){
        var msg = "请输入详细地址！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var companyLegalPersonSn = $("#companyLegalPersonSn").val();
    if(companyLegalPersonSn==''){
        var msg = "请输入身份证号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var businessLicenceSn = $("#businessLicenceSn").val();
    if(businessLicenceSn==''){
        var msg = "请输入营业执照编号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var businessLicenceAddress = $("#businessLicenceAddress").val();
    if(businessLicenceAddress==''){
        var msg = "请输入营业执照地址！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    $("#validate").attr("onclick","");
    $.ajax({
        url:$("#form1").attr("action"),
        type:"post",
        dataType:"json",
        data:$("#form1").serialize(),
        success : function(res) {
            if(res.success) {
                appSupport.cm.errorMessageShow("#errorMsg", "提交成功，请付款并等待审核");
                setTimeout(function() {
                    appSupport.cm.errorMessageHide("#errorMsg");
                }, 1000);
                window.location.href="../jsp/mobile/agent/do/applyAgent.jsp?agentEnterMoney="+agentEnterMoney;
                return;
            }else{
                appSupport.cm.errorMessageShow("#errorMsg", "提交失败，重新提交");
                setTimeout(function() {
                    appSupport.cm.errorMessageHide("#errorMsg");
                }, 1000);
                $("#validate").attr("onclick","validateForm()");
            }
        }
    });
}
function jump() {
    window.location.href='index.jsp';
}