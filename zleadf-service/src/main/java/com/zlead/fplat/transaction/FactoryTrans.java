package com.zlead.fplat.transaction;

import com.zlead.constant.Cnst;
import com.zlead.dao.MemberDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.OaFactoryInfo;

import java.util.Date;


@Service
@Transactional
public class FactoryTrans {

    @Autowired
    private OaFactoryInfoMapper mapper;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ShopDao shopDao;

    public int insertFactory(OaFactoryInfo fac) {
        fac.setCreateTime(new Date());

        //添加会员
        String contactNo = fac.getContactNo();
        MemberEntity member = new MemberEntity();
        member.setPhone(contactNo);
        member.setUsername(contactNo);
        member.setPassword(MD5Util.toMD5("111111"));
        member.setGender(Cnst.MemberCnst.GENDER_SECRET);
        member.setIfPhone(1);
        member.setIsDelete(0);
        member.setIsDisable(0);
        member.setStatus(1);
        member.setPoint(0L);
        member.setMemberType(0);
        memberDao.insertSelective(member);


        //添加店铺
        ShopEntity shop = new ShopEntity();
        shop.setMemberid(String.valueOf(member.getId()));
        shop.setSn(Cnst.getCurTime() + "");
        shop.setAuditSchedule(3);
        shop.setCreateDate(new Date());
        shop.setDisable(0);//是否禁用
        shop.setShopType(6);
        shop.setStatus(1);
        shop.setContactPhone(contactNo);
        shop.setContactPhone(contactNo);
        shop.setShopName(fac.getFactName()+"店铺");

        shopDao.insert(shop);
        fac.setShopId(shop.getId().intValue());
        int result1 = mapper.insertSelective(fac);
       // mapper.insertShopId(fac);
       // mapper.updateByPrimaryKey(fac);
        System.out.println("shopid-------: " + shop.getId());
        member.setOwnShopid(shop.getId());

        memberDao.updateByPrimaryKey(member);

        System.out.println("memberid: " + member.getId());
        return result1;
    }


}
