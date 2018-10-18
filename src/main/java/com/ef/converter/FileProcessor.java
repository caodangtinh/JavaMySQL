package com.ef.converter;

import com.ef.model.Log;
import com.ef.utils.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileProcessor {

    public static List<Log> processInputFile(String inputFilePath) {
        Function<String, Log> mapToItem = (line) -> new Log(line.split(Constant.DELIMITER));
        List<Log> inputList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFilePath))))) {
            inputList = br.lines().map(mapToItem).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
