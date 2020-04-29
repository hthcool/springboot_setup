package com.hth.flink.zookeeper;

/**
 * @Author hantenghui
 * @Date 2020-04-29 18:01
 * @Email hantenghui@tuyoogame.com
 */
public abstract class AbstractLock implements Lock{

    void getLock() {
        if (tryLock()) {
            System.out.println("获得锁");
        } else {
            waitLock();
            getLock();
        }
    }

    abstract boolean tryLock();
    abstract void waitLock();
}
