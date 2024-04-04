package com.mr.clock.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author orane
 * @create 2024/3/31 17:00
 * @Description 日期时间工具类
 **/

public class DateTimeUtil {
    /**
     * @create 2024/3/31 17:01
     * @Description 获取当前时间字符串
     **/
    public static String timeNow(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    /**
     * @create 2024/3/31 17:04
     * @Description 获取当前日期字符串
     **/
    public static String dateNow(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    /**
     * @create 2024/3/31 17:05
     * @Description 获取当前日期时间字符串
     **/
    public static String dateTimeNow(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    /**
     * @create 2024/3/31 17:11
     * @Description 获取由当前年、月、日、时、分、秒数字所组成的数组
     **/
    public static Integer[] now(){
        Integer now[] = new Integer[6];
        Calendar c = Calendar.getInstance();            //日历对象
        now[0] = c.get(Calendar.YEAR);                  //年
        now[1] = c.get(Calendar.MONTH) + 1;             //月
        now[2] = c.get(Calendar.DAY_OF_MONTH);          //日
        now[3] = c.get(Calendar.HOUR_OF_DAY);           //时
        now[4] = c.get(Calendar.MINUTE);                //分
        now[5] = c.get(Calendar.SECOND);                //秒
        return now;
    }
    /**
     * @create 2024/3/31 17:15
     * @Description 获取指定月份的总天数
     * @Param year
     * @Param month
     * @return int
     **/
    public static int getLastDay(int year, int month){
        Calendar c = Calendar.getInstance();                    //日历对象
        c.set(Calendar.YEAR, year);                             //指定年
        c.set(Calendar.MONTH, month - 1);                       //指定月
        //返回这月的最后一天
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /**
     * @create 2024/3/31 17:18
     * @Description 将字符串转换为Date对象
     * @Param datetime
     * @return java.util.Date
     **/
    public static Date dateOf(String datetime) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
    }
    /**
     * @create 2024/3/31 17:22
     * @Description 按照指定年、月、日和时间创建Date对象
     * @Param year
     * @Param month
     * @Param day
     * @Param time
     * @return java.util.Date
     **/
    public static Date dateOf(int year, int month, int day, String time) throws ParseException {
        String datetime = String.format("%4d-%02d-%02d %s", year, month, day, time);
        return dateOf(datetime);
    }
    /**
     * @create 2024/3/31 17:26
     * @Description 校验时间字符串是否符合 HH:mm:ss格式
     * @Param time
     * @return boolean
     **/
    public static boolean checkTimeStr(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            sdf.parse(time);                //将时间字符串转为Date对象
            return true;
        } catch (ParseException e) {
            return false;                   //发生异常则表示字符串格式错误
        }
    }
}
