package com.lucaspratis.workshopmongo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {
    @Bean
    NewTopic userRegisterTopic() {
		return TopicBuilder.name("register.user").partitions(1).replicas(1).build();
	}
	
    
	
	
	

}
