package com.github.lyrric.customer;

import redis.clients.jedis.Jedis;
import static com.github.lyrric.constant.Constant.*;
/**
 * Created on 2019/4/17.
 *
 * @author wangxiaodong
 */
public class CustomerMain {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        Customer customer = new ThreadPoolCustomer(jedis);
        customer.run();
    }
}
