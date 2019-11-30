$(function(){
	init();
    changeType();
});
function getShopId() {
    
}
function checkAgent() {
    $.ajax({
        url: '../agent/isAgent',
        type: 'post',
        success: function(res) {
            if(res.code==1){
                if (res.data.isAgent){
                    $("#agentEnter").hide();
                }else{
                    $("#agentEnter").show();
                }
            }else{
                $("#agentEnter").show();
            }
        }
    })

}
function init(){
	var memberId = localStorage.getItem("memberId");
	$.ajax({
		url: '../zlead/login/memberInfo',
        type: 'post',
        data: {
        	"memberId":memberId
        },
        success: function(res) {
        	if(res.code==1){
        		$("#userId").html(res.data.member.id);
        		$(".headImg").attr('src',res.data.member.headImg);
        		$("#account").html(res.data.member.account);
        		$("#point").html(res.data.member.point);
                $("#memberType").val(res.data.member.memberType);
        	}else if(res.code==2){
				window.location.href = "login.jsp";
			}else if(res.code==510){
				window.location.href = "login.jsp";
			}
            checkAgent();
        }
	})

}

function changeType(){
    var memberType = $("#memberType").val();
    if(memberType==0){ //0是会员
		$("#type4").hide();
	}else if(memberType==1){
        $("#type1,#type2,#type3,#type5,#type6,#type7,#type8").hide();
	}
}

function gotoPurchaseOrder(){
	window.location.href = "purchaseOrder.jsp";
}

function gotoGoodsList() {
	window.location.href = "goodsQuery.jsp";
}

function gotoResetPsd(){
	window.location.href = "resetPsd.jsp";
}
function gotoEnterpriseNews(){
    window.location.href = "newsList1.jsp";
}

function gotoSellOrder(){
    window.location.href = "sellOrder.jsp";
}

function gotoAgent(){
	window.location.href = "../jsp/mobile/agent/do/applyAgent.jsp";
}

function gotoAddress(){
	window.location.href = "addressManage.jsp";
}

function gotoAllianceList(){
	window.location.href = "allianceEnter.jsp";
}

function gotoCompanyEnter(){

    window.location.href = "companyEnter.jsp";
}

function gotoAgentEnter(){
    window.location.href = "agentEnter.jsp";
}

function gotoAdsList(){
    window.location.href = "adsList.jsp";
}


function gotoSellOrder(){
	window.location.href = "sellOrder.jsp";
}

function gotoGoodsQuery(){
	window.location.href = "goodsQuery.jsp";
}


function gotoWithdraw(){
	window.location.href = "withdrawList.jsp";
}

function gotoInformationInstall() {
    $.post("../zlead/region/findMemberId",{"memberId":window.localStorage.getItem("memberId")},function (res) {
        window.location.href = "InformationInstall.jsp?sid="+res.data.id;
    })

}