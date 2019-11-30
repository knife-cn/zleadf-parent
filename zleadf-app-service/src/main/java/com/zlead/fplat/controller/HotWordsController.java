package com.zlead.fplat.controller;

import com.zlead.fplat.entity.Hotwords;
import com.zlead.fplat.service.HotWordsService;
import com.zlead.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by admin on 2019/1/16.
 */
@RestController
@RequestMapping("/hotWords")
public class HotWordsController {

    @Autowired
    private HotWordsService hotWordsService;

    //更新高频词汇
    @PostMapping(value = "/update")
    public JsonResult updateHotWord(@RequestBody Hotwords hotWord) {
        return hotWordsService.addHotWords(hotWord.getWordName());
    }

    //首页前五条热词
    @GetMapping("/find")
    public JsonResult findHotWord() {
        return hotWordsService.findHotWords();
    }

    /**
     * 根据关键字查询10条
     */
    @RequestMapping(value = "/findByKey", method = RequestMethod.GET)
    public JsonResult findHotWordsByKey(@RequestParam(value = "key", required = false) String key) {
        JsonResult result = new JsonResult();
        try {
            List<Hotwords> hotwords = hotWordsService.findHotWordsByKey(key);
            result.setData(hotwords);
            result.setCode(1);
            result.setSuccess(true);
            result.setMessage("查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(2);
            result.setSuccess(false);
        }
        return result;
    }

}
