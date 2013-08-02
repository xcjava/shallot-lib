package com.gmail.xcjava.base.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QueryParamReader {

	private JsonObject valueJson;
	private JsonObject conJson;
	private JsonObject linkJson;
	private String hql;
	private List<Object> paramList;
	private Map<String, Object> customParam;
	public QueryParamReader(String queryValue, String queryCon, String queryLink){
		JsonParser jp = new JsonParser();
		this.valueJson = jp.parse(queryValue).getAsJsonObject();
		this.conJson = jp.parse(queryCon).getAsJsonObject();
		this.linkJson = jp.parse(queryLink).getAsJsonObject();
		this.hql = "";
		this.paramList = new ArrayList<Object>();
		this.customParam = new HashMap<String, Object>();
	}
	
	/**
	 * 获取查询语句
	 * @param prefix
	 * @return
	 */
	public String getQueryHql(String prefix){
		
		Set<Entry<String, JsonElement>> set= valueJson.entrySet();
		if(set.size() == 0)
			return "";
		
		for(Entry<String, JsonElement> e : set){
			String key = e.getKey();
			//组合条件
			if(key.contains(",")){
				String[] keyItem = key.split(",");
				String[] valueItem = valueJson.get(key).getAsString().split("<split>com.gdcct.ec</split>");
				String[] conItem = conJson.get(key).getAsString().split(",");
				String[] linkItem = linkJson.get(key).getAsString().split(",");
				
				this.hql += " and ( ";
				for(int i = 0; i < keyItem.length; i++){
					if(!"null".equals(valueItem[i])){
						this.hql += prefix + "." + keyItem[i] + " " + conItem[i] + " " + "?" + " ";
						paramList.add(toObject(valueItem[i]));
					}else{
						this.hql += prefix + "." + keyItem[i] + " " + conItem[i] + " " + "null" + " ";
					}
					
					if(i < linkItem.length - 1)
						this.hql += linkItem[i+1] + " ";
				}
				this.hql += ")";
			}
			//单条件
			else{
				String value = valueJson.get(key).getAsString();
				String con = conJson.get(key).getAsString();
				//自定义查询
				if("?".equals(con)){
					customParam.put(key, "null".equals(value) ? null : toObject(value));
				}
				//普通查询
				else{
					if(!"null".equals(value)){
						this.hql += " and " + prefix + "." + key + " " + con + " " + "?" + " ";
						paramList.add(toObject(value));
					}else{
						this.hql += " and " + prefix + "." + key + " " + con + " " + "null" + " ";
					}
				}
			}
		}
		
		return this.hql;
	}
	
	/**
	 * 获取查询参数值
	 * @return
	 */
	public  List<Object> getQueryParam(){
		return this.paramList;
	}
	
	/**
	 * 获取自定义查询参数
	 * @return	JSONObject
	 * @throws JSONException
	 */
	public Map<String, Object> getCustomQueryParam() {
		return this.customParam;
	}
	
	private Object toObject(String str){
		
		/*if(!str.startsWith("new java.") && !str.endsWith(")"))
			return str;
		
		GroovyShell gs = new GroovyShell();
		return gs.evaluate(str);*/
		
		String[] strs = str.split("<split>query.value</split>");
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
