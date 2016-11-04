package com.yihu.cwd.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	public static final String HH_MM = "HH:mm";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YY = "yy";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 字符串转时间格式
	 */
	public static Date strToDate(String strDate) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		else{
			int length = strDate.length();
			if(strDate.contains("-"))
			{
				if(length == 10)
				{
					 return strToDate(strDate,YYYY_MM_DD);
				}
				else if(length == 19)
				{
					return strToDate(strDate,YYYY_MM_DD_HH_MM_SS);
				}
			}
			else{
				if(length == 8)
				{
					return strToDate(strDate,YYYYMMDD);
				}
				else if(length == 14)
				{
					return strToDate(strDate,YYYYMMDDHHMMSS);
				}
			}
		}

		return null;
	}

	/**
	  * 获取现在时间
	  * 
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(dateString, pos);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		String dateString = formatter.format(currentTime);
		return strToDate(dateString, YYYY_MM_DD);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return formatter.format(currentTime);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(String format) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(currentTime);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		return formatter.format(currentTime);
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat(HH_MM_SS);
		Date currentTime = new Date();
		return formatter.format(currentTime);
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	public static Date strToDateShort(String strDate) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		if (dateDate == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return formatter.format(dateDate);
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrShort(Date dateDate) {
		if (dateDate == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		return formatter.format(dateDate);
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 *
	 * @param dateDate
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date dateDate, String format) {
		if (dateDate == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(dateDate);
	}

	/**
	 * 将短时间格式字符串转换为时间
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate, String format) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}



	public static Date strToDateAppendNowTime(String strDate, String format) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		strDate += " " + getTimeShort();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	/**
	 * 得到现在时间
	 *
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 *
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 *
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 *
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 *
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(YYYY_MM_DD);
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几分钟的时间,nowdate为时间,delay为前移或后延的分钟数
	 */
	public static Date getNextMin(Date date, int delay) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE, delay);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
			String mdate = "";
			Date d = strToDate(nowdate, YYYY_MM_DD);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	// public static String getNextDay(Date d, int delay) {
	// try {
	// SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
	// String mdate = "";
	// long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
	// d.setTime(myTime * 1000);
	// mdate = format.format(d);
	// return mdate;
	// } catch (Exception e) {
	// return "";
	// }
	// }

	public static String getNextDay(Date d, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, days);
		return dateToStrShort(c.getTime());
	}

	public static String getNextMonth(Date d, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, months);
		return dateToStrShort(c.getTime());
	}

	public static String getNextYear(Date d, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.YEAR, year);
		return dateToStrShort(c.getTime());
	}

	/**
	 * 判断是否润年
	 *
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate, YYYY_MM_DD);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 *
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * 获取一个月的最后一天
	 *
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 *
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
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 *
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 *
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateUtil.strToDate(sdate, YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 *
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate, YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtil.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat(YYYY_MM_DD);
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 返回两个日期相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";
		// 得到这个月的1号是星期几
		Date date = DateUtil.strToDate(sdate, YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtil.getNextDay(sdate, 1 - u);
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k 表示是取几位随机数，可以自己定
	 */
	public static String getNo(int k) {
		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 根据用户生日计算年龄
	 */
	public static int getAgeByBirthday(Date birthday) {
		try {
			Calendar cal = Calendar.getInstance();
			if (cal.before(birthday)) {
				return 0;
			}
			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

			cal.setTime(birthday);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			int age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					age--;
				}
			}
			return age;
		} catch (Exception e) {
			return 0;
		}
	}

	public static void main(String[] args) {
//		String hour = "12:22:12";
//		System.out.println(Time.valueOf(hour));
//		System.out.println(getNowDate());
//
		System.out.println(getNextYear(new Date(), -65));
	}
}
