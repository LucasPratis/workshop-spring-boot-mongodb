package com.lucaspratis.workshopmongo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.lucaspratis.workshopmongo.domain.User;
import com.lucaspratis.workshopmongo.repository.UserRepository;

@Component
public class UserRegisterListener {
	
	@Autowired
	private UserRepository repo;
	
	@KafkaListener(topics = "register.user", groupId = "user-group")
	public void listen(User user) {
		repo.save(user);
	}


}
