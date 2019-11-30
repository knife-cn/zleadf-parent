package com.zlead.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yl1794 on 2018/5/16.
 */
public class PinyinUtil {

    /**
     * 将字符串中的中文转化为拼音全拼,英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String getPingYinAll(String chines) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder output = new StringBuilder();
        if (chines != null && chines.length() > 0
                && !"null".equals(chines)) {
            char[] input = chines.trim().toCharArray();
            try {
                for (char anInput : input) {
                    if (Character.toString(anInput).matches(
                            "[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                                anInput, format);
                        output.append(temp[0]);
                    } else
                        output.append(Character.toString(anInput));
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            return "*";
        }
        return output.toString();
    }

    /**
     * 将字符串中的中文转化为拼音首字母（大写），英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String getPingYinAllUpper(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNameChar : nameChar) {
            if (aNameChar > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
                            aNameChar, defaultFormat)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(aNameChar);
            }
        }
        return pinyinName.toString();
    }

    /**
     * 将字符串中的中文转化为拼音首字母（小写），英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String getPingYinAllLower(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNameChar : nameChar) {
            if (aNameChar > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
                            aNameChar, defaultFormat)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(aNameChar);
            }
        }
        return pinyinName.toString();
    }

    /**
     * 获取拼音首字母
     */
    public static String getPinYinFirstLetter(String sentence) {
        String first = null;
        if (StringUtils.isNotBlank(sentence)) {
            String pinyin = getPingYinAllUpper(sentence);
            if (StringUtils.isNotBlank(pinyin)) {
                first = pinyin.substring(0, 1);
            }
        }
        return first;
    }

}
