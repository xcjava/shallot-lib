package com.gmail.xcjava.base.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class OrderParamReader {

	List<String> orderField;
	List<String> orderType;
	JsonObject customOrderParamIson;
	JsonObject orderParamIson;
	Map<String, Object> map;
	Gson gson;
	
	public OrderParamReader(String orderParamStr, String customOrderParamStr){
		this.orderField = new ArrayList<String>();
		this.orderType = new ArrayList<String>();
		this.orderParamIson = new JsonObject();
		this.customOrderParamIson = new JsonObject();
		this.map = new HashMap<String, Object>();
		
		JsonParser jp = new JsonParser();
		if(!StringUtils.isEmpty(customOrderParamStr)){
			this.customOrderParamIson = jp.parse(customOrderParamStr).getAsJsonObject();
		}
		if(!StringUtils.isEmpty(orderParamStr)){
			gson = new Gson();
			Type type = new TypeToken<ArrayList<String>>(){}.getType();
			this.orderParamIson = jp.parse(orderParamStr).getAsJsonObject();
			Set<Entry<String, JsonElement>> set = orderParamIson.entrySet();
			for(Entry<String, JsonElement> e : set){
				this.orderField = gson.fromJson(e.getKey(), type);
				this.orderType = gson.fromJson(e.getValue().getAsString(), type);
			}
		}
	}
	
	/**
	 * 获取自定义排序参数
	 * @return
	 */
	public Map<String, Object> getCustomOrderParam(){
		
		Set<Entry<String, JsonElement>> set = customOrderParamIson.entrySet();
		for(Entry<String, JsonElement> e : set){
			String key = e.getKey();
			String value = e.getValue().getAsString();
			
			
			if(value.contains("<split>com.gdcct.ec</split>")){
				
				String[] valueStr = value.split("<split>com.gdcct.ec</split>");
				
				//多条件、多值
				if(key.contains(",")){
					String[] keyStr = key.split(",");
					Map<String, Object> innerMap = new HashMap<String, Object>();
					for(int i = 0; i < keyStr.length; i++){
						innerMap.put(keyStr[i], "null".equals(valueStr[i]) ? null : toObject(valueStr[i]));
					}
					this.map.put(key, innerMap);
				}
				////单条件、多值
				else{
					List<Object> list = new ArrayList<Object>();
					for(String s : valueStr){
						list.add("null".equals(s) ? null : toObject(s));
					}
					this.map.put(key, list);
				}
			}
			//单条件、单值
			else{
				this.map.put(key, "null".equals(value) ? null : toObject(value));
			}
			
		}
		
		return this.map;
	}
	
	
	/**
	 * 获取排序语句
	 * @param prefix
	 * @return
	 */
	public String getOrderString(String prefix){
		if(this.orderField.size() == 0)
			return "";
		String orderString = "";
		for(int i = 0; i < this.orderField.size(); i++){
			orderString += (prefix == null ? this.orderField.get(i) : prefix + "." + this.orderField.get(i)) + " " + this.orderType.get(i) + " ";
			if(i + 1 != this.orderField.size())
				orderString += ", ";
		}
		return orderString;
	}
	
	private Object toObject(String str){
		
		String[] strs = str.split("<split>orderBy.value</split>");
		if(strs.length != 2)
			return null;
		
		if(strs[0].trim().equals("java.lang.Boolean")){
			return new Boolean(strs[1]);
		}else if(strs[0].trim().equals("java.lang.Integer")){
			return new Integer(strs[1]);
		}else if(strs[0].trim().equals("java.lang.Double")){
			return new Double(strs[1]);
		}else if(strs[0].trim().equals("java.lang.Float")){
			return new Float(strs[1]);
		}else if(strs[0].trim().equals("java.lang.Long")){
			return new Long(strs[1]);
		}else if(strs[0].trim().equals("java.lang.Short")){
			return new Short(strs[1]);
		}else{
			return strs[1];
		}
	}
}
