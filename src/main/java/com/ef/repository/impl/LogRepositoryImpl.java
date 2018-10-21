package com.ef.repository.impl;

import com.ef.model.Log;
import com.ef.repository.LogRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class LogRepositoryImpl implements LogRepository {

    private static final String QUERY_INSERT = "insert into log(log_date, ip, request, status, user_agent) values (?,?,?,?,?)";
    private static final String QUERY_FILTER = "select distinct ip from log " +
            "where ((select count(*) from log where log_date between ? and ?) > ?);";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBatchLog(final List<Log> batchList) {
        jdbcTemplate.batchUpdate(QUERY_INSERT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        Log log = batchList.get(i);
                        ps.setTimestamp(1, new java.sql.Timestamp(log.getDate().getMillis()));
                        ps.setString(2, log.getIp());
                        ps.setString(3, log.getRequest());
                        ps.setShort(4, log.getStatus());
                        ps.setString(5, log.getUserAgent());
                    }

                    @Override
                    public int getBatchSize() {
                        return batchList.size();
                    }
                });

    }

    @Override
    public List<String> findByPeriodAndThreshold(DateTime start, DateTime end, int threshold) {
        List<String> ips = jdbcTemplate.queryForList(QUERY_FILTER, new Object[]{start, end, threshold}, String.class);
        return ips;
    }
}
