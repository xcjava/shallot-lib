package com.gmail.xcjava.base.web;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {

	private List<String> orderField;
	private List<String> orderType;
	
	public OrderModel(){
		this.orderField = new ArrayList<String>();
		this.orderType = new ArrayList<String>();
	}
	
	public OrderModel addOrderCondition(String field, String type){
		orderField.add(field);
		orderType.add(type);
		return this;
	}
	
	public String getOrderString(String prefix){
		if(orderField.size() == 0)
			return "";
		String orderString = "";
		for(int i = 0; i < orderField.size(); i++){
			orderString += (prefix == null ? orderField.get(i) : prefix + "." + orderField.get(i)) + " " + orderType.get(i) + " ";
			if(i + 1 != orderField.size())
				orderString += ", ";
		}
		return orderString;
	}
}
