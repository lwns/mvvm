package com.core.op.lib.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @description 日期工具类
 * @author   Tp_yang
 * @createDate 2015-1-22
 * @version  1.0
 */
public class DateUtil {
	private static Calendar calendar = Calendar.getInstance();
	private static SimpleDateFormat formatYearCn = new SimpleDateFormat("yyyy年MM月dd日",Locale.getDefault());
	public static final long SECOND = 1000L;
	public static final long MINUTE = 60000L;
	public static final long HOUR = 3600000L;
	public static final long DAY = 86400000L;
	public static final long LOCAL_TIME = System.currentTimeMillis();

	public static String getFormatTime(String time) {
		long mTime = Long.parseLong(time);
		Calendar.getInstance().setTimeInMillis(mTime);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		return year + "年" + month + "月" + date + "日";
	}

	public static String getFormatTime1(String time) {
		long mTime = Long.parseLong(time);
		Date currentTime = new Date(mTime);
		String dateString = formatYearCn.format(currentTime);
		return dateString;
	}

	public static String getCurrentTime() {
		Date currentTime = new Date(Calendar.getInstance().getTimeInMillis());
		String dateString = formatYearCn.format(currentTime);
		return dateString;
	}

	public static String getCurrentTime(String format) {
		Date currentTime = new Date(Calendar.getInstance().getTimeInMillis());
		String dateString = new SimpleDateFormat(format).format(currentTime);
		return dateString;
	}

	public static String getYesterdayTime() {
		Date currentTime = new Date(Calendar.getInstance().getTimeInMillis() - 86400000L);
		String dateString = formatYearCn.format(currentTime);
		return dateString;
	}

	public static String getTemorrowTime() {
		Date currentTime = new Date(Calendar.getInstance().getTimeInMillis() + 86400000L);
		String dateString = formatYearCn.format(currentTime);
		return dateString;
	}
}
