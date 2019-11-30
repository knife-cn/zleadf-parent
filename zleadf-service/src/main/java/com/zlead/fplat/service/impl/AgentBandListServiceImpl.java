package com.zlead.fplat.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.zlead.dao.GoodsDao;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.Agentband;
import com.zlead.fplat.entity.AgentbandList;
import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.CrmPrdList;
import com.zlead.fplat.entity.CrmPrdModel;
import com.zlead.fplat.entity.vo.AgentBlmcVO;
import com.zlead.fplat.service.AgentBandListService;
import com.zlead.fplat.service.CrmPrdCatService;
import com.zlead.fplat.service.OaFactoryInfoService;
import com.zlead.util.JsonResult;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentBandListServiceImpl implements AgentBandListService {
    @Autowired
    private AgentbandListMapper agentbandListMapper;
    
    @Autowired
    private GoodscatMapper goodscatMapper;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private CrmPrdCatService crmPrdCatService;

    @Autowired
    private OaFactoryInfoService oaFactoryInfoService;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private AgentbandMapper agentbandMapper;

    @Autowired
    private CrmPrdCatMapper crmPrdCatMapper;

    @Autowired
    private CrmPrdListMapper crmPrdListMapper;

    @Autowired
    private CrmPrdModelMapper crmPrdModelMapper;
    @Autowired
    private CustbandMapper custbandMapper;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public int insertSelective(AgentbandList agentbandList) {
        return agentbandListMapper.insertSelective(agentbandList);
    }
    
    
     
    /**
     * @param factoryId
     * @param agentId
     * @param bKey
     * @param lKey
     * @param mKey
     * @param cKey
     * @param key
     * @return
     */
    @Override
    public Map<String, Object> queryBandListOrModelList(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key,String x ){
        List<Map<String, Object>> blist = new ArrayList<>();//前台显示品牌
        List<Map<String, Object>> llist = new ArrayList<>();//前台显示系列
        List<Map<String, Object>> mlist = new ArrayList<>();//前台显示型号
        List<Map<String, Object>> clist = new ArrayList<>();//前台显示分类
        Map<String, Object> term = new HashMap<>();
        Set<Long> brandSet = new HashSet<>();//品牌ID用于查询
        Set<Long> listSet  = new HashSet<>();//序列ID用于查询
        Set<Long> modelSet = new HashSet<>();//型号ID用于查询
        Set<Long> catSet   = new HashSet<>();//分类ID用于查询
        //1、根据前台转过来的品牌名称查询对应的品牌ID
        if(StringUtils.isNotBlank(bKey)){
            List<Map<String,Object>> dataList = custbandMapper.findByName(agentId,factoryId,bKey);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> data : dataList){
                    if(data.get("id") != null){
                        brandSet.add(Long.valueOf(data.get("id").toString()));
                    }
                }
            }
        }
        //2、根据前台传过来的序列名称查询对应的序列ID
        if(StringUtils.isNotBlank(lKey)){
            List<Map<String,Object>> dataList = crmPrdListMapper.findByName(agentId,factoryId,lKey);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> data : dataList){
                    if(data.get("id") != null){
                        listSet.add(Long.valueOf(data.get("id").toString()));
                    }
                }
            }
        }
        //3、根据前台传过来的型号名称查询对应的型号ID
        if(StringUtils.isNotBlank(mKey)){
            List<Map<String,Object>> dataList = crmPrdModelMapper.findByName(agentId,factoryId,mKey);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> data : dataList){
                    if(data.get("id") != null){
                        modelSet.add(Long.valueOf(data.get("id").toString()));
                    }
                }
            }
        }
        //4、根据前台传过来的分类名称查询对应的分类ID（包含下级分类）
        Set<Long> filterCatSet = new HashSet<>();//一级分类ID，用于商品归属多个分类，从首页或店铺页点击分类进入二级页面时只显示点击的分类名称，其他不显示，
        boolean filterLevelOneCat = false;
        if(StringUtils.isNotBlank(cKey)){
            filterLevelOneCat = true;
            //根据前台传过来的分类名称查询对应的分类ID
            List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.findLevelOneByAgentOrName(factoryId,agentId,cKey);
            if(CollectionUtils.isNotEmpty(crmPrdCats)){
                for(CrmPrdCat crmPrdCat : crmPrdCats){
                    if(crmPrdCat.getPcatId() == 0){//一级分类
                        filterCatSet.add(crmPrdCat.getCatId().longValue());
                    } else{
                        CrmPrdCat levelOneCat = this.getParentCat(crmPrdCat.getCatId());
                        if(levelOneCat != null){
                            filterCatSet.add(levelOneCat.getCatId().longValue());
                        }
                    }
                }
            }
            List<CrmPrdCat> crmPrdCatList = crmPrdCatService.findChildAndOwnByCatName(agentId,factoryId,cKey);
            if(CollectionUtils.isNotEmpty(crmPrdCatList)){
                for(CrmPrdCat crmPrdCat : crmPrdCatList){
                    catSet.add(crmPrdCat.getCatId().longValue());
                }
            }
        }
        List<Map<String,Object>> kindList = goodsDao.findGoodsKindByCondition(agentId,factoryId,brandSet,listSet,modelSet,catSet,key);
        if(CollectionUtils.isNotEmpty(kindList)){
            brandSet.clear();
            listSet.clear();
            modelSet.clear();
            catSet.clear();
            for(Map<String,Object> dataMap : kindList){
                this.addId(dataMap,"brand_id",brandSet);
                this.addId(dataMap,"list_id",listSet);
                this.addId(dataMap,"model_id",modelSet);
                this.addId(dataMap,"cat_id",catSet);
            }
        }
        //添加品牌
        if(CollectionUtils.isNotEmpty(brandSet)){
            List<Map<String,Object>> dataList = custbandMapper.findNameByIds(agentId,brandSet);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> dataMap : dataList){
                    String id = dataMap.get("id").toString();
                    String name = dataMap.get("name").toString();
                    if (StringUtils.isNotBlank(name)) {
                        boolean addFlag = true;
                        for(Map<String,Object> map : blist){
                            if(name.equals(map.get("band_name"))){
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("band_id",id);
                            map.put("band_name",name);
                            blist.add(map);
                        }
                    }
                }
            }
        }

        //添加品牌
        if(CollectionUtils.isNotEmpty(listSet)){
            List<Map<String,Object>> dataList = crmPrdListMapper.findNameByIds(agentId,listSet);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> dataMap : dataList){
                    String listId = dataMap.get("id").toString();
                    String listName = dataMap.get("name").toString();
                    if (listId != null && StringUtils.isNotBlank(listName)) {
                        boolean addFlag = true;
                        for(Map<String,Object> map : llist){
                            if(listName.equals(map.get("name"))){
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("id",listId);
                            map.put("name",listName);
                            llist.add(map);
                        }
                    }
                }
            }
        }
        //添加型号
        if(CollectionUtils.isNotEmpty(modelSet)){
            List<Map<String,Object>> dataList = crmPrdModelMapper.findNameByIds(agentId,modelSet);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> dataMap : dataList){
                    String modelId = dataMap.get("id").toString();
                    String modelName = dataMap.get("name").toString();
                    if (modelId != null && StringUtils.isNotBlank(modelName)) {
                        boolean addFlag = true;
                        for(Map<String,Object> map : mlist){
                            if(modelName.equals(map.get("name"))){
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("id",modelId);
                            map.put("name",modelName);
                            mlist.add(map);
                        }
                    }
                }
            }
        }
        //分类排序
        List<Long> sortCatList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(catSet)){
            List<Map<String,Object>> dataList = this.crmPrdCatMapper.findNameByIds(agentId,catSet);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> dataMap : dataList){
                    Long id = Long.valueOf(dataMap.get("id").toString());
                    sortCatList.add(id);
                }
            }
        }
        //添加分类（分类只展示一级分类，需要向上递归查询一级分类，
        // 首页进去只展示平台分类，店铺进去展示平台分类和自定义分类，且平台分类显示备注名称，自定义分类显示分类名称）
        //二级分类显示分类名称还是分类备注（当选择的工厂的时候显示的是分类备注）
        boolean showCatNameFlag = true;
        if(factoryId != null){
            showCatNameFlag = false;
        }
        if(CollectionUtils.isNotEmpty(sortCatList)){
            for(Long id : sortCatList){
                CrmPrdCat crmPrdCat = this.getParentCat(id.intValue());//得到一级分类
                if(crmPrdCat != null){
                    if(filterLevelOneCat){//只显示首页或店铺也传过来的分类名称
                        if(!filterCatSet.contains(crmPrdCat.getCatId().longValue())){
                            continue;
                        }
                    }
                    if(factoryId == null && 1 != crmPrdCat.getIsFac()){//首页进去自定义分类不展示
                        continue;
                    }
                    Integer catId = crmPrdCat.getCatId();
                    String catName = null;
                    if(showCatNameFlag){
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
                        for(Map<String,Object> map : clist){
                            if(catName.equals(map.get("name"))){
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("id",catId);
                            map.put("name",catName);
                            clist.add(map);
                        }
                    }
                }
            }
        }

        /*Collections.sort(clist, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                int id1 = Integer.parseInt(o1.get("id").toString());
                int id2 = Integer.parseInt(o2.get("id").toString());
                if(id1 > id2){
                    return 1;
                } else if(id1 == id2){
                    return 0;
                } else {
                    return -1;
                }
            }
        });*/

        term.put("blist", blist);
        term.put("llist", llist);
        term.put("mlist", mlist);
        term.put("clist", clist);
    	return term;
    }

    private void addId(Map<String,Object> dataMap,String key,Set<Long> dataSet){
        Object obj = dataMap.get(key);
        if(obj != null){
            dataSet.add(Long.valueOf(obj.toString()));
        }
    }
    
    private Set<Long> splitString(String ids){
        Set<Long> idSet = new HashSet<>();
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            for(String id : idArray){
                idSet.add(Long.valueOf(id));
            }
        }
        return idSet;
    }
    
    private CrmPrdCat getParentCat(Integer catId) {
        CrmPrdCat crmPrdCat = crmPrdCatService.findOneById(catId);
        if (crmPrdCat != null && crmPrdCat.getPcatId() != 0) {
            crmPrdCat = getParentCat(crmPrdCat.getPcatId());
        }
        return crmPrdCat;
    }

    /**
     * 通过代理系列，代理商id，工厂id查询当前代理系列是否存在
     */
    public AgentbandList queryAgencyList(@Param("agentId") Integer agentId, @Param("factId") Integer factId, @Param("listId")Integer listId){
        return agentbandListMapper.queryAgencyList(agentId,factId,listId);
    }
}
