package com.zlead.utils;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Map.Entry;

/**
 * 按拼音首字母表排序
 * 例如:首字母A开头的放在A下面，B开头的放到B下面
 *
 * @author jzz
 */
public class SortByLetterAscUtils {

    /**
     * @param object 一个集合
     * @return
     * @throws Exception
     */
    public static Map<String, ArrayList<Object>> sortByLetterAsc(Object object) throws Exception {
        if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            //按拼音首字母表排序
            Map<String, ArrayList<Object>> letterMap = new TreeMap<String, ArrayList<Object>>();
            if (!list.isEmpty()) {
                for (Object t : list) {
                    String pinYinFirstLetter = getFieldValue("pinyin", t);
                    if (!letterMap.containsKey(pinYinFirstLetter) && pinYinFirstLetter.matches("[A-Z]")) {
                        letterMap.put(pinYinFirstLetter, new ArrayList<Object>());
                    }
                }

                Iterator<Entry<String, ArrayList<Object>>> entries = letterMap.entrySet().iterator();
                while (entries.hasNext()) {
                    Entry<String, ArrayList<Object>> next = entries.next();
                    ArrayList<Object> listTemp = new ArrayList<Object>();
                    for (Object t : list) {
                        String pinYinFirstLetter = getFieldValue("pinyin", t);
                        if (!StringUtils.isEmpty(pinYinFirstLetter) && next.getKey().equals(pinYinFirstLetter)) {
                            listTemp.add(t);
                        }
                    }
                    next.getValue().addAll(listTemp);
                }
            }

            ArrayList<Object> listTemp2 = new ArrayList<Object>();
            if (!list.isEmpty()) {
                for (Object t : list) {
                    String pinYinFirstLetter = getFieldValue("pinyin", t);
                    if (!pinYinFirstLetter.matches("[A-Z]")) {
                        listTemp2.add(t);
                    }
                }
                if (!listTemp2.isEmpty()) {
                    letterMap.put("#", listTemp2);
                }
            }
            return letterMap;
        } else {
            return null;
        }
    }

    /**
     * 获取传递字段的属性值
     */
    private static String getFieldValue(String field, Object o) {
        String pinYinFirstLetter = null;
        if (o instanceof Map) {
            Object value = ((Map) o).get(field);
            if (value instanceof String) {
                pinYinFirstLetter = PinyinUtil.getPinYinFirstLetter(value.toString()).toUpperCase();
            }
        }
        return pinYinFirstLetter;
    }
}