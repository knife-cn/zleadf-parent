package com.zlead.reception.service;

import com.zlead.entity.MemberAcctEntity;
import java.util.Map;

import com.zlead.entity.MemberEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 会员账户
 *
 * @author fqf
 * @date 2018-07-23 14:56:41
 */
public interface MemberAcctService {

    PageList<MemberAcctEntity> getPage(Map params, PageBounds rowBounds);

    void save(MemberAcctEntity entity);

    void update(MemberAcctEntity entity);

    void delete(Long id);

    MemberAcctEntity findById(Long id);

    /**
     * 保存账户的信息
     * @param member
     * @param accountType
     */
    void saveMemberAcct(MemberEntity member, int accountType);
}

