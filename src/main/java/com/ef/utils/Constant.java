package com.ef.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

public class Constant {
    public static final String DELIMITER = Pattern.quote("|");
    public static final String HOURLY = "hourly";
    public static final String DAILY = "daily";
    public static final DateTimeFormatter LOG_DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd.HH:mm:ss");
    public static final Pattern DOUBLE_QUOTE = Pattern.compile("\"");
}
