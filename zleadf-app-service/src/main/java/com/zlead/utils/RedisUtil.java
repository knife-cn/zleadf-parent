package com.zlead.utils;

import com.puqian.util.RedisCacheClient;
import com.zlead.constant.BreadCrumbConstant;
import com.zlead.entity.dto.GoodsDto;
import com.zlead.entity.vo.BreadCrumbNaviVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author hyde
 * @Email zhangyayun@fuljoy.com
 * @Date 2019/3/25 21:12
 * @Description 面包屑导航相关redis存储
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisCacheClient redisCacheClient;

    public void save(String key,BreadCrumbNaviVo vo){

        try {
            redisCacheClient.set(key,vo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public BreadCrumbNaviVo query(String key){

        BreadCrumbNaviVo vo = null;
        try {
            vo =  redisCacheClient.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  vo;
    }

    public  void delete(String key){
        try {
            redisCacheClient.delete(key);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /*进入首页先清除redis中面包屑导航数据*/
    public void clearNaviRedis(HttpServletRequest request){
        String id = request.getSession().getId();
        String secondKey  = BreadCrumbConstant.SECOND_NAVI+id;
        String thirdKey = BreadCrumbConstant.THIRD_NAVI+id;
        delete(secondKey);
        delete(thirdKey);
    }

    /**
     *  存储 SECOND_NAVI 面包屑导航数据
     * @param request
     * @param flag  1：订单页；2：店铺，3：购物车；4：收藏；5：活动页；
     */
    public void saveNaviRedis(HttpServletRequest request,int flag,String url){

        String sessionId = request.getSession().getId();
        String key = BreadCrumbConstant.SECOND_NAVI+sessionId;

        BreadCrumbNaviVo bcnVo = new BreadCrumbNaviVo();
        bcnVo.setCateIndex(2);
        bcnVo.setNodeUrl(url);

        switch (flag){
            case 1:
                bcnVo.setNodeName("我的订单");
                bcnVo.setFlag("4");
                break;
            case 2:
                bcnVo.setNodeName("店铺");
                bcnVo.setFlag("5");

                break;
            case 3:
                bcnVo.setNodeName("购物车");
                bcnVo.setFlag("3");
                break;
            case 4:
                bcnVo.setNodeName("收藏");
                bcnVo.setFlag("0");
                break;
            case 5:
                bcnVo.setFlag("2");
                bcnVo.setNodeName("活动详情");
                break;
            default:
        }
        save(key,bcnVo);

    }


    /*搜索页*/
    public void  saveNaviRedis(HttpServletRequest request,Long f, String b, String l, String m, String c, String key){

        BreadCrumbNaviVo bcnVo = new BreadCrumbNaviVo();
        String nodeUrl ="";
        try {
            nodeUrl= request.getHeader("Referer");
        }catch (Exception e){
            e.printStackTrace();
        }

        bcnVo.setNodeUrl(nodeUrl);
        bcnVo.setCateIndex(2);

        StringBuffer nodeName= new StringBuffer();
        //从首页 分类/品牌/系列 进入
        if (key.isEmpty()){
            if (!b.isEmpty()){
                nodeName.append(b);
                if (!l.isEmpty()){
                    nodeName.append("+"+l);
                }
                if (!c.isEmpty()){
                    nodeName.append("+"+c);
                }
                if (!m.isEmpty()){
                    nodeName.append("+"+m);
                }
            } else if (!c.isEmpty()){
                nodeName.append(c);
                if (!b.isEmpty()){
                    nodeName.append("+"+b);
                }
                if (!l.isEmpty()){
                    nodeName.append("+"+l);
                }
                if (!m.isEmpty()){
                    nodeName.append("+"+m);
                }
            }else if (!l.isEmpty()){
                nodeName.append(l);
                if (!b.isEmpty()){
                    nodeName.append("+"+b);
                }
                if (!c.isEmpty()){
                    nodeName.append("+"+c);
                }
                if (!m.isEmpty()){
                    nodeName.append("+"+m);
                }
            } else if (!m.isEmpty()){
                nodeName.append(m);
                if (!b.isEmpty()){
                    nodeName.append("+"+b);
                }
                if (!c.isEmpty()){
                    nodeName.append("+"+c);
                }
                if (!l.isEmpty()){
                    nodeName.append("+"+l);
                }
            }
        }else {//从首页搜索框进入
            nodeName.append(key);
            if (!b.isEmpty()){
                nodeName.append("+"+b);
            }
            if (!l.isEmpty()){
                nodeName.append("+"+l);
            }
            if (!m.isEmpty()){
                nodeName.append("+"+m);
            }
            if (!c.isEmpty()){
                nodeName.append("+"+c);
            }
        }

        bcnVo.setNodeName(nodeName.toString());
        //不传值会进入搜索界面 无法获取节点名称 在此自定义为'搜索'
        if (c.isEmpty()&&b.isEmpty()&&l.isEmpty()&& key.isEmpty()
                &&m.isEmpty()){
            bcnVo.setFlag("1");
            bcnVo.setNodeName("搜索");
        }
        String id = request.getSession().getId();
        String redisKey = BreadCrumbConstant.SECOND_NAVI+id;
        save(redisKey,bcnVo);

    }

    /*商品页*/
    public void saveNaviRedis(HttpServletRequest request,GoodsDto map){
        BreadCrumbNaviVo bcnVo = new BreadCrumbNaviVo();
        String nodeUrl= request.getHeader("Referer");
        bcnVo.setNodeUrl(nodeUrl);
        bcnVo.setCateIndex(3);
        bcnVo.setNodeName(map.getGoodsDetailDto().getGoodsName());
        String id = request.getSession().getId();
        String key = BreadCrumbConstant.THIRD_NAVI+id;
        save(key,bcnVo);

    }

}
