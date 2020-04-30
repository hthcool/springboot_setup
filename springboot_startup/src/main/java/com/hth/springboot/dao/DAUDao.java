package com.hth.springboot.dao;

import com.hth.springboot.bean.response.Response;

import java.util.List;

/**
 * @Author hantenghui
 * @Date 2020-04-30 10:44
 * @Email hantenghui@tuyoogame.com
 */
public interface DAUDao {
    List queryDAUByDayAndEvent(String day, String event);
}
