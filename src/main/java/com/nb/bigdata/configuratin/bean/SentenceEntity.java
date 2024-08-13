package com.nb.bigdata.configuratin.bean;

import lombok.Data;

/**
 * @author zhaohaojie
 * @date 2024-06-25 23:10
 */
@Data
public class SentenceEntity {
    Long sentenceId;
    String content;
    Long sentenceDateTime;
    String md5;
    String rowKey;
}

