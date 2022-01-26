package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 * 
 *
 * 功能描述：rabbitmq配置信息实体类
 * @author wangzhou
 * 时间：2018年7月23日
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RabbitSetting extends PageCondition{

	/**.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//主键
	
	
	private String exchangeName;//路由名称
	
	private String queueName;//队列名称
	
	private String routingKey;//监听key值
	
	private String constructorArg;//构造参数，多个参数用逗号分开
	
	private String property;//配置信息
	
	private String remark;//备注
	
	private String code;//编码
	
	private int status;//0-禁用 1-启用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConstructorArg() {
		return constructorArg;
	}

	public void setConstructorArg(String constructorArg) {
		this.constructorArg = constructorArg;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
