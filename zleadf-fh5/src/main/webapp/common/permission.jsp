<%@ page import="com.up72.sys.constant.SysCnst" %>
<%@ page import="org.slf4j.Logger, com.up72.sys.rpc.service.ISysPermRpcService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!

    private static Logger loggerPermission = org.slf4j.LoggerFactory.getLogger("权限检查common/permission.jsp");

    /**
     * 检查权限
     * @param req HttpServletRequest请求
     * @throws Exception 当用户没有登录、或者用户没有权限时抛出异常
     */
    private static void $checkPermission(HttpServletRequest req) throws Exception {
        if (!$hasPerm(req)) {
            throw new Exception(SysCnst.NO_PERMISSION);
        }
    }

    /**
     * 检查权限
     * @param permission 权限路径，如：jsp/sysConfig/impl/topMenu/do/doEditOrDel.jsp
     * @throws Exception
     */
    private static void $checkPermission(String permission) throws Exception {
        if (!$hasPerm(permission)) {
            throw new Exception(SysCnst.NO_PERMISSION);
        }
    }

    /**
     * 是否拥有权限，先判断用户是否登录，根据请求的jsp来进行菜单权限的验证或者操作权限的验证
     * @param req 请求
     * @return
     */
    private static boolean $hasPerm(HttpServletRequest req) {
        try {
            ShiroUser user = SecurityHelper.getLoginUser();
            if (user == null) {
                return false;
            }
            ISysPermRpcService sysPermRpcService = (ISysPermRpcService) ApplicationContextHolder.getBean("sysPermRpcService");
            String uri = req.getRequestURI();
            uri = uri.replaceAll("\\\\", "/") // 替换反斜杠
                    .replaceFirst(req.getContextPath(), "") // 去掉上下文路径
                    .replaceAll("/\\./","/") // 替换/./为/，否则jetty会报错
                    .replaceAll("\\.jsp", ""); // 删除后缀
            uri = uri.substring(1);
            String permissionCode = uri.replaceAll("/", "_"); // 得到权限码
            boolean hasPermission = sysPermRpcService.hasPermission(user.getRoleId(), permissionCode);
            return hasPermission;
        } catch (Exception e) {
            loggerPermission.error("检查权限异常",e);
            return false;
        }
    }

    /**
     * 是否拥有权限，先判断用户是否登录，根据permission参数来进行菜单权限的验证或者操作权限的验证
     * @param permission 权限
     * @return
     */
    private static boolean $hasPerm(String permission) {
        try {
            if(StringUtils.isBlank(permission)) {
                return false;
            }
            ShiroUser user = SecurityHelper.getLoginUser();
            if (user == null) {
                return false;
            }
            ISysPermRpcService sysPermRpcService = (ISysPermRpcService) ApplicationContextHolder.getBean("sysPermRpcService");
            String permissionCode = permission.replaceAll("\\.jsp", "").replaceAll("/", "_"); // 得到权限码
            boolean hasPermission = sysPermRpcService.hasPermission(user.getRoleId(), permissionCode);
            return hasPermission;
        } catch (Exception e) {
            loggerPermission.error("检查权限异常",e);
            return false;
        }
    }
%>