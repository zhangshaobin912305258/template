package com.zhang.template.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 */
public class DateUtil {

    public static final String DEFAULT_DATE_STRING = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    /**
     * 将String转换为LocalDateTime 默认日期格式yyyy-MM-dd HH:mm:ss
     * @param dateString
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        return LocalDateTime.parse(dateString,DateTimeFormatter.ofPattern(DEFAULT_DATE_STRING));
    }

    /**
     * 获取指定LocalDateTime的零点零分零秒
     * @param localDateTime
     * @return
     */
    public static LocalDateTime convertToZeroLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return LocalDateTime.of(localDateTime.toLocalDate(),LocalTime.of(0,0));
    }


    /**
     * 将String转换为LocalDateTime 指定日期格式
     * @param dateString
     * @param pattern
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(String dateString,String pattern) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        return LocalDateTime.parse(dateString,DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String转换为Date 默认日期格式yyyy-MM-dd HH:mm:ss
     * @param dateString
     * @return
     */
    public static Date convertToDate(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(DEFAULT_DATE_STRING));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将String转换为Date 指定日期格式
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date convertToDate(String dateString,String pattern) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
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
     * 将LocalDateTime转换为String 默认日期格式yyyy-MM-dd HH:mm:ss
     * @param localDateTime
     * @return
     */
    public static String convertToString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_STRING));
    }

    /**
     * 将LocalDateTime转换为String 默认日期格式yyyy-MM-dd HH:mm:ss
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String convertToString(LocalDateTime localDateTime,String pattern) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将Date转换为默认的日期格式字符串
     * @param date
     * @return
     */
    public static String convertToString(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DEFAULT_DATE_STRING).format(date);
    }
    /**
     * 将Date转换为指定的日期格式字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String convertToString(Date date,String pattern) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }
}