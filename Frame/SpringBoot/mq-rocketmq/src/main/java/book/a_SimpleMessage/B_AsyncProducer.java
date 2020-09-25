/*
      Date:  2019-12-15 17:29
                                 */
package book.a_SimpleMessage;

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
 * 消息发送方式：异步发送
 * 应用：异步发送通常被用于对响应时间敏感的业务场景
 */
public class B_AsyncProducer {
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

			// 异步发送消息
			producer.send(message, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.println("发送 + " + message + " : 成功！");
				}

				@Override
				public void onException(Throwable e) {
					e.printStackTrace();
				}
			});

		}

		// 关闭生产者
		producer.shutdown();

	}
}
