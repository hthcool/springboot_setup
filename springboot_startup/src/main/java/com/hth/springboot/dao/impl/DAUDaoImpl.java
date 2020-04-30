package com.hth.springboot.dao.impl;

import com.hth.springboot.dao.DAUDao;
import com.hth.springboot.utils.ImpalaConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author hantenghui
 * @Date 2020-04-30 10:45
 * @Email hantenghui@tuyoogame.com
 */
@Repository
public class DAUDaoImpl implements DAUDao {

    @Autowired
    private ImpalaConnection impalaConnection;

    @Override
    public String queryDAUByDayAndEvent(String day, String event) {

        String sql = "select\n" +
                    "    day, \n" +
                    "    count(distinct user_id) as dau\n" +
                    "from bicloud_log.login_log\n" +
                    "where day = ? \n" +
                    "and event_id in ('11000', '11001', '11002', '11003')\n" +
                    "and cloud_id = '59'\n" +
                    "and platform_id = '3'\n" +
                    "and product_id = '129'";

        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}
