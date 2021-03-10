package com.code.mq.kafka.basic.msgtype;

import com.code.mq.kafka.KafkaApplicationTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * @author 愆凡
 * @date 2021/1/12 14:10
 */
@SuppressWarnings("all")
public class ConsumerTest extends KafkaApplicationTest {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;

	@Value("${spring.kafka.consumer.key-deserializer}")
	private String keyDeserializer;

	@Value("${spring.kafka.consumer.value-deserializer}")
	private String valueDeserializer;

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	@Test
	public void consumerTest() {
		// 配置消费者
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", bootstrapServers);
		properties.setProperty("group.id", groupId);
		properties.setProperty("auto.offset.reset", autoOffsetReset);
		properties.setProperty("key.deserializer", keyDeserializer);
		properties.setProperty("value.deserializer", valueDeserializer);

		// 创建消费者对象
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

		// 订阅主题
		consumer.subscribe(List.of(topic));

		// 循环拉取消息
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));

			for (ConsumerRecord<String, String> record : records) {
				System.err.println(record.toString());
			}
		}
	}

}
