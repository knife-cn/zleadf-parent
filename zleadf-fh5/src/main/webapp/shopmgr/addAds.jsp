<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <!-- 宽度设置为设备实际宽度，初始化倍数为1，最小倍数为1，最大倍数为1，用户缩放为否 -->
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
    <!-- 删除默认的苹果工具栏和菜单栏 -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- 苹果手机顶部为黑色 -->
    <meta name="apple-mobile-web-status-bar-style" content="block"/>
    <!-- 屏蔽浏览器自动识别数字为电话号码 -->
    <meta name="fromat-detecition" content="telephone=no"/>
    <title>宏古</title>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/style.css?v=1.1"/>
    <link rel="stylesheet" href="css/allianceEnter.css"/>
    <script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/config/url.js?v=1"></script>
    <script type="text/javascript" src="js/config/comn.js?v=1"></script>
    <script type="text/javascript" src="js/config/iscroll-zoom.js"></script>
    <script type="text/javascript" src="js/config/zepto.min.js"></script>
    <script type="text/javascript" src="js/control/addAds.js"></script>
    <script type="text/javascript" src="js/config/ajaxfileupload.js"></script>
</head>
<body>
<div id="errorMsg"></div>
<div class="allianceInfo">
    <div class="allianceTitle">
        <img src="img/icon-back.png" class="fl go-back-1">
        <span>添加广告</span>
    </div>
    <form id="adsForm">
        <input type="hidden" class="shopId" id="shopId" name="shopId" value="">
        <div class="alliance">
            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="title" name="title" placeholder="请输入广告标题">
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-yyzz.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="adsContent" name="adsContent"
                           placeholder="请输入广告内容">
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spxl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="hits" name="hits" placeholder="请输入浏览量">
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-diz.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="contentPath" name="contentPath"
                           placeholder="请输入内容地址">
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-yyzz.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="introduce" name="introduce"
                           placeholder="请输入广告简介">
                </div>
            </div>


            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spfl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <select class="allianceDiv-input" id="adstype" name="adstype" placeholder="请选择广告分类">
                        <option value="0">商城</option>
                        <option value="1">企业</option>
                    </select>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spfl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <select class="allianceDiv-input" id="catagory" name="catagory" placeholder="请选择广告性质">
                        <option value="0">广告</option>
                        <option value="1">轮播</option>
                    </select>
                </div>
            </div>
            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spfl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <select class="allianceDiv-input" id="channel" name="channel" placeholder="板块">
                        <option value="0">非首页</option>
                        <option value="1">首页1</option>
                        <option value="2">首页2</option>
                        <option value="3">首页3</option>
                        <option value="4">首页4</option>
                        <option value="5">首页5</option>
                    </select>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-yyzz.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="sort" name="sort" placeholder="首页显示顺序">
                </div>
            </div>


            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spfl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <select class="allianceDiv-input" id="contentType" name="contentType" placeholder="请选择广告内容">
                        <option value="0">广告图片</option>
                        <option value="1">文章</option>
                        <option value="2">商品</option>
                        <option value="3">视频</option>
                        <option value="4">外链</option>
                    </select>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spfl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <select class="allianceDiv-input" id="isTop" name="isTop" placeholder="是否置顶">
                        <option value="0">置顶</option>
                        <option value="1">不置顶</option>
                    </select>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spfl.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <select class="allianceDiv-input" id="status" name="status" placeholder="广告状态">
                        <option value="0">未发布</option>
                        <option value="1">已发布</option>
                        <option value="1">删除</option>
                    </select>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-yyzz.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="remark" name="remark" placeholder="广告备注">
                </div>
            </div>

            <div class="allianceDiv" style="height:2.5rem;">
                <img class="allianceDivImg" src="img/icon-sppic.png" alt="">
                <section class="upload-section">
                    <div class="upload-btn1"></div>
                    <input type="file" name="demo_input" id="demo_input" value="" accept="image/*"  onchange="uploadAdsImg()"/>
                    <input type="hidden" class="adsImg" id="adsImg" name="adsImg" value="">
                </section>
                <div class="img_area" id="ads_img_area">
                    <img src="" alt=""   class="img_area_img">
                </div>
            </div>
            <div class="upload-img">
            </div>
        </div>
    </form>
    <div class="validate">
        <img id="validate" src="img/btn-tijiao.png" alt="" onclick="addAds()">
    </div>
</div>
</body>
</html>
