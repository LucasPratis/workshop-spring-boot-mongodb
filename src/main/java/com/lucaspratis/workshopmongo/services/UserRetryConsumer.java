package com.lucaspratis.workshopmongo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRetryConsumer {
	
	private final KafkaTemplate<String, String> kafkaTemplate;

    public UserRetryConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "Register.User.retry.1000", groupId = "user-group")
    public void consumeRetry1000(String message) throws InterruptedException {
        Thread.sleep(1000);
        kafkaTemplate.send("Register.User.retry.3000", message);
    }

    @KafkaListener(topics = "Register.User.retry.3000", groupId = "user-group")
    public void consumeRetry3000(String message) throws InterruptedException {
        Thread.sleep(3000);
        try {
            System.out.println("Processando tentativa final: " + message);
            if (message.contains("fail")) {
                throw new RuntimeException("Erro final!");
            }
        } catch (Exception e) {
            System.out.println("Falhou de novo, enviando para DLQ");
            kafkaTemplate.send("Dead.letter", message);
        }
    }

}
