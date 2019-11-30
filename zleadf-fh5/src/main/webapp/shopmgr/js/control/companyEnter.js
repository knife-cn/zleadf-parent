$(function() {
    initAreaSelect('expressArea', function(expressArea) {
        $("#expressArea dl").html(expressArea);
    })
})
function validateForm(){
    var contactName = $("#addMemebrId").val();
    if(contactName==''){
        var msg = "请输入加盟商编号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
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
    var companyName = $("#companyName").val();
    if(companyName==''){
        var msg = "请输入公司名称！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var province_name = $("#prive").val();
    var city_name = $("#city").val();
    var county_name = $("#trxo").val();

    if($("#prive").val() == null||$("#prive").val() == "") {
        var msg ="请选择省市区";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var companyAddress = $("#companyAddress").val();
    if(companyAddress==''){
        var msg = "请输入详细地址！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var companyLegalPerson = $("#companyLegalPerson").val();
    if(companyLegalPerson==''){
        var msg = "请输入公司法人名字！";
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
    $("#validate").attr("onclick","");
    $.ajax({
        url:$("#form1").attr("action"),
        type:"post",
        dataType:"json",
        data:$("#form1").serialize(),
        success : function(res) {
            if(res.code==1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.location.href = "agentAccount.jsp";
                }, 2000);
            }else{
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 2000);
                $("#validate").attr("onclick","validateForm()");
            }
        }
    });
}
function jump() {
    window.location.href='index.jsp';
}