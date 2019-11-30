package com.zlead.util.page;

import java.io.Serializable;
import java.util.*;

/**
 * Created by hejie on 2015/6/6.
 */
public class Page<E> implements Serializable{

    private static final long serialVersionUID = 1088834587229906334L;

    protected List<E> result = new ArrayList<E>();

    private Pagination pagination;

    public Page() {}

    public Page(Collection<? extends E> c) {
        if(c != null){
            result.addAll(c);
        }
    }


    public Page(Collection<? extends E> c,Pagination p) {
        if(c != null){
            result.addAll(c);
        }
        this.pagination = p;
    }

    public Page(Pagination p) {
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

    public Iterator<E> iterator() {
        return (Iterator<E>) (result == null ? Collections.emptyList().iterator() : result.iterator());
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public boolean hasContent(){
        return !result.isEmpty();
    }
}
