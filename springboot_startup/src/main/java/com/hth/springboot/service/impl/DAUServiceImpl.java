package com.hth.springboot.service.impl;

import com.hth.springboot.dao.DAUDao;
import com.hth.springboot.service.DAUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hantenghui
 * @Date 2020-04-30 10:39
 * @Email hantenghui@tuyoogame.com
 */
@Service
public class DAUServiceImpl implements DAUService {

    @Autowired
    private DAUDao dauDao;

    @Override
    public String queryDauByDay(String day, String event) {
        return dauDao.queryDAUByDayAndEvent(day, event);
    }
}
