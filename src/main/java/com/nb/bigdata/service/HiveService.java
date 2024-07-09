package com.nb.bigdata.service;

import com.nb.bigdata.configuratin.dto.domain.PredictResult;

import java.util.List;

/**
 * @author zhaohaojie
 * @date 2024-07-06 18:15
 */
public interface HiveService {
    void batchInsert(int id,  String name,  int age);

    Object select(String hql);

    List<String> listAllTables();

    List<String> describeTable(String tableName);

    List<String> selectFromTable(String tableName);

    void saveSentence(List<PredictResult> predictResults);
}
