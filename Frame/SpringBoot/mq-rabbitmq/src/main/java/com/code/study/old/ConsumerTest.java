package com.code.study.old;

import com.code.study.old.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerTest {

    @RabbitListener(queues = RabbitmqConfig.QUEUE_SMS)
    public void receive_email(String msg, Message message, Channel channel) {

        System.out.println(msg);

    }

}
