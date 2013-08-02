package com.gmail.xcjava.base.spring;

public enum QueryCondition {

	/**
	 * 等于
	 */
	QC_EQ{

		@Override
		public String toString() {
			return "=";
		}

		
	},
	
	/**
	 * 不等于
	 */
	QC_UNEQ{

		@Override
		public String toString() {
			return "!=";
		}
		
	},
	
	/**
	 * 大于
	 */
	QC_GT{

		@Override
		public String toString() {
			return ">";
		}
		
	},
	
	/**
	 * 大于等于
	 */
	QC_GE{

		@Override
		public String toString() {
			return ">=";
		}
		
	},
	
	/**
	 * 小于
	 */
	QC_LT{

		@Override
		public String toString() {
			return "<";
		}
		
	},
	
	/**
	 * 小于等于
	 */
	QC_LE{

		@Override
		public String toString() {
			return "<=";
		}
		
	},
	
	/**
	 * 模糊搜索
	 */
	QC_LIKE{

		@Override
		public String toString() {
			return "like";
		}
		
	};
	
	@Override
	public abstract String toString();
}
