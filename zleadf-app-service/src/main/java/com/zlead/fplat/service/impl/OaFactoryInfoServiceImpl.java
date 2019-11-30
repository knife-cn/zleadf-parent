package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.service.OaFactoryInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class OaFactoryInfoServiceImpl implements OaFactoryInfoService {
    @Autowired
    private OaFactoryInfoMapper facinfomapper;

    @Override
    public List<Map<String, Object>> unRelationFactoryListByAgentId(Long agentId, String key) {
        return facinfomapper.unRelationFactoryListByAgentId(agentId, key);
    }

    @Override
    public List<Map<String, Object>> relationFactoryListByAgentId(Long agentId) {
        return facinfomapper.relationFactoryListByAgentId(agentId);
    }

    @Override
    public Map<String, Object> findShopByFactoryId(Long factoryId) {
        return facinfomapper.findShopByFactoryId(factoryId);
    }

    @Override
    public List<Map<String, Object>> findShopAdsImgByFactoryId(Integer shopId) {
        return facinfomapper.findShopAdsImgByFactoryId(shopId);
    }

    @Override
    public List<Map<String, Object>> findBrandsByFactoryIdAndAgentId(Long factoryId, Long agentId) {
        return facinfomapper.findBrandsByFactoryIdAndAgentId(factoryId, agentId);
    }

    @Override
    public List<Map<String, Object>> findAllBrandsByFactoryIdAndAgentId(Long factoryId, Long agentId) {
        return facinfomapper.findAllBrandsByFactoryIdAndAgentId(factoryId, agentId);
    }

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌（跟着首页查询数据走）
     */
    public List<Map<String, Object>> newFindAllBrandsByFactoryIdAndAgentId(@Param("factoryId") Long factoryId, @Param("agentId") Long agentId){
        return facinfomapper.newFindAllBrandsByFactoryIdAndAgentId(factoryId,agentId);
    }

    @Override
    public List<Map<String, Object>> findAllBrandsByFactoryId(Long factId) {
        return facinfomapper.findAllBrandsByFactoryId(factId);
    }

    @Override
    public List<Map<String, Object>> findAllListsByFactoryIdAndBids(Long factoryId, Set<Long> bids) {
        return facinfomapper.findAllListsByFactoryIdAndBids(factoryId, bids);
    }

    @Override
    public List<Map<String, Object>> findListsByFactoryId(Long factoryId, Set<Long> ids,Long agentId) {
        return facinfomapper.findListsByFactoryId(factoryId, ids,agentId);
    }

    @Override
    public List<Map<String, Object>> findCatsByFactoryId(Long factoryId) {
        return facinfomapper.findCatsByFactoryId(factoryId);
    }

    @Override
    public List<Map<String, Object>> findAllCatsByFactoryId(Long factoryId) {
        return facinfomapper.findAllCatsByFactoryId(factoryId);
    }

    @Override
    public List<Map<String, Object>> findChildCatsByPactId(Long factoryId, Long pcatId) {
        return facinfomapper.findChildCatsByPactId(factoryId, pcatId);
    }

    @Override
    public List<Map<String, Object>> findAllChildCatsByPactId(Long factoryId, Long pcatId, Set<Long> listIds) {
        return facinfomapper.findAllChildCatsByPactId(factoryId, pcatId, listIds);
    }

    @Override
    public List<Map<String, Object>> findShopGoodsByCatIds(Long factoryId, Long agentId, Set<Long> brandIds, Set<Long> listIds, Set<Long> catIds) {
        return facinfomapper.findShopGoodsByCatIds(factoryId, agentId, brandIds, listIds, catIds);
    }

    @Override
    public int checkVcode(String vcode, Long factoryId) {
        return facinfomapper.checkVcode(vcode, factoryId);
    }

    @Override
    public OaFactoryInfo findFacByShopId(Integer shopId) {
        return facinfomapper.findFacByShopId(shopId);
    }

    @Override
    public OaFactoryInfo findFacByFactId(Integer factId) {
        return facinfomapper.findFacByFactId(factId);
    }

    /**
     * 关联工厂验证
     * @param vcode
     * @param factoryId
     * @return
     */
    @Override
    public Integer facRelevancy(@Param("vcode") String vcode,@Param("factoryName")String factoryName ){
        return facinfomapper.facRelevancy(vcode,factoryName);
    }


}
