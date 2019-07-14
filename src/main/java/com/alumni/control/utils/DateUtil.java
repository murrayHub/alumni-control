package com.alumni.control.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description : 日期工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 18:01
 */
public class DateUtil {
    public static final String TIME_PATTERN = "HHmmss";
    public static final String TIMES_PATTERN = "yyyy/MM/ddHH:mm:ss";
    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String SHORT_DATE_PATTERN = "yyMMdd";
    public static final String FULL_PATTERN = "yyyyMMddHHmmss";
    public static final String FULL_PATTERNS = "yyyyMMddHHmmssSS";
    public static final String PART_PATTERN = "yyMMddHHmmss";
    public static final String SETTLE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String HOUR_OF_MINUTE = "HHmm";
    public static final String YYYYMM = "yyyyMM";
    public static final String DATE_FULL_PATTERN = "yyyyMMdd HH:mm:ss";
    public static final String YEAR_OF_MINUTE = "yyyyMMddHHmm";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String SMALL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SMALL_DATE_PATTERN = "yyyy/MM/dd";
    public static final String MILLIS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

    public DateUtil() {
    }

    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    public static String getCurrent() {
        return getCurrent("yyyyMMddHHmmss");
    }

    public static String getCurrentStr() {
        return getCurrent("yyyyMMdd");
    }

    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    public static Date parse(String date) {
        int fourteen = 14;
        return !StringUtils.isBlank(date) && date.length() > fourteen ? parse(date, "yyyyMMddHHmmssSS") : parse(date, "yyyyMMddHHmmss");
    }

    public static String toFullString(String date) {
        return StringUtils.isEmpty(date) ? "" : date.substring(0, 19);
    }

    public static Date parse(String date, String pattern) {
        DateTime dateTime = parseTime(date, pattern);
        return dateTime == null ? null : dateTime.toDate();
    }

    public static DateTime parseTime(String date, String pattern) {
        if (StringUtils.isBlank(date)) {
            return null;
        } else {
            if ("yyyyMMddHHmmss".equals(pattern)) {
                date = StringUtils.rightPad(date, 14, '0');
            }

            return DateTimeFormat.forPattern(pattern).parseDateTime(date);
        }
    }

    public static String format(Date date) {
        return format(date, "yyyyMMddHHmmss");
    }

    public static String formatDate(Date date) {
        return null == date ? null : format(date, "yyyyMMdd");
    }

    public static String format(Date date, String pattern) {
        return date == null ? null : (new DateTime(date)).toString(pattern);
    }

    public static String formatSmall(Date date) {
        return date == null ? null : (new DateTime(date)).toString("yyyy-MM-dd");
    }

    public static Date parseString2Date(String dateStr) throws Exception {
        Date dt;
        try {
            dt = parse(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
        } catch (Exception var7) {
            try {
                dt = parse(dateStr, "yyyy-MM-dd HH:mm:ss");
            } catch (Exception var6) {
                try {
                    dt = parse(dateStr, "yyyy-MM-dd");
                } catch (Exception var5) {
                    throw new Exception(var5);
                }
            }
        }

        return dt;
    }

    public static String dateFormatToParse(String date) {
        if (date != null && !date.equals("")) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                Date date1 = formatDate.parse(date);
                return formatDate.format(date1);
            } catch (ParseException var3) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String formatFull(Date date) {
        return date == null ? null : (new DateTime(date)).toString("yyyy-MM-dd HH:mm:ss");
    }

    public static String formatYearOnly(Date date) {
        return date == null ? null : (new DateTime(date)).toString("yyyy");
    }

    public static String computeDate(String date, int day, String inPattern, String outPattern) {
        try {
            DateTime dateTime = parseTime(date, inPattern);
            if (dateTime == null) {
                return null;
            } else {
                dateTime = dateTime.minusDays(0 - day);
                return format(dateTime.toDate(), outPattern);
            }
        } catch (Exception var5) {
            return null;
        }
    }

    private static Date addDate(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date could not be null!");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static Date addSeconds(Date source, int seconds) {
        return addDate(source, 13, seconds);
    }

    public static Date addDays(Date source, int addDays) {
        return addDate(source, 5, addDays);
    }

    public static Date addYear(Date date, int year) {
        if (date == null) {
            throw new IllegalArgumentException("The date could not be null!");
        } else {
            DateTime dateTime = new DateTime(date);
            return dateTime.plusYears(year).toDate();
        }
    }

    public static Date addDay(Date date, int day) {
        if (date == null) {
            throw new IllegalArgumentException("The date could not be null!");
        } else {
            DateTime dateTime = new DateTime(date);
            return dateTime.plusDays(day).toDate();
        }
    }

    public static DateTime thisMonthEnd(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.dayOfMonth().withMaximumValue();
    }

    public static DateTime thisMonthBegin(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.dayOfMonth().withMinimumValue();
    }

    public static boolean isMonthBegin(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTime beginDT = dateTime.dayOfMonth().withMinimumValue();
        return beginDT.equals(dateTime);
    }

    public static boolean isMonthEnd(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTime beginDT = dateTime.dayOfMonth().withMaximumValue();
        return beginDT.equals(dateTime);
    }

    public static String getYear(Date date) {
        DateTime dateTime = new DateTime(date);
        return String.valueOf(dateTime.getYear());
    }

    public static String getMonth(Date date) {
        DateTime dateTime = new DateTime(date);
        return String.valueOf(dateTime.getMonthOfYear());
    }

    public static Date getCurrentDate(String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse((new DateTime()).toString(pattern), format).toDate();
    }

    public static long getBetweenDays(Date startDate, Date endDate) {
        if (endDate != null && startDate != null) {
            Long days = endDate.getTime() - startDate.getTime();
            return days / 86400000L;
        } else {
            return -1L;
        }
    }
}
