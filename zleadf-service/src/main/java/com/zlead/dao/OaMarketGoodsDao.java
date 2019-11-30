package com.zlead.dao;

import com.zlead.entity.OaMarketGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OaMarketGoodsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OaMarketGoods record);

    int insertSelective(OaMarketGoods record);

    OaMarketGoods selectByPrimaryKey(OaMarketGoods record);

    int updateByPrimaryKeySelective(OaMarketGoods record);

    int updateByPrimaryKey(OaMarketGoods record);


    List<OaMarketGoods> selectByaActId(@Param("actId") int actId);

    int updateIsMarketByActIds(@Param("actIds") List<Integer> actIds);




}