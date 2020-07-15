/*
      Date:  2019-12-19 19:38
                                 */
package com.code.study;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author NotUpToYou
 *
 * 常量和工具
 */
public class MyConstantAndUtils {

    public static final String QUEUE_NAME = "QUEUE";
    public static final String EXCHANGE_NAME = "EXCHANGE";

    public static final String ROUTING_KEY_ONE = "RoutingKey_one";
    public static final String ROUTING_KEY_TWO = "RoutingKey_two";
    public static final String ROUTING_KEY_THREE = "RoutingKey_three";

    private static Connection connection = null;
    public static Channel channel = null;

    /**
     * 获取通道
     * @return Channel
     */
    public static Channel getChannel() {
        ConnectionFactory factory = new ConnectionFactory();

        // factory.setUri("amqp://username:password@ipAddress:portNumber/virtualHost");
        factory.setHost("192.168.56.1");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("/");

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * 关闭通道和连接
     */
    public static void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
