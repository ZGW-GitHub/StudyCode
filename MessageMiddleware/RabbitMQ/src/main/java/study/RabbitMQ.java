package study;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {

    public static final String QUEUE_NAME = "QUEUE";
    public static final String EXCHANGE_NAME = "EXCHANGE";

    private static Connection connection = null;
    public static Channel channel = null;

    public static Channel getChannel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("snow");

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }

    public static void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}