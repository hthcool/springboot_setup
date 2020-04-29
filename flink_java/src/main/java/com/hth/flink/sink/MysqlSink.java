package com.hth.flink.sink;

import com.hth.flink.bean.Log;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author hantenghui
 * @Date 2020-04-29 10:31
 * @Email hantenghui@tuyoogame.com
 */
public class MysqlSink extends RichSinkFunction<Log> {
    private String url = "jdbc:mysql://hthmac:3306/practice?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String driver = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String passwd = "123456";
    private Connection conn;
    private PreparedStatement prep;

    private String device_id;
    private String event;
    private long event_time;
    private String project_id;
    private String user_id;
    private String type;
    private String proj_request_id;

    private IntCounter counter = new IntCounter(1);
    private long timer = System.currentTimeMillis();

    private void init() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);
        conn = DriverManager.getConnection(this.url, this.username, this.passwd);
        conn.setAutoCommit(false);
        prep = conn.prepareStatement("insert into practice.log values(?, ?, ?, ?, ?, ?, ?)");
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        init();
    }

    @Override
    public void close() throws Exception {
        if (this.prep != null) this.prep.close();
        if (this.conn != null) this.conn.close();
    }

    @Override
    public void invoke(Log data, Context context) throws Exception {
        addBatch(data);
        if (counter.getLocalValue() % 1000 == 0 || System.currentTimeMillis() - timer > 1000 * 30) {
            prep.executeBatch();
            timer = System.currentTimeMillis();
            conn.commit();
        }
    }

    private void addBatch(Log data) throws SQLException {
        counter.add(1);

        device_id = data.getDevice_id();
        event = data.getEvent();
        event_time = data.getEvent_time();
        project_id = data.getProject_id();
        user_id = data.getUser_id();
        type = data.getType();
        proj_request_id = data.getProperties().getProj_request_id();

        prep.setString(1, device_id);
        prep.setString(2, event);
        prep.setLong(3, event_time);
        prep.setString(4, project_id);
        prep.setString(5, user_id);
        prep.setString(6, type);
        prep.setString(7, proj_request_id);

        prep.addBatch();
    }
}
