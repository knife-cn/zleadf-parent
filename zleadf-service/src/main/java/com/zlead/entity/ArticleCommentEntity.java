package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章评论回复
 *
 * @author yxl
 * @date 2018-11-20 10::08
 */
public class ArticleCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private int id;

    /**
     * 文章表id
     */
    private int articleId;

    /**
     * memberid
     */
    private long memberId;

    /**
     * id bigint(20
     */
    private int parentCommentid;
    
    
    private int shopId;

    /**
     * 赞数
     */
    private int likes;

    /**
     * 回复内容
     */
    private String replyMsg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 评论日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 是否审核0未审核1审核通过2审核失败
     */
    private int isAudit;


    /**
     * 用户名
     */
    private String username;


    /**
     * 头像
     */
    private String headImg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getParentCommentid() {
        return parentCommentid;
    }

    public void setParentCommentid(int parentCommentid) {
        this.parentCommentid = parentCommentid;
    }
    
    

    public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(int isAudit) {
        this.isAudit = isAudit;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}