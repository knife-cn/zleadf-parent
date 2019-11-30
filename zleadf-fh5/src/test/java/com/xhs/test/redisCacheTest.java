package com.xhs.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puqian.util.RedisCacheClient;
 


 


import com.zlead.utils.LoginUtil;
//import com.xhs.entity.User;
//import com.xhs.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring-common.xml", "classpath*:/config/spring-mvc.xml", "classpath*:/config/spring-redis.xml" })
public class redisCacheTest extends AbstractJUnit4SpringContextTests  {

	 @Autowired
	 private RedisCacheClient redisCacheClient;
	
	 @Autowired
	 private LoginUtil loginUtil;
//	//@Autowired
//	@Resource
//    private GoodsService goodsService;
    
	@Test
	public void testAdd(){
		 
//		ValueOperations<String, String> valueops = redisTemplate.opsForValue();
		String skey="u1";
		String stv="v1";
		redisCacheClient.set(skey, stv); 
		System.out.println("redis set key:"+skey+" "+stv); 
	}
	
//	@Test
//	public void testFindAll(){
//		List<User> findAllList = userMapper.findAll();
//		System.out.println(findAllList.size());
//	}
	
	@Test
	public void testFindById(){
//		ValueOperations<String, String> valueops = redisTemplate.opsForValue();
		String skey="u1";
		//String stv="v1";
		String stv=redisCacheClient.get(skey); 
		System.out.println("redis get key:"+skey+" "+stv); 
	}


	@Test
	public void testUpdate(){
//		ValueOperations<String, String> valueops = redisTemplate.opsForValue();
		String skey="u1";
		String stv="v1";
		redisCacheClient.set(skey, stv);
		System.out.println("redis update key:"+skey+" "+stv); 
	}
	
	@Test
	public void testDelete(){
//		ValueOperations<String, String> valueops = redisTemplate.opsForValue();
		String skey="u1";
		//String stv="v1";
		redisCacheClient.delete(skey);
		System.out.println("redis del key:"+skey ); 
	}
}
