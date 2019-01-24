package com.example.camunda.cainao.demo;

import com.example.camunda.cainao.demo.domain.ReceiveMessage;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Configurations {

    private static final String QUEUE_NAME  = "queue-pubsub-test";

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListener messageListener) {
        SimpleMessageListenerContainer simpleListener = new SimpleMessageListenerContainer();
        simpleListener.setConnectionFactory(connectionFactory);
        simpleListener.setMessageListener(messageListener);
        simpleListener.setQueueNames(QUEUE_NAME);

        return simpleListener;
    }

    @Bean
    MessageListener messageListener() {
        return new ReceiveMessage();
    }
}
