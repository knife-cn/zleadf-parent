package com.zlead.common;

import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;

public class PageResponse extends JsonResult {
    private int pageNumber;//当前页数
    private int pageSize;//分页条数
    private long count;//总条数

    public PageResponse() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setPage(PageBounds page){
        this.pageNumber = page.getPageNumber();
        this.pageSize = page.getPageSize();
    }
}
