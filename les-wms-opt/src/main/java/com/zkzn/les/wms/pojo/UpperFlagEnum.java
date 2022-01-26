package com.zkzn.les.wms.pojo;

public enum UpperFlagEnum {
	//flag 0 查询所有 1调拨单 2其它 3 生产上架
	// '上架来源 1:采购上架 2:生产退料上架 3:调拨上架 4:其他上架'
	ALL("0","所有未上架数据",""),
	SLIP("1","调拨单对应上架表的upperOrigin为3","3"),
	STOCK("2","调拨单对应上架表的upperOrigin为1","1"),
	PRODUCE("3","调拨单对应上架表的upperOrigin为2","2"),
	;



	private String parameter;
	private String paraphrase;
	private String tableField;


	/**
	 * @Description TD:
	 * @param parameter 参数
	 * @param paraphrase 转换中文释义
	 * @param tableField 数据库字段
	 * @Return
	 * @Author sangsang
	 * @Date 2021/1/10 1:14
	 **/
	UpperFlagEnum(String parameter, String paraphrase, String tableField) {
		this.parameter = parameter;
		this.paraphrase = paraphrase;
		this.tableField = tableField;
	}

	public String getParameter() {
		return parameter;
	}

	public String getParaphrase() {
		return paraphrase;
	}

	public String getTableField() {
		return tableField;
	}

	public static String getTableField(String parameter) {
		for (UpperFlagEnum e : UpperFlagEnum.values()) {
			if (e.getParameter().equals(parameter)) {
				return e.tableField;
			}
		}
		return null;
	}

	public static String toFlag(String tableField) {
		for (UpperFlagEnum e : UpperFlagEnum.values()) {
			if (e.getTableField().equals(tableField)) {
				return e.parameter;
			}
		}
		return null;
	}


}
