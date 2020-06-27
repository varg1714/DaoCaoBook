package com.daocao.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author varg
 * @date 2020/5/5 19:26
 */
@Configuration
public class SmsTopicMqConfig {

    @Bean
    public Queue smsTelCheckQueue(){
        return new Queue("smsTelCheckQueue");
    }

    @Bean
    public TopicExchange smsTelCheckExchange(){
        return new TopicExchange("smsTelCheckExchange");
    }

    @Bean
    public Binding bindSms(){
        return BindingBuilder.bind(smsTelCheckQueue()).to(smsTelCheckExchange()).with("topic.smsCheck");
    }
}
