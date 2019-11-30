///**
// * @program: zleadf-parent
// * @description:TokenUtil
// * @author: ytchen
// * @create: 2019-02-14 18:01
// **/
//package com.zlead.util;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
///**
// * Description:Token生成工具
// * 第一部分我们称它为头部（header),第二部分我们称其为载荷（payload, 类似于飞机上承载的物品)，第三部分是签证（signature).
// * Auth: Frank
// * Date: 2019-02-14
// * Time: 18:01
// */
//public class TokenUtil {
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//
//    /**
//     * 生成一个令牌
//     * @param userId 用户ID
//     * @return 返回令牌
//     */
//    public String createToken(int userId){
//        //生成token
//        UUID uuid = UUID.randomUUID();
//        String token = userId+"_"
//                +uuid.toString().replaceAll("-", "");
//        //将token存入redis
//        String key = userId+"_token";
//        redisTemplate.opsForValue().set(key, token,
//                Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
//        return token;
//    }
//
//    /**
//     * 检查token是否正确
//     * @param token 令牌
//     * @return true正确;false失败
//     */
//    public boolean checkToken(String token){
//        //解析出userId和uuid
//        if(token==null || "".equals(token)){
//            return false;
//        }
//        String[] arr1 = token.split("_");
//        if(arr1.length != 2){
//            return false;
//        }
//        //根据redis进行检查
//        String key = arr1[0]+"_token";
//        String r_token = (String)redisTemplate.opsForValue().get(key);
//        if(r_token==null){
//            return false;
//        }
//        if(!token.equals(r_token)){
//            return false;
//        }
//        //返回检测结果,更新token时间
//        redisTemplate.opsForValue().set(key, token,
//                Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
//        return true;
//    }
//
//    /**
//     * 注销Token
//     * @param token 令牌
//     * @return true正确;false失败
//     */
//    public boolean clearToken(String token){
//        //解析出userId和uuid
//        if(token==null || "".equals(token)){
//            return false;
//        }
//        String[] arr1 = token.split("_");
//        if(arr1.length != 2){
//            return false;
//        }
//        //根据redis进行检查
//        String key = arr1[0]+"_token";
//        String r_token = (String)redisTemplate.opsForValue().get(key);
//        if(r_token==null){
//            return false;
//        }
//        //注销token
//        redisTemplate.delete(key);
//        return true;
//    }
//
//
//}
