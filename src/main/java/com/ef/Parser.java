package com.ef;

import com.ef.configuration.ApplicationConfiguration;
import com.ef.converter.FileProcessor;
import com.ef.repository.impl.LogRepositoryImpl;
import com.ef.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ef.utils.Constant.*;

public class Parser {
    private static final Logger LOGGER = Logger.getLogger(LogRepositoryImpl.class);

    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);
        Map<String, String> map = Stream.of(args)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1], (a, b) -> a, HashMap::new));
        if (argsList.size() < 3)
            throw new IllegalArgumentException("You must pass accesslog, startDate, duration, threshold");

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        FileProcessor fileProcessor = context.getBean(FileProcessor.class);
        // load data to MySQL if exist --accesslog parameter
        if (map.containsKey("--accesslog")) {
            fileProcessor.processAndInsert(map.get("--accesslog"));
            LOGGER.info("Finished loading data");
        }

        // start date
        String startDateStr = map.get("--startDate");
        DateTime paramDate = INPUT_DATE_TIME_FORMATTER.parseDateTime(startDateStr);
        // change format from "yyyy-MM-dd.HH:mm:ss" to "yyyy-MM-dd HH:mm:ss.SSS"
        String inputDateTimeStr = LOG_DATE_TIME_FORMATTER.print(paramDate);
        DateTime startDate = LOG_DATE_TIME_FORMATTER.parseDateTime(inputDateTimeStr);
        DateTime endDate = LOG_DATE_TIME_FORMATTER.parseDateTime(inputDateTimeStr);
        // duration
        String duration = map.get("--duration");
        if (HOURLY.equals(duration)) {
            endDate = endDate.plusHours(1);
        } else if (DAILY.equals(duration)) {
            endDate = endDate.plusDays(1);
        } else {
            throw new IllegalArgumentException("Invalid duration");
        }

        //
        String threshold = map.get("--threshold");
        if (!StringUtils.isNumeric(threshold)) {
            throw new IllegalArgumentException("Invalid threshold");
        }
        List<String> ips = logService.findByPeriodAndThreshold(startDate, endDate, Integer.valueOf(threshold));
        LOGGER.info("List matched IPs " + ips);
        if (ips.size() > 0) {
            LOGGER.info("Saving search data for " + ips.size() + " records");
            logService.saveSearch(ips, "Search from " + startDate + " to " + endDate + " with threshold " + threshold);
            LOGGER.info("Finished saving search data");
        }
    }
}
