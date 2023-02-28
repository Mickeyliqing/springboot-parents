package com.example.springbootkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

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
     * 引入 Kafka 的死信队列
     * 这里使用的是 Kafka 默认的监听容器类，也可以使用自定义的，使用自定义的只需要在 @KafkaListener 注解之中引入 containerFactory = “” 指定即可
     * 使用自定义的时候，对应的 Bean 最好做成配置类注入到 Spring 中
     * @param configurer
     * @param kafkaConsumerFactory
     * @param template
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> kafkaConsumerFactory, KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(template), new FixedBackOff(0, 3)));
        //factory.setCommonErrorHandler(new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template), new FixedBackOff(0, 3)));
        return factory;
    }

    /**
     * 指定对应的主题消费
     * @param record
     */
    @KafkaListener(topics = {"topic_1"})
    public void consumer_1(ConsumerRecord<?, ?> record){
        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }

    /**
     * 消费单条信息
     * 主题通过配置类指定消费，并且手动维护数据偏移量
     * @param records
     * @param ack
     */
    @KafkaListener(topics = {"#{'${spring.kafka.topics}'.split(',')}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer_2(ConsumerRecord<?, ?> records, Acknowledgment ack) {
        System.out.println("消费："+records.topic()+"-"+records.partition()+"-"+records.value());
        Optional<?> kafkaMessage = Optional.ofNullable(records);
        kafkaMessage.ifPresent(u -> {
            try {
                System.out.println("消费的消息为：" + u);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("消费异常,错误消息:{},异常信息：{}", u, e.getMessage());
            }
        });
        ack.acknowledge();
    }

    /**
     * 批量消费信息，批量消费的时候需要在配置信息里面添加批量消费的配置
     * 主题通过配置类指定消费，并且手动维护数据偏移量
     * @param records
     * @param ack
     */
    @KafkaListener(id = "consumer_3", topics = {"#{'${spring.kafka.topics}'.split(',')}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer_3(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        long startTime = System.currentTimeMillis();
        records.forEach(record -> {
            log.info(String.format("消费：topic:%s-partition:%s-offset:%s-value:%s", record.topic(), record.partition(),
                    record.offset(), record.value()));
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            kafkaMessage.ifPresent(u -> {
                try {
                    log.info(Thread.currentThread().getName() + ":" + u);
                    System.out.println("消费的消息为：" + u);
                } catch (Exception e) {
                    log.error("消费异常,错误消息:{},异常信息：{}", u, e.getMessage());
                }
            });
        });
        log.info("data size:{} kafka消费耗时 {}", records.size(), System.currentTimeMillis() - startTime);
        ack.acknowledge();
    }
}
