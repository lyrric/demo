package com.demo.zookeeper;

import com.demo.zookeeper.zookeeper.ZookeeperClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/9/6.
 *
 * @author wangxiaodong
 */
public class ZookeeperApp {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(()->{
                ZookeeperClient zookeeperClient = new ZookeeperClient();
                try {
                    zookeeperClient.init();
                    String lockPath = zookeeperClient.getLock();
                    Thread.sleep(1000);
                    zookeeperClient.realseLock(lockPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads.add(thread);
            thread.start();
        }
        for(Thread thread:threads){
            thread.join();
        }
    }
}
