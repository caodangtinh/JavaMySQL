package com.ef.repository;

import com.ef.model.Log;
import org.joda.time.DateTime;

import java.util.List;

public interface LogRepository {
    void insertBatchLog(final List<Log> employeeList);
    List<String> findByPeriodAndThreshold(DateTime start, DateTime end, int threshold);
}
