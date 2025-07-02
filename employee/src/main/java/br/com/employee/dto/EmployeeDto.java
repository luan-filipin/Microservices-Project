package br.com.employee.dto;

import java.time.LocalDate;

import br.com.employee.model.Address;
import br.com.employee.model.enums.EmployeeGender;
import br.com.employee.model.enums.EmployeeRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeDto(
		@NotBlank(message = "O campo name é obrigatorio") String name,
		@NotBlank(message = "O campo document é obrigatorio") String document,
		@NotNull(message = "O campo birthDate é obrigatório") LocalDate birthDate,
		@NotNull(message = "O campo gender é obrigatório") EmployeeGender employeeGender,
		@Valid // Valida os campos internos do Address.
		@NotNull(message = "O campo address é obrigatório") Address address) {
}
