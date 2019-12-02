package study.demo04;

import com.rabbitmq.client.Channel;
import study.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Producer {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        channel.basicPublish(RabbitMQ.EXCHANGE_NAME, "com.demo01", null, "消息：路由1".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(RabbitMQ.EXCHANGE_NAME, "com.snow.a", null, "消息：路由2".getBytes(StandardCharsets.UTF_8));

        RabbitMQ.close();

    }

}
