package com.zkzn.les.basicInfo.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author wangzhou
 * @date 2020年7月28日
 * @Description:单据类型实体类
 */
public class DocumentType extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159539909860902084L;

	
	private String id;//主键
	@ApiModelProperty(name="documentCode", value="单据类型编码")
	private String documentCode;
	@ApiModelProperty(name="documentName", value="单据类型名称")
	private String documentName;
	@ApiModelProperty(name="businessType", value="业务类型（0-入库、1-出库、2-调拨")
	private Integer businessType;
	@ApiModelProperty(name="sourceOfDocument", value="单据来源（0-接口，1-自建）")
	private Integer sourceOfDocument;
	@ApiModelProperty(name="status", value="状态（1-启动，0-禁用）")
	private Integer status;
	@ApiModelProperty(name="businessTypeStr", value="业务类型（0-入库、1-出库、2-调拨）")
	private String businessTypeStr;
	@ApiModelProperty(name="sourceOfDocumentStr", value="单据来源（0-接口，1-自建）")
	private String sourceOfDocumentStr;
	@ApiModelProperty(name="statusStr", value="状态（0-启动，1-禁用）")
	private String statusStr;
	@ApiModelProperty(name="documentCodesSpecific", value="排除特定的单据类型 ',' 分割")
	private String documentCodesSpecific;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocumentCode() {
		return documentCode;
	}
	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getSourceOfDocument() {
		return sourceOfDocument;
	}
	public void setSourceOfDocument(Integer sourceOfDocument) {
		this.sourceOfDocument = sourceOfDocument;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	public String getBusinessTypeStr() {
		return businessTypeStr;
	}
	public void setBusinessTypeStr(String businessTypeStr) {
		this.businessTypeStr = businessTypeStr;
	}
	public String getSourceOfDocumentStr() {
		return sourceOfDocumentStr;
	}
	public void setSourceOfDocumentStr(String sourceOfDocumentStr) {
		this.sourceOfDocumentStr = sourceOfDocumentStr;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}


	public String getDocumentCodesSpecific() {
		return documentCodesSpecific;
	}

	public void setDocumentCodesSpecific(String documentCodesSpecific) {
		this.documentCodesSpecific = documentCodesSpecific;
	}
}
