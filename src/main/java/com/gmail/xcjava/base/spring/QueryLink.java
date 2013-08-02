package com.gmail.xcjava.base.spring;

public enum QueryLink {

	/**
	 * 与关系
	 */
	QL_AND{

		@Override
		public String toString() {
			return "and";
		}
		
	},
	
	/**
	 * 或关系
	 */
	QL_OR{

		@Override
		public String toString() {
			return "or";
		}
		
	};
	
	@Override
	public abstract String toString();
}
