package study.demo04;

import com.rabbitmq.client.*;
import study.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 路由选择
 *      通过通配符，交换机模式：TOPIC（topic）
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        channel.exchangeDeclare(RabbitMQ.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.queueDeclare(RabbitMQ.QUEUE_NAME, true, false, false, null);

        channel.queueBind(RabbitMQ.QUEUE_NAME, RabbitMQ.EXCHANGE_NAME, "com.#");
//        channel.queueBind(RabbitMQ.QUEUE_NAME, RabbitMQ.EXCHANGE_NAME, "demo02");

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, StandardCharsets.UTF_8));
            }
        };

        channel.basicConsume(RabbitMQ.QUEUE_NAME, true, consumer);

    }

}
