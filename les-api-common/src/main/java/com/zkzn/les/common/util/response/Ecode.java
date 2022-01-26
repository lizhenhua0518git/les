package com.zkzn.les.common.util.response;



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
	NO_AREA("25","区域不存在"),
	AREA_ERROR("19","区域地标不正确!"),
	VERIFY_POSITION("77","仓位不存在(或者仓位已禁用)！"),
	UN_MAX_MUM("20","物料最大容量为空！"),
	verify_Carrier("21","扫描载具不正确"),
	verifyCarrier("22","仓位与载具匹配不正确，请重新扫描"),
	Audit("23","审核过账失败！"),
	MAINTAIN("24","请维护当前【库存地点】物料基础数据！"),
	AD_ADMIN_PASSWD_ERR("100", "密码有误"),
	USR_NOT_EXISTS("1050", "用户不存在"),
	USR_DISABLED("1051", "管理员账号不能修改"),
	USR_EXISTS("1052", "该用户已经存在，请重新输入"),
	UNDEFIND("-99","未找到相关信息！"),
	LOGIN_LOSE("1052","登录信息失效"),
	APPLIANCES_NUM_ERR("-1232","回收载具数量错误"),
	APPLIANCES_FLOW_NUM_ERR("-231","供应商载具回收流水数量错误"),
	INSPECT_RULE_ERROR("-1233","质检规则重复"),
	SUPPLIER_CODE_ERROR("-1234","发货方编码填写错误"),
	MATH_CAST_ERROR("-12345","数据转换异常"),
	WAREHOUSE_CODE_ISNULL("-1232","仓库信息为空"),
	SUBMIT_INVENTORY_DATA_APP("-101","盘点任务还未确认开始,不可提交盘点数据！"),
	CLIENT_EXIST("1502","客户已存在"),
	STORAGE_POSITION_EXIST("1501","库位已存在"),
	CARRIER_EXIST("1500","载具已存在"),
	FORBID_LOAD("50","行项目禁止卸货"),
	;


	private String code;
	private String msg;

	Ecode(String code, String msg) {
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
