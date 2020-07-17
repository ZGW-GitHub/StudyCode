package com.code.study.old.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RabbitmqConfig {

    public static final String QUEUE_EMAIL = "queue_email";
    public static final String QUEUE_SMS = "queue_sms";
    public static final String EXCHANGE_TOPICS = "exchange_topics";

    /**
     * 交换机配置
     * @return a exchange
     */
    @Bean(EXCHANGE_TOPICS)
    public Exchange EXCHANGE_TOPICS() {
        // .durable(true)：持久化交换机
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS).durable(true).build();
    }

    // 声明队列
    @Bean(QUEUE_EMAIL)
    public Queue QUEUE_EMAIL() {
        return new Queue(QUEUE_EMAIL);
    }

    // 声明队列
    @Bean(QUEUE_SMS)
    public Queue QUEUE_SMS() {
        return new Queue(QUEUE_SMS);
    }

    /**
     * 绑定队列与交换机
     * @return the binding
     */
    public Binding EXCHANGE_QUEUE_EMAIL(@Qualifier(QUEUE_EMAIL) Queue queue, @Qualifier(EXCHANGE_TOPICS) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#").noargs();
    }
    public Binding EXCHANGE_QUEUE_SMS(@Qualifier(QUEUE_SMS) Queue queue, @Qualifier(EXCHANGE_TOPICS) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#").noargs();
    }

}
