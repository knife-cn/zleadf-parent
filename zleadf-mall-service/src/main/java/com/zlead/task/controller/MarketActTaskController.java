package com.zlead.task.controller;

/**
* @program: zleadf-parent
* @description:
* @author: ytchen
* @create: 2019-03-25 17:11
**/

import com.zlead.service.GoodsBgService;
import com.zlead.service.MarketActService;
import com.zlead.service.OaMarketGoodsService;
import com.zlead.task.common.listener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Controller
public class MarketActTaskController implements listener {

    
    @Resource
    private OaMarketGoodsService marketGoodsService;
    @Resource
    private GoodsBgService goodsBgService;
    @Resource
    private MarketActService marketActService;



    int count1 = 0;
    //每2小时执行一次0 */5 * * * ?---0 */2 * * *--"0 59 23 ? * *"   每分钟0 */1 * * * ? @Scheduled(cron="0 59 23 * * ?")
    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void activity() {
        ++count1;
        System.out.println("定时器：时间=" + new Date() + " 执行了" + count1 + "次"); // 1次
        List<Integer> actIds = null;//marketActService.queryNoActiveActId();
        if (null != actIds && actIds.size() > 0) {
            //活动商品下架
            marketGoodsService.updateIsMarketByActIds(actIds);
            //普通商品下架
            goodsBgService.updateIsMarketByActIds(actIds);
            //活动过期处理
            marketActService.updateExpiredActConState();
            System.out.println("已过期活动处理完成");
        } else {
            System.out.println("无已过期活动 无须筛选");
        }

    }

}
