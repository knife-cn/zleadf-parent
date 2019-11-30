$(function(){
	init();
});

function init(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	var memberId = localStorage.getItem("memberId");
    var baseUrl=localStorage.getItem("weburl");
    $.ajax({
		url: '/zlead/login/memberInfo',
        type: 'post',
        data: {
        	"memberId":memberId
        },
        success: function(res) {
        	if(res.code==1){
        		$("#userId").html(res.data.member.id);
        		$(".headImg>img").attr('src',"http://116.62.124.171/group1/"+res.data.member.headImg);
        		$("#account").html(res.data.member.account);
        		$("#point").html(res.data.member.point);
                $("#memberType").val(res.data.member.memberType);
        	}else if(res.code==2){
				window.location.href = "../member/login.jsp?shopId="+shopId;
			}else if(res.code==510){
				window.location.href = "../member/login.jsp?shopId="+shopId;
			}
            // checkAgent();

        }
	})

}

function gotoPurchaseOrder(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	window.location.href = "../member/order.jsp?shopId="+shopId;
}

function gotoResetPsd(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	window.location.href = "../member/resetPsd.jsp?shopId="+shopId;
}
function gotoBusinessCard(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    var baseUrl=localStorage.getItem("weburl");
    window.location.href = baseUrl+"/company/businessCard.action?shopId="+shopId;
}

function gotoAgent(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	window.location.href = "../jsp/mobile/agent/do/applyAgent.jsp?shopId="+shopId;
}

function gotoAddress(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	window.location.href = "../member/addressManage.jsp?shopId="+shopId;
}

function gotoAllianceList(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	window.location.href = "../member/allianceEnter.jsp?shopId="+shopId;
}

function gotoCompanyEnter(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/companyEnter.jsp?shopId="+shopId;
}

function gotoAgentEnter(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/agentEnter.jsp?shopId="+shopId;
}

/*function gotoAllianceList(){
	window.location.href = "adsList.jsp";
}*/

function gotoSellOrder(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/sellOrder.jsp?shopId="+shopId;
}

function gotoGoodsQuery(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/goodsQuery.jsp?shopId="+shopId;
}
function gotoAdsList(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/adsList.jsp?shopId="+shopId;
}
function gotoWithdraw(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
	window.location.href = "../member/withdrawList.jsp?shopId="+shopId;
}

function gotoShopingCar(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    var baseUrl=localStorage.getItem("weburl");
    window.location.href = baseUrl+"/member/shopCar.action?shopId="+shopId;
    // window.location.href = "..//member/shopCar.jsp?shopId="+shopId;
}
function gotoVerify(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/verify.jsp?shopId="+shopId;
}
function gotoInformationInstall() {
    // $.post("../zlead/region/findMemberId",{"memberId":window.localStorage.getItem("memberId")},function (res) {
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/InformationInstall.jsp?shopId="+shopId
    // })
}

function gotoShare(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/share.jsp?shopId="+shopId
}
function gotoEnterpriseNews(){
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/newsList1.jsp?shopId="+shopId;
}

function gotoLogOut() {
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = "../member/logout.jsp?shopId="+shopId;
}

function personal() {
    var baseUrl=localStorage.getItem("weburl");
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId = localStorage.getItem("shopId");
    }
    window.location.href = baseUrl+"/member/personalAccount.jsp?shopId=" + shopId;
}

function login() {
    var baseUrl=localStorage.getItem("weburl");
    window.location.href = baseUrl+"/member/login.jsp"
}

function shopCarHide() {
    var shopId=appSupport.cm.queryString("shopId");
    if (!shopId){
        shopId=$("#shopId").val();
    }
    if (!shopId) {
        $(".shopCar").hide();
    }
}