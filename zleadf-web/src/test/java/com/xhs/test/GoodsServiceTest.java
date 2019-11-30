package com.xhs.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 




 
import com.zlead.entity.GoodsEntity;
//import com.zlead.reception.service.GoodsService;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring-common.xml", "classpath*:/config/spring-mvc.xml", "classpath*:/config/spring-redis.xml" })
public class GoodsServiceTest extends AbstractJUnit4SpringContextTests  {
 
	
//    @Resource
//    private GoodsService goodsService;
    
	 
	@Test
	public void testFindGoodsById(){
//		goodsDao.findById(2L);
		Integer showNum=3;
//    	List<GoodsEntity> goodsList = goodsService.gethomeGoodsList(showNum);
//    	System.out.println("Goods:"+goodsList.size());
	}

 
}
