package com.zkzn.les.common.util.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PageCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4095334929076820036L;
	
	private int limit=10;
    private int page=1;
    private int totalCount;
    private String sortColums; 
    @ApiModelProperty(name="createrId", value="创建人id")
    private String createrId;//创建人id
    @ApiModelProperty(name="createrName", value="创建人名称")
    private String createrName;//创建人名称
    @ApiModelProperty(name="createTime", value="创建时间")
    private Date createTime; //创建时间
    @ApiModelProperty(name="modifierId", value="修改人id")      
    private String modifierId; // 修改人id
    @ApiModelProperty(name="modifierName", value="修改人名称") 
    private String modifierName; // 修改人名称
    @ApiModelProperty(name="modifiedTime", value="修改时间") 
    private Date modifiedTime; // 修改时间
    @ApiModelProperty(name="remark", value="备注") 
    private String remark; //备注

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

   
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}
    
    
    
}
