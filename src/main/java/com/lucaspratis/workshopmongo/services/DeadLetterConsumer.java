package com.lucaspratis.workshopmongo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeadLetterConsumer {
	

	    @KafkaListener(topics = "Dead.letter", groupId = "user-group")
	    public void consumeDLQ(String message) {
	        System.out.println("Mensagem na DLQ: " + message);
	    }

}
