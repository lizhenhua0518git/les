package com.zkzn.les.common.util.lang;
import com.google.common.base.Joiner;
import com.zkzn.les.common.util.date.DateUtil;
import com.zkzn.les.common.util.str.StrUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BatchCodeUtil {

	
	private static String lastDate = null;
	
	private static int num = 0;
	//连接器
	 private static final Joiner joiner = Joiner.on("-").skipNulls();
	private static final String CBZX = "CBZX";
	private static  final String QUALITY = "WWCK";
	/**
	 * . 
	 *
	 * 功能描述:生成批次号：年（四位）+月（两位）+日（两位）+时分（四位）+4位流水号
	 * 
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-09-01 17:07
	 *
	 */
	public static String crateBatchCode(){
		
		String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmm");
		if(lastDate==null){
			lastDate = date;
		}
		if(!lastDate.equals(date)){
			num = 1;
			lastDate = date;
		}else{
			
			num++;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(date);
		String tempNum = ""+num;
		int zerroNum = 4 - tempNum.length();
		if(zerroNum>0){
			for(int i=0;i<zerroNum;i++){
				buffer.append("0");
			}
			buffer.append(num);
		}
		
	
		return buffer.toString();
	}

	/***
	 * @Discription: 获取批次号对应的时间
	 * @param （四位）+月（两位）+日（两位）+时分（四位）+4位流水号
	 * @return java.util.Date
	 * @Author: zhanglei on 2020/9/23 13:55
	 */
	public static Date getBatchNoDate(String batchNo) throws ParseException {
		if (StrUtil.isEmpty(batchNo) || batchNo.length()<8) {
			return null;
		}
		//只获取到八位时间序列号
		String dateString = batchNo.substring(0, 9);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = dateFormat.parse(dateString);//工具类异常抛出,不进行捕获处理
		return date;
	}

	public static String crateBatchCodeCBZX(String busiType){
		String date = DateUtil.getCurrentDateWithFormat("yyyyMMddHHmmss");
		String code = null;
		if (StrUtil.isEmpty(busiType) || "5".equals(busiType) ||"7".equals(busiType)) {
			code = joiner.join(CBZX, date).toString();
		}else if("9".equals(busiType)) {
			code = joiner.join(QUALITY, date).toString();
		}else if ("11".equals(busiType)){
			code = joiner.join(QUALITY, date).toString();
		}
		return code;
	}
}
