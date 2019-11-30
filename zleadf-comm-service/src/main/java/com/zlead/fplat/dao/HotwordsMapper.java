package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Hotwords;
import com.zlead.fplat.entity.HotwordsExample;
import java.util.List;

public interface HotwordsMapper {
    /**
     * This method:deleteByPrimaryKey
     *   t_hot_words
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     *   t_hot_words
     *
     * @ET
     */
    int insert(Hotwords record);

    /**
     * This method:insertSelective
     *   t_hot_words
     *
     * @ET
     */
    int insertSelective(Hotwords record);

    /**
     * This method:selectByExample
     *   t_hot_words
     *
     * @ET
     */
    List<Hotwords> selectByExample(HotwordsExample example);

    /**
     * This method:selectByPrimaryKey
     *   t_hot_words
     *
     * @ET
     */
    Hotwords selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_hot_words
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Hotwords record);

    /**
     * This method:updateByPrimaryKey
     *   t_hot_words
     *
     * @ET
     */
    int updateByPrimaryKey(Hotwords record);


    /*
    搜索前五个高频热词
    * */

    List<Hotwords>   findFiveHotwords();
}