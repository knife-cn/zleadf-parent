package com.zlead.entity.vo;

public class OrderCountVo {
    private Integer status;
    private Integer ordercnt;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrdercnt() {
        return ordercnt;
    }

    public void setOrdercnt(Integer ordercnt) {
        this.ordercnt = ordercnt;
    }

    /*
    //private Integer monthCnt;
    //private Integer totalCnt;
    暂时注释掉，功能稳定后可直接删除，2019-02-27 喻聪聪
    public Integer getMonthCnt() {
        return monthCnt;
    }

    public void setMonthCnt(Integer monthCnt) {
        this.monthCnt = monthCnt;
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }*/
}
