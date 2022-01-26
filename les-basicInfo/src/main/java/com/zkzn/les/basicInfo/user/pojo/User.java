package com.zkzn.les.basicInfo.user.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends PageCondition {

	private static final long serialVersionUID = 1L;

	private String id;//用户Id
	private Integer userId;//用户Id
	private String userName;//用户名
	private String userPassword;//密码
	private String oldPassWord;//旧密码
	private String phone; //手机号码
	private String orgId;//所属组织ID
	private String orgCode;//所属组织编号
	private String orgName;//所属组织名称
	private String avatar;//用户头像
	private Integer status;//用户状态 0-禁用 1-启用
	private Date lastLoginTime;//最后一次登录时间
	private String loginAccount;//用户名(登陆账号)

}
