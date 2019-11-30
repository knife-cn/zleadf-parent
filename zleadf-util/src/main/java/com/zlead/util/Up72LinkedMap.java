package com.zlead.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 扩展LinkedHashMap，可以根据下标为获取Entry对象
 */
public class Up72LinkedMap<K, V> extends LinkedHashMap {

    /**
     * 根据下标来获取Entry对象
     *
     * @param index 下标
     * @return Entry对象，可能为空
     */
    public Map.Entry<K, V> getEntryByIndex(int index) {
        Set<Map.Entry<K, V>> entrySet = this.entrySet();
        int i = 0;
        for (Map.Entry<K, V> entry : entrySet) {
            if (i == index) {
                return entry;
            }
            i++;
        }
        return null;
    }


    /**
     * 根据key来获取key的下标
     *
     * @param key 键
     * @return 下标，可能为空
     */
    public Integer getIndexByKey(K key) {
        Set<K> keySet = this.keySet();
        if (key == null || keySet == null) {
            return null;
        }
        int index = 0;
        for (K k : keySet) {
            if (k == key || k.equals(key)) {
                return index;
            }
            index++;
        }
        return null;
    }

}
