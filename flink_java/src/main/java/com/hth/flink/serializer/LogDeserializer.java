package com.hth.flink.serializer;

import com.alibaba.fastjson.JSON;
import com.hth.flink.bean.Log;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @Author hantenghui
 * @Date 2020-04-28 19:48
 * @Email hantenghui@tuyoogame.com
 */
public class LogDeserializer implements Deserializer<Log> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Log deserialize(String s, byte[] bytes) {
        return JSON.parseObject(bytes, Log.class);
    }

    @Override
    public Log deserialize(String topic, Headers headers, byte[] data) {
        return JSON.parseObject(data, Log.class);
    }

    @Override
    public void close() {

    }
}
