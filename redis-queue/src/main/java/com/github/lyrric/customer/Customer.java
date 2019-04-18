package com.github.lyrric.customer;

import redis.clients.jedis.Jedis;

/**
 * Created on 2019/4/17.
 *
 * @author wangxiaodong
 */
public abstract class Customer {

    protected Jedis jedis;

    public Customer(Jedis jedis) {
        this.jedis = jedis;
    }
    /**
     * 运行
     */
    public abstract void run();
}
