/*
      Date:  2019-12-20 14:22
                                 */
package study.a_direct;

import com.rabbitmq.client.*;
import study.MyConstantAndUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class MyConsumer {
    public static void main(String[] args) throws IOException {

        // 创建Channel
        Channel channel = MyConstantAndUtils.getChannel();

        // 创建消费者，处理消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, Charset.defaultCharset()));
            }
        };

        // 获取消息
        channel.basicConsume(MyConstantAndUtils.QUEUE_NAME, true, consumer);

        // 关闭连接和Channel
        MyConstantAndUtils.close();

    }
}
