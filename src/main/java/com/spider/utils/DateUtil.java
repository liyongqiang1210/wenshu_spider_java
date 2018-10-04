package com.spider.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Title: DateUtil
 * </p>
 * <p>
 * Description: 日期工具类
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月4日 上午9:34:17
 */
public class DateUtil {

	/**
	 * 获取两个日期之间的所有日期
	 * 
	 * @param startDate
	 *            开始日期,格式为(YYYY-MM-DD)
	 * @param endDate
	 *            结束日期,格式为(YYYY-MM-DD)
	 * @return
	 */
	public static List<String> getDays(String startDate, String endDate) {

		// 返回的日期集合
		List<String> days = new ArrayList<String>();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = dateFormat.parse(startDate);
			Date end = dateFormat.parse(endDate);

			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(start);

			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(end);
			tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
			while (tempStart.before(tempEnd)) {
				days.add(dateFormat.format(tempStart.getTime()));
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return days;
	}

	public static void main(String[] args) {

		List<String> days = DateUtil.getDays("2016-01-01", "2017-01-01");
		System.out.println(days);
	}
}
