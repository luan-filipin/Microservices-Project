package br.com.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.user.dto.ResponseCreateUserDto;
import br.com.user.dto.UserCreateDto;
import br.com.user.exception.GlobalExceptionHandler;
import br.com.user.exception.LoginAlreadyExistsException;
import br.com.user.model.UserRole;
import br.com.user.service.UserServiceImp;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	private MockMvc mockMvc;
	@Mock
	private UserServiceImp userServiceImp;
	@InjectMocks
	private UserController userController;
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setControllerAdvice(new GlobalExceptionHandler())
				.build();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	void shouldReturnCreatedResponseWhenUserIsCreated() throws Exception{
		
		UserCreateDto userCreateDto = new UserCreateDto("luan.filipin@hotmail.com",	"6033",	UserRole.ADMIN);
		
		String userJson = objectMapper.writeValueAsString(userCreateDto);
        
        mockMvc.perform(post("/api/user/create")
        		.contentType("application/json")
        		.content(userJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.message").value(ResponseCreateUserDto.CREATE_WITH_SUCESS));
        
        verify(userServiceImp).createUser(argThat(dto -> dto.login().equals("luan.filipin@hotmail.com")));
	}
	
	@Test
	void shouldReturnBadRequestWhenLoginAlreadyExists() throws Exception{
		
		UserCreateDto userCreateDto = new UserCreateDto("luan.filipin@hotmail.com",	"6033",	UserRole.ADMIN);

	    String userJson = objectMapper.writeValueAsString(userCreateDto);
	    
	    doThrow(new LoginAlreadyExistsException()).when(userServiceImp).createUser(any(UserCreateDto.class));
	    
	    mockMvc.perform(post("/api/user/create")
	    		.contentType("application/json")
	    		.content(userJson))
	    .andExpect(status().isBadRequest())
	    .andExpect(content().string(org.hamcrest.Matchers.containsString("O Login ja existe")));

	    verify(userServiceImp).createUser(any(UserCreateDto.class));
	}
}
