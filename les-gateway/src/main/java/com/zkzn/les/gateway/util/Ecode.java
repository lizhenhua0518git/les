package com.zkzn.les.gateway.util;

public enum Ecode {
	/**.
	 * 公共错误码 0 - 10
	 * **/
	FAIL("-1","失败！"),
	SUCCESS("0", "成功!"),
	PARAM("1", "参数不能为空"),
	INTER("2", "服务器正忙，请稍后再试！"),
	TIMEOUT("3", "调用外部接口超时"),
	EXTERNAL("4", "外部接口错误"),
	RESRC("5", "接口不存在"),
	AUTH("6", "授权失败"),
	PARAMNUMBER("7","参数个数不对"),
	PARAMNAMEERROR("8","参数名错误"),
	DATE_NOT_EXIST("9","数据不存在！"),
	UPLOAD_FAIL("10","文件上传失败"),
	CLOSE("11","关闭行项目"),
	OVERSTOCK("12","超储"),
	OVERSTOCK_UNTREATED("13","超储未处理"),
	UNLOADED("14","前面有号未卸货！"),
	REPEAT("15","重复排号！"),
	isSame("16","非同一操作人！"),
	SECONDCONFIRM("17","二次确认不成功！"),
	POSTING_FAILED("18","过账失败！"),
	AREA_ERROR("19","区域地标不正确!"),
	UN_MAX_MUM("20","物料最大容量为空！"),
	verify_Carrier("21","扫描载具不正确"),
	verifyCarrier("22","仓位与载具匹配不正确，请重新扫描"),
	Audit("23","审核过账失败！"),
	MAINTAIN("24","请维护当前【库存地点】物料基础数据！"),
	AD_ADMIN_PASSWD_ERR("100", "密码有误"),
	USR_NOT_EXISTS("1050", "用户不存在"),
	USR_DISABLED("1051", "用户已禁用"),
	UNDEFIND("-99","到货通知单不存在！"),
	LOGIN_LOSE("1052","登录信息失效"),
	FORBID_LOAD("50","行项目禁止卸货"),
    MATERIAL_TYPE_ERROR("25","该物料存储类型未维护"),
	STORAGE_NULL_ERROR("001","仓位信息不存在！"),
	ACCESS_DENIED_ERROR("401","登陆信息失效"),
	ACCESS_ROLE_ERROR("402","无权限访问");
	private String code;
	private String msg;
	
	private Ecode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**.
	 * 重写父类方法，用于直接获取code 字符串
	 * 
	 * @return string
	 */
	public String toString() {
		return this.code;
	}

	/**.
	 * 新增
	 * 
	 * @return string
	 */
	public String msg() {
		return this.msg;
	}
	
	/**.
	 * 
	 * @return
	 */
	public String code(){
		return this.code;
	}

	/**.
	 * 与成功比较
	 * 
	 * @return
	 */
	public boolean compareSucc() {
		return Integer.parseInt(this.code) == Integer.parseInt(SUCCESS.code);
	}
	
	/**.
	 * 与 int 或者 string 串比较
	 * @param obj
	 * @return
	 */
	public boolean compare(Object obj) {
		Integer target = null;
		if("java.lang.String".equals(obj.getClass().getName())){
			target = Integer.parseInt(obj.toString());
		}else if("java.lang.Integer".equals(obj.getClass().getName())){
			target = (Integer)obj;
		}else if(Ecode.class.isInstance(obj)){
			Ecode tmp= (Ecode)obj;
			target = Integer.parseInt(tmp.code);
		}
		
		if(target == null){
			try {
				throw new Exception("method only support compare: \n"
						+ " TEcode String Integer int");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return Integer.parseInt(this.code) == target;
	}
}
