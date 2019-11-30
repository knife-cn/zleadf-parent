package com.zlead.fplat.service.impl;

import com.zlead.dao.FastSearchDao;
import com.zlead.entity.GoodsEntity;
import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.CrmPrdList;
import com.zlead.fplat.entity.Custband;
import com.zlead.fplat.entity.vo.*;
import com.zlead.fplat.service.FastSearchService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FastSearchServiceImpl implements FastSearchService {
    @Autowired
    private FastSearchDao fastSearchDao;
    /**
     * 首页品牌、系列、分类展示
     * @param agentId
     * @return
     */
    @Override
    public Screen displayBrandListCat(Long agentId) {
        //根据条件查询代理人有权限且有上架商品的品牌、系列、分类数据
        List<KindVo> kindList = null;
        if(agentId != null){
            kindList = fastSearchDao.findSortKindByCondition(agentId,null);
        } else{
            kindList = fastSearchDao.findSortKind();
        }
        if(CollectionUtils.isEmpty(kindList)){
            return this.emptyData(null);
        }
        LinkedHashMap<Integer,List<KindVo>> groupDataMap = this.groupByFactory(kindList);//按工厂分组
        List<KindVo> dataList = this.poll(groupDataMap,6);//轮询每个工厂得到需要展示的数据
        //封装需要展示的品牌、系列对象
        List<Custband> brandList = new ArrayList<>();//前台显示的品牌数据
        List<CrmPrdList> listList = new ArrayList<>();//前台显示的系列数据
        Custband custband;
        CrmPrdList crmPrdList;
        for(KindVo kindVo : dataList){
            //封装前台显示品牌数据
            custband = new Custband();
            custband.setBandId(kindVo.getBrandId());
            custband.setBandName(kindVo.getBrandName());
            custband.setBandIds(kindVo.getBrandId().toString());
            brandList.add(custband);
        }
        //如果品牌数量不足需要展示的个数时，重新轮询展示系列（存在一个品牌对应多个系列的情况，品牌不足可能存在系列数足够）
        if(dataList.size() < 6){
            List<KindVo> showListData = this.pollList(dataList,groupDataMap,6 - dataList.size());//轮询获取前台需要展示的数据
            for(KindVo kindVo : showListData){
                //封装前台显示系列数据
                crmPrdList = new CrmPrdList();
                crmPrdList.setListId(kindVo.getListId());
                crmPrdList.setListName(kindVo.getListName());
                crmPrdList.setListIds(kindVo.getListId().toString());
                listList.add(crmPrdList);
            }
        } else{
            for(KindVo kindVo : dataList){
                //封装前台显示系列数据
                crmPrdList = new CrmPrdList();
                crmPrdList.setListId(kindVo.getListId());
                crmPrdList.setListName(kindVo.getListName());
                crmPrdList.setListIds(kindVo.getListId().toString());
                listList.add(crmPrdList);
            }
        }
        /*******************封装前台展示的分类数据**********************/
        LinkedHashMap<Integer,List<CatTreeDisplay>> dataMap = this.getPollCatData(kindList,false);//获取待轮询的分类数据
        List<CatTreeDisplay> showDataList = this.pollCatTreeDisplay(dataMap,6);//轮询展示分类数据
        List<CrmPrdCat> crmPrdCatList = new ArrayList<>();
        for(CatTreeDisplay data : showDataList){
            crmPrdCatList.add(data.getLevelOneList().get(0));
        }
        Screen screen = new Screen();
        screen.setCustbandList(brandList);
        screen.setCrmPrdListList(listList);
        screen.setCrmPrdCatList(crmPrdCatList);
        return screen;
    }


    /**
     * 首页商品展示区域
     * @param agentId
     * @return
     */
    @Override
    public List<HomeProductsVo> displayGoodsArea(Long agentId) {
        List<Integer> facIdList = new ArrayList<>();
        Object data = new HashMap<>();
        //根据条件查询代理人有权限且有上架商品的品牌、系列、分类数据
        List<KindVo> kindList = null;
        if(agentId != null){
            List<Map<String,Object>> facInfoDataList = this.fastSearchDao.findFactoryInfoByAgent(agentId,null);
            if(CollectionUtils.isEmpty(facInfoDataList)){
                return new ArrayList<>();
            }
            for(Map<String,Object> dataMap : facInfoDataList){
                facIdList.add(this.getIntegerValue("shop_id",dataMap));
            }
            kindList = fastSearchDao.findSortKindByCondition(agentId,null);
        } else{
            kindList = fastSearchDao.findSortKind();
        }
        if(CollectionUtils.isEmpty(kindList)){
            return new ArrayList<>();
        }
        LinkedHashMap<Integer,List<CatTreeDisplay>> dataMap = this.getPollCatData(kindList,true);//获取待轮询的分类数据
        List<CatTreeDisplay> showDataList = this.pollCatTreeDisplay(dataMap,6);//轮询展示分类数据
        this.handleSameCatName(showDataList,dataMap);//不同工厂同名分类处理
        /*****************分装前台展示的商品区域数据************************/

        List<HomeProductsVo> result = new ArrayList<>();
        HomeProductsVo homeProductsVo;
        int maxCount = 4;
        int index = 1;
        for(CatTreeDisplay catTreeDisplay : showDataList){
            if(index++ > 2){
                maxCount = 8;
            }
            homeProductsVo = new HomeProductsVo();
            homeProductsVo.setFirstCrmPrdCat(catTreeDisplay.getLevelOneList().get(0));//一级分类
            homeProductsVo.setSecondCrmPrdCat(this.getSecondCat(catTreeDisplay,6));//二级分类数据
            homeProductsVo.setGoodsEntityList(this.getGoods(agentId,facIdList,catTreeDisplay,maxCount));//商品数据
            result.add(homeProductsVo);
        }
        return result;
    }

    /**
     * 根据分类ID查询分类树形结构
     * @param catIds
     * @param flag 是否构造所有分类，如果为false则只构造一级分类
     * @return
     */
    @Override
    public List<CatTree> findCatTreeByCatId(Collection<Integer> catIds,boolean flag){
        if(CollectionUtils.isEmpty(catIds)){
            return null;
        }
        List<Map<String,Object>> dataList = fastSearchDao.findCatTreeByCatId(catIds);
        if(CollectionUtils.isEmpty(dataList)){
            return null;
        }
        List<CatTree> catTreeList = new ArrayList<>();
        Integer pcatId;//当前分类的上级分类ID
        Integer twoPcatId;//当前分类的二级分类的上级分类ID
        CatTree catTree;
        CrmPrdCat crmPrdCat;//当前分类
        CrmPrdCat levelOne;//一级分类
        CrmPrdCat levelTwo;//二级分类
        for(Map<String,Object> dataMap : dataList){
            pcatId = this.getIntegerValue("pcat_id",dataMap);
            twoPcatId = this.getIntegerValue("two_pcat_id",dataMap);
            catTree = new CatTree();
            catTree.setCatId(this.getIntegerValue("cat_id",dataMap));//设置当前分类ID
            if(pcatId != null && pcatId == 0){//当前分类是一级分类，则没有二级分类，一级分类就是它本身
                crmPrdCat = new CrmPrdCat();
                crmPrdCat.setCatId(this.getIntegerValue("cat_id",dataMap));
                crmPrdCat.setCatName(this.getStringValue("cat_name",dataMap));
                crmPrdCat.setCatDesc(this.getStringValue("cat_desc",dataMap));
                crmPrdCat.setIsFac(this.getIntegerValue("is_fac",dataMap));
                crmPrdCat.setPicPath(this.getStringValue("pic_path",dataMap));
                crmPrdCat.setPcatId(this.getIntegerValue("pcat_id",dataMap));
                crmPrdCat.setShopId(this.getIntegerValue("shop_id",dataMap));

                catTree.setLevelOne(crmPrdCat);
                if(flag){
                    catTree.setLevelTwo(null);
                    catTree.setCrmPrdCat(crmPrdCat);
                }
                catTreeList.add(catTree);
                continue;
            } else if(twoPcatId != null && twoPcatId == 0){//当前分类是二级分类，则二级分类是它本身，一级分类是二级分类的上级分类
                levelOne = new CrmPrdCat();
                levelOne.setCatId(this.getIntegerValue("two_cat_id",dataMap));
                levelOne.setCatName(this.getStringValue("two_cat_name",dataMap));
                levelOne.setCatDesc(this.getStringValue("two_cat_desc",dataMap));
                levelOne.setIsFac(this.getIntegerValue("two_is_fac",dataMap));
                levelOne.setPicPath(this.getStringValue("two_pic_path",dataMap));
                levelOne.setPcatId(this.getIntegerValue("two_pcat_id",dataMap));
                levelOne.setShopId(this.getIntegerValue("two_shop_id",dataMap));

                catTree.setLevelOne(levelOne);
                if(flag){
                    crmPrdCat = new CrmPrdCat();
                    crmPrdCat.setCatId(this.getIntegerValue("cat_id",dataMap));
                    crmPrdCat.setCatName(this.getStringValue("cat_name",dataMap));
                    crmPrdCat.setCatDesc(this.getStringValue("cat_desc",dataMap));
                    crmPrdCat.setIsFac(this.getIntegerValue("is_fac",dataMap));
                    crmPrdCat.setPicPath(this.getStringValue("pic_path",dataMap));
                    crmPrdCat.setPcatId(this.getIntegerValue("pcat_id",dataMap));
                    crmPrdCat.setShopId(this.getIntegerValue("shop_id",dataMap));

                    catTree.setLevelTwo(crmPrdCat);
                    catTree.setCrmPrdCat(crmPrdCat);
                }
                catTreeList.add(catTree);
                continue;
            } else {//当前分类是三级分类，则二级分类是它的上级分类，一级分类是二级分类的上级分类
                levelOne = new CrmPrdCat();
                levelOne.setCatId(this.getIntegerValue("one_cat_id",dataMap));
                levelOne.setCatName(this.getStringValue("one_cat_name",dataMap));
                levelOne.setCatDesc(this.getStringValue("one_cat_desc",dataMap));
                levelOne.setIsFac(this.getIntegerValue("one_is_fac",dataMap));
                levelOne.setPicPath(this.getStringValue("one_pic_path",dataMap));
                levelOne.setPcatId(this.getIntegerValue("one_pcat_id",dataMap));
                levelOne.setShopId(this.getIntegerValue("one_shop_id",dataMap));
                catTree.setLevelOne(levelOne);

                if(flag){
                    levelTwo = new CrmPrdCat();
                    levelTwo.setCatId(this.getIntegerValue("two_cat_id",dataMap));
                    levelTwo.setCatName(this.getStringValue("two_cat_name",dataMap));
                    levelTwo.setCatDesc(this.getStringValue("two_cat_desc",dataMap));
                    levelTwo.setIsFac(this.getIntegerValue("two_is_fac",dataMap));
                    levelTwo.setPicPath(this.getStringValue("two_pic_path",dataMap));
                    levelTwo.setPcatId(this.getIntegerValue("two_pcat_id",dataMap));
                    levelTwo.setShopId(this.getIntegerValue("two_shop_id",dataMap));
                    catTree.setLevelTwo(levelTwo);

                    crmPrdCat = new CrmPrdCat();
                    crmPrdCat.setCatId(this.getIntegerValue("cat_id",dataMap));
                    crmPrdCat.setCatName(this.getStringValue("cat_name",dataMap));
                    crmPrdCat.setCatDesc(this.getStringValue("cat_desc",dataMap));
                    crmPrdCat.setIsFac(this.getIntegerValue("is_fac",dataMap));
                    crmPrdCat.setPicPath(this.getStringValue("pic_path",dataMap));
                    crmPrdCat.setPcatId(this.getIntegerValue("pcat_id",dataMap));
                    crmPrdCat.setShopId(this.getIntegerValue("shop_id",dataMap));
                    catTree.setCrmPrdCat(crmPrdCat);
                }
                catTreeList.add(catTree);
            }
        }
        //保证和catIds顺序一致
        Iterator<Integer> it = catIds.iterator();
        List<CatTree> result = new ArrayList<>(catTreeList.size());
        int itValue;
        int catId;
        while(it.hasNext()){
            itValue = it.next().intValue();
            for(CatTree tree : catTreeList){
                catId = tree.getCatId();
                if(itValue == catId){
                    result.add(tree);
                }
            }
        }
        return result;
    }



    /**
     * 返回空的首页展示数据
     * @param screen
     * @return
     */
    private Screen emptyData(Screen screen){
        if(screen ==  null){
            screen = new Screen();
        }
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
     * 按工厂分组
     * @param kindList
     * @return
     */
    private LinkedHashMap<Integer,List<KindVo>> groupByFactory(List<KindVo> kindList){
        LinkedHashMap<Integer,List<KindVo>> result = new LinkedHashMap<>();
        Integer shopId;
        List<KindVo> dataList;
        for(KindVo kindVo : kindList){
            shopId = kindVo.getShopId();
            if(result.containsKey(shopId)){
                result.get(shopId).add(kindVo);
            } else{
                dataList = new ArrayList<>();
                dataList.add(kindVo);
                result.put(shopId,dataList);
            }
        }
        return result;
    }


    /**
     * 轮询展示每个工厂的品牌、系列
     * 不能有重名的品牌
     * @param groupDataMap
     * @param maxCount
     * @return
     */
    private List<KindVo> poll(LinkedHashMap<Integer,List<KindVo>> groupDataMap,int maxCount){
        List<KindVo> result = new ArrayList<>();
        int maxNum = maxCount;
        while(true){
            for(Map.Entry<Integer,List<KindVo>> entry : groupDataMap.entrySet()){
                if(maxCount == 0){
                    break;
                }
                for(KindVo data : entry.getValue()){
                    if(this.existSameName(result,data.getBrandName(),0)){
                        continue;
                    }
                    result.add(data);
                    maxCount--;
                    break;
                }
            }
            maxNum--;
            if(maxCount == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }

    /**
     * 当品牌数不足展示个数时，再次轮询获取展示的系列数（一个品牌对应多个系列的情况，需要展示满）
     * 不能有重名的品牌
     * @param groupDataMap
     * @param maxCount
     * @return
     */
    private List<KindVo> pollList(List<KindVo> result,LinkedHashMap<Integer,List<KindVo>> groupDataMap,int maxCount){
        int maxNum = maxCount;
        //工厂按品牌分组后的数据,key 为工厂id,value 为按品牌分组后的数据
        Map<Integer,LinkedHashMap<Integer,List<KindVo>>> hashMap = new HashMap<>();
        LinkedHashMap<Integer,List<KindVo>> groupByBrand;//按品牌分组数据，key为品牌ID，value为分类数据集合（即每个品牌下的系列集合）
        while(true){
            //首先从每个工厂选择一个系列，直到满足显示个数结束
            for(Map.Entry<Integer,List<KindVo>> entry : groupDataMap.entrySet()){
                if(maxCount == 0){
                    break;
                }
                List<KindVo> dataList = entry.getValue();
                //将每个工厂的数据按品牌分组，然后轮询从每个品牌中取一个系列
                groupByBrand = hashMap.get(entry.getKey());
                if(groupByBrand == null){//判断是否按品牌分组了，避免循环重复分组
                    groupByBrand = new LinkedHashMap<>();
                    for(KindVo kindVo : dataList){
                      Integer brandId = kindVo.getBrandId();
                      if(groupByBrand.containsKey(brandId)){
                          groupByBrand.get(brandId).add(kindVo);
                      }else{
                          List<KindVo> list = new ArrayList<>();
                          list.add(kindVo);
                          groupByBrand.put(brandId,list);
                      }
                    }
                    hashMap.put(entry.getKey(),groupByBrand);
                }
                //如果同一家工厂存在多个品牌的时候，需要轮询每个品牌直到满足展示需要的数量为止
                for(Map.Entry<Integer,List<KindVo>> bandEntry : groupByBrand.entrySet()){
                    boolean addFlag = false;
                    for(KindVo data : bandEntry.getValue()){
                        if(existSameName(result,data.getListName(),1)){
                            continue;
                        }
                        addFlag = true;
                        result.add(data);
                        maxCount--;
                        break;
                    }
                    if(addFlag){
                        break;
                    }
                }
            }
            maxNum--;
            if(maxCount == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }

    /**
     * 轮询显示分类
     * @param dataMap
     * @param maxCount
     * @return
     */
    private List<CatTreeDisplay> pollCatTreeDisplay(LinkedHashMap<Integer,List<CatTreeDisplay>> dataMap,int maxCount){
        List<CatTreeDisplay> result = new ArrayList<>();
        int maxNum = maxCount;
        while(true){
            for(Map.Entry<Integer,List<CatTreeDisplay>> entry : dataMap.entrySet()){
                if(maxCount == 0){
                    break;
                }
                for(CatTreeDisplay data : entry.getValue()){
                    if(this.existSameCatTreeDisplayName(result,data.getName())){
                        continue;
                    }
                    result.add(data);
                    maxCount--;
                    break;
                }
            }
            maxNum--;
            if(maxCount == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }


    /**
     * 判断集合中是否存在重名的数据
     * @param result
     * @param catName
     * @return
     */
    private boolean existSameCatTreeDisplayName(List<CatTreeDisplay> result,String catName){
        for(CatTreeDisplay data : result){
            if(StringUtils.equals(catName,data.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断集合中是否有重名的数据
     * @param dataList
     * @param name 名称
     * @param flag 用于判断是品牌名称还是系列名称 0：品牌民称，1：系列民称
     * @return
     */
    private boolean existSameName(List<KindVo> dataList,String name,int flag){
        String tmpName = "";
        for(KindVo kindVo : dataList){
            if(flag ==0){
                tmpName = kindVo.getBrandName();
            } else if(flag == 1){
                tmpName = kindVo.getListName();
            }
            if(StringUtils.equals(name,tmpName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断集合中是否存在重名的分类
     * @param dataList
     * @param name
     * @return
     */
    private boolean existSameCatName(List<CrmPrdCat> dataList,String name){
        for(CrmPrdCat data : dataList){
            if(StringUtils.equals(name,data.getCatName())){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据key取map中的Integer类型值
     * @param key
     * @param dataMap
     * @return
     */
    private Integer getIntegerValue(String key,Map<String,Object> dataMap){
        Object obj = dataMap.get(key);
        if(obj != null){
            return Integer.valueOf(obj.toString());
        }
        return null;
    }

    /**
     * 根据key取map中的String类型值
     * @param key
     * @param dataMap
     * @return
     */
    private String getStringValue(String key,Map<String,Object> dataMap){
        Object obj = dataMap.get(key);
        if(obj != null){
            return obj.toString();
        }
        return null;
    }

    /**
     * 去除重复的分类ID
     * @param dataMap
     * @return
     */
    private LinkedHashMap<Integer,Set<Integer>> removeDuplicateCat(LinkedHashMap<Integer,List<KindVo>> dataMap){
       LinkedHashMap<Integer,Set<Integer>> result = new LinkedHashMap<>();
       Set<Integer> idSet;
       for(Map.Entry<Integer,List<KindVo>> entry : dataMap.entrySet()){
           idSet = new LinkedHashSet<>();
           for(KindVo kindVo : entry.getValue()){
               if(kindVo.getCatId() != null){
                   idSet.add(kindVo.getCatId());
               }
           }
           if(CollectionUtils.isNotEmpty(idSet)){
               result.put(entry.getKey(),idSet);
           }
       }
       return result;
    }


    /**
     * 根据分类ID集合构造分类树形结构
     * @param catIdSet
     * @param flag 是否构造所有分类，如果为false则只构造一级分类
     * @return
     */
    private List<CatTreeDisplay> generateCatTreeDisplay(Set<Integer> catIdSet,boolean flag){
        List<CatTree> catTreeList = this.findCatTreeByCatId(catIdSet,flag);//根据分类ID查询分类机构树
        if(CollectionUtils.isEmpty(catIdSet)){
            return null;
        }
        List<CatTreeDisplay> result = new ArrayList<>();
        CatTreeDisplay catTreeDisplay;
        CrmPrdCat levelOne;//一级分类
        CrmPrdCat levelTwo;//二级分类
        boolean addFlag;
        for(CatTree catTree : catTreeList){
            levelOne = catTree.getLevelOne();
            //首页只展示平台分类
            if(levelOne.getIsFac() == null || levelOne.getIsFac() != 1){
                continue;
            }
            levelTwo = catTree.getLevelTwo();
            addFlag = true;
            for(int i = 0,size = result.size(); i < size; i++){
                catTreeDisplay = result.get(i);
                if(StringUtils.equals(catTreeDisplay.getName(),levelOne.getCatName())){//如果结果集存在相同的一级分类
                    addFlag = false;
                    this.addDifferentCat(catTreeDisplay,levelOne,1);//添加一级分类
                    if(flag){
                        this.addDifferentCat(catTreeDisplay,levelTwo,2);//添加二级分类
                    }
                    this.addDifferentCat(catTreeDisplay,catTree.getCrmPrdCat(),3);//添加当前分类
                    break;
                }
            }
            if(addFlag){
                catTreeDisplay = new CatTreeDisplay();
                catTreeDisplay.setShopId(levelOne.getShopId());
                catTreeDisplay.setId(levelOne.getCatId());
                catTreeDisplay.setName(levelOne.getCatName());
                catTreeDisplay.getLevelOneList().add(levelOne);
                if(flag && levelTwo != null){
                    catTreeDisplay.getLevelTwoList().add(levelTwo);
                }
                catTreeDisplay.getAllList().add(catTree.getCrmPrdCat());
                result.add(catTreeDisplay);
            }
        }
        return result;
    }


    /**
     * 添加不同的分类（分类名称和分类ID都相同的才是不同分类）
     * @param catTreeDisplay
     * @param crmPrdCat
     * @param level
     * @return
     */
    private boolean addDifferentCat(CatTreeDisplay catTreeDisplay,CrmPrdCat crmPrdCat,int level){
        if(crmPrdCat == null){
            return false;
        }
        List<CrmPrdCat> crmPrdCatList;
        if(level == 1){
            crmPrdCatList = catTreeDisplay.getLevelOneList();
        } else if(level == 2){
            crmPrdCatList = catTreeDisplay.getLevelTwoList();
        } else{
            crmPrdCatList = catTreeDisplay.getAllList();
        }
        boolean addFlag = true;
        for(CrmPrdCat data : crmPrdCatList){
            if(StringUtils.equals(data.getCatName(),crmPrdCat.getCatName())
                && crmPrdCat.getCatId().intValue() == data.getCatId().intValue()){
                addFlag = false;
                break;
            }
        }
        if(addFlag){
            crmPrdCatList.add(crmPrdCat);
        }
        return addFlag;
    }

    /**
     * 处理同名的分类数据（包括一级和二级分类）
     * @param showDataList
     * @param dataMap
     */
    private void handleSameCatName(List<CatTreeDisplay> showDataList,LinkedHashMap<Integer,List<CatTreeDisplay>> dataMap){
        List<CatTreeDisplay> dataList;
        for(CatTreeDisplay catTreeDisplay : showDataList){
            for(Map.Entry<Integer,List<CatTreeDisplay>> entry : dataMap.entrySet()){
                if(catTreeDisplay.getShopId().intValue() == entry.getKey().intValue()){//同一店铺不存在分类名称相同的数据，不需要处理
                    continue;
                }
                dataList = entry.getValue();
                for(CatTreeDisplay data : dataList){
                    if(data.getId().intValue() == catTreeDisplay.getId().intValue()){//同一分类不做处理
                        continue;
                    }
                    if(StringUtils.equals(data.getName(),catTreeDisplay.getName())){//一级分类重名
                        if(CollectionUtils.isNotEmpty(data.getLevelOneList())){
                            catTreeDisplay.getLevelOneList().addAll(data.getLevelOneList());//添加所有的一级分类
                        }
                        if(CollectionUtils.isNotEmpty(data.getLevelTwoList())){
                            catTreeDisplay.getLevelTwoList().addAll(data.getLevelTwoList());//添加所有的二级分类
                        }
                        if(CollectionUtils.isNotEmpty(data.getAllList())){
                            catTreeDisplay.getAllList().addAll(data.getAllList());//添加所有子分类
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取二级分类数据
     * @param catTreeDisplay
     * @return
     */
    private List<CrmPrdCat> getSecondCat(CatTreeDisplay catTreeDisplay,int maxCount){
        List<CrmPrdCat> crmPrdCatList = catTreeDisplay.getLevelTwoList();
        //根据工厂分类
        LinkedHashMap<Integer,List<CrmPrdCat>> groupByFacMap = new LinkedHashMap<>();
        Integer shopId;
        List<CrmPrdCat> dataList;
        for(CrmPrdCat crmPrdCat : crmPrdCatList){
            shopId = crmPrdCat.getShopId();
            if(groupByFacMap.containsKey(shopId)){
                groupByFacMap.get(shopId).add(crmPrdCat);
            } else{
                dataList = new ArrayList<>();
                dataList.add(crmPrdCat);
                groupByFacMap.put(shopId,dataList);
            }
        }
        //轮询展示每个工厂的
        List<CrmPrdCat> result = new ArrayList<>();
        int maxNum = maxCount;
        while(true){
            for(Map.Entry<Integer,List<CrmPrdCat>> entry : groupByFacMap.entrySet()){
                if(maxCount == 0){
                    break;
                }
                for(CrmPrdCat data : entry.getValue()){
                    if(this.existSameCatName(result,data.getCatName())){
                        continue;
                    }
                    result.add(data);
                    maxCount--;
                    break;
                }
            }
            maxNum--;
            if(maxCount == 0 || maxNum ==0){
                break;
            }
        }
        return result;
    }

    /**
     * 获取商品数据
     * @param catTreeDisplay
     * @param maxCount
     * @return
     */
    private List<GoodsEntity> getGoods(Long agentId,List<Integer> shopIds,CatTreeDisplay catTreeDisplay,int maxCount){
        Set<Integer> catIdSet = new HashSet<>();
        for(CrmPrdCat crmPrdCat : catTreeDisplay.getAllList()){
            catIdSet.add(crmPrdCat.getCatId());
        }
        List<Map<String,Object>> dataList = this.fastSearchDao.findGoodsByCatIds(agentId,null,catIdSet);
        if(CollectionUtils.isNotEmpty(dataList)){
            LinkedHashMap<Integer,List<Integer>> goodsByFacMap = new LinkedHashMap<>();
            List<Integer> goodsIdList;
            Integer shopId;
            String goodsIdStr;
            String[] array;
            if(agentId != null){
                for(Map<String,Object> dataMap : dataList){
                    shopId = this.getIntegerValue("shop_id",dataMap);
                    goodsIdList = new ArrayList<>();
                    for(Integer id : shopIds){
                        if(shopId.intValue() == id.intValue()){
                            goodsIdStr = this.getStringValue("goodsIds",dataMap);
                            array = goodsIdStr.split(",");
                            for(String str : array){
                                goodsIdList.add(Integer.valueOf(str));
                            }
                            goodsByFacMap.put(shopId,goodsIdList);
                        }
                    }
                }
            }else{
                for(Map<String,Object> dataMap : dataList){
                    shopId = this.getIntegerValue("shop_id",dataMap);
                    goodsIdList = new ArrayList<>();
                    goodsIdStr = this.getStringValue("goodsIds",dataMap);
                    array = goodsIdStr.split(",");
                    for(String str : array){
                        goodsIdList.add(Integer.valueOf(str));
                    }
                    goodsByFacMap.put(shopId,goodsIdList);
                }
            }
            return this.allocateGoods(maxCount,goodsByFacMap);
        }
        return null;
    }

    /**
     * 首页商品分类展示算法
     * @param count 需要展示的数据量
     * @param dataMap
     * @return
     */
    private List<GoodsEntity> allocateGoods(int count,LinkedHashMap<Integer,List<Integer>> dataMap){
        LinkedHashMap<Integer,List<Integer>> sortMap = new LinkedHashMap<>();//工厂分类数据
        List<Integer> dataList;
        //轮询去取数据
        int maxNum = count;
        while(true){
            for(Map.Entry<Integer,List<Integer>> entry : dataMap.entrySet()){
                if(count == 0){
                    break;
                }
                for(Integer goodsId : entry.getValue()){
                    if(sortMap.containsKey(entry.getKey())){
                        boolean addFlag = true;
                        for(Integer id : sortMap.get(entry.getKey())){
                            if(goodsId.intValue() == id.intValue()){
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            sortMap.get(entry.getKey()).add(goodsId);
                            count--;
                            break;
                        }
                    } else{
                        dataList = new ArrayList<>();
                        dataList.add(goodsId);
                        sortMap.put(entry.getKey(),dataList);
                        count--;
                        break;
                    }
                }
            }
            maxNum--;
            if(count == 0 || maxNum ==0){
                break;
            }
        }
        List<Integer> goodsIds = new ArrayList<>();
        for(Map.Entry<Integer,List<Integer>> entry :sortMap.entrySet()){
            goodsIds.addAll(entry.getValue());
        }
        List<GoodsEntity> goodsList = fastSearchDao.findGoodsByIds(goodsIds);
        if(CollectionUtils.isEmpty(goodsList)){
            return null;
        }
        List<GoodsEntity> result = new ArrayList<>(goodsList.size());
        //对显示顺序做处理
        for(Integer goodsId : goodsIds){
            for(GoodsEntity entity : goodsList){
                if(goodsId.intValue() == entity.getId().intValue()){
                    result.add(entity);
                }
            }
        }
        return result;
    }


    /**
     * 获取待轮询的分类数据
     * @param dataList 分类数据
     * @param flag 是否构造二级分类
     * @return
     */
    private LinkedHashMap<Integer,List<CatTreeDisplay>> getPollCatData(List<KindVo> dataList,boolean flag){
        List<KindVo> noEmptyCatList = this.removeEmptyCat(dataList);//去除空的分类数据
        //分类ID去重
        LinkedHashSet<Integer> catIdSet = new LinkedHashSet<>();
        for(KindVo kindVo : noEmptyCatList){
            catIdSet.add(kindVo.getCatId());
        }
        List<CatTreeDisplay> catTreeDisplayList = this.generateCatTreeDisplay(catIdSet,flag);//得到分类树形结构
        //根据工厂分类
        LinkedHashMap<Integer,List<CatTreeDisplay>> result = new LinkedHashMap<>();
        Integer shopId ;
        List<CatTreeDisplay> mapList;
        for(CatTreeDisplay tree : catTreeDisplayList){
            shopId = tree.getShopId();
            if(result.containsKey(shopId)){
                result.get(shopId).add(tree);
            }else{
                mapList = new ArrayList<>();
                mapList.add(tree);
                result.put(shopId,mapList);
            }
        }
        return result;
    }

    /**
     * 删除null的分类数据
     * @param dataList
     * @return
     */
    private List<KindVo> removeEmptyCat(List<KindVo> dataList){
        List<KindVo> result = new ArrayList<>();
        for(KindVo kindVo : dataList){
            if(kindVo.getCatId() != null){
                result.add(kindVo);
            }
        }
        return result;
    }
}
