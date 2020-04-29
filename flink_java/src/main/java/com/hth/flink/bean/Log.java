package com.hth.flink.bean;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hantenghui
 * @Date 2020-04-28 19:13
 * @Email hantenghui@tuyoogame.com
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private String project_id;
    private Long event_time;
    private String user_id;
    private String device_id;
    private String type;
    private String event;
    private ProjectRequest properties;
}
