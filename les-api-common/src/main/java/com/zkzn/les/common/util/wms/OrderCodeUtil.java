package com.zkzn.les.common.util.wms;

import com.zkzn.les.common.util.date.DateUtil;
import com.zkzn.les.common.util.wms.RedisUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.zkzn.les.common.util.wms.Constants.BILL_TYPE;

public class OrderCodeUtil {
	@Resource
	private RedisUtil redisUtil;

	private static String lastDate = null;

	private static int num = 0;

	//上架前缀
	private static String SJ_PREFIX = "SJ";
	//盘点前缀
	private static String PD_PREFIX = "PD";
	//盘点前缀
	private static String BF_PREFIX = "BF";

	private static OrderCodeUtil orderCodeUtil;

	@PostConstruct
	public void init() {
		orderCodeUtil = this;
		orderCodeUtil.redisUtil = this.redisUtil;

	}

	/**
	 * .
	 *
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月15日
	 * @Description:生成订单号：年（四位）+月（两位）+日（两位）+6位流水号
	 */
	public static String crateOrderCode() {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMdd");
		if (lastDate == null) {
			lastDate = date;
		}
		if (!lastDate.equals(date)) {
			num = 1;
			lastDate = date;
		} else {
			num++;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = "" + num;
		int zerroNum = 6 - tempNum.length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(num);
		}

		return buffer.toString();
	}

	/**
	 * @return
	 * @params
	 * @Author Lty
	 * @date 2020/10/13
	 * @Description 调拨任务编号(DB + 年月日时分秒 + 4位流水号)
	 */
	public static String crateTransferSlipCode() {
		String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmmss");
		if (lastDate == null) {
			lastDate = date;
		}
		if (!lastDate.equals(date)) {
			num = 1;
			lastDate = date;
		} else {
			num++;
		}
		date = "DB" + date;
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = "" + num;
		int zerroNum = 4 - tempNum.length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(num);
		}

		return buffer.toString();
	}

	/**
	 * @param rkFlag 传2或者4
	 * @Description TD: 生成退补料任务号
	 * @Return java.lang.String
	 * @Author zhanglei
	 * @Date 2020/11/10 14:36
	 **/
	public static String crateTaskCodeOfTBLOrBL(String rkFlag) {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmmss");
		if (lastDate == null) {
			lastDate = date;
		}
		if (!lastDate.equals(date)) {
			num = 1;
			lastDate = date;
		} else {
			num++;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = "" + num;
		int zerroNum = 4 - tempNum.length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(num);
		}
		if (rkFlag.equals(BILL_TYPE[4])) {
			return "TBLZYRK" + buffer.toString();
		} else if (rkFlag.equals(BILL_TYPE[2])) {
			return "TLZYCK" + buffer.toString();
		}
		return "XXXXX" + buffer.toString();
	}

	/**
	 * @param
	 * @Description TD: 生成上架任务号
	 * @Return java.lang.String
	 * @Author sangsang
	 * @Date 2020/12/1 15:07
	 **/

	public static String getUpperTaskCode() {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMdd");

		StringBuffer buffer = new StringBuffer();
		buffer.append(date);

		Long taskNum = orderCodeUtil.redisUtil.hincrADay(SJ_PREFIX + "_TASK_NUM", 1L);
		int zerroNum = 4 - taskNum.toString().length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(taskNum);
		}
		return "SJ" + buffer.toString();
	}

	//总装立库入库任务
	public static String getZZUpperTaskCode() {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmmss");
		if (lastDate == null) {
			lastDate = date;
		}
		if (!lastDate.equals(date)) {
			num = 1;
			lastDate = date;
		} else {
			num++;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = "" + num;
		int zerroNum = 4 - tempNum.length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(num);
		}

		return "ZZLK" + buffer.toString();
	}

	//sap单据号任务
	public static String getSapTaskCode() {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmmss");
		if (lastDate == null) {
			lastDate = date;
		}
		if (!lastDate.equals(date)) {
			num = 1;
			lastDate = date;
		} else {
			num++;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = "" + num;
		int zerroNum = 4 - tempNum.length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(num);
		}

		return "ZZLK" + buffer.toString();
	}


	public static String getInventoryTaskCode() {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMdd");

		StringBuffer buffer = new StringBuffer();
		buffer.append(date);

		Long taskNum = orderCodeUtil.redisUtil.hincrADay(PD_PREFIX + "_TASK_NUM", 1L);
		int zerroNum = 4 - taskNum.toString().length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(taskNum);
		}
		return "PD" + buffer.toString();
	}


	public static String getScrappedTaskCode() {

		String date = DateUtil.getCurrentDateWithFormat("yyyyMMdd");

		StringBuffer buffer = new StringBuffer();
		buffer.append(date);

		Long taskNum = orderCodeUtil.redisUtil.hincrADay(BF_PREFIX + "_TASK_NUM", 1L);
		int zerroNum = 4 - taskNum.toString().length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(taskNum);
		}
		return "BF" + buffer.toString();
	}

	public static String getProductSapTaskCode(String str) {

		//String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmmss");

		String date = System.currentTimeMillis()+"";
		if (lastDate == null) {
			lastDate = date;
		}
		if (!lastDate.equals(date)) {
			num = 1;
			lastDate = date;
		} else {
			num++;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = "" + num;
		int zerroNum = 4 - tempNum.length();
		if (zerroNum > 0) {
			for (int i = 0; i < zerroNum; i++) {
				buffer.append("0");
			}
			buffer.append(num);
		}

		return str + buffer.toString();
	}
}
