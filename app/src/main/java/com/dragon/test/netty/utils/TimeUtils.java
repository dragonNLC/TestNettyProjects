package com.dragon.test.netty.utils;



import com.dragon.test.netty.data.bean.DateBean;
import com.dragondevl.clog.CLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Administrator on 2017/7/19.
 */

public class TimeUtils {

    public static DateBean formatCurData() {
        String mYear;
        String mMonth;
        String mDay;
        String mWay;
        String AM_PM;
        String mHour;
        String mMin;
        final Calendar c = Calendar.getInstance();

        /*System.setProperty("user.timezone","GMT+8");
        c.setTimeZone(TimeZone.getTimeZone("GMT+8"));*///如果当前时区不是东八区的话，一般都是按照系统设置的时区

        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));//获取当前星期
        AM_PM = String.valueOf(c.get(Calendar.AM_PM));
        mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        mMin = String.valueOf(c.get(Calendar.MINUTE));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        DateBean dateBean = new DateBean();
        dateBean.setYear(mYear);
        dateBean.setMonth(mMonth);
        dateBean.setDay(mDay);
        dateBean.setWay(mWay);
        dateBean.setHour(mHour);

        if ("0".equals(AM_PM)) {
            dateBean.setAmPm("上午");
        } else {
            dateBean.setAmPm("下午");
        }
        dateBean.setHour(dateBean.getHour().length() <= 1 ? "0" + dateBean.getHour() : dateBean.getHour());
        dateBean.setMin(mMin.length() <= 1 ? "0" + mMin : mMin);
        dateBean.setMonth(dateBean.getMonth().length() <= 1 ? "0" + dateBean.getMonth() : dateBean.getMonth());
        dateBean.setDay(dateBean.getDay().length() <= 1 ? "0" + dateBean.getDay() : dateBean.getDay());
        return dateBean;
    }

    public static Date simpleParse(String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.parse(time);
    }

    public static String simpleCurrentFormat() {
        return simpleCurrentFormat("yyyyMMddHHmmss");
    }

    public static String simpleCurrentFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.format(new Date());
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.format(date);
    }

    //判断当前时间是不是已经超过上次重启的n天了，如果不是，判断当前时间是否与保存的时间相同，不相同，则加一。相同，则不变

    //获取当前年月日
    public static String generateCurrentDate() {
        DateBean curDate = TimeUtils.formatCurData();
        return curDate.getYear() + " " + curDate.getMonth() + " " + curDate.getDay();
    }

    //获取当前年月日
    public static String generateCurrentTime() {
        DateBean curDate = TimeUtils.formatCurData();
        return curDate.getYear() + " " + curDate.getMonth() + " " + curDate.getDay() + " " + curDate.getHour() + " " + curDate.getMin();
    }

    public static String formatDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINESE);
        return sdf.format(new Date());
    }

    public static String formatTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss", Locale.CHINESE);
        return sdf.format(new Date());
    }

    public static Date parseDay(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD", Locale.CHINESE);
        return sdf.parse(time);
    }

    public static Date parseTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss", Locale.CHINESE);
        return sdf.parse(time);
    }

    public static String formatInt(int value) {
        if(value < 10) {
            return "0" + value;
        } else {
            return String.valueOf(value);
        }
    }

    public static boolean checkDataBetween(String sTime, String eTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINESE);
        return checkDataBetween(sTime, eTime, sdf.format(new Date()));
    }

    public static boolean checkDataBetween(String sTime, String eTime, String cTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINESE);
        try {
            Date sDate = sdf.parse(sTime);
            Date eDate = sdf.parse(eTime);
            Date cDate = sdf.parse(cTime);
            if (cDate.after(sDate) && cDate.before(eDate)) {//订单在某个时间段的
                CLog.e(sTime + "---" + cTime + "---" + eTime + "---订单在某个时间段的");
                return true;
            }
            CLog.e(sTime + "---" + cTime + "---" + eTime + "---订单不在某个时间段的");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}
