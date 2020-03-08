/*
      Date:  2019-12-17 18:43
                                 */
package dubbo.consumer;

import dubbo.consumer.service.EchoConsumer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * @author NotUpToYou
 *
 * EnableDubbo 指定要扫描的消费注解，会触发注入
 *
 * 消费者
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"dubbo.consumer.service"})
public class DubboConsumerApplication {
    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext context = SpringApplication.run(DubboConsumerApplication.class, args);

        EchoConsumer echoConsumer = context.getBean(EchoConsumer.class);
        String echo = echoConsumer.echo("Hello World !");

        System.out.println("result : " + echo);

        System.in.read();

    }
}
