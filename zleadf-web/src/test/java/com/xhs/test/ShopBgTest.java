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
import com.zlead.entity.ShopEntity;
import com.zlead.service.ShopBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring-common.xml", "classpath*:/config/spring-mvc.xml", "classpath*:/config/spring-redis.xml" })
public class ShopBgTest extends AbstractJUnit4SpringContextTests  {
 
    @Resource
    private ShopBgService shopBgService;
	 
	@Test
	public void testFindById(){
 
	}
	
	@Test
	public void testFindAll(){
		 Map params = new HashMap();
	        params.put("shopTypeall",1);
	        int pageNum=1;
	        int size=3;
	        //页数
	        PageBounds pageBounds = new PageBounds(pageNum,size);
	        PageList<ShopEntity>  list = shopBgService.getPage(params, pageBounds);
	        if(list!=null&&list.size()>0){
	            Page page =  new Page<ShopEntity>(list, list.getPagination());
//	            jsonResult =  new JsonResult(1,"列表信息",page,true);
	        }else{
//	            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
	        }
	} 

 
}
