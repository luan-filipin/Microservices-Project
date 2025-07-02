package br.com.employee.service;


import java.util.List;

import br.com.employee.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto findEmployeeByDocument(String document);
	
	List<EmployeeDto>findAllEmployees();
	
	void deleteEmployee(String document);
	
	EmployeeDto updateEmployee(String document, EmployeeDto employeeDto);

}
