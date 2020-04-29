package com.hth.flink.kafka;

import com.hth.flink.bean.Log;
import com.hth.flink.bean.ProjectRequest;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author hantenghui
 * @Date 2020-04-28 18:46
 * @Email hantenghui@tuyoogame.com
 */
public class MyKafkaProducer {

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.hth.flink.serializer.LogSerializer");
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hthmac:9092");
        prop.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        KafkaProducer<String, Log> kafkaProducer = new KafkaProducer<>(prop);

        for (int i = 0; i < 100; i++) {

            Log log = new Log(i + "20435", System.currentTimeMillis(), "1000" + i, "test@tuyoogame.com", "track",
                    "create", new ProjectRequest(System.currentTimeMillis() + ""));

            ProducerRecord<String, Log> producerRecord = new ProducerRecord<>("topic-log",  log);

            kafkaProducer.send(producerRecord);
        }

        System.out.println("发送完毕");

    }
}
