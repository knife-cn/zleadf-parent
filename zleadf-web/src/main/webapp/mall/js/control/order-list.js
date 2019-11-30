$(function () {
  var urlParams = appSupport.cm.paramsFormat();
  var orderState = urlParams.t || 0;
  var pageNo = 1;

  //获取订单列表
  function getOrderList() {
    var url = baseUrl.formal + 'order/order_manage.html';
    var data = {
      flag: orderState, //0全部 1:待付款 2:待发货 3:待收货
      userId: localStorage.getItem("userId"),
      page: pageNo
    };
    appSupport.cm.postAjaxFunction(url, data, function (res) {
      if(res.code == 1) {
        var _html = '';
        for(var i = 0; i < res.data.length; i++) {
          _html += '<li>'+
            '<div class="label-box f15">'+
              '卖家已付款'+
            '</div>'+
            '<div class="product-box f13">'+
              '<div class="product-img">'+
                '<img class="product-image" width="84" height="84" src="http://placeholder.qiniudn.com/168x168" alt="">'+
                '<div class="product-text">'+
                  '<p class="pname">黛宜菲姜美人符文按摩修复油黛宜菲姜美人符文按摩修复油</p>'+
                  '<p class="prop">化妆品净含量：120ml</p>'+
                  '<p class="price">'+
                    '<span class="sale-price">￥120</span>'+
                    '<span class="market-price">￥120</span>'+
                    '<span class="num">X1</span>'+
                  '</p>'+
                '</div>'+
              '</div>'+
              '<div class="product-value text-right f13">'+
                '共一件商品 合计：￥120.00（含运费0.00）'+
              '</div>'+
              '<div class="text-right pb15">'+
                '<button class="btn border-btn">查看更多</button>'+
                '<button class="btn border-btn">查看更多</button>'+
                '<button class="btn border-btn red">查看更多</button>'+
              '</div>'+
            '</div>'+
          '</li>'
        }
        $('#list-container').append(_html);

        if(pageNo == 1 && res.data.length == 0) {
          $('.nothing').show();
        }
      }else {
        appSupport.cm.errorMessageShow('#errorMsg', res.message);
        setTimeout(function() {
          appSupport.cm.errorMessageHide('#errorMsg');
        }, 500);
      }
    });
  }
  getOrderList();

  /* tab切换 */
  var $navTabs = $(".frue-part");
  $navTabs.click(function(){
    $navTabs.removeClass('active');
    $(this).addClass('active')

    var index = $(this).index();
    orderState = index;
    pageNo = 1;
    $('#list-container').empty();
    getOrderList();

    //修改地址栏参数
    var urlParams = appSupport.cm.paramsFormat();
    urlParams.t = index;
    var url = location.pathname + '?' + $.param(urlParams);
    window.history.replaceState(null, "", url);
  });

})
