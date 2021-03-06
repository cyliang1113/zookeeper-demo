package com.leolab.zookeeper.demo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper 一个socket通道 是按顺序去处理的
 *
 *
 *
 */
public class Demo01 {


    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 10000, new Watcher(){
            public void process(WatchedEvent event) {
                System.out.println("new ZooKeeper Watcher receive the event: " + event);
                countDownLatch.countDown();
            }
        });
        System.out.println("zooKeeper.getState(): " + zooKeeper.getState());
        countDownLatch.await();
    }
}
