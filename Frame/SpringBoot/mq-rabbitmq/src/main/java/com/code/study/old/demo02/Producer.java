package com.code.study.old.demo02;

import com.rabbitmq.client.Channel;
import com.code.study.old.MyRabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Producer {

    public static void main(String[] args) throws IOException {

        Channel channel = MyRabbitMQ.getChannel();

        String mes = "消息";

        for (int i = 0; i < 10; i++) {
            channel.basicPublish(MyRabbitMQ.EXCHANGE_NAME, "", null, mes.getBytes(StandardCharsets.UTF_8));
        }

        MyRabbitMQ.close();

    }

}
