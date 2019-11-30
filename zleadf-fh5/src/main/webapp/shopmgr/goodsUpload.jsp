<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
		<!-- 宽度设置为设备实际宽度，初始化倍数为1，最小倍数为1，最大倍数为1，用户缩放为否 -->
		<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
		<!-- 删除默认的苹果工具栏和菜单栏 -->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!-- 苹果手机顶部为黑色 -->
		<meta name="apple-mobile-web-status-bar-style" content="block" />
		<!-- 屏蔽浏览器自动识别数字为电话号码 -->
		<meta name="fromat-detecition" content="telephone=no" />
		<title>宏古</title>
		<link rel="stylesheet" href="./css/common.css"/>
		<link rel="stylesheet" href="./css/style.css?v=1.1"/>
		<link rel="stylesheet" href="./css/allianceEnter.css"/>
		<script type="text/javascript" src="./js/library/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="./js/config/url.js?v=1"></script>
		<script type="text/javascript" src="./js/config/comn.js?v=1"></script>
		<script type="text/javascript" src="./js/config/iscroll-zoom.js"></script>
		<script type="text/javascript" src="./js/config/zepto.min.js"></script>
		<script type="text/javascript" src="./js/control/goodsUpload.js"></script>
		<script type="text/javascript" src="./js/config/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/library/upload.js"></script>

</head>
<body>
<div id="errorMsg"></div>
<div class="allianceInfo">
	<div class="allianceTitle">
		<img src="img/icon-back.png" class="fl go-back-1" >
		<span>商品上传</span>
	</div>
	<div class="alliance">
	    <div class="allianceDiv">
			<img class="allianceDivImg" src="img/icon-spmc.png" alt="">
			<div class="allianceDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="allianceDiv-input" type="text" id="fullName" name="fullName" placeholder="请输入商品名称">
			</div>
		</div>
		<div class="allianceDiv">
			<img class="allianceDivImg" src="img/icon-spfl.png" alt="">
			<div class="allianceDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<select class="allianceDiv-input" id="catId" name="catId">
				</select>
			</div>
		</div>
		<div class="allianceDiv">
			<img class="allianceDivImg" src="img/icon-spjg.png" alt="">
			<div class="allianceDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="allianceDiv-input" type="text" id="price" name="price" placeholder="请输入会员价格">
			</div>
		</div>
        <div class="allianceDiv">
            <img class="allianceDivImg" src="img/icon-spjg.png" alt="">
            <div class="allianceDivInput">
                <img class="quan" src="img/img-quan.png" alt="">
                <input class="allianceDiv-input" type="text" id="marketPrice" name="marketPrice" placeholder="请输入市场价格">
            </div>
        </div>
		<div class="allianceDiv">
			<img class="allianceDivImg" src="img/icon-spkc.png" alt="">
			<div class="allianceDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="allianceDiv-input" type="text" id="stock" name="stock" placeholder="请输入商品库存">
			</div>
		</div>
		<div class="allianceDiv">
			<img class="allianceDivImg" src="img/icon-spxl.png" alt="">
			<div class="allianceDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="allianceDiv-input" type="text" id="salesNum" name="salesNum" placeholder="商品销量">
			</div>
		</div>
		<div class="allianceDiv">
			<img class="allianceDivImg" src="img/icon-sphh.png" alt="">
			<div class="allianceDivInput">
				<img class="quan" src="img/img-quan.png" alt="">
				<input class="allianceDiv-input" type="text" id="itemNumber" name="itemNumber" placeholder="请输入商品货号">
			</div>
		</div>
		<div class="allianceDiv" style="height:auto">
			<img class="allianceDivImg" src="img/icon-sppic.png" alt="">
			<input type="hidden" class="firstImg" id="firstImg" name="firstImg" value="">
			<input type="hidden" class="imgs" id="imgs" name="imgs" value="">
			<div class="upload-box">
				<div class="image-box clear" id="imgbox">
					<section class="upload-section">
						<div class="upload-btn"></div>
						<input type="file" name="demo_input" id="demo_input1" value=""
							   accept="image/jpg,image/jpeg,image/png,image/bmp" multiple="multiple"/>
						<%--<input class="allianceDiv-input" type="text" id="contactWeixin" name="contactWeixin" placeholder="请输入微信号">--%>
					</section>
				</div>
			</div>
		</div>
		<div class="upload-img"></div>
		<div class="allianceDiv" style="margin-bottom:0.5rem;">
			<span>是否上架</span>
			<div class="allianceDiv-chose">
				<input class="allianceDiv-chose-radio" type="radio" value="1" name="isMarketable">
				<label>立即上架</label>
			</div>
			<div class="allianceDiv-chose">
				<input class="allianceDiv-chose-radio" type="radio" value="2" name="isMarketable">
				<label>暂不上架</label>
			</div>
		</div>
		<!-- <div class="allianceDiv" style="margin-bottom:0.5rem;">
			<span>产品详情</span>
			<div class="allianceDiv-chose">
				<input class="allianceDiv-chose-radio" type="radio" value="1" name="no">
				<label>显示</label>
			</div>
			<div class="allianceDiv-chose">
				<input class="allianceDiv-chose-radio" type="radio" value="2" name="no">
				<label>不显示</label>
			</div>
		</div> -->
	</div>
	<div class="validate">
		<img id="validate" src="img/btn-tijiao.png" alt="" onclick="validateForm()">
	</div>
</div>
</body>
<script>
    $("#demo_input1").ajaxImageUpload({
        url: '../ajeasy/fdfs/uploading', //上传的服务器地址
        data: {name: 'name'},
        maxNum: 9, //允许上传图片数量
        zoom: true, //允许上传图片点击放大
        allowType: ["gif", "jpeg", "jpg", "bmp", 'png'], //允许上传图片的类型
        maxSize: 2, //允许上传图片的最大尺寸，单位M
        success: function (res) {
            console.log(res);
            if (!$("#firstImg").val()){
                $("#firstImg").val(res.data);
			}
			if(!$("#imgs").val()){
                $("#imgs").val(res.data);
            }else {
                $("#imgs").val($("#imgs").val() + ","+ res.data);
            }
        },
        error: function (e) {
            console.log(e);
        }
    });
</script>
</html>