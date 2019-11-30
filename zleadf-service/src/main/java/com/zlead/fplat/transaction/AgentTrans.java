package com.zlead.fplat.transaction;

import com.zlead.constant.Cnst;
import com.zlead.dao.MemberDao;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class AgentTrans {
    @Autowired
    private OaAgentMasMapper agentMasMapper;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    public int insertAgent(OaAgentMas agentMas) {
        //创建代理商
        int insert = agentMasMapper.insert(agentMas);
        String linkTel = agentMas.getLinkTel();
        //创建会员
        MemberEntity member = new MemberEntity();
        member.setPhone(linkTel);
        member.setUsername(linkTel);
        member.setPassword(MD5Util.toMD5("111111"));
        member.setGender(Cnst.MemberCnst.GENDER_SECRET);
        member.setIfPhone(1);
        member.setIsDelete(0);
        member.setIsDisable(0);
        member.setStatus(1);
        member.setPoint(0L);
        member.setMemberType(3);
        member.setCreateTime(new Date());
        Integer agentId = agentMas.getAgentId();
        member.setAgentId(Long.valueOf(agentId));
        memberDao.insertSelective(member);
        return insert;

    }

    public int insertAgentWithPass(OaAgentMas agentMas, String passwd) {
        //创建代理商
        int insert = agentMasMapper.insert(agentMas);
        String linkTel = agentMas.getLinkTel();
        //创建会员
        MemberEntity member = new MemberEntity();
        member.setPhone(linkTel);
        member.setUsername(linkTel);
        member.setPassword(MD5Util.toMD5(passwd));
        member.setGender(Cnst.MemberCnst.GENDER_SECRET);
        member.setIfPhone(1);
        member.setIsDelete(0);
        member.setIsDisable(0);
        member.setStatus(1);
        member.setPoint(0L);
        member.setMemberType(3);
        member.setCreateTime(new Date());

        Integer agentId = agentMas.getAgentId();
        if (agentId < 1)
            agentMas = agentMasMapper.selectByPhone(linkTel);
        member.setAgentId(Long.valueOf(agentId));
        member.setMemberId(linkTel);

        System.out.println("insertAgentWithPass agentId: " + agentId);
        memberDao.insertSelective(member);
        return insert;

    }

    @Transactional
    public boolean insertOaAgentMasAndMember(List<Object> obj) {
        String c_a = obj.get(0).toString();//供货商ID
        String c_b = obj.get(1).toString();//供货商手机号
        String c_c = obj.get(2).toString();//供货商真实姓名
        String c_d = obj.get(3).toString();//供货商昵称
        String c_e = obj.get(4).toString();//商铺ID
        String c_f = obj.get(5).toString();//店铺公司名称
        String c_g = obj.get(6).toString();//店铺状态:1(不可用)7(开启)
        String c_h = obj.get(7).toString();//店铺联系人姓名
        String c_i = obj.get(8).toString();//店铺联系人电话
        String c_j = obj.get(9).toString();//店铺名称
        String c_k = obj.get(10).toString();//店铺详细地址
        String c_l = obj.get(11).toString();//校验标记  是否活跃（1true 0false）
        String c_m = obj.get(12).toString();//身份证认证标记  是否认证

        if (StringUtils.isNotBlank(c_b) && (StringUtils.isNotBlank(c_c) || StringUtils.isNotBlank(c_d))) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            try {
                OaAgentMas agentMas = new OaAgentMas();
                agentMas.setLinkTel(c_b);
                agentMas.setAgentName(c_j);
                agentMas.setLinkName(c_h);
                agentMas.setLinkTel(c_i);
                agentMas.setRevProvince(c_k);
                agentMas.setAgentState("7".equals(c_g) ? "1" : "0");
                agentMasMapper.insert(agentMas);
                //创建会员
                MemberEntity member = new MemberEntity();
                member.setPhone(c_b);
                member.setUsername(StringUtils.isNotBlank(c_c) ? c_c : c_d);
                member.setPassword(MD5Util.toMD5("111111"));
                member.setGender(Cnst.MemberCnst.GENDER_SECRET);
                member.setIfPhone(1);
                member.setIsDelete(0);
                member.setIsDisable(0);
                member.setStatus(1);
                member.setPoint(0L);
                member.setMemberType(3);
                member.setCreateTime(new Date());
                Integer agentId = agentMas.getAgentId();
                member.setAgentId(Long.valueOf(agentId));
                memberDao.insert(member);
                sqlSession.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sqlSession.close();
            }
        }
        return false;
    }

}
