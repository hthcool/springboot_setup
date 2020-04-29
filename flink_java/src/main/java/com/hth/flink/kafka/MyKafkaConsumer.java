package com.hth.flink.kafka;

import com.hth.flink.bean.Log;
import com.hth.flink.serializer.LogDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.HashSet;
import java.util.Properties;

/**
 * @Author hantenghui
 * @Date 2020-04-29 09:30
 * @Email hantenghui@tuyoogame.com
 */
public class MyKafkaConsumer {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hthmac:9092");
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "idea_consumer");
        prop.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.hth.flink.serializer.LogDeserializer");

        KafkaConsumer<String, Log> kafkaConsumer = new KafkaConsumer<>(prop);

        HashSet<String> topics = new HashSet<>();
        topics.add("topic-log");
        kafkaConsumer.subscribe(topics);
        int count = 0;
        while (count++ < 100) {
            ConsumerRecords<String, Log> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            consumerRecords.forEach((consumerRecord) -> {
                System.out.println(consumerRecord.value());
            });
        }

        kafkaConsumer.close();
    }
}
