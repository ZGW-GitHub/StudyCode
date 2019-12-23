package study.old.demo03;

import com.rabbitmq.client.*;
import study.old.MyRabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 路由选择
 *      通过路由，交换机模式：DIRECT（direct）
 */
public class Consumer {

    public static void main(String[] args) throws IOException {

        Channel channel = MyRabbitMQ.getChannel();

        channel.exchangeDeclare(MyRabbitMQ.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        channel.queueDeclare(MyRabbitMQ.QUEUE_NAME, true, false, false, null);

        channel.queueBind(MyRabbitMQ.QUEUE_NAME, MyRabbitMQ.EXCHANGE_NAME, "demo01");
        channel.queueBind(MyRabbitMQ.QUEUE_NAME, MyRabbitMQ.EXCHANGE_NAME, "demo02");

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, StandardCharsets.UTF_8));
            }
        };

        channel.basicConsume(MyRabbitMQ.QUEUE_NAME, true, consumer);

    }

}
