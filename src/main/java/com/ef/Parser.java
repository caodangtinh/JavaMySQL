package com.ef;

import com.ef.configuration.ApplicationConfiguration;
import com.ef.converter.FileProcessor;
import com.ef.model.Log;
import com.ef.service.LogService;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    private final org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());

    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);
        Map<String, String> map = Stream.of(args)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1], (a, b) -> a, HashMap::new));
        if (argsList.size() < 3) throw  new IllegalArgumentException("You must pass accesslog, startDate, duration, threshold");

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        if (map.containsKey("--accesslog")) {
            List<Log> logList = FileProcessor.processInputFile("/home/tinhcao/Downloads/Java_MySQL_Test/access.log");
            logService.insertBatchLog(logList);
        }
    }
}
