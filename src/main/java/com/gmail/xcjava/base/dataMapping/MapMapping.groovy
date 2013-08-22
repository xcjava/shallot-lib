package com.gmail.xcjava.base.dataMapping;

import java.lang.reflect.Field;

class MapMapping {

	/**
	 * 对象转Map
	 * 对象的属性必须都是基础类型
	 * @param obj
	 * @return
	 */
	static obj2map(obj){
		
		Map map = new HashMap<String, Object>();
		
		/*Binding binding = new Binding();
		binding.setVariable("obj", obj);
		binding.setVariable("map", map);
		GroovyShell shell = new GroovyShell(binding);*/
		
		Field[] objFields = obj.getClass().getDeclaredFields();
		for(Field f : objFields){
			//shell.evaluate("map." + f.getName() + " = obj." + f.getName());
			map[f.getName()] = obj[f.getName()];
		}
		return map;
		
	}
	
	/**
	 * Map转对象
	 * Map的键值必须都是基础类型
	 * @param map
	 * @param obj
	 * @return
	 */
	static map2obj(map, obj, boolean allowNullValue){
		
		/*Binding binding = new Binding();
		binding.setVariable("obj", obj);
		GroovyShell shell = new GroovyShell(binding);*/
		
		map.each{key, value ->
			Field f;
			try{
				f = obj.getClass().getDeclaredField(key);
			}catch(NoSuchFieldException e){ 

			}
			
			if(f != null && value.getClass().getName().equals(f.getType().getName())){
				/*if(f.getType().getName().equals(Date.class.getName())){
					//shell.evaluate("obj." + key + " = new " + f.getType().getName() + "(" + value.getTime() + ");");
					obj[key] = new Date(value.getTime());
				}else if(f.getType().getName().equals(String.class.getName())){
					obj[key] = value;
				}else{
					//shell.evaluate("obj." + key + " = new " + f.getType().getName() + "(\"" + value + "\");");
					obj[key] = map[key];
				}*/
				
				if(allowNullValue){
					obj[key] = map[key];
				}else{
					if(map[key] != null){
						obj[key] = map[key];
					}
				}
				
				
			}
		}
		
		return obj;
	}
	
	static map2obj(map, obj){
		map2obj(map, obj, true);
	}
}
