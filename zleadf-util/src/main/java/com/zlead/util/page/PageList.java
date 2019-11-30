package com.zlead.util.page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * 包含“分页”信息的List
 * 
 * <p>要得到总页数请使用 getPagination().getTotalPages();</p>
 * 
 * @author up72
 */
public class PageList<E> extends ArrayList<E> {

    private static final long serialVersionUID = -2298511046987733341L;

    private Pagination pagination;

    public PageList() {}
    
	public PageList(Collection<? extends E> c) {
		super(c);
	}


	public PageList(Collection<? extends E> c,Pagination p) {
        super(c);
        this.pagination = p;
    }

    public PageList(Pagination p) {
        this.pagination = p;
    }


	/**
	 * 得到分页器，通过Paginator可以得到总页数等值
	 * @return
	 */
	public Pagination getPagination() {
		return this.pagination;
	}


    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
