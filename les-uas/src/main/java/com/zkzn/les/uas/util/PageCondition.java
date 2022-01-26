package com.zkzn.les.uas.util;

import java.io.Serializable;

public class PageCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5994360006229635123L;
	
	private int limit=10;
    private int page=1;
    private int totalCount;
    private String sortColums; 

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getPage() {
        return this.page;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSortColums() {
        return sortColums;
    }

    public void setSortColums(String sortColums) {
        this.sortColums = sortColums;
    }
	

}
