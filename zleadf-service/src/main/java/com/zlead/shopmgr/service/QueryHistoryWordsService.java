package com.zlead.shopmgr.service;

import com.zlead.entity.QueryHistoryWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("QueryHistoryWordsService")
public interface QueryHistoryWordsService {
    /**
     * 添加
     * @param queryHistoryWords
     * @return
     */
    int insertSelective(QueryHistoryWords queryHistoryWords);

    /**
     * 根据用户id查询该用户的所有历史搜索词
     * @param memberId
     * @return
     */
    List<QueryHistoryWords> queryWords(@Param("memberId") Integer memberId, @Param("start") Integer start,
                                       @Param("end") Integer end);

    /**
     * 根据用户编号删除所有的用户历史搜索词
     */
    int delete(Integer memberId);

    /**
     * 修改历史搜索词,根据用户id和用户搜索词将数据库原本的数据+1
     */
    int updateByPrimaryKeySelective(QueryHistoryWords queryHistoryWords);

}