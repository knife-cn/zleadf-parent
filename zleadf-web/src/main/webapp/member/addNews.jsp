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
    <%--<link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <link rel="stylesheet" href="css/news.css"/>
    <link rel="stylesheet" href="css/unslider.css"/>
    <link rel="stylesheet" href="css/unslider-dots.css"/>--%>

    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/style.css?v=1.1"/>
    <link rel="stylesheet" href="css/allianceEnter.css"/>
    <script type="text/javascript" src="js/library/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/library/banner.js"></script>
    <script type="text/javascript" src="js/config/url.js?v=1"></script>
    <script type="text/javascript" src="js/config/comn.js?v=1.1"></script>
    <script type="text/javascript" src="js/control/addNews.js"></script>
    <script type="text/javascript" src="js/library/unslider-min.js"></script>
    <script type="text/javascript" src="js/config/ajaxfileupload.js"></script>
    <script type="text/javascript" src="js/config/dropload.min.js?v=1.1"></script>
    <title>宏古</title>
</head>
<body>
<div id="errorMsg"></div>
<div class="indexInfo">
    <div class="allianceTitle">
        <img src="img/icon-back.png" class="fl go-back-1">
        <span>发布新闻</span>
    </div>

    <form id="addNews" method="post">
        <div class="alliance">
            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="title" name="title" placeholder="请填写标题"/>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <%--<input class="allianceDiv-input" type="text" id="categoryid" name="categoryid"
                           placeholder="请填写分类编号"/>--%>
                    <select class="allianceDiv-input" name="categoryid" id="categoryid">
                    <option value="0">行业介绍</option>
                    <option value="1">关于我们</option>
                    <option value="2">联系我们</option>
                    <option value="3">企业新闻</option>
                </select>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <%--<img class="quan" src="img/img-quan.png" alt="">--%>
                    <textarea class="allianceDiv-input" name="content" id="content" placeholder="请填写新闻内容"></textarea>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">

                    <input class="allianceDiv-input" type="text" id="hits" name="hits" placeholder="请填写点击数"/>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="author" name="author" placeholder="请填写作者"/>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">

                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="seoTitle" name="seoTitle"
                           placeholder="请输入seo标题"/>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">

                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="seoKeywords" name="seoKeywords"
                           placeholder="请输入seo关键词"/>
                </div>
            </div>

            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">

                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="seoDescription" name="seoDescription"
                           placeholder="请输入seo描述"/>
                </div>
            </div>

            <%--<div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">

                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">
                    <input class="allianceDiv-input" type="text" id="shopId" name="shopId"
                           placeholder="请输入企业id"/>
                </div>
            </div>
--%>
            <div class="allianceDiv">
                <img class="allianceDivImg" src="img/icon-spmc.png" alt="">
                <div class="allianceDivInput">
                    <img class="quan" src="img/img-quan.png" alt="">

                    <div class="form-group">
                        <select class="allianceDiv-input" name="isAudit" id="isAudit">
                            <option value="0">未审核</option>
                            <option value="1">审核成功</option>
                            <option value="2">审核失败</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="allianceDiv" style="margin-bottom:0.5rem;" id="isTop">
                <span>是否显示首页</span>
                <div class="allianceDiv-chose">
                    <input class="allianceDiv-chose-radio" type="radio" value="1" name="isTop">
                    <label>是</label>
                </div>
                <div class="allianceDiv-chose">
                    <input class="allianceDiv-chose-radio" type="radio" value="0" name="isTop">
                    <label>否</label>
                </div>
            </div>
            <div class="allianceDiv" style="margin-bottom:0.5rem;" id="isPublication">
                <span>是否发布</span>
                <div class="allianceDiv-chose">
                    <input class="allianceDiv-chose-radio" type="radio" value="1" name="isPublication">
                    <label>已发布</label>
                </div>
                <div class="allianceDiv-chose">
                    <input class="allianceDiv-chose-radio" type="radio" value="0" name="isPublication">
                    <label>未发布</label>
                </div>
            </div>

            <div class="allianceDiv" style="margin-bottom:0.5rem;" id="publishType">
                <span>发布类型</span>
                <div class="allianceDiv-chose">
                    <input class="allianceDiv-chose-radio" type="radio" value="0" name="publishType">
                    <label>企业发布</label>
                </div>
                <div class="allianceDiv-chose">
                    <input class="allianceDiv-chose-radio" type="radio" value="1" name="publishType">
                    <label>平台发布</label>
                </div>

            </div>
            <%--<div class="allianceDiv" style="height:2.5rem;">--%>
                <%--<img class="allianceDivImg" src="img/icon-sppic.png" alt="">--%>
                <%--&lt;%&ndash;<span>缩略图</span>&ndash;%&gt;--%>
                <%--<div class="uploadDiv">--%>
                    <%--<input type="file" name="demo_input" id="demo_input" accept="image/*" onchange="uploadImg()"/>--%>
                    <%--<input type="hidden" class="thumbnail" name="thumbnail" id="thumbnail" value="">--%>
                <%--</div>--%>
                <%--<div class="img_area" id="img_area">--%>
                    <%--<img src="" alt="" class="img_area_img">--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="allianceDiv" style="height:2.5rem;">
                <img class="allianceDivImg" src="img/icon-sppic.png" alt="">
                <input type="hidden" class="thumbnail" id="thumbnail" name="thumbnail" value="">
                <section class="upload-section">
                    <div class="upload-btn1"></div>
                    <input type="file" name="demo_input" id="demo_input" value="" accept="image/*"
                           onchange="uploadImg()"/>
                </section>
                <div class="img_area" id="img_area">
                    <img class="img_area_img" alt="">
                </div>
            </div>
        </div>
    </form>
    <div class="validate">
        <img id="validate" src="img/btn-queren.png" alt="" onclick="addNews()"/>
    </div>
</div>
</body>
</html>
