package com.zlead.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 对集合进行排序
 */
public class CollectionSort {

    /**
     * 对list集合中的map集合进行排序，
     * @author 廖巨会
     * @param gasResultList 传入的集合
     * @param sortName  需要排序的key
     * @return List<Map<String,Object>>
     */
    public  static void listContainMapSort(List gasResultList,String sortName){
        if (null != gasResultList && gasResultList.size() > 0) {
            Collections.sort(gasResultList, new Comparator<Map>() {
                public int compare(Map o1, Map o2) {
                    String str1 = o1.get("stock").toString();
                    String  str2 = o2.get("stock").toString();
                    if (str1.equals("0")){
                        o1.put(sortName,"0");
                    }
                   // if (o1.get(sortName).equals(o2.get(sortName))) {
                        Integer one = Integer.valueOf(o1.get(sortName).toString());
                        Integer two = Integer.valueOf(o2.get(sortName).toString());
                        return two.compareTo(one);
                  //  }
                   // return 0;
                }
            });

        }
    }
}
