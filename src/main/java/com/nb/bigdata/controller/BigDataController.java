package com.nb.bigdata.controller;

import com.nb.bigdata.configuratin.bean.*;
import com.nb.bigdata.service.HbaseService;
import com.nb.bigdata.service.HiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author zhaohaojie
 * @date 2024-07-08 12:03
 */
@RestController
@RequestMapping("/bigdata")
@Slf4j
public class BigDataController {

    @Autowired
    private HbaseService hbaseService;

    @Autowired
    private HiveService hiveService;

    @PostMapping("/saveSentence")
    public Integer saveSentence(@RequestBody BigDataSentencePredictResult sentencePredictResult){
        log.info("input param:{}",sentencePredictResult.toString());
        hbaseService.saveSentence(sentencePredictResult.getSentencePredictResult());
        hiveService.saveSentence(sentencePredictResult.getPredictResults());

        return 1;
    }

    @PostMapping("/saveRawSentence")
    public Integer saveRawSentence(@RequestBody List<SentenceEntity> rawSentences){
        hbaseService.saveRawData(rawSentences);
        return 1;
    }
}

