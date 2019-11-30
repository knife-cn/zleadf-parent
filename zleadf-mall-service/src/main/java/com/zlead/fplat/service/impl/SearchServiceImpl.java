package com.zlead.fplat.service.impl;

import com.zlead.dao.SearchDao;
import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.CrmPrdList;
import com.zlead.fplat.entity.Custband;
import com.zlead.fplat.entity.vo.Screen;
import com.zlead.fplat.service.CrmPrdCatService;
import com.zlead.fplat.service.SearchService;
import com.zlead.util.ObjectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;
    @Autowired
    private CrmPrdCatService crmPrdCatService;

    /**
     * 根据代理人ID和工厂ID查询首页展示的品牌、序列、分类
     * @param agentId
     * @param factoryId
     * @return
     */
    public Screen findBrandListCatByAgent(Long agentId, Long factoryId){
        Screen screen = new Screen();
        int showCount = 6;//前台需要显示几个数据
        Set<Long> shopIdSet = new HashSet<>();//店铺ID
        Set<Long> levelIdSet = new HashSet<>();//级别ID
        //Set<Long> actIdSet = new HashSet<>();//活动ID
        //根据代理人ID查询代理人关联的工厂信息
        List<Map<String,Object>> facInfoList = searchDao.findFactoryInfoByAgent(agentId,factoryId);
        if(CollectionUtils.isEmpty(facInfoList)){
            return this.emptyData(screen);
        }
        for(Map<String,Object> dataMap : facInfoList){
            this.addId("shop_id",dataMap,shopIdSet);//添加查询参数店铺ID
            this.addId("level_id",dataMap,levelIdSet);//添加查询参数代理级别ID
        }
        if(CollectionUtils.isEmpty(levelIdSet)){
            levelIdSet.add(-1L);
        }
        //根据代理人ID查询代理人参与的活动
        /*List<Map<String,Object>> actInfoList = this.searchDao.findActivityInfoByAgent(agentId,shopIdSet);
        if(CollectionUtils.isNotEmpty(actInfoList)){
            for(Map<String,Object> dataMap : actInfoList){
                this.addId("act_id",dataMap,actIdSet);
            }
        }*/
        //根据条件查询有商品的分类已排序（按关联工厂时间和商品上架时间排序）
        List<Map<String,Object>> queryDataList = this.searchDao.findSortKindByCondition(agentId,factoryId,levelIdSet);
        if(CollectionUtils.isEmpty(queryDataList)){
            return this.emptyData(screen);
        }
        LinkedHashMap<String,List<Map<String,Object>>> dataGroupByFactory = this.groupByFactory(queryDataList);//按店铺分组
        List<Map<String,Object>> showData = this.poll(showCount,dataGroupByFactory);//轮询获取前台需要展示的数据
        List<Custband> brandList = new ArrayList<>();//前台显示的品牌数据
        List<CrmPrdList> listList = new ArrayList<>();//前台显示的系列数据
        for(Map<String,Object> dataMap : showData){
            this.addSameName(dataMap,queryDataList);//处理同名的数据
            //封装前台显示品牌数据
            Custband custband = new Custband();
            custband.setBandId(this.getIntegerValue("brand_id",dataMap));
            custband.setBandName(this.getStringValue("band_name",dataMap));
            custband.setBandIds(this.getStringValue("brandIds",dataMap));
            brandList.add(custband);
        }
        if(showData.size() < showCount){
            List<Map<String,Object>> showListData = this.pollList(showCount - showData.size(),dataGroupByFactory,showData);//轮询获取前台需要展示的数据
            for(Map<String,Object> dataMap : showListData){
                //封装前台显示系列数据
                CrmPrdList crmPrdList = new CrmPrdList();
                crmPrdList.setListId(this.getIntegerValue("list_id",dataMap));
                crmPrdList.setListName(this.getStringValue("list_name",dataMap));
                crmPrdList.setListIds(this.getStringValue("listIds",dataMap));
                listList.add(crmPrdList);
            }
        } else{
            for(Map<String,Object> dataMap : showData){
                //封装前台显示系列数据
                CrmPrdList crmPrdList = new CrmPrdList();
                crmPrdList.setListId(this.getIntegerValue("list_id",dataMap));
                crmPrdList.setListName(this.getStringValue("list_name",dataMap));
                crmPrdList.setListIds(this.getStringValue("listIds",dataMap));
                listList.add(crmPrdList);
            }
        }
        screen.setCustbandList(brandList);
        screen.setCrmPrdListList(listList);

        /*******************封装前台展示的分类数据**********************/
        LinkedHashMap<String,Set<Integer>> catGroupMap = this.removeDuplicateCat(dataGroupByFactory);
        LinkedHashMap<String,List<CrmPrdCat>> levelOneGroup = new LinkedHashMap<>();//一级分类分组
        //递归查询一级分类
        for(Map.Entry<String,Set<Integer>> entry : catGroupMap.entrySet()){
            List<CrmPrdCat> levelOneList = new ArrayList<>();//一级分类
            for(Integer catId : entry.getValue()){
                CrmPrdCat crmPrdCat = this.getParentCat(catId);//得到一级分类
                if(crmPrdCat != null){
                    if(factoryId == null && 1 != crmPrdCat.getIsFac()){//首页进去自定义分类不展示
                        continue;
                    }
                    String catName = null;
                    if(factoryId == null){
                        catName = crmPrdCat.getCatName();
                    } else{
                        //二级店铺页，平台分类显示备注名称，自定义分类显示分类名称
                        if(1 == crmPrdCat.getIsFac()){
                            catName = crmPrdCat.getCatDesc();
                        }else{
                            catName = crmPrdCat.getCatName();
                        }
                    }
                    if (StringUtils.isNotBlank(catName)) {
                       boolean addFlag = true;
                       for(CrmPrdCat cat : levelOneList){
                           if(StringUtils.equals(catName,cat.getCatName())){//判断是否重名
                               addFlag = false;
                               if(cat.getCatId().intValue() != crmPrdCat.getCatId().intValue()){
                                   cat.setCatIds(cat.getCatIds() + "," + crmPrdCat.getCatId());
                               }
                               break;
                           }
                       }
                       if(addFlag){
                           crmPrdCat.setCatIds(crmPrdCat.getCatId().toString());
                           levelOneList.add(crmPrdCat);
                       }
                    }
                }
            }
            levelOneGroup.put(entry.getKey(),levelOneList);
        }
        //轮询展示一级分类
        List<CrmPrdCat> crmPrdCatList = this.pollCat(showCount,levelOneGroup);
        this.addSameName(crmPrdCatList,levelOneGroup);
        screen.setCrmPrdCatList(crmPrdCatList);
        return screen;
    }

    /**
     * 递归查询一级分类
     * @param catId
     * @return
     */
    @Override
    public CrmPrdCat getParentCat(Integer catId) {
        CrmPrdCat crmPrdCat = crmPrdCatService.findOneById(catId);
        if (crmPrdCat != null && crmPrdCat.getPcatId() != 0) {
            crmPrdCat = getParentCat(crmPrdCat.getPcatId());
        }
        return crmPrdCat;
    }

    /**
     * 根据代理人ID和工厂ID查询首页展示的一级分类
     * @param agentId
     * @param factoryId
     * @return
     */
    public List<CrmPrdCat> findLevelOneCat(Long agentId, Long factoryId){
        int showCount = 6;//前台需要显示几个数据
        Set<Long> shopIdSet = new HashSet<>();//店铺ID
        Set<Long> levelIdSet = new HashSet<>();//级别ID
        //根据代理人ID查询代理人关联的工厂信息
        List<Map<String,Object>> facInfoList = searchDao.findFactoryInfoByAgent(agentId,factoryId);
        if(CollectionUtils.isEmpty(facInfoList)){
            return new ArrayList<>();
        }
        for(Map<String,Object> dataMap : facInfoList){
            this.addId("shop_id",dataMap,shopIdSet);//添加查询参数店铺ID
            this.addId("level_id",dataMap,levelIdSet);//添加查询参数代理级别ID
        }
        if(CollectionUtils.isEmpty(levelIdSet)){
            levelIdSet.add(-1L);
        }
        List<Map<String,Object>> queryDataList = this.searchDao.findSortKindByCondition(agentId,factoryId,levelIdSet);
        if(CollectionUtils.isEmpty(queryDataList)){
            return new ArrayList<>();
        }
        LinkedHashMap<String,List<Map<String,Object>>> dataGroupByFactory = this.groupByFactory(queryDataList);//按店铺分组
        /*******************封装前台展示的分类数据**********************/
        LinkedHashMap<String,Set<Integer>> catGroupMap = this.removeDuplicateCat(dataGroupByFactory);
        LinkedHashMap<String,List<CrmPrdCat>> levelOneGroup = new LinkedHashMap<>();//一级分类分组
        //递归查询一级分类
        for(Map.Entry<String,Set<Integer>> entry : catGroupMap.entrySet()){
            List<CrmPrdCat> levelOneList = new ArrayList<>();//一级分类
            for(Integer catId : entry.getValue()){
                CrmPrdCat crmPrdCat = this.getParentCat(catId);//得到一级分类
                if(crmPrdCat != null){
                    if(factoryId == null && 1 != crmPrdCat.getIsFac()){//首页进去自定义分类不展示
                        continue;
                    }
                    String catName = null;
                    if(factoryId == null){
                        catName = crmPrdCat.getCatName();
                    } else{
                        //二级店铺页，平台分类显示备注名称，自定义分类显示分类名称
                        if(1 == crmPrdCat.getIsFac()){
                            catName = crmPrdCat.getCatDesc();
                        }else{
                            catName = crmPrdCat.getCatName();
                        }
                    }
                    if (StringUtils.isNotBlank(catName)) {
                        boolean addFlag = true;
                        for(CrmPrdCat cat : levelOneList){
                            if(StringUtils.equals(catName,cat.getCatName())){//判断是否重名
                                addFlag = false;
                                if(cat.getCatId().intValue() != crmPrdCat.getCatId().intValue()){
                                    cat.setCatIds(cat.getCatIds() + "," + crmPrdCat.getCatId());
                                }
                                break;
                            }
                        }
                        if(addFlag){
                            crmPrdCat.setCatIds(crmPrdCat.getCatId().toString());
                            levelOneList.add(crmPrdCat);
                        }
                    }
                }
            }
            levelOneGroup.put(entry.getKey(),levelOneList);
        }
        //轮询展示一级分类
        List<CrmPrdCat> crmPrdCatList = this.pollCat(showCount,levelOneGroup);
        this.addSameName(crmPrdCatList,levelOneGroup);
        return crmPrdCatList;
    }

    private Screen emptyData(Screen screen){
        if(screen.getCustbandList() == null){
            List<Custband> showBrandData = new ArrayList<>();
            screen.setCustbandList(showBrandData);
        }
        if(screen.getCrmPrdListList() == null){
            List<CrmPrdList> showListData = new ArrayList<>();
            screen.setCrmPrdListList(showListData);
        }
        if(screen.getCrmPrdCatList() == null){
            List<CrmPrdCat> showCatData = new ArrayList<>();
            screen.setCrmPrdCatList(showCatData);
        }
        return screen;
    }

    /**
     * 去除重复的分类ID
     * @param dataMap
     * @return
     */
    private LinkedHashMap<String,Set<Integer>> removeDuplicateCat(LinkedHashMap<String,List<Map<String,Object>>> dataMap){
        LinkedHashMap<String,Set<Integer>> result = new LinkedHashMap<>();
        for(Map.Entry<String,List<Map<String,Object>>> entry : dataMap.entrySet()){
            Set<Integer> idSet = new LinkedHashSet<>();
            for(Map<String,Object> data : entry.getValue()){
                idSet.add(this.getIntegerValue("cat_id",data));
            }
            result.put(entry.getKey(),idSet);
        }
        return result;
    }

    /**
     * 分类重名处理
     * @param crmPrdCatList
     * @param levelOneGroup
     */
    private void addSameName(List<CrmPrdCat> crmPrdCatList,LinkedHashMap<String,List<CrmPrdCat>> levelOneGroup){
        for(CrmPrdCat crmPrdCat : crmPrdCatList){
            for(Map.Entry<String,List<CrmPrdCat>> entry : levelOneGroup.entrySet()){
                List<CrmPrdCat>  value = entry.getValue();
                for(CrmPrdCat cat : value){
                    if(StringUtils.equals(crmPrdCat.getCatName(),cat.getCatName())
                        && crmPrdCat.getCatId().intValue() != cat.getCatId().intValue()){
                        crmPrdCat.setCatIds(crmPrdCat.getCatIds() + "," + cat.getCatId());
                    }
                }
            }
        }
    }


    /**
     * 同名数据处理
     * @param dataMap
     * @param queryDataList
     */
    private void addSameName(Map<String,Object> dataMap, List<Map<String,Object>> queryDataList){
        String brandId = this.getStringValue("brand_id",dataMap);
        String brandName = this.getStringValue("band_name",dataMap);

        String listId = this.getStringValue("list_id",dataMap);
        String listName = this.getStringValue("list_name",dataMap);
        StringBuilder brandIds = new StringBuilder(brandId);
        StringBuilder listIds = new StringBuilder(listId);
        for(Map<String,Object> queryDataMap : queryDataList){
            String queryBrandId = this.getStringValue("brand_id",queryDataMap);
            String queryBrandName = this.getStringValue("band_name",queryDataMap);

            String queryListId = this.getStringValue("list_id",queryDataMap);
            String queryListName = this.getStringValue("list_name",queryDataMap);
            //品牌重名处理
            if(StringUtils.equals(brandName,queryBrandName)
                    && !StringUtils.equals(brandId,queryBrandId)){
                brandIds.append(",").append(queryBrandId);
            }
            //系列重名处理
            if(StringUtils.equals(listName,queryListName)
                    && !StringUtils.equals(listId,queryListId)){
                listIds.append(",").append(queryListId);
            }
        }
        if(brandIds.lastIndexOf(",") > 0){
            brandIds.deleteCharAt(brandIds.length() - 1);
        }
        if(listIds.lastIndexOf(",") > 0){
            listIds.deleteCharAt(listIds.length() - 1);
        }
        dataMap.put("brandIds",brandIds.toString());
        dataMap.put("listIds",listIds.toString());
    }

    /**
     * 判断dataList中是否有重名的数据
     * @param catName
     * @param dataList
     * @return
     */
    private boolean existSameName(String catName,List<CrmPrdCat> dataList){
        for(CrmPrdCat crmPrdCat : dataList){
            if(StringUtils.equals(catName,crmPrdCat.getCatName())){
                return true;
            }
        }
        return false;
    }


    /**
     * 轮询展示数据
     * @param count
     * @param levelOneGroup
     * @return
     */
    private List<CrmPrdCat> pollCat(int count ,LinkedHashMap<String,List<CrmPrdCat>> levelOneGroup){
        List<CrmPrdCat> result = new ArrayList<>();
        int maxNum = count;
        while(true){
            for(Map.Entry<String,List<CrmPrdCat>> entry : levelOneGroup.entrySet()){
                if(count == 0){
                    break;
                }
                List<CrmPrdCat> dataList = entry.getValue();
                for(CrmPrdCat data : dataList){
                    String name = data.getCatName();
                    if(this.existSameName(name,result)){
                        continue;
                    }
                    result.add(data);
                    count--;
                    break;
                }
            }
            maxNum--;
            if(count == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }

    /**
     * 轮询展示数据
     * @param count
     * @param dataMap
     * @return
     */
    private List<Map<String,Object>> poll(int count ,LinkedHashMap<String,List<Map<String,Object>>> dataMap){
        List<Map<String,Object>> result = new ArrayList<>();
        int maxNum = count;
        while(true){
            for(Map.Entry<String,List<Map<String,Object>>> entry : dataMap.entrySet()){
                if(count == 0){
                    break;
                }
                List<Map<String,Object>> dataList = entry.getValue();
                for(Map<String,Object> data : dataList){
                    String name = this.getStringValue("band_name",data);
                    if(this.existSameName(name,result,"band_name")){
                        continue;
                    }
                    result.add(data);
                    count--;
                    break;
                }
            }
            maxNum--;
            if(count == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }

    /**
     * 轮询展示系列数据
     * @param count
     * @param dataMap
     * @return
     */
    private List<Map<String,Object>> pollList(int count ,LinkedHashMap<String,List<Map<String,Object>>> dataMap,List<Map<String,Object>> result){
        int maxNum = count;
        Map<String,LinkedHashMap<String,List<Map<String,Object>>>> hashMap = new HashMap<>();//工厂按品牌分组后的数据,key 为工厂id,value 为按品牌分组后的数据
        LinkedHashMap<String,List<Map<String,Object>>> groupByBrand;//按品牌分组数据
        while(true){
            for(Map.Entry<String,List<Map<String,Object>>> entry : dataMap.entrySet()){
                if(count == 0){
                    break;
                }
                List<Map<String,Object>> dataList = entry.getValue();
                //将每个工厂的数据按品牌分组，然后轮询从每个品牌中取一个系列
                groupByBrand = hashMap.get(entry.getKey());
                if(groupByBrand == null){
                    groupByBrand = new LinkedHashMap<>();
                    for(Map<String,Object> map : dataList){
                        String key = getStringValue("band_id",map);
                        if(groupByBrand.containsKey(key)){
                            groupByBrand.get(key).add(map);
                        }else{
                            List<Map<String,Object>> valueList = new ArrayList<>();
                            valueList.add(map);
                            groupByBrand.put(key,valueList);
                        }
                    }
                    hashMap.put(entry.getKey(),groupByBrand);
                }
                for(Map.Entry<String,List<Map<String,Object>>> bandEntry : groupByBrand.entrySet()){
                    boolean addFlag = false;
                    for(Map<String,Object> data : bandEntry.getValue()){
                        String name = getStringValue("list_name",data);
                        if(existSameName(name,result,"list_name")){
                            continue;
                        }
                        addFlag = true;
                        result.add(data);
                        count--;
                        break;
                    }
                    if(addFlag){
                        break;
                    }
                }
            }
            maxNum--;
            if(count == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }


    /**
     * 是否存在同名的数据
     * @param str
     * @param dataList
     * @param key
     * @return
     */
    private boolean existSameName(String str,List<Map<String,Object>> dataList,String key){
        for(Map<String,Object> dataMap: dataList){
            String name = this.getStringValue(key,dataMap);
            if(StringUtils.equals(str,name)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据工厂ID对数据进行分组
     * @param dataList
     * @return
     */
    private LinkedHashMap<String,List<Map<String,Object>>> groupByFactory(List<Map<String,Object>> dataList){
        LinkedHashMap<String,List<Map<String,Object>>> result = new LinkedHashMap<>();
        for(Map<String,Object> dataMap : dataList){
            String shopId = this.getStringValue("shop_id",dataMap);
            if(result.containsKey(shopId)){
                result.get(shopId).add(dataMap);
            } else{
                List<Map<String,Object>> mapList = new ArrayList<>();
                mapList.add(dataMap);
                result.put(shopId,mapList);
            }
        }
        return result;
    }

    /**
     * 从Map中取ID放到集合中
     * @param key
     * @param dataMap
     * @param dataCollection
     */
    private void addId(String key, Map<String,Object> dataMap, Collection<Long> dataCollection){
        Long id = this.getLongValue(key,dataMap);
        if(id != null){
            dataCollection.add(id);
        }
    }

    /**
            * 从map集合中获取Long类型数据
     * @param key 键值
     * @param dataMap
     * @return
             */
    private Long getLongValue(String key,Map<String,Object> dataMap){
        Object obj = dataMap.get(key);
        if(obj == null){
            return null;
        }
        return Long.valueOf(obj.toString());
    }

    /**
     * 从map集合中获取Integer类型数据
     * @param key 键值
     * @param dataMap
     * @return
     */
    private Integer getIntegerValue(String key,Map<String,Object> dataMap){
        Object obj = dataMap.get(key);
        if(obj == null){
            return null;
        }
        return Integer.valueOf(obj.toString());
    }

    /**
     * 从map集合中获取String类型数据
     * @param key 键值
     * @param dataMap
     * @return
     */
    private String getStringValue(String key,Map<String,Object> dataMap){
        Object obj = dataMap.get(key);
        if(obj == null){
            return null;
        }
        return obj.toString();
    }
}
