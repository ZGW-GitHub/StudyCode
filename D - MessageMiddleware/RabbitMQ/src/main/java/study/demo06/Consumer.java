package study.demo06;

import com.rabbitmq.client.*;
import study.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQ.getChannel();

        Map<String, Object> map = new HashMap<>();
        map.put("Demo1", "demo1");
        map.put("Demo2", "demo2");
        map.put("Demo3", "demo3");
        map.put("x-match", "all");

        channel.queueDeclare(RabbitMQ.QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(RabbitMQ.EXCHANGE_NAME, BuiltinExchangeType.HEADERS);
        channel.queueBind(RabbitMQ.QUEUE_NAME, RabbitMQ.EXCHANGE_NAME, "", map);

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, StandardCharsets.UTF_8));


                channel.basicAck(envelope.getDeliveryTag(), false);

                // 1、
                // basicReject：是接收端告诉服务器这个消息我拒绝接收，不处理，可以设置是否放回到队列中还是丢掉。
                // false：直接删除，true：重新放回
                // 而且只能一次拒绝一个消息，官网中有明确说明不能批量拒绝消息，为解决批量拒绝消息才有了basicNack。
//                channel.basicReject(envelope.getDeliveryTag(), false);

                // 2、
                // basicRecover：路由不成功的消息可以使用 recovery 重新发送到队列中。
//                channel.basicRecover(false);

                // 3、
                // basicNack：可以一次拒绝N条消息，客户端可以设置 basicNack 方法的 multiple 参数为true，
                // 服务器会拒绝指定了 delivery_tag 的所有未确认的消息
                // (tag是一个64位的long值，最大值是9223372036854775807)。

            }
        };

        channel.basicConsume(RabbitMQ.QUEUE_NAME, false, consumer);

    }

}
