package com.hth.flink.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author hantenghui
 * @Date 2020-04-29 15:56
 * @Email hantenghui@tuyoogame.com
 * @Description
 *  zookeeper 客户端
 */
public class ZKCli {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();


    private ZooKeeper getZKCli() {



        return zk;
    }

    public static void main(String[] args) throws IOException {

    }
}
