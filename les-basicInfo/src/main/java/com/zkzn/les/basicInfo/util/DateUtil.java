 package com.zkzn.les.basicInfo.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DateUtil {

    
    private DateUtil() {
        
    }
    
	/**
	 * 功能描述: 指定时间转化成指定格式字符串
	 * @param date 时间  
	 * @param format 格式 如：yyyy-MM-dd
	 * @return 转换后的时间格式字符串
	 */
	public static String getFormatDate(Date date, String format){
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		String formateDate=myFormatter.format(date);
		return formateDate;
	}
	
	/**
	 * 功能描述: 指定时间字符串转化成指定格式时间 
	 * @param dateStr 时间字符串
	 * @param format  格式 默认为yyyy-MM-dd
	 * @return
	 */
	public static Date formatStrToDate(String dateStr, String format){
		 
		if(format == null){
			format = "yyyy-MM-dd";
		} 
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		return date;
	}
	
	/**
	 * 功能描述: 指定时间字符串转化成指定格式时间 
	 * @param dateStr 时间字符串
	 * @param format  格式 默认为yyyy-MM-dd
	 * @return
	 */
	public static String formatStrToDateStr(String dateStr, String format){
		int  flag = 0;
		if(format == null){
			format = "yyyy-MM-dd";
		}else if(Objects.equals(format, "yyyy年MM月dd日")) {
			format = "yyyy-MM-dd";
			flag =1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
			dateStr  = getFormatDate(date, format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(flag==1) {
			String[]  array = dateStr.split("-");
			dateStr =array[0]+"年"+array[1]+"月"+array[2] +"日";
		}
		return dateStr;
	}
	
	/**
	 * 功能描述: 指定时间向前/后多少天
	 * @param date 时间
	 * @param d 天数; 正数时向后；负数时向前
	 * @return  向前/后多少天的时间
	 */
	public static String getDifferDay(Date date, int d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, d);
		date = calendar.getTime();
		//DateFormat df = DateFormat.getDateInstance("yyyy-MM-dd");
		String dateString =getFormatDate(date,"yyyy-MM-dd");
		return dateString;
	}
	
	/**
	 * 功能描述: 指定时间向前/后多少小时
	 * @param date 时间
	 * @param d 天数; 正数时向后；负数时向前
	 * @param fmt 返回时间格式；默认：yyyy-MM-dd hh
	 * @return  向前/后多少天的时间
	 */
	public static String getDifferHour(Date date, int d,String fmt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, d);
		date = calendar.getTime();
		if(fmt == null) {
			fmt = "yyyy-MM-dd hh";
		}
		String dateString = getFormatDate(date,fmt);
		return dateString;
	}
	
	/**
	 * 功能描述: 指定时间向前/后多少小时
	 * @param date 时间
	 * @param d 天数; 正数时向后；负数时向前
	 * @param fmt 返回时间格式；默认：yyyy-MM-dd hh
	 * @return  向前/后多少天的时间
	 */
	public static Date getDifferMinute(Date date,int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	/**
	 * 功能描述: 两个时间相差距离多少天多少小时多少分多少秒 
	 * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
	 * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
	 * @return 返回值为：xx天xx小时xx分xx秒 
	 */
	public static String getDistanceTime(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0; 
        String fs = "";
        try {  
            one = df.parse(str1.toString());  
            two = df.parse(str2.toString());  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ; 
            
            if(time1<time2) {  
                diff = time2 - time1;  
                fs = "-";
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        String str = "";
        if(fs!=null){
        	str += fs;
        }
        if(day!=0){
        	str += day + "天";
        }
        if(hour!=0){
        	str += hour + "小时";
        }
        if(min!=0){
        	str += min + "分";
        }
        if(sec!=0){
        	str += sec + "秒";
        }
        return  str;  
    } 
	
	/**
	 * 功能描述: 获取时间段中的每一分钟
	 * @param dBegin 开始时间
	 * @param dEnd 结束时间
	 * @return 时间段中的分钟
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd){  
		List<Date> lDate = new ArrayList<Date>();  
		lDate.add(dBegin);  
        Calendar calBegin = Calendar.getInstance();  
        //使用给定的 Date 设置此 Calendar 的时间  
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后  
        while (dEnd.after(calBegin.getTime())){  
        	// 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
        	calBegin.add(Calendar.SECOND, 1);  
        	lDate.add(calBegin.getTime());  
        }  
        return lDate;  
	} 
	
	/**
	 * 功能描述:计算时间差值
	 * @param str1 开始时间
	 * @param str2 结束时间
	 * @param type 1 天 2 小时 3 分钟
	 * @return
	 */
	public  static int dateTimeMinus(String fromDate, String toDate,int type){
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long from = 0L;
		long to = 0L;
		try {
			from = simpleFormat.parse(fromDate).getTime();
			 to = simpleFormat.parse(toDate).getTime();
		} catch (ParseException e) {
			 
			e.printStackTrace();
		}
        int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        int hours = (int) ((to - from)/(1000 * 60 * 60));
		int minutes = (int) ((to - from)/(1000 * 60));
		if( type == 1){
			return days;	
		}else if(type == 2){
			return hours;	
		}else{
			return minutes;	
		}
		
	}
	
	/**
	 * 获取当前时间
	 * 格式如yyyy-MM-dd,yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateWithFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 功能描述:比较时间大小  1 第一个时间大于第二时间  -1 第一个时间小于第二时间 0 相等
	 * @param begindate
	 * @param endDate
	 * @param format
	 * @return
	 */
    public static int compareDate(String beginTime, String endTime,String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(beginTime);
            Date dt2 = df.parse(endTime);
            if (dt1.getTime() > dt2.getTime()) {
                
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
 
    /**
     * 获取某个日期开始时间
     * 叶正楠
     * 2018年10月18号
     * @param date
     * @return
     */
    public static Date getDayStartTime(Date date) {
	Calendar calendar = Calendar.getInstance();
    if (null != date)
        calendar.setTime(date);
    	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * 获取某个日期结束时间
     * 叶正楠
     * 2018年10月18号
     * @param date
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
	    Calendar calendar = Calendar.getInstance();
	    if (null != d){
	        calendar.setTime(d);
	    	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	    	calendar.set(Calendar.MILLISECOND, 999);
	    }
    	return new Timestamp(calendar.getTimeInMillis());
    }
    
	/**
	 * 获取本周的开始时间
	 * 叶正楠
	 * 2018年10月18号
	 * @return
	 */    
	public static Date getBeginDayOfWeek() {
		Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
	    if (dayofweek == 1) {
	        dayofweek += 7;
	    }
	    cal.add(Calendar.DATE, 2 - dayofweek);
	    return getDayStartTime(cal.getTime());
	}
    
	/**
	 * 获取本周的结束时间
	 * 叶正楠
	 * 2018年10月18号
	 * @return
	 */ 
    public static Date getEndDayOfWeek() {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(getBeginDayOfWeek());
	    cal.add(Calendar.DAY_OF_WEEK, 6);
	    Date weekEndSta = cal.getTime();
	    return getDayEndTime(weekEndSta);
    }
	
    /**
	 * 获取上周的开始时间
	 * 叶正楠
	 * 2018年10月18号
	 * @return
	 */
    public static Date getBeginDayOfLastWeek() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek - 7);
		return getDayStartTime(cal.getTime());
	}
    
    /**
	 * 获取上周的结束时间
	 * 叶正楠
	 * 2018年10月18号
	 * @return
	 */ 
    public static Date getEndDayOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfLastWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date weekEndSta = cal.getTime();
		return getDayEndTime(weekEndSta);
	}
    
	/**
	 * 获取当前年、月、上月、本周、上周
	 * 叶正楠
	 * 2018年10月18号
	 * @return
	 */
	public static Map<String,Object> getYearAndMonthAndWeek(){
		Map<String,Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);		//本年
		int thisMonth = cal.get(Calendar.MONTH)+1;	//本月
		int lastMonth = cal.get(Calendar.MONTH);	//上月
		
		map.put("thisYear", thisYear);
		String thisMonthStr = "";
		if(thisMonth<10){
			thisMonthStr = "0"+lastMonth;
			map.put("thisMonth", thisYear+"-"+thisMonthStr);	//本月
		}else{
			map.put("thisMonth", thisYear+"-"+thisMonth);
		}
		
		String lastMonthStr = "";
		if(lastMonth<10){
			lastMonthStr = "0"+lastMonth;
			map.put("lastMonth", thisYear+"-"+lastMonthStr);	//上月
		}else{
			map.put("lastMonth", thisYear+"-"+lastMonth);
		}
		
		map.put("thisWeekBegin", getBeginDayOfWeek());		//本周开始日期
		map.put("thisWeekEnd", getEndDayOfWeek());			//本周结束日期
		map.put("lastWeekBegin", getBeginDayOfLastWeek());	//上周开始日期
		map.put("lastWeekEnd", getEndDayOfLastWeek());		//上周结束日期
		return map;
	}
	/**
	 * . 
	 *
	 * 功能描述:获取一周开始到结束的list集合
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2019-04-17 09:08
	 *
	 */
	public static List<Date> getWeekAllDay(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}
	/**
	 * . 
	 *
	 * 功能描述:获取某一天的前(后)num天
	 *  flag 1 向明天  -1 向昨天
	 * @param dBegin
	 * @param num
	 * @param flag
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2019-04-24 17:53
	 *
	 */
	public static List<String> getSevenDay(Date dBegin, int num, int flag) {
		List<String> lDate = new ArrayList<>();
		String dateValue = "";

		if (flag > 0) {
			for (int i = 0; i < num; i++) {
				dateValue = getDifferDay(dBegin, i + 1);
				lDate.add(dateValue);
			}
		} else {
			for (int i = 0; i > -num; i--) {
				dateValue = getDifferDay(dBegin, i - 1);

				lDate.add(dateValue);
			}
		}

		return lDate;
	}
	/**
	 * 将时间+sec秒
	 * @param str 格式只能是:130159 时分秒
	 * @param sec
	 * @return
	 */
	public static String parseTimeStrAndAddSec(String timeStr,int sec){
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		try {
			 long time=sdf.parse(timeStr).getTime()+sec*1000;
			 Date date=new Date();
			 date.setTime(time);
			 return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long getDistanceHour(Date dateOne,Date dateTwo){
		 long time1 = dateOne.getTime();  
         long time2 = dateTwo.getTime();  
         long diff ; 
         
         if(time1<time2) {  
             diff = time2 - time1;  
         } else {  
             diff = time1 - time2;  
         }  
         long day = diff / (24 * 60 * 60 * 1000); 
         long hour = (diff / (60 * 60 * 1000) - day * 24);  
         
         return hour;
	}
	
	/**
	 * . 
	 *
	 * 功能描述:获取当年第一天
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 * @author  刘松山  
	 *
	 *时间:  2020-03-02 14:19
	 *
	 */
	public static String getThisYear(String date)   {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
	    Date time;
	    Date startTime;
	    String dates = "";
		try {
			time = format3.parse(date);
			 String time1 = format3.format(time);
			 startTime = format.parse(time1 + "-01-01");
			 dates = format.format(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    return dates;
	}
}
 
