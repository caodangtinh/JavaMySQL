package com.ef.service;

import com.ef.model.Log;
import org.joda.time.DateTime;

import java.util.List;

public interface LogService {
    void insertBatchLog(final List<Log> employeeList);
    List<Log> findByPeriodAndThreshold(DateTime start, DateTime end, int threshold);
}
