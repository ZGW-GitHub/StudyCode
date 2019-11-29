package com.snow.rabbitmq.demo01;

import com.rabbitmq.client.Channel;
import com.snow.rabbitmq.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Producer {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        String message = "雄安锡";

        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", RabbitMQ.QUEUE_NAME, null, (message + i).getBytes(StandardCharsets.UTF_8));
        }

        RabbitMQ.close();

    }

}
