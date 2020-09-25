package book.b_TimeMessage;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class Producer {
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

			// 预设值的时间长度（1s/5s/10s/30s/1m/2m/3m/4m/5m/6m/7m/8m/9m/10m/20m/30m/1h/2h）
			// 此消息将在10秒后传递给消费者。
			message.setDelayTimeLevel(3);
			producer.send(message);

		}

		// 关闭生产者
		producer.shutdown();

	}
}
