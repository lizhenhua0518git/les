package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 *
 * @author wangzhou
 * 字典表 实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DictItems extends PageCondition {

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年3月31日上午9:12:48
	 * 功能描述:
	 */
	private static final long serialVersionUID = 3954383025366100633L;

	private String id;//主键id

	private String dictTypeId;//数据字典类型id

	private String itemName;//数据值名称

	private String itemValue;//数据值

	private String itemDesc;//数据描述

	private Integer sortNo;//当前数据类型的排序号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
