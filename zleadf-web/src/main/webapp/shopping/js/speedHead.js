$(function(){
    $('#cat').click(function(){ 
		if($('.cart_xi').is(':hidden')){ 
		$('.cart_xi').show(); 
		$('#xt').show();
		$('#st').hide();
		}else{ 
		$('.cart_xi').hide(); 
		$('#xt').hide();
		$('#st').show();
		}
    })
    
    $('#gen_b').click(function(){//点击a标签
		if($('.info_xione').is(':hidden')){//如果当前隐藏
		$('.info_xione').show();//那么就显示div
		}else{//否则
		$('.info_xione').hide();//就隐藏div
		}
    })
    $('#yh').click(function(){//点击a标签
		if($('.yxi').is(':hidden')){//如果当前隐藏
		$('.yxi').show();//那么就显示div
		$('#yho').hide(); 
		$('#yht').show();
		 
		}else{//否则
		$('.yxi').hide();//就隐藏div
		$('#yho').show(); 
		$('#yht').hide();
		}
		return false;
    });

    $('#ino').click(function(){//点击a标签
		if($('.info_xi').is(':hidden')){//如果当前隐藏
		$('.info_xi').show();//那么就显示div
		$('#xo').show();
		$('#so').hide();
		}else{//否则
		$('.info_xi').hide();//就隐藏div
		$('.info_xione').hide();
		$('#xo').hide();
		$('#so').show();
		}
    })
     $('#ino').click(function(){//点击a标签
		 
		$('.cart_xi').hide();//就隐藏div
		 
    })
      $('#cat').click(function(){//点击a标签
		 
		$('.info_xi').hide();//就隐藏div
		$('.info_xione').hide();
		 
    })
    
})