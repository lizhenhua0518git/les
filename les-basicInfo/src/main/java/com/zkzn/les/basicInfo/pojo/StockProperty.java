package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

/**.
 * 
 * @author wangzhou
 * 盘点配置实体类
 */
public class StockProperty extends PageCondition {

	
	
	/**
	 * 创建人: wangzhou
	 * 时间:20202020年4月2日下午2:30:51
	 * 功能描述:
	 */
	private static final long serialVersionUID = -2854926049010929744L;
	
	
	private String id;
	/**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 仓库编码(les系统中编码)
     */
    private String warehouseCode;
    /**
     * 仓库代码（sap中的库存地点）
     */
    private String storageLocation;
    /**
     * 所属产品线
     */
    private String productLine;
    /**
     * 所属产品线名称
     */
    private String productLineName;
    /**
     *班组长
     */
    private String teamLeader;
    /**
     * 班组长用户表id
     */
    private String teamLeaderId;
    /**
     * sap管理工
     */
    private String sapManager;
    /**
     * sap管理工用户表id
     */
    private String sapManagerId;
    /**
     * 业务组
     */
    private String businessLeader;
    /**
     * 业务组用户表id
     */
    private String businessLeaderId;
    /**
     * 分管部长
     */
    private String minister;
    /**
     * 分管部长用户id
     */
    private String ministerId;
    
    /**
     * 业务经理
     */
    private String businessManager;
    
    /**
     *业务经理id
     */
    private String businessManagerId;
    
    /**
     *  部长用户
     */
    private String mainMinister;
    
    /**
     *  部长用户id
     */
    private String mainMinisterId;
    
    /**
     * 物控组
     */
    private String materialControl;
    /**
    * 物控组id
    */
   private String materialControlId;  
    /**
     * 仓库名称
     */
    private String createUser;

    /**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-02-27 15:51
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 获取 id
	 * @return 返回 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 id
	 * @param id 对id进行赋值
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 warehouseName
	 * @return 返回 warehouseName
	 */
	public String getWarehouseName() {
		return warehouseName;
	}

	/**
	 * 设置 warehouseName
	 * @param warehouseName 对warehouseName进行赋值
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	/**
	 * 获取 warehouseCode
	 * @return 返回 warehouseCode
	 */
	public String getWarehouseCode() {
		return warehouseCode;
	}

	/**
	 * 设置 warehouseCode
	 * @param warehouseCode 对warehouseCode进行赋值
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	/**
	 * 获取 storageLocation
	 * @return 返回 storageLocation
	 */
	public String getStorageLocation() {
		return storageLocation;
	}

	/**
	 * 设置 storageLocation
	 * @param storageLocation 对storageLocation进行赋值
	 */
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	/**
	 * 获取 productLine
	 * @return 返回 productLine
	 */
	public String getProductLine() {
		return productLine;
	}

	/**
	 * 设置 productLine
	 * @param productLine 对productLine进行赋值
	 */
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	/**
	 * 获取 productLineName
	 * @return 返回 productLineName
	 */
	public String getProductLineName() {
		return productLineName;
	}

	/**
	 * 设置 productLineName
	 * @param productLineName 对productLineName进行赋值
	 */
	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	/**
	 * 获取 teamLeader
	 * @return 返回 teamLeader
	 */
	public String getTeamLeader() {
		return teamLeader;
	}

	/**
	 * 设置 teamLeader
	 * @param teamLeader 对teamLeader进行赋值
	 */
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	/**
	 * 获取 teamLeaderId
	 * @return 返回 teamLeaderId
	 */
	public String getTeamLeaderId() {
		return teamLeaderId;
	}

	/**
	 * 设置 teamLeaderId
	 * @param teamLeaderId 对teamLeaderId进行赋值
	 */
	public void setTeamLeaderId(String teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}

	/**
	 * 获取 sapManager
	 * @return 返回 sapManager
	 */
	public String getSapManager() {
		return sapManager;
	}

	/**
	 * 设置 sapManager
	 * @param sapManager 对sapManager进行赋值
	 */
	public void setSapManager(String sapManager) {
		this.sapManager = sapManager;
	}

	/**
	 * 获取 sapManagerId
	 * @return 返回 sapManagerId
	 */
	public String getSapManagerId() {
		return sapManagerId;
	}

	/**
	 * 设置 sapManagerId
	 * @param sapManagerId 对sapManagerId进行赋值
	 */
	public void setSapManagerId(String sapManagerId) {
		this.sapManagerId = sapManagerId;
	}

	/**
	 * 获取 businessLeader
	 * @return 返回 businessLeader
	 */
	public String getBusinessLeader() {
		return businessLeader;
	}

	/**
	 * 设置 businessLeader
	 * @param businessLeader 对businessLeader进行赋值
	 */
	public void setBusinessLeader(String businessLeader) {
		this.businessLeader = businessLeader;
	}

	/**
	 * 获取 businessLeaderId
	 * @return 返回 businessLeaderId
	 */
	public String getBusinessLeaderId() {
		return businessLeaderId;
	}

	/**
	 * 设置 businessLeaderId
	 * @param businessLeaderId 对businessLeaderId进行赋值
	 */
	public void setBusinessLeaderId(String businessLeaderId) {
		this.businessLeaderId = businessLeaderId;
	}

	/**
	 * 获取 minister
	 * @return 返回 minister
	 */
	public String getMinister() {
		return minister;
	}

	/**
	 * 设置 minister
	 * @param minister 对minister进行赋值
	 */
	public void setMinister(String minister) {
		this.minister = minister;
	}

	/**
	 * 获取 ministerId
	 * @return 返回 ministerId
	 */
	public String getMinisterId() {
		return ministerId;
	}

	/**
	 * 设置 ministerId
	 * @param ministerId 对ministerId进行赋值
	 */
	public void setMinisterId(String ministerId) {
		this.ministerId = ministerId;
	}

	/**
	 * 获取 businessManager
	 * @return 返回 businessManager
	 */
	public String getBusinessManager() {
		return businessManager;
	}

	/**
	 * 设置 businessManager
	 * @param businessManager 对businessManager进行赋值
	 */
	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	/**
	 * 获取 businessManagerId
	 * @return 返回 businessManagerId
	 */
	public String getBusinessManagerId() {
		return businessManagerId;
	}

	/**
	 * 设置 businessManagerId
	 * @param businessManagerId 对businessManagerId进行赋值
	 */
	public void setBusinessManagerId(String businessManagerId) {
		this.businessManagerId = businessManagerId;
	}

	/**
	 * 获取 mainMinister
	 * @return 返回 mainMinister
	 */
	public String getMainMinister() {
		return mainMinister;
	}

	/**
	 * 设置 mainMinister
	 * @param mainMinister 对mainMinister进行赋值
	 */
	public void setMainMinister(String mainMinister) {
		this.mainMinister = mainMinister;
	}

	/**
	 * 获取 mainMinisterId
	 * @return 返回 mainMinisterId
	 */
	public String getMainMinisterId() {
		return mainMinisterId;
	}

	/**
	 * 设置 mainMinisterId
	 * @param mainMinisterId 对mainMinisterId进行赋值
	 */
	public void setMainMinisterId(String mainMinisterId) {
		this.mainMinisterId = mainMinisterId;
	}

	/**
	 * 获取 materialControl
	 * @return 返回 materialControl
	 */
	public String getMaterialControl() {
		return materialControl;
	}

	/**
	 * 设置 materialControl
	 * @param materialControl 对materialControl进行赋值
	 */
	public void setMaterialControl(String materialControl) {
		this.materialControl = materialControl;
	}

	/**
	 * 获取 materialControlId
	 * @return 返回 materialControlId
	 */
	public String getMaterialControlId() {
		return materialControlId;
	}

	/**
	 * 设置 materialControlId
	 * @param materialControlId 对materialControlId进行赋值
	 */
	public void setMaterialControlId(String materialControlId) {
		this.materialControlId = materialControlId;
	}

	/**
	 * 获取 createUser
	 * @return 返回 createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置 createUser
	 * @param createUser 对createUser进行赋值
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	

}
