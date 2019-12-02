package study.demo02;

import com.rabbitmq.client.Channel;
import study.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Producer {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        String mes = "消息";

        for (int i = 0; i < 10; i++) {
            channel.basicPublish(RabbitMQ.EXCHANGE_NAME, "", null, mes.getBytes(StandardCharsets.UTF_8));
        }

        RabbitMQ.close();

    }

}
