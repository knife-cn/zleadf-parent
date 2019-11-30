package com.zlead.reception.service.impl;

import com.puqian.payment.apipay.commonUtil.StringUtil;
import com.zlead.config.LogisticsConfig;
import com.zlead.dao.*;
import com.zlead.entity.*;
import com.zlead.reception.service.LogisticsService;
import com.zlead.util.page.PageBounds;
//import com.zlead.model.ZxOrder;
//import com.zlead.model.ZxOrderShipping;
import com.zlead.utils.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物流信息
 * @author: fqf
 **/
@Service
@Transactional
public class LogisticsServiceImpl implements LogisticsService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ExpressSubscriptionDao expressSubscriptionDao;

    @Autowired
    private LogisticsInformationDao logisticsInformationDao;

    @Autowired
    private OrderShippingDao orderShippingDao;

    @Autowired
    private ExpressCompanyDao expressCompanyDao;

    /**
     * 查询物流跟踪信息（快递100实时查询接口）
     * @param orderSn 订单编号
     * @return
     * @author fqf
     */
    @Override
    public Map<String,Object> getLogisticsInfoRealTime(String orderSn){
        //通过orderSn查询订单信息
        OrderEntity order = orderDao.findBySn(orderSn);
        //查询的物流的信息
        List<Map<String, Object>> expressList = null;
        if(order ==null){
            return null;
        }
        //查询物流表的信息
        OrderShippingEntity shipping = orderShippingDao.findByOrderId(order.getId());
        if(shipping==null){
            return null;
        }
        //返回的结果的map
        Map<String, Object> map = new HashMap<String, Object>();
        //获取快递单号
        String expressNu = shipping.getDeliveryNumber();
        //获取公司编号
        String comNu = shipping.getExCmpNo();
        //获取快递公司的信息
        ExpressCompanyEntity expressCompany = expressCompanyDao.findByComNo(comNu);
        //查询快递公司的名称
        String comName = "";
        if(expressCompany!=null){
            comName = expressCompany.getComName();
        }
        map.put("orderStatus",order.getStatus());
        map.put("comName",comName);
        map.put("expressNu",expressNu);
        String comCode = this.getExpressComNum(expressNu);
        String param ="{\"com\":\""+comCode+"\",\"num\":\""+expressNu+"\",\"from\":\"\",\"to\":\"\"}";
        //快递100分配的公司账户
        String customer = LogisticsConfig.customer;
        //快递100分配的key值
        String key = LogisticsConfig.key;
        //需要传递的签名
        String sign = MD5Util.toMD5(param+key+customer).toUpperCase();
        try {
            URL postUrl = new URL("http://poll.kuaidi100.com/poll/query.do");
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 默认是 GET方式
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
            String content = "customer=" + URLEncoder.encode(customer, "UTF-8")
                    + "&sign=" + URLEncoder.encode(sign, "UTF-8")
                    + "&param=" + param;
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
            out.writeBytes(content);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            //获取数据
            String res =  reader.readLine();
            if(StringUtil.isNullOrEmpty(res)){
                reader.close();
                connection.disconnect();
                return map;
            }
            JSONObject object = JSONObject.fromObject(res);
            String message = object.get("message").toString();
            //如果message不为OK则调用数据不成功
            if(!message.equals("ok")){
                reader.close();
                connection.disconnect();
                return map;
            }
            String data = object.get("data").toString();
            ObjectMapper objectMapper = new ObjectMapper();
            expressList = objectMapper.readValue(data, List.class);
            map.put("expressList",expressList);
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询订单所属的快递公司编号
     * @param expressNu 快递单号
     * @return
     * @author fqf
     */
    @Override
    public String getExpressComNum(String expressNu){
        //快递100给定的key值
        String key = LogisticsConfig.key;
        //快递公司编号
        String comCode = "";
        try {
            URL postUrl = new URL("http://www.kuaidi100.com/autonumber/auto");
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // 默认是 GET方式
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
            String content = "num=" + URLEncoder.encode(expressNu, "UTF-8");
                   content +="&key="+URLEncoder.encode(key, "UTF-8");;
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
            out.writeBytes(content);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //获取转过来的值
            String res =  reader.readLine();
            if(!StringUtil.isNullOrEmpty(res)){
                JSONArray array = JSONArray.fromObject(res);
                //去第一个的快递公司编号
                comCode =  array.getJSONObject(0).get("comCode").toString();
            }
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return comCode;
    }

    /**
     * 快递100信息订阅接口
     * @author fqf
     */
    @Override
    public void expressInfoPush(){
        //查询所有的已发货订单待收货的订单
        Map params = new HashMap();
        params.put("status",2);
        PageBounds pageBounds=new PageBounds(0,20);
        List<OrderEntity> orderList = orderDao.findOrderList(params,pageBounds);
        if(orderList!=null&&orderList.size()>0){
            for(OrderEntity order : orderList){
                if (order!=null){
                    //查询物流表的信息
                    OrderShippingEntity shipping = orderShippingDao.findByOrderId(order.getId());
                    //获取快递单号
                    String expressNu = "";
                    if(shipping!=null){
                        expressNu = shipping.getDeliveryNumber();
                    }
                    //返回的结果的map
                    Map<String, Object> map = new HashMap<String, Object>();
                    //快递100给定的key值
                    String key = LogisticsConfig.key;
                    //获取快递公司的信息
                    String comCode = this.getExpressComNum(expressNu);
                    //输出的格式
                    String schema = "json";
                    HashMap param = new HashMap();
                    param.put("company",comCode);
                    param.put("number",expressNu);
                    param.put("key",key);
                    HashMap parameters = new HashMap();
                    parameters.put("callbackurl", LogisticsConfig.back_url);
                    //将parameters转化成json
                    JSONObject parametersJson = JSONObject.fromObject(parameters);
                    param.put("parameters",parametersJson);
                    //将param转成json
                    JSONObject paramJson = JSONObject.fromObject(param);
                    String paramStr = paramJson.toString();
                    try {
                        URL postUrl = new URL("http://poll.kuaidi100.com/poll");
                        // 打开连接
                        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
                        // 设置是否向connection输出，因为这个是post请求，参数要放在
                        // http正文内，因此需要设为true
                        connection.setDoOutput(true);
                        // Read from the connection. Default is true.
                        connection.setDoInput(true);
                        // 默认是 GET方式
                        connection.setRequestMethod("POST");
                        // Post 请求不能使用缓存
                        connection.setUseCaches(false);
                        connection.setInstanceFollowRedirects(true);
                        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
                        // 进行编码
                        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
                        // 要注意的是connection.getOutputStream会隐含的进行connect。
                        connection.connect();
                        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
                        String content = "schema=" + URLEncoder.encode(schema, "UTF-8")
                                       + "&param="+paramStr;
                        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
                        out.writeBytes(content);
                        out.flush();
                        out.close();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                        //获取转过来的值
                        String res =  reader.readLine();
                        if(!StringUtil.isNullOrEmpty(res)){
                            JSONObject object = JSONObject.fromObject(res);
                            String returnCode = object.get("returnCode").toString();
                            String result = object.get("result").toString();
                            String message = object.get("message").toString();
                            //返回数据的类型
                            int type = 99;
                            if(schema.equals("json")){
                                type = 0;
                            }else if(schema.equals("xml")){
                                type = 1;
                            }
                            //保存快递订阅日志信息
                            ExpressSubscription sub = new ExpressSubscription();
                            sub.setOrderSn(order.getSn());
                            sub.setRequestData("http://poll.kuaidi100.com/poll?"+content);
                            sub.setExpressNu(expressNu);
                            sub.setResult(result);
                            sub.setReturnCode(returnCode);
                            sub.setMessage(message);
                            sub.setData(object.toString());
                            sub.setDataType(type);
                            sub.setCreateDate(new Date());
                            //保存信息
                            expressSubscriptionDao.insert(sub);
                        }
                        reader.close();
                        connection.disconnect();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 快递100信息推送回调方法（保存返回的快递信息）
     * @param param 快递返回的数据
     * @author fqf
     */
    @Override
    public void getExpressPushInfo(String param){
        if(!StringUtil.isNullOrEmpty(param)){
            try{
                //将获取的数据转成json取值
                JSONObject paramJson = JSONObject.fromObject(param);
                //监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。
                //其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时”status= abort
                String status = "";
                //贵司提交的原始的快递公司编码
                String comOld = "";
                //我司纠正后的新的快递公司编码
                String comNew = "";
                //物流的信息
                String lastResult = "";
                //当前的签收状态
                //包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回等状态
                String state = "";
                //快递单明细状态标记，若state=0,则condition如下值代表如下状态：
                //condition=CU001 等待清关condition=CU002 清关中condition=CU003 已清关condition=CU004 清关异常
                String condition = "";
                //快递公司编号
                String com = "";
                //快递单号
                String nu = "";
                //是否签收
                String ischeck = "";
                //物流的详细信息
                String data = "";
                String message = "";
                if(paramJson.get("status")!=null&&paramJson.get("status")!=""){
                    status = paramJson.get("status").toString();
                }
                if(paramJson.get("comOld")!=null&&paramJson.get("comOld")!=""){
                    comOld = paramJson.get("comOld").toString();
                }
                if(paramJson.get("comNew")!=null&&paramJson.get("comNew")!=""){
                    comNew = paramJson.get("comNew").toString();
                }
                if(paramJson.get("lastResult")!=null&&paramJson.get("lastResult")!=""){
                    lastResult = paramJson.get("lastResult").toString();
                    //获取物流的信息
                    JSONObject lastResultJson = JSONObject.fromObject(lastResult);
                    message = lastResultJson.get("message").toString();
                    if(message.equals("ok")){
                        if(lastResultJson.get("state")!=null&&lastResultJson.get("state")!=""){
                            state = lastResultJson.get("state").toString();
                        }
                        if(lastResultJson.get("condition")!=null&&lastResultJson.get("condition")!=""){
                            condition = lastResultJson.get("condition").toString();
                        }
                        if(lastResultJson.get("com")!=null&&lastResultJson.get("com")!=""){
                            com = lastResultJson.get("com").toString();
                        }
                        if(lastResultJson.get("ischeck")!=null&&lastResultJson.get("ischeck")!=""){
                            ischeck = lastResultJson.get("ischeck").toString();
                        }
                        if(lastResultJson.get("nu")!=null&&lastResultJson.get("nu")!=""){
                            nu = lastResultJson.get("nu").toString();
                        }
                        if(lastResultJson.get("data")!=null&&lastResultJson.get("data")!=""){
                            data = lastResultJson.get("data").toString();
                        }
                    }
                }
                //查询订单的信息
                OrderShippingEntity shipping = orderShippingDao.findByDeliveryNumber(nu);
                //保存物流的信息
                LogisticsInformation logisticsInformation = new LogisticsInformation();
                if(shipping!=null){
                    logisticsInformation.setOrderId(shipping.getOrderId());
                }
                logisticsInformation.setCreateDate(new Date());
                logisticsInformation.setExpressNu(nu);
                logisticsInformation.setComOld(comOld);
                logisticsInformation.setComNew(comNew);
                logisticsInformation.setComCode(com);
                logisticsInformation.setMessage(message);
                logisticsInformation.setState(state);
                logisticsInformation.setStateCondition(condition);
                logisticsInformation.setIsCheck(ischeck);
                logisticsInformation.setExpressData(data);
                logisticsInformation.setCurrentState(status);
                //保存信息
                logisticsInformationDao.insert(logisticsInformation);
            }catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询保存的最新的物流信息
     * @param orderSn
     * @return
     * @author fqf
     */
    @Override
    public Map<String,Object> getLogisticsInfo(String orderSn){
        //查询订单的信息
        //List<ZxOrder> orderList = zxOrderMapper.findErrorOrder(OrderEnum.orderType.TWO.getIndex());
        OrderEntity order = orderDao.findBySn(orderSn);
        if(order==null){
            return null;
        }
        //查询最近时间的一条记录
        LogisticsInformation logisticsInformation = logisticsInformationDao.selectByOrderId(order.getId());
        if(logisticsInformation==null){
            return null;
        }
        //返回的结果的map
        Map<String, Object> map = new HashMap<String, Object>();
        //查询的物流的信息
        List<Map<String, Object>> expressList = null;
        //获取快递公司的信息
        ExpressCompanyEntity expressCompany = expressCompanyDao.findByComNo(logisticsInformation.getComCode());

        //查询快递公司的名称
        String comName = "";
        if(expressCompany!=null){
            comName = expressCompany.getComName();
        }
        map.put("orderStatus",order.getStatus());
        map.put("comName",comName);
        map.put("expressNu",logisticsInformation.getExpressNu());
        //物流的信息
        String data = logisticsInformation.getExpressData();
        JSONArray a = JSONArray.fromObject(data);
        expressList = JSONArray.toList( JSONArray.fromObject(data),new HashMap(),new JsonConfig());
        map.put("expressList",expressList);
        return map;
    }

}
