package com.lucaspratis.workshopmongo.listener;

import org.springframework.stereotype.Component;

import com.lucaspratis.workshopmongo.domain.User;
import com.lucaspratis.workshopmongo.repository.UserRepository;

@Component
public class UserRegisterListener {
	
	private UserRepository repo;
	
	public void listen(User user) {
		repo.save(user);
	}


}
