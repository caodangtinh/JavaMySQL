package com.ef.repository.impl;

import com.ef.constant.Constant;
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

@Repository
public class LogRepositoryImpl implements LogRepository {
    public static final String QUERY_INSERT = "insert into log(log_date, ip, request, status, user_agent) values (?,?,?,?,?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBatchLog(final List<Log> logDTOS) {
        for (int j = 0; j < logDTOS.size(); j += Constant.BATCH_SIZE) {
            final List<Log> batchList = logDTOS.subList(j, j + Constant.BATCH_SIZE > logDTOS.size() ? logDTOS.size() : j + Constant.BATCH_SIZE);
            jdbcTemplate.batchUpdate(QUERY_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            Log logDTO = batchList.get(i);
                            ps.setTimestamp(1, new java.sql.Timestamp(logDTO.getDate().getMillis()));
                            ps.setString(2, logDTO.getIp());
                            ps.setString(3, logDTO.getRequest());
                            ps.setShort(4, logDTO.getStatus());
                            ps.setString(5, logDTO.getUserAgent());
                        }

                        @Override
                        public int getBatchSize() {
                            return batchList.size();
                        }
                    });

        }
    }

    @Override
    public List<Log> findByPeriodAndThreshold(DateTime start, DateTime end, int threshold) {
        return null;
    }
}
