package com.gmail.xcjava.base.spring;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonObject;

public class QueryParamWriter {
	
	private JsonObject valueJson;
	private JsonObject conJson;
	private JsonObject linkJson;
	
	public QueryParamWriter(){
		this.valueJson = new JsonObject();
		this.conJson = new JsonObject();
		this.linkJson = new JsonObject();
	}
	
	/**
	 * 添加单条件查询
	 * @param key			字段
	 * @param value			值
	 * @param condition		条件，如“=”、“!=”
	 * @return
	 */
	public QueryParamWriter addQueryParam(String key, Object value, QueryCondition condition) {
		
		if(StringUtils.isEmpty(key) || condition == null)
			return this;
		
		this.valueJson.addProperty(key, this.getValueStr(value));
		this.conJson.addProperty(key, condition.toString());
		this.linkJson.addProperty(key, "null");
		
		return this;
	}
	
	/**
	 * 添加组合条件查询(keys、values、conditions长度要一致，links长度比其他少1)
	 * @param keys				｛字段，字段...｝
	 * @param values			｛值，值...｝
	 * @param conditions		｛条件，条件...｝
	 * @param links				｛连接符...｝
	 * @return
	 */
	public QueryParamWriter addQueryParams(String[] keys, Object[] values, QueryCondition[] conditions, QueryLink[] links){
		
		if(keys == null || values == null || conditions == null || links == null)
			return this;
		
		if(keys.length != values.length || keys.length != conditions.length)
			return this;
		
		if(keys.length != 1 && (links.length == 0 || keys.length - links.length != 1))
			return this;
		
		String keyStr = "";
		String valueStr = "";
		String conStr = "";
		String linkStr = "null,";
		for(int i = 0; i < keys.length; i++){
		
			if(StringUtils.isEmpty(keys[i]) || conditions[i] == null)
				continue;
			
			if(i < keys.length - 1 && links[i] == null)
				continue;
			
			keyStr += keys[i];
			valueStr += this.getValueStr(values[i]);
			conStr += conditions[i].toString();
			
			if(i < links.length)
				linkStr += links[i].toString();
			
			if(i < keys.length - 1){
				keyStr += ",";
				valueStr += "<split>com.gdcct.ec</split>";
				conStr += ",";
			}
			
			if(i < links.length - 1)
				linkStr += ",";
		}
		
		this.valueJson.addProperty(keyStr, valueStr);
		this.conJson.addProperty(keyStr, conStr);
		this.linkJson.addProperty(keyStr, linkStr);
		
		return this;
	}
	
	/**
	 * 添加自定义条件查询
	 * @param key			字段
	 * @param value			值(简单的类型)
	 * @return
	 * @throws JSONException 
	 */
	public QueryParamWriter addCustomQueryParam(String key, Object value) {
		
		if(StringUtils.isEmpty(key))
			return this;
		
		this.valueJson.addProperty(key, this.getValueStr(value));
		this.conJson.addProperty(key, "?");
		this.linkJson.addProperty(key, "null");
		
		return this;
	}
	
	/**
	 * 获取查询字段值字符串
	 * @return
	 */
	public String getQueryValue(){
		return this.valueJson.toString();
	}
	
	/**
	 * 获取查询条件字符串
	 * @return
	 */
	public String getQueryCon(){
		return this.conJson.toString();
	}
	
	/**
	 * 获取查询连接符字符串
	 * @return
	 */
	public String getQueryLink(){
		return this.linkJson.toString();
	}
	/**
	 * 获得对象字符串(内部使用)
	 * @param value
	 * @return
	 */
	private String getValueStr(Object value){
		
		/*String valueStr = "";
		if(value == null)
			valueStr = "null";
		else if(value instanceof java.util.Date)
			valueStr = "new " + value.getClass().getName() + "(" + ((java.util.Date)value).getTime() + ")";
		else if(value instanceof java.lang.String)
			valueStr = value.toString();
		else
			valueStr = "new " + value.getClass().getName() + "(\"" + value.toString() + "\")";
		
		return valueStr;*/
		
		StringBuffer sb = new StringBuffer();
		
		if(value == null || !value.getClass().getName().startsWith("java.lang.")){
			sb.append("null");
		}else{
			sb.append(value.getClass().getName());
			sb.append("<split>query.value</split>");
			sb.append(value.toString());
		}
		
		return sb.toString();
	}
	
}
