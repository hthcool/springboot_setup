package com.hth.flink.serializer;

import com.alibaba.fastjson.JSON;
import com.hth.flink.bean.Log;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author hantenghui
 * @Date 2020-04-28 19:58
 * @Email hantenghui@tuyoogame.com
 * flink 消费kafka中序列化后数据
 */
public class LogSchema implements DeserializationSchema<Log>, SerializationSchema<Log> {
    private final String encoding = "UTF8";

    @Override
    public Log deserialize(byte[] bytes) throws IOException {
        return JSON.parseObject(bytes, Log.class);
    }

    @Override
    public boolean isEndOfStream(Log log) {
        return false;
    }

    @Override
    public byte[] serialize(Log log) {
        try {
            return JSON.toJSONString(log).getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public TypeInformation<Log> getProducedType() {
        return TypeInformation.of(Log.class);
    }
}
