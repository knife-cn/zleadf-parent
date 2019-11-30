package com.zlead.fplat.service.impl;

import com.zlead.dao.GoodsDao;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.*;
import com.zlead.fplat.entity.vo.*;
import com.zlead.fplat.service.*;
import com.zlead.util.JsonResult;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.ChineseInitialUtil;
import com.zlead.utils.LoginUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by admin on 2019/1/16.
 */
@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private OaFactoryInfoMapper mapper;

    @Autowired
    private CustbandMapper custbandMapper;

    @Autowired
    private CrmPrdCatMapper crmPrdCatMapper;

    @Autowired
    private CrmPrdListMapper crmPrdListMapper;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private AgentbandMapper agentbandMapper;

    @Autowired
    private AcctSaleTypeMapper acctSaleTypeMapper;

    @Autowired
    private MarketactMapper marketactMapper;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private AgentbandService agentbandService;

    @Autowired
    private CrmPrdCatService crmPrdCatService;
    @Autowired
    private SearchService searchService;

    /*
     * 导航栏筛选和工厂列表*/
    @Override
    public JsonResult navigation(HttpServletRequest request) {
        MemberEntity member = loginUtil.getLoginMember(request);
        /*1.用户为空  没登录
           1.1 登录 关联工厂
            1.1.1 登录  没有关联工厂
        * */
        if (null == member) {
            //用户没登录的情况
            return noMemberNavigation();
        } else {
            //用户登录的情况
            JsonResult result = new JsonResult();
            NavigationVo navigationVo = new NavigationVo();
            Screen screen = new Screen();
            System.out.println("navigation agentId: " + member.getAgentId());
            List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
            if (facIds.isEmpty()) {
                //为空  则没有关联工厂 数据为空
                return noFac(result, navigationVo, screen);
            } else {
                //登录有关联工厂//原先的查询有问题，在没有考虑到活动商品不是当前代理商代理的品牌的时候会查询不出
                //return loginHasFac(member.getAgentId(), facIds, result, navigationVo, screen, member.getAgentId());
                //return  newLoginHasFac(member.getAgentId(),null,result, facIds, navigationVo, screen);
                return newLoginHasFac(member.getAgentId(),null,result, facIds, navigationVo);//不要动，动了首页品牌、序列展示顺序不对概不负责
            }
        }
    }

    private void addId(Map<String,Object> dataMap,String key,Set<Long> dataSet){
        Object obj = dataMap.get(key);
        if(obj != null){
            dataSet.add(Long.valueOf(obj.toString()));
        }
    }

    private CrmPrdCat getParentCat(Integer catId) {
        CrmPrdCat crmPrdCat = crmPrdCatService.findOneById(catId);
        if (crmPrdCat != null && crmPrdCat.getPcatId() != 0) {
            crmPrdCat = getParentCat(crmPrdCat.getPcatId());
        }
        return crmPrdCat;
    }

    private String concatID(String str,String id){
        if(str == null){
            str = "";
        }
        return str + "," + id;
    }

    public JsonResult newLoginHasFac(Long agentId,Long factoryId,JsonResult result,List<Integer> facIds, NavigationVo navigationVo){
        Screen screen = this.searchService.findBrandListCatByAgent(agentId,factoryId);
        navigationVo.setScreen(screen);
        navigationVo.setFactoryList(fscList(facIds));
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(navigationVo);
        return result;
    }


    public JsonResult oldLoginHasFac(Long agentId,Long factoryId,JsonResult result,List<Integer> facIds, NavigationVo navigationVo, Screen screen){
        List<Custband> custbandList = new ArrayList<>();//前端显示品牌
        List<CrmPrdList> crmPrdListList = new ArrayList<>();//前端显示系列
        List<CrmPrdCat> crmPrdCatList = new ArrayList<>();//前端显示分类
        Set<Long> brandSet = new HashSet<>();//品牌ID用于查询
        Set<Long> listSet  = new HashSet<>();//序列ID用于查询
        Set<Long> catSet   = new HashSet<>();//分类ID用于查询
        //查询代理人有权限且有商品的品牌、系列、型号、分类（包含活动商品）
        List<Map<String,Object>> kindList = goodsDao.findGoodsBandListCatByAgent(agentId,null);
        if(CollectionUtils.isEmpty(kindList)){//不存在有商品的品牌、系列、分类
            screen.setCustbandList(custbandList);
            screen.setCrmPrdListList(crmPrdListList);
            screen.setCrmPrdCatList(crmPrdCatList);
            navigationVo.setScreen(screen);
            navigationVo.setFactoryList(fscList(facIds));
            result.setSuccess(true);
            result.setCode(1);
            result.setMessage("success");
            result.setData(navigationVo);
            return result;
        }
        if(CollectionUtils.isNotEmpty(kindList)){
            for(Map<String,Object> dataMap : kindList){
                this.addId(dataMap,"brand_id",brandSet);
                this.addId(dataMap,"list_id",listSet);
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
                        for(Custband custband : custbandList){
                            if(name.equals(custband.getBandName())){
                                custband.setBandIds(this.concatID(custband.getBandIds(),id));
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            Custband custband = new Custband();
                            custband.setBandId(Integer.valueOf(id));
                            custband.setBandName(name);
                            custband.setBandIds(id);
                            custbandList.add(custband);
                        }
                    }
                }
            }
        }

        //添加系列
        if(CollectionUtils.isNotEmpty(listSet)){
            List<Map<String,Object>> dataList = crmPrdListMapper.findNameByIds(agentId,listSet);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(Map<String,Object> dataMap : dataList){
                    String listId = dataMap.get("id").toString();
                    String listName = dataMap.get("name").toString();
                    if (listId != null && StringUtils.isNotBlank(listName)) {
                        boolean addFlag = true;
                        for(CrmPrdList crmPrdList : crmPrdListList){
                            if(listName.equals(crmPrdList.getListName())){
                                crmPrdList.setListIds(this.concatID(crmPrdList.getListIds(),listId));
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            CrmPrdList crmPrdList = new CrmPrdList();
                            crmPrdList.setListId(Integer.valueOf(listId));
                            crmPrdList.setListName(listName);
                            crmPrdList.setListIds(listId);
                            crmPrdListList.add(crmPrdList);
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
                    Integer catId = crmPrdCat.getCatId();
                    boolean flag = false;
                    for (Map<String,Object> map :kindList ) {
                        Integer cId = Integer.parseInt(map.get("cat_id").toString());
                        if (0 != cId  && cId.equals(catId)){
                            String prodType = map.get("prod_type").toString();
                            if (prodType.equals("2")){
                                flag = true;
                            }
                        }
                    }
                    if(factoryId == null && 1 != crmPrdCat.getIsFac()){//首页进去自定义分类不展示
                        continue;
                    }else if (flag){
                        continue;
                    }
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
                        for(CrmPrdCat cat : crmPrdCatList){
                            if(catName.equals(cat.getCatName())){
                                cat.setCatIds(this.concatID(cat.getCatIds(),catId.toString()));
                                addFlag = false;
                                break;
                            }
                        }
                        if(addFlag){
                            CrmPrdCat cat = new CrmPrdCat();
                            cat.setCatId(catId);
                            cat.setCatName(catName);
                            cat.setCatIds(catId.toString());
                            crmPrdCatList.add(cat);
                        }
                    }
                }
            }
        }
        //CrmPrdCat.CatIds去重
        if(CollectionUtils.isNotEmpty(crmPrdCatList)){
            for(CrmPrdCat crmPrdCat : crmPrdCatList){
                this.removeDuplicateData(crmPrdCat);
            }
        }

       /* Collections.sort(crmPrdCatList, new Comparator<CrmPrdCat>() {
            @Override
            public int compare(CrmPrdCat o1, CrmPrdCat o2) {
                if(o1.getCatId() > o2.getCatId()){
                    return 1;
                } else if(o1.getCatId() == o2.getCatId()){
                    return 0;
                } else{
                    return -1;
                }
            }
        });*/
        screen.setCustbandList(this.subList(custbandList,6));
        screen.setCrmPrdListList(this.subList(crmPrdListList,6));
        screen.setCrmPrdCatList(this.subList(crmPrdCatList,6));
        navigationVo.setScreen(screen);
        navigationVo.setFactoryList(fscList(facIds));
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(navigationVo);
        return result;
    }

    /**
     * 对CrmPrdCat.catIds去重
     * @param crmPrdCat
     */
    private void removeDuplicateData(CrmPrdCat crmPrdCat){
        if(crmPrdCat == null){
            return ;
        }
        String catIds = crmPrdCat.getCatIds();
        Set<String> idSet = new LinkedHashSet<>();
        if(StringUtils.isNotBlank(catIds)){
            String[] ids = catIds.split(",");
            if(ids != null && ids.length > 0){
                for(String id : ids){
                    idSet.add(id);
                }
                StringBuilder sb = new StringBuilder("");
                for(String id : idSet){
                    sb.append(id).append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                crmPrdCat.setCatIds(sb.toString());
                //System.out.println("去重前=" + catIds + "去重后=" + crmPrdCat.getCatIds());
            }
        }
    }

    private List subList(List dataList,int size){
        if(dataList.size() < size){
            return dataList;
        } else{
            return dataList.subList(0,size);
        }
    }
    //用户登录 有工厂
    /*facIds:工厂ids
    id：代理商id
    * */
    public JsonResult loginHasFac(Long agentId, List<Integer> facIds, JsonResult result, NavigationVo navigationVo, Screen screen, Long id) {
        //得到关联的工厂的品牌 根据工厂agentId得到agentFac的ids集合
        List<Integer> ids = agentFacMapper.findIdByAgentId(agentId);
        System.out.println("ids 2: " + ids);
        if (ids.isEmpty()) {
            //没有关联的品牌 则品牌 与系列 分类都为空
            screen.setCustbandList(new ArrayList<>());
            screen.setCrmPrdListList(new ArrayList<>());
            screen.setCrmPrdCatList(new ArrayList<>());

            navigationVo.setScreen(screen);
            navigationVo.setFactoryList(fscList(facIds));

            result.setSuccess(true);
            result.setCode(1);
            result.setMessage("success");
            result.setData(navigationVo);
            return result;
        }
        //List<Agentband> agentList = agentbandMapper.getBrandByFctIdAndAgentId(agentId);

        //品牌被查出存放集合
//        List<Map<Object,Object>> list1 = new ArrayList<>();
//        //所有工厂品牌集合
//        List<List<Map<Object,Object>>> list2 = new ArrayList<>();
//
//        int i = 0;
//        for (Integer f: facIds) {
//            //循环依次查出添加入集合
//            list1 = agentbandMapper.getBrandByFctIdAndAgentId(agentId,f.longValue());
//            list2.add(list1);
//        }
//        //存放品牌名称的集合
//        List<String> list3 = new ArrayList<>();
//        //一个跳出的标识
//        boolean flag = true;
//        int number = 0;
//        for (int j=0;j<6;j++){
//            if(list2.get(j).get(number) != null){
//                list3.add(list2.get(j).get(number).get("band_name").toString());
//                continue;
//            }
//            if(list2.size() > j){
//                int num = 6-list2.size();
//                number = 1;
//                for (int c=0;c<num;c++){
//                    if(list2.get(c).get(number) != null){
//                        list3.add(list2.get(c).get(number).get("band_name").toString());
//                    }
//                }
//                flag = false;
//            }
//        }



        List<Agentband> agentbands = agentbandMapper.selectByAgentFacIds(agentId.intValue(), facIds);
        System.out.println("agentbands 3: " + agentbands.toArray().toString());
        if (agentbands.isEmpty()) {
            //没有关联的品牌 则品牌 与系列 分类都为空
            screen.setCustbandList(new ArrayList<>());
            screen.setCrmPrdListList(new ArrayList<>());
            screen.setCrmPrdCatList(new ArrayList<>());
            System.out.println("agentbands 3: is null ");

            navigationVo.setScreen(screen);
            navigationVo.setFactoryList(fscList(facIds));

            result.setSuccess(true);
            result.setCode(1);
            result.setMessage("success");
            result.setData(navigationVo);
            return result;
        } else {
            //品牌去重
            List<Custband> nc = new ArrayList<>();
            int index = 0, findex=0;
            List<Custband> sortBand = new ArrayList<>();//排序后的品牌
            
            HashMap<Integer,List<Agentband>> agentFbandsMap=new HashMap();
            for (int kindex=0;kindex<facIds.size();kindex++) {
            	Integer f=facIds.get(kindex);
            	List<Agentband> agentFbands = agentbandMapper.getBrandByFctIdAndAgentId(agentId,f.longValue());
            	agentFbandsMap.put(kindex, agentFbands);
            }
            while(index<agentbands.size() && index<6){
            	// 按工厂的顺序添加品牌，如果没有则跳过
            for (int kindex=0;kindex<facIds.size();kindex++) {
            	Integer f=facIds.get(kindex);
            	List<Agentband> agentFbands = agentFbandsMap.get(kindex);//agentbandMapper.getBrandByFctIdAndAgentId(agentId,f.longValue());
            	if ( findex<agentFbands.size() && index<6) {            		 
                  Custband custband = new Custband();
                  custband.setBandId(agentFbands.get(findex).getBandId());
                  custband.setBandName(agentFbands.get(findex).getBandName());
                  //String s = strIncise(agentFbands.get(findex).getBandIds());
                  if(agentFbands.get(findex).getBandIds()==null && agentFbands.get(findex).getBandId()!=null)
                	  custband.setBandIds(agentFbands.get(findex).getBandId().toString());
                  else if(agentFbands.get(findex).getBandIds()!=null)
                	  custband.setBandIds(agentFbands.get(findex).getBandIds());
                  nc.add(index, custband);
                  index++;
              }
            }
            findex++;
           }
            
//            for (Agentband agentband : agentbands) {
//                Custband custband = new Custband();
//                custband.setBandId(agentband.getBandId());
//                custband.setBandName(agentband.getBandName());
//                String s = strIncise(agentband.getBandIds());
//                custband.setBandIds(s);
//                nc.add(index, custband);
//            }
//            /**品牌排序**/
//            List<Custband> sortBand = new ArrayList<>();//排序后的品牌
//            Set<Long> bSet = new HashSet<>();
//            for(Custband custband : nc){
//                String bandIds = custband.getBandIds();
//                if(StringUtils.isNotBlank(bandIds)){
//                    String[] array = bandIds.split(",");
//                    if(array != null && array.length > 0){
//                        for(String str : array){
//                            bSet.add(Long.valueOf(str));
//                        }
//                    }
//                }
//            }
//            List<Map<String, Object>> bandDataList = custbandMapper.findNameByIds(agentId, bSet);
//            if (CollectionUtils.isNotEmpty(bandDataList)) {
//                for (Map<String, Object> dataMap : bandDataList) {
//                    Integer bandId = Integer.parseInt(dataMap.get("id").toString());
//                    for (Custband custband : nc) {
//                        if (bandId.equals(custband.getBandId())) {
//                            sortBand.add(custband);
//                        }
//                    }
//                }
//            }
//          
            
            if (sortBand.size() <= 6) {
                screen.setCustbandList(nc);
            } else {
                screen.setCustbandList(nc.subList(0, 6));
            }
            //查找系列 品牌ids
            //品牌上找到list的ids
            List<Integer> listIds = new ArrayList<>();
            for (Agentband agentband : agentbands) {
                String str = agentband.getListIds();
                if ("" != str && null != str) {
                    String[] list = str.split(",");
                    if (list.length > 0) {
                        for (String s : list) {
                            //if (null != s && "" != s) {
                            if (null != s && "" != s && !s.isEmpty() && s.length() > 0) {
                                System.out.println("listIds:" + s);
                                listIds.add(Integer.parseInt(s));
                            }
                        }
                    }
                }
            }
            //根据ListIds 查找系列
            if (listIds.isEmpty()) {
                //系列为空   无关联的分类
                screen.setCrmPrdListList(new ArrayList<>());
                screen.setCrmPrdCatList(new ArrayList<>());

                navigationVo.setScreen(screen);
                navigationVo.setFactoryList(fscList(facIds));

                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("success");
                result.setData(navigationVo);
                return result;
            }
            List<CrmPrdList> crmPrdLists = crmPrdListMapper.selectByListIds(listIds.stream().distinct().collect(Collectors.toList()), facIds, agentId);
            List<Integer> listIds2 = new ArrayList<>();
            System.out.println("crmPrdLists 4: " + crmPrdLists.toArray().toString());
            if (crmPrdLists.isEmpty()) {
                //系列为空   无关联的分类
                screen.setCrmPrdListList(crmPrdLists);
                screen.setCrmPrdCatList(new ArrayList<>());
                System.out.println("crmPrdLists 4 is null  ");

                navigationVo.setScreen(screen);
                navigationVo.setFactoryList(fscList(facIds));

                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("success");
                result.setData(navigationVo);
                return result;
            } else {
                //系列去重
                //List crmList = crmPrdLists.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getListName()))), ArrayList::new));
                //List crmList =removeDuplicate(crmPrdLists);
                System.out.println("crmPrdLists 4  listId:  " + crmPrdLists.get(0).getListId());
                for (CrmPrdList crmPrdList : crmPrdLists) {
                    crmPrdList.setListIds(strIncise(crmPrdList.getListIds()));
                    listIds2.add(crmPrdList.getListId());
                }
                /**序列排序**/
                List<CrmPrdList> sortList = new ArrayList<>();//排序后的序列
                Set<Long> lSet = new HashSet<>();
                for(CrmPrdList crmPrdList : crmPrdLists){
                    String idsStr = crmPrdList.getListIds();
                    if(StringUtils.isNotBlank(idsStr)){
                        String[] array = idsStr.split(",");
                        if(array != null && array.length > 0){
                            for(String str : array){
                                lSet.add(Long.valueOf(str));
                            }
                        }
                    }
                }
                List<Map<String, Object>> listDataList = this.crmPrdListMapper.findNameByIds(agentId, lSet);
                if (CollectionUtils.isNotEmpty(listDataList)) {
                    for (Map<String, Object> dataMap : listDataList) {
                        Integer listId = Integer.parseInt(dataMap.get("id").toString());
                        for (CrmPrdList crmPrdList : crmPrdLists) {
                            if (listId.equals(crmPrdList.getListId().intValue())) {
                                sortList.add(crmPrdList);
                            }
                        }
                    }
                }
                List crmList = crmPrdLists;
                if (sortList.size() <= 6) {
                    screen.setCrmPrdListList(sortList);
                } else {
                    screen.setCrmPrdListList(sortList.subList(0, 6));
                }
            }

            //查找分类（平台） 一级分类 系列关联分类  显示一级分类
            //查找系列 品牌ids
            //品牌上找到list的ids
            List<Integer> catIds = new ArrayList<>();
            for (CrmPrdList crmPrdList : crmPrdLists) {
//            	catIds.add(Integer.parseInt(crmPrdList.getCatIds()));
                String str = crmPrdList.getCatIds();
                if (!("" == str || null == str)) {
                    String[] list = str.split(",");
                    if (list.length > 0) {
                        for (String s : list) {
                            if (null != s || "" != s) {
                                catIds.add(Integer.parseInt(s));
                            }
                        }
                    } else {
                        catIds.add(Integer.parseInt(str));
                    }
                }
            }
            if (catIds.isEmpty()) {
                //分类Id为空  则分类为空
                screen.setCrmPrdCatList(new ArrayList<>());

                navigationVo.setScreen(screen);
                navigationVo.setFactoryList(fscList(facIds));

                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("success");
                result.setData(navigationVo);
                return result;
            }
            //根据Ids查询一级分类
            System.out.println("catIds 5: " + catIds.toArray().toString());

            //  List<CrmPrdCat> crmPrdCatList = crmPrdCatMapper.selectByIds(catIds);
            //管理后台已经添加了分类的上级分类的关联关系，此处保留，如果数据正确，以上一行代码为
            //List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.selectByCIds(catIds);
//            List<CrmPrdCat> crmPrdCats = new ArrayList<CrmPrdCat>();
//            List<CrmPrdCat> catIds1 = getCatIds(catIds, crmPrdCats);
            //  System.out.println(catIds1);
//            List<Integer> catIdss = c1.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());

            List<Integer> catIdss = crmPrdCatMapper.queryCatIdsList(agentId);

            //分类排序
            List<Long> sortCatList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(catIdss)){
                Set<Long> queryCatId = new HashSet<>();
                for(Integer catId : catIdss){
                    queryCatId.add(catId.longValue());
                }
                List<Map<String,Object>> dataList = this.crmPrdCatMapper.findNameByIds(agentId,queryCatId);
                if(CollectionUtils.isNotEmpty(dataList)){
                    for(Map<String,Object> dataMap : dataList){
                        Long catId = Long.valueOf(dataMap.get("id").toString());
                        sortCatList.add(catId);
                    }
                }
            }
            List<CrmPrdCat> crmPrdCatList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(sortCatList)){
                for(Long catId : sortCatList){
                    CrmPrdCat crmPrdCat = this.crmPrdCatMapper.findOneById(catId.intValue());
                    if(crmPrdCat != null){
                        if(crmPrdCat.getIsFac() != 1){
                            continue;
                        }
                        Integer dataId = crmPrdCat.getCatId();
                        String dataName = crmPrdCat.getCatName();
                        if (StringUtils.isNotBlank(dataName)) {
                            boolean addFlag = true;
                            for(CrmPrdCat cat : crmPrdCatList){
                                if(dataName.equals(cat.getCatName())){
                                    cat.setCatIds(this.concatID(cat.getCatIds(),catId.toString()));
                                    addFlag = false;
                                    break;
                                }
                            }
                            if(addFlag){
                                CrmPrdCat cat = new CrmPrdCat();
                                cat.setCatId(dataId);
                                cat.setCatName(dataName);
                                cat.setCatIds(catId.toString());
                                crmPrdCatList.add(cat);
                            }
                        }
                    }
                }
            }
            //CrmPrdCat.CatIds去重
            if(CollectionUtils.isNotEmpty(crmPrdCatList)){
                for(CrmPrdCat crmPrdCat : crmPrdCatList){
                    this.removeDuplicateData(crmPrdCat);
                }
            }
            screen.setCrmPrdCatList(this.subList(crmPrdCatList,6));

            /*if (CollectionUtils.isNotEmpty(catIdss)) {
                List<CrmPrdCat> crmPrdCatList = crmPrdCatMapper.selectByIds(catIdss);
                for (CrmPrdCat c : crmPrdCatList) {
                    c.setCatIds(strIncise(c.getCatIds()));
                }

                if (crmPrdCatList.size() <= 6) {
                    screen.setCrmPrdCatList(crmPrdCatList);
                } else {
                    //大于9 只要9个
                    screen.setCrmPrdCatList(crmPrdCatList.subList(0, 6));
                }
            }*/
        }
        /**解决品牌、序列、分类排序问题**/
        navigationVo.setScreen(screen);
        navigationVo.setFactoryList(fscList(facIds));

        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(navigationVo);
        return result;
    }


    //用户没有登录的情况
    JsonResult noMemberNavigation() {
        JsonResult result = new JsonResult();
        //最终返回结果
        NavigationVo navigationVo = new NavigationVo();

        //筛选
        Screen screen = new Screen();

        //工厂列表
        FactoryList list = new FactoryList();


        /*展示平台所有工厂中品牌的6个
        系列的6个
        分类的6个 及平台中所有工厂列表
        * */
        List<Integer> ids = mapper.findAllIds();
        if (ids.size() == 0) {//
            return noFac(result, navigationVo, screen);
        } else { //
            //平台有工厂  查询关联的
            //查询平台对应的店铺
            List<Integer> shopids = mapper.findShopIdById(ids);
            return hasFacs(ids, shopids, result, navigationVo, screen);
        }
    }


    //没有工厂时返回
    public JsonResult noFac(JsonResult result, NavigationVo navigationVo, Screen screen) {
        //没有平台工厂   则品牌 系列 分类 和工厂列表都为空
        screen.setCrmPrdCatList(new ArrayList<>());
        screen.setCrmPrdListList(new ArrayList<>());
        screen.setCustbandList(new ArrayList<>());
        navigationVo.setScreen(screen);
        navigationVo.setFactoryList(new FactoryList());

        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(navigationVo);
        return result;
    }


    //未登录 有工厂的情况
    public JsonResult hasFacs(List<Integer> ids, List<Integer> shopids, JsonResult result, NavigationVo navigationVo, Screen screen) {
        List<Custband> custbandList = custbandMapper.findAllBand(shopids);
        //查找分类（平台） 一级分类
        List<CrmPrdCat> crmPrdCatList = crmPrdCatMapper.findOwnCat(shopids);
        if (custbandList.size() == 0) {
            //此时没有品牌 没有系列
            screen.setCustbandList(new ArrayList<>());
            screen.setCrmPrdListList(new ArrayList<>());
            if (crmPrdCatList.size() == 0) {
                screen.setCrmPrdCatList(new ArrayList<>());
            } else {
                //名字去重 全部展示
                //List<CrmPrdCat> newCat = crmPrdCatList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
                List<CrmPrdCat> newCat = removeDuplicate1(crmPrdCatList);
                if (newCat.size() <= 9) {
                    screen.setCrmPrdCatList(newCat);
                } else {
                    //大于9 只要9个
                    screen.setCrmPrdCatList(newCat.subList(0, 9));
                }
            }
        } else {
            //品牌去重
            //List<Custband> newCat = custbandList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getBandName()))), ArrayList::new));
            List<Custband> newCat = removeDuplicate3(custbandList);
            if (newCat.size() <= 6) {
                screen.setCustbandList(newCat);
            } else {
                screen.setCustbandList(newCat.subList(0, 6));
            }
            List<CrmPrdList> crmPrdLists = crmPrdListMapper.findListByshopIds(shopids);
            //系列去重
            if (crmPrdLists.isEmpty()) {
                screen.setCrmPrdListList(crmPrdLists);
            } else {
                //List<CrmPrdList> newCrm = crmPrdLists.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getListName()))), ArrayList::new));
                List<CrmPrdList> newCrm = removeDuplicate2(crmPrdLists);
                if (newCrm.size() <= 6) {
                    screen.setCrmPrdListList(newCrm);
                } else {
                    screen.setCrmPrdListList(newCrm.subList(0, 6));
                }
            }
            //名字去重 全部展示
            //List<CrmPrdCat> newrrdCat = crmPrdCatList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
            List<CrmPrdCat> newrrdCat = removeDuplicate1(crmPrdCatList);
            if (newrrdCat.size() <= 9) {
                screen.setCrmPrdCatList(newrrdCat);
            } else {
                //大于9 只要9个
                screen.setCrmPrdCatList(newrrdCat.subList(0, 9));
            }
        }
        FactoryList factoryList = fscList(ids);
        navigationVo.setScreen(screen);
        navigationVo.setFactoryList(factoryList);

        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(1);
        result.setData(navigationVo);

        return result;
    }


    //工厂列表 字母排序
    public FactoryList fscList(List factIds) {
        FactoryList factoryList = new FactoryList();
        List<OaFactoryInfo> oaFactoryInfos = null;
        if (null == factIds) {
            return factoryList;
        }
        List<Integer> shopIds = new ArrayList<>();
        for (Object factId : factIds) {
            Integer shopId = agentFacMapper.findShopByFactId((Integer) factId);
            if (shopId != null) {
                shopIds.add(shopId);
            }
        }
        if (CollectionUtils.isNotEmpty(shopIds)) {
            oaFactoryInfos = mapper.findAllInfo(shopIds);
        }
        if (oaFactoryInfos == null || oaFactoryInfos.size() == 0) {
            return factoryList;
        } else {
            //工厂不为空 首字母排序  放入对应的map中
            Collections.sort(oaFactoryInfos, new Comparator<OaFactoryInfo>() {
                @Override
                public int compare(OaFactoryInfo o1, OaFactoryInfo o2) {
                    //这里俩个是对属性判null处理，为null的都放到列表最下面
                    if (null == o1.getFactName()) {
                        return 1;
                    }
                    if (null == o2.getFactName()) {
                        return -1;
                    }
                    return Collator.getInstance(Locale.CHINESE).compare(o1.getFactName(), o2.getFactName());
                }
            });
            //遍历工厂list
            for (OaFactoryInfo of : oaFactoryInfos) {
                //首字母
                char initial = ChineseInitialUtil.getPYHeadChar(of.getFactName()).toUpperCase().charAt(0);
                if (factoryList.getMap().containsKey(initial)) {
                    factoryList.getMap().get(initial).add(of);
                } else {
                    List<OaFactoryInfo> list = new ArrayList<>();
                    list.add(of);
                    factoryList.getMap().put(initial, list);
                }
            }
            return factoryList;
        }
    }



    /*
     * 限时活动模块
     * */

    @Override
    public JsonResult actives(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        //最终返回结果
        List<Marketact> oaMacketContList = new ArrayList<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        /*1.未登录  平台工厂的4个限时活动
        2.已登录
          2.1  没有关联工厂  没有活动
          2.2  有工厂  展示关联工厂的活动
        * */
        if (null == member) {
            return noLoginActives(result, oaMacketContList);
        } else {
            List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
            if (facIds.isEmpty()) {
                //沒有关联工厂  没有限时活动
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("success");
                result.setData(oaMacketContList);
                return result;
            } else {
                //关联了工厂  获取限时活动
                return getActCon(member.getAgentId(), facIds);
            }

        }


        // return null;
    }

    //未登录  获取限时活动
    public JsonResult noLoginActives(JsonResult result, List<Marketact> oaMacketContList) {
        //平台所有工厂ids
        //List<Integer> ids = mapper.findAllIds();
        return getActCon(null, null);
    }

    //根据工厂ids获取活动内容  有错误
 /*   public JsonResult getActCon(Long agentId, List<Integer> ids) {
    	JsonResult result=new JsonResult();
    	List<Marketact> oaMacketContList=null;
        if (ids!=null && !ids.isEmpty()) {
            List<Integer> typeIds = getActivesType(ids);
            System.out.println("getActCon 1 agentId: "+agentId);
//            if (!typeIds.isEmpty()) {
            	System.out.println("getActCon 2 "+typeIds.size());
                //List<Marketact> cons = getCon(typeIds);
                List<Marketact> cons =marketactMapper.findByAgentIdPage(agentId.intValue(), new PageBounds(0, 10));
                if (!cons.isEmpty()) {
                    cons = cons.stream().map(marketact -> {
                        if (null == marketact.getContUrl()) {
                            marketact.setContUrl("");
                            return marketact;
                        }
                        return marketact;
                    }).collect(Collectors.toList());
                    if (cons.size() <= 4) {
                    	System.out.println("getActCon 3 "+cons.size());
                        oaMacketContList = cons;
                    } else {
                        oaMacketContList = cons.subList(0, 4);
                    }
                }
//            }
        }else{
        	List<Marketact> cons =marketactMapper.findAllByPage(new PageBounds(0, 10));
        	if (cons.size() <= 4) {
            	System.out.println("getActCon 3 1 "+cons.size());
                oaMacketContList = cons;
            } else {
                oaMacketContList = cons.subList(0, 4);
            }
        }
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(oaMacketContList);
        return result;
    }*/

    //根据工厂ids获取活动内容
    public JsonResult getActCon(Long agentId, List<Integer> factIds) {
        JsonResult result = new JsonResult();
        List<Marketact> oaMacketContList = null;
        if (factIds != null && !factIds.isEmpty()) {
            List<Integer> typeIds = getActivesType(factIds);
            System.out.println("getActCon 1 agentId: " + agentId);
//            if (!typeIds.isEmpty()) {
            System.out.println("getActCon 2 " + typeIds.size());
            // List<Marketact> cons = getCon(typeIds);
            List<Marketact> cons = marketactMapper.findByAgentIdPage(agentId.intValue(), new PageBounds(0, 10), factIds);
            for (Marketact con : cons) {
                //活动图片为 "" 或者 null 设置默认图片       首页活动
                if (null!=con && ("".equals(con.getActPic()) || null == con.getActPic())) {
                    con.setActPic("/shopping/img/index/sl3.png");
                }
            }
            if (!cons.isEmpty()) {
                cons = cons.stream().map(marketact -> {
                    if (null == marketact.getContUrl()) {
                        marketact.setContUrl("");
                        return marketact;
                    }
                    return marketact;
                }).collect(Collectors.toList());
                if (cons.size() <= 4) {
                    System.out.println("getActCon 3 " + cons.size());
                    oaMacketContList = cons;
                } else {
                    oaMacketContList = cons.subList(0, 4);
                }
            }
//            }
        } else {
            List<Marketact> cons = marketactMapper.findAllByPage(new PageBounds(0, 10));
            for (Marketact con : cons) {
                //活动图片为 "" 或者 null 设置默认图片
                if (null !=con && ("".equals(con.getActPic()) || null == con.getActPic())) {
                    con.setActPic("/shopping/img/index/sl3.png");
                }
            }
            if (cons.size() <= 4) {
                System.out.println("getActCon 3 1 " + cons.size());
                oaMacketContList = cons;
            } else {
                oaMacketContList = cons.subList(0, 4);
            }
        }
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(oaMacketContList);
        return result;
    }


    //根据工厂Id查询限时活动类型ids
    public List<Integer> getActivesType(List<Integer> facIds) {
        List<Integer> actTypeIds = acctSaleTypeMapper.findTypyByfacIds(facIds);
        return actTypeIds;
    }

    //根据活动类型Ids 查找有效期内的活动
    public List<Marketact> getCon(List<Integer> typeIds) {
        return marketactMapper.findConByType(typeIds);
    }


    /*
     * 首页锚点数据（分类）
     * */
    @Override
    public JsonResult getCat(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        MemberEntity member = loginUtil.getLoginMember(request);
        List<CrmPrdCat> cats = catList(member, request);
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(cats);
        return result;
    }

    //分类锚点2
    public List<CrmPrdCat> catList(MemberEntity member, HttpServletRequest request) {
        if (null == member) {
            //平台所有工厂下的分类
            List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.selectCatAll();
            return catPoint(crmPrdCats);
        } else {
            List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
            if (CollectionUtils.isNotEmpty(facIds)) {
                //关联工厂下的所有分类
                return getFirstCatList(member, facIds);
            }
            return new ArrayList<>();
        }
    }

    //关联工厂下的所有分类  品牌-系列-分类
    public List<CrmPrdCat> getFacAll(Long agentId, List<Integer> facIds) {
        List<Agentband> agentbands = agentbandMapper.selectAgentBandByAgentId(agentId);
        if (agentbands.isEmpty()) {
            return new ArrayList<>();
        }
        //查找系列 品牌ids
        //品牌上找到list的ids
        List<Integer> listIds = new ArrayList<>();
        for (Agentband agentband : agentbands) {
            String str = agentband.getListIds();
            if (!("" == str || null == str)) {
                String[] list = str.split(",");
                if (list.length > 0) {
                    for (String s : list) {
                        if (null != s && "" != s && !s.isEmpty() && s.length() > 0) {
                            listIds.add(Integer.parseInt(s));
                        }
                    }
                }
            }
        }
        //根据ListIds 查找系列
        if (listIds.isEmpty()) {
            //系列为空   无关联的分类
            return new ArrayList<>();
        }
        List<CrmPrdList> crmPrdLists = crmPrdListMapper.selectByListIds(listIds.stream().distinct().collect(Collectors.toList()), facIds, agentId);
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
        if (catIds.isEmpty()) {
            //分类Id为空  则分类为空
            return new ArrayList<>();
        }
        //绑定工厂关联的所有分类  包括一级与二级
//        List<Integer> facIds = agentFacMapper.findFacByagentId(agentId);
//        List<Integer> shopIds=mapper.findShopIdById(facIds);
        //===================通过得到的分类id寻找主分类==start==================
        //管理后台已经添加了分类的上级分类的关联关系，此处保留，如果数据正确，一下内容可删除 1 begin
        List<CrmPrdCat> returs = new ArrayList<CrmPrdCat>();
        if (CollectionUtils.isNotEmpty(getCatIds(catIds, returs))) {
            catIds = getCatIds(catIds, returs).stream().map(CrmPrdCat::getCatId).distinct().collect(Collectors.toList());
        }
        //===================通过得到的分类id寻找主分类=end===================
        //管理后台已经添加了分类的上级分类的关联关系，此处保留，如果数据正确， 内容可删除 1 end
        //根据工厂与分类Id  获得分类 //640,639,635,592,611,582,581,802,801,621
        List<CrmPrdCat> cats = crmPrdCatMapper.selectAllBycatIds(catIds, facIds);
        return cats;
    }

    //与工厂与系列关联的分类中 查询出一级分类 708
    public List<CrmPrdCat> getFirstCats(MemberEntity member, List<Integer> facIds) {
        //系列关联工厂的所有分类
        List<CrmPrdCat> cats = getFacAll(member.getAgentId(), facIds);
        //从这些id中找出一级分类
        if (cats.isEmpty()) {
            return new ArrayList<>();
        } else {
            System.out.println("getFirstCats :" + member.getId() + " " + member.getUsername());
            System.out.println(cats.size());
        }
        List<Integer> catIds = cats.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
//        List<CrmPrdCat> catList = crmPrdCatMapper.selectFirstCatByIds(catIds);
        List<CrmPrdCat> catList = crmPrdCatMapper.selectFirstCatByAgentId(member.getAgentId(), null);
        return catList;
    }

    //分类锚点一级分类
    public List<CrmPrdCat> getFirstCatList(MemberEntity member, List<Integer> facIds) {
        return catPoint(getFirstCats(member, facIds));
    }


    //分类锚点
    public List<CrmPrdCat> catPoint(List<CrmPrdCat> crmPrdCats) {
        List<CrmPrdCat> cats = new ArrayList<>();
        if (crmPrdCats.isEmpty()) {
            return cats;
        } else {
            //名字去重
            //List<CrmPrdCat> newCats = crmPrdCats.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
            List<CrmPrdCat> newCats = removeDuplicate1(crmPrdCats);
            if (newCats.size() <= 5) {
                return cats = newCats;
            } else {
                return cats = newCats.subList(0, 5);
            }
        }
    }


    /*首页商品区域*/
    @Override
    public JsonResult getPrdArea(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        MemberEntity member = loginUtil.getLoginMember(request);
        //最终返回结果
        List<HomeProductsVo> res = new ArrayList<>();
        //
        if (member != null) {
            //List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.selectFirstCatByAgentId(member.getAgentId(), null);
            List<CrmPrdCat> crmPrdCats = this.searchService.findLevelOneCat(member.getAgentId().longValue(),null);
            if (!crmPrdCats.isEmpty() && crmPrdCats.size() > 0) {
                System.out.println("getPrdArea  1 : crmPrdCats.size(): " + crmPrdCats.size());
                int index = 1;//序号
                int rowCount = 4;//首页每块区域显示多少个数据
                for (CrmPrdCat cat : crmPrdCats) {
                    if (cat.getPicPath() == null || "".equals(cat.getPicPath())){
                        cat.setPicPath(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                    }
                    cat.setCatIds(strIncise(cat.getCatIds()));//catIds去重
                    if (null != cat) {
                        System.out.println("getPrdArea  12 : 分类 : " + cat.getCatId() + " " + cat.getCatName() + " " + cat.getCatDesc());
                        HomeProductsVo vo = new HomeProductsVo();
                        //cat.getCatIds()
                        /**
                         if(member!=null){
                         System.out.println("getPrdArea  : member: "+member.getId()+" "+member.getUsername());
                         vo = getHomeGoods(cat, request);
                         }
                         else **/
                        //vo = getLoginHomePro(cat, request);
                        if(index > 2){
                            rowCount = 8;
                        }
                        index++;
                        vo = newGetLoginHomePro(cat,rowCount, request);
                        if (null != vo) {
                            res.add(vo);
                        }
                    }

                }
                result.setData(res);
            } else {
                //一级分类为空 则数据全部为空
                result.setData(res);
//                System.out.println("getPrdArea  2 : member: " );
//                // 取其他分类的数据
//                if(member!=null){
//                        //一级分类为空，不需要在查询其他系列，不然会导致当没有分类的时候查询出所有的一级分类
//                			System.out.println("getPrdArea  3 : member: "+member.getId()+" "+member.getUsername());
//                     		Long agentId=member.getAgentId();
//                            List<Integer> listCats = agentbandService.findCatByAgentId(member.getAgentId());
//                            if (listCats!=null) {
//                                List<CrmPrdCat> prdcats=crmPrdCatMapper.selectFirstCatByIds(listCats);
//                                for(CrmPrdCat prcat:prdcats){
//                                    if(prcat!=null && prcat.getCatId()!=0){
//                                        HomeProductsVo vo=new HomeProductsVo();
//                                        vo.setFirstCrmPrdCat(prcat);
//                                        List<GoodsEntity> goodsEntityList =goodsDao.queryGoodsListByagentId(agentId, prcat.getCatId());
//                                        if(goodsEntityList!=null && goodsEntityList.size()>0)
//                                            vo.setGoodsEntityList(goodsEntityList);
//
//                                        List<CrmPrdCat> secprdcats =crmPrdCatMapper.selectSecByCatId(prcat.getCatId());
//                                        if(goodsEntityList==null)
//                                            goodsEntityList =goodsDao.queryGoodsListByagentId(agentId, prcat.getCatId());
//                                        vo.setSecondCrmPrdCat(secprdcats);
//                                        res.add(vo);
//                                    }
//
//                                }
//                            }
//                            /**
//                            for (Integer cat : listCats) {
//                            	if (null != cat) {
//                            		System.out.println("分类 : "+cat);
//                            		HomeProductsVo vo=new HomeProductsVo();
//                            		System.out.println("getPrdArea  4: member: "+member.getId()+" "+member.getUsername());
//                            		CrmPrdCat crmPrdCat=crmPrdCatMapper.selectByPrimaryKey(cat);
//                            		List<GoodsEntity> goodsEntityList =goodsDao.queryGoodsListByagentId(agentId, cat);
//                            		if(!goodsEntityList.isEmpty() && goodsEntityList!=null && goodsEntityList.size()>0){
//                            			System.out.println(" getPrdArea  5: member:queryGoodsListByagentId size: "+goodsEntityList.size());
//                            			//if(crmPrdCat.getPcatId()==0)
//                            				vo.setFirstCrmPrdCat(crmPrdCat);
//
//                            			vo.setGoodsEntityList(goodsEntityList);
//                            			res.add(vo);
//                            		}
//                     		//vo = getHomeGoods(cat, request);
//                            	}
//                            } **/
//                            result.setData(res);
//                }
            }

        } else {
            //一级分类集合
            List<CrmPrdCat> crmPrdCats = catList(member, request);
            //List<CrmPrdCat> crmPrdCats= crmPrdCatMapper.selectFirstCatByAgentId(member.getAgentId());
            if (!crmPrdCats.isEmpty()) {
                System.out.println("getPrdArea  1 : crmPrdCats.size(): " + crmPrdCats.size());
                for (CrmPrdCat cat : crmPrdCats) {
                    if (null != cat) {
                        System.out.println("getPrdArea  12 : 分类 : " + cat.getCatId() + " " + cat.getCatName() + " " + cat.getCatDesc());
                        HomeProductsVo vo = new HomeProductsVo();
                        /**
                         if(member!=null){
                         System.out.println("getPrdArea  : member: "+member.getId()+" "+member.getUsername());
                         vo = getHomeGoods(cat, request);
                         }
                         else **/
                        vo = getHomePro(cat, request);
                        if (null != vo) {
                            res.add(vo);
                        }
                    }

                }
                result.setData(res);
            } else {
                //一级分类为空 则数据全部为空
                //result.setData(res);
                System.out.println("getPrdArea  2 : member: ");
                // 取其他分类的数据
                if (member != null) {
                    //一级分类为空，不需要在查询其他系列，不然会导致当没有分类的时候查询出所有的一级分类
                    System.out.println("getPrdArea  3 : member: " + member.getId() + " " + member.getUsername());
                    Long agentId = member.getAgentId();
                    List<Integer> listCats = agentbandService.findCatByAgentId(member.getAgentId());
                    if (listCats != null) {
                        List<CrmPrdCat> prdcats = crmPrdCatMapper.selectFirstCatByIds(listCats);
                        for (CrmPrdCat prcat : prdcats) {
                            if (prcat != null && prcat.getCatId() != 0) {
                                HomeProductsVo vo = new HomeProductsVo();
                                vo.setFirstCrmPrdCat(prcat);
                                List<GoodsEntity> goodsEntityList = goodsDao.queryGoodsListByagentId(agentId, prcat.getCatId());
                                if (goodsEntityList != null && goodsEntityList.size() > 0)
                                    vo.setGoodsEntityList(goodsEntityList);

                                List<CrmPrdCat> secprdcats = crmPrdCatMapper.selectSecByCatId(prcat.getCatId());
                                if (goodsEntityList == null)
                                    goodsEntityList = goodsDao.queryGoodsListByagentId(agentId, prcat.getCatId());
                                vo.setSecondCrmPrdCat(secprdcats);
                                res.add(vo);
                            }

                        }
                    }
                    /**
                     for (Integer cat : listCats) {
                     if (null != cat) {
                     System.out.println("分类 : "+cat);
                     HomeProductsVo vo=new HomeProductsVo();
                     System.out.println("getPrdArea  4: member: "+member.getId()+" "+member.getUsername());
                     CrmPrdCat crmPrdCat=crmPrdCatMapper.selectByPrimaryKey(cat);
                     List<GoodsEntity> goodsEntityList =goodsDao.queryGoodsListByagentId(agentId, cat);
                     if(!goodsEntityList.isEmpty() && goodsEntityList!=null && goodsEntityList.size()>0){
                     System.out.println(" getPrdArea  5: member:queryGoodsListByagentId size: "+goodsEntityList.size());
                     //if(crmPrdCat.getPcatId()==0)
                     vo.setFirstCrmPrdCat(crmPrdCat);

                     vo.setGoodsEntityList(goodsEntityList);
                     res.add(vo);
                     }
                     //vo = getHomeGoods(cat, request);
                     }
                     } **/
                    result.setData(res);
                }

            }
        }

        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        return result;
    }

    //首页商品 cat 一级分类  用户
    public HomeProductsVo getHomePro(CrmPrdCat cat, HttpServletRequest req) {
        MemberEntity member = loginUtil.getLoginMember(req);
        if (null == member) {
            HomeProductsVo hpv = new HomeProductsVo();
            //用户未登录 得到平台所有工厂
            List<Integer> facIds = mapper.findAllIds();
            if (facIds.isEmpty()) {
                return null;
            }

            hpv.setFirstCrmPrdCat(cat);
            GoodsQueryRequest request = new GoodsQueryRequest();

//            request.setIsMarket(1);
//            request.setIds(cat);
//            System.out.println(" getHomePro 11 cat 获取数据 : "+cat.getCatId()+" "+cat.getCatName());
            List<GoodsEntity> goodsEntityList = goodsDao.getGoodsByCatId(cat.getCatId());
            if (!goodsEntityList.isEmpty()) {
                //上架商品为空  获取库存商品  展销价不展示
                System.out.println(" getHomePro 1一级 cat 获取数据 : " + cat.getCatId() + " " + cat.getCatName());
                for (GoodsEntity g: goodsEntityList) {
                    String fullName = StrTools.strDistinct(g.getFullName());
                    g.setFullName(fullName);
                }
                hpv.setGoodsEntityList(goodsEntityList);
                //return hpv;
            }
            //选取 工厂与一级分类下的二级分类 名字不重复的
            List<CrmPrdCat> seCat = getSecondCat(cat, facIds);
            if (seCat.isEmpty()) {
                //如果二级为空  查询一级下的商品
                hpv.setSecondCrmPrdCat(new ArrayList<>());
                //平台下所有工厂  名字相同的一级分类的ids
                List<CrmPrdCat> firstCats = crmPrdCatMapper.selectByFirstCatName(cat.getCatName());
                System.out.println("分类  firstCats: " + firstCats.toString());
                List<Integer> firstIds = firstCats.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
                // 查询一级下的商品 有上架 写上架  没有上架找库存商品
                request = new GoodsQueryRequest();
                request.setIsMarket(1);
                request.setIds(firstIds);
                System.out.println("分类  firstIds: " + firstIds.toString());
                goodsEntityList = goodsDao.getGoods(request);
                if (goodsEntityList.isEmpty()) {
                    //上架商品为空  获取库存商品  展销价不展示
//                	hpv.getGoodsEntityList().
                    if (getStockGoods(firstCats) != null)
                        hpv.setGoodsEntityList(getStockGoods(firstCats));
                    System.out.println("goodsEntityList: is empty ");
                } else {
                    for (GoodsEntity g: goodsEntityList) {
                        String fullName = StrTools.strDistinct(g.getFullName());
                        g.setFullName(fullName);
                    }
                    //展示上架商品 取展销价的最低价
                    hpv.setGoodsEntityList(goodsEntityList);
                    System.out.println("goodsEntityList:  上架商品不为空   获取上架商品 ");

                    //上架商品不为空   获取上架商品
                    List<GoodsEntity> goodsEntityList2 = getOnGoods(firstCats);
                    if (!goodsEntityList2.isEmpty() && goodsEntityList2.size() > 0 && hpv.getGoodsEntityList() == null)
                        hpv.setGoodsEntityList(goodsEntityList2);
                    System.out.println("goodsEntityList:   " + goodsEntityList.size());
                }
                return hpv;
            }
            hpv.setSecondCrmPrdCat(seCat);
            // 二级分类下的产品  先上架商品  没有则库存商品
            //有重名的二级分类
            List<CrmPrdCat> catList = gethasSecondCat(seCat, facIds);
            List<Integer> ids = catList.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
            //二级分类下  查询是否有上架商品0=库存商品 1=已上架, 2=已下架, 3=停止销售
            request = new GoodsQueryRequest();
            request.setIsMarket(1);
            request.setIds(ids);
            goodsEntityList = goodsDao.getGoods(request);
            if (goodsEntityList.isEmpty() && hpv != null && hpv.getGoodsEntityList() == null) {
                //上架商品为空  获取库存商品  展销价不展示
                hpv.setGoodsEntityList(getStockGoods(catList));
            } else {
                for (GoodsEntity g: goodsEntityList) {
                    String fullName = StrTools.strDistinct(g.getFullName());
                    g.setFullName(fullName);
                }
                //展示上架商品 取展销价的最低价
                List<GoodsEntity> goodsEntityList2 = getOnGoods(catList);
                if (!goodsEntityList2.isEmpty() && goodsEntityList2.size() > 0)
                    hpv.setGoodsEntityList(goodsEntityList2);
            }
            return hpv;
        }
        /**
         else {
         System.out.println(" getHomePro 01 findFacByagentId  "+cat.getCatId()+" "+cat.getCatName());
         HomeProductsVo hpv = new HomeProductsVo();
         Long agentId=member.getAgentId();
         //用户登录 没有关联工厂  则所有数据为空
         List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
         List<Integer> shopIds = agentFacMapper.queryByShopIdAll(member.getAgentId());
         if (shopIds.isEmpty()) {
         //没有关联工厂   全部为空
         System.out.println(" getHomePro 00 findFacByagentId is null "+cat.getCatId()+" "+cat.getCatName());
         return null;
         } else {
         //工厂不为空
         hpv.setFirstCrmPrdCat(cat);
         //登录下  关联的所有分类 与一级分类//品牌 - 系列 - 分类
         List<CrmPrdCat> allCats = getFacAll(member.getAgentId(),facIds);
         if (allCats.isEmpty()) {
         hpv.setSecondCrmPrdCat(new ArrayList<>());
         return hpv;
         }
         List<Integer> allCatIds = allCats.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
         //===============================对分类进行了迭代，通过子分类选择一级分类start====================================
         List<CrmPrdCat> returns = new ArrayList<CrmPrdCat>();
         List<CrmPrdCat> catIds = getCatIds(allCatIds, returns);
         List<Integer> collect = catIds.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
         //===============================对分类进行了迭代，通过子分类选择一级分类end====================================
         //根据名字查找 一级的ids
         List<CrmPrdCat> allc = crmPrdCatMapper.selectFir(cat.getCatName(), collect,shopIds);
         allc = removeDuplicate(allc);
         List<Integer> firIds = allc.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
         //在所有分类id中  查找一级同名下  id下的二级分类 含重名
         List<CrmPrdCat> seCatList =null;
         if(firIds!=null && firIds.size()==1){
         seCatList =crmPrdCatMapper.selectSecByAllcatId(firIds.get(0),shopIds);
         }
         else if(firIds!=null && firIds.size()>1)
         seCatList =crmPrdCatMapper.selectSecByAllcatIds(firIds,shopIds,agentId);

         //if (seCatList.isEmpty() && hpv!=null && hpv.getGoodsEntityList()==null) {
         Set<Long> cids=new HashSet<>();
         for (Integer cid:firIds){
         cids.add(cid.longValue());
         }
         if ( seCatList==null || seCatList.isEmpty()    ) {
         hpv.setSecondCrmPrdCat(new ArrayList<>());
         //二级为空  显示一级下的上架商品
         System.out.println(" getHomePro 22 cat 获取数据 : "+cat.getCatId()+" "+cat.getCatName());
         List<GoodsEntity> goodsEntityList =queryGoodsByAgent(agentId,cids,shopIds);//getOnGoods(allc);
         if(!goodsEntityList.isEmpty() && goodsEntityList!=null && goodsEntityList.size()>0)
         hpv.setGoodsEntityList(goodsEntityList);
         else{
         System.out.println(" getHomePro 33 cat 获取数据 : "+cat.getCatId()+" "+cat.getCatName());
         goodsEntityList=goodsDao.getGoodsByCatIdWithAgent(cat.getCatId(),agentId.intValue());
         if(!goodsEntityList.isEmpty() && goodsEntityList!=null && goodsEntityList.size()>0)
         hpv.setGoodsEntityList(goodsEntityList);
         }
         return hpv;
         }
         //二级不为空  展示不重名 6个二级
         //List<CrmPrdCat> CatSeconds = seCatList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
         List<CrmPrdCat> CatSeconds = removeDuplicate(seCatList);
         List<CrmPrdCat> newCats = getloginSecondCat(CatSeconds);
         hpv.setSecondCrmPrdCat(newCats);
         //二级分类 在二级分类中查找和找出的名字相同的二级的集合
         List<String> catNames = newCats.stream().map(CrmPrdCat::getCatName).collect(Collectors.toList());
         //展示的二级含重复值得二级
         List<CrmPrdCat> res = new ArrayList<>();
         for (String name : catNames) {
         for (CrmPrdCat prdCat : seCatList) {
         if (name.equals(prdCat.getCatName())) {
         res.add(prdCat);
         }
         }
         }
         //递归查找下级分类
         cids = getChildCatIds(cids, cids);
         //                List<GoodsEntity> goodsEntityList2 = getOnGoods(res);
         //                if(!goodsEntityList2.isEmpty() && goodsEntityList2!=null && goodsEntityList2.size()>0)
         //                	hpv.setGoodsEntityList(goodsEntityList2);
         List<GoodsEntity> goodsEntities = queryGoodsByAgent(agentId, cids, shopIds);
         hpv.setGoodsEntityList(goodsEntities);
         return hpv;
         }
         } **/
        else {
            return null;
        }
    }

    public HomeProductsVo getLoginHomePro(CrmPrdCat cat, HttpServletRequest req) {
        MemberEntity member = loginUtil.getLoginMember(req);

        System.out.println(" getHomePro 01 findFacByagentId  " + cat.getCatId() + " " + cat.getCatName());
        HomeProductsVo hpv = new HomeProductsVo();
        Long agentId = member.getAgentId();
        //用户登录 没有关联工厂  则所有数据为空
        List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
        List<Integer> shopIds = agentFacMapper.queryByShopIdAll(member.getAgentId());
        if (shopIds.isEmpty()) {
            //没有关联工厂   全部为空
            System.out.println(" getHomePro 00 findFacByagentId is null " + cat.getCatId() + " " + cat.getCatName());
            return null;
        } else {
            //工厂不为空
            hpv.setFirstCrmPrdCat(cat);
            String[] befCatIds = cat.getCatIds().split(",");


            //取一级和二级分类下的商品
            //登录下  关联的所有分类 与一级分类//品牌 - 系列 - 分类
            List<CrmPrdCat> allCats = getFacAll(member.getAgentId(), facIds);
            if (allCats.isEmpty()) {
                hpv.setSecondCrmPrdCat(new ArrayList<>());
                List<GoodsEntity> goodsEntities = goodsDao.getGoodsByCatIdWithAgent(cat.getCatId(), agentId.intValue());
                hpv.setGoodsEntityList(goodsEntities);
                return hpv;
            }
            List<Integer> allCatIds = allCats.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
            //===============================对分类进行了迭代，通过子分类选择一级分类start====================================
            List<CrmPrdCat> returns = new ArrayList<CrmPrdCat>();
            List<CrmPrdCat> catIds = getCatIds(allCatIds, returns);
            List<Integer> collect = catIds.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
            //===============================对分类进行了迭代，通过子分类选择一级分类end====================================
            //根据名字查找 一级的ids
            List<CrmPrdCat> allc = crmPrdCatMapper.selectFir(cat.getCatName(), collect, shopIds);
            allc = removeDuplicate(allc);
            List<Integer> firIds = allc.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
            //在所有分类id中  查找一级同名下  id下的二级分类 含重名
            List<CrmPrdCat> seCatList = null;

            if (firIds != null && firIds.size() == 1) {
                seCatList = crmPrdCatMapper.selectSecByAllcatId(firIds.get(0), shopIds);
            } else if (firIds != null && firIds.size() > 1)
                seCatList = crmPrdCatMapper.selectSecByAllcatIds(firIds, shopIds, agentId);

            //if (seCatList.isEmpty() && hpv!=null && hpv.getGoodsEntityList()==null) {
            Set<Long> cids = new HashSet<>();
            for (Integer cid : firIds) {
                cids.add(cid.longValue());
            }
            if (seCatList == null || seCatList.isEmpty()) {
                hpv.setSecondCrmPrdCat(new ArrayList<>());
                //二级为空  显示一级下的上架商品
                List<GoodsEntity> goodsEntities = goodsDao.getGoodsByCatIdWithAgent(cat.getCatId(), agentId.intValue());
                hpv.setGoodsEntityList(goodsEntities);
                return hpv;
            }
            //二级不为空  展示不重名 6个二级
            //List<CrmPrdCat> CatSeconds = seCatList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
            List<CrmPrdCat> CatSeconds = removeDuplicate1(seCatList);
            List<CrmPrdCat> newCats = getloginSecondCat(CatSeconds);
            hpv.setSecondCrmPrdCat(newCats);
            //二级分类 在二级分类中查找和找出的名字相同的二级的集合
            List<String> catNames = newCats.stream().map(CrmPrdCat::getCatName).collect(Collectors.toList());
            //展示的二级含重复值得二级
            List<CrmPrdCat> res = new ArrayList<>();
            for (String name : catNames) {
                for (CrmPrdCat prdCat : seCatList) {
                    if (name.equals(prdCat.getCatName())) {
                        res.add(prdCat);
                    }
                }
            }
            //递归查找下级分类
            cids = getChildCatIds(cids, cids);
//                List<GoodsEntity> goodsEntityList2 = getOnGoods(res);
//                if(!goodsEntityList2.isEmpty() && goodsEntityList2!=null && goodsEntityList2.size()>0)
//                	hpv.setGoodsEntityList(goodsEntityList2);
            //List<GoodsEntity> goodsEntities = queryGoodsByAgent(agentId, cids, shopIds);
            List<GoodsEntity> goodsEntities = goodsDao.getGoodsByCatIdWithAgent(cat.getCatId(), agentId.intValue());
            hpv.setGoodsEntityList(goodsEntities);
            return hpv;

        }
    }

    /**
     * 首页分类商品查询
     *
     * @param cat
     * @param req
     * @return
     */
    public HomeProductsVo oldGetLoginHomePro(CrmPrdCat cat, HttpServletRequest req) {
        MemberEntity member = loginUtil.getLoginMember(req);

        System.out.println(" getHomePro 01 findFacByagentId  " + cat.getCatId() + " " + cat.getCatName());
        HomeProductsVo hpv = new HomeProductsVo();
        Long agentId = member.getAgentId();
        //用户登录 没有关联工厂  则所有数据为空
        //List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
        List<Integer> shopIds = agentFacMapper.queryByShopIdAll(member.getAgentId());
        if (shopIds.isEmpty()) {
            //没有关联工厂   全部为空
            System.out.println(" getHomePro 00 findFacByagentId is null " + cat.getCatId() + " " + cat.getCatName());
            return null;
        } else {
            //工厂不为空
            hpv.setFirstCrmPrdCat(cat);
            String[] befCatIds = cat.getCatIds().split(",");
            List<Long> catIds = new ArrayList<>();
            for (String catId : befCatIds) {
                catIds.add(Long.parseLong(catId));
            }
            //befCatIds   二级分类
            List<CrmPrdCat> crmPrdCats = crmPrdCatService.upCatIdQueryConTent(agentId, catIds, null);

            // List<CrmPrdCat> CatSeconds = removeDuplicate1(crmPrdCats);
            List<CrmPrdCat> newCats = getloginSecondCat(crmPrdCats);
            hpv.setSecondCrmPrdCat(newCats);
            //二级分类 在二级分类中查找和找出的名字相同的二级的集合
            //List<String> catNames = newCats.stream().map(CrmPrdCat::getCatName).collect(Collectors.toList());
            List<GoodsEntity> goodsEntities = goodsDao.newGetGoodsByCatIdWithAgent(catIds, agentId.intValue(), null);
            for (GoodsEntity goodsEntity : goodsEntities) {
                String full_name = StrTools.strDistinct(goodsEntity.getFullName());
                goodsEntity.setFullName(full_name);
            }
            hpv.setGoodsEntityList(goodsEntities);
            return hpv;

        }
    }



    /**
     * 首页分类商品查询
     *
     * @param cat
     * @param req
     * @return
     */
    public HomeProductsVo newGetLoginHomePro(CrmPrdCat cat,int rowCount, HttpServletRequest req) {
    	   MemberEntity member = loginUtil.getLoginMember(req);

           System.out.println(" getHomePro 01 findFacByagentId  " + cat.getCatId() + " " + cat.getCatName());
           HomeProductsVo hpv = new HomeProductsVo();
           Long agentId = member.getAgentId();
           //用户登录 没有关联工厂  则所有数据为空
           //List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
           List<Integer> shopIds = agentFacMapper.queryByShopIdAll(member.getAgentId());
           if (shopIds.isEmpty()) {
               //没有关联工厂   全部为空
               System.out.println(" getHomePro 00 findFacByagentId is null " + cat.getCatId() + " " + cat.getCatName());
               return null;
           } else {
               //工厂不为空
               hpv.setFirstCrmPrdCat(cat);
               String[] befCatIds = cat.getCatIds().split(",");
               List<Long> catIds = new ArrayList<>();
               for (String catId : befCatIds) {
                   catIds.add(Long.parseLong(catId));
               }
               //befCatIds   二级分类
               List<CrmPrdCat> crmPrdCats = crmPrdCatService.upCatIdQueryConTent(agentId, catIds, null);

               // List<CrmPrdCat> CatSeconds = removeDuplicate1(crmPrdCats);
               //List<CrmPrdCat> newCats = getloginSecondCat(crmPrdCats);  mapper文件中做了限制六个的判断
               hpv.setSecondCrmPrdCat(crmPrdCats);
               List<GoodsEntity> goodsEntities = new ArrayList<>();
               //根據一級ID個數判斷有多少個工廠（同一工廠不允许有同名的分類）
               LinkedHashMap<Long,List<GoodsEntity>> goodsDataMap = new LinkedHashMap<>();
              /* int factoryCount = catIds.size();//工厂数
               int oneFactoryCount = rowCount % factoryCount ==0 ? rowCount / factoryCount : rowCount / factoryCount + 1;
               int count = 0;*/
               //根据代理人关联的工厂排序
               List<Map<String,Object>> sortCarList = this.crmPrdCatMapper.findNameByIds(agentId,catIds.stream().collect(Collectors.toSet()));
               //int limtCount = oneFactoryCount;//本次需要提取的数据数量
               for(Map<String,Object> dataMap : sortCarList){
                   Long catId = Long.valueOf(dataMap.get("id").toString());
                   List<Long> catIdList = new ArrayList<>();
                   catIdList.add(catId);
                   List<GoodsEntity> dataList = goodsDao.newTowGetGoodsByCatIdWithAgent(catIdList, agentId.intValue(), null,rowCount);
                   if (CollectionUtils.isNotEmpty(dataList)){
                       for (GoodsEntity goodsEntity : dataList) {
                           String imgs = goodsEntity.getImgs();
                           if (StringUtils.isBlank(imgs)){
                               imgs = StrTools.localImagesPath(req,"/shopping/img/index/sl3.png");
                               goodsEntity.setImgs(imgs);
                           }
                           if (goodsEntity.getFirstImg() == null || "".equals(goodsEntity.getFirstImg())){
                        	   goodsEntity.setFirstImg(StrTools.localImagesPath(req,"/shopping/img/index/sl3.png"));
                           }
                       }
                   }
                   /*for (GoodsEntity goodsEntity : dataList) {
                       String fullName = StrTools.strDistinct(goodsEntity.getFullName());
                       goodsEntity.setFullName(fullName);
                   }
                   int size = 0;
                   if(CollectionUtils.isNotEmpty(dataList)){
                       size = dataList.size();
                       if(size  > limtCount){
                           goodsEntities.addAll(dataList.subList(0,limtCount));
                       } else {
                           goodsEntities.addAll(dataList);
                       }
                   }
                   //重新计算下一次需要提取的数据数量，数量=每个工厂展示的数量 + 上一工厂不足的数量
                   limtCount = oneFactoryCount + (limtCount - size);*/
                   goodsDataMap.put(catId,dataList);
               }
               goodsEntities = this.allocateGoods(rowCount,goodsDataMap);
               for (GoodsEntity goodsEntity : goodsEntities) {
                   String fullName = StrTools.strDistinct(goodsEntity.getFullName());
                   goodsEntity.setFullName(fullName);
               }
               hpv.setGoodsEntityList(goodsEntities);
               return hpv;

           }
    }
    
    /**
     *  commet add by qj at 20190415
     */
    public HomeProductsVo newGetLoginHomePro_bak(CrmPrdCat cat,int rowCount, HttpServletRequest req) {
 	   MemberEntity member = loginUtil.getLoginMember(req);

        System.out.println(" getHomePro 01 findFacByagentId  " + cat.getCatId() + " " + cat.getCatName());
        HomeProductsVo hpv = new HomeProductsVo();
        Long agentId = member.getAgentId();
        //用户登录 没有关联工厂  则所有数据为空
        //List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
        List<Integer> shopIds = agentFacMapper.queryByShopIdAll(member.getAgentId());
        if (shopIds.isEmpty()) {
            //没有关联工厂   全部为空
            System.out.println(" getHomePro 00 findFacByagentId is null " + cat.getCatId() + " " + cat.getCatName());
            return null;
        } else {
            //工厂不为空
            hpv.setFirstCrmPrdCat(cat);
            String[] befCatIds = cat.getCatIds().split(",");
            List<Long> catIds = new ArrayList<>();
            for (String catId : befCatIds) {
                catIds.add(Long.parseLong(catId));
            }
            //befCatIds   二级分类
            List<CrmPrdCat> crmPrdCats = crmPrdCatService.upCatIdQueryConTent(agentId, catIds, null);

            // List<CrmPrdCat> CatSeconds = removeDuplicate1(crmPrdCats);
            List<CrmPrdCat> newCats = getloginSecondCat(crmPrdCats);
            hpv.setSecondCrmPrdCat(newCats);
            List<GoodsEntity> goodsEntities = new ArrayList<>();
            //根據一級ID個數判斷有多少個工廠（同一工廠不允许有同名的分類）
            int factoryCount = catIds.size();//工厂数
            int oneFactoryCount = rowCount % factoryCount ==0 ? rowCount / factoryCount : rowCount / factoryCount + 1;
            int count = 0;
            //根据代理人关联的工厂排序
            List<Map<String,Object>> sortCarList = this.crmPrdCatMapper.findNameByIds(agentId,catIds.stream().collect(Collectors.toSet()));
            int limtCount = oneFactoryCount;//本次需要提取的数据数量
            for(Map<String,Object> dataMap : sortCarList){
                Long catId = Long.valueOf(dataMap.get("id").toString());
                List<Long> catIdList = new ArrayList<>();
                catIdList.add(catId);
                List<GoodsEntity> dataList = goodsDao.newTowGetGoodsByCatIdWithAgent(catIdList, agentId.intValue(), null,limtCount);
                for (GoodsEntity goodsEntity : dataList) {
                    String fullName = StrTools.strDistinct(goodsEntity.getFullName());
                    goodsEntity.setFullName(fullName);
                }
                int size = 0;
                if(CollectionUtils.isNotEmpty(dataList)){
                    size = dataList.size();
                    if(size  > limtCount){
                        goodsEntities.addAll(dataList.subList(0,limtCount));
                    } else {
                        goodsEntities.addAll(dataList);
                    }
                }
                //重新计算下一次需要提取的数据数量，数量=每个工厂展示的数量 + 上一工厂不足的数量
                limtCount = oneFactoryCount + (limtCount - size);
            }
            if(goodsEntities!=null && goodsEntities.size()>=8)
            	hpv.setGoodsEntityList(goodsEntities.subList(0, 8));
            else
            	hpv.setGoodsEntityList(goodsEntities);
            return hpv;

        }
 }    
    /**
     *  commet end by qj at 20190415
     */

    /**
     * 首页商品分类展示算法
     * @param count 需要展示的数据量
     * @param dataMap
     * @return
     */
    private List<GoodsEntity> allocateGoods(int count,LinkedHashMap<Long,List<GoodsEntity>> dataMap){
        LinkedHashMap<Long,List<GoodsEntity>> sortMap = new LinkedHashMap<>();//工厂分类数据
        //轮询去取数据
        int maxNum = count;
        while(true){
            for(Map.Entry<Long,List<GoodsEntity>> entry : dataMap.entrySet()){
                if(count == 0){
                    break;
                }
               for(GoodsEntity goodsEntity : entry.getValue()){
                   if(sortMap.containsKey(entry.getKey())){
                       boolean addFlag = true;
                       for(GoodsEntity goods : sortMap.get(entry.getKey())){
                           if(goodsEntity.getId() == goods.getId()){
                               addFlag = false;
                               break;
                           }
                       }
                       if(addFlag){
                           List<GoodsEntity> sortList = sortMap.get(entry.getKey());
                           sortList.add(goodsEntity);
                           sortMap.put(entry.getKey(),sortList);
                           count--;
                           break;
                       }
                   } else{
                       List<GoodsEntity> dataList = new ArrayList<>();
                       dataList.add(goodsEntity);
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
        List<GoodsEntity> result = new ArrayList<>();
        for(Map.Entry<Long,List<GoodsEntity>> entry :sortMap.entrySet()){
            result.addAll(entry.getValue());
        }
        return result;
    }

    private Set<Long> getChildCatIds(Set<Long> ids, Set<Long> result) {
        if (CollectionUtils.isNotEmpty(ids)) {
            Set<Long> childIds = crmPrdCatMapper.findChildCatids(ids);
            if (CollectionUtils.isNotEmpty(childIds)) {
                result.addAll(childIds);
                result = getChildCatIds(childIds, result);
            }
        }
        return result;
    }


    //登陆下展示6个二级分类
    public List<CrmPrdCat> getloginSecondCat(List<CrmPrdCat> CatSeconds) {
        if (CatSeconds.size() <= 6) {
            return CatSeconds;
        }
        return CatSeconds.subList(0, 6);
    }


    //根据一级分类与工厂Ids获取二级分类 不重名 符合需求6个
    public List<CrmPrdCat> getSecondCat(CrmPrdCat cat, List<Integer> facIds) {
        List<Integer> shopIds = mapper.findShopIdById(facIds);
        SecondCatRequest res = new SecondCatRequest();
        res.setFirstCatName(cat.getCatName());
        res.setFacIds(shopIds);
        System.out.println(" getSecondCat: " + cat.getCatName());
        if (shopIds != null && shopIds.size() > 0)
            for (int index = 0; index < shopIds.size(); index++)
                System.out.println(" getSecondCat shopIds: " + shopIds.get(index));
        List<CrmPrdCat> cats = crmPrdCatMapper.findSecondCat(res);
        //去掉名字重复的
        //List<CrmPrdCat> newCats = cats.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
        List<CrmPrdCat> newCats = removeDuplicate(cats);
        if (newCats.size() <= 6) {
            return newCats;
        }
        return newCats.subList(0, 6);
    }

    //根据二级分类名称与工厂ids获取有重名的二级分类   此条件下所有的
    public List<CrmPrdCat> gethasSecondCat(List<CrmPrdCat> seCat, List<Integer> facIds) {
        List<Integer> shopIds = mapper.findShopIdById(facIds);
        List<String> cNames = seCat.stream().map(CrmPrdCat::getCatName).collect(Collectors.toList());
        CatSecondQueryRequest request = new CatSecondQueryRequest();
        request.setSeCat(cNames);
        request.setFacIds(shopIds);
        return crmPrdCatMapper.selectBycatNameAndFacIds(request);
    }

    //上架商品为空  获取库存商品  展销价不展示
    //catList 有重名的二级分类
    public List<GoodsEntity> getStockGoods(List<CrmPrdCat> catList) {
        Map<String, List<Integer>> map = getsecCatIds(catList);
        List<List<GoodsEntity>> goodsEntities = new ArrayList<>();
        List<Integer> catIds = getCatIds(catList);
        //以二级分类集合拿不是上架的商品 0为库存商品
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
//            GoodsQueryRequest request = new GoodsQueryRequest();
//            request.setIds(entry.getValue());
//            request.setIsMarket(0);
            // 此处应该查询库存
            List<GoodsEntity> goods = goodsDao.getGoodsByCatIds(catIds);//goodsDao.getGoods(request);
            if (!goods.isEmpty()) {
                //展示架不展示   置为_
                List<GoodsEntity> entityList = goods;
//                goods.stream().map(goodsEntity -> {
//                    goodsEntity.setShowPrice(new BigDecimal("_"));
//                    return goodsEntity;
//                }).collect(Collectors.toList());
                goodsEntities.add(entityList);
            }
        }
        List<GoodsEntity> goodsEntityList = findFacByagentId(goodsEntities);
        return goodsEntityList;
    }

//    public List<GoodsEntity> getStockGoodsByAgent(Long agentId,List<CrmPrdCat> catList,List<Integer> shopIds) {
//        Map<String, List<Integer>> map = getsecCatIds(catList);
//        List<List<GoodsEntity>> goodsEntities = new ArrayList<>();
//        List<Integer> catIds=getCatIds(catList);
//        //以二级分类集合拿不是上架的商品 0为库存商品
//        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
////            GoodsQueryRequest request = new GoodsQueryRequest();
////            request.setIds(entry.getValue());
////            request.setIsMarket(0);
//            List<GoodsEntity> goods = goodsDao.querySecGoodsListByagentId(agentId,catIds,shopIds);//goodsDao.getGoods(request);
//            if (!goods.isEmpty()) {
//                //展示架不展示   置为_
//                List<GoodsEntity> entityList = goods;
////                goods.stream().map(goodsEntity -> {
////                    goodsEntity.setShowPrice(new BigDecimal("_"));
////                    return goodsEntity;
////                }).collect(Collectors.toList());
//                goodsEntities.add(entityList);
//            }
//        }
//        List<GoodsEntity> goodsEntityList = findFacByagentId(goodsEntities);
//        return goodsEntityList;
//    }


    public List<GoodsEntity> getOnGoods(List<CrmPrdCat> catList) {
        //Map<String, List<Integer>> map = getsecCatIds(catList);
        List<List<GoodsEntity>> goodsEntities = new ArrayList<>();
        List<Integer> catIds = getCatIds(catList);

        List<GoodsEntity> goods = goodsDao.getGoodsByCatIds(catIds);//goodsDao.getGoods(catIds);
        /*for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            GoodsQueryRequest request = new GoodsQueryRequest();
            request.setIds(entry.getValue());
            //获取上架商品
            request.setIsMarket(1);
            List<GoodsEntity> goods = goodsDao.getGoods(request);
            if (!goods.isEmpty()) {
                goodsEntities.add(goods);
            }
        }*/
        List<GoodsEntity> goodsEntityList = findFacByagentId(goodsEntities);
        return goodsEntityList;
    }

    //上架商品不为空   获取上架商品 943
    //public List<GoodsEntity> getOnGoods(Long agentId,List<CrmPrdCat> catList) {
    public List<GoodsEntity> queryGoodsByAgent(Long agentId, Set<Long> catIds, List<Integer> shopIds) {
        //Map<String, List<Integer>> map = getsecCatIds(catList);
//        List<List<GoodsEntity>> goodsEntities = new ArrayList<>();
//        List<Integer> catIds=getCatIds(catList);

        List<GoodsEntity> goods = goodsDao.querySecGoodsListByagentId(agentId, catIds, shopIds);
        /*for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            GoodsQueryRequest request = new GoodsQueryRequest();
            request.setIds(entry.getValue());
            //获取上架商品
            request.setIsMarket(1);
            List<GoodsEntity> goods = goodsDao.getGoods(request);
            if (!goods.isEmpty()) {
                goodsEntities.add(goods);
            }
        }*/
        List<List<GoodsEntity>> goodsList = new ArrayList<List<GoodsEntity>>();
        goodsList.add(goods);
        List<GoodsEntity> goodsEntityList = findFacByagentId(goodsList);
        return goodsEntityList;
    }

    //二级分类  相同名字的id集合
    public Map<String, List<Integer>> getsecCatIds(List<CrmPrdCat> cats) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (CrmPrdCat cat : cats) {
            String name = cat.getCatName();
            List<Integer> ids = map.get(name);
            if (null == ids) {
                List<Integer> list = new ArrayList<>();
                list.add(cat.getCatId());
                map.put(name, list);
            } else {
                map.get(name).add(cat.getCatId());
            }
        }
        return map;
    }

    //二级分类  相同名字的id集合
    public List<Integer> getCatIds(List<CrmPrdCat> cats) {
        List<Integer> list = new ArrayList<>();
        for (CrmPrdCat cat : cats) {
            if (cat != null && cat.getCatId() != null)
                list.add(cat.getCatId());
        }
        return list;
    }

    //二级分类下的商品取值排列 二级分类最多8个
    public List<GoodsEntity> findFacByagentId(List<List<GoodsEntity>> goodsList) {
        //根据二级分类数   拼接数据
        List<GoodsEntity> goods = new ArrayList<>();
        if (goodsList != null && goodsList.size() > 0) {
            int k = 0;
            for (int i = 0; i < 8; i++) {
                for (int z = 0; z < goodsList.size(); z++) {
                    if (goodsList.get(z).size() > i) {
                        goods.add(goodsList.get(z).get(i));
                        k++;
                        if (k >= 8) {
                            return goods;
                        }
                    }
                }
            }
        }
        return goods;
    }

    public HomeProductsVo getHomeGoods(CrmPrdCat cat, HttpServletRequest req) {
        MemberEntity member = loginUtil.getLoginMember(req);

        if (member != null) {
            Long agentId = member.getAgentId();
            System.out.println(" getHomeGoods 01 agentId  " + agentId);
            HomeProductsVo hpv = new HomeProductsVo();
            //用户登录 没有关联工厂  则所有数据为空
            List<Integer> facIds = agentFacMapper.findFacByagentId(member.getAgentId());
            List<Integer> fIds = agentFacMapper.queryByShopIdAll(member.getAgentId());
            if (fIds.isEmpty()) {
                //没有关联工厂   全部为空
                System.out.println(" getHomeGoods 00 findFacByagentId is null " + cat.getCatId() + " " + cat.getCatName());
                return null;
            } else {
                //工厂不为空
                hpv.setFirstCrmPrdCat(cat);
                List<GoodsEntity> goodsEntityList = goodsDao.queryGoodsListByagentId(agentId, cat.getCatId());
                if (!goodsEntityList.isEmpty() && goodsEntityList != null && goodsEntityList.size() > 0) {
                    System.out.println(" getHomeGoods 00 queryGoodsListByagentId size: " + goodsEntityList.size());
                    hpv.setGoodsEntityList(goodsEntityList);
                }

                //登录下  关联的所有分类 与一级分类
                List<CrmPrdCat> allCats = getFacAll(member.getAgentId(), facIds);
                List<Integer> allCatIds = allCats.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
                //根据名字查找 一级的ids
                List<CrmPrdCat> allc = crmPrdCatMapper.selectFir(cat.getCatName(), allCatIds, fIds);
                List<Integer> firIds = allc.stream().map(CrmPrdCat::getCatId).collect(Collectors.toList());
                //在所有分类id中  查找一级同名下  id下的二级分类 含重名
                List<CrmPrdCat> seCatList = crmPrdCatMapper.selectSecByAllcatIds(firIds, fIds, agentId);
                hpv.setSecondCrmPrdCat(seCatList);
                if (seCatList.isEmpty() && hpv != null && hpv.getGoodsEntityList() == null) {
                    //hpv.setSecondCrmPrdCat(seCatList);

                    //二级为空  显示一级下的上架商品
                    System.out.println(" getHomePro 22 cat 获取数据 : " + cat.getCatId() + " " + cat.getCatName());
                    /*
                    goodsEntityList =getOnGoods(allc);
                    if(!goodsEntityList.isEmpty() && goodsEntityList!=null && goodsEntityList.size()>0)
                    	hpv.setGoodsEntityList(goodsEntityList);
                    else{
                    	System.out.println(" getHomePro 33 cat 获取数据 : "+cat.getCatId()+" "+cat.getCatName());
                    	goodsEntityList=goodsDao.getGoodsByCatId(cat.getCatId());
                    	if(!goodsEntityList.isEmpty() && goodsEntityList!=null && goodsEntityList.size()>0)
                        	hpv.setGoodsEntityList(goodsEntityList);
                    }*/
                    return hpv;
                }
                //二级不为空  展示不重名 6个二级
                //List<CrmPrdCat> CatSeconds = seCatList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCatName()))), ArrayList::new));
                List<CrmPrdCat> CatSeconds = removeDuplicate(seCatList);
                List<CrmPrdCat> newCats = getloginSecondCat(CatSeconds);
                hpv.setSecondCrmPrdCat(getloginSecondCat(newCats));
                //二级分类 在二级分类中查找和找出的名字相同的二级的集合
                List<String> catNames = newCats.stream().map(CrmPrdCat::getCatName).collect(Collectors.toList());
                //展示的二级含重复值得二级
                List<CrmPrdCat> res = new ArrayList<>();
                for (String name : catNames) {
                    for (CrmPrdCat prdCat : seCatList) {
                        if (name.equals(prdCat.getCatName())) {
                            res.add(prdCat);
                        }
                    }
                }

                List<GoodsEntity> goodsEntityList2 = getOnGoods(res);
                if (!goodsEntityList2.isEmpty() && goodsEntityList2 != null && goodsEntityList2.size() > 0)
                    hpv.setGoodsEntityList(goodsEntityList2);
                return hpv;
            }
        }
        return null;
    }

    public static List List(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    public static List removeDuplicate(List list) {
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).equals(list.get(i))) {
                        list.remove(j);
                    }
                }
            }
            return list;
        }
        return null;
    }

    public static List removeDuplicateName(List<Agentband> list) {
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).getBandName().equals(list.get(i).getBandName())) {
                        list.remove(j);
                    }
                }
            }
            return list;
        }
        return null;
    }


    public static List removeDuplicate1(List<CrmPrdCat> list) {
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).getCatName().equals(list.get(i).getCatName())) {
                        list.remove(j);
                    }
                }
            }
            return list;
        }
        return null;
    }

    public static List removeDuplicate2(List<CrmPrdList> list) {
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).getListName().equals(list.get(i).getListName())) {
                        list.remove(j);
                    }
                }
            }
            return list;
        }
        return null;
    }

    public static List removeDuplicate3(List<Custband> list) {
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).getBandName().equals(list.get(i).getBandName())) {
                        list.remove(j);
                    }
                }
            }
            return list;
        }
        return null;
    }


    //253

    /**
     * 递归查询上级分类
     *
     * @param catid
     * @param result
     * @return
     */
    private List<CrmPrdCat> getCatIds(List<Integer> catid, List<CrmPrdCat> result) {
        if (CollectionUtils.isNotEmpty(catid)) {
            List<CrmPrdCat> crmPrdCats = null;
            if (catid != null && catid.size() > 1)
                crmPrdCats = crmPrdCatMapper.selectByCIds(catid);
            else if (catid != null && catid.size() == 1 && null != catid.get(0) && 0 != catid.get(0))
                //if(catid.size()==1&&null==catid.get(0)||catid.size()==0){
                crmPrdCats = crmPrdCatMapper.selectByCId(catid.get(0));
            if (CollectionUtils.isNotEmpty(crmPrdCats)) {
                result.addAll(crmPrdCats);
                List<Integer> collect = crmPrdCats.stream().map(CrmPrdCat::getPcatId).collect(Collectors.toList());
                if (collect.size() > 0)
                    result = getCatIds(collect, result);
                else if (crmPrdCats != null)
                    result = getCatId(crmPrdCats.get(0).getCatId(), result);
            }
        }
        return result;
    }

    private List<CrmPrdCat> getCatId(Integer catid, List<CrmPrdCat> result) {
        if (null != catid) {
            List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.selectByCId(catid);
            if (CollectionUtils.isNotEmpty(crmPrdCats)) {
                result.addAll(crmPrdCats);
                List<Integer> collect = crmPrdCats.stream().map(CrmPrdCat::getPcatId).collect(Collectors.toList());

                if (collect.size() > 0)
                    result = getCatIds(collect, result);
                else if (crmPrdCats != null)
                    result = getCatId(crmPrdCats.get(0).getCatId(), result);
            }
        }
        return result;
    }
    //List<CrmPrdCat> crmPrdCatList = crmPrdCatMapper.selectByIds(catIds);

    /**
     * 根据逗号切割
     */
    private String strIncise(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if ("" == str && null == str) {
            return str;
        }
        String[] split = str.split(",");
        if (split.length > 0) {
            TreeSet<String> hset = new TreeSet<String>(Arrays.asList(split));
            Iterator i = hset.iterator();
            while (i.hasNext()) {
                stringBuffer.append(i.next() + ",");
            }
        } else {
            return str;
        }
        String substring = stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        return substring;
    }

    /*
     * 导航栏筛选和工厂列表
     * */
    @Override
    public JsonResult newNavigation(HttpServletRequest request){
        JsonResult result = new JsonResult();
        NavigationVo navigationVo = new NavigationVo();
        Long agentId = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member != null){
            agentId = member.getAgentId();
            List<Integer> facIds = agentFacMapper.findFacByagentId(agentId);
            if(CollectionUtils.isEmpty(facIds)){
                Screen screen = new Screen();
                screen.setCustbandList(new ArrayList<>());
                screen.setCrmPrdListList(new ArrayList<>());
                screen.setCrmPrdCatList(new ArrayList<>());
                navigationVo.setFactoryList(new FactoryList());
                navigationVo.setScreen(screen);
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("success");
                result.setData(navigationVo);
                return result;
            }
            navigationVo.setFactoryList(fscList(facIds));
        } else{
            List<Integer> facIds = mapper.findAllIds();
            if(CollectionUtils.isEmpty(facIds)){
                Screen screen = new Screen();
                screen.setCustbandList(new ArrayList<>());
                screen.setCrmPrdListList(new ArrayList<>());
                screen.setCrmPrdCatList(new ArrayList<>());
                navigationVo.setFactoryList(new FactoryList());
                navigationVo.setScreen(screen);
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("success");
                result.setData(navigationVo);
                return result;
            }
            navigationVo.setFactoryList(fscList(facIds));
        }
        Screen screen = this.fastSearchService.displayBrandListCat(agentId);
        navigationVo.setScreen(screen);
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        result.setData(navigationVo);
        return result;
    }

    /*
     * 首页商品区域*/
    @Override
    public JsonResult newGetPrdArea(HttpServletRequest request){
        JsonResult result = new JsonResult();
        Long agentId = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member != null){
            agentId = member.getAgentId();
        }
        List<HomeProductsVo> data = this.fastSearchService.displayGoodsArea(agentId);
        //商品和分类默认图片处理
        String imgs ;
        List<GoodsEntity> goodsList;
        CrmPrdCat crmPrdCat;
        for(HomeProductsVo homeProductsVo : data){
            crmPrdCat = homeProductsVo.getFirstCrmPrdCat();
            if (crmPrdCat.getPicPath() == null || "".equals(crmPrdCat.getPicPath())){
                crmPrdCat.setPicPath(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
            }
            //商品默认图片处理
            goodsList = homeProductsVo.getGoodsEntityList();
            if(CollectionUtils.isNotEmpty(goodsList)){
                for(GoodsEntity goodsEntity : homeProductsVo.getGoodsEntityList()){
                    imgs = goodsEntity.getImgs();
                    if (StringUtils.isBlank(imgs)){
                        imgs = StrTools.localImagesPath(request,"/shopping/img/index/sl3.png");
                        goodsEntity.setImgs(imgs);
                    }
                    if (goodsEntity.getFirstImg() == null || "".equals(goodsEntity.getFirstImg())){
                        goodsEntity.setFirstImg(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                    }
                }
            }
        }
        result.setData(data);
        result.setSuccess(true);
        result.setCode(1);
        result.setMessage("success");
        return result;
    }

    @Autowired
    private FastSearchService fastSearchService;
}
