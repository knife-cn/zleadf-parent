package com.zlead.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zleadf-parent
 * @description:短信  勿动
 * @author: ytchen
 * @create: 2019-02-14 18:11
 **/

public class SendTelMsgUtils {

	/**
	 * ACCOUNT_SID:
	 */
//	public static final String ACCOUNT_SID = "";


	/**
	 * AUTH_TOKEN:
	 */
//	 public static final String AUTH_TOKEN = "";
	
	/**
	 * BASE_URL:请求地址
	 * B端验证
	 */
	public static final String BASE_URL = "http://preapi.wujinyunshang.com/api/FactoryCommon/SendSms";
	/**
	 * 验签测试url
	 */
//	public static final String BASE_URL = "http://192.168.1.10/ZENSURE.EHandWare.WebApi/api/FactoryCommon/SendSms";

	/**
	 * RESP_DATA_TYPE:数据返回格式为JSON格式
	 */
	public static final String RESP_DATA_TYPE = "json";

	/**
	 * 
	 * @Title：sendMsgTo
	 * @Description：发送短信验证码
	 * @return：String
	 */
	public static String sendMsgMobile(String Mobile, String createRandNum) {

		/**
		 * randNum:生成的六位验证码随机数
		 */
		String randNum = createRandNum;

		/** 不要动模板
		 * Msg:短信内容(短信签名+短信内容)
		 */
		String Msg = "【直链网】您的验证码是" +randNum+ "，10分钟内有效。如非本人操作，请忽略本短信";

		String Type="100";



		String http_post = "Mobile=" + Mobile + "&Type=" + Type + "&Msg="
				+ Msg;
        Map<String, String> map = new HashMap<String, String>();
        map.put("Mobile",Mobile);
        map.put("Type",Type);
        map.put("Msg",Msg);
        JSONObject com =BClientHttp.post(BASE_URL,map);


		return com.toString();
	}

	/**
	 * 获取时间戳
	 */
	public static String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		return date;
	}

	/**
	 * 对"ACCOUNT_SID + AUTH_TOKEN + timestamp"进行MD5加密
	 * 
	 *                            <dependency>
	 *                            <groupId>commons-codec</groupId>
	 *                            <artifactId>commons-codec</artifactId>
	 *                            <version>1.10</version> </dependency>
	 *                            </dependencies>
	 */
	public static String sig_MD5(String str) {
		String sig_md5 = DigestUtils.md5Hex(str);
		return sig_md5;
	}

}