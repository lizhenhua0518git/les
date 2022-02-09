package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**.
 * 
 *
 * 功能描述：载具策略实体类
 * @author wangzhou
 * 时间：2018年8月31日
 */
public class StrategyCar extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;//主键
	private String productLine;//产品线
	private String projectCode;//项目号
	private String cartCode;//车号代码
	private String subFactory;//分厂
	private String cartTypeCode;//车型组件号
	private String cartTypeDesc;//车型描述
	private String stationModuleCode;//工位组件号
	private String stationDesc;//工位描述
	private String processCode;//工序组件号
	private String processDesc;//工序描述
	private String type;//属性
	private String carrierType;//建议车型
	private String carrierCode;//车号
	private String putLevel;//摆放层级
	private String secondCarrierType;//次选载具
	private String superCarrier;//超大件载具
	private Integer status;//0-同步数据，1-编辑数据
	private String factory;//工厂
	private String bomuse;//bom用途
	private String stationCode;//工位代码
	private String pipeLine;//产线
 
	private String sublineCode;//辅线工位
	private String sublineDesc;//辅线工位描述
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getSubFactory() {
		return subFactory;
	}
	public void setSubFactory(String subFactory) {
		this.subFactory = subFactory;
	}
	
	public String getStationModuleCode() {
		return stationModuleCode;
	}
	public void setStationModuleCode(String stationModuleCode) {
		this.stationModuleCode = stationModuleCode;
	}
	public String getStationDesc() {
		return stationDesc;
	}
	public void setStationDesc(String stationDesc) {
		this.stationDesc = stationDesc;
	}
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getProcessDesc() {
		return processDesc;
	}
	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getPutLevel() {
		return putLevel;
	}
	public void setPutLevel(String putLevel) {
		this.putLevel = putLevel;
	}
	public String getSecondCarrierType() {
		return secondCarrierType;
	}
	public void setSecondCarrierType(String secondCarrierType) {
		this.secondCarrierType = secondCarrierType;
	}
	public String getSuperCarrier() {
		return superCarrier;
	}
	public void setSuperCarrier(String superCarrier) {
		this.superCarrier = superCarrier;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getBomuse() {
		return bomuse;
	}
	public void setBomuse(String bomuse) {
		this.bomuse = bomuse;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getPipeLine() {
		return pipeLine;
	}
	public void setPipeLine(String pipeLine) {
		this.pipeLine = pipeLine;
	}
	
	 
	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}
	public String getCartTypeCode() {
		return cartTypeCode;
	}
	public void setCartTypeCode(String cartTypeCode) {
		this.cartTypeCode = cartTypeCode;
	}
	public String getCartTypeDesc() {
		return cartTypeDesc;
	}
	public void setCartTypeDesc(String cartTypeDesc) {
		this.cartTypeDesc = cartTypeDesc;
	}
	
	
	
	public String getSublineCode() {
		return sublineCode;
	}
	public void setSublineCode(String sublineCode) {
		this.sublineCode = sublineCode;
	}
	public String getSublineDesc() {
		return sublineDesc;
	}
	public void setSublineDesc(String sublineDesc) {
		this.sublineDesc = sublineDesc;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return ReflectionToStringBuilder.toString(this);
	}
	

}
