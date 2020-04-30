package com.hth.springboot.service;

import java.util.List;

/**
 * @Author hantenghui
 * @Date 2020-04-30 10:38
 * @Email hantenghui@tuyoogame.com
 */
public interface DAUService {
    List queryDauByDay(String day, String event);
}
