package com.zlead.util.page;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  分页查询对象（用于Page集合查询）
 *  @author up72
 */
public class PageBounds extends RowBounds implements Serializable {
    private static final long serialVersionUID = 2622261151961385068L;

    public final static int NO_PAGE = 1;
    /** 页号 */
    protected int pageNumber = NO_PAGE;
    /** 分页大小 */
    protected int pageSize;
    /** 分页排序信息 */
    protected List<Order> orders = new ArrayList<Order>();
    /** 结果集是否包含TotalCount */
    protected boolean containsTotalCount;

    protected Boolean asyncTotalCount;

    public PageBounds(){
        containsTotalCount = true;
    }

    public PageBounds(RowBounds rowBounds) {
        if(rowBounds instanceof PageBounds){
            PageBounds bounds = (PageBounds)rowBounds;
            this.pageNumber = bounds.pageNumber;
            this.pageSize = bounds.pageSize;
            this.orders = bounds.orders;
            this.containsTotalCount = bounds.containsTotalCount;
            this.asyncTotalCount = bounds.asyncTotalCount;
        }else{
            this.pageNumber = (rowBounds.getOffset()/rowBounds.getLimit())+1;
            this.pageSize = rowBounds.getLimit();
        }

    }

    /**
     * Query TOP N, default containsTotalCount = false
     * @param pageSize
     */
    public PageBounds(int pageSize) {
        this.pageSize = pageSize;
        this.containsTotalCount = false;
    }

    /**
     * 分页查询对象
     * @param pageNumber 页号
     * @param pageSize 分页大小
     */
    public PageBounds(int pageNumber, int pageSize) {
        this(pageNumber, pageSize, new ArrayList<Order>(), true);
    }

    /**
     * 分页查询对象
     * @param pageNumber 页号
     * @param limit 分页大小
     * @param containsTotalCount 结果集是否包含TotalCount
     */
    public PageBounds(int pageNumber, int limit, boolean containsTotalCount) {
        this(pageNumber, limit, new ArrayList<Order>(), containsTotalCount);
    }

    /**
     * Just sorting, default containsTotalCount = false
     * @param orders
     */
    public PageBounds(List<Order> orders) {
        this(NO_PAGE, NO_ROW_LIMIT,orders ,false);
    }

    /**
     * Just sorting, default containsTotalCount = false
     * @param order
     */
    public PageBounds(Order... order) {
        this(NO_PAGE, NO_ROW_LIMIT,order);
        this.containsTotalCount = false;
    }

    public PageBounds(int pageNumber, int pageSize, Order... order) {
        this(pageNumber, pageSize, Arrays.asList(order), true);
    }

    public PageBounds(int pageNumber, int pageSize, List<Order> orders) {
        this(pageNumber, pageSize, orders, true);
    }

    public PageBounds(int pageNumber, int pageSize, List<Order> orders, boolean containsTotalCount) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orders = orders;
        this.containsTotalCount = containsTotalCount;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getLimit() {
        return pageSize;
    }

    public boolean isContainsTotalCount() {
        return containsTotalCount;
    }

    public void setContainsTotalCount(boolean containsTotalCount) {
        this.containsTotalCount = containsTotalCount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Boolean getAsyncTotalCount() {
        return asyncTotalCount;
    }

    public void setAsyncTotalCount(Boolean asyncTotalCount) {
        this.asyncTotalCount = asyncTotalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getOffset() {
        if(pageNumber >= 1){
            return (pageNumber-1) * pageSize;
        }
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageBounds{");
        sb.append("pageNumber=").append(pageNumber);
        sb.append(", pageSize=").append(this.getLimit());
        sb.append(", orders=").append(orders);
        sb.append(", containsTotalCount=").append(containsTotalCount);
        sb.append(", asyncTotalCount=").append(asyncTotalCount);
        sb.append('}');
        return sb.toString();
    }


}