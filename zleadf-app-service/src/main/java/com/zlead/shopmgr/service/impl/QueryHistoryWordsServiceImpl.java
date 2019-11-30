package com.zlead.shopmgr.service.impl;

import com.zlead.dao.MemberDao;
import com.zlead.dao.QueryHistoryWordsDao;
import com.zlead.entity.QueryHistoryWords;
import com.zlead.shopmgr.service.QueryHistoryWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 历史搜索词
 */
@Service("QueryHistoryWordsService")
public class QueryHistoryWordsServiceImpl implements QueryHistoryWordsService {

    @Autowired
    private QueryHistoryWordsDao queryHistoryWordsDao;

    /**
     * 添加,当用户id不为空的时候添加用户
     * @param queryHistoryWords
     * @return
     */
    @Override
    public int insertSelective(QueryHistoryWords queryHistoryWords) {
        int i = 0;
        if (queryHistoryWords != null && queryHistoryWords.getMemberId() != null){
            i = queryHistoryWordsDao.insertSelective(queryHistoryWords);
        }
        return i;
    }

    /**
     * 获取用户id查询用户所有的历史搜索词，按照时间倒序
     * @param memberId
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<QueryHistoryWords> queryWords(Integer memberId, Integer start, Integer end) {
        if (memberId == null || memberId == 0) {
            return  null;
        }

        //com.zlead.dao.QueryHistoryWordsMapper.queryWords
        List<QueryHistoryWords> queryHistoryWords = queryHistoryWordsDao.queryWords(memberId, start, end);
        return queryHistoryWords;
    }

    /**
     * 当用户点击清空搜索记录的时候清空该用户所有的历史搜索记录
     * @param memberId
     * @return
     */
    @Override
    public int delete(Integer memberId) {
        if(memberId == null || memberId == 0){
            return 0;
        }
        int delete = queryHistoryWordsDao.delete(memberId);
        return delete;
    }

    /**
     * 当用户搜索以及存在的数据的时候修改刷新修改时间及查询次数+1，需要的参数是修改时间
     * @param queryHistoryWords
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(QueryHistoryWords queryHistoryWords) {
        int i = 0;
        if(queryHistoryWords != null && queryHistoryWords.getMemberId() != null && queryHistoryWords.gettWordName() != null){
            i = queryHistoryWordsDao.updateByPrimaryKeySelective(queryHistoryWords);
        }
        return i;
    }
}
