package com.code.study.old.demo01;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import study.old.MyRabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 直接通信
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException {

        Channel channel = MyRabbitMQ.getChannel();

        channel.queueDeclare(MyRabbitMQ.QUEUE_NAME, true, false, false, null);

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("收到了：" + message);
            }
        };

        channel.basicConsume(MyRabbitMQ.QUEUE_NAME, true, consumer);

    }

}
