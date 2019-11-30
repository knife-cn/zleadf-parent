package com.zlead.member.controller;

import com.zlead.entity.dto.MemberAddressDto;
import com.zlead.fplat.dao.AgentAddressMapper;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.entity.AgentAddress;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.service.AgentFacService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.puqian.payment.apipay.commonUtil.StringUtil;
import com.zlead.entity.Region;
import com.zlead.entity.MemberAddressEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.reception.service.MemberAddressService;
import com.zlead.service.RegionService;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;




/**
 * 会员收货地址
 *
 * @author fqf
 * @date 2018-07-31 15:06:05
 */
@Controller
@RequestMapping("/zlead/memaddr")
public class MemberMgrAddressController {
    @Resource
    private MemberAddressService memberAddressService;

    @Resource
    private RegionService regionService;

    @Resource
    private AgentAddressMapper agentAddressMapper;


    @Resource
    private AgentFacService agentFacService;

    @Resource
    private LoginUtil loginUtil;
    /*
     * 获取所有的地址信息  老的
     */
    @RequestMapping("/getAllAddress")
    @ResponseBody
    public JsonResult getAllAddress(HttpServletRequest request){
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                //老的
                List<Map<String, Object>> list = memberAddressService.getAllAddress(member);
                if(list!=null&&list.size()>0){
                    jsonResult =  new JsonResult(1,"地址列表",list,true);
                }else{
                    jsonResult =  new JsonResult(2,"没有地址信息","",false);
                }
            }else{
                jsonResult =  new JsonResult(2,"没有查询到登录用户","",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("返回用户地址信息:"+result);
        return jsonResult;
    }

    /*
     * 获取所有的代理商地址信息  新的
     */
    @RequestMapping("/getAllAgentAddress")
    @ResponseBody
    public JsonResult getAllAgentAddress(HttpServletRequest request){
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                //新的
                Map<String, List<MemberAddressDto>> list = memberAddressService.getAllAgentAddress(member);
                if(list!=null&&list.size()>0){
                    jsonResult =  new JsonResult(1,"地址列表",list,true);
                }else{
                    jsonResult =  new JsonResult(2,"没有地址信息","",false);
                }
            }else{
                jsonResult =  new JsonResult(2,"没有查询到登录用户","",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("返回用户地址信息:"+result);
        return jsonResult;
    }


    /**
     * 获取默认地址
     */
    @RequestMapping("/getDefaultAddress")
    @ResponseBody
    public JsonResult getDefaultAddress(HttpServletRequest request){
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                //获取默认的地址
                MemberAddressEntity address = memberAddressService.getFirstOfMember(member.getId());
                jsonResult =  new JsonResult(1,"返回用户",address,true);
            }else{
                jsonResult =  new JsonResult(2,"没有查询到登录用户","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 添加修改地址
     */
    @RequestMapping("/addOrUpdateMemberAddress")
    @ResponseBody
    public JsonResult addOrUpdateMemberAddress(@RequestParam(name="memberName",required=true)@Validated @NotNull String memberName,
                                               @RequestParam(name="phone",required=true)@Validated @NotNull String phone,
                                               @RequestParam(name="provinceName",required=true)@Validated @NotNull String provinceName,
                                               @RequestParam(name="cityName",required=true)@Validated @NotNull String cityName,
                                               @RequestParam(name="countyName",required=true)@Validated @NotNull String countyName,
                                               @RequestParam(name="detailAddress",required=true)@Validated @NotNull String detailAddress,
                                               @RequestParam(name="isDefault",required=true)@Validated @NotNull int isDefault,
                                               @RequestParam(name="type",required=true)@Validated @NotNull int type,
                                               @RequestParam(name="addressId",required=false) Long addressId,
                                               HttpServletRequest request){
        JsonResult jsonResult = null;
        try {
            //查询登录的用户信息
            MemberEntity member = loginUtil.getLoginMember(request);
            //省的编号
            String provinceId = "";
            //市的编号
            String cityId = "";
            //县的编号
            String countryId = "";
            //转码
			/*memberName = new String(memberName.getBytes("ISO-8859-1"),"UTF-8");
			provinceName= new String(provinceName.getBytes("ISO-8859-1"),"UTF-8");
			cityName= new String(cityName.getBytes("ISO-8859-1"),"UTF-8");
			countyName= new String(countyName.getBytes("ISO-8859-1"),"UTF-8");*/
            //resp.setContentType("text/html;charset=utf-8");
            //通过省的名称查询省的编号
            Region regionProvince = regionService.getRegion(1, provinceName);
            if(regionProvince!=null){
                provinceId = regionProvince.getRegionCode();
                //查询市的信息
                Region regionCity = regionService.getRegion(regionProvince.getId(), cityName);
                if(regionCity!=null){
                    cityId = regionCity.getRegionCode();
                    //查询县的信息
                    Region regionCounty = regionService.getRegion(regionCity.getId(), countyName);
                    if(regionCounty!=null){
                        countryId = regionCounty.getRegionCode();
                    }
                }
            }
            if(member!=null){
                if(!StringUtil.isNullOrEmpty(provinceId)&&!StringUtil.isNullOrEmpty(cityId)&&!StringUtil.isNullOrEmpty(countryId)){
                    long  memberId = member.getId();
                    //type 1:添加2：修改
                    MemberAddressEntity address = null;
                    if(type==1){
                        address = new MemberAddressEntity();
                        address.setMemberId(memberId);
                        address.setMemberName(memberName);

                        List<String> listById = memberAddressService.getListById(member.getId());
                        if (!listById.equals(phone)) {//判断手机号不重复
                            address.setPhone(phone);
                            if(!StringUtil.isNullOrEmpty(provinceId)){
                                address.setProvinceId(Long.parseLong(provinceId));
                            }
                            if(!StringUtil.isNullOrEmpty(cityId)){
                                address.setCityId(Long.parseLong(cityId));
                            }
                            if(!StringUtil.isNullOrEmpty(countryId)){
                                address.setRegionId(Long.parseLong(countryId));
                            }
                            address.setAddress(detailAddress);
                            address.setIsDefault(isDefault);
                            //代理商地址ID
                            //address.setRevId(agentAddress.getRevId());
                            memberAddressService.save(address);
                            //保存信息
                            Integer memberType = member.getMemberType();
                            AgentAddress agentAddress = new AgentAddress();
                            if (memberType==3){
                                //agent_id
                                Long agentId = member.getAgentId();
                                List<AgentFac> agentFac = agentFacService.findAgentFac(agentId);
                                for (AgentFac fac : agentFac) {
                                    if(fac!=null && fac.getSysId()!=null){
                                    //member_adr_id
                                    agentAddress.setMemberAddrId(address.getId().intValue());
                                    //工厂ID
                                    agentAddress.setSysId(fac.getSysId());
                                    //代理商ID
                                    agentAddress.setAgentId(member.getAgentId().intValue());
                                    //姓名
                                    agentAddress.setRevName(address.getMemberName());
                                    //电话
                                    agentAddress.setRevTel(address.getPhone());
                                    //省
                                    agentAddress.setRevProvince(address.getProvinceId()+"");
                                    //市
                                    agentAddress.setRevCity(address.getCityId()+"");
                                    //县
                                    agentAddress.setRevCounty(address.getRegionId()+"");
                                    //具体地址
                                    agentAddress.setRevAddr(address.getAddress());
                                    //是否默认
                                    agentAddress.setRevDefault(address.getIsDefault()+"");
                                    //表示不是工厂端创建
                                    agentAddress.setIsFact("0");
                                    agentAddress.setRevDefault(address.getIsDefault().toString());
                                    agentAddressMapper.insert(agentAddress);
                                    }else{
                                        if(fac!=null)
                                        System.out.println("addOrUpdateMemberAddress fac sysid is null. agentFac id: "+fac.getId());
                                    }
                                }
                            }
                            //判断是够为默认的地址是的话将以前的地址都改成非默认  会员地址
                            if(isDefault==1){
                                memberAddressService.updateOtherIsNotDefault(address.getMemberId(), address.getId());
                            }
                            if (memberType == 3 && "1".equals(agentAddress.getRevDefault())) {
                                agentAddressMapper.updateAgentAddressOtherIsDefault(address.getId().intValue(),member.getAgentId().intValue());
                            }
                            jsonResult =  new JsonResult(1,"地址添加成功！","",true);
                        }else{
                            jsonResult =  new JsonResult(2,"该手机号已经用于其他地址注册！","",false);
                        }

                    }else if(type==2){
                        if(addressId!=null){
                            address = memberAddressService.getById(addressId);
                            if(address!=null){
                                //address.setMemberId(memberId);
                                address.setMemberName(memberName);

                                List<String> listById = memberAddressService.getListById(member.getId());
                                if (!listById.equals(phone)) {//判断手机号不重复
                                    address.setPhone(phone);
                                    if(!StringUtil.isNullOrEmpty(provinceId)){
                                        address.setProvinceId(Long.parseLong(provinceId));
                                    }
                                    if(!StringUtil.isNullOrEmpty(cityId)){
                                        address.setCityId(Long.parseLong(cityId));
                                    }
                                    if(!StringUtil.isNullOrEmpty(countryId)){
                                        address.setRegionId(Long.parseLong(countryId));
                                    }

                                    address.setAddress(detailAddress);
                                    address.setIsDefault(isDefault);
                                    //修改信息
                                    memberAddressService.update(address);
                                    Integer memberType = member.getMemberType();
                                    if (memberType==3) {
                                        List<AgentAddress> agentAddressList = agentAddressMapper.selectByPrimaryKeyList(address.getId().intValue());
                                        for (AgentAddress adlist : agentAddressList) {
                                            if (address.getIsFact()!=1){
                                                //代理商ID
                                                adlist.setAgentId(member.getAgentId().intValue());
                                                //姓名
                                                adlist.setRevName(address.getMemberName());
                                                //电话
                                                adlist.setRevTel(address.getPhone());
                                                //省
                                                adlist.setRevProvince(address.getProvinceId() + "");
                                                //市
                                                adlist.setRevCity(address.getCityId() + "");
                                                //县
                                                adlist.setRevCounty(address.getRegionId() + "");
                                                //具体地址
                                                adlist.setRevAddr(address.getAddress());
                                                //默认地址
                                                adlist.setRevDefault(address.getIsDefault().toString());

                                                adlist.setMemberAddrId(address.getId().intValue());
                                                agentAddressMapper.updateByPrimaryKey(adlist);
                                                if ("0".equals(adlist.getIsFact()) && "1".equals(adlist.getRevDefault())) {
                                                    agentAddressMapper.updateAgentAddressOtherIsDefault(address.getId().intValue(),member.getAgentId().intValue());
                                                }
                                            }
                                        }
                                    }
                                    //判断是够为默认的地址是的话将以前的地址都改成非默认
                                    if(isDefault==1){
                                        memberAddressService.updateOtherIsNotDefault(address.getMemberId(), address.getId());
                                    }
                                    jsonResult =  new JsonResult(1,"地址修改成功！","",true);
                                }else {
                                    jsonResult =  new JsonResult(2,"该手机号已经用于其他地址注册！","",false);
                                }
                            }else{
                                jsonResult =  new JsonResult(2,"没有查询到该地址！","",false);
                            }
                        }else{
                            jsonResult =  new JsonResult(2,"地址id为空！","",false);
                        }
                    }else{
                        jsonResult =  new JsonResult(2,"添加或者修改的标识不正确！","",false);
                    }
                }else{
                    jsonResult =  new JsonResult(2,"传入的省市县有误","",false);
                }
            }else{
                jsonResult =  new JsonResult(2,"没有查询到登录用户","",false);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("返回用户地址信息:"+result);
        return jsonResult;
    }

    /**
     * 删除地址
     */
    @RequestMapping("/deleteMemberAddress")
    @ResponseBody
    public JsonResult deleteMemberAddress(HttpServletRequest request,
                                          @RequestParam(name="addressId",required=true)@Validated @NotNull Long addressId){
        JsonResult jsonResult = null;
        try {
            MemberAddressEntity byId = memberAddressService.getById(addressId);
            //删除当前的地址
            memberAddressService.delete(addressId);
            agentAddressMapper.deleteByPrimaryKey(byId.getId().intValue());

            jsonResult =  new JsonResult(1,"地址删除成功！","",true);
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /*
     * 将地址设置成默认地址
     */
    @RequestMapping("/setDefaultAddress")
    @ResponseBody
    public JsonResult setDefaultAddress(HttpServletRequest request,
                                        @RequestParam(name="addressId",required=true)@Validated @NotNull Long addressId){
        JsonResult jsonResult = null;
        try {
            //查询登录的用户信息
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                long memberId = member.getId();
                //将该地址设置为默认
                MemberAddressEntity address = memberAddressService.getById(addressId);
                if(address!=null){
                    //会员地址
                    address.setIsDefault(1);
                    memberAddressService.update(address);
                    //会员    将之前的地址设置为非默认
                    memberAddressService.updateOtherIsNotDefault(memberId, addressId);
                    //代理商
                    if (member.getMemberType() == 3) {
                        List<AgentAddress> agentAddress = agentAddressMapper.selectByPrimaryKeyList(address.getId().intValue());
                        for (AgentAddress agentAds : agentAddress) {
                            if ("0".equals(agentAds.getIsFact())) {
                                agentAds.setRevDefault("1");
                                agentAddressMapper.updateByPrimaryKey(agentAds);
                                agentAddressMapper.updateAgentAddressOtherIsDefault(address.getId().intValue(),member.getAgentId().intValue());
                            }
                        }
                    }
                    jsonResult =  new JsonResult(1,"默认地址设置成功！","",true);
                }else{
                    jsonResult =  new JsonResult(2,"没有查询到该地址","",false);
                }
            }else{
                jsonResult =  new JsonResult(2,"没有查询到登录用户","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("返回用户地址信息:"+result);
        return jsonResult;
    }

}
