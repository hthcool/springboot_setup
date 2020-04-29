package com.hth.flink.serializer;

import com.alibaba.fastjson.JSON;
import com.hth.flink.bean.Log;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author hantenghui
 * @Date 2020-04-28 19:22
 * @Email hantenghui@tuyoogame.com
 *  kafka 自定义序列化
 */
public class LogSerializer implements Serializer<Log>{

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Log log) {
        try {
            return JSON.toJSONString(log).getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Log data) {
        try {
            return JSON.toJSONString(data).getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
