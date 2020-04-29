package com.hth.flink;

import com.hth.flink.bean.Log;
import com.hth.flink.serializer.LogSchema;
import com.hth.flink.sink.MysqlSink;
import org.apache.flink.api.common.functions.IterationRuntimeContext;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
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

        DataStreamSource<Log> input = env.addSource(flinkKafkaConsumer);

        input.addSink(new MysqlSink()).setParallelism(1);

        env.execute("kafka source mysql sink");
    }
}
