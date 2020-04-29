package com.hth.flink.zookeeper;

/**
 * @Author hantenghui
 * @Date 2020-04-29 17:59
 * @Email hantenghui@tuyoogame.com
 */
public interface Lock {
    boolean lock();
    void unLock();
}
