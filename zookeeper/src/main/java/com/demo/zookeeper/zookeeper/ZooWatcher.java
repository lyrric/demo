package com.demo.zookeeper.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created on 2018/9/6.
 *
 * @author wangxiaodong
 */
public class ZooWatcher implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
    }
}
