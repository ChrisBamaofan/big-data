package com.nb.bigdata.service;

import com.nb.bigdata.configuratin.bean.SentencePredictResult;
import com.nb.bigdata.configuratin.dto.domain.PredictResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhaohaojie
 * @date 2024-07-06 18:16
 */
@Service
@Slf4j
public class HiveServiceImpl implements HiveService{
    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    @Autowired
    @Qualifier("hiveDruidDataSource")
    private DataSource hiveDruidDataSource;

    @Override
    public void batchInsert(int id,  String name,  int age) {
        String sql = String.format("INSERT INTO test1 (id, name, age) VALUES (%d, '%s', %d)", id, name, age);
        hiveJdbcTemplate.execute(sql);
    }

    @Override
    public Object select(String hql) {
        return hiveJdbcTemplate.queryForObject(hql, Object.class);
    }

    @Override
    public List<String> listAllTables() {
        List<String> result = new ArrayList<>();
        try {
            Statement statement = hiveDruidDataSource.getConnection().createStatement();
            String sql = "show tables";
            log.info("Running: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public List<String> describeTable(String tableName) {
        if (StringUtils.isEmpty(tableName)){
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        try {
            Statement statement = hiveDruidDataSource.getConnection().createStatement();
            String sql = "describe " + tableName;
            log.info("Running" + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> selectFromTable(String tableName) {
        if (StringUtils.isEmpty(tableName)){
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        try {
            Statement statement = hiveDruidDataSource.getConnection().createStatement();
            String sql = "select * from " + tableName;
            log.info("Running" + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            int columnCount = resultSet.getMetaData().getColumnCount();
            String str = null;
            while (resultSet.next()) {
                str = "";
                for (int i = 1; i < columnCount; i++) {
                    str += resultSet.getString(i) + " ";
                }
                str += resultSet.getString(columnCount);
                log.info(str);
                result.add(str);
            }
            return result;
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void saveSentence(List<PredictResult> predictResults) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `bigdata`.`predict_result`  PARTITION (country='CN')  (sentence_id,model_id,plate_id,type,confidence_rate,create_date) values");
        for (PredictResult predictResult:predictResults){
            sql.append("(").append(predictResult.getSentenceId()).append(",");
            sql.append("'").append(predictResult.getModelId()).append("'").append(",");
            sql.append(predictResult.getPlateId()).append(",");
            sql.append(predictResult.getType()).append(",");
            sql.append(predictResult.getConfidenceRate()).append(",");
            sql.append("'").append(predictResult.getCreateDate().toLocalDate()).append("'").append("),");
        }

        sql.deleteCharAt(sql.lastIndexOf(","));
        hiveJdbcTemplate.execute(sql.toString());
    }
}

