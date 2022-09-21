package com.example.springbootkafka.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Component
@AllArgsConstructor
@Slf4j
public class KafkaProvider {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageData(String topic, String data) {
        long start = System.currentTimeMillis();
        try {
            kafkaTemplate.send(topic, data);
            log.info("\n生产消息至Kafka:\n" + data);
        } catch (Exception e) {
            log.error("出错！！！！！！！！！！！" + e.getMessage());
            e.printStackTrace();
        }
        log.info("线程: {} , kafka插入耗时: {}", Thread.currentThread().getName(), System.currentTimeMillis() - start);
    }
}
