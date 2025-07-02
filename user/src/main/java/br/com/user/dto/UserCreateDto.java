package br.com.user.dto;

import br.com.user.model.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(
		@NotBlank(message = "O campo login é obrigatorio") String login,
		@NotBlank(message = "O campo senha é obrigatoria") String password,
		UserRole role) {

}
