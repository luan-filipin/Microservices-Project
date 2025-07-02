package br.com.user.mapper;

import org.mapstruct.Mapper;

import br.com.user.dto.UserCreateDto;
import br.com.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserCreateDto toDto(User user);
	
	User toEntity(UserCreateDto userCreateDto);
}
