package com.zlead.entity;

import com.zlead.fplat.entity.Goodsattrval;
import com.zlead.fplat.entity.Goodscat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品
 *
 * @author fqf
 * @date 2018-07-25 11:41:37
 */
public class GoodsEntity {
    /**
     *
     */
    private Long id;
    /**
     * 产品ID
     */
    private Long prodId;
    /**
     * 商品类型： 0-普通商品  1-积分类商品  2-活动商品
     */
    private Integer prodType;
    /**
     * 分类ID
     */
    private Long catagoryId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 系列id
     */
    private Long listId;
    /**
     * 型号id
     */
    private Long modelId;
    /**
     * 商品全称
     */
    private String fullName;
    /**
     * 商品发布区域: 0.平台自营区 1.店铺 2.代理商城(平台发布到代理区） 3.商家商城（从代理商进货） 4.附近商家
     */
    private Integer channel;
    /**
     * 规格名称
     */
    private String spec;
    /**
     * 供应商标价
     */
    private BigDecimal supplyPrice;
    /**
     * 代理价
     */
    private BigDecimal agentPrice;
    /**
     * 市场价格
     */
    private BigDecimal marketPrice;
    /**
     * 展销价
     */
    private BigDecimal showPrice;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 积分商品价格
     */
    private BigDecimal pointPrice;
    /**
     * 商品可以用多少积分抵扣,积分按一定比例兑换人民币
     */
    private Long point;
    /**
     * 当前优惠价
     */
    private BigDecimal price;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 冻结库存
     */
    private Integer freezeStock;
    /**
     * 销量
     */
    private Integer salesNum;
    /**
     * 被收藏数量
     */
    private Integer clickNum;
    /**
     * 是否上架
     */
    private Integer isMarket;
    /**
     * 第一张图片
     */
    private String firstImg;
    /**
     * 图片，以逗号分隔
     */
    private String imgs;
    /**
     * 标签IDS,格式为：1,2
     */
    private String tags;
    /**
     * 用于SOLR查询
     */
    private String solrName;
    /**
     * 是否审核通过
     */
    private Integer isAudit;
    /**
     * 审核人
     */
    private Long auditUserId;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 是否显示在首页
     */
    private Integer isHome;
    /**
     * 商家ID/加盟商
     */
    private Long shopId;
    /**
     * 供应商商家ID
     */
    private Long supplierShopId;
    /**
     * 购买物品获得积分数量
     */
    private BigDecimal integral;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 操作备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 商品介绍
     */
    private String intro;

    /**
     * 产品参数id
     */
    private Long prodAttrId;
    
    private Integer ifShowPrice;
    
    private Integer ifShowStock;
    /**
     * 下架条件 1 不自动下架 2 以日期 3 以库存'
     */
    private Integer marketConfig;

    private Integer terminal; 
    
    /**
     * 上架库存:0-空、1-自定义  2-实际库存
     */
    private Integer stockType;
 

	/**
     * 属性值
     */
    private List<Goodsattrval> goodsattrvals;
    /**
     * 分类
     */
    private List<Goodscat> goodscats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Integer getProdType() {
        return prodType;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }

    public Long getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(Long catagoryId) {
        this.catagoryId = catagoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public BigDecimal getSupplyPrice() {
        if (this.supplyPrice != null &&  this.supplyPrice.compareTo(BigDecimal.ZERO) == 1){
            return this.supplyPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        }else{
            return supplyPrice == null ? new BigDecimal(0) : supplyPrice;
        }
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public BigDecimal getAgentPrice() {
        if (this.agentPrice != null && this.agentPrice.compareTo(BigDecimal.ZERO) == 1){
            return this.agentPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        }else{
            return agentPrice == null ? new BigDecimal(0) : agentPrice;
        }
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    public BigDecimal getMarketPrice() {
        if (this.marketPrice != null && this.marketPrice.compareTo(BigDecimal.ZERO) == 1){
            return this.marketPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        }else{
            return marketPrice == null ? new BigDecimal(0) : marketPrice;
        }
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShowPrice() {
        if (this.showPrice != null && this.showPrice.compareTo(BigDecimal.ZERO) == 1){
            return this.showPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        }else{
            return showPrice == null ? new BigDecimal(0) : showPrice;
        }
    }

    public void setShowPrice(BigDecimal showPrice) {
        this.showPrice = showPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPointPrice() {
        if (this.pointPrice != null && this.pointPrice.compareTo(BigDecimal.ZERO) == 1){
            return this.pointPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        }else{
            return pointPrice == null ? new BigDecimal(0) : pointPrice;
        }
    }

    public void setPointPrice(BigDecimal pointPrice) {
        this.pointPrice = pointPrice;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public BigDecimal getPrice() {
        if (this.price != null && this.price.compareTo(BigDecimal.ZERO) == 1){
            return this.price.setScale(2,BigDecimal.ROUND_HALF_UP);
        }else{
            return price == null ? new BigDecimal(0) : price;
        }
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getFreezeStock() {
        return freezeStock;
    }

    public void setFreezeStock(Integer freezeStock) {
        this.freezeStock = freezeStock;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(Integer isMarket) {
        this.isMarket = isMarket;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSolrName() {
        return solrName;
    }

    public void setSolrName(String solrName) {
        this.solrName = solrName;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSupplierShopId() {
        return supplierShopId;
    }

    public void setSupplierShopId(Long supplierShopId) {
        this.supplierShopId = supplierShopId;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getProdAttrId() {
        return prodAttrId;
    }

    public void setProdAttrId(Long prodAttrId) {
        this.prodAttrId = prodAttrId;
    }

    public List<Goodsattrval> getGoodsattrvals() {
        return goodsattrvals;
    }

    public void setGoodsattrvals(List<Goodsattrval> goodsattrvals) {
        this.goodsattrvals = goodsattrvals;
    }

    public List<Goodscat> getGoodscats() {
        return goodscats;
    }

    public void setGoodscats(List<Goodscat> goodscats) {
        this.goodscats = goodscats;
    }

	public Integer getIfShowPrice() {
		return ifShowPrice;
	}

	public void setIfShowPrice(Integer ifShowPrice) {
		this.ifShowPrice = ifShowPrice;
	}

	public Integer getIfShowStock() {
		return ifShowStock;
	}

	public void setIfShowStock(Integer ifShowStock) {
		this.ifShowStock = ifShowStock;
	}

	public Integer getTerminal() {
		return terminal;
	}

	public void setTerminal(Integer terminal) {
		this.terminal = terminal;
	}

    public Integer getMarketConfig() {
        return marketConfig;
    }

    public void setMarketConfig(Integer marketConfig) {
        this.marketConfig = marketConfig;
    }
    
    public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}
}
