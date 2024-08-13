package com.nb.bigdata.configuratin.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 *
 */
@Configuration
@Slf4j
public class RawSentConfig {
    public static final String RAW_SENTENCE_TABLENAME = "raw";

    public static final String RAW_FAMILY_NAME = "cf";

    @Autowired
    private Connection connection;

    // 使用预分区
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
            byte[][] splits = new byte[][]{
                    Bytes.toBytes("56"),
                    Bytes.toBytes("AB")
            };
            admin.createTable(hTableDescriptor,splits);
            log.info("创建hbase表：{}",tableName);
        }
    }

}
