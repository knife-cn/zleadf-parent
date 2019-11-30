package com.zlead.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.zlead.util.page.PageBounds;

public class PageUtils {	
    /** 获取分页信息 */
    public static PageBounds getPageBounds(ServletRequest request, int... pageSize) {
        int size = 15;
        if (pageSize.length > 0 && pageSize[0] > 0) {
            size = pageSize[0];
        }
        int pageNumber = ToolsUtils.getParam(request, "pageNumber", 1);
        if (pageNumber < 1) pageNumber = 1;
        PageBounds pageBounds = new PageBounds(pageNumber, size);
        return pageBounds;
    }

    /** 获取分页信息 */
    public PageBounds getBounds(HttpServletRequest req) {
        return new PageBounds(ToolsUtils.getParam(req, "pageNumber", 1), 15);
    }
}
