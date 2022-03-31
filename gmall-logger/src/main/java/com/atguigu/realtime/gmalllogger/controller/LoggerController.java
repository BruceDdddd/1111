package com.atguigu.realtime.gmalllogger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoggerController {
    @RequestMapping("/applog")
    public String logger(@RequestParam("param") String logStr){

        
        saveToDisk(logStr);

        writeToKafka(logStr);

        return  "ok";


    }

    @Autowired
    KafkaTemplate<String,String> kafka;
    private void writeToKafka(String logStr) {
        kafka.send("ods_log",logStr);
    }

    private void saveToDisk(String logStr) {
        log.info(logStr);
    }

}
