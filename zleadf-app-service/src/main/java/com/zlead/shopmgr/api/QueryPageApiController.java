package com.zlead.shopmgr.api;

import com.zlead.entity.MemberEntity;
import com.zlead.fplat.controller.QueryPageController;
import com.zlead.entity.QueryHistoryWords;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.service.*;
import com.zlead.service.GoodsBgService;
import com.zlead.shopmgr.service.QueryHistoryWordsService;
import com.zlead.util.JsonResult;
import com.zlead.utils.LoginUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 搜素条件 / 搜索页面  APP
 */
@RestController
@RequestMapping(value = "/api/query")
public class QueryPageApiController {
    private static final Logger logger = LoggerFactory.getLogger(QueryPageApiController.class);
    @Autowired
    private AgentbandService agentbandService;//代理商和品牌接口
    @Autowired
    private CustbandService custbandService;//品牌接口
    @Autowired
    private CrmPrdListService crmPrdListService;//系列接口
    @Autowired
    private CrmPrdModelService crmPrdModelService;//型号接口
    @Autowired
    private CrmPrdCatService crmPrdCatService;//分类接口
    @Autowired
    private AgentFacService agentFacService;//代理商和工厂的关联接口
    @Autowired
    private GoodsBgService goodsBgService;//商品接口
    @Autowired
    private LoginUtil loginUtil;
    @Autowired
    private QueryHistoryWordsService queryHistoryWordsService;//查询历史记录接口

    @Autowired
    private OaFactoryInfoService oaFactoryInfoService;

    /**
     * 查找搜索条件
     * 品牌+系列+型号+分类
     */
    @RequestMapping(value = "term", method = RequestMethod.GET)
    public JsonResult term(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        Integer code = null;
        Boolean success = null;
        String message = null;
        Map<String, Object> dataMap = new HashMap<>();
        Set<Long> bids = new HashSet<>();//品牌id
        Set<Long> lids = new HashSet<>();//系列id
        Set<Long> mids = new HashSet<>();//型号id
        Set<Long> cids = new HashSet<>();//分类id
        List<Map<String, Object>> blist = new ArrayList<>();//品牌
        List<Map<String, Object>> llist = new ArrayList<>();//系列
        List<Map<String, Object>> mlist = new ArrayList<>();//型号
        List<Map<String, Object>> clist = new ArrayList<>();//分类
        try {
            //获取当前登录用户所属代理商agent_id
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
                if (member.getAgentId() != null && member.getAgentId() != 0) {//已关联代理商
                    //获取该代理商所关联的品牌
                    List<Map<String, Object>> brandAndLists = agentbandService.findByAgentIdAndKey(null, member.getAgentId(), null);
                    if (CollectionUtils.isNotEmpty(brandAndLists)) {
                        for (Map<String, Object> bMap : brandAndLists) {
                            bids.add(Long.parseLong(bMap.get("band_id").toString()));
                            Map<String, Object> brandsMap = new LinkedHashMap<>();
                            brandsMap.put("band_id", bMap.get("band_id"));
                            brandsMap.put("name", bMap.get("band_name"));
                            //获取当前品牌关联的系列ids
                            if (bMap.get("list_ids") != null) {
                                String list_ids = bMap.get("list_ids").toString();
                                if (StringUtils.isNotBlank(list_ids)) {
                                    for (String id : list_ids.split(",")) {
                                        lids.add(Long.parseLong(id));
                                    }
                                }
                            }
                            blist.add(brandsMap);
                        }
                        //根据系列id获取系列list
                        if (CollectionUtils.isNotEmpty(lids)) {
                            llist = crmPrdListService.findNameListByIdsAndKey(lids, null);
                            lids.clear();
                            if (CollectionUtils.isNotEmpty(llist)) {
                                for (Map<String, Object> listMap : llist) {
                                    if (listMap.get("id") != null) {
                                        lids.add(Long.parseLong(listMap.get("id").toString()));
                                    }
                                }
                            }
                            //根据系列id获取型号list
                            mlist = crmPrdModelService.findNameListByListIdsAndKey(lids, null);
                            if (CollectionUtils.isNotEmpty(mlist)) {
                                for (Map<String, Object> modelMap : mlist) {
                                    if (modelMap.get("id") != null) {
                                        mids.add(Long.parseLong(modelMap.get("id").toString()));
                                    }
                                }
                            }
                            //根据系列id获取分类list
                            List<Map<String, Object>> catlist = crmPrdCatService.findNameListByListIdsAndKey(lids, null);
                            if (CollectionUtils.isNotEmpty(catlist)) {
                                for (Map<String, Object> catMap : catlist) {
                                    if (catMap.get("id") != null) {
                                        cids.add(Long.parseLong(catMap.get("id").toString()));
                                    }
                                    //搜索条件只展示1级分类
                                    if (catMap.get("pcat_id") != null && "0".equals(catMap.get("pcat_id").toString())) {
                                        clist.add(catMap);
                                    }
                                }
                            }
                        }
                    }
                    code = 1;
                    success = true;
                } else {
                    code = 2;
                    success = false;
                    message = "未关联代理商";
                }
            } else {
                code = 2;
                success = false;
                message = "未登录用户";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            code = 2;
            success = false;
            message = "获取搜索条件失败";
        }
        if (success) {
            //开始去重
            List<String> temp = new ArrayList<>();//用来存放临时名字
            List<Map<String, Object>> _blist = new ArrayList<>();
            List<Map<String, Object>> _llist = new ArrayList<>();
            List<Map<String, Object>> _mlist = new ArrayList<>();
            List<Map<String, Object>> _clist = new ArrayList<>();
            //品牌去重
            if (CollectionUtils.isNotEmpty(blist)) {
                for (Map<String, Object> map : blist) {
                    map.put("show", 1);
                    String name = String.valueOf(map.get("name"));
                    String bid = String.valueOf(map.get("band_id"));
                    if (!temp.contains(name)) {
                        temp.add(name);
                        _blist.add(map);
                    } else {
                        for (Map<String, Object> _map : _blist) {
                            String _name = String.valueOf(_map.get("name"));
                            String _bid = String.valueOf(_map.get("band_id"));
                            if (_name.equals(name)) {
                                _map.put("band_id", _bid + "_" + bid);
                            }
                        }
                    }
                }
                temp.clear();
            }
            //系列去重
            if (CollectionUtils.isNotEmpty(llist)) {
                for (Map<String, Object> map : llist) {
                    map.put("show", 1);
                    String id = String.valueOf(map.get("id"));
                    String name = String.valueOf(map.get("name"));
                    String bid = String.valueOf(map.get("band_id"));
                    if (!temp.contains(name)) {
                        temp.add(name);
                        _llist.add(map);
                    } else {
                        for (Map<String, Object> _map : _llist) {
                            String _id = String.valueOf(_map.get("id"));
                            String _name = String.valueOf(_map.get("name"));
                            String _bid = String.valueOf(_map.get("band_id"));
                            if (_name.equals(name)) {
                                _map.put("id", _id + "_" + id);
                                _map.put("band_id", _bid + "_" + bid);
                            }
                        }
                    }
                }
                temp.clear();
            }
            //型号去重
            if (CollectionUtils.isNotEmpty(mlist)) {
                for (Map<String, Object> map : mlist) {
                    map.put("show", 1);
                    String id = String.valueOf(map.get("id"));
                    String name = String.valueOf(map.get("name"));
                    String lid = String.valueOf(map.get("list_id"));
                    if (!temp.contains(name)) {
                        temp.add(name);
                        _mlist.add(map);
                    } else {
                        for (Map<String, Object> _map : _mlist) {
                            String _id = String.valueOf(_map.get("id"));
                            String _name = String.valueOf(_map.get("name"));
                            String _lid = String.valueOf(_map.get("list_id"));
                            if (_name.equals(name)) {
                                _map.put("id", _id + "_" + id);
                                _map.put("list_id", _lid + "_" + lid);
                            }
                        }
                    }
                }
                temp.clear();
            }
            //分类去重
            if (CollectionUtils.isNotEmpty(clist)) {
                for (Map<String, Object> map : clist) {
                    map.put("show", 1);
                    String id = String.valueOf(map.get("id"));
                    String name = String.valueOf(map.get("name"));
                    String lid = String.valueOf(map.get("list_id"));
                    if (!temp.contains(name)) {
                        temp.add(name);
                        _clist.add(map);
                    } else {
                        for (Map<String, Object> _map : _clist) {
                            String _id = String.valueOf(_map.get("id"));
                            String _name = String.valueOf(_map.get("name"));
                            String _lid = String.valueOf(_map.get("list_id"));
                            if (_name.equals(name)) {
                                _map.put("id", _id + "_" + id);
                                _map.put("list_id", _lid + "_" + lid);
                            }
                        }
                    }
                }
            }
            dataMap.put("blist", _blist);
            dataMap.put("llist", _llist);
            dataMap.put("mlist", _mlist);
            dataMap.put("clist", _clist);
            result.setData(dataMap);
        }
        result.setCode(code);
        result.setSuccess(success);
        result.setMessage(message);
        return result;
    }

    /**
     * @param key 关键词
     * @param b   品牌
     * @param l   系列
     * @param m   型号
     * @param c   分类
     * @param p   当前页
     * @param s   每页条数
     * @return 检索出来的商品
     */
    @RequestMapping(value = "goods1", method = RequestMethod.GET)
    public JsonResult goods(HttpServletRequest request,
                            @RequestParam(value = "key", required = false) String key,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "l", required = false) String l,
                            @RequestParam(value = "m", required = false) String m,
                            @RequestParam(value = "c", required = false) String c,
                            @RequestParam(value = "p", defaultValue = "1") Integer p,
                            @RequestParam(value = "s", defaultValue = "20") Integer s) {
        JsonResult result = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
//                if (member.getAgentId() != null && member.getAgentId() != 0) {
//                    Long agentId = member.getAgentId();
                    Map<String, Object> dataMap = new HashMap<>();
                    Set<Long> bids = new HashSet<>();//品牌id
                    Set<Long> lids = new HashSet<>();//系列id
                    Set<Long> mids = new HashSet<>();//型号id
                    Set<Long> cids = new HashSet<>();//分类id
                    List<Map<String, Object>> blist = new ArrayList<>();//品牌
                    List<Map<String, Object>> llist = new ArrayList<>();//系列
                    List<Map<String, Object>> mlist = new ArrayList<>();//型号
                    List<Map<String, Object>> clist = new ArrayList<>();//分类
                    int count = 0;
                    Map<String, Object> term = new HashMap<>();
//                    List<Map<String, Object>> goodsList = new ArrayList<>();
                    //判读是否已关联工厂
//                    List<Map<String, Object>> faclist = agentFacService.findFactoryIdListByAgentAPP(agentId, f);
//                    if (CollectionUtils.isNotEmpty(faclist)) {
                        //==============================查找全部关联的条件start==================================
                    OaFactoryInfo fac = oaFactoryInfoService.findFacByShopId(member.getOwnShopid().intValue());
                    Long f = fac.getFactId().longValue();
                //获取该代理商所关联的品牌
                        blist = agentbandService.findByAgentIdAndKeyAPP(f, b);
                        if (CollectionUtils.isNotEmpty(blist)) {
                            Set<Long> listIdsSet = new HashSet<>();
                            for (Map<String, Object> bMap : blist) {
                                if (bMap.get("band_id") != null) {
                                    bids.add(Long.parseLong(bMap.get("band_id").toString()));
                                }
                                //获取当前品牌关联的系列ids
                                if (bMap.get("list_ids") != null) {
                                    String list_ids = bMap.get("list_ids").toString();
                                    if (StringUtils.isNotBlank(list_ids)) {
                                        for (String id : list_ids.split(",")) {
                                            listIdsSet.add(Long.parseLong(id));
                                        }
                                    }
                                }
                            }
                            //根据系列id获取系列list
                            if (CollectionUtils.isNotEmpty(listIdsSet)) {
                                llist = crmPrdListService.findNameListByIdsAndKeyAPP(listIdsSet, l);
                                if (CollectionUtils.isNotEmpty(llist)) {
                                    for (Map<String, Object> listMap : llist) {
                                        if (listMap.get("id") != null) {
                                            lids.add(Long.parseLong(listMap.get("id").toString()));
                                        }
                                    }
                                    //根据系列id获取型号list
                                    mlist = crmPrdModelService.findNameListByListIdsAndKeyAPP(lids, m);
                                    if (CollectionUtils.isNotEmpty(mlist)) {
                                        for (Map<String, Object> modelMap : mlist) {
                                            if (modelMap.get("id") != null) {
                                                mids.add(Long.parseLong(modelMap.get("id").toString()));
                                            }
                                        }
                                    }
                                    //根据系列id获取分类list
                                    List<Map<String, Object>> catlist = crmPrdCatService.findNameListByListIdsAndKeyAPP(lids, c);
                                    if (CollectionUtils.isNotEmpty(catlist)) {
                                        for (Map<String, Object> catMap : catlist) {
                                            if (catMap.get("id") != null) {
                                                cids.add(Long.parseLong(catMap.get("id").toString()));
                                            }
                                            //搜索条件只展示平台的1级分类
                                            if (catMap.get("pcat_id") != null
                                                    && catMap.get("is_fac") != null
                                                    && "0".equals(catMap.get("pcat_id").toString())
                                                    && "1".equals(catMap.get("is_fac").toString())) {
                                                clist.add(catMap);
                                            }
                                        }
                                        //递归查找下级分类
                                        cids = getChildCatIds(cids, cids);
                                    }
                                }
                            }
                        }
                        //==============================查找全部关联的条件end==================================
                        System.out.println("bids=" + bids.toString());
                        System.out.println("lids=" + lids.toString());
                        System.out.println("mids=" + mids.toString());
                        System.out.println("cids=" + cids.toString());
                        //查出来所有商品关联的品牌、系列、型号、分类
                        List<Map<String, Object>> terms = goodsBgService.queryAllListByTermAPP(f, bids, lids, mids, cids, key);
                        if (CollectionUtils.isNotEmpty(terms)) {
                            count = terms.size();
//                            goodsList = goodsBgService.queryListByTermAPP(f, bids, lids, mids, cids, key, (p - 1) * s, s);
                            bids.clear();
                            lids.clear();
                            mids.clear();
                            cids.clear();
                            for (Map<String, Object> tMap : terms) {
                                if (tMap.get("brand_id") != null) {
                                    Long bid = Long.parseLong(tMap.get("brand_id").toString());
                                    bids.add(bid);
                                }
                                if (tMap.get("list_id") != null) {
                                    Long lid = Long.parseLong(tMap.get("list_id").toString());
                                    lids.add(lid);
                                }
                                if (tMap.get("model_id") != null) {
                                    Long mid = Long.parseLong(tMap.get("model_id").toString());
                                    mids.add(mid);
                                }
                                if (tMap.get("cat_ids") != null) {
                                    String cidstr = tMap.get("cat_ids").toString();
                                    String[] cidsArray = cidstr.split(",");
                                    for (String cid : cidsArray) {
                                        cids.add(Long.parseLong(cid));
                                    }
                                }
                            }
                            blist = blist.stream().filter(map -> {
                                Long id = Long.parseLong(map.get("band_id").toString());
                                return bids.contains(id);
                            }).collect(Collectors.toList());
                            llist = llist.stream().filter(map -> {
                                Long id = Long.parseLong(map.get("id").toString());
                                return lids.contains(id);
                            }).collect(Collectors.toList());
                            mlist = mlist.stream().filter(map -> {
                                Long id = Long.parseLong(map.get("id").toString());
                                return mids.contains(id);
                            }).collect(Collectors.toList());
                            Set<Long> finalCids = cids;
                            clist = clist.stream().filter(map -> {
                                Long id = Long.parseLong(map.get("id").toString());
                                return finalCids.contains(id);
                            }).collect(Collectors.toList());
                        }
//                    }
//                    品牌
                    term.put("blist", blist);
//                    系列
                    term.put("llist", llist);
//                    型号
                    term.put("mlist", mlist);
//                    分类
                    term.put("clist", clist);

                    dataMap.put("count", count);
                    //查出来所有商品关联的品牌、系列、型号、分类
//                    dataMap.put("goods", goodsList);
                    dataMap.put("term", term);
                    result.setCode(1);
                    result.setSuccess(true);
                    result.setData(dataMap);
//                } else {
//                    result.setCode(2);
//                    result.setSuccess(false);
//                    result.setMessage("未关联代理商");
//                }
            } else {
                result.setCode(3);
                result.setSuccess(false);
                result.setMessage("未登录");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setCode(2);
            result.setSuccess(false);
            result.setMessage("搜索商品数据失败");
        }
        return result;
    }

    private Set<Long> getChildCatIds(Set<Long> ids, Set<Long> result) {
        if (CollectionUtils.isNotEmpty(ids)) {
            Set<Long> childIds = crmPrdCatService.findChildCatidsAPP(ids);
            if (CollectionUtils.isNotEmpty(childIds)) {
                result.addAll(childIds);
                result = getChildCatIds(childIds, result);
            }
        }
        return result;
    }


    /**
     * @param key 关键词
     * @param b   品牌
     * @param l   系列
     * @param m   型号
     * @param c   分类
     * @param p   当前页
     * @param s   每页条数
     * @return 检索出来的商品
     */
    @RequestMapping(value = "goods", method = RequestMethod.GET)
    public JsonResult getGoods(HttpServletRequest request,
                            @RequestParam(value = "key", required = false) String key,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "l", required = false) String l,
                            @RequestParam(value = "m", required = false) String m,
                            @RequestParam(value = "c", required = false) String c,
                            @RequestParam(value = "p", defaultValue = "1") Integer p,
                            @RequestParam(value = "s", defaultValue = "20") Integer s) {
        JsonResult result = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
                Map<String,Object> term = new HashMap<>();
                Map<String,Object> map = new HashMap<>();
                List<Map<String, Object>> clist = crmPrdCatService.findCatsByShopId(member.getOwnShopid());
                //自定义分类显示分类名称，平台分类显示备注名称
                if(CollectionUtils.isNotEmpty(clist)){
                    for(Map<String,Object> dataMap : clist){
                        if(dataMap.get("is_fac") != null && "1".equals(dataMap.get("is_fac").toString())){
                            dataMap.put("name",dataMap.get("cat_desc").toString());
                        }
                    }
                }
                List<Map<String, Object>> blist = custbandService.findBrandsByShopId(member.getOwnShopid());
                List<Map<String, Object>> llist = crmPrdListService.findListsByShopId(member.getOwnShopid());
                List<Map<String, Object>> mlist = crmPrdModelService.findModelsByShopId(member.getOwnShopid());
                term.put("clist",clist);
                term.put("blist",blist);
                term.put("llist",llist);
                term.put("mlist",mlist);
                map.put("term",term);
                result = new JsonResult(1,"搜索列表",map,true);
            } else {
                result.setCode(3);
                result.setSuccess(false);
                result.setMessage("未登录");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setCode(2);
            result.setSuccess(false);
            result.setMessage("搜索商品数据失败");
        }
        return result;
    }

    /**
     * 通过用户id查询用户所有的历史搜索词
     * @param start
     * @param end
     * @param request
     * @return
     */
    @RequestMapping(value = "queryWords", method = RequestMethod.GET)
    public JsonResult queryWords(@RequestParam(value = "start", required = false)Integer start,
                                 @RequestParam(value = "end", required = false) Integer end,
                                 HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member != null){
            int memberId = member.getId().intValue();
            if(memberId == 0){
               return jsonResult = new JsonResult(2,"用户未登入",false);
            }
            List<QueryHistoryWords> queryHistoryWords = queryHistoryWordsService.queryWords(memberId, start, end);
            if (queryHistoryWords != null){
                if (queryHistoryWords.size() == 0){
                    jsonResult = new JsonResult(1,"查询成功","",true);
                }else{
                    jsonResult = new JsonResult(1,"查询成功",queryHistoryWords,true);
                }
            }else {
                jsonResult = new JsonResult(2,"查询失败",false);
            }
        }else {
            jsonResult = new JsonResult(3,"未登入",false);
        }
        return jsonResult;
    }

    /**
     * 添加用户历史搜索词，用户id以获取
     */
    @RequestMapping(value = "insertSelective", method = RequestMethod.GET)
    public  JsonResult insertSelective(HttpServletRequest request,QueryHistoryWords queryHistoryWords){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        int i = 0 ;
        if (member != null){
        try {
                int memberId =  member.getId().intValue();
                if (memberId == 0){
                    return jsonResult = new JsonResult(2,"用户未登入",false);
                }
                queryHistoryWords.setMemberId(memberId);//获取用户id
                i = queryHistoryWordsService.insertSelective(queryHistoryWords);
                if(i > 0){
                    jsonResult = new JsonResult(1,"添加成功",i,true);
                }else{
                    jsonResult = new JsonResult(1,"数据不全，添加失败",i,true);
                }
            }catch (Exception e){
                jsonResult = new JsonResult(2, "添加凭证失败", false);
                return jsonResult;
            }
        }else {
            jsonResult = new JsonResult(3,"未登入",false);
        }
        return jsonResult;
    }

    /**
     * 清空用户的历史搜索词
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public  JsonResult delete(HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null){
            try {
               int menberId = member.getId().intValue();//获取用户id
                if (menberId == 0){
                    return jsonResult = new JsonResult(3,"未登入",false);
                }
                int delete = queryHistoryWordsService.delete(menberId);
                if (delete > 0){
                    jsonResult = new JsonResult(1,"删除成功",delete,true);
                }else {
                    jsonResult = new JsonResult(1,"获取用户失败，删除失败",delete,true);
                }
            }catch (Exception e){
                jsonResult = new JsonResult(2,"删除失败",false);
                return jsonResult;
            }
        }else{
            jsonResult = new JsonResult(2,"用户未登入",false);
        }
        return  jsonResult;
    }

    /**
     * 修改用户历史搜索数据，通过获得用户输入的搜索词与用户id查询用户是否有之前使用过的词，如果有就进行修改操作，
     * 将数据库中的update_time时间修改一下，更新一下修改时间即可
     */
    @RequestMapping(value = "updateByPrimaryKey", method = RequestMethod.GET)
    public JsonResult updateByPrimaryKey(HttpServletRequest request,QueryHistoryWords queryHistoryWords){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null){
            try{
                if (queryHistoryWords != null && queryHistoryWords.gettWordName() != null){
                    queryHistoryWords.setMemberId(member.getId().intValue());
                    int i = queryHistoryWordsService.updateByPrimaryKeySelective(queryHistoryWords);
                    if (i > 0 ){
                        jsonResult = new JsonResult(1,"修改成功",i,true);
                    }else {
                        jsonResult = new JsonResult(1,"获取数据不完整，修改失败",i,true);
                    }
                }else {
                    jsonResult = new JsonResult(2,"未获取到用户信息",false);
                }
            }catch(Exception e){
                jsonResult = new JsonResult(2,"修改失败",false);
                return jsonResult;
            }
        }else {
            jsonResult = new JsonResult(3,"未登入",false);
        }
        return  jsonResult;
    }


}
