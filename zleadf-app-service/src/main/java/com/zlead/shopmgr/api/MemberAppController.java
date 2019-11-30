package com.zlead.shopmgr.api;


import com.zlead.dao.ShopDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberAddressEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.Region;
import com.zlead.entity.vo.MemberAddrVo;
import com.zlead.entity.vo.MemberInfoVo;
import com.zlead.entity.vo.RegionVo;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.transaction.AgentTrans;
import com.zlead.reception.service.MemberAddressService;
import com.zlead.reception.service.MemberService;
import com.zlead.service.RegionService;
import com.zlead.shopmgr.service.AclUserService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.utils.AppUtil;
import com.zlead.utils.HttpUtil;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.MD5Util;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 登录注册接口
 */
@Controller
@RequestMapping("/api/member")
public class MemberAppController {
    @Resource
    private MemberService memberService;

    @Resource
    private MemberAddressService memberAddressService;

    @Resource
    private LoginUtil loginUtil;

    @Resource
    private HttpUtil httpUtil;

    @Resource
    private RegionService regionService;

    private String weburl;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private AgentTrans agentTrans;

    @Autowired
    private AclUserService aclUserService;

    @Value("#{fdfs['fdfs_url']}")
    private String path;

    @ModelAttribute
    public void getWebUrl() {
        weburl = httpUtil.getWebUrl();
    }

    //用户注册接口
    @RequestMapping("registered")
    @ResponseBody
    public JsonResult userRegist(HttpServletRequest request, MemberEntity memberEntity) {
        JsonResult jsonResult = null;
//        String result=null;
        String phone = request.getParameter("phone");
        String passWord = request.getParameter("passWord");
//        String provinceName= request.getParameter("provinceName");
//        String cityName= request.getParameter("cityName");
//        String countyName = request.getParameter("countyName");
//        result = JsonUtil.getJson(jsonResult);
        //  JsonUtil.render(response, result);
        //查询电话有没有被占用
        MemberEntity member = memberService.findByPhone(phone);
        if (member != null) {
            jsonResult = new JsonResult(2, "该手机号已被占用", "", false);
            return jsonResult;
        }
        //boolean b = memberService.saveMember(phone,passWord);
        OaAgentMas agentMas = new OaAgentMas();
        agentMas.setAgentState("1");
        agentMas.setLinkTel(phone);
        int b = agentTrans.insertAgentWithPass(agentMas, passWord);
        if (b > 0) {
            jsonResult = new JsonResult(1, "注册成功", "", true);
        } else {
            jsonResult = new JsonResult(2, "注册失败", "", false);
        }
        return jsonResult;
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult appLogin(@RequestBody AclUserEntity aclUserEntity, HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            Map map = aclUserService.appLogin(aclUserEntity.getUsername(), aclUserEntity.getPassword(), request);
            if (map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                Object data = map.get("data");
                if ("true".equals(result) && map.get("data") != null) {
                    jsonResult = new JsonResult(1, message, data, true);
                } else if ("false".equals(result)) {
                    jsonResult = new JsonResult(2, message, false);
                }
            } else {
                jsonResult = new JsonResult(2, "用户名或密码错误", false);
            }
        } catch (Exception e) {
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }


    /**
     * 会员/代理商个人详细信息
     *
     * @param request
     * @return
     */
    @RequestMapping("memberInfo")
    @ResponseBody
    public JsonResult memberInfo(HttpServletRequest request) {
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            MemberInfoVo appMemberInfo = memberService.getAppMemberInfo(member.getId());
            if (appMemberInfo != null) {
                jsonResult = new JsonResult(1, "获取个人信息成功", appMemberInfo, true);
            }
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }


    //忘记密码接口
    @RequestMapping("forgetPass")
    @ResponseBody
    public String forgetPass(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String result = null;
        String phone = request.getParameter("phone");
        String newPass = request.getParameter("newPass");

        MemberEntity member = memberService.findByPhone(phone);
        if (member != null) {
            member.setPassword(MD5Util.toMD5(newPass));
            memberService.updateById(member);
            if (MD5Util.toMD5(newPass).equals(member.getPassword())) {
                jsonResult = new JsonResult(1, "密码修改成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "密码修改失败", "", false);
            }
        } else {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
        }

        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);
        return null;
    }

    /**
     * 已知密码的情况下 修改密码接口
     *
     * @param request
     * @param em
     * @return
     */
    @RequestMapping("updatePwd")
    @ResponseBody
    public JsonResult updatePwd(HttpServletRequest request, @RequestBody MemberEntity em) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        MemberEntity sc = memberService.findById(member.getId());
        //原密码
        String paw = em.getNpassword();
        //新密码
        String pawred = em.getPassword();
        //原密码 转md5
        String pawt = MD5Util.toMD5(paw);
        if (member != null) {
            //判断原密码与数据库是否相同 md5的情况
            if (sc.getPassword().equals(pawt)) {
                em.setPassword(MD5Util.toMD5(pawred));
                em.setId(member.getId());
                em.setMemberId(member.getMemberId());
                em.setUsername(member.getUsername());
                em.setNickName(member.getNickName());
                em.setIfPhone(member.getIfPhone());
                em.setAccount(member.getAccount());
                em.setIsDisable(member.getIsDisable());
                em.setIsDelete(member.getIsDelete());
                em.setIsStaff(member.getIsStaff());
                em.setIsVip(member.getIsVip());
                em.setCreateTime(member.getCreateTime());
                em.setIsSalesmen(member.getIsSalesmen());
                em.setAgentId(member.getAgentId());
                int a = memberService.updatepwd(em);
                AclUserEntity aclUser = aclUserService.findUserByMemberId(em.getId());
                int b = aclUserService.updatepwd(em.getPassword(),aclUser.getUserId());
                if(a==1&&b==1){
                    jsonResult = new JsonResult(1, "密码修改成功", "", true);
                    //同时登出前台跳转登录页面
                    memberService.appLogout(request);
                }else{
                    jsonResult = new JsonResult(2, "密码修改失败", "", false);
                }
            } else {
                jsonResult = new JsonResult(2, "请输入正确的旧密码", "", false);
            }
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }

    /**
     * 修改用户
     *
     * @return
     */
    @RequestMapping("updateVipe")
    @ResponseBody
    public String updatevipe(MemberEntity em, HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String result = null;
        //获取已登录用户信息
//        em.setNickName("测试");
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null) {
            member.setNickName(em.getNickName());
            member.setGender(em.getGender());
            member.setProvinceId(em.getProvinceId());
            member.setCityId(em.getCityId());
            member.setRegionId(em.getRegionId());
            member.setAddress(em.getAddress());
            member.setBirthday(em.getBirthday());
            int in = memberService.updatevipe(member);
            if (in != 0) {
                jsonResult = new JsonResult(1, "用户修改成功", in, true);
            } else {
                jsonResult = new JsonResult(1, "用户修改失败", in, false);
            }
        } else {
            jsonResult = new JsonResult(1, "用户不存在", false);
        }
        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);

        return null;
    }

    /**
     * 登出接口
     */
    @RequestMapping("/logout")
    @ResponseBody
    public JsonResult appLogout(HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            Map map = memberService.appLogout(request);
            if (map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                if ("true".equals(result)) {
                    Map data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    jsonResult = new JsonResult(1, message, data, true);
                } else if ("false".equals(result)) {
                    jsonResult = new JsonResult(2, message, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }


    //个人信息接口
    @RequestMapping("uploading")
    @ResponseBody
    public JsonResult uploading(@RequestPart("demo_input") MultipartFile file, HttpServletRequest request) throws Exception {
        JsonResult jsonResult = null;
        String result = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            System.out.println(" uploading ");
            try {
                ClientGlobal.init(this.getClass().getResource("/fdfs_client.properties").toURI().toURL().getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient client = new StorageClient(trackerServer, storageServer);
            Random random1 = new Random(100000);
            random1.nextInt();
            String uploadFileName = random1.nextInt() + ".jpg";

            String fileExtName = "";  //随机名
            if (uploadFileName.contains(".")) {
                fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
            } else {
                System.out.println("uploading Fail to upload file, because the format of filename is illegal.");
                return null;
            }
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", uploadFileName);//文件全名
            metaList[1] = new NameValuePair("fileExtName", fileExtName);//截取文件后缀的文件名
            metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));//文件长度
            String[] files = client.upload_file(file.getBytes(), fileExtName, metaList);
            String headImg = path + files[1];
            System.out.println("uploading " + Arrays.asList(files));
            System.out.println(headImg + " uploading ");
            //下载文件
            // downloadFile(files[0],files[1]);

            //将图片名称保存到数据库中
            int isSuceess = memberService.saveMemberHeadImg(headImg, member.getId());
//            FastDFSController.deleteFile("group1",member.getHeadImg());
            if (isSuceess > 0) {
                jsonResult = new JsonResult(1, "上传图片成功", headImg, true);
            }
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }


    //个人信息接口
    @RequestMapping("uploadVoucher")
    @ResponseBody
    public JsonResult uploadVoucher(@RequestPart("demo_input") MultipartFile[] fileList, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResult jsonResult = null;
        String result = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            System.out.println(" uploading ");
            try {
                ClientGlobal.init(this.getClass().getResource("/fdfs_client.properties").toURI().toURL().getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient client = new StorageClient(trackerServer, storageServer);
            StringBuilder dataStr = new StringBuilder();
            if (fileList.length > 1) {
                jsonResult = new JsonResult(2, "凭证只能上传一张图片", "", false);
            } else {
                Random random1 = new Random(100000);
                random1.nextInt();
                String uploadFileName = random1.nextInt() + ".jpg";

                String fileExtName = "";  //随机名
                if (uploadFileName.contains(".")) {
                    fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                } else {
                    System.out.println("uploading Fail to upload file, because the format of filename is illegal.");
                    return null;
                }
                NameValuePair[] metaList = new NameValuePair[3];

                metaList[0] = new NameValuePair("fileName", uploadFileName);//文件全名
                metaList[1] = new NameValuePair("fileExtName", fileExtName);//截取文件后缀的文件名
                metaList[2] = new NameValuePair("fileLength", String.valueOf(fileList[0].getSize()));//文件长度
                String[] files = client.upload_file(fileList[0].getBytes(), fileExtName, metaList);
                String data = path  + files[1];
                jsonResult = new JsonResult(1, "上传图片成功", data, true);
            }
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }

    /**
     * 收货人地址查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping("memberAddr")
    @ResponseBody
    public JsonResult memberAddr(HttpServletRequest request,Integer agentId) {
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            List<MemberAddrVo> memberAddrs = memberAddressService.getAppMemberAddr(agentId,member.getId());
            if (memberAddrs != null && memberAddrs.size() > 0) {
                jsonResult = new JsonResult(1, "获取个人地址信息成功", memberAddrs, true);
            }
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }

    /**
     * 新增收货人地址接口
     *
     * @param request
     * @param memberAddrVo
     * @return
     */
    @RequestMapping("addAddr")
    @ResponseBody
    public JsonResult addMemberAddr(@RequestBody MemberAddrVo memberAddrVo,
                                    HttpServletRequest request) {
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            if(AppUtil.containsEmoji(memberAddrVo.getMemberName())||AppUtil.containsEmoji(memberAddrVo.getAddress())){
                jsonResult = new JsonResult(2, "包含非法字符", false);
                return jsonResult;
            }
            boolean isSuccess = memberAddressService.save(memberAddrVo, member);
            if(isSuccess){
                jsonResult = new JsonResult(1, "添加地址信息成功", true);
            }
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }


    /**
     * 编辑收货人地址接口
     *
     * @param request
     * @param memberAddrVo
     * @return
     */
    @RequestMapping("updateAddr")
    @ResponseBody
    public JsonResult updateMemberAddr(@RequestBody MemberAddrVo memberAddrVo, HttpServletRequest request) {
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            MemberAddressEntity addressEntity = new MemberAddressEntity();
            addressEntity.setId(memberAddrVo.getAddrId());
            addressEntity.setMemberId(member.getId());
            addressEntity.setPhone(memberAddrVo.getPhone());
            addressEntity.setProvinceId(Long.parseLong(memberAddrVo.getProvinceId()));
            addressEntity.setCityId(Long.parseLong(memberAddrVo.getCityId()));
            addressEntity.setRegionId(Long.parseLong(memberAddrVo.getAreaId()));
            addressEntity.setMemberName(memberAddrVo.getMemberName());
            addressEntity.setAddress(memberAddrVo.getAddress());
            addressEntity.setIsDefault(memberAddrVo.getIsDefault());
            memberAddressService.update(addressEntity);
            if (addressEntity.getIsDefault() == 1) {
                memberAddressService.updateOtherIsNotDefault(member.getId(), memberAddrVo.getAddrId());
            }
            jsonResult = new JsonResult(1, "修改地址信息成功", true);
        } else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }

    /**
     * 三级联动
     * ytchen
     * 19-1-22
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/findProvince")
    public JsonResult findProvince(HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            List<Region> regions = regionService.findprovince();
            List<RegionVo> regionVos = new ArrayList<>();
            for (Region region : regions) {
                RegionVo regionVo = new RegionVo();
                regionVo.setRegionId(region.getId());
                regionVo.setRegionCode(region.getRegionCode());
                regionVo.setRegionName(region.getRegionName());
                regionVos.add(regionVo);
            }
            jsonResult = new JsonResult(1, "获取列表成功", regionVos, true);
        }else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }


        return jsonResult;
    }

    /**
     * 三级联动 市/县/区
     * ytchen
     * 19-1-22
     *
     * @param parentId
     * @return jsonResult
     */
    @ResponseBody
    @RequestMapping("/findRegion")
    public JsonResult findRegion(@RequestParam("parentId") @Validated @NotNull Integer parentId,HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member != null) {
            List<Region> regions = regionService.findid(parentId);
            List<RegionVo> regionVos = new ArrayList<>();
            for (Region region : regions) {
                RegionVo regionVo = new RegionVo();
                regionVo.setRegionId(region.getId());
                regionVo.setRegionCode(region.getRegionCode());
                regionVo.setRegionName(region.getRegionName());
                regionVos.add(regionVo);
            }
            jsonResult = new JsonResult(1, "地址信息市/县/区", regionVos, true);
        }else {
            jsonResult = new JsonResult(3, "未登录", "", false);
        }
        return jsonResult;
    }


}
