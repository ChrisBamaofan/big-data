#hive建表语句
DROP TABLE IF EXISTS `bigdata`.`predict_result`;
CREATE TABLE `bigdata`.`predict_result`
(
    `sentence_id`         bigint COMMENT 'sentence id',
    `model_id`            string COMMENT 'model_id',
    `positive_plate_id`   string COMMENT 'plate_id',
    `positive_plate_name` string COMMENT 'plate_id',
    `negative_plate_id`   string COMMENT 'plate_id',
    `negative_plate_name` string COMMENT 'plate_id',
    `confidence_rate`     int COMMENT '概率 1-10分',
    `create_date`         date COMMENT '创建日期'
) COMMENT '预测结果'
PARTITIONED BY (country STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE;