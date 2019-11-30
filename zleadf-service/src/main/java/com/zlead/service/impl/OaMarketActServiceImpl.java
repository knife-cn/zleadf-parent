package com.zlead.service.impl;
import java.util.List;

import com.zlead.entity.OaMarketAct;
import com.zlead.service.MarketActService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlead.dao.OaMarketActDao;


@Service
public class OaMarketActServiceImpl implements MarketActService {
	
	@Autowired 
	private OaMarketActDao oaMarketActDao;
	@Override
	public List<OaMarketAct> queryNoActiveActivity(){
		return oaMarketActDao.queryNoActiveActivity();
		
	}

	@Override
	public List<Integer> queryNoActiveActId() {
		return oaMarketActDao.queryNoActiveActId();
	}

    @Override
    public int updateExpiredActConState() {
        return oaMarketActDao.updateExpiredActConState();
    }
}