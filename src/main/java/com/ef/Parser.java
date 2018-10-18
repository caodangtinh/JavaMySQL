package com.ef;

import com.ef.converter.FileProcessor;
import com.ef.model.Log;

import java.util.List;

public class Parser {
    public static void main(String[] args) {
//        if (args.length < 3) throw  new IllegalArgumentException("");
        List<Log> logList = FileProcessor.processInputFile("/home/tinhcao/Downloads/Java_MySQL_Test/access.log");
        System.out.println(logList.size());
    }
}
