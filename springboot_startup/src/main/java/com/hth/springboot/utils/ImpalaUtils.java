package com.hth.springboot.utils;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hantenghui
 * @Date 2020-04-30 15:23
 * @Email hantenghui@tuyoogame.com
 */
@Component
@Slf4j
public class ImpalaUtils {

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
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            log.error("资源关闭失败" + e.getMessage());
        }
    }

    /**
     * 查询 sql
     * @param clazz 返回值类型
     * @param sql sql
     * @param params sql 入参
     * @param <T> 返回值范型
     * @return 返回值集合
     * @throws InstantiationException
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, String... params) throws InstantiationException {

        ArrayList<T> result = new ArrayList<>();

        Connection conn = getImpalaConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;

        try {
            prep = conn.prepareStatement(sql);
            if (params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    prep.setObject(i + 1, params[i]);
                }
            }
            rs = prep.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();

            Field[] fields = clazz.getDeclaredFields();

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    for (Field field : fields) {
                        if (field.getName().equalsIgnoreCase(columnName)) {
                            field.setAccessible(true);
                            field.set(t, rs.getObject(i + 1));
                        }
                    }
                }
                result.add(t);
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(rs, prep, conn);
        }
        return result;
    }
}
