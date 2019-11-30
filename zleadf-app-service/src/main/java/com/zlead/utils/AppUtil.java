package com.zlead.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zlead.dao.AclUserDao;
import com.zlead.dao.MemberDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.constant.ModuCodeConstant;
import com.zlead.entity.dto.AclModuUserDto;
import com.zlead.entity.vo.RoleRightsVo;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.BaseWarehouse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Component
public class AppUtil {

    @Autowired
    private AclUserDao aclUserDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private AgentFacMapper agentFacMapper;

    public static AppUtil appUtil;

    @Autowired
    public static LoginUtil loginUtil;
    /**
     * 验签成功
     */
    public static final int SIGN_OK = 4;
    /**
     * 非法请求
     */
    public static final int SIGN_ILLEGAL = 5;
    /**
     * 超时请求
     */
    public static final int SIGN_TIMEOUT = 6;


    @PostConstruct
    public void init() {
        appUtil = this;
    }

    public static String wordToHtml(String urlString) throws Exception {
        if (urlString != null) {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3 * 60 * 1000);
            StringBuilder content = new StringBuilder();
            InputStream input = conn.getInputStream();
            if (urlString.endsWith(".doc")) { // doc为后缀的
                WordExtractor doc = new WordExtractor(input);
                content.append(doc.getText());
            } else if (urlString.endsWith(".docx")) {
                XWPFWordExtractor docx = new XWPFWordExtractor(new XWPFDocument(input));
                content.append(docx.getText());
            }
            input.close();
            return content.toString();
        }
        return "";
    }

    private static List<AclUserEntity> getChildNode(AclUserEntity aclUserEntity, List<AclUserEntity> acceptResultList) {
        if (acceptResultList == null) {
            acceptResultList = new ArrayList<>();
        }
        //递归查询出当前用户下的下级用户
        List<AclUserEntity> childList = appUtil.aclUserDao.findUnitsById(aclUserEntity.getUserId());
        if (childList != null && childList.size() > 0) {
            acceptResultList.addAll(childList);
            for (AclUserEntity userEntity : childList) {
                getChildNode(userEntity, acceptResultList);
            }
        }
        return acceptResultList;
    }

    public static List<AclUserEntity> getOwnAndChild(AclUserEntity aclUserEntity) {
        List<AclUserEntity> resultList = new ArrayList<>();
        resultList.add(aclUserEntity);
        List childList = getChildNode(aclUserEntity, null);
        resultList.addAll(childList);
        return resultList;
    }

    public static List<MemberEntity> getChildMember(AclUserEntity aclUserEntity) {
        List<AclUserEntity> aclUserEntities = getOwnAndChild(aclUserEntity);
        List<MemberEntity> list = new ArrayList<>();
        if (aclUserEntities != null && aclUserEntities.size() > 0) {
            for (AclUserEntity aclUser : aclUserEntities) {
                MemberEntity member = appUtil.memberDao.findMemberByUserId(aclUser.getUserId());
                if (member != null) {
                    list.add(member);
                }
            }
        }
        return list;
    }

    /**
     * 保留四位小数，四舍五入的方法
     *
     * @param d
     * @return
     */
    public static double formatDouble(double d) {
        return (double) Math.round(d * 10000) / 10000;
    }

    /**
     * 获取当前登录用户及其直属/从属下级userId
     *
     * @param memberId
     * @return
     */
    public static Set<Integer> getNextLevelUserIds(Long memberId) {
        Set<Integer> userIds = new HashSet<>();
        AclUserEntity aclUser = appUtil.aclUserDao.findUserByMemberId(memberId);
        List<AclUserEntity> aclUsers = AppUtil.getOwnAndChild(aclUser);
        aclUsers.forEach(user -> {
            userIds.add(user.getUserId());
        });
        return userIds;
    }

    public static List<Long> getMemberIds(List<MemberEntity> memberEntities) {
        List<Long> memberIds = new ArrayList<>();
        for (MemberEntity member : memberEntities) {
            memberIds.add(member.getId());
        }
        return memberIds;
    }

    /**
     * 随机生成的公钥为:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnqMs3a1S6iGIpAjuGpFVu6yuoXz6KxB3GbZx28gY+hR5vNoWN7g0vXjm6OJRDsjJOOnsnEnIRPtMHZBJ8WGH1fOJp4X0Y/PmmXq6CAF+c+MwDd94zuIsgp62E9jsfabA00bA1cVGRct6M/xv3b5MhvGJGp2eMz22BU8iqjJbypQIDAQAB
     * 随机生成的私钥为:MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKeoyzdrVLqIYikCO4akVW7rK6hfPorEHcZtnHbyBj6FHm82hY3uDS9eObo4lEOyMk46eycSchE+0wdkEnxYYfV84mnhfRj8+aZeroIAX5z4zAN33jO4iyCnrYT2Ox9psDTRsDVxUZFy3oz/G/dvkyG8YkanZ4zPbYFTyKqMlvKlAgMBAAECgYAQV4+XU0rq86g9vw32TcZWDW4v9zitZfhRAxYhMe4R4f/Zb0MNVorKdzDBSgqJnrDwlxqx8EZWD/2Z1tNGc4b4sjFX+epm3OUH8699i9IpcdGoixlmh+JH3tTnD6wJMoKeSJ9uYLpSATMVfJHHhOPApOpO8GEbBzPru+4Yd1LnAQJBAODtrIUE9ogEUFhrFrBLdrMYwFY1Xewm8tbrHMRB3sC+j0SmQuNoY1Ip8+m5NQu5TodzxP5tMC//9rsBUDnpJvECQQC+0d4XmuN8qDKRv0g8DYbGIw6uznDB8HOpdGzSJmwo3wUe1/lH1N9wfdPtgBc+DJQuj8ZSQOr1Eg1odHYoi471AkEAjFdfbEPTeryOUQzIRElhj4+i+mcj6iPHIxfhhAbBcKu1gpTmaTOJvQtWF/+qPF1rxRIT2NfR2DEyBi5kmy2BUQJBALCyyDUVkZgqbIF4Hpy1bdrmNb97nQCJ96CwFeDYAYorUqOIHOb+YVXavreLyHoFzeX77wrV4HzFlbLnPU84RE0CQEiud+QewkCWIPb/FUuVKvT7LIyaYMperRha5j7X8opRet+2yts6cJwxoPWpA+6/04R9F2X8cZ/6de5zgTHUxaE=
     */
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnqMs3a1S6iGIpAjuGpFVu6yuoXz6KxB3GbZx28gY+hR5vNoWN7g0vXjm6OJRDsjJOOnsnEnIRPtMHZBJ8WGH1fOJp4X0Y/PmmXq6CAF+c+MwDd94zuIsgp62E9jsfabA00bA1cVGRct6M/xv3b5MhvGJGp2eMz22BU8iqjJbypQIDAQAB";

    public static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKeoyzdrVLqIYikCO4akVW7rK6hfPorEHcZtnHbyBj6FHm82hY3uDS9eObo4lEOyMk46eycSchE+0wdkEnxYYfV84mnhfRj8+aZeroIAX5z4zAN33jO4iyCnrYT2Ox9psDTRsDVxUZFy3oz/G/dvkyG8YkanZ4zPbYFTyKqMlvKlAgMBAAECgYAQV4+XU0rq86g9vw32TcZWDW4v9zitZfhRAxYhMe4R4f/Zb0MNVorKdzDBSgqJnrDwlxqx8EZWD/2Z1tNGc4b4sjFX+epm3OUH8699i9IpcdGoixlmh+JH3tTnD6wJMoKeSJ9uYLpSATMVfJHHhOPApOpO8GEbBzPru+4Yd1LnAQJBAODtrIUE9ogEUFhrFrBLdrMYwFY1Xewm8tbrHMRB3sC+j0SmQuNoY1Ip8+m5NQu5TodzxP5tMC//9rsBUDnpJvECQQC+0d4XmuN8qDKRv0g8DYbGIw6uznDB8HOpdGzSJmwo3wUe1/lH1N9wfdPtgBc+DJQuj8ZSQOr1Eg1odHYoi471AkEAjFdfbEPTeryOUQzIRElhj4+i+mcj6iPHIxfhhAbBcKu1gpTmaTOJvQtWF/+qPF1rxRIT2NfR2DEyBi5kmy2BUQJBALCyyDUVkZgqbIF4Hpy1bdrmNb97nQCJ96CwFeDYAYorUqOIHOb+YVXavreLyHoFzeX77wrV4HzFlbLnPU84RE0CQEiud+QewkCWIPb/FUuVKvT7LIyaYMperRha5j7X8opRet+2yts6cJwxoPWpA+6/04R9F2X8cZ/6de5zgTHUxaE=";


    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    private static String base64Decode(String base64Str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(base64Str);
        String decode = new String(bytes, "UTF-8");
        return decode;
    }

    /**
     * App创建订单和上传凭证验签流程
     *
     * @param request
     * @return 1、验签成功；2、非法请求；3、超时接口失效
     * @throws IOException
     */
    public static int verifySign(HttpServletRequest request) throws IOException {
        String authInfo = request.getHeader("authInfo");
        if (StringUtils.isBlank(authInfo)) {
            return SIGN_ILLEGAL;
        }
        String json = base64Decode(authInfo);
        JSONObject jsonObject = JSON.parseObject(json);
        String token = request.getHeader("Authorization");
        String timeStamp = jsonObject.getString("timeStamp");
        String nonce = jsonObject.getString("nonce");
        String appSecret = "qzed+suj4+hzudl9zrqw9q==";
        String signature = jsonObject.getString("signature");
        if (StringUtils.isBlank(token) || StringUtils.isBlank(timeStamp) || StringUtils.isBlank(nonce) || StringUtils.isBlank(signature)) {
            //非法请求
            return SIGN_ILLEGAL;
        }
        if (signatureTimeout(timeStamp)) {
            //超时接口失效
            return SIGN_TIMEOUT;
        }
        String newSignature = toHexStr(MD5Util.toMD5(timeStamp + nonce + token + appSecret).toUpperCase());
        if (signature.equals(newSignature)) {
            //验签成功
            return SIGN_OK;
        } else {
            //非法请求
            return SIGN_ILLEGAL;
        }
    }

    private static boolean signatureTimeout(String time) {
        Long timeStamp = Long.parseLong(time);
        Long nowStamp = System.currentTimeMillis();
        Long delta = nowStamp - timeStamp;
        return delta > 60 * 1000L;
    }


    /**
     * 判断是否具有功能权限
     *
     * @param aclModuUserDtoList
     * @return
     */
    public static RoleRightsVo getModuleUser(List<AclModuUserDto> aclModuUserDtoList, List<BaseWarehouse> warehouseList, Integer userId) {
        RoleRightsVo roleRightsVo = new RoleRightsVo();
        if (CollectionUtils.isNotEmpty(aclModuUserDtoList)) {
            for(AclModuUserDto aclModuUserDto:aclModuUserDtoList) {
                if(ModuCodeConstant.OAMACKETCONT.equals(aclModuUserDto.getModuCode())) {
                    roleRightsVo.setActivityList(1);
                }
                if(ModuCodeConstant.PRDINVENTMAS.equals(aclModuUserDto.getModuCode())) {
                    roleRightsVo.setStockList(1);
                }
            }

        }
        return roleRightsVo;
    }

    private static Set<Integer> findSubUserByUser(Integer userId) {
        List<AclUserEntity> list = appUtil.aclUserDao.findAllUsers();
        Set<Integer> ids = new HashSet<>();
        ids.add(userId);
        if (list == null || list.size() == 0) {
            return ids;
        }
        addSubId(ids, list, userId);
        return ids;
    }

    private static void addSubId(Set<Integer> userIds, List<AclUserEntity> aclUsers, Integer pUserId) {
        for (AclUserEntity item : aclUsers) {
            if (pUserId.equals(item.getUint1())) {
                userIds.add(item.getUserId());
                addSubId(userIds, aclUsers, item.getUserId());
            }
        }
    }

    public static Set<Integer> findSubUserByMember(Long memberId) {
        AclUserEntity aclUserEntity = appUtil.aclUserDao.findUserByMemberId(memberId);
        Set<Integer> userIds = findSubUserByUser(aclUserEntity.getUserId());
        return userIds;
    }


    public static Set<Integer> findSubAgentByMember(Long memberId){
        Set<Integer> userIds = findSubUserByMember(memberId);
        List<AgentFac> agentFacs = appUtil.agentFacMapper.findByShopIdByUserIds(memberId,userIds);
        Set<Integer> agentIds = new HashSet<>();
        for(AgentFac agentFac:agentFacs){
            if(agentFac!=null){
                agentIds.add(agentFac.getAgentId());
            }
        }
        return agentIds;
    }
    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 字符串转换成为16进制(无需Unicode编码)
     *
     * @param str
     * @return
     */
    private static String toHexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 判断是否为库管员
     * @param warehouseList
     * @param userId
     * @return
     */
    private static boolean isWareHouseMgr( List<BaseWarehouse> warehouseList,Integer userId){
        if (CollectionUtils.isNotEmpty(warehouseList)) {
            for (BaseWarehouse baseWarehouse : warehouseList) {
                //判断登录用户是否为库管员
                if (baseWarehouse.getMgrUserid().contains(userId.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 查询登录用户是否有角色或是否已被停用状态
     * @param aclUserEntity
     * @return
     */
    public static boolean isRoleAndStateOff(AclUserEntity aclUserEntity){
        AclUserEntity aclUser = appUtil.aclUserDao.findUserOffOrNot(aclUserEntity.getUserId());
        return aclUser!=null;
    }


    /**
     * 判断是否为非Emoji字符
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isNotEmojiCharacter(codePoint)) {
                //判断到了这里表明，确认有表情字符
                return true;
            }
        }
        return false;
    }
}
