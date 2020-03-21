package study.old.demo02;

import com.rabbitmq.client.*;
import study.old.MyRabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer2 {

    public static void main(String[] args) throws IOException {

        Channel channel = MyRabbitMQ.getChannel();

        channel.exchangeDeclare(MyRabbitMQ.EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        channel.queueDeclare(MyRabbitMQ.QUEUE_NAME + 2, true, false, false, null);

        channel.queueBind(MyRabbitMQ.QUEUE_NAME + 2, MyRabbitMQ.EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String mes = new String(body, StandardCharsets.UTF_8);
                System.out.println(mes);
            }
        };

        channel.basicConsume(MyRabbitMQ.QUEUE_NAME + 2, true, consumer);

    }

}