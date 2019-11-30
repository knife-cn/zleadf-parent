package com.zlead.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zlead.constant.Cnst;
import com.zlead.dao.MemberDao;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.transaction.AgentTrans;
import com.zlead.fplat.transaction.FactoryTrans;
import com.zlead.service.readExcelFileService;
import com.zlead.utils.MD5Util;
import com.zlead.utils.ReadExcel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class readExcelFileServiceImpl implements readExcelFileService {

    @Autowired
    private OaAgentMasMapper agentMasMapper;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private AgentTrans agentTrans;
    @Autowired
    private FactoryTrans factoryTrans;


    @Override
    public String readAgentFile(MultipartFile file) {
        int i = 0;
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<List<Object>> excelInfo = readExcel.getExcelInfo(file);
        System.out.println(excelInfo);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        // return null;
        try {
            for (List agent : excelInfo) {

                String shopName = agent.get(1).toString();
                String shopStatus = agent.get(2).toString();
                String contactsName = agent.get(3).toString();
                String contactsPhone = agent.get(4).toString();
                System.out.println(" 电话号码：" + contactsPhone);
                String companyTopRegionId = agent.get(5).toString();
                String companyCityRegionId = agent.get(6).toString();
                String companyRegionId = agent.get(7).toString();


                OaAgentMas Oagent = new OaAgentMas();

                if (contactsPhone != null && contactsPhone.length() != 0) {
                    Oagent.setLinkTel(contactsPhone);//联系电话ContactsPhone
                    Oagent.setLinkName(contactsName);//ContactsName  联系人姓名
                    Oagent.setAgentName(shopName);//ShopName 代理商名称
                    Oagent.setAgentState("1");//ShopStatus
                    // 店铺状态:1(不可用),2(待审核),3(待付款),4(被拒绝),5(待确认),6(冻结),7(开启)  只要两种状态
                    if (null != agent.get(8)) {
                        String companyAddress = agent.get(8).toString();
                        Oagent.setAgentAddr(companyAddress);//CompanyAddress 代理商地址
                    }
                    Oagent.setRevProvince(companyTopRegionId + "." + companyCityRegionId + "." + companyRegionId);//CompanyTopRegionId  CompanyCityRegionId  CompanyRegionId,省市区
                    // 省市区的名称拼接一块合并成该字段
                    i = agentTrans.insertAgent(Oagent);
                } else {
                    System.out.println("电话号码不可为空");
                }
                if (i == 0) {
                    result = "插入数据库失败";
                }
            }

            if (excelInfo != null && !excelInfo.isEmpty()) {
                result = "上传成功";
            } else {
                result = "上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String importAgentFile(MultipartFile file) {
        int i = 0;
        String result = "";
        ReadExcel readExcel = new ReadExcel();
        List<List<Object>> excelInfo = readExcel.getExcelInfo(file);
        System.out.println(excelInfo);
        try {
            for (List obj : excelInfo) {
                i++;
                boolean flag = agentTrans.insertOaAgentMasAndMember(obj);
                if (!flag) {
                    System.out.println("第" + i + "行导入失败:" + JSONObject.toJSONString(obj));
                }
//                System.out.println("c_a=" + c_a + ",c_b=" + c_b + ",c_c=" + c_c + ",c_d=" + c_d + ",c_e=" + c_e + ",c_f=" + c_f + ",c_g=" + c_g + ",c_h=" + c_h + ",c_i=" + c_i + ",c_j=" + c_j + ",c_k=" + c_k + ",c_l=" + c_l + ",c_m=" + c_m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String readFacFile(MultipartFile file) {

        int ref = 0;
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<List<Object>> excelInfo = readExcel.getExcelInfo(file);
        try {
            for (List factoryColumn : excelInfo) {
                String factoryName = factoryColumn.get(0).toString();
                String userName = factoryColumn.get(1).toString();
                String phone = factoryColumn.get(2).toString();
                String sheng = factoryColumn.get(3).toString();
                String city = factoryColumn.get(4).toString();
                String add = factoryColumn.get(5).toString();
                String user = factoryColumn.get(6).toString();
                String time = factoryColumn.get(7).toString();


                OaFactoryInfo factoryInfo = new OaFactoryInfo();

                if (phone != null && phone.length() != 0) {
                    factoryInfo.setFactName(factoryName);
                    factoryInfo.setContactMan1(userName);
                    factoryInfo.setContactNo(phone);
                    factoryInfo.setModifyTime(new Date());
                    factoryTrans.insertFactory(factoryInfo);

                } else {
                    System.out.println("电话号码不可为空");


                }
                if (ref == 0) {
                    result = "插入数据库失败";
                }
            }

            if (excelInfo != null && !excelInfo.isEmpty()) {
                result = "上传成功";
            } else {
                result = "上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public String readProdFile(MultipartFile file) {
        return null;
    }


}
