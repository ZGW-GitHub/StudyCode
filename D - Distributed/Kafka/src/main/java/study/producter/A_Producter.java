/*
      Date:  2019-12-23 23:09
                                 */
package study.producter;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 同步发送消息
 */
public class A_Producter {
    public static void main(String[] args) {

        // 创建Properties对象用于配置生产者
        Properties properties = new Properties();
        // 设置生产者属性
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 创建ProducerRecord对象封装消息
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("CustomerCountry", "Precision Products", "France");

        // 调用生产者对象发送消息
        Future<RecordMetadata> future = producer.send(producerRecord);

        // 获取发送结果
        try {
            RecordMetadata metadata = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
