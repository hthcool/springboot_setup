package com.hth.springboot.bean.response;

import com.hth.springboot.bean.status.InsertStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author hantenghui
 * @Date 2020-04-30 17:46
 * @Email hantenghui@tuyoogame.com
 */

@Data
@Component
public class Response<T> {
    private InsertStatus status;
    private T data;
}
