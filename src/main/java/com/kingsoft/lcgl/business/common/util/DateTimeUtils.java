package com.kingsoft.lcgl.business.common.util;

import org.joda.time.DateTime;

import java.util.Date;


public class DateTimeUtils {

    public static final Long DAY = 1L;
    public static final Long WEEK = 2L;
    public static final Long MONTH = 3L;
	

    /**
     * 取得开始的时间戳
     * @param date
     * @param timeType
     * @return
     */
    public static Long getStartMillTime(Date date, long timeType) {

		DateTime time = new DateTime(date);

		DateTime endMillTime;
		if(timeType == 3){
			endMillTime = time.dayOfMonth().withMinimumValue().withTime(0, 0, 0, 0);
		}else if(timeType == 2){
			endMillTime = time.dayOfWeek().withMinimumValue().withTime(0, 0, 0, 0);
		}else {
			endMillTime = time.withTime(0, 0, 0, 0);
		}
		return  endMillTime.getMillis()/1000;
    }


	/**
	 * 取得结束毫秒时间戳
	 * @param date
	 * @return
	 */
	public static Long getEndMillTime(Date date,long timeType) {
		DateTime time = new DateTime(date);
		DateTime endMillTime;

		if(timeType == 3){
			endMillTime = time.dayOfMonth().withMaximumValue().withTime(23, 59, 59, 999);
		}else if(timeType == 2){
			endMillTime = time.dayOfWeek().withMaximumValue().withTime(23, 59, 59, 999);
		}else {
			endMillTime = time.withTime(23, 59, 59, 999);
		}
		return  endMillTime.getMillis()/1000;
	}

	/**
	 * 获取天
	 * @param date
	 * @return
	 */
	public static String getDayByDate(Date date){
		DateTime time = new DateTime(date);

		return String.valueOf(time.getDayOfMonth());
	}
	/**
	 * 获取月
	 * @param date
	 * @return
	 */
	public static String getMonthByDate(Date date){
		DateTime time = new DateTime(date);

		return String.valueOf(time.getMonthOfYear());
	}

}
