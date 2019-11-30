package com.zlead.reception.service.impl;

import com.zlead.dao.MemberAcctDao;
import com.zlead.entity.MemberAcctEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.reception.service.MemberAcctService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


@Transactional
@Service
public class MemberAcctServiceImpl implements MemberAcctService {
    @Autowired
    private MemberAcctDao memberAcctBgDao;

    @Transactional(readOnly = true)
    public PageList<MemberAcctEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = memberAcctBgDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public MemberAcctEntity findById(Long id) {
        return memberAcctBgDao.findById(id);
    }

    public void save(MemberAcctEntity entity) {
        memberAcctBgDao.insert(entity);
    }

    public void update(MemberAcctEntity entity) {
        memberAcctBgDao.update(entity);
    }

    public void delete(Long id) {
        memberAcctBgDao.delete(id);
    }

    /**
     * 保存账户的信息
     * @param member
     * @param accountType
     */
    public void saveMemberAcct(MemberEntity member, int accountType){
        MemberAcctEntity newMemberAcct = new MemberAcctEntity();
        newMemberAcct.setUsername(member.getUsername());
        newMemberAcct.setAccounttype(accountType);
        if(accountType==0){
            newMemberAcct.setAcctName("现金账户");
            newMemberAcct.setRemark("用户注册时添加现金账户");
        }else if(accountType==1){
            newMemberAcct.setAcctName("积分账户");
            newMemberAcct.setRemark("用户注册时添加积分账户");
        }
        newMemberAcct.setAccount(new BigDecimal(0.0));
        newMemberAcct.setBlance(new BigDecimal(0.0));
        newMemberAcct.setFreezeBlance(new BigDecimal(0.0));
        newMemberAcct.setMembertype(member.getMemberType());
        if (member != null ) {
            newMemberAcct.setMemberid(member.getMemberId());
        }
        newMemberAcct.setSystemid(member.getSystemId());
        newMemberAcct.setCreateDate(new Date());
        memberAcctBgDao.insert(newMemberAcct);
    }

    /**
     * 添加冻结金额
     */


}
