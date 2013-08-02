package com.gmail.xcjava.base.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class OrderParamWriter {

	List<String> orderField;
	List<String> orderType;
	JsonObject customOrderParamIson;
	JsonObject orderParamIson;
	Gson gson;
	
	public OrderParamWriter(){
		this.orderField = new ArrayList<String>();
		this.orderType = new ArrayList<String>();
		gson = new Gson();
		customOrderParamIson = new JsonObject();
		orderParamIson = new JsonObject();
	}
	
	/**
	 * 添加排序条件
	 * @param field
	 * @param type
	 * @return
	 */
	public OrderParamWriter addOrderCondition(String field, String type){
		
		if(StringUtils.isEmpty(field) || StringUtils.isEmpty(type))
			return this;
		
		this.orderField.add(field);
		this.orderType.add(type);
		return this;
	}
	
	/**
	 * 添加自定义排序（单条件、单值）
	 * @param field
	 * @param obj
	 * @return
	 */
	public OrderParamWriter addCustomOrderParam(String field, Object obj){
		
		if(StringUtils.isEmpty(field))
			return this;
		
		this.customOrderParamIson.addProperty(field, this.getValueStr(obj));
		return this;
	}
	
	/**
	 * 添加自定义排序（单条件、多值）
	 * @param field
	 * @param obj
	 * @return
	 */
	public OrderParamWriter addCustomOrderParam(String field, Object[] objs){
		
		if(StringUtils.isEmpty(field) || objs == null || objs.length == 0)
			return this;
		
		String value = "";
		for(int i = 0; i < objs.length; i++){
			value += this.getValueStr(objs[i]);
			if(i < objs.length - 1)
				value += "<split>com.gdcct.ec</split>";
		}
		this.customOrderParamIson.addProperty(field, value);
		return this;
	}
	
	/**
	 * 添加自定义排序（多条件、多值）
	 * @param field
	 * @param obj
	 * @return
	 */
	public OrderParamWriter addCustomOrderParam(String[] fields, Object[] objs){
		
		if(fields == null || objs == null || fields.length != objs.length || fields.length == 0)
			return this;
		
		String key = "";
		String value = "";
		for(int i = 0; i < fields.length; i++){
			key += fields[i];
			value += this.getValueStr(objs[i]);
			
			if(i < fields.length - 1){
				key += ",";
				value += "<split>com.gdcct.ec</split>";
			}
		}
		this.customOrderParamIson.addProperty(key, value);
		return this;
	}
	
	/**
	 * 获取排序参数字符串
	 * @return
	 */
	public String getOrderParamStr(){
		this.orderParamIson.addProperty(gson.toJson(this.orderField), gson.toJson(this.orderType));
		return this.orderParamIson.toString();
	}
	
	/**
	 * 获取自定义排序参数字符串
	 * @return
	 */
	public String getCustomOrderParamStr(){
		return this.customOrderParamIson.toString();
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
			sb.append("<split>orderBy.value</split>");
			sb.append(value.toString());
		}
		
		return sb.toString();
	}
}
