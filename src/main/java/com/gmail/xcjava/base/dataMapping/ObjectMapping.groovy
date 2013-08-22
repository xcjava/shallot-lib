package com.gmail.xcjava.base.dataMapping

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.log4j.Logger;

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

class ObjectMapping {
	
	private static Logger logger = Logger.getLogger(ObjectMapping.class);
	
	/**
	 * 忽略错误强制类型转换,只允许基础类型<br/>
	 * 例如:str2obj("java.lang.String", 123);//数字转字符串<br/>
	 * str2obj("java.util.Date", data.getTime().toString());//时间戳转时间<br/>
	 * str2obj("java.lang.Integer", 123l);//long转int<br/>
	 * @param type
	 * @param str
	 * @return
	 */
	static str2obj(type, str){
		GroovyShell gs = new GroovyShell();
		if(type.equals(Date.class.getName())){
			return gs.evaluate("new " + type + "(" + str +")");
		}else if(type.equals(String.class.getName())){
			return str + "";
		}
		return gs.evaluate("new " + type + "(\"" + str +"\")");
	}
	
	/**
	 * 对象映射
	 * 拷贝同名同类型的域变量的值
	 * @param source
	 * @param target
	 * @return
	 */
	static objMapping(source, target, boolean allowNullValue){
		
		long debugTimestamp = 0;
		if(logger.isDebugEnabled()){
			debugTimestamp = new Date().getTime();
		}
		
		Field[] targetFields = target.getClass().getDeclaredFields();
		
		for(Field f : targetFields){
			Field s;
			try{
				s = source.getClass().getDeclaredField(f.getName());
			}catch(NoSuchFieldException e){
				continue;
			}
			
			if(s != null && s.getType().getName().equals(f.getType().getName())){
				if(!allowNullValue && source[s.getName()] == null){
					continue;
				}
				target[s.getName()] = source[s.getName()];
			}
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("对象映射耗时:" + (new Date().getTime() - debugTimestamp) + "ms");
		}
		
		return target;
				
	}
	
	static objMapping(source, target){
		objMapping(source, target, true);
	}
}
