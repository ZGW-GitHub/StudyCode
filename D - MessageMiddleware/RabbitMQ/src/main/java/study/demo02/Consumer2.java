package study.demo02;

import com.rabbitmq.client.*;
import study.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer2 {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        channel.exchangeDeclare(RabbitMQ.EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        channel.queueDeclare(RabbitMQ.QUEUE_NAME + 2, true, false, false, null);

        channel.queueBind(RabbitMQ.QUEUE_NAME + 2, RabbitMQ.EXCHANGE_NAME, "");

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String mes = new String(body, StandardCharsets.UTF_8);
                System.out.println(mes);
            }
        };

        channel.basicConsume(RabbitMQ.QUEUE_NAME + 2, true, consumer);

    }

}
