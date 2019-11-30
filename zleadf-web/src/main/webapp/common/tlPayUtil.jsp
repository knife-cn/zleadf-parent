<%@ page import="com.up72.zx.utils.MD5Util" %>
<%@ page import="com.up72.zx.utils.Interface.RequestHttpUtil" %>
<%@ page import="org.apache.commons.httpclient.HttpException" %>
<%@ page import="org.apache.commons.httpclient.HttpClient" %>
<%@ page import="org.apache.commons.httpclient.methods.PostMethod" %>
<%@ page import="java.io.InputStream" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/function.jsp"%>
<%!
    //---------------------通联支付------------------------
        /**
         * 通联支付--用户注册接口
         * @param memberId 用户id
         * @return 返回通联会员id号  （后续升级，把该用户对应的通联会员id保存到数据库中）
         */
    public static String userReg(Long memberId){
        String userId = "";
        try {
            String merchantId = "008140873720000"; //商户号
            String partnerUserId = ""+memberId;//会员id
            String key = "1234567890";//秘钥
            String signType = "0";//签名方式，0：MD5
            String signStr = "&signType="+signType+"&merchantId="+merchantId+"&partnerUserId=" + partnerUserId+"&key="+key+"&"; //加密的源串
            System.out.println(signStr);
            String signMsg = MD5Util.toMD5(signStr).toUpperCase().trim();
            System.out.println(signMsg);
            String url = "https://cashier.allinpay.com/usercenter/merchant/UserInfo/reg.do";
            String param = "signType="+signType+"&merchantId="+merchantId+"&partnerUserId=" + partnerUserId+"&signMsg="+signMsg;
            String requestUrl = url+"?"+param;
            String resp = RequestHttpUtil.readHttpGet(requestUrl);
            userId = JSONObject.parseObject(resp).getString("userId");
        }catch (Exception e){
            e.printStackTrace();
        }
        return userId;
    }

    /**
     * post请求
     * @param url 请求地址
     * @param requestBody 参数
     * @return
     * @throws org.apache.commons.httpclient.HttpException
     * @throws IOException
     */
    public static String readHttpPost(String url,String requestBody) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.setRequestBody(requestBody);
        post.getParams().setContentCharset("UTF-8");
        // 发送http请求
        client.executeMethod(post);
        StringBuilder result = new StringBuilder();
        InputStream in = null;
        try {
            in = post.getResponseBodyAsStream();
            byte[] buff = new byte[1024];
            int flag = -1;

            while ((flag = in.read(buff)) != -1) {
                result.append(new String(buff, 0, flag, "UTF-8"));
            }
            in.close();
        } catch (IOException e) {
            throw new HttpException("post请求异常");
        } finally {
            close(in);
            post.releaseConnection();
        }
        String returnStr = result.toString();
        System.out.println("[readHttpPost]请求URL：" + url + "\n结果：" + returnStr);
        return returnStr;
    }

    private static void close(InputStream in) {
        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }
%>