package com.xhs.test;


import com.zlead.constant.Cnst;
import com.zlead.dao.MemberDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.service.impl.AgentbandServiceImpl;
import com.zlead.fplat.transaction.FactoryTrans;
import com.zlead.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
//import com.zlead.reception.service.GoodsService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring-common.xml", "classpath*:/config/spring-mvc.xml", "classpath*:/config/spring-redis.xml" })
public class FactoryTest extends AbstractJUnit4SpringContextTests  {

    @Resource
	private FactoryTrans factory;
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private OaFactoryInfoMapper mapper;
	@Autowired
	private ShopDao shopDao;

	@Autowired
	private OaAgentMasMapper agentMasMapper;
	
	@Test
	public void testFindGoodsById(){
		OaFactoryInfo fac=new OaFactoryInfo();
		//fac.setFactId(5L);
		fac.setContactNo("16602213522");
		//fac.setShopId(4);
		int shopid=12;
		fac.setShopId(shopid);
		// factory.insertFactory(fac);
	}
	
 
	public void FactoryTest1() {
   // int result = mapper.insert(fac);
    OaFactoryInfo fac=new OaFactoryInfo();
    fac.setContactNo("16602213520");
//    fac.setFactId(15);
    int result = mapper.insertSelective(fac);
    System.out.println("fac id:"+fac.getFactId());
    //添加会员
    String contactNo = fac.getContactNo();
    //fac.setCreateTime(new Date());
    MemberEntity member = new MemberEntity();
   // member.setPhone(contactNo);
    member.setUsername(contactNo);
	member.setPassword(MD5Util.toMD5("111111"));

    memberDao.insertSelective(member);
    System.out.println("member id:"+member.getId());
    
    //添加店铺
    ShopEntity shop = new ShopEntity();
	shop.setSn(Cnst.getCurTime()+"");
	shop.setStatus(1);
    shop.setMemberid(String.valueOf(member.getId()));
    shopDao.insert(shop);
    System.out.println("shop id:"+shop.getId());
    
    fac.setShopId(shop.getId().intValue());
    mapper.updateShopid(fac);
    
    member.setOwnShopid(shop.getId());
    memberDao.updateByPrimaryKey(member);
	System.out.println("shopid:"+shop.getId());

	}

 
	public void agentTest(){
		//创建代理商
		OaAgentMas agentMas = new OaAgentMas();


		agentMas.setLinkTel("16602113520");
		//agentMas.setAgentId(18);

		int insert = agentMasMapper.insertSelective(agentMas);
		//创建会员
		MemberEntity member = new MemberEntity();
		String linkTel = agentMas.getLinkTel();
		member.setPhone(linkTel);
		member.setUsername(linkTel);
		member.setPassword(MD5Util.toMD5("111111"));
		member.setGender(Cnst.MemberCnst.GENDER_SECRET);
		member.setIfPhone(1);
		member.setIsDelete(0);
		member.setIsDisable(0);
		member.setStatus(1);
		member.setPoint(0L);
		member.setMemberType(0);
		Integer agentId = agentMas.getAgentId();
		member.setAgentId(Long.valueOf(agentId));
		member.setCreateTime(new Date());

		Integer id = agentMas.getAgentId();
		
		System.out.println("agentid"+id);
		
		member.setAgentId(id.longValue());
		

		memberDao.insertSelective(member);
		memberDao.updateById(member);
		//agentMasMapper.updateByPrimaryKeySelective(agentMas);

		System.out.println(linkTel);
		System.out.println(member.getId());



	}
}
