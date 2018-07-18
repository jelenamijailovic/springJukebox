package com.telnet.jukebox.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telnet.jukebox.spring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findUserByEmail(String email);
	
}
