package com.zkzn.les.gateway.util;

import java.io.Serializable;

public class PageCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4095334929076820036L;
	
	private int limit=10;
    private int page;
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
