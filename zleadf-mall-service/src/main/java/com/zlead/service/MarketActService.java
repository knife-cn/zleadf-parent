package com.zlead.service;
import java.util.List;

import com.zlead.entity.OaMarketAct;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface MarketActService {
	public List<OaMarketAct> queryNoActiveActivity();

	/**
	 * 查询出所有活动失效的actIds
	 * @return
	 */
	List<Integer> queryNoActiveActId();

	/**
	 * 将过期活动处理
	 * @return
	 */
	int updateExpiredActConState();


}