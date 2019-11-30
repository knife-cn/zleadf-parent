<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<up72:override name="head">
    <title>非法提交</title>
</up72:override>
<up72:override name="content">
    <div style="height:250px; text-align:center; margin-top:50px;">
        <%
            String path = request.getContextPath();
            if (path.equals("")) {
                path = "/";
            }
        %>
        您已经非法提交或重复提交，
        <c:choose>
            <c:when test="${referer != null}">
                <script type="text/javascript">
                    var timer = 3;
                    var interval = window.setInterval(function () {
                        if (timer <= 1) {
                            window.clearInterval(interval);
                            window.location.href = "${referer}";
                        } else {
                            timer--;
                            $("#timer_span").html(timer);
                        }
                    }, 1000);
                </script>
                <span style="color:#FF0000" id="timer_span">3</span>秒后自动返回到上一页。<br />
                如果您的页面没有反映，请点击 <a style="color:#FF0000" href="${referer}">返回上一页</a> <a style="color:#FF0000"
                                                                                       href="<%=path%>">返回首页</a>
            </c:when>
            <c:otherwise>
                <script type="text/javascript">
                    var timer = 3;
                    var interval = window.setInterval(function () {
                        if (timer <= 1) {
                            window.clearInterval(interval);
                            window.location.href = "<%=path%>";
                        } else {
                            timer--;
                            $("#timer_span").html(timer);
                        }
                    }, 1000);
                </script>
                <span style="color:#FF0000" id="timer_span">3</span>秒后自动返回到首页。<br />
                如果您的页面没有反映，请点击 <a style="color:#FF0000" href="<%=path%>">返回首页</a> <a style="color:#FF0000"
                                                                                     href="${ctx}/admin/login">管理员登陆</a>
            </c:otherwise>
        </c:choose>
    </div>
</up72:override>
<jsp:include page="/admin/adminBase"/>