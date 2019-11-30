package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 3102快递公司表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-14 10:14:02
 */
public class ExpressCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
		private Long expressCompanyId;
	/**
	 * 
	 */
	private String comNu;
	/**
	 * 
	 */
	private String comName;
	/**
	 * 
	 */
	private String dataExplain;
	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private String countryCode;
	/**
	 * 
	 */
	private Date craeteDate;

	/**
	 * 设置：
	 */
	public void setExpressCompanyId(Long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}
	/**
	 * 获取：
	 */
	public Long getExpressCompanyId() {
		return expressCompanyId;
	}
	/**
	 * 设置：
	 */
	public void setComNu(String comNu) {
		this.comNu = comNu;
	}
	/**
	 * 获取：
	 */
	public String getComNu() {
		return comNu;
	}
	/**
	 * 设置：
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}
	/**
	 * 获取：
	 */
	public String getComName() {
		return comName;
	}
	/**
	 * 设置：
	 */
	public void setDataExplain(String dataExplain) {
		this.dataExplain = dataExplain;
	}
	/**
	 * 获取：
	 */
	public String getDataExplain() {
		return dataExplain;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * 获取：
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * 设置：
	 */
	public void setCraeteDate(Date craeteDate) {
		this.craeteDate = craeteDate;
	}
	/**
	 * 获取：
	 */
	public Date getCraeteDate() {
		return craeteDate;
	}
}
