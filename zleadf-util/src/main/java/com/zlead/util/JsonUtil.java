package com.zlead.util;

import static com.zlead.util.ObjectUtils.isNotEmpty;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.xml.XMLSerializer;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;


public class JsonUtil {
	
	private static JsonConfig config = new JsonConfig();
	
	/**
	 * 解读json串
	 * @param jsonStr
	 * @param ifCharset 是否设置字符集  0为不设置 大于0为设置   全英文不需要设置
	 * @return	
	 * 读取Map中值  例：String accessToken = map.get("access_token").toString();
	 */
	public static HashMap readJsonToMap(String jsonStr,int ifCharset){
		JSONObject object = null;
		try {
			if(ifCharset>0){
				jsonStr = new String(jsonStr.getBytes("ISO-8859-1"),"utf-8");
			}
			object = JSONObject.fromObject(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(object!=null && object.keySet()!=null){
		Iterator iter = object.keySet().iterator();
		HashMap map = new HashMap();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = object.getString(key);
			map.put(key, value);
		}
		return map;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 解读json串
	 * @param jsonStr
	 * @param ifCharset 是否设置字符集  0为不设置 大于0为设置   全英文不需要设置
	 * @return	
	 * 读取Map中值  例：String accessToken = map.get("access_token").toString();
	 */
	public static Map readJson(String jsonStr,int ifCharset){
		ObjectMapper mapper = new ObjectMapper();
		Map map = null;
		try {
			if(ifCharset>0){
				jsonStr = new String(jsonStr.getBytes("ISO-8859-1"),"utf-8");
			}
			map = mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			map = null;
			e.printStackTrace();
		}
		return map;
	}
	
	
	public static String getJson(Object object){
		if(object == null)
			return null;
		JSONObject json = JSONObject.fromObject(object);
		return json.toString();
	}
	
	public static String getJsonFromArray(Object object){
		if(object == null)
			return null;
		JSONArray json = JSONArray.fromObject(object);
		return json.toString();
	}
	
	/**
	 * 向前台抛出json数据的时候可以用此方法  
	 * @param object	Array数组  List
	 * @return
	 */
	public static String getJsonFormatDateFromArray(Object object){
		if(object == null)
			return "";
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd'T'HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		
		return jsonArray.toString();
	}
	
	/**
	 * 向前台抛出json数据的时候可以使用此方法
	 * @param object	单个实体类	Map HashMap Entity
	 * @return
	 */
	public static String getJsonFormatDateFromObject(Object object){
		if(object == null)
			return "";
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd'T'HH:mm:ss"));
		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		
		return jsonObject.toString();
	}
	
	/**
	 * 解读miniui传入后台的实体类list格式的json串
	 * @param json
	 * @param entityClass
	 * @return
	 */
	public static List readJsonFormatDateToListByEntityClass(String json,Class entityClass){
		
//		if(StringUtil.isNullOrEmpty(json))
//			return null;
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		
		JSONArray jsonArray= JSONArray.fromObject(json,jsonConfig);
		return JSONArray.toList(jsonArray, entityClass);
	}
	
	public static Object readJsonToObjectByEntityClass(String json,Class entityClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return JSONObject.toBean(jsonObject,entityClass);
	}
	
	/**
	 * 直接输出内容的简便函数.
	 * 
	 */
	public static void render(HttpServletResponse response,final String content) {
		try {
			// 分析headers参数
			String encoding = "UTF-8";
			boolean noCache = true;
			// 设置headers参数
			String fullContentType = "application/json;charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将Map对象,将被转化为json字符串.
	 * @param response
	 * @param map
	 * @param headers
	 */
	@SuppressWarnings("unchecked")
	public static void renderJson(HttpServletResponse response, final Map map,
			final String... headers) {
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		String jsonString = JSONObject.fromObject(map, config).toString();
		renderJson(response, jsonString);
	}
	
	/**
	 * 直接输出JSON格式
	 * 
	 * @param string
	 *            json字符串.
	 */
	public static void renderJson(HttpServletResponse response,
			final String string) {
		render(response, string);
	}
	
	 /**
     * JSON(数组)字符串<STRONG>转换</STRONG>成XML字符串
     * 
     * @param jsonString
     * @return
     */
    public static String json2xml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        // return xmlSerializer.write(JSONArray.fromObject(jsonString));//这种方式只支持JSON数组
    }
	
    /**  
     * Map转换成Xml  
     * @param map  
     * @return  
     */  
    public static String map2Xmlstring(Map<String,Object> map){  
        StringBuffer sb = new StringBuffer("");  
        sb.append("<xml>");  
          
        Set<String> set = map.keySet();  
        for(Iterator<String> it=set.iterator(); it.hasNext();){  
            String key = it.next();  
            Object value = map.get(key);  
            sb.append("<").append(key).append(">");  
            sb.append(value);  
            sb.append("</").append(key).append(">");  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    } 
    
	/**
	 * 根据JsonResult数据，返回json数据
	 * @param code
	 * @param message
	 * @param b
	 * @param response
	 */
	public static void renderJsonResult(HttpServletResponse response,final Integer code,final String message,final boolean b){
		JsonResult result = new JsonResult(code, message, b);
		String content = getJson(result);
		render(response, content);
	}
	
	public static void renderJsonResultTwo(HttpServletResponse response,final Integer code,final String message,Object data,final boolean b){
		JsonResult result = new JsonResult(code, message, data, b);
		String content = getJson(result);
		System.out.println(content);
		render(response, content);
	}
	
	public static JsonResult getJsonResult(final Integer code,final String message,final boolean b){
		JsonResult result = new JsonResult(code, message, b);
		return result;
	}
	
	  /**
     * object对象转换为 json格式字符串
     *
     * @param object
     * @return
     * @throws IOException
     */
    @SuppressWarnings({"unchecked"})
    public static String object2Json(Object object) throws IOException {
        if (object == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, object);
        gen.close();
        return sw.toString();
    }

    /**
     * json格式字符串转换为object对象
     *
     * @param json
     * @param cls
     * @return
     * @throws IOException
     */
    @SuppressWarnings({"unchecked"})
    public static Object json2Object(String json, Class cls) throws IOException {
        if (!isNotEmpty(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, cls);
    }

	
}
