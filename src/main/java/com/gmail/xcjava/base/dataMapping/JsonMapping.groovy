package com.gmail.xcjava.base.dataMapping

import java.lang.reflect.Field;

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

class JsonMapping {

	/**
	 * 对象转Json
	 * @param obj
	 * @return
	 */
	static String obj2json(obj){
		return JsonOutput.toJson(obj);
	}
	
	/**
	 * json转对象
	 * @param str
	 * @return
	 */
	static json2obj(str){
		JsonSlurper js = new JsonSlurper();
		return js.parseText(str);
	}
	
}
