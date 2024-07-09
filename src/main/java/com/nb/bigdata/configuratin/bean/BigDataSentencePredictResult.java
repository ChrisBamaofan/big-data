package com.nb.bigdata.configuratin.bean;

import com.nb.bigdata.configuratin.dto.domain.PredictResult;
import lombok.Data;

import java.util.List;

/**
 * @author zhaohaojie
 * @date 2024-07-08 18:09
 */
@Data
public class BigDataSentencePredictResult {
    SentencePredictResult sentencePredictResult;
    List<PredictResult> predictResults;
}

