package com.app.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

	
	Optional<User> findByUserName(String username);
	
	boolean existsByUserName(String username);
	
	boolean existsByEmail(String email);
	
	User findByEmail(String email);
}
