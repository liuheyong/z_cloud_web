package com.cloud.web.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    private static final String YYYYMMDD = "yyyyMMdd";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String HHmmss = "HHmmss";

    public DateUtils() {
    }

    public static boolean isDateEqual(Date date1, Date date2) {
        return date1 != null && date2 != null && date1.compareTo(date2) == 0;
    }

    public static Date getDate(String s) {
        return getDate(s, "yyyyMMdd");
    }

    public static final Date currentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static final String getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(1));
    }

    public static final String getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(2) + 1);
    }

    public static final String getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(3));
    }

    public static final String getStandard(Date date) {
        return sdf.format(date);
    }

    public static final String getYYYYMM(Date date) {
        return (new SimpleDateFormat("yyyyMM")).format(date);
    }

    public static final String getYYYYMMDD(Date date) {
        return (new SimpleDateFormat("yyyyMMdd")).format(date);
    }

    public static final String getHHMMSS(Date date) {
        return (new SimpleDateFormat("HHmmss")).format(date);
    }

    public static final String getDDMMYYYY(Date date) {
        return (new SimpleDateFormat("ddMMyyyy")).format(date);
    }

    public static final Date getDate(String date, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(date);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static final int getMaxDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        return cal.getActualMaximum(5);
    }

    public static final int getDayBetween2Dates(Date begin, Date end) {
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(begin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);
        return (int) ((calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000L / 60L / 60L / 24L);
    }

    public static final Date getPastDate(int past) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(6, -past);
        return cal.getTime();
    }

    public static final Date getNextDate(int next) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(6, next);
        return cal.getTime();
    }

    public static final Date getNextDate(Date date, int next) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(6, next);
        return cal.getTime();
    }

    public static String getCurrentDate(String parrtern) {
        return getDateString(new Date(), parrtern);
    }

    public static Date getDate(Timestamp time) {
        return new Date(time.getTime());
    }

    public static String getDateString(Date date) {
        return getDateString(date, "yyyyMMdd");
    }

    public static String getDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Calendar getCalendar(String dateStr) {
        return getCalendar(dateStr, "yyyyMMdd");
    }

    public static Calendar getCalendar(String dateStr, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate(dateStr, pattern));
        return calendar;
    }

    public static String getCalendarString(Calendar calendar) {
        return getCalendarString(calendar, "yyyyMMdd");
    }

    public static String getCalendarString(Calendar calendar, String pattern) {
        return getDateString(new Date(calendar.getTime().getTime()), pattern);
    }

    public static Timestamp getTimestamp(String timestamp) throws ParseException {
        return getTimestamp(timestamp, "yyyyMMdd");
    }

    public static Timestamp getTimestamp(String timestamp, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return new Timestamp(format.parse(timestamp).getTime());
    }
}
