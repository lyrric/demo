package com.demo.zookeeper.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2018/9/6.
 *
 * @author wangxiaodong
 */
public class ZookeeperClient {

    private final String CONNECT_STR = "localhost:2181";
    private final String ROOT_LOCK_PATH = "/lock";
    private final String LOCK_NAME = "user_";
    private ZooKeeper zooKeeper;
    private String appId = String.valueOf(new Random().nextInt(100));


    /**
     * 获取连接
     * @return
     * @throws IOException
     */
    public void init() throws IOException {
        System.out.println(appId + "初始化zookeeper");
        synchronized (this){
            if (zooKeeper == null) {
                zooKeeper = new ZooKeeper(CONNECT_STR, 10000, new ZooWatcher());
                while (!zooKeeper.getState().isConnected()) {
                }
            }
        }
    }
    public ZooKeeper getClient() throws IOException {
        init();
        return zooKeeper;
    }

    public String getLock() throws KeeperException, InterruptedException {
        String lockPath = zooKeeper.create(ROOT_LOCK_PATH+"/"+LOCK_NAME, appId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(appId + "尝试获取锁, 创建节点:"+lockPath);
        tryLock(lockPath);
        return lockPath;
    }

    private void tryLock(String lockPath) throws KeeperException, InterruptedException {
        List<String> nodes = zooKeeper.getChildren(ROOT_LOCK_PATH, false);
        //排序
        Collections.sort(nodes);
        //判断当前节点是否是队列中第一个,是则获取到锁
        int index = nodes.indexOf(lockPath.substring(ROOT_LOCK_PATH.length()+1));
        if(index == 0){
            System.out.println(appId + "获取到锁, 节点为:"+lockPath);
        }else{
            //获取上一个节点
            String preLockPath = nodes.get(index - 1);
            Watcher watcher = event -> {
                if(event.getType().equals(Watcher.Event.EventType.NodeDeleted)){
                    System.out.println(appId + "检测到" + preLockPath + "被删除,重新尝试获取锁");
                    synchronized(this){
                        notifyAll();
                    }

                }
            };
            //判断上一个节点是否还存在,并监听事件
            Stat stat = zooKeeper.exists(ROOT_LOCK_PATH + "/" + preLockPath, watcher);
            if(stat == null){
                //上一个节点已经不存在(节点已经变化),则重新尝试获取锁
                tryLock(lockPath);
            }else{
                System.out.println(appId + "等待上一个节点:" + preLockPath + ",释放锁");
                synchronized (this){
                    this.wait();
                }
                System.out.println(appId + "被唤醒");
                tryLock(lockPath);
            }
        }
    }

    /**
     * 释放锁
     */
    public void realseLock(String lockPath) throws KeeperException, InterruptedException {
        zooKeeper.delete(lockPath, 0);
        System.out.println(appId + "释放锁:" + lockPath);
    }
}
