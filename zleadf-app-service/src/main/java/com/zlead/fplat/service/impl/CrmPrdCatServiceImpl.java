package com.zlead.fplat.service.impl;

import com.zlead.entity.AclUserEntity;
import com.zlead.fplat.dao.CrmPrdCatMapper;
import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.service.CrmPrdCatService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CrmPrdCatServiceImpl implements CrmPrdCatService {
    @Autowired
    private CrmPrdCatMapper mapper;
    @Autowired
    private CrmPrdCatMapper crmPrdCatMapper;

    @Override
    public List<Map<String, Object>> findAllNameList() {
        return mapper.findAllNameList();
    }

    @Override
    public List<Map<String, Object>> findNameListByFactoryIdsAndKey(Set<Long> factoryIds, String key) {
        return mapper.findNameListByFactoryIdsAndKey(factoryIds, key);
    }

    @Override
    public Set<Long> findAllCatidsByfactoryIdsSet(Set<Long> factoryIds) {
        return mapper.findAllCatidsByfactoryIdsSet(factoryIds);
    }

    @Override
    public Set<Long> findChildCatids(Set<Long> catids) {
        return mapper.findChildCatids(catids);
    }


    @Override
    public Set<Long> findChildCatidsAPP(Set<Long> catids) {
        return mapper.findChildCatidsAPP(catids);
    }

    @Override
    public List<Map<String, Object>> findNameListByListIdsAndKey(Set<Long> listIds, String key) {
        return mapper.findNameListByListIdsAndKey(listIds, key);
    }
    
//    @Override
//    public List<Map<String, Object>> findNameListByAgent(Set<Long> listIds, String key,Integer agentId) {
//        return mapper.findNameListByAgent(listIds, key,agentId);
//    }

    @Override
    public List<Map<String, Object>> findNameListByListIdsAndKeyAPP(Set<Long> listIds, String key) {
        return mapper.findNameListByListIdsAndKeyAPP(listIds, key);
    }

    /**查询所有的分类
     * @author 廖巨会
     * @date 需要分类id集合
     * @param
     * @return
     */
    public List<CrmPrdCat> selectByCIds(List<Integer> selectByIds){
        return mapper.selectByCIds(selectByIds);
    }

    @Override
    public List<CrmPrdCat> findListByIds(Set<Long> ids) {
        return mapper.findListByIds(ids);
    }

    @Override
    public CrmPrdCat findOneById(Integer id) {
        return mapper.findOneById(id);
    }

    @Override
    public List<Map<String, Object>> findCatsByShopId(Long ownShopid) {
        return mapper.findCatsByShopId(ownShopid);
    }
    /**
     * 通过上级分类查询下级分类
     * @param agentId
     * @param catIds
     * @return
     */
    public List<CrmPrdCat> upCatIdQueryConTent(@Param("agentId")Long agentId, @Param("catIds")List<Long> catIds, @Param("factId") Long factId){
        return  mapper.upCatIdQueryConTent(agentId,catIds,factId);
    }

    /**
     * 获取工厂分类
     * @param agentId
     * @param factId
     * @return
     */
    public List<Map<String, Object>> newFindNameListByListIdsAndKey(@Param("agentId") Long agentId, @Param("factId") Long factId){
        return mapper.newFindNameListByListIdsAndKey(agentId,factId);
    }
    
    public Set<Long> getChildCatIds(Set<Long> ids, Set<Long> result,@Param("agentId") Long agentId, @Param("factId") Long factId) {
        if (CollectionUtils.isNotEmpty(ids)) {
            Set<Long> childIds = mapper.findChildCatids(ids);
            if (CollectionUtils.isNotEmpty(childIds)) {
                result.addAll(childIds);
                result = getChildCatIds(childIds, result  ,agentId,  factId);
            }
        }
        return result;
    }

    public Set<Long> newGetChildCatIds(@Param("agentId") Long agentId, @Param("factId") Long factId) {
        Set<Long> result = new HashSet<>();
        Set<Long> allCatIds = mapper.getAllCatIds(agentId, factId);

        Set<Long> allPcatIds = mapper.getAllPcatIds(agentId, factId);
        if (null != allCatIds && allCatIds.size() > 0) {
            result.addAll(allCatIds);
        }
        if (null != allPcatIds && allPcatIds.size() > 0) {
            result.addAll(allPcatIds);
        }
        return result;
    }
    /**
     * 优化查询工厂分类
     * @param agentId
     * @param factId
     * @return
     */
    @Override
    public List<Map<String, Object>> fastFindNameListByListIdsAndKey(Long agentId, Long factId) {
//        Set<Long> childCatIds = newGetChildCatIds(agentId, factId);
        List<Integer> allCatByAgentAndFacId = null;
        //所有分类ID 1级，2级，3级等
        if((agentId != null && agentId != 0) && (factId != null && factId != 0)){
            allCatByAgentAndFacId = getAllCatByAgentAndFacId(agentId, factId);
        }
        //取出所有 1级分类ID
        if (CollectionUtils.isEmpty(allCatByAgentAndFacId)){
            return null;
        }
        return mapper.fastFindNameListByListIdsAndKey(allCatByAgentAndFacId);
    }
    /**
     * 优化查询工厂分类
     * @param agentId
     * @param factoryId
     * @return
     */
    @Override
    public List<CrmPrdCat> selectFirstFacCatByAgentId(@Param("agentId") Long agentId, @Param("factId") Long factoryId){
        /**
    	Set<Long> catIds = newGetChildCatIds(agentId, factoryId);
        if(null != catIds && catIds.size()>0 )
        	return mapper.selectFirstFacCatByAgentId(catIds);
        return null;**/
        List<Integer> allCatByAgentAndFacId = null;
        //所有分类ID 1级，2级，3级等
        if((agentId != null && agentId != 0) && (factoryId != null && factoryId != 0)){
            allCatByAgentAndFacId = getAllCatByAgentAndFacId(agentId, factoryId);
        }
        if (null!=allCatByAgentAndFacId && allCatByAgentAndFacId.size()>0) {
            //取出一级分类ID
            //Set<Long> longs = allCatByAgentAndFacId.get(0);
            return mapper.selectFirstFacCatByAgentId(allCatByAgentAndFacId);
        }else{
            return null;
        }

    }

    @Override
    public List<Integer> getAllCatByAgentAndFacId(Long agentId, Long factId) {
        List<CrmPrdCat> crmPrdCats = new ArrayList<>();
        //全部分类的id
        List<Integer> allOneCatList = new ArrayList<>();
        //除一级分类以外的分类id
        List<Integer> catList = new ArrayList<>();
        //查询出所有的分类
        List<Map<String,Integer>> catAllList = mapper.getAllCatIdsWithGoods(agentId,factId);
        if(catAllList != null && catAllList.size() > 0){
            for (int i =0;i<catAllList.size();i++) {
                if (catAllList.get(i).get("pcat_id") == 0){
                    //将一级分类存入全部的分类集合中
                    allOneCatList.add(catAllList.get(i).get("cat_id"));
                }else{
                    //将除一级以外的存入集合
                    catList.add(catAllList.get(i).get("pcat_id"));
                }
            }
            //将一级以外的分类进入迭代器迭代
            List<CrmPrdCat> crmPrdCatsList = getCatIds(catList,crmPrdCats);
            for (CrmPrdCat crmprdcat: crmPrdCatsList) {
                //添加迭代的数据存入所有分类的集合
                allOneCatList.add(crmprdcat.getCatId());
            }
            //去除所有分类集合的重复数据
            List<Integer> collect = allOneCatList.stream().distinct().collect(Collectors.toList());
            return collect;
        }else{
            return null;
        }

    }

    /**
     * 递归查询上级分类
     * @param catid
     * @param result
     * @return
     */
    private List<CrmPrdCat> getCatIds(List<Integer> catid,List<CrmPrdCat> result){
        if (CollectionUtils.isNotEmpty(catid)) {
            List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.selectByCIds(catid);
            if (CollectionUtils.isNotEmpty(crmPrdCats)){
                result.addAll(crmPrdCats);
                List<Integer> collect = crmPrdCats.stream().map(CrmPrdCat::getPcatId).collect(Collectors.toList());
                result = getCatIds(collect, result);
            }
        }
        return  result;
    }
    /**
     * 查询当前分类下的所有下级分类
     */
    public List<CrmPrdCat> findChildByCatId(Integer catId,List<CrmPrdCat> acceptResultList){
        if(acceptResultList == null){
            return acceptResultList;
        }
        //递归查询出当前分类下的下级分类
        List<CrmPrdCat> childList = mapper.findChildByCatId(catId);
        if(childList != null && childList.size() > 0){
            acceptResultList.addAll(childList);
            for(CrmPrdCat crmPrdCat : childList){
                findChildByCatId(crmPrdCat.getCatId(),acceptResultList);
            }
        }
        return acceptResultList;
    }

    public List<CrmPrdCat> findChildAndOwnByCatName(Long agentId,Long factoryId,String catName){
        List<CrmPrdCat> acceptResultList = new ArrayList<>();
        List<CrmPrdCat> crmPrdCatList = mapper.findLevelOneByAgentOrName(factoryId,agentId,catName);
        if(CollectionUtils.isNotEmpty(crmPrdCatList)){
            for(CrmPrdCat crmPrdCat : crmPrdCatList){
                List<CrmPrdCat> childList = this.findChildByCatId(crmPrdCat.getCatId(),acceptResultList);
                if(CollectionUtils.isNotEmpty(childList)){
                    acceptResultList.addAll(childList);
                }
            }
            acceptResultList.addAll(crmPrdCatList);
        }
        return acceptResultList;
    }
    
    public CrmPrdCat getParentCat(Integer catId) {
        CrmPrdCat crmPrdCat = findOneById(catId);
        if (crmPrdCat != null && crmPrdCat.getPcatId() != 0) {
            crmPrdCat = getParentCat(crmPrdCat.getPcatId());
        }
        return crmPrdCat;
    }

    /**
     * 根据代理人ID、工厂ID、分类id查询自己和子分类
     * @param agentId 代理人ID
     * @param factoryId 工厂ID
     * @param catId  分类ID
     * @return
     */
    public List<CrmPrdCat> findChildAndOwnByCatId(Long agentId,Long factoryId,Long catId){
        List<CrmPrdCat> acceptResultList = new ArrayList<>();
        CrmPrdCat crmPrdCat = mapper.findOneByCondition(factoryId,agentId,catId);
        if(crmPrdCat != null){
            acceptResultList.add(crmPrdCat);//添加本身
            findChildByCatId(crmPrdCat.getCatId(),acceptResultList);//添加子分类
        }
        return acceptResultList;
    }

    /**
     * 根据代理人ID、工厂ID、分类id查询自己和子分类
     * @param agentId
     * @param factoryId
     * @param catId
     * @return
     */
    public Set<Long> findChildAndOwnById(Long agentId,Long factoryId,Long catId){
        Set<Long> dataSet = new HashSet<>();
        List<CrmPrdCat> dataList = this.findChildAndOwnByCatId(agentId,factoryId,catId);
        if(CollectionUtils.isNotEmpty(dataList)){
            for(CrmPrdCat crmPrdCat : dataList){
                dataSet.add(crmPrdCat.getCatId().longValue());
            }
        }
        return dataSet;
    }



}
