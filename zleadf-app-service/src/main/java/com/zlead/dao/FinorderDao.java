package com.zlead.dao;

import com.zlead.entity.FinorderEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Repository;

//import com.puqian.payment.module.ZxFinorder;
//import com.puqian.payment.apipay.module.ZxPayAcct;

/**
 * 微信支付支付dao
 * */
@Repository
public interface FinorderDao {


	
	/**
	 * 新增转账订单
	 * */
	void insertFinorder(FinorderEntity finorderEntity);

	FinorderEntity findByOutTradeNo(String outTradeNo);

	FinorderEntity findById(Long id);

	void updateFinorder(FinorderEntity FinorderEntity);

	PageList<FinorderEntity> findFinorderByMemberid(PageBounds rowBounds,long id);
	
}


























