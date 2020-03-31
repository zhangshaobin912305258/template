package com.zhang.template.util;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * 日期操作工具类
 */
public class DateUtil {
    public static String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_PATTERN_DATE = "yyyy-MM-dd";
    /**
     * 将Date转化为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertByDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss的日期格式字符串转化为Date
     * @param dateString
     * @return
     */
    public static Date convertByString(String dateString) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(FORMAT_PATTERN));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        return Date.from(zdt.toInstant());
    }

    /**
     * @param localDateTime
     * 将LocalDateTime类转换为yyyy-MM-dd HH:mm:ss字符串
     * @return
     */
    public static String convertToString(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN));
    }

    /**
     * 将LocalDate类转换为yyyy-MM-dd字符串
     * @param localDate
     * @return
     */
    public static String convertToString(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE));
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss字符串转换为LocalDateTime
     * @param dateString
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(String dateString) {
        if (StringUtils.isNotEmpty(dateString)) {
            return LocalDateTime.parse(dateString,DateTimeFormatter.ofPattern(FORMAT_PATTERN));
        }
        return null;
    }

    /**
     * 将Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
    }

    /**
     * 获取指定LocalDateTime的零点零分零秒
     * @param localDateTime
     * @return
     */
    public static LocalDateTime convertToZeroLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            localDateTime = LocalDateTime.now();
        }
        return LocalDateTime.of(localDateTime.toLocalDate(),LocalTime.of(0,0));
    }

    /**
     * 将LocalDateTime转换为Date
     * @param localDateTime
     * @return
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将Date转换为yyyy-MM-dd HH:mm:ss字符串
     * @param date
     * @return
     */
    public static String convertToString(Date date) {
        if (null == date) {
            return null;
        }
        return new SimpleDateFormat(DateUtil.FORMAT_PATTERN).format(date);
    }

    public static Long convertToTimestamp(LocalDateTime startDateTime) {
        return DateUtil.convertToDate(startDateTime).getTime();
    }
}
