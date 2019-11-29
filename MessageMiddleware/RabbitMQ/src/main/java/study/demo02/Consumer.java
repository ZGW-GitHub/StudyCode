package com.snow.rabbitmq.demo02;

import com.rabbitmq.client.*;
import com.snow.rabbitmq.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 广播模式（发布/订阅）
 *      多队列订阅同一个交换机，交换机模式为广播模式：FANOUT（fanout）
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        channel.exchangeDeclare(RabbitMQ.EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        channel.queueDeclare(RabbitMQ.QUEUE_NAME + 1, true, false, false, null);

        channel.queueBind(RabbitMQ.QUEUE_NAME + 1, RabbitMQ.EXCHANGE_NAME, "");

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String mes = new String(body, StandardCharsets.UTF_8);
                System.out.println(mes);
            }
        };

        channel.basicConsume(RabbitMQ.QUEUE_NAME + 1, true, consumer);

    }

}
