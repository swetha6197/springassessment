package com.ibm.javaspring.springcurdangular.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.javaspring.springcurdangular.model.User;
import com.ibm.javaspring.springcurdangular.service.UserService;


@RestController
@RequestMapping("/api")
public class UserResourse {
	private UserService userService;

	public UserResourse(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		return userService.findAll();
	}

	@RequestMapping(value = "user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
		try {
			User result = userService.save(user);
			return ResponseEntity.created(new URI("/api/user/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) throws URISyntaxException {
		if (user.getId() == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		try {
			User result = userService.update(user);

			return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		userService.delete(id);

		return ResponseEntity.ok().build();
	}
	
}
