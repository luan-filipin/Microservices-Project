package br.com.employee.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.employee.model.enums.EmployeeGender;
import br.com.employee.model.enums.EmployeeRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Document(collection = "employee")
public class Employee {

	@Id
	private String id;
	private String name;
	private String document;
	private LocalDate birthDate;
	
	private EmployeeGender employeeGender;
	private Address address;
}
