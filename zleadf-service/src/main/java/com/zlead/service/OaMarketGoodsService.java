package com.zlead.service;
import com.zlead.entity.OaMarketGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OaMarketGoodsService {
    int deleteByPrimaryKey(Integer id);

    int insert(OaMarketGoods record);

    int insertSelective(OaMarketGoods record);

    OaMarketGoods selectByPrimaryKey(OaMarketGoods record);

    int updateByPrimaryKeySelective(OaMarketGoods record);

    int updateByPrimaryKey(OaMarketGoods record);


    List<OaMarketGoods> selectByaActId(@Param("actId") int actId);

    /**
     * 通过活动查询出过期活动actIds 讲商品下架
     * @param actIds
     * @return
     */
    int updateIsMarketByActIds(@Param("actIds") List<Integer> actIds);




}