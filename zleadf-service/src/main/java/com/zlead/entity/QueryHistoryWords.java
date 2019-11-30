package com.zlead.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 搜索历史关键词
 */
public class QueryHistoryWords implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 搜索关键词
     */
    private String tWordName;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
    private Date createTime;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
    private Date updateTime;
    /**
     * 查询次数
     */
    private Integer tNum;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String gettWordName() {
        return tWordName;
    }

    public void settWordName(String tWordName) {
        this.tWordName = tWordName == null ? null : tWordName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer gettNum() {
        return tNum;
    }

    public void settNum(Integer tNum) {
        this.tNum = tNum;
    }
}