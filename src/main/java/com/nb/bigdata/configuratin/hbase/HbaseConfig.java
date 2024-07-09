package com.nb.bigdata.configuratin.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * Created by lskun on 2019/3/6.
 */
@org.springframework.context.annotation.Configuration
public class HbaseConfig {

    @Value("${hbase.zookeeper.quorum}")
    private String host;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String port;

    @Value("${hbase.master}")
    private String master;

    @Value("${hbase.root.dir}")
    private String dir;


    @Bean
    public Connection init() throws IOException {

        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", host);
        config.set("hbase.zookeeper.property.clientPort", port);
        config.set("hbase.master", master);
        config.set("hbase.root.dir", dir);
        Connection connection = ConnectionFactory.createConnection(config);
        return connection;
    }



}
