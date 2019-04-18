package com.github.lyrric.customer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.*;

import static com.github.lyrric.constant.Constant.*;
/**
 * Created on 2019/4/17.
 *
 * @author wangxiaodong
 */
public class ThreadPoolCustomer extends Customer {

    private ExecutorService executorService;

    public ThreadPoolCustomer(Jedis jedis) {
        super(jedis);
        ThreadFactory threadFactory =  new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        executorService = new ThreadPoolExecutor(0, 20,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory);
    }

    @Override
    public void run(){
        while(true){
            try {
                List<String> stringList = jedis.blpop(0, KEY);
                System.out.println("收到消息：" + stringList + "，交给线程池处理");
                executorService.submit(new TaskRun(stringList));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 处理任务
     */
    class TaskRun extends Thread{
        private List<String> msg;

        TaskRun(List<String> msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            super.run();
            try {
                //假设每个要处理100毫秒
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消息：" + msg + "，处理完毕！----" + Thread.currentThread().getName());
        }
    }
}
