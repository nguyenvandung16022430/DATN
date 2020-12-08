package com.rabbitmq.demo.messagingrabbitmq;


import java.util.HashMap;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingRabbitmqApplication {

  public static final String topicExchangeName = "spring-boot-exchange-input";
  public static final String outQueue = "spring-boot-exchange-output";
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }
  @Bean
  HashMap<String,String> creathMap() {
	 return  new HashMap<String, String>();  
  }
  @Bean
  Queue queue() {
    return new Queue(outQueue, false);
  }
  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setMessageListener(listenerAdapter);
    container.setQueueNames(outQueue);
    return container;
  }
  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
	  MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver, "receiveMessage");
	  return listenerAdapter;
  }
}