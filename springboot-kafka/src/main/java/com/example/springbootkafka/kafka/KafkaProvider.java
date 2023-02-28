package com.example.springbootkafka.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.info("消息发送失败：" +ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("消息发送成功 ：" + result);
                }
            });
            log.info("\n生产消息至Kafka:\n" + data);
        } catch (Exception e) {
            log.error("出错！！！！！！！！！！！" + e.getMessage());
            e.printStackTrace();
        }
        log.info("线程: {} , kafka插入耗时: {}", Thread.currentThread().getName(), System.currentTimeMillis() - start);
    }
}
