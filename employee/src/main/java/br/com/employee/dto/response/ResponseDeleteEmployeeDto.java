package br.com.employee.dto.response;

public record ResponseDeleteEmployeeDto(String message) {

	public static final String DELETE_EMPLOYEE = "Employee deleted sucessfully";
	
	public ResponseDeleteEmployeeDto() {
		this(DELETE_EMPLOYEE);
	}
}
