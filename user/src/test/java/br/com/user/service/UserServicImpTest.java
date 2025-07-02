package br.com.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.user.dto.UserCreateDto;
import br.com.user.exception.LoginAlreadyExistsException;
import br.com.user.mapper.UserMapper;
import br.com.user.model.User;
import br.com.user.model.UserRole;
import br.com.user.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServicImpTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;
	@Mock
	private PasswordEncoder passwordEncoder;
	@InjectMocks
	private UserServiceImp userServiceImp;
	
	@Test
	void shouldCreateUserSuccessfully() {
		
		UserCreateDto userDto = new UserCreateDto("luan.filipin@hotmail.com", "6033", UserRole.ADMIN);
		
		User user = new User();
		user.setLogin("luan.filipin@hotmail.com");
		
		User savedUser = new User();
		savedUser.setId("1L");
		savedUser.setLogin("luan.filipin@hotmail.com");
				
		when(userRepository.existsByLogin(userDto.login())).thenReturn(false);
		when(userMapper.toEntity(userDto)).thenReturn(user);
		when(passwordEncoder.encode("6033")).thenReturn("encoder-password");
		when(userRepository.save(user)).thenReturn(savedUser);
		
		userServiceImp.createUser(userDto);
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(userCaptor.capture());
		
		User capturedUser = userCaptor.getValue();
		assertThat(capturedUser.getLogin()).isEqualTo("luan.filipin@hotmail.com");
		assertThat(capturedUser.getPassword()).isEqualTo("encoder-password");
		
		verify(userRepository).existsByLogin("luan.filipin@hotmail.com");
		verify(userMapper).toEntity(userDto);
		verify(passwordEncoder).encode("6033");
		
	}
	
	@Test
	void shouldThrowExceptionWhenLoginAlreadyExists() {
		
		UserCreateDto userDto = new UserCreateDto("luan.filipin@hotmail.com", "6033", UserRole.ADMIN);
		
		when(userRepository.existsByLogin(userDto.login())).thenReturn(true);
		
		assertThatThrownBy(()-> userServiceImp.createUser(userDto))
		.isInstanceOf(LoginAlreadyExistsException.class)
		.hasMessageContaining("O Login ja existe");
		
		verify(userRepository).existsByLogin(userDto.login());
		verifyNoMoreInteractions(userMapper, passwordEncoder, userRepository);
	}
}
