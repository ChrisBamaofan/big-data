package com.nb.bigdata.controller;

import com.nb.bigdata.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhaohaojie
 * @date 2024-07-08 11:04
 */

@RestController
@RequestMapping("/hive")
public class HiveController {

    @Autowired
    private HiveService hiveService;

    @PostMapping("/createTable")
    public String createTable() {
//        hiveService.createTable();
        return "Table created successfully.";
    }

    @PostMapping("/insertData")
    public String insertData(@RequestParam int id, @RequestParam String name, @RequestParam int age) {
        hiveService.batchInsert(id, name, age);
        return "Data inserted successfully.";
    }

    @GetMapping("/queryData")
    public List<String> queryData(@RequestParam String table) {
        return hiveService.selectFromTable(table);
    }

}

