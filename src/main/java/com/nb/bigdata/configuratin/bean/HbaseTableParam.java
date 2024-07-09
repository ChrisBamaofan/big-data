package com.nb.bigdata.configuratin.bean;

import lombok.Data;

/**
 * @author zhaohaojie
 * @date 2024-07-08 12:05
 */
@Data
public class HbaseTableParam {

    String tableName;
    String rowKey;
    String columnFamily;
    String column;
    String value;
}

