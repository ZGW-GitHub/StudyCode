/*
      Date:  2019-12-19 19:41
                                 */
package study.a_direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import study.MyConstantAndUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class MyProducer {
    public static void main(String[] args) throws IOException {

        // 创建Channel
        Channel channel = MyConstantAndUtils.getChannel();

        // 声明Exchange
        channel.exchangeDeclare(MyConstantAndUtils.EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, false, null);

        // 声明Queue
        channel.queueDeclare(MyConstantAndUtils.QUEUE_NAME, true, false, false, null);

        // 绑定交换器和队列
        channel.queueBind(MyConstantAndUtils.QUEUE_NAME, MyConstantAndUtils.EXCHANGE_NAME, MyConstantAndUtils.ROUTING_KEY_ONE);

        // 发送消息
        channel.basicPublish(MyConstantAndUtils.EXCHANGE_NAME, MyConstantAndUtils.ROUTING_KEY_ONE, null, "Hello Word!".getBytes(Charset.defaultCharset()));

        // 关闭连接和Channel
        MyConstantAndUtils.close();

    }
}
