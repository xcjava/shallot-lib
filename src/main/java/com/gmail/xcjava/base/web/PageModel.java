package com.gmail.xcjava.base.web;

/**
 * 分页参数类
 * @author xiaocong
 * @email xcjava@gmail.com
 */
public class PageModel {

	private int pageIndex = 1;	// 当前页数
	private int recordCount; // 总记录条数
	private int pageSize;	// 页数
	private int startRow; // 开始行数
	
	public int getPageCount() {
		if(recordCount%pageSize == 0) {
			pageCount = recordCount/pageSize;
		}else {
			pageCount = recordCount/pageSize + 1;
		}
		
		return pageCount;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	private int pageCount;//总页数
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		
	}

	public int getStartRow() {
		this.startRow = (this.pageIndex - 1) * this.getPageSize();
		return this.startRow;
	}
}
