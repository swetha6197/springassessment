package com.ibm.javaspring.springcurdangular.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;                          
import org.springframework.stereotype.Service;

import com.ibm.javaspring.springcurdangular.model.User;
import com.ibm.javaspring.springcurdangular.repository.UserRepository;



@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public     UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save( User user) {
		if (user.getId() != null && userRepository.exists(user.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return userRepository.save(user);
	}

	public User update( User user) {
		if (user.getId() != null && !userRepository.exists(user.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return userRepository.save(user);
	}

	public List< User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}

	public void delete(Integer id) {
		userRepository.delete(id);
	}

}
