package com.code.study.old;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import study.old.config.RabbitmqConfig;

@Component
public class ConsumerTest {

    @RabbitListener(queues = RabbitmqConfig.QUEUE_SMS)
    public void receive_email(String msg, Message message, Channel channel) {

        System.out.println(msg);

    }

}
