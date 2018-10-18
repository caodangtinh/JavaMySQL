package com.ef.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

public class Constant {
    public static final String DELIMITER = Pattern.quote("|");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final Pattern DOUBLE_QUOTE = Pattern.compile("\"");
}
