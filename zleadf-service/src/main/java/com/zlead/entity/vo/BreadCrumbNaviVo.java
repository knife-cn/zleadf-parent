package com.zlead.entity.vo;

import java.io.Serializable;

/**
 * @Author hyde
 * @Email zhangyayun@fuljoy.com
 * @Date 2019/3/24 16:18
 * @Description ...
 */
public class BreadCrumbNaviVo  implements Serializable{

	 /**
	 *  节点url*
	 */
	 private String nodeUrl;

	 /**
	 *节点名称
	 */
    private String nodeName;

	/**
	 * 前端显示导航 判断节点的标志
	 * "0" 收藏; "1" 搜索; "2" 活动详情; "3" 购物车;"4" 我的订单 ;"5" 商城
	 */
	private String flag;

    private int cateIndex;//现在的实现方式没有用到


	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getCateIndex() {
		return cateIndex;
	}

	public void setCateIndex(int cateIndex) {
		this.cateIndex = cateIndex;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
