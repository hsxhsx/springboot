package com.test.dbhappy.utils;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static int getDutyDays(java.util.Date startDate,java.util.Date endDate) {
        int result = 0;
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd ");
        Calendar cal = Calendar.getInstance();
        while (startDate.compareTo(endDate) <= 0) {
            cal.setTime(startDate);
            //判断是否为周六日
            int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (week == 0 || week == 6) {//0为周日，6为周六
                result++;
            }
            cal.add(Calendar.DAY_OF_MONTH, +1);
            startDate = cal.getTime();
        }
        return result;
    }

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getmsTime() {
        return dateTimeNow("yyyyMMddHHmmssSSS");
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(String s, Object str)
    {
        if (str == null)
        {
            return null;
        }
        return parseDate(str.toString(), parsePatterns);
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    public static void main(String[] args) throws ParseException {
        Date now = new Date();
        Date before = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-10-26 13:00:00");
        System.out.println(getDatePoor(now, before));
    }

    /**
     * 计算两个时间差
     */
    public static long getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;

        return day = hour >= 3 ? day + 1 :day;
    }



    /**
     * 获取每月起始日的起始时间
     * @param monthTime
     * @return
     */
    public static String getMonthlyStartFirstDay(String monthTime) {
        LocalDateTime date = null;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        if(monthTime == null || monthTime == ""){
            date = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        }else{
            int year = Integer.parseInt(monthTime.substring(0,4));
            int month = Integer.parseInt(monthTime.substring(5,7));
            date = LocalDateTime.of(LocalDate.of(year, month, 1),LocalTime.MIN);
        }
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
//        System.out.println("firstday:" + firstday.format(fmt));
        return firstday.format(fmt);
    }

    /**
     * 获取每月末尾日的最后时间
     * @param monthTime
     * @return
     */
    public static String getMonthlyEndLastDay(String monthTime) {
        LocalDateTime date = null;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        if(monthTime == null || monthTime == ""){
            date = LocalDateTime.of(LocalDate.now(),LocalTime.MAX);
        }else{
            int year = Integer.parseInt(monthTime.substring(0,4));
            int month = Integer.parseInt(monthTime.substring(5,7));
            date = LocalDateTime.of(LocalDate.of(year, month, 1),LocalTime.MIN);
        }
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println("lastDay:" + lastDay.format(fmt));
        return lastDay.format(fmt);
    }

    /**
     * 获取当前日期后多少天
     *
     * @param beforOrAfterDay(负数就是之前多少天)
     * @param
     * @return
     */
    public  static Date getBeforeOrAfterDate(int beforOrAfterDay, Date now) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, now.getHours());
        calendar.set(Calendar.MINUTE, now.getMinutes());
        calendar.set(Calendar.SECOND, now.getSeconds());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, beforOrAfterDay);
        Date date = calendar.getTime();
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String delayDay = sdf.format(date);
        Date lastDate = sdf.parse(delayDay);
        return lastDate;
    }

    public static Date getMonth(int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, month);
        Date m = c.getTime();
        String mon = format.format(m);
        return m;
    }

}

