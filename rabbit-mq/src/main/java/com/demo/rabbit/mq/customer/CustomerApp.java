package com.demo.rabbit.mq.customer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * Created on 2018/6/12.
 * 消费者-消费任务
 * @author wangxiaodong
 */
public class CustomerApp {

    private static final String QUEUE_NAME = "test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("等待消息中...");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, Charset.forName("UTF-8"));
                System.out.println("收到消息:" + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
