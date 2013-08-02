package com.gmail.xcjava.base.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.gmail.xcjava.base.date.DateUtil;

public class QueryParamModel {

	private Map<String, Object> objMap;
	private Map<String, Object> optypeMap;
	private Map<String, Object> combinationRelationMap;
	private Map<String, List<String>> combinationAttrMap;
	private Map<String, Object> dataTypeMap;
	private Map<String, Boolean> isCombinationMap;
	private Map<String, Boolean> isCustomQueryMap;
	
	
	public QueryParamModel(){
		this.objMap = new HashMap<String, Object>();
		this.optypeMap = new HashMap<String, Object>();
		this.combinationRelationMap = new HashMap<String, Object>();
		this.isCombinationMap = new HashMap<String, Boolean>();
		this.dataTypeMap = new HashMap<String, Object>();
		this.isCustomQueryMap = new HashMap<String, Boolean>();
		this.combinationAttrMap = new HashMap<String, List<String>>();
	}
	
	/**
	 * 等于
	 */
	public QueryParamModel eq(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, "=");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 不等于
	 */
	public QueryParamModel uneq(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, "!=");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 大于
	 */
	public QueryParamModel gt(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, ">");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 大于等于
	 */
	public QueryParamModel ge(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, ">=");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 小于
	 */
	public QueryParamModel lt(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, "<");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 小于等于
	 */
	public QueryParamModel le(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, "<=");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 模糊搜索
	 */
	public QueryParamModel like(String key, Object obj, Type type){
		this.objMap.put(key, obj);
		this.optypeMap.put(key, "like");
		this.isCombinationMap.put(key, false);
		this.dataTypeMap.put(key, type);
		this.isCustomQueryMap.put(key, false);
		this.combinationRelationMap.put(key, null);
		return this;
	}
	
	/**
	 * 自定义查询
	 */
	public QueryParamModel customQuery(String key, Object[] objs, Type[] types){
		List<Object> objList = new ArrayList<Object>();
		List<Object> typeList = new ArrayList<Object>();
		for(int i = 0; i < objs.length; i++){
			objList.add(objs[i]);
			typeList.add(types[i]);
		}
		this.combinationRelationMap.put(key, null);
		this.isCombinationMap.put(key, false);
		this.objMap.put(key, objList);
		this.optypeMap.put(key, null);
		this.dataTypeMap.put(key, typeList);
		this.isCustomQueryMap.put(key, true);
		return this;
	}
	public QueryParamModel customQuery(String key, Object objs, Type types){
		this.combinationRelationMap.put(key, null);
		this.isCombinationMap.put(key, false);
		this.objMap.put(key, objs);
		this.optypeMap.put(key, null);
		this.dataTypeMap.put(key, types);
		this.isCustomQueryMap.put(key, true);
		return this;
	}
	
	/**
	 * 与关系合并搜索条件
	 */
	public QueryParamModel and(String key, QueryParamModel[] qpms){
		List<Object> objs = new ArrayList<Object>();
		List<Object> optypes = new ArrayList<Object>();
		List<Object> types = new ArrayList<Object>();
		List<String> attrs = new ArrayList<String>();
		for(QueryParamModel item : qpms){
			objs.addAll(item.objMap.values());
			optypes.addAll(item.optypeMap.values());
			types.addAll(item.dataTypeMap.values());
			attrs.add((String) item.objMap.keySet().toArray()[0]);
		}
		this.combinationAttrMap.put(key, attrs);
		this.combinationRelationMap.put(key, "and");
		this.isCombinationMap.put(key, true);
		this.objMap.put(key, objs);
		this.optypeMap.put(key, optypes);
		this.dataTypeMap.put(key, types);
		this.isCustomQueryMap.put(key, false);
		return this;
	}
	
	/**
	 * 或关系合并搜索条件
	 */
	public QueryParamModel or(String key, QueryParamModel[] qpms){
		List<Object> objs = new ArrayList<Object>();
		List<Object> optypes = new ArrayList<Object>();
		List<Object> types = new ArrayList<Object>();
		List<String> attrs = new ArrayList<String>();
		for(QueryParamModel item : qpms){
			objs.addAll(item.objMap.values());
			optypes.addAll(item.optypeMap.values());
			types.addAll(item.dataTypeMap.values());
			attrs.add((String) item.objMap.keySet().toArray()[0]);
		}
		this.combinationAttrMap.put(key, attrs);
		this.combinationRelationMap.put(key, "or");
		this.isCombinationMap.put(key, true);
		this.objMap.put(key, objs);
		this.optypeMap.put(key, optypes);
		this.dataTypeMap.put(key, types);
		this.isCustomQueryMap.put(key, false);
		return this;
	}
	
	/**
	 * 转换为hql
	 * @return
	 */
	public String toHql(String key, String prefix){
		if(!this.objMap.containsKey(key))
			return "";
		
		if(this.isCustomQueryMap.get(key))
			return "";
		
		StringBuffer sb = new StringBuffer();
		sb.append(" and ");
		if(this.isCombinationMap.get(key)){
			sb.append("( ");
			sb.append(this.combinationRelationMap.get(key).equals("and") ? "1=1 " : "1!=1 ");
			for(int i = 0; i < ((List) objMap.get(key)).size(); i++){
				sb.append(this.combinationRelationMap.get(key)).append(" ").append(prefix).append(".").append(combinationAttrMap.get(key).get(i)).append(" ").append(((List)optypeMap.get(key)).get(i));
				sb.append(((List)objMap.get(key)).get(i) == null ? " null " : " ? ");
			}
			sb.append(") ");
		}else{
			sb.append(prefix).append(".").append(key).append(" ").append(optypeMap.get(key));
			sb.append(objMap.get(key) == null ? " null " : " ? ");
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取查询对象
	 * @param key
	 * @return
	 */
	public Object getQueryParamObject(String key){
		if(!this.objMap.containsKey(key))
			return null;
		
		return objMap.get(key);
	}
	
	/**
	 * 填充参数列表
	 * @param key
	 * @param paramList
	 * @return
	 */
	public boolean fillParamList(String key, List<Object> paramList){
		
		if(!this.containsParam(key))
			return false;
		
		Object obj = this.getQueryParamObject(key);
		if(obj == null)
			return true;
		
		if(obj instanceof List){
			for(Object item : (List)obj){
				paramList.add(item);
			}
		}else{
			paramList.add(obj);
		}
		return true;
	}
	
	/**
	 * 填充参数类型列表
	 * @param key
	 * @param typeList
	 * @return
	 */
	public boolean fillTypeList(String key, List<Type> typeList){
		if(!this.containsParam(key))
			return false;
		
		Object obj = this.getQueryParamType(key);
		if(obj == null)
			return true;
		
		if(obj instanceof List){
			for(Object item : (List)obj){
				typeList.add((Type) item);
			}
		}else{
			typeList.add((Type) obj);
		}
		return true;
	}

	/**
	 * 获取查询类型
	 * @param key
	 * @return
	 */
	public Object getQueryParamType(String key){
		if(!this.objMap.containsKey(key))
			return null;
		
		return dataTypeMap.get(key);
	}
	
	/**
	 * 查询参数是否存在
	 * @param key
	 * @return
	 */
	public boolean containsParam(String key){
		return this.objMap.containsKey(key);
	}
	
	public static void main(String[] args){
		QueryParamModel qpm = new QueryParamModel().uneq("marry", null, null).eq("id", 1, Hibernate.INTEGER).gt("age", 10, Hibernate.INTEGER).like("name", "fuck", Hibernate.STRING);
		QueryParamModel[] qpmbirthday = {new QueryParamModel().ge("birthday1", DateUtil.parseDate("19800101", "yyyyMMdd"), Hibernate.DATE), new QueryParamModel().le("birthday2", new Date(), Hibernate.DATE)};
		QueryParamModel[] qpmsex = {new QueryParamModel().eq("sex1", Boolean.valueOf(false), Hibernate.BOOLEAN), new QueryParamModel().eq("sex2", true, Hibernate.BOOLEAN)};
		qpm.and("birthday", qpmbirthday);
		qpm.or("sex", qpmsex);
		System.out.println(qpm.toHql("marry", "model"));
		System.out.println(qpm.toHql("id", "model"));
		System.out.println(qpm.toHql("age", "model"));
		System.out.println(qpm.toHql("name", "model"));
		System.out.println(qpm.toHql("birthday", "model"));
		System.out.println(qpm.toHql("sex", "model"));
		System.out.println(qpm.getQueryParamObject("id"));
		System.out.println(qpm.getQueryParamType("id"));
	}
}