package com.kingsoft.lcgl.business.common.util;


import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * jdk  时间与时间戳转换工具类
 * Created by yangdiankang on 2017/11/28.
 */
public class TimeUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    private static final String TimeType1 = "yyyy-MM-dd";
    private static final String TimeType2 = "yyyyMMdd";
    private static final String TimeType3 = "yyyy-MM-dd HH:mm:ss";
    private static final String TimeType4 = "yyyy年MM月dd日 HH点mm分";

    /**
     * string  转时间戳
     * @param time
     * @param type
     * @return
     */
    public static Long getLongByStr(String time, String type){
        try {
            SimpleDateFormat format =   new SimpleDateFormat( type );
            Date date = format.parse(time);
            return date.getTime()/1000;
        }catch (Exception e){
            logger.info("转换时间戳报错"+"-------"+e);
        }
     return null;
    }
    /**
     * 时间戳转String
     * @param time
     * @param type
     * @return
     */
    public static String getStrByLong(Long time, String type){

        SimpleDateFormat sdf = new SimpleDateFormat(type);
        if(time.toString().length()<13){
            return sdf.format(new Date(time*1000));
        }else {
            return sdf.format(new Date(time));
        }
    }

    /**
     * string  转Date
     * @param time
     * @param type
     * @return
     */
    public static Date getDateByStr(String time, String type){
        try {
            SimpleDateFormat format =   new SimpleDateFormat( type );
            Date date = format.parse(time);
            return date;
        }catch (Exception e){
            logger.info("转Date报错"+"-------"+e);
        }
        return null;
    }

    /**
     * 时间戳转Date
     * @param time
     * @return
     */
    public static Date getDateByLong(Long time){
        if(time.toString().length() <13){
            return new Date(time*1000);
        }else {
            return new Date(time);
        }
    }
}
