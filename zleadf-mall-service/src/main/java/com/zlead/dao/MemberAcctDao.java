package com.zlead.dao;

import com.zlead.entity.MemberAcctEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 会员账户
 * 
 * @author fqf
 * @date 2018-07-23 14:56:41
 */
public interface MemberAcctDao {

    void insert(MemberAcctEntity entity);

    void update(MemberAcctEntity entity);

    void delete(Long id);

    int updateByPrimaryKeySelective(MemberAcctEntity record);

    PageList<MemberAcctEntity> findPage(Map params, PageBounds rowBounds);

    MemberAcctEntity findById(Long id);

    MemberAcctEntity findByMemberid(@Param(value = "memberid") String memberid,
                                @Param(value = "accounttype") Integer accounttype,@Param(value = "systemid") String systemid);
	
}
