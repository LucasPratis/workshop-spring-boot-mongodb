package com.lucaspratis.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lucaspratis.workshopmongo.domain.User;
import com.lucaspratis.workshopmongo.dto.UserDTO;
import com.lucaspratis.workshopmongo.repository.UserRepository;
import com.lucaspratis.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	public User createOrUpdate (User user) {
		User saved = repo.save(user);
		kafkaTemplate.send("register.user", saved);
		return saved;
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	

}
