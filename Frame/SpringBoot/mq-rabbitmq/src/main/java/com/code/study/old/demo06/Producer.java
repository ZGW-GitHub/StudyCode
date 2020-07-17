package com.code.study.old.demo06;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.code.study.old.MyRabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Producer {

    public static void main(String[] args) throws IOException {

        Channel channel = MyRabbitMQ.getChannel();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Demo1", "demo1");
        map.put("Demo2", "demo2");
        map.put("Demo3", "demo3");

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().headers(map).build();

        channel.basicPublish(MyRabbitMQ.EXCHANGE_NAME, MyRabbitMQ.QUEUE_NAME, properties, "信息".getBytes(StandardCharsets.UTF_8));

        MyRabbitMQ.close();

    }

}
