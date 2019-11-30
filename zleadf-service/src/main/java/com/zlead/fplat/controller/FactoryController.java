package com.zlead.fplat.controller;

import com.zlead.entity.MemberAddressEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.dao.AgentAddressMapper;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.dao.OaAgentUserinfoMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.*;
import com.zlead.fplat.entity.vo.AgentBlmcVO;
import com.zlead.fplat.service.*;
import com.zlead.reception.service.MemberAddressService;
import com.zlead.service.GoodsBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.StrTools;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.PinyinUtil;
import com.zlead.utils.RedisUtil;
import com.zlead.utils.SortByLetterAscUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工厂接口
 */
@RestController
@RequestMapping(value = "factory")
public class FactoryController {

    private static final Logger logger = LoggerFactory.getLogger(FactoryController.class);

    @Autowired
    private AgentFacService agentFacService;

    @Autowired
    private AgentbandService agentbandService;

    @Autowired
    private OaFactoryInfoService oaFactoryInfoService;

    @Autowired
    private CustbandService custbandService;

    @Autowired
    private CrmPrdListService crmPrdListService;

    @Autowired
    private CrmPrdModelService crmPrdModelService;

    @Autowired
    private CrmPrdCatService crmPrdCatService;//分类接口

    @Autowired
    private GoodsBgService goodsBgService;//商品接口

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private AgentBandListService agentBandListService;

    @Autowired
    private OaAgentMasMapper agentMasMapper;

    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private OaAgentUserinfoMapper oaAgentUserinfoMapper;

    @Autowired
    private OaFactoryInfoMapper oaFactoryInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MemberAddressService memberAddressService;

    @Resource
    private AgentAddressMapper agentAddressMapper;

    /**
     * 关联工厂
     */
    @RequestMapping(value = "relationapply", method = RequestMethod.POST)
    public JsonResult relationApply(HttpServletRequest request, @RequestBody AgentFac agentFac) {
        JsonResult result = new JsonResult();
        try {
            if (agentFac != null && agentFac.getFactoryId() != null && StringUtils.isNotBlank(agentFac.getVcode())) {
                //先去获取当前登录用户
                MemberEntity member = loginUtil.getLoginMember(request);
                if (member != null) {
                    if (member.getAgentId() == null || member.getAgentId() == 0) {
                        result.setSuccess(false);
                        result.setMessage("请先关联代理商");
                        result.setCode(2);
                        return result;
                    }
                    //直接提交工厂后端审核
//                    OaAgentMas oaAgentMas = oaAgentMasMapper.selectByPrimaryKey(member.getAgentId().intValue());
//                    if (oaAgentMas == null) {
//                        result.setSuccess(false);
//                        result.setMessage("没有找到代理商");
//                        result.setCode(2);
//                        return result;
//                    }
                    int count = oaFactoryInfoService.checkVcode(agentFac.getVcode(), agentFac.getFactoryId().longValue());
                    if (count != 1) {
                        result.setSuccess(false);
                        result.setMessage("关联码不匹配");
                        result.setCode(2);
                        return result;
                    }
                    // 判断是否重复提交
                    count = agentFacService.queryCountByAgentIdAndFactoryIdAndStatus(member.getAgentId(), agentFac.getFactoryId().longValue(), 0);
                    if (count > 0) {
                        result.setSuccess(false);
                        result.setMessage("关联申请正在审核，请勿重复提交");
                        result.setCode(2);
                        return result;
                    }
                    // 判断是否已关联该工厂
                    count = agentFacService.queryCountByAgentIdAndFactoryIdAndStatus(member.getAgentId(), agentFac.getFactoryId().longValue(), 1);
                    if (count > 0) {
                        result.setSuccess(false);
                        result.setMessage("已关联该工厂，不能再次关联");
                        result.setCode(2);
                        return result;
                    }
                    count = agentFacService.queryCountByAgentIdAndFactoryIdAndStatus(member.getAgentId(), agentFac.getFactoryId().longValue(), 2);
                    if (count > 0) {
                        result.setSuccess(false);
                        result.setMessage("你的关联申请正在处理中，请耐心等候！");
                        result.setCode(2);
                        return result;
                    }
                    //判断是否已经停止合作  （工厂与代理商停止合作，不能在关联）
                    count = agentFacService.queryCountByAgentIdAndFactoryIdAndStatus(member.getAgentId(), agentFac.getFactoryId().longValue(), 3);
                    if (count > 0) {
                        result.setSuccess(false);
                        result.setMessage("已停止合作，不能再次关联");
                        result.setCode(2);
                        return result;
                    }
                    agentFac.setMemberId(member.getId().toString());
                    agentFac.setAgentId(member.getAgentId().intValue());
                    agentFac.setStatus("0");
                    agentFac.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    agentFac.setApplyDate(new Date());
                    agentFac.setCreator(member.getId().intValue());
                    agentFac.setModifier(member.getId().intValue());
                    agentFac.setModifyTime(new Date());
                    //sysId 店铺ID值
                    OaFactoryInfo fac = oaFactoryInfoService.findFacByFactId(agentFac.getFactoryId());
                    agentFac.setSysId(fac.getShopId());
                    //by ykf bug7106 【工厂管理端】-【平台商城】代理商待审核列表部分内容没有正常显示
                    if(agentFac.getAgentbands() != null && agentFac.getAgentbands().size() > 0){
                        StringBuilder sb = new StringBuilder("");
                        for (Agentband agentband : agentFac.getAgentbands()){
                            sb.append(agentband.getBandName()).append(",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        agentFac.setAgentBrand(sb.toString());
                    }
                    agentFacService.insert(agentFac);
                    if (CollectionUtils.isNotEmpty(agentFac.getAgentbands())) {
                        for (Agentband agentband : agentFac.getAgentbands()) {
                        	if(null!=agentband && null!=agentband.getBandName() && ""!=agentband.getBandName()){
                            agentband.setAgentId(agentFac.getAgentId());
                            agentband.setFactId(agentFac.getFactoryId());
                            //agentbandService.insert(agentband);
                            String listIdsStr = agentband.getListIds();
                            StringBuilder agentBandBuilder = new StringBuilder();
                            OaAgentMas oaAgentMas = agentMasMapper.selectByPrimaryKey(agentFac.getAgentId());
                            if (listIdsStr.contains(",")) {
                                String[] listIds = listIdsStr.split(",");
                                for (String listId : listIds) {
                                    AgentbandList agentbandList = new AgentbandList();
                                    agentbandList.setAgentId(agentband.getAgentId());
                                    agentbandList.setBandId(agentband.getBandId());
                                    agentbandList.setBandName(agentband.getBandName());
                                    agentbandList.setFactId(agentband.getFactId());
                                    if (StringUtils.isNotEmpty(listId)) {
                                        agentbandList.setListId(Integer.parseInt(listId));
                                    } else {
                                        agentbandList.setListId(null);
                                    }
                                    agentBandBuilder.append(agentband.getBandName()).append(",");
                                    agentBandListService.insertSelective(agentbandList);
                                }
                                String agentBandStr = agentBandBuilder.substring(0, agentBandBuilder.length() - 1);
                                oaAgentMas.setAgentBrand(StringUtils.isNotEmpty(oaAgentMas.getAgentBrand()) ? oaAgentMas.getAgentBrand() + "," + agentBandStr : agentBandStr);

                            } else {
                                AgentbandList agentbandList = new AgentbandList();

                                agentbandList.setAgentId(agentband.getAgentId());
                                agentbandList.setBandId(agentband.getBandId());
                                agentbandList.setBandName(agentband.getBandName());
                                agentbandList.setFactId(agentband.getFactId());
                                if (StringUtils.isNotEmpty(listIdsStr)) {
                                    agentbandList.setListId(Integer.parseInt(listIdsStr));
                                } else {
                                    agentbandList.setListId(null);
                                }

                                agentBandListService.insertSelective(agentbandList);
                                
                                oaAgentMas.setAgentBrand(StringUtils.isNotEmpty(oaAgentMas.getAgentBrand()) ? oaAgentMas.getAgentBrand() + "," + agentband.getBandName() : agentband.getBandName());
                            }
                            agentbandService.insert(agentband);
                            agentMasMapper.updateByPrimaryKeySelective(oaAgentMas);
                            AgentFac agentFac1 = new AgentFac();
                            agentFac1.setAgentId(oaAgentMas.getAgentId());
                            agentFac1.setAgentName (oaAgentMas.getAgentName());
                            agentFacService.updateAgentFacAtter(agentFac1);
                        	}
                        }
                    }
                    result.setSuccess(true);
                    result.setMessage("关联申请已提交");
                    result.setCode(1);
                    //新增关联工厂系统消息
                    sysMessageService.insertAgentFactorySysMsg(member, agentFac.getFactoryId());
                    //关联工厂申请成功后，根据当前登录用户向代理商联系人表中插入一条信息
                    this.insertAgentUserInfoByMemberInfo(member, agentFac.getFactoryId());
                    //代理商同步地址（用户创建地址时还没有关联工厂,关联工厂后将地址插入进去）
                    if (member.getMemberType() == 3) {
                        Long id = member.getId();
                        List<MemberAddressEntity> allAddressByMId = memberAddressService.getAllAddressByMId(id);
                        for (MemberAddressEntity memberAddressEntity : allAddressByMId) {
                            AgentAddress agentAddress = new AgentAddress();
                            //member_adr_id
                            agentAddress.setMemberAddrId(memberAddressEntity.getId().intValue());
                            //工厂ID
                            agentAddress.setSysId(agentFac.getSysId());
                            //代理商ID
                            agentAddress.setAgentId(member.getAgentId().intValue());
                            //姓名
                            agentAddress.setRevName(memberAddressEntity.getMemberName());
                            //电话
                            agentAddress.setRevTel(memberAddressEntity.getPhone());
                            //省
                            agentAddress.setRevProvince(memberAddressEntity.getProvinceId()+"");
                            //市

                            agentAddress.setRevCity(memberAddressEntity.getCityId()+"");
                            //县
                            agentAddress.setRevCounty(memberAddressEntity.getRegionId()+"");
                            //具体地址
                            agentAddress.setRevAddr(memberAddressEntity.getAddress());
                            //是否默认
                            agentAddress.setRevDefault(memberAddressEntity.getIsDefault()+"");
                            //表示不是工厂端创建
                            agentAddress.setIsFact("0");
                            agentAddress.setRevDefault(memberAddressEntity.getIsDefault().toString());
                            agentAddressMapper.insert(agentAddress);
                        }
                    }
                } else {
                    result.setSuccess(false);
                    result.setMessage("用户未登录");
                    result.setCode(2);
                }
            } else {
                result.setSuccess(false);
                result.setMessage("申请失败");
                result.setCode(2);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("申请失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * 根据当前登录用户信息添加对应的代理商联系人信息
     * @param member
     * @param factoryId
     */
    private void insertAgentUserInfoByMemberInfo(MemberEntity member, Integer factoryId) {
        //根据factoryId找到对应的shopId
        OaFactoryInfo factory = oaFactoryInfoMapper.selectByPrimaryKey(factoryId);
        Integer shopId = factory.getShopId();
        String userName = member.getUsername();
        Long agentId = member.getAgentId();
        OaAgentUserinfo userInfo = oaAgentUserinfoMapper.selectByAgentIdAndPhone(agentId, userName);
        if(userInfo != null){
            //重置主键
            userInfo.setUserId(null);
        }else{
            //使用member的手机号，但不设置联系人姓名
            userInfo = new OaAgentUserinfo();
            userInfo.setAgentId(agentId.intValue());
            userInfo.setLinkTel(userName);
            userInfo.setMainUser("1");
        }
        userInfo.setSysId(shopId);
        oaAgentUserinfoMapper.insertSelective(userInfo);
    }

    /**
     * @param vcode     关联码
     * @param factoryId 工厂id
     * @return true/false
     */
    @RequestMapping(value = "checkvcode", method = RequestMethod.GET)
    public JsonResult checkVcode(@RequestParam("vcode") String vcode, @RequestParam("factoryId") Long factoryId) {
        JsonResult result = new JsonResult();
        try {
            int count = 0;
            if (StringUtils.isNotBlank(vcode) && factoryId != null) {
                count = oaFactoryInfoService.checkVcode(vcode, factoryId);
            }
            result.setSuccess(count == 1);
            result.setMessage(count == 1 ? "验证成功" : "关联码与工厂不匹配");
            result.setCode(1);
            result.setData(count == 1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("验证关联码与工厂是否关联失败");
            result.setCode(2);
        }
        return result;
    }


    /**
     * 获取已关联的工厂列表
     */
    @RequestMapping(value = "relation", method = RequestMethod.GET)
    public JsonResult relationFactory(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            //先去获取当前登录用户
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null) {
                if (member.getAgentId() != null && member.getAgentId() != 0) {
                    List<Map<String, Object>> list = oaFactoryInfoService.relationFactoryListByAgentId(member.getAgentId());
                    for (Map<String, Object> map : list) {
                        if (map.get("fact_name") != null) {
                            map.put("pinyin", PinyinUtil.getPingYinAllLower(map.get("fact_name").toString()));
                        }
                    }
                    Map<String, ArrayList<Object>> map = SortByLetterAscUtils.sortByLetterAsc(list);
                    result.setSuccess(true);
                    result.setData(map);
                    result.setCode(1);
                } else {
                    result.setSuccess(false);
                    result.setMessage("请先关联代理商");
                    result.setCode(2);
                    return result;
                }
            } else {
                result.setSuccess(false);
                result.setMessage("用户未登录");
                result.setCode(2);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取已关联工厂列表失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * 获取当前登录用户所属代理商的未关联的工厂
     */
    @RequestMapping(value = "unrelation", method = RequestMethod.GET)
    public JsonResult oldUnRelationFactory(HttpServletRequest request, @RequestParam(value = "key", required = false) String key) {
        JsonResult result = new JsonResult();
        try {
            //先去获取当前登录用户
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null) {
                if (member.getAgentId() != null && member.getAgentId() != 0) {
                    List<Map<String, Object>> list = oaFactoryInfoService.unRelationFactoryListByAgentId(member.getAgentId(), key);
                    result.setSuccess(true);
                    result.setData(list);
                    result.setCode(1);
                } else {
                    result.setSuccess(false);
                    result.setMessage("请先关联代理商");
                    result.setCode(2);
                    return result;
                }
            } else {
                result.setSuccess(false);
                result.setMessage("用户未登录");
                result.setCode(2);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取未关联工厂列表失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * 关联工厂验证
     */
    @RequestMapping(value = "facRelevancy", method = RequestMethod.GET)
    public JsonResult unRelationFactory(HttpServletRequest request, @RequestParam(value = "vcode", required = false) String vcode,
                                                                    @RequestParam(value = "factoryName", required = false) String factoryName ) {
        JsonResult result = new JsonResult();
        try {
            //先去获取当前登录用户
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null) {
                Integer i = oaFactoryInfoService.facRelevancy(vcode, factoryName);
                if (i  == null || i < 0){
                    result.setSuccess(false);
                    result.setMessage("关联码跟关联工厂不匹配！");
                    result.setCode(2);
                }else {
                    result.setSuccess(true);
                    result.setMessage("成功");
                    result.setData(i);
                    result.setCode(1);
                }
            } else {
                result.setSuccess(false);
                result.setMessage("用户未登录");
                result.setCode(2);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取信息失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * @param factId 工厂id
     * @return 该工厂的品牌
     */
    @RequestMapping(value = "prdbrand", method = RequestMethod.GET)
    public JsonResult brandList(@RequestParam("factoryId") Long factId) {
        JsonResult result = new JsonResult();
        try {
            List<Map<String, Object>> brands = oaFactoryInfoService.findAllBrandsByFactoryId(factId);
            if(brands!=null && brands.size()>0) {
                result.setSuccess(true);
                result.setData(brands);
                result.setCode(1);
            }else{
                result.setSuccess(true);
                result.setData(brands);
                result.setMessage("该工厂还没有品牌");
                result.setCode(1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取该工厂品牌数据失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * @param factoryId 工厂id
     * @param bids      品牌ids
     * @return 该工厂的系列
     */
    @RequestMapping(value = "prdlist", method = RequestMethod.GET)
    public JsonResult brandList(@RequestParam("factoryId") Long factoryId, @RequestParam("bids") String bids) {
        JsonResult result = new JsonResult();
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            if (factoryId != null && StringUtils.isNotBlank(bids)) {
                Set<Long> bidsSet = new HashSet<>();
                for (String bid : bids.split(",")) {
                    bidsSet.add(Long.parseLong(bid));
                }
                list = oaFactoryInfoService.findAllListsByFactoryIdAndBids(factoryId, bidsSet);
            }
            result.setCode(1);
            result.setData(list);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取该工厂系列数据失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * @return 工厂的店铺信息
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public JsonResult info(HttpServletRequest request, @RequestParam("factoryId") Long factoryId) {

        /*存储面包屑导航数据*/
        try {
            String url = request.getHeader("Referer");
            redisUtil.saveNaviRedis(request,2,url);
        }catch (Exception e){
            e.printStackTrace();
        }

        JsonResult result = new JsonResult();
        try {
            Map<String, Object> dataMap = new HashMap<>();
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null && member.getAgentId() != null && member.getAgentId() != 0) {
                //查询工厂的信息和店铺的信息
                Map<String, Object> info = oaFactoryInfoService.findShopByFactoryId(factoryId);
                if(info.get("shop_logo")==null || info.get("shop_logo")==""){                	 
                	info.put("shop_logo", "/shopping/img/index/sl3.png");
                }
                if(info.get("powerpic_path")==null || info.get("powerpic_path")==""){                	 
                	info.put("powerpic_path", "/shopping/img/index/sl3.png");
                }
                if(info.get("apapic_path")==null || info.get("apapic_path")==""){                	 
                	info.put("apapic_path", "/shopping/img/index/sl3.png");
                }
                dataMap.put("info", info);
                //查询店铺的轮播图
                if (info.get("shop_id") != null) {
                    Integer shopId = Integer.valueOf(info.get("shop_id").toString());
                    List<Map<String, Object>> ads = oaFactoryInfoService.findShopAdsImgByFactoryId(shopId);
                    dataMap.put("ads", ads);
                }
                //查询工厂与代理商关联的品牌、系列、分类
                //获取该代理商所关联的品牌
               // List<Map<String, Object>> brands = oaFactoryInfoService.findAllBrandsByFactoryIdAndAgentId(factoryId, member.getAgentId());
                List<Map<String, Object>> brands = oaFactoryInfoService.newFindAllBrandsByFactoryIdAndAgentId(factoryId, member.getAgentId());
                //判断是否有关联的品牌
                if (null != brands && brands.size()>0) {
                    List<Map<String, Object>> newBrands = new ArrayList<>();
                    List<Map<String, Object>> lists = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(brands)) {
                        for (Map<String, Object> brandAndList2 : brands) {
                            //************************系列 begin*******************
                            Map<String, Object> lists2 = new LinkedHashMap<>();
                            lists2.put("id", brandAndList2.get("list_id"));
                            lists2.put("listIds", brandAndList2.get("listIds"));
                            lists2.put("name", brandAndList2.get("list_name"));
                            lists2.put("pic_path", brandAndList2.get("listpicpath"));
                            lists.add(lists2);
                            //************************系列 end*******************
                        }
                    }
                    List<Map<String, Object>> bandList = this.distinctBand(brands);
                    //*******************品牌begin*******************
                    if (CollectionUtils.isNotEmpty(bandList)) {
                        for (Map<String, Object> brandAndList : bandList) {
                            Map<String, Object> brandsMap = new LinkedHashMap<>();
                            brandsMap.put("id", brandAndList.get("band_id").toString());
                            brandsMap.put("name", brandAndList.get("band_name").toString());
                            brandsMap.put("pic_path",brandAndList.get("pic_path"));
                            brandsMap.put("bandIds",brandAndList.get("bandIds"));
                            newBrands.add(brandsMap);
                        }

                    }
                    //*******************品牌end*******************
                   // List<Map<String, Object>> cats = new ArrayList<>();
                   // Set<Long> catIdsSet = new HashSet<>();
                    //根据系列id获取系列list 递归查询上级分类
                    //if (CollectionUtils.isNotEmpty(listIdsSet)) {
                        //lists = oaFactoryInfoService.findListsByFactoryId(factoryId, listIdsSet,member.getAgentId());
                        //List<CrmPrdList> crmPrdLists = crmPrdListMapper.selectByListIds(listIds.stream().distinct().collect(Collectors.toList()),facIds,agentId);

                        //if (CollectionUtils.isNotEmpty(lists)){
                           // for (Map<String, Object> map: lists ) {
                             //   catIdsSet.add(Long.parseLong(map.get("id").toString()));
                            //}
                       //}
                        //获取分类
    //                List<Map<String, Object>> cats = oaFactoryInfoService.findCatsByFactoryId(factoryId);
                       // cats = new ArrayList<>();
                        List<Map<String, Object>> catlist = new ArrayList<>();
                        if (CollectionUtils.isNotEmpty(lists)){
                            //根据系列id获取分类list
                            //List<Map<String, Object>> catlist = crmPrdCatService.findNameListByListIdsAndKey(catIdsSet, null);
    //                        catlist = crmPrdCatService.newFindNameListByListIdsAndKey(member.getAgentId(), factoryId);
                            //优化的查询list

                            catlist = crmPrdCatService.fastFindNameListByListIdsAndKey(member.getAgentId(), factoryId);
                            //前端不接受null值
                            if (CollectionUtils.isEmpty(catlist)){
                                catlist = new ArrayList<>();
                            }
                            /*if (CollectionUtils.isNotEmpty(catlist)) {
                                for (Map<String, Object> catMap : catlist) {
                                    //只展示1级分类
                                    if (catMap.get("pcat_id") != null && "0".equals(catMap.get("pcat_id").toString())) {
                                        if (cats.size() < 6) {
                                            cats.add(catMap);
                                        } else{
                                            break;
                                        }
                                    }
                                }
                            }*/
                        }
                    dataMap.put("brands", this.subList(newBrands,6));
                    dataMap.put("lists", this.subList(lists,6));
                    dataMap.put("cats", catlist);
                    result.setCode(1);
                    result.setSuccess(true);
                    result.setData(dataMap);
                }else{
                    dataMap.put("brands", "");
                    dataMap.put("lists", "");
                    dataMap.put("cats", "");
                    result.setCode(1);
                    result.setSuccess(true);
                    result.setData(dataMap);
                    result.setMessage("没有关联品牌");
                }
            } else {
                result.setCode(2);
                result.setSuccess(false);
                result.setMessage("用户未登录");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取店铺信息失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * @param factoryId 工厂id
     * @return 工厂的商铺中所有1级分类所关联的商品信息
     */
    @RequestMapping(value = "shop/goods", method = RequestMethod.GET)
    public JsonResult shopGoods(HttpServletRequest request, @RequestParam("factoryId") Long factoryId) {
        JsonResult result = new JsonResult();
        try {
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null && member.getAgentId() != null && member.getAgentId() != 0) {
                Map<String, Object> dataMap = new HashMap<>();
                int showType = 0;
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                //查找店铺的展示方式

                Map<String, Object> shopMap = oaFactoryInfoService.findShopByFactoryId(factoryId);
                if (shopMap != null && shopMap.get("show_type") != null) {
                    showType = Integer.parseInt(shopMap.get("show_type").toString());
                    if (showType == 1) {//按品牌展示
                        list = goodsBgService.showGoodsByBand(factoryId, member.getAgentId());
                    } else if (showType == 2) {//按系列展示
                        list = goodsBgService.showGoodsByList(factoryId, member.getAgentId());
                    } else if (showType == 3) {//按分类展示
                        list = goodsBgService.newShowGoodsByCat(factoryId, member.getAgentId());
                    }
                }
                dataMap.put("showType", showType);
                dataMap.put("list", list);
                result.setCode(1);
                result.setSuccess(true);
                result.setData(dataMap);
            } else {
                result.setCode(2);
                result.setSuccess(false);
                result.setMessage("用户未登录");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage("获取店铺商品数据失败");
            result.setCode(2);
        }
        return result;
    }

    /**
     * 品牌去重
     * @param list
     * @return
     */
    private List<Map<String,Object>> distinctBand(List<Map<String,Object>> list){
        for (int i = 0; i < list.size() - 1; i++){
            String bandName = list.get(i).get("band_name").toString();
            String listId = list.get(i).get("list_id").toString();
            String listIds = list.get(i).get("listIds").toString();
            StringBuilder sb = new StringBuilder(listIds);
            for  ( int  j  =  list.size() - 1 ; j  >  i; j -- )  {
                if  (list.get(j).get("band_name").equals(bandName) && !list.get(j).get("list_id").equals(listId))  {
                    sb.append(",").append(list.get(j).get("list_id"));
                    list.remove(j);
                }
            }
            list.get(i).put("listIds",sb.toString());
        }
        return list;
    }

    /**
     * 集合切割
     * @param dataList
     * @param size
     * @return
     */
    private List<Map<String, Object>> subList(List<Map<String, Object>> dataList,int size){
        if(dataList.size() < size){
            return dataList;
        } else{
            return dataList.subList(0,size);
        }
    }
}
