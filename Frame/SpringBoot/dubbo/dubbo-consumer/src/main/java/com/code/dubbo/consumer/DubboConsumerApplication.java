package com.code.dubbo.consumer;

import com.code.dubbo.consumer.service.EchoConsumer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 愆凡
 * <p>
 * {@link EnableDubbo @EnableDubbo} 指定要扫描的消费注解，会触发注入
 * <p>
 * 消费者
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.code.dubbo.consumer.service"})
public class DubboConsumerApplication {
	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(DubboConsumerApplication.class, args);

		EchoConsumer echoConsumer = context.getBean(EchoConsumer.class);
		String echo = echoConsumer.echo("Hello World !");

		System.out.println("result : " + echo);

		Thread.currentThread().join();

	}
}
