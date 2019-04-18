package com.github.lyrric.producer;

import redis.clients.jedis.Jedis;
import static com.github.lyrric.constant.Constant.*;
/**
 * Created on 2019/4/17.
 *
 * @author wangxiaodong
 */
public class ProducerMain {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        new Producer(jedis).run();
    }

}
