$(function(){
	newsDetails();
})
function newsDetails(){
	var id=appSupport.cm.queryString("id");
	var html='';
	$.ajax({
		url: '../article/detailNews',
        type: 'post',
        data: {
        	"id":id
        },
        success: function(res) {
        	  console.info(res.data);
        	  for(var i=0;i<res.data.length;i++){ 
        		html+='<div class="newsDetails">';
        		html+='<div class="title">';
        		html+='<span>'+res.data[i].seoTitle+'</span>';
        		html+='</div>';
        		html+='<div class="newsImg">';
        		html+='<img src="http://116.62.124.171'+res.data[i].thumbnail+'" alt="">';
        		html+='</div>';
        		html+='<div class="detail-title">';
        		html+='<span>'+res.data[i].title+'</span>';
        		html+='</div>';
        		html+='<div class="detail">';
        		html+='<p>'+res.data[i].content+'</p>';
        		html+='</div>';
        		html+='</div>';
        		}
        		$(".news").html(html);
        }
	})
}
