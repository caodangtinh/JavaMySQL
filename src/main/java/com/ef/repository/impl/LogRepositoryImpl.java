package com.ef.repository.impl;

import com.ef.model.Log;
import com.ef.repository.LogRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepositoryImpl implements LogRepository {

    private static final String QUERY_INSERT = "insert into log(log_date, ip, request, status, user_agent) " +
            "values (:log_date,:ip,:request,:status,:user_agent)";
    private static final String QUERY_FILTER = "select distinct ip from log " +
            "where ((select count(*) from log where log_date between :startDate and :endDate) > :threshold)";

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public void insertBatchLog(final List<Log> batchList) {
        List<MapSqlParameterSource> batchArgs = new ArrayList<>();
        int size = batchList.size();
        MapSqlParameterSource parameters;
        for (int i = 0; i < size; i++) {
            Log log = batchList.get(i);
            parameters = new MapSqlParameterSource();
            parameters.addValue("log_date", log.getDate().getMillis());
            parameters.addValue("ip", log.getIp());
            parameters.addValue("request", log.getRequest());
            parameters.addValue("status", log.getStatus());
            parameters.addValue("user_agent", log.getUserAgent());
            batchArgs.add(parameters);
        }
        namedJdbcTemplate.batchUpdate(QUERY_INSERT, batchArgs.toArray(new MapSqlParameterSource[size]));
    }

    @Override
    public List<String> findByPeriodAndThreshold(DateTime start, DateTime end, int threshold) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("startDate", start.getMillis());
        namedParameters.addValue("endDate", end.getMillis());
        namedParameters.addValue("threshold", threshold);

        return namedJdbcTemplate.queryForList(QUERY_FILTER, namedParameters, String.class);
    }
}
