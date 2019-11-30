package com.zlead.entity.dto;

import java.math.BigDecimal;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/16.
 * @Desoription TODO
 */
public class GoodsDetailDto {

    /**
     * 商品 id
     */
    private long id;

    /**
     * 商品名称
     **/
    private String goodsName;

    /**
     * 商品类型
     **/
    private int prodType;

    /**
     * 商品轮播图 逗号隔开
     **/
    private String imgs;

    /**
     * 第一张
     **/
    private String firstImag;

    /**
     * 库存
     **/
    private long stock;

    /**
     * 规格名称
     **/
    private String spec;

    private BigDecimal actPrice;
    /**
     * 最终价
     */
    private BigDecimal resultPrice;
    /**
     * 经销价
     */
    private BigDecimal agentPrice;

    /**
     * 零售价
     */
    private BigDecimal marketPrice;

    /**
     * 展销价格
     **/
    private BigDecimal showPrice;

    /**
     * 批发价
     **/
    private BigDecimal price;

    /**
     * 成本价
     */
    private BigDecimal supplyPrice;

    /**
     * 商品介绍
     **/
    private String intro;

    /**
     * 产品参数
     **/
    private int attrId;

    /**
     * 产品id
     **/
    private int prodId;
    
    /**
     * shopid
     **/
    private int shopId;

	/**
     * 品牌名称
     **/
    private String bandName;

    /**
     * 系列名称
     **/
    private String listName;

    /**
     * 型号名称
     **/
    private String modelName;

    /**
     * 是否被收藏
     **/
    private int isCollect;

    private int ifShowPrice;

    private int ifShowStock;

    /**
     * 是否下架
     */
    private String isMarket;

    /**
     * 下架条件 1 不自动下架 2 按日期 3 按库存
     **/
    private int marketConfig;

    /**
     * 规格名称
     **/
    private String packagespec;
    
    /**
     * 上架库存:0-空、1-自定义/2-实际库存
     */
    private Integer stockType;

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public long getId() {
        return id;
    }

    public String getPackagespec() {
        return packagespec;
    }

    public void setPackagespec(String packagespec) {
        this.packagespec = packagespec;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getProdType() {
        return prodType;
    }

    public void setProdType(int prodType) {
        this.prodType = prodType;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getFirstImag() {
        return firstImag;
    }

    public void setFirstImag(String firstImag) {
        this.firstImag = firstImag;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getSpec() {
        return spec;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(BigDecimal showPrice) {
        this.showPrice = showPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getIfShowPrice() {
        return ifShowPrice;
    }

    public void setIfShowPrice(int ifShowPrice) {
        this.ifShowPrice = ifShowPrice;
    }

    public int getIfShowStock() {
        return ifShowStock;
    }

    public void setIfShowStock(int ifShowStock) {
        this.ifShowStock = ifShowStock;
    }

    public int getMarketConfig() {
        return marketConfig;
    }

    public void setMarketConfig(int marketConfig) {
        this.marketConfig = marketConfig;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }

    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getResultPrice() {
        return resultPrice;
    }

    public void setResultPrice(BigDecimal resultPrice) {
        this.resultPrice = resultPrice;
    }
    
    public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
    public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

    public String getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(String isMarket) {
        this.isMarket = isMarket;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
}
