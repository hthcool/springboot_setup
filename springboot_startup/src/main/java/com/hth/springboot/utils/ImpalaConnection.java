package com.hth.springboot.utils;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author hantenghui
 * @Date 2020-04-30 15:23
 * @Email hantenghui@tuyoogame.com
 */
@Component
@Slf4j
public class ImpalaConnection {

    @Autowired
    private DataSource dataSource;

    /**
     * 获取 impala jdbc 连接
     * @return impala jdbc 连接
     */
    public Connection getImpalaConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("获取连接出错，" + e.getMessage());
        }
        return null;
    }

    /**
     * 关闭资源，归还 conn 到连接池
     * @param rs
     */
    public void close(ResultSet rs, PreparedStatement prep, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (prep != null) rs.close();
            if (conn != null) rs.close();
        } catch (SQLException e) {
            log.error("资源关闭失败" + e.getMessage());
        }
    }
}
