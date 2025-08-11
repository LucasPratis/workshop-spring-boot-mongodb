package com.lucaspratis.workshopmongo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
	
	private final KafkaTemplate<String, String> kafkaTemplate;

    public UserConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "Register.User", groupId = "user-group")
    public void consume(String message) {
        try {
            System.out.println("Processando usuário: " + message);
            if (message.contains("fail")) {
                throw new RuntimeException("Erro simulado");
            }

        } catch (Exception e) {
            System.out.println("Erro, enviando para retry 1s...");
            kafkaTemplate.send("Register.User.retry.1000", message);
        }
        
    }

}
