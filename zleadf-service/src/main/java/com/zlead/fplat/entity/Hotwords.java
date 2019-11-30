package com.zlead.fplat.entity;

import java.util.Date;

public class Hotwords {
    /**
     * 字段名称: id .
     * 字段定义: t_hot_words.id
     *
     * @ET
     */
    private Integer id;

    /**
     * 字段名称: 词汇名称 .
     * 字段定义: t_hot_words.word_name
     *
     * @ET
     */
    private String wordName;

    /**
     * 字段名称: 搜索次数 .
     * 字段定义: t_hot_words.num
     *
     * @ET
     */
    private Integer num;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: t_hot_words.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 更新时间 .
     * 字段定义: t_hot_words.update_time
     *
     * @ET
     */
    private Date updateTime;

    /**
     * This method:getId
     * t_hot_words.id
     *
     * @return the value of t_hot_words.id
     *
     * @ET
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_hot_words.id
     *
     * @param id the value for t_hot_words.id
     *
     * @ET
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method:getWordName
     * t_hot_words.word_name
     *
     * @return the value of t_hot_words.word_name
     *
     * @ET
     */
    public String getWordName() {
        return wordName;
    }

    /**
     * This method:setWordName
     *  t_hot_words.word_name
     *
     * @param wordName the value for t_hot_words.word_name
     *
     * @ET
     */
    public void setWordName(String wordName) {
        this.wordName = wordName == null ? null : wordName.trim();
    }

    /**
     * This method:getNum
     * t_hot_words.num
     *
     * @return the value of t_hot_words.num
     *
     * @ET
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method:setNum
     *  t_hot_words.num
     *
     * @param num the value for t_hot_words.num
     *
     * @ET
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method:getCreateTime
     * t_hot_words.create_time
     *
     * @return the value of t_hot_words.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  t_hot_words.create_time
     *
     * @param createTime the value for t_hot_words.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getUpdateTime
     * t_hot_words.update_time
     *
     * @return the value of t_hot_words.update_time
     *
     * @ET
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method:setUpdateTime
     *  t_hot_words.update_time
     *
     * @param updateTime the value for t_hot_words.update_time
     *
     * @ET
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}