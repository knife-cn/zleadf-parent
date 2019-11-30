package com.zlead.entity.dto;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/16.
 * @Desoription TODO
 */
public class GoodsDto {

    private GoodsDetailDto goodsDetailDto;

    private List<LinkedHashMap<String, List>> prodAttr;

    private List<String> thisGoodsAttr;

    private List<Map<String, Object>> attrVals;
    
    private Double discount=Double.valueOf(1);

    public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public List<LinkedHashMap<String, List>> getProdAttr() {
        return prodAttr;
    }

    public void setProdAttr(List<LinkedHashMap<String, List>> prodAttr) {
        this.prodAttr = prodAttr;
    }

    public GoodsDetailDto getGoodsDetailDto() {

        return goodsDetailDto;
    }

    public void setGoodsDetailDto(GoodsDetailDto goodsDetailDto) {
        this.goodsDetailDto = goodsDetailDto;
    }

    public List<String> getThisGoodsAttr() {
        return thisGoodsAttr;
    }

    public void setThisGoodsAttr(List<String> thisGoodsAttr) {
        this.thisGoodsAttr = thisGoodsAttr;
    }

    public List<Map<String, Object>> getAttrVals() {
        return attrVals;
    }

    public void setAttrVals(List<Map<String, Object>> attrVals) {
        this.attrVals = attrVals;
    }
}
