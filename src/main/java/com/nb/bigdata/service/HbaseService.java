package com.nb.bigdata.service;

import com.nb.bigdata.configuratin.bean.SentencePredictResult;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.nb.bigdata.configuratin.hbase.SentencePredictResultConfiguration.FAMILY_NAME;
import static com.nb.bigdata.configuratin.hbase.SentencePredictResultConfiguration.SENTENCE_PREDICT_RESULT_TABLENAME;

/**
 * @author zhaohaojie
 * @date 2024-07-08 12:03
 */
@Service
public class HbaseService {

    @Autowired
    private Connection connection;

    public void putData(String tableName, String rowKey, String columnFamily, String column, String value) throws IOException {
        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
            table.put(put);
        }
    }

    public String getData(String tableName, String rowKey, String columnFamily, String column) throws IOException {
        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);
            byte[] value = result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            return Bytes.toString(value);
        }
    }

    public void saveSentence(SentencePredictResult sentencePredictResult) {

        Table table = null;
        try {
            table = connection.getTable(TableName.valueOf(SENTENCE_PREDICT_RESULT_TABLENAME));

            //3. 准备数据
            String rowkey = sentencePredictResult.getSentence_id().toString();
            Put put= new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes(FAMILY_NAME), Bytes.toBytes("sentence"), Bytes.toBytes(sentencePredictResult.getSentence()));
            put.addColumn(Bytes.toBytes(FAMILY_NAME), Bytes.toBytes("good"), Bytes.toBytes(sentencePredictResult.getPositive_labels()));
            put.addColumn(Bytes.toBytes(FAMILY_NAME), Bytes.toBytes("bad"), Bytes.toBytes(sentencePredictResult.getNegative_labels()));
            put.addColumn(Bytes.toBytes(FAMILY_NAME), Bytes.toBytes("weight"), Bytes.toBytes(sentencePredictResult.getWeight()));
            put.addColumn(Bytes.toBytes(FAMILY_NAME), Bytes.toBytes("date"), Bytes.toBytes(LocalDateTime.now().toString()));

            // 4. 添加数据
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
