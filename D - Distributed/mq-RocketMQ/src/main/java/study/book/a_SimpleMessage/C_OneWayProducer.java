/*
      Date:  2019-12-15 17:29
                                 */
package study.book.a_SimpleMessage;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 消息类型：简单消息
 * 消息发送方式：单向发送
 * 应用：单向发送用于要求一定可靠性的场景，例如：日志收集。
 */
public class C_OneWayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {

        // 构建生产者实例
        DefaultMQProducer producer = new DefaultMQProducer("group_name_sync");
        // 设置发送消息失败重试次数
        producer.setRetryTimesWhenSendFailed(3);
        // 设置NameServer地址
        producer.setNamesrvAddr("192.168.56.101:9876;192.168.56.102:9876");
        // 启动生产者
        producer.start();

        for (int i = 0; i < 10; i++) {

            // 创建一个消息实例，指定主题、Tag、消息主题
            Message message = new Message(
                    "TopicTest",
                    "TagA",
                    ("Hello RocketMQ !" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            // 单向发送消息
            // 由于在OneWay方式发送消息时没有请求应答处理，一旦出现消息发送失败，则会因为没有重试而导致数据丢失。
            // 若数据不可丢，建议选用可靠同步或可靠异步发送方式。
            producer.sendOneway(message);

        }

        // 关闭生产者
        producer.shutdown();

    }
}
