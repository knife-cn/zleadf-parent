$(function() {
	var memberId = localStorage.getItem("memberId");
	init();
	/*if(memberId == null) {
		window.location.href = "login.jsp";
	} else {
		init();
	}*/
})

function init(){
	var html="";
	var memberId = localStorage.getItem("memberId");
    var baseUrl=localStorage.getItem("weburl");
    $.ajax({
		url: baseUrl+'/zlead/tmemberaddress/getAllAddress',
        type: 'post',
        data: {
       	 'memberId':memberId
        },
        success: function(res) {
        	if(res.data.length>0){
        		for(var i=0;i<res.data.length;i++){
        			html+="<li>";
        			html+="<p class='InfoList-p1'>";
        			html+="<img class='addressImg' src='img/icon-name.png' alt=''>";
        			html+="<span class='InfoList-span memberName' id='memberName'>"+res.data[i].memberName+"</span>";
        			html+="</p>";
        			html+="<p class='InfoList-p1'>";
        			html+="<img class='addressImg' src='img/icon-phone.png' alt=''>";
        			html+="<input type='hidden' class='provinceName' value='"+res.data[i].provinceName+"'>";
        			html+="<input type='hidden' class='cityName' value='"+res.data[i].cityName+"'>";
        			html+="<input type='hidden' class='countyName' value='"+res.data[i].countyName+"'>";
        			html+="<input type='hidden' class='detailAddressInfo' value='"+res.data[i].detailAddress+"'>";
        			html+="<span class='InfoList-span' id='detailAddress'>"+res.data[i].provinceName+""+res.data[i].cityName+""+res.data[i].countyName+""+res.data[i].detailAddress+"</span>";
        			html+="</p>";
        			html+="<p class='InfoList-p1'>";	
        			html+="<img class='addressImg' src='img/icon-dz.png' alt=''>";	
        			html+="<span class='InfoList-span phone' id='phone'>"+res.data[i].phone+"</span>";
        			html+="</p>";
        			html+="<div class='change-div'>";
        			html+="<input type='hidden' class='isDefault' value='"+res.data[i].isDefault+"'>";
        			if(res.data[i].isDefault ==1){
        				html+="<input name='id' type='radio' value='"+res.data[i].id+"' checked='checked' onclick='setDefault(value)' >";
        			}else{
        				html+="<input name='id' type='radio' value='"+res.data[i].id+"' onclick='setDefault(value)' >";
        			}
        			html+="<span>默认地址</span>";
        			html+="<img class='edit' src='img/icon-edit.png' alt='' onclick='edit(\""+res.data[i].id+"\","+i+")'>";
        			html+="<img class='del' src='img/icon-shanchu.png' alt='' onclick='del(\""+res.data[i].id+"\","+i+")'>";	
        			html+="</div>";	
        			html+="</li>";
        		}
        		$("#InfoList").html(html);
        	}	
        }
	})
}
	
function edit(id,index){
	var addressId=id;
	var provinceName = $(".provinceName").eq(index).val();
	var cityName =$(".cityName").eq(index).val();
	var countyName =$(".countyName").eq(index).val();
	var memberName = $(".memberName").eq(index).text();
	var phone = $(".phone").eq(index).text();
	var isDefault=$(".isDefault").eq(index).val();
	var detailAddress = $(".detailAddressInfo").eq(index).val();
	window.location.href = "addAddress.jsp?memberName=" + memberName + "&phone=" + phone +"&isDefault=" + isDefault +"&addressId=" + addressId + "&detailAddress=" + detailAddress + "&provinceName=" + provinceName + "&cityName=" + cityName + "&countyName=" + countyName + "&type="+2;
}

function del(id,index){
    var addressId = id;
	var baseUrl=localStorage.getItem("weburl");
    $.ajax({
		url: baseUrl+'/zlead/tmemberaddress/deleteMemberAddress',
        type: 'post',
        data: {
       	 'addressId':addressId
        },
        success: function(res) {
        	if(res.code==1){
        		var msg=res.message;
        		appSupport.cm.errorMessageShow(errorMsg, msg);
    			setTimeout(function() {
    				appSupport.cm.errorMessageHide(errorMsg);
    				init();
    			}, 1000);
        	}
        }
    })
}
	
function setDefault(){
	var memberId = localStorage.getItem("memberId"); 
    var addressId = $('input[name="id"]:checked').val();
	var baseUrl=localStorage.getItem("weburl");
    $.ajax({
		url: baseUrl+'/zlead/tmemberaddress/setDefaultAddress',
        type: 'post',
        data: {
       	 'memberId':memberId,
       	 'addressId':addressId
        },
        success: function(res) {
        	if(res.code==1){
        		var msg=res.message;
        		appSupport.cm.errorMessageShow(errorMsg, msg);
    			setTimeout(function() {
    				appSupport.cm.errorMessageHide(errorMsg);
    			}, 1000);
        	}
        }
    })
}

function gotoAdd(){
	window.location.href = "addAddress.jsp"
}
function goBack(){
    window.history.back();
}