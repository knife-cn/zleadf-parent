package com.zlead.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.zlead.constant.SysCnst;
import com.zlead.utils.MD5Util;
import org.apache.commons.lang.StringUtils;

import com.zlead.dto.Result;

public class ToolsUtils {
	    //判断对象不为null或空
	//  private static final boolean $isNotEmpty(Object pattern) {
	//      return Ognl.isNotEmpty(pattern);
	//  }
	
	  public static String getParam(ServletRequest request, String name,
	                                        String defval) {
	      String param = request.getParameter(name);
	      return (param != null ? param : defval);
	  }
	
	  public static Long getParam(ServletRequest request, String name,
	                                        Long defval) {
	      Long result = null;
	      String param = request.getParameter(name);
	      if(param != null){
	          result = Long.parseLong(param);
	      }else{
	          result = defval;
	      }
	      return result;
	  }
	
	  public static int getParam(ServletRequest request, String name,
	                                     int defval) {
	      String param = request.getParameter(name);
	      int value = defval;
	      if (param != null) {
	          try {
	              value = Integer.parseInt(param);
	          } catch (NumberFormatException ignore) {
	          }
	      }
	      return value;
	  }
	
	  public static Long[] $getParams(ServletRequest request, String name) {
	      String[] params = request.getParameterValues(name);
	      if (params == null) {
	          return null;
	      }
	      int j = params.length;
	      Long[] result = new Long[j];
	      for (int i = 0; i < j; i++) {
	          result[i] = Long.valueOf(params[i]);
	      }
	      return result;
	  }
	  
	  /**
	     * 获取参数Map
	     * @param req 请求
	     * @param fields 字段数组
	     * @return
	     */
	    public static Map $getParamMap(HttpServletRequest req, String[] fields) {
	        Map params = new HashMap();
	        for (String field : fields) {
	            if (StringUtils.isBlank(field)) continue;
	            String value = req.getParameter(field);
	            if (value == null) continue;
	            value = value.replaceAll("'", "").trim();
	            if (StringUtils.isBlank(value)) continue;
	            params.put(field, value);
	        }
	        return params;
	    }
	    
	 // 是否为空
	    public static boolean isBlank(String s) {
	        return StringUtils.isBlank(s);
	    }

	    // 是否不为空
	    public static boolean isNotBlank(String s) {
	        return StringUtils.isNotBlank(s);
	    }
	    
	    /**
	     * 处理异常(用于Result)
	     * @param e Exception
	     * @param result Result
	     */
	    public static void $handleResultExcp(Exception e, Result result) {
	        result.setSuccess(false);
	        String msg = e.getMessage();
	        if (SysCnst.NO_PERMISSION.equals(msg)) {
	            result.setMessage(msg);
	            return;
	        }
	        if ($hasChineseCharacter(msg)) {
	            result.setMessage(msg);
	        } else {
	            result.setMessage("系统出现异常，无法处理您的请求。");
	            e.printStackTrace();
	        }
	    }
	    
	    /** 是否包含汉字 */
	    private static boolean $hasChineseCharacter(String content) {
	        if (content == null) {
	            return false;
	        }
	        return content.getBytes().length > content.length();
	    }
	    
	  //生成八位随意的字符串
		public static String genRandomNum(){  
		     int  maxNum = 36;  
		     int i;  
		     int count = 0;  
		     char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
		       'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
		       'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
		     StringBuffer pwd = new StringBuffer("");  
		     Random r = new Random();  
		     while(count < 8){  
		    	 i = Math.abs(r.nextInt(maxNum));     
		    	 if (i >= 0 && i < str.length) {  
		    		 pwd.append(str[i]);  
		    		 count ++;  
		    	 }  
		     }  
		     return pwd.toString();  
		}
		
		/**
		 * webservice接口请求参数加密处理
		 * */
	    public static String doDataXml(String xml, String key)
	    {
	    	String str1 = xml;
	    	String str2 =  str1.trim();
	    	String str3 = str2.replaceAll("> <", "><"); 
	    	String str4 = str3+key;
	    	String str5 = MD5Util.toMD5(str4);
	    	String sign = str5.toUpperCase();
	    	return sign;

	    }

	/**
	 * 将日期格式转换为时间毫秒数 ：2019-04-03 00:00:00 -> 124589465446
	 * @param date
	 * @return
	 */
	public static long getDateToHm(String date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return simpleDateFormat.parse(date).getTime();
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
