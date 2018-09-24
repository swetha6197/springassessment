package com.ibm.javaspring.springcurdangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.javaspring.springcurdangular.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	User findByName(String name);
}
