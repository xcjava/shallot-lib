package com.gmail.xcjava.base.hql;

import java.io.Serializable;

public class QueryParam implements Serializable {

	/**
	 * 查询字段值字符串;
	 */
	String queryValue;
	/**
	 * 查询条件字符串;
	 */
	String queryCon;
	/**
	 * 查询连接符字符串;
	 */
	String queryLink;
	/**
	 * 排序参数字符串（用于列表排序，查询数量时忽略该属性）
	 */
	String orderParamStr;
	/**
	 * 自定义排序参数字符串（用于列表排序，查询数量时忽略该属性）
	 */
	String customOrderParamStr;
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public String getQueryCon() {
		return queryCon;
	}
	public void setQueryCon(String queryCon) {
		this.queryCon = queryCon;
	}
	public String getQueryLink() {
		return queryLink;
	}
	public void setQueryLink(String queryLink) {
		this.queryLink = queryLink;
	}
	public String getOrderParamStr() {
		return orderParamStr;
	}
	public void setOrderParamStr(String orderParamStr) {
		this.orderParamStr = orderParamStr;
	}
	public String getCustomOrderParamStr() {
		return customOrderParamStr;
	}
	public void setCustomOrderParamStr(String customOrderParamStr) {
		this.customOrderParamStr = customOrderParamStr;
	}
}
