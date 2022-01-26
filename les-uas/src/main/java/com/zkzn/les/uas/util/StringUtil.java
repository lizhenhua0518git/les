package com.zkzn.les.uas.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 

public final class StringUtil {

	private static final String ZERO_STRING = "0";
	private StringUtil() {
		
	}
	
	/**.
	 * 判断是否为空
	 * 
	 * @param parme
	 * @return
	 */
	public static Boolean isBlankOrEmpty(String parme) {
		if (parme == null || parme.equals("") || parme.equals("null")) {
			return true;
		}
		return false;
	}
	
	/**.
	 * 功能描述: 字符串连接
	 * @param conChar 原字符串
	 * @param tmpList 字符串list
	 * @return 连接后的字符串
	 */
	public static String connectStr(String conChar, List<String> tmpList) {
		if (tmpList == null || tmpList.size() == 0) {
			return "";
		}
		String tmpString = "";
		for (String a : tmpList) {
			tmpString += (a + conChar);
		}
		return tmpString.substring(0, tmpString.length() - conChar.length());
	}
	/**.
	 * 验证一个字符串是否为空
	 * 
	 * @param str
	 * @return 如果为空返回真，如果不为空返回假
	 */

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
            return true;
        }

		String pattern = "\\S";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(str);
		return !m.find();
	}
	/**.
	 * 
	 * 功能描述：一个字符在另个一字符中出现的次数
	 * 作者：wangzhou
	 * 时间：2018年6月25日
	 * @param str
	 * @param ichar
	 * @return
	 */
	public static int appearStr(String ichar,String str){
		int num = 0;
		String [] arryStr = str.split(ichar);
		if(arryStr!=null){
			num = arryStr.length;
			if(!str.endsWith(ichar)) {
                num--;
            }
		}
		return num;
	}
	
	/**
	 * . 功能描述: 字符串长度校验
	 * 
	 * @param str
	 *            传入的字符串
	 * @param length
	 *            要求的长度
	 * @return 0 字符为空或者空字符串 1 合法 2 超长了 -1 短于要求的值
	 */
	public static int validateLength(String str, int length) {

		if (isEmpty(str)) { // 字符串为空
			return 0;
		} else if (length == str.length()) { // 长度合法
			return 1;
		} else if (length < str.length()) { // 字符串超长了
			return 2;
		} else { // 字符串短于要求的
			return -1;
		}

	}

	/**
	 * . 功能描述:检验日期格式是yyyyMMdd
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateDateStyle(String str) {
		if (isEmpty(str)) {
			return false;
		} else if (str.trim().length() == 8) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * . 功能描述:检验日期内容是否正确
	 * 
	 * @param sourceDate
	 *            日期
	 * @param format
	 *            日期格式 如 yyyyMMdd yyyy/MM/dd
	 * @return
	 */
	public static boolean validateDateContent(String sourceDate, String format) {
		if (sourceDate == null) {
			return false;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			dateFormat.setLenient(false);
			dateFormat.parse(sourceDate);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	 

	/**
	 * . 功能描述:如果为不为空和空字符串返回真，如果为空返回假
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * . 功能描述:只判断为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * 判断字符串是否为（空或只含空格）
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNullOrEmptyString(Object str)
	{
		if (str == null){
			return true;
		}
		if (str.toString().trim().length() == 0){
			return true;
		}
		return false;

	}

	/***************************************************************************
	 * 正整数验证
	 * 
	 * @param str
	 *            待验证字符串
	 * @return true 验证通过 false 验证失败
	 */
	public static boolean isValidInteger(String str) {
		boolean flag = false;
		Pattern p = Pattern.compile("^\\d*$");
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
		}
		return flag;
	}

	/***************************************************************************
	 * 整数验证(包括正整数与 负整数)
	 * 
	 * @param str
	 *            待验证字符串
	 * @return true 验证通过 false 验证失败
	 */
	public static boolean isValidNo(String str) {
		boolean flag = false;
		Pattern p = Pattern.compile("^-?\\d*$");
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
		}
		return flag;
	}

	/**
	 * 验证非负整数(正整数+0)
	 * 
	 * @param str
	 *            待验证字符串
	 * @return true 验证通过 false 验证失败
	 */
	public static boolean isValidNonNegative(String str) {
		boolean flag = false;
		Pattern p = Pattern.compile("^\\d+$");
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
		}
		return flag;
	}

	/**
	 * 验证非负整数(正整数+0)
	 * 
	 * @param str
	 *            待验证字符串
	 * @return true 验证通过 false 验证失败
	 */
	public static boolean isValidPositiveInteger(String str) {
		boolean flag = false;
		Pattern p = Pattern.compile("^\\d+$");
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
			if (ZERO_STRING.equals(str)) {
				flag = false;
			}
		}

		return flag;
	}

	/***************************************************************************
	 * 匹配英文字母(汉语拼音)
	 * 
	 * @param str
	 *            待匹配字符串
	 * @return true 匹配通过 false 匹配失败
	 */
	public static boolean isValidEnglish(String str) {
		boolean flag = false;
		Pattern p = Pattern.compile("^[A-Za-z]*$");
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
		}
		return flag;
	}

	/***************************************************************************
	 * 匹配英文字母 或者汉字，数字 过滤特殊字符
	 * 
	 * @param str
	 *            待匹配字符串
	 * @return true 匹配通过 false 匹配失败
	 */
	public static boolean isValidNonSpecialChar(String str) {
		boolean flag = false;
		Pattern p = Pattern.compile("^[A-Za-z\u4E00-\u9FA5\\d]*$");
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
		}
		return flag;
	}

	/***************************************************************************
	 * 验证结束时间是否大于开始时间 **注意：此方法必须先调用validateDateStyle 方法后调用 
	 * 
	 * @param startDate 
	 *            开始时间  格式 yyyyMMdd
	 * @param endDate
	 *            结束时间 格式 yyyyMMdd
	 * @return true 验证通过 false 验证失败
	 */
	public static boolean isValidTimeZone(String startDate, String endDate) {
		boolean flag = false;
		if (startDate != null && endDate != null) {
			if (validateDateStyle(startDate) && validateDateStyle(endDate)) // 格式验证，避免可能抛类型转换异常
			{
				flag = (Integer.parseInt(endDate) > Integer.parseInt(startDate));
			}
		}
		return flag;
	}

	/***************************************************************************
	 * 验证手机号码
	 * 
	 * @param telNo
	 *            电话号码字符串 130到139 和 150，152 ，157，158，159 ，186，189，187
	 * @return
	 */
	public static boolean isValidMobileNo(String mobileNo) {
		// 1、(13[0-9])|(15[02789])|(18[679]) 13段 或者15段 18段的匹配
		// 2、\\d{8} 整数出现8次
		boolean flag = false;
		// Pattern p = Pattern.compile("^(1[358][13567890])(\\d{8})$");
		Pattern p = Pattern.compile("^((13[0-9])|(15[02789])|(18[679]))\\d{8}$");
		Matcher match = p.matcher(mobileNo);
		if (mobileNo != null) {
			flag = match.matches();
		}
		return flag;
	}

	 

	/**
	 * 验证输入两位小数
	 * 
	 * @param str 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isDecimal(String str) {
		String regex = "^[0-9]+(.[0-9]{2})?$";
		Pattern pattern = Pattern.compile(regex);
		boolean flag = false;
		if (str != null) {
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		}
		return flag;
	}
	/**
	 * . 
	 * 功能描述:保留小数
	 * @param str
	 * @param digit  几位小数 1 一位小数 2 两位小数...
	 * @return
	 */
	public static String getDecimal(String str, int digit){
		double  d =Double.parseDouble(str);
		DecimalFormat decimalFormat = null;
		switch (digit) {
		case 1:
			decimalFormat = new DecimalFormat("#.0");
			break;
		case 2:
			decimalFormat = new DecimalFormat("#.00");
			break;
		case 3:
			decimalFormat = new DecimalFormat("#.000");
			break;
		case 4:
			decimalFormat = new DecimalFormat("#.0000");
			break;

		default:
			decimalFormat = new DecimalFormat("#.0");
			break;
		}
		
		return decimalFormat.format(d);
	}
	/**
	 * @author 罗旌海
	 * 把null改成""
	 * @param obj
	 * @return
	 */
	public static String null2String(Object obj) {
		String str=obj==null?"":obj+"";
		return str;
	}
	
	
	public static String null3String(Object obj) {
		String str="";
			if(obj!=null&& !"".equals(obj)&& !"NULL".equals(obj)&& !"null".equals(obj)){
				 str=obj.toString();
			}
		return str;
	}
	
	
	/**
	 * @author 罗旌海
	 * 把null改成0
	 * @param obj
	 * @return
	 */
	public static int null2Int(Object obj) {
		int num=obj==null?0:Integer.parseInt(String.valueOf(obj));
		return num;
	}
	
	/**
	 * @Title: get10SizeString 
	 * @author 胡志明 
	 * @date 2019年8月22日 下午1:05:19
	 * @Description: 批次号最多十位多余的截取掉，去掉空格，小写转大写
	 * @return String    返回类型 
	 * @throws
	 */
	public static String get10SizeString(Object obj){
        String str = "";
        if (!("").equals(obj)&&obj!=null){
        	str = obj.toString().toUpperCase();//小写字母转大写字母
        	str.replaceAll(" ","");//去除字符串空格
            if (str.length()>10) {
    			str = str.substring(0, 10);//截取前10个字符
    		}
        }
		return str;
	}
}
