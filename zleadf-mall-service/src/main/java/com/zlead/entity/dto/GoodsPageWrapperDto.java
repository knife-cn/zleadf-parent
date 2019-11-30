package com.zlead.entity.dto;

import java.util.List;

public class GoodsPageWrapperDto {
    private List<GoodsPageDto> goodsPageDtoList;
    private int count;

    public List<GoodsPageDto> getGoodsPageDtoList() {
        return goodsPageDtoList;
    }

    public void setGoodsPageDtoList(List<GoodsPageDto> goodsPageDtoList) {
        this.goodsPageDtoList = goodsPageDtoList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
