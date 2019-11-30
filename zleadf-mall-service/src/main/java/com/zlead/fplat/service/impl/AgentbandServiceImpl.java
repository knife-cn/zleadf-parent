package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.AgentbandListMapper;
import com.zlead.fplat.dao.AgentbandMapper;
import com.zlead.fplat.dao.CrmPrdListMapper;
import com.zlead.fplat.dao.PrdlistcatsMapper;
import com.zlead.fplat.entity.Agentband;
import com.zlead.fplat.entity.AgentbandList;
import com.zlead.fplat.entity.CrmPrdList;
import com.zlead.fplat.entity.Prdlistcats;
import com.zlead.fplat.service.AgentbandService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgentbandServiceImpl implements AgentbandService {
    @Autowired
    private AgentbandMapper agentbandMapper;

    @Autowired
    private AgentbandListMapper agentbandlistMapper;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private CrmPrdListMapper crmPrdListMapper;

    @Autowired
    private PrdlistcatsMapper prdlistcatsMapper;

    @Override
    public int insert(Agentband agentband) {
        return agentbandMapper.insert(agentband);
    }

    @Override
    public List<Map<String, Object>> findByAgentIdAndKey(Long factoryId, Long agentId, String key) {
        return agentbandMapper.findByAgentIdAndKey(factoryId, agentId, key);
    }

    @Override
    public List<Map<String, Object>> findByAgentIdAndKeyAPP(Long factoryId, String key) {
        return agentbandMapper.findByAgentIdAndKeyAPP(factoryId,key);
    }

    @Override
    public List<Integer> findListByAgentId(Long agentId) {
        List<Integer> ids = agentFacMapper.findIdByAgentId(agentId);
        List<Integer> facIds = agentFacMapper.findFacByagentId(agentId);

        List<Agentband> agentbands = agentbandMapper.selectByAgentFacIds(agentId.intValue(), facIds);
        System.out.println("agentbands 3: " + agentbands.toArray().toString());
        //查找系列 品牌ids
        //品牌上找到list的ids
        List<Integer> listIds = new ArrayList<>();

        for (Agentband agentband : agentbands) {
            String str = agentband.getListIds();
            if (!("" == str || null == str)) {
                String[] list = str.split(",");
                if (list.length > 0) {
                    for (String s : list) {
                        if (null != s || "" != s) {
                            listIds.add(Integer.parseInt(s));
                        }
                    }
                }
            }
        }
        return listIds;
    }

    @Override
    public List<Integer> findCatByAgentId(Long agentId) {
        //List<Integer> ids = agentFacMapper.findIdByAgentId(agentId);
        List<Integer> facIds = agentFacMapper.findFacByagentId(agentId);
        if (facIds != null && facIds.size() > 0) {
            List<AgentbandList> agentbandlists = agentbandlistMapper.selectByAgentFacIds(agentId.intValue(), facIds);
            System.out.println("agentbands 3: " + agentbandlists.toArray().toString());
            //当没有品牌的时候直接跳出，没必要在查系列和分类
            if(CollectionUtils.isEmpty(agentbandlists)){
                return null;
            }

            //查找系列 品牌ids
            //品牌上找到list的ids
            List<Integer> listIds = new ArrayList<>();

            for (AgentbandList agentbandlist : agentbandlists) {
                System.out.println("agentbands 3: findCatByAgentId agentbandlist.getListId():  " + agentbandlist.getListId());
                listIds.add(agentbandlist.getListId());
            }
            List<Prdlistcats> crmPrdListcats = prdlistcatsMapper.selectCatsByListIds(listIds,agentId);
            if (crmPrdListcats.isEmpty()) {
                List<CrmPrdList> crmPrdLists = crmPrdListMapper.selectByListIds(listIds.stream().distinct().collect(Collectors.toList()),facIds,agentId);
                if (crmPrdLists.isEmpty()) {
                    //系列为空   无关联的分类
                    return new ArrayList<>();
                }
                //查找分类（平台） 一级分类 系列关联分类  显示一级分类
                //查找系列 品牌ids
                //品牌上找到list的ids
                List<Integer> catIds = new ArrayList<>();
                for (CrmPrdList crmPrdList : crmPrdLists) {
                    String str = crmPrdList.getCatIds();
                    if (!("" == str || null == str)) {
                        String[] list = str.split(",");
                        if (list.length > 0) {
                            for (String s : list) {
                                if (null != s || "" != s) {
                                    catIds.add(Integer.parseInt(s));
                                }
                            }
                        }
                    }
                }
                return catIds;
            } else {
                //系列为空   无关联的分类
                //return new ArrayList<>();
                //查找分类（平台） 一级分类 系列关联分类  显示一级分类
                //查找系列 品牌ids
                //品牌上找到list的ids
                if (!crmPrdListcats.isEmpty()) {
                    List<Integer> catIds = new ArrayList<>();
                    for (Prdlistcats crmPrdListcat : crmPrdListcats) {
                        catIds.add(crmPrdListcat.getCatId());
                    }
                    return catIds;
                }
            }
            return null;
        } else {
            return null;
        }
    }
}
