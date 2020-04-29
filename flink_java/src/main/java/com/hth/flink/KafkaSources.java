package com.hth.flink;

import com.hth.flink.bean.Log;
import com.hth.flink.serializer.LogSchema;
import com.hth.flink.sink.MysqlSink;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @Author hantenghui
 * @Date 2020-04-28 17:55
 * @Email hantenghui@tuyoogame.com
 */
public class KafkaSources {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties prop = new Properties();

        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hthmac:9092");
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_source");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.hth.flink.serializer.LogDeserializer");

        FlinkKafkaConsumer<Log> flinkKafkaConsumer = new FlinkKafkaConsumer<>("topic-log", new LogSchema(), prop);

        DataStreamSource<Log> input = env.addSource(flinkKafkaConsumer).setParallelism(3);

        KeyedStream<Log, Tuple> keyedStream = input.keyBy("user_id");

        keyedStream.addSink(new MysqlSink()).setParallelism(3).name("mysql_sink");

        env.execute("kafka source mysql sink");
    }
}
