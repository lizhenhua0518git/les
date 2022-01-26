package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResourceApiAdress extends PageCondition{
	/**
	 * 创建人: luozhihong
	 * 时间:2020年9月1日
	 */
	private static final long serialVersionUID = -2404819768481378598L;
	
    private String id;//主键id

    private String apiPath;//api接口路径

    private String resourceId;//资源的id

    private String remark;//资源描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}