package com.nb.bigdata.controller;

import com.nb.bigdata.configuratin.bean.HbaseTableParam;
import com.nb.bigdata.configuratin.bean.SentencePredictResult;
import com.nb.bigdata.service.HbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author zhaohaojie
 * @date 2024-07-08 12:03
 */
@RestController
@RequestMapping("/hbase")
public class HbaseController {

    @Autowired
    private HbaseService hbaseService;

    @PostMapping("/put")
    public void put(@RequestBody HbaseTableParam table){
        try {
            hbaseService.putData(table.getTableName(),table.getRowKey(),table.getColumnFamily(),table.getColumn(),table.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/saveSentence")
    public void saveSentence(@RequestBody SentencePredictResult sentencePredictResult){
        hbaseService.saveSentence(sentencePredictResult);

    }
    @PostMapping("/get")
    public String get(@RequestBody HbaseTableParam table) throws IOException {
        return hbaseService.getData(table.getTableName(),table.getRowKey(),table.getColumnFamily(),table.getColumn());
    }
}

