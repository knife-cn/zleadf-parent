
package com.zlead.utils;

import java.util.Random;

/**
 * @program: zleadf-parent
 * @description:RandNum
 * @author: ytchen
 * @create: 2019-02-21 09:50
 * 随机六位数验证码
 **/
public class RandNumIs {

    /**
     * @生成一个6位的随机码
     */
    public static String createRandNum() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i <= 5; i++) {
            String s = random.nextInt(10) + "";
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * @生成一个4位的随机码
     */
    public static String create4RandNum() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i <= 3; i++) {
            String s = random.nextInt(10) + "";
            sb.append(s);
        }
        return sb.toString();
    }

}
