package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlmPlantStructure extends PageCondition{
	@ApiModelProperty(name="id", value="主键")
    private String id;
	
	@ApiModelProperty(name="plmUid", value="PLM系统主键id")
    private String plmUid;
	
	@ApiModelProperty(name="bomUid", value="BOM的唯一标识")
    private String bomUid;
	
	@ApiModelProperty(name="plantItemId", value="父节点id")
    private String plantItemId;
	@ApiModelProperty(name="modelType", value="类型:1工厂、2车间、3产线、4工位")
    private String modelType;
	@ApiModelProperty(name="modelTypeName", value="类型名称:1工厂、2车间、3产线、4工位")
	private  String modelTypeName;
	
	@ApiModelProperty(name="modelCode", value="模型编码")
    private String modelCode;
	
	@ApiModelProperty(name="modelName", value="模型名称")
    private String modelName;
 
 
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlmUid() {
        return plmUid;
    }

    public void setPlmUid(String plmUid) {
        this.plmUid = plmUid;
    }

    public String getBomUid() {
        return bomUid;
    }

    public void setBomUid(String bomUid) {
        this.bomUid = bomUid;
    }

    public String getPlantItemId() {
        return plantItemId;
    }

    public void setPlantItemId(String plantItemId) {
        this.plantItemId = plantItemId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

 
    
    /**
	 * 获取 modelTypeName
	 * @return 返回 modelTypeName
	 */
	public String getModelTypeName() {
		return modelTypeName;
	}

	/**
	 * 设置 modelTypeName
	 * @param modelTypeName 对modelTypeName进行赋值
	 */
	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}
}