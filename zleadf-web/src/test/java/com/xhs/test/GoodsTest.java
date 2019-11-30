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
 




import com.zlead.dao.GoodsDao;
import com.zlead.entity.GoodsEntity;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring-common.xml", "classpath*:/config/spring-mvc.xml", "classpath*:/config/spring-redis.xml" })
public class GoodsTest extends AbstractJUnit4SpringContextTests  {
 
	
    @Autowired
    private GoodsDao goodsDao;
    
	 
	@Test
	public void testFindById(){
//		goodsDao.findById(2L);
		Long id=1412L;
		//System.out.println(goodsDao.findById(id).getId());
		//System.out.println(goodsDao.findById(id).getFullName());
	}
	
	@Test
	public void testFindAll(){
		Map params = new HashMap();
		Integer showNum=3;
        params.put("showNum",showNum);
        params.put("prodType",0);
        List<GoodsEntity> list = goodsDao.findHomeGoods(params);
	} 

 
}
