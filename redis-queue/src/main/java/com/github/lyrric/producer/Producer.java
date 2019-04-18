package com.github.lyrric.producer;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static com.github.lyrric.constant.Constant.*;
/**
 * Created on 2019/4/17.
 *
 * @author wangxiaodong
 */
public class Producer {

    private Jedis jedis;

    public Producer(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run(){
        int total = 0;
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.print("请输入要发送的消息数量：");
            try {
                String str = is.readLine();
                int amount = Integer.parseInt(str);
                for(int i = 0;i < amount; i++){
                    jedis.rpush(KEY,"This is a msg! no = "+ (++total));
                }
                System.out.println("发送成功，发送数量："+ str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
