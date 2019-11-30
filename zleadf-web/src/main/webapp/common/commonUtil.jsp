<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.serializer.SerializerFeature" %>
<%@ page import="com.fasterxml.jackson.core.JsonFactory" %>
<%@ page import="com.fasterxml.jackson.core.JsonGenerator" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.up72.framework.dto.Result" %>
<%@ page import="com.up72.framework.util.ObjectUtils" %>
<%@ page import="com.up72.framework.util.ParamUtils" %>
<%@ page import="com.up72.framework.util.holder.ApplicationContextHolder" %>
<%@ page import="com.up72.framework.util.holder.BeanValidatorHolder" %>
<%@ page import="com.up72.framework.util.page.Order" %>
<%@ page import="com.up72.framework.util.page.Page" %>
<%@ page import="com.up72.framework.util.page.PageBounds" %>
<%@ page import="com.up72.framework.util.page.PageList" %>
<%@ page import="com.up72.security.shiro.SecurityHelper" %>
<%@ page import="com.up72.security.shiro.domain.ShiroUser" %>
<%@ page import="com.up72.sys.service.ISysRoleMenuService" %>
<%@ page import="com.up72.zx.constant.Cnst" %>
<%@ page import="com.up72.zx.utils.LoginUtil" %>
<%@ page import="org.apache.commons.lang.StringUtils, org.slf4j.Logger, org.slf4j.LoggerFactory, javax.validation.ConstraintViolation, java.io.IOException" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%!

    /********************function.jsp 公用方法************************/
    private static final void $forward(String url, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        request.getRequestDispatcher(url).forward(request, response);
    }

    private static final void $redirect(String url, HttpServletResponse response)
            throws Exception {
        response.sendRedirect(url);
    }

    private static boolean $validate(Object obj, HttpServletRequest request) {
        Set<ConstraintViolation<Object>> set = BeanValidatorHolder.validate(obj);
        if (!set.isEmpty()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            Iterator<ConstraintViolation<Object>> it = set.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> error = it.next();
                errorMap.put(error.getPropertyPath().toString(), error.getMessage());
            }
            request.setAttribute("errors", errorMap);
            return false;
        }
        return true;
    }

    private static Map<String, String> $validateErrors(Object obj, HttpServletRequest request) {
        Set<ConstraintViolation<Object>> set = BeanValidatorHolder.validate(obj);
        Map<String, String> errorMap = null;
        if (!set.isEmpty()) {
            errorMap = new HashMap<String, String>();
            Iterator<ConstraintViolation<Object>> it = set.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> error = it.next();
                errorMap.put(error.getPropertyPath().toString(), error.getMessage());
            }
        }
        return errorMap;
    }

    private static final void $setAttribute(String key, Object value, HttpServletRequest request)
            throws Exception {
        request.setAttribute(key, value);
    }

    private static final void $referer(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    private static final void $jsMessage(JspWriter out, String message,
                                         String Url) throws Exception {
        out.println("<script>alert('" + message + "')</script>");
        out.println("<script>location.href='" + Url + "';</script>");
    }

    /**
     * object对象转换为 json格式字符串
     *
     * @param object
     * @return
     * @throws IOException
     */
    @SuppressWarnings({"unchecked"})
    public String $json(Object object) throws IOException {
        if (object == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, object);
        gen.close();
        return sw.toString();
    }

    /**
     * JSON对象转换为字符串，将字符串为 Null的转换为空字符串
     * @param object
     * @return
     */
    public String $tojson(Object object) {
        if (object == null) {
            return "";
        }
        //空字段依然json化，若字符串为null自动转化为空字符串
        String jsonString = JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
        return jsonString;
    }

    /**
     * 获取参数Map
     * @param req 请求
     * @param fields 字段数组
     * @return
     */
    public Map $getParamMap(HttpServletRequest req, String[] fields) {
        Map params = new HashMap();
        for (String field : fields) {
            if (StringUtils.isBlank(field)) continue;
            String value = req.getParameter(field);
            if (value == null) continue;
            value = value.replaceAll("'", "").trim();
            if (StringUtils.isBlank(value)) continue;
            params.put(field, value);
        }
        return params;
    }

    /** 获取分页信息 */
    private static PageBounds $getPageBounds(ServletRequest request, int... pageSize) {
        int size = 15;
        if (pageSize.length > 0 && pageSize[0] > 0) {
            size = pageSize[0];
        }
        int pageNumber = $getParam(request, "pageNumber", 1);
        if (pageNumber < 1) pageNumber = 1;
        PageBounds pageBounds = new PageBounds(pageNumber, size);
        return pageBounds;
    }

    /** 获取分页信息 */
    public PageBounds getBounds(HttpServletRequest req) {
        return new PageBounds($getParam(req, "pageNumber", 1), 15);
    }


    /**
     * 处理异常(用于Result)
     * @param e Exception
     * @param result Result
     */
    private static void $handleResultExcp(Exception e, Result result) {
        result.setSuccess(false);
        String msg = e.getMessage();
        if (SysCnst.NO_PERMISSION.equals(msg)) {
            result.setMessage(msg);
            return;
        }
        if ($hasChineseCharacter(msg)) {
            result.setMessage(msg);
        } else {
            result.setMessage("系统出现异常，无法处理您的请求。");
            e.printStackTrace();
        }
    }

    /** 处理异常（用于page） */
    private void $handlePageExcp(Exception e, HttpServletRequest req, HttpServletResponse resp) {
        try {
            String msg = e.getMessage();
            if (SysCnst.NO_PERMISSION.equals(msg)) {
                $forward("/unauthorized.jsp", req, resp);
                return;
            }
            req.setAttribute("exception", e);
            req.setAttribute("requestUri", req.getRequestURI());
            $forward("/common/error.jsp", req, resp);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /** 是否包含汉字 */
    private static boolean $hasChineseCharacter(String content) {
        if (content == null) {
            return false;
        }
        return content.getBytes().length > content.length();
    }

    /** 设置成功的数据 */
    private Result $setS(Result result, Object... data) {
        result.setSuccess(true).setData(data.length == 0 ? "" : data[0]).setMessage("操作成功");
        return result;
    }

    /** 设置失败的消息，如果需要返回数据，则放进第二个可选的参数里面 */
    private Result $setF(Result result, String msg, Object... data) {
        result.setSuccess(false).setMessage(StringUtils.isBlank(msg) ? "操作失败" : msg).setData(data.length == 0 ? "" : data[0]);
        return result;
    }

    // 是否为空
    private boolean isBlank(String s) {
        return StringUtils.isBlank(s);
    }

    // 是否不为空
    private boolean isNotBlank(String s) {
        return StringUtils.isNotBlank(s);
    }


    /**
     * 私有的空方法，只为了保持引用，让其他jsp文件可以少引入一些类。
     * 同时确保这个commonUtil.jsp不会因为误敲了优化引用的快捷键(如“Ctrl + Shift + O”)而把无效的引用删除掉。
     */
    private static final void _private_remain_quote() {
        Class clazz = ArrayList.class;
        clazz = ApplicationContextHolder.class;
        clazz = ISysRoleMenuService.class;
        clazz = SecurityHelper.class;
        clazz = ShiroUser.class;
        clazz = Method.class;
        clazz = SimpleDateFormat.class;
        clazz = Order.class;
        clazz = Page.class;
        clazz = PageBounds.class;
        clazz = PageList.class;
        clazz = ParamUtils.class;
        clazz = Cnst.class;
        clazz = LoginUtil.class;
        clazz = ObjectUtils.class;
        clazz = Logger.class;
        clazz = LoggerFactory.class;
    }


%>