package com.zlead.utils;
import com.alibaba.fastjson.JSONObject;
import com.zlead.exception.ParameterNotFoundException;
import com.zlead.util.ObjectUtils;
import com.zlead.util.ProjectProperties;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 终端签名工具
 */
public class ClientSignature {

    /**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o) throws IllegalAccessException {
        String result = getStrSign(o);
        result += "secretKey=" + ProjectProperties.secretKey;
        result = MD5Util.interfaceToMD5(result);
        result = new sun.misc.BASE64Encoder().encode(result.getBytes());
        return result;
    }

    private static String getStrSign(Object o) throws IllegalAccessException{
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            String v = f.get(o) == null ? "" : f.get(o).toString();
            String n = f.getName();
            list.add(n + "=" + v + "&");
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        return sb.toString();
    }

    /**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @param secretKey 终端秘钥
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o,String secretKey) throws IllegalAccessException {
        String result = getStrSign(o);
        result += "secretKey=" + secretKey;
        result = MD5Util.interfaceToMD5(result);
        result = new sun.misc.BASE64Encoder().encode(result.getBytes());
        return result;
    }

    public static String getSign(Map<String,Object> map){
        String result = getStrSign(map);
        result += "secretKey=" + ProjectProperties.secretKey;
        result = MD5Util.interfaceToMD5(result);
        result = new sun.misc.BASE64Encoder().encode(result.getBytes());
        return result;
    }

    private static String getStrSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            String v = entry.getValue() == null ? "" : entry.getValue().toString();
            String k = entry.getKey();
            list.add(k + "=" + v + "&");
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        return sb.toString();
    }

    public static String getSign(Map<String,Object> map,String secretKey){
        String result = getStrSign(map);
        result += "secretKey=" + secretKey;
        result = MD5Util.interfaceToMD5(result);
        result = new sun.misc.BASE64Encoder().encode(result.getBytes());
        return result;
    }

    /**
     * 验证签名
     * @param dataJson 要签名的数据
     * @return
     */
    public static boolean validateSign(String dataJson){
        boolean flag = false;
        try {
            String sign = JSONObject.parseObject(dataJson).getString("sign"); //请求的签名
            String secretId = JSONObject.parseObject(dataJson).getString("secretId"); //请求方id
            String nonce = JSONObject.parseObject(dataJson).getString("nonce"); //随机正整数
            String timeStamp = JSONObject.parseObject(dataJson).getString("timeStamp"); //请求时间戳\
            if(!ClientSignature.validParamsNotNull(sign,secretId,nonce,timeStamp)){
                return flag;
            }
            String data = JSONObject.parseObject(dataJson).getString("data"); //请求的数据
            Map<String,Object> dataMap = (Map<String,Object>)JSONObject.parse(data);
            Map<String,Object> signData = new HashMap<String,Object>(); //要进行签名的数据
            signData.put("secretId",secretId);
            signData.put("nonce",nonce);
            signData.put("timeStamp", timeStamp);
            signData.putAll(dataMap);
            String locationSign = "";
            String secretKey = "";
            if(ProjectProperties.secretId.equals(secretId)){
                secretKey = ProjectProperties.secretKey;
            }
            locationSign = ClientSignature.getSign(signData,secretKey);
            if(ObjectUtils.isNotEmpty(sign) && sign.equals(locationSign)){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * base64转码
     * @param result
     * @return
     */
    public static String getBase64Encode(String result){
        String str=null;
        try {
            str = new sun.misc.BASE64Encoder().encode(result.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * base64解码
     * @param result
     * @return
     */
    public static String getBase64Decoder(String result){
        if (result == null){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(result);
            return new String(b,"UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * url转码
     * @param value
     * @return
     */
    public static String urlEncode(String value) {
        try {
            return java.net.URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * url解码
     * @param value
     * @return
     */
    public static String urlDecode(String value) {
        try {
            return java.net.URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 随机字符
     * @return
     */
    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 时间戳
     * @return
     */
    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 随机正整数
     * @return
     */
    public static int create_nonce(){
        int i = 0;
        Random r = new Random();
        i = r.nextInt(10000);
        return i;
    }

    /**
     * 验证参数不为空
     * @param params
     * @throws ParameterNotFoundException
     */
    public static boolean validParamsNotNull(Object... params) throws ParameterNotFoundException {
        boolean flag = true;
        if (params == null || params.length < 1){
            return flag;
        }
        for (Object o:params) {
            if (o == null){
                flag = false;
                break;
            }
            if (o instanceof String){
                if(StringUtils.isBlank((String) o)){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }



    public static void main(String[] a){
        String json = "{\n" +
                "    \"secretId\": \"AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA\",\n" +
                "    \"nonce\": \"345122\",\n" +
                "    \"timeStamp\": \"154343232\",\n" +
                "    \"sign\": \"HgIYOPcx5lN6gz8JsCFBNAWp2oQ=\",\n" +
                "    \"data\": {\n" +
                "        \"userId \": \"123\",\n" +
                "        \"name \": \"test\",\n" +
                "        \"sex\": \"1\",\n" +
                "        \"list\": [\n" +
                "            {\n" +
                "                \"dd\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"dd\": 2\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        boolean flag = ClientSignature.validateSign(json);
    }
}
