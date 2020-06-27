package com.daocao.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author varg
 * @date 2020/5/6 20:46
 */
@Configuration
public class BookTopicMqConfig {

    @Bean
    public Queue bookGeneQueue(){
        return new Queue("bookGeneQueue");
    }

    @Bean
    public Queue bookDeleteQueue(){
        return new Queue("bookDeleteQueue");
    }

    @Bean
    public TopicExchange bookTopicExchange(){
        return new TopicExchange("bookTopicExchange");
    }

    @Bean
    public Binding bindBookGene(){
        return BindingBuilder.bind(bookGeneQueue()).to(bookTopicExchange()).with("topic.bookGene");
    }

    @Bean
    public Binding bindBookDelete(){
        return BindingBuilder.bind(bookDeleteQueue()).to(bookTopicExchange()).with("topic.bookDelete");
    }
}
