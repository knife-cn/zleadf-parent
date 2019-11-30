package com.zlead.fplat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/22.
 * @Desoription TODO  凭证
 */
public class    Voucher {

    private int id;

    /**凭证金额 */
    private BigDecimal amount;

    /**上传图片路径 */
    private String img;

    /**付款人姓名 */
    private String payer;

    /**付款人手机号 */
    private String payerPhone;

    /**工厂Id */
    private int shopId;

    /**登陆人memberId */
    private Long memberId;
    
    /** 购买代理公司编号 */
    private Long buyerCorpId;


    /**凭证类型 1:线下;2:线上*/
    private int payType;

    /**支付时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    /**上传凭证会员id */
    private int uploadMember;

    /**修改凭证时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**修改凭证会员id */
    private int modifyMember;

    /**备注 */
    private String remark;

    /**
     * 所属业务员id t_agent_fac.user_id
     */
    private Integer saleUser;

    /**
     * 是否是工厂端添加  1.是 0.不是
     */
    private String isFact;
    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 支付类型备注（用于记录支付类型手输文字）
     * @return
     */
    private String payRemark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getUploadMember() {
        return uploadMember;
    }

    public void setUploadMember(int uploadMember) {
        this.uploadMember = uploadMember;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getModifyMember() {
        return modifyMember;
    }

    public void setModifyMember(int modifyMember) {
        this.modifyMember = modifyMember;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

	public Long getBuyer_corpid() {
		return buyerCorpId;
	}

	public void setBuyer_corpid(long buyercorpId) {
		this.buyerCorpId = buyercorpId;
	}

    public Long getBuyerCorpId() {
        return buyerCorpId;
    }

    public void setBuyerCorpId(Long buyerCorpId) {
        this.buyerCorpId = buyerCorpId;
    }

    public Integer getSaleUser() {
        return saleUser;
    }

    public void setSaleUser(Integer saleUser) {
        this.saleUser = saleUser;
    }

    public String getIsFact() {
        return isFact;
    }

    public void setIsFact(String isFact) {
        this.isFact = isFact;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }
}
