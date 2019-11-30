package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.HotwordsMapper;
import com.zlead.fplat.entity.Hotwords;
import com.zlead.fplat.entity.HotwordsExample;
import com.zlead.fplat.service.HotWordsService;
import com.zlead.util.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2019/1/15.
 */
@Service
@Transactional
public class HotWordsServiceImpl implements HotWordsService {

    @Autowired
    private HotwordsMapper mapper;

    /**
     * 首页高频搜索词汇添加
     */

    @Override
    public JsonResult addHotWords(String wordName) {

        JsonResult result = new JsonResult();
        if (!StringUtils.isNotEmpty(wordName.trim())) {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("高频热搜词不能为空！");
            return result;
        }
        //先查询  如果有  搜索次数+1   否则新增
        HotwordsExample example = new HotwordsExample();
        example.createCriteria().andWordNameEqualTo(wordName.trim());
        List<Hotwords> hotwords = mapper.selectByExample(example);
        if (hotwords.size() != 0) {
            //有结果 则数量+1   更新
            Hotwords hw = new Hotwords();
            hw.setId(hotwords.get(0).getId());
            hw.setNum(hotwords.get(0).getNum() + 1);
            hw.setWordName(wordName.trim());
            hw.setUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(hw);
            result.setCode(1);
            result.setSuccess(true);
            result.setMessage("更新成功！");
            return result;
        }
        //无   则新增
        Hotwords newHotwords = new Hotwords();
        newHotwords.setNum(1);
        newHotwords.setCreateTime(new Date());
        newHotwords.setWordName(wordName.trim());
        mapper.insert(newHotwords);
        result.setCode(1);
        result.setSuccess(true);
        result.setMessage("新增成功！");
        return result;
    }


    @Override
    public JsonResult findHotWords() {
        JsonResult result = new JsonResult();
        List<String> res = new ArrayList<>();
        List<Hotwords> list = mapper.findFiveHotwords();
        if (list.size() > 0) {
            res = list.stream().map(hotwords -> hotwords.getWordName()).collect(Collectors.toList());
        }
        result.setCode(1);
        result.setSuccess(true);
        result.setMessage("查询成功！");
        result.setData(res);
        return result;
    }

    @Override
    public List<Hotwords> findHotWordsByKey(String key) {
        return mapper.findHotWordsByKey(key);
    }
}
