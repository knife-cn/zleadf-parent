package com.zlead.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2019/1/17.
 */
@Component
public class ChineseInitialUtil {

    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPYHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

}
