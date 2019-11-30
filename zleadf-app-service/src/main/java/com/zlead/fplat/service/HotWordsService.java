package com.zlead.fplat.service;

import com.zlead.fplat.entity.Hotwords;
import com.zlead.util.JsonResult;

import java.util.List;

/**
 * Created by admin on 2019/1/15.
 */
public interface HotWordsService {

    //添加高频搜索词汇
    JsonResult addHotWords(String wordName);


    //搜索高频搜索词汇
    JsonResult findHotWords();


    List<Hotwords> findHotWordsByKey(String key);
}
