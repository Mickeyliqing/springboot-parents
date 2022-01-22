package com.example.springbootkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Component
@Slf4j
public class KafkaConsumer {

    /**
     * 批量手动确认ACK
     **/
    @KafkaListener(id = "consumer_2", topics = {"#{'${spring.kafka.topics}'.split(',')}"},
            groupId = "${spring.kafka.consumer.group-id}", containerFactory = "batchFactoryAck")
    public void consumer_2(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        long startTime = System.currentTimeMillis();
        records.forEach(record -> {
            log.info(String.format("消费：topic:%s-partition:%s-offset:%s-value:%s", record.topic(), record.partition(),
                    record.offset(), record.value()));
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            kafkaMessage.ifPresent(u -> {
                try {
                    log.info(Thread.currentThread().getName() + ":" + u);

                    /**
                     * 指定消费到数据后的操作
                     */

                } catch (Exception e) {
                    log.error("消费异常,错误消息:{},异常信息：{}", u, e.getMessage());
                }
            });
        });
        log.info("data size:{} kafka消费耗时 {}", records.size(), System.currentTimeMillis() - startTime);
        ack.acknowledge();
    }
}
