/**
 * @program: zleadf-parent
 * @description:
 * @author: ytchen
 * @create: 2019-03-05 13:26
 **/
package com.zlead.utils;



import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Base64Utils
{
    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }
}
