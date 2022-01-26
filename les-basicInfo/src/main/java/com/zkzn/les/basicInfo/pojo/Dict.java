package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 * 
 *
 * 功能描述：数据字典类型实体类
 * @author wangzhou
 * 时间：2018年6月27日
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dict extends PageCondition{

	/**.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键id
	
	private String dictType;//字典类型编号
	
	private String dictName;//字典类型名称

	private String dictDesc;//字典类型描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
}
