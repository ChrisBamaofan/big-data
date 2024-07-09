package com.nb.bigdata.configuratin.bean;

/**
 * @author zhaohaojie
 * @date 2024-07-05 11:17
 */

import lombok.Data;

@Data
public class SentencePredictResult {
    private String sentence;
    private String positive_labels;
    private String negative_labels;
    private Integer weight;
    private Long sentence_id;

}

