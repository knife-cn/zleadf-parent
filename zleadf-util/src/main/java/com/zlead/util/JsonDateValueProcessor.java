package com.zlead.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor{
	private String datePattern = "yyyy-MM-dd HH:mm:ss";// 日期格式
	
	
	public JsonDateValueProcessor() {
		super();
	}
	
	// 构造函数
	public JsonDateValueProcessor(String format) {
		super();
		this.datePattern = format;
	}
	
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}
	
	private Object process(Object value) {
		try {
			if (value instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.UK);
				return sdf.format((Date) value);
			}
			return value == null ? "" : value.toString();
		} catch (Exception e) {
		return "";
		}
	}
	
	public String getDatePattern() {
		return datePattern;
	}
	
	public void setDatePattern(String datePaterns) {
		this.datePattern = datePaterns;
	}
	
}
