package com.nb.bigdata.configuratin.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 *
 */
@Configuration
@Slf4j
public class RawSentenceConfiguration {
    public static final String RAW_SENTENCE_TABLENAME = "raw_sentence";

    public static final String RAW_FAMILY_NAME = "raw_sentence_info";

    @Autowired
    private Connection connection;

    @PostConstruct
    public void init() throws Exception{
        //1. 连接HBase
        Admin admin = connection.getAdmin();

        //2.创建表
        //2.1 通过表名称类，传入表名称，获取表名称对象
        TableName tableName = TableName.valueOf(RAW_SENTENCE_TABLENAME);
        if (! admin.tableExists(tableName)) {
            //2.2 如果表不存在就重新创建一个表
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            hTableDescriptor.addFamily(new HColumnDescriptor(RAW_FAMILY_NAME));
            admin.createTable(hTableDescriptor);
            log.info("创建hbase表：{}",tableName);
        }
    }

}
