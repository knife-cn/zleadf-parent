package com.zlead.fplat.service;

import com.zlead.dto.Result;

/**
 * Created by admin on 2019/1/15.
 */
public interface HotWordsService {

    //添加高频搜索词汇
    Result addHotWords(String wordName);


    //搜索高频搜索词汇
    Result findHotWords();



}
