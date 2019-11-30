package com.zlead.service.impl;

import com.zlead.dao.OaMarketGoodsDao;
import com.zlead.entity.OaMarketGoods;
import com.zlead.service.OaMarketGoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OaMarketGoodsService")
public class OaMarketGoodsServiceImpl implements OaMarketGoodsService {
    @Autowired
    private OaMarketGoodsDao oaMarketGoodsDao;

    public int deleteByPrimaryKey(Integer id){
        return oaMarketGoodsDao.deleteByPrimaryKey(id);
    }

    public int insert(OaMarketGoods record){
        return oaMarketGoodsDao.insert(record);
    }

    public int insertSelective(OaMarketGoods record){
        return oaMarketGoodsDao.insertSelective(record);
    }

    public OaMarketGoods selectByPrimaryKey(OaMarketGoods record){
        return oaMarketGoodsDao.selectByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(OaMarketGoods record){
        return oaMarketGoodsDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OaMarketGoods record){
        return oaMarketGoodsDao.updateByPrimaryKey(record);
    }


   public List<OaMarketGoods> selectByaActId(int id){
        return oaMarketGoodsDao.selectByaActId(id);
   }

    @Override
    public int updateIsMarketByActIds(List<Integer> actIds) {
        return oaMarketGoodsDao.updateIsMarketByActIds(actIds);
    }


}