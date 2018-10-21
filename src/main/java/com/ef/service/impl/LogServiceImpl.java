package com.ef.service.impl;

import com.ef.model.Log;
import com.ef.repository.LogRepository;
import com.ef.service.LogService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogServiceImpl implements LogService {


    @Autowired
    private LogRepository logRepository;

    @Override
    public void insertBatchLog(List<Log> logDTOS) {
        logRepository.insertBatchLog(logDTOS);
    }

    @Override
    public List<String> findByPeriodAndThreshold(DateTime start, DateTime end, int threshold) {
        return logRepository.findByPeriodAndThreshold(start, end, threshold);
    }
}
