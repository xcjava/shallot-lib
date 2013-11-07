package com.gmail.xcjava.base.date;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

/**
 * <p>实现日期操作的工具类</p>
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 * @version 2.1
 */
public class DateUtil{
	
	
	/**
	* 取得指定月份的最后一天
	* @param strdate String
	* @return String
	*/
	public static Date getMonthEnd(Date date){
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.MONTH,1);
		calendar.add(java.util.Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}
	
	/**
	* 取得指定月份的最后一天
	* @param strdate String
	* @return String
	*/
	public static Date getMonthStart(Date date){
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,   1);
		return calendar.getTime();
	}
	
	/**
	 * java.sql.Date转java.util.Date
	 * @param date
	 * @return
	 */
	public static Date parseDate(java.sql.Date date) {
		return date;
	}

	/**
	 * java.util.Date转java.sql.Date
	 * @param date
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	/**
	* 返回年份
	* @param date 日期
	* @return 返回年份
	*/
	public static int getYear(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	* 返回月份
	* @param date 日期
	* @return 返回月份
	*/
	public static int getMonth(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	* 返回日份
	* @param date 日期
	* @return 返回日份
	*/
	public static int getDayOfMonth(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回星期几
	 * @param date
	 * @return 1-7:Calendar.SUNDAY,Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	} 
	
	/**
	* 返回小时
	* @param date 日期
	* @return 返回小时
	*/
	public static int getHour(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	* 返回分钟
	* @param date 日期
	* @return 返回分钟
	*/
	public static int getMinute(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	* 返回秒钟
	* @param date 日期
	* @return 返回秒钟
	*/
	public static int getSecond(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	* 返回毫秒
	* @param date 日期
	* @return 返回毫秒
	*/
	public static long getMillis(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取关于时间的数组
	 * @param date 日期
	 * @return 0：year 1：date 2：month 3：hour 4：min 5：sec
	 */
	public static int[] getDateData(Date date){
		int[] dateData = {0,0,0,0,0};
		java.util.Calendar now = java.util.Calendar.getInstance();
		now.setTime(date);
		dateData[0] = now.get(java.util.Calendar.YEAR);
		dateData[1] = now.get(java.util.Calendar.DAY_OF_MONTH);
		dateData[2] = now.get(java.util.Calendar.MONTH) + 1;
		dateData[3] = now.get(java.util.Calendar.HOUR);
		dateData[4] = now.get(java.util.Calendar.MINUTE);
		dateData[5] = now.get(java.util.Calendar.SECOND);
		return dateData;
	}
	
	/**
	 * 判断两个日期是否在同一周
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
			}
			else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
			}
			else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
		}
		return false;
	}
	
	/**
	 * 产生周序号
	 */
	public static String getSeqWeek(){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if(week.length()==1)week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR)); 
		return year+week;
	}
	
	/**
	 * 获得周几的日期
	 * @param date 日期
	 * @param dayOfWeek(1-7):Calendar.SUNDAY,Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY
	 * @return
	 */
	public static Date getDateByDayOfWeek(Date date,int dayOfWeek){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,dayOfWeek);
		return c.getTime();
	}
	

	/**
	 * 从身份证获取日期
	 * @param IDCardNum
	 * @return
	 */
	public static Calendar getDateFromIDCard(String IDCardNum){
		int year, month, day, idLength = IDCardNum.length();
		Calendar cal = Calendar.getInstance();

		if(idLength == 18){
			year = Integer.parseInt(IDCardNum.substring(6,10));
			month = Integer.parseInt(IDCardNum.substring(10,12));
			day = Integer.parseInt(IDCardNum.substring(12,14));
		}
		else if(idLength == 15){
			year = Integer.parseInt(IDCardNum.substring(6,8)) + 1900;
			month = Integer.parseInt(IDCardNum.substring(8,10));
			day = Integer.parseInt(IDCardNum.substring(10,12));
		}
		else {
			return null;
		}
		cal.set(year, month, day);
		return cal;
	}

	/**
	* 日期偏移
	* @param date 日期
	* @param offset 天数
	* @return 返回相加后的日期
	*/
	public static Date changeDay(Date date, int offset){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	/**
	 * 日期偏移
	 * @param calendar
	 * @param offset
	 * @return 返回相加后的Calendar
	 */
	public static Calendar changeDay(Calendar calendar, int offset){
		calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar;
	}

	/**
	* 日期相减
	* 
	* @param date 日期
	* @param date1 日期
	* @return 返回相减后的天数
	*/
	public static int getDiffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((date.getTime() - date1.getTime()) / (24 * 3600 * 1000));
	}

	/**
	 * 
	 * @param date 日期
	 * @param date1 日期
	 * @return 返回相减后的天数
	 */
	public static int getDiffDate(Calendar date, Calendar date1) {
		return getDiffDate(date.getTime(), date1.getTime());
	}
	
	/**
	* 日期相加
	* 
	* @param date 日期
	* @param day 天数
	* @return 返回相加后的日期
	*/
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	
	/**
	 * 日期相加
	 * @param date		开始日期
	 * @param millis	毫秒数
	 * @return
	 */
	public static java.util.Date addMillis(java.util.Date date, long millis) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + millis);
		return c.getTime();
	}
	
	/**
	 * 日期加月份
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date addMonth(java.util.Date date, int month) {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.MONTH, 1);
		return calendar.getTime();
	}

	/**
	* 字符串转日期
	* 
	* @param dateStr 字符型日期
	* @param formatStr 格式
	* @return 返回日期
	*/
	public static java.util.Date parseDate(String dateStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	* 以指定的格式来格式化日期
	* @param date Date
	* @param format String
	* @return String
	*/
	public static String formatDate(java.util.Date date,String format){
		String result = "";
		if(date != null)
		{
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 获取某天的下i天的日期
	 * @param date
	 * @param i
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, int i,String format) {
		
		DateFormat df = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, i);
		return df.format(calendar.getTime());
	}

	/**
	 * 字符串转日期
	 * @param dateStr 日期字符串
	 * @param format 格式
	 * @return
	 */
	public static java.sql.Date parseSqlDate(String dateStr, String format) {
		java.util.Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}

	/**
	 * 字符串转时间
	 * @param dateStr 日期字符
	 * @param format 格式
	 * @return
	 */
	public static java.sql.Timestamp parseTimestamp(String dateStr,String format) {
		java.util.Date date = parseDate(dateStr, format);
		if (date != null) {
			long t = date.getTime();
			return new java.sql.Timestamp(t);
		} else
			return null;
	}
	
	/**
	 * 是否是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, 2, 1);
		calendar.add(Calendar.DATE, -1);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 29) {
			return true;
		} else {
			return false;
		}
	} 
	
	/**
	 * 比较时间
	 * @param time1 时间1,格式HH:mm:ss
	 * @param time2 时间2,格式HH:mm:ss
	 * @return 1:时间1>时间2;0:时间1=时间2;-1:时间1<时间2
	 */
	public static int compareTime(String time1, String time2){
		
		Date newDate1 = parseDate("1990-01-01 " + time1, "yyyy-MM-dd HH:mm:ss");
		Date newDate2 = parseDate("1990-01-01 " + time2, "yyyy-MM-dd HH:mm:ss");
		if(getMillis(newDate1) - getMillis(newDate2) > 0)
			return 1;
		else if(getMillis(newDate1) - getMillis(newDate2) < 0)
			return -1;
		return 0;
	}
	
	/**
	 * 计算时间是否在时间段内,含时间段头尾边界
	 * @param time 时间,格式HH:mm:ss
	 * @param beginTime 开始时间,格式HH:mm:ss
	 * @param endTime 结束时间,格式HH:mm:ss
	 * @return 
	 */
	public static boolean isTimeBetween(String time, String beginTime, String endTime){
		
		Date newDate = parseDate("1990-01-01 " + time, "yyyy-MM-dd HH:mm:ss");
		Date newDate1 = parseDate("1990-01-01 " + beginTime, "yyyy-MM-dd HH:mm:ss");
		Date newDate2 = parseDate("1990-01-01 " + endTime, "yyyy-MM-dd HH:mm:ss");
		
		if((getMillis(newDate) - getMillis(newDate1) >= 0) && (getMillis(newDate) - getMillis(newDate2) <= 0))
			return true;
		else
			return false;
		
	}
	
	/**
	 * 时间转成字符串
	 * @param date 时间
	 * @param format 字符串格式
	 * @return
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {}
		return result;
	}
	
	/**
	 * 获取两个日期中间的所有日期
	 * @param d1  开始日期
	 * @param d2 结束日期
	 * @return
	 */
	public static GregorianCalendar[]  getBetweenDate(String d1,String d2){   
	        Vector<GregorianCalendar> v=new Vector<GregorianCalendar>();   
	        SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");   
	        GregorianCalendar gc1=new GregorianCalendar(),gc2=new GregorianCalendar();   
	        try {
				gc1.setTime(sdf.parse(d1));
				gc2.setTime(sdf.parse(d2)); 
				 do{   
			           GregorianCalendar gc3=(GregorianCalendar)gc1.clone();   
			           v.add(gc3);   
			           gc1.add(Calendar.DAY_OF_MONTH, 1);                
			     }while(!gc1.after(gc2));  
			} catch (Exception e) {
				e.printStackTrace();
			}   
	        return v.toArray(new GregorianCalendar[v.size()]);   
	} 
	
	/**
	 * 计算两个日期相差的月数
	 * @param sourceDate
	 * @param targetDate
	 * @return
	 */
	public static int getMonthOffset(Date sourceDate, Date targetDate){
		
		int sourceMonth = getMonth(sourceDate);
		int sourceYear = getYear(sourceDate);
		
		int targetMonth = getMonth(targetDate);
		int targetYear = getYear(targetDate);
		
		return (sourceYear - targetYear) * 12 + sourceMonth - targetMonth;
		
	}
	
	/*public static void main(String[] args) {
		String str = format(new Date(), "HH:mm:ss");
		String str1 = "09:55:31";
		String str2 = "15:59:59";
		System.out.println(isTimeBetween(str,str1,str2));
	}*/
	
	
}