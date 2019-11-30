package com.zlead.util;

import java.io.Serializable;

public class JsonResult implements Serializable {
	private Integer code;
	private String message;
	private Object data;
	private boolean success;

	public JsonResult() {
		super();
		this.data = data != null ? data :"" ;
	}
	
	
	public JsonResult(Integer code, String message, boolean success) {
		this();
		this.code = code;
		this.message = message;
		this.success = success;
	}


	public JsonResult(Integer code, String message, Object data, boolean success) {
		this();
		this.code = code;
		this.message = message;
		this.data = data;
		this.success = success;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	
	
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", message=" + message + ", data="
				+ data + ", success=" + success + "]";
	}
	
	
}
