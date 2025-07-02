package br.com.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.user.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	UserDetails findByLogin(String username);
	boolean existsByLogin(String login);
}
