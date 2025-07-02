package br.com.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.user.dto.UserCreateDto;
import br.com.user.exception.LoginAlreadyExistsException;
import br.com.user.mapper.UserMapper;
import br.com.user.model.User;
import br.com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService{

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public void createUser(UserCreateDto userCreateDto) {
		
		if(userRepository.existsByLogin(userCreateDto.login())) {
			throw new LoginAlreadyExistsException();
		}
		
		User user = userMapper.toEntity(userCreateDto);
		user.setPassword(passwordEncoder.encode(userCreateDto.password()));
		userRepository.save(user);
	}
}
