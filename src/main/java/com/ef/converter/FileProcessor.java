package com.ef.converter;

import com.ef.model.Log;
import com.ef.repository.impl.LogRepositoryImpl;
import com.ef.service.LogService;
import com.ef.utils.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileProcessor {
    private static final Logger LOGGER = Logger.getLogger(LogRepositoryImpl.class);

    @Autowired
    private LogService logService;

    public void processAndInsert(String inputFilePath) {
        int count = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            List<Log> logs = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) {
                logs.add(new Log(line.split(Constant.DELIMITER)));
                if (logs.size() == 100) {
                    logService.insertBatchLog(logs);
                    LOGGER.info("Finished insert data for batch " + count + " with " + logs.size() + " records");
                    logs.clear();
                    count ++;
                }
            }

            // last batch
            if (logs.size() > 0) {
                logService.insertBatchLog(logs);
            }
        } catch (IOException e) {
            LOGGER.error("Error while trying to parse " + e.getMessage());
        }

    }
}
