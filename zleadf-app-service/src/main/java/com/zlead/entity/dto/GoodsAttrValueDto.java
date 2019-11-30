package com.zlead.entity.dto;

import com.zlead.entity.httpResponse.GoodsAttrValueReponse;

import java.util.List;

public class GoodsAttrValueDto {
    private Long prdId;
    private List<GoodsAttrValueReponse> goodsAttrValueReponses;

    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    public List<GoodsAttrValueReponse> getGoodsAttrValueReponses() {
        return goodsAttrValueReponses;
    }

    public void setGoodsAttrValueReponses(List<GoodsAttrValueReponse> goodsAttrValueReponses) {
        this.goodsAttrValueReponses = goodsAttrValueReponses;
    }
}
