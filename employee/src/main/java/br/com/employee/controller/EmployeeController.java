package br.com.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.employee.dto.EmployeeDto;
import br.com.employee.dto.response.ResponseDeleteEmployeeDto;
import br.com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto){
		EmployeeDto createEmployee = employeeService.createEmployee(employeeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createEmployee);
	}
	
	@GetMapping("/findByDocument/{document}")
	public ResponseEntity<EmployeeDto> findByDocument(@PathVariable String document){
		EmployeeDto employee = employeeService.findEmployeeByDocument(document);
		return ResponseEntity.ok().body(employee);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<EmployeeDto>> findAllEmployees(){
		List<EmployeeDto> employees = employeeService.findAllEmployees();
		return ResponseEntity.ok().body(employees);
	}
	
	@DeleteMapping("/delete/{document}")
	public ResponseEntity<ResponseDeleteEmployeeDto> deleteEmployee(@PathVariable String document){
		employeeService.deleteEmployee(document);
		return ResponseEntity.ok().body(new ResponseDeleteEmployeeDto());
	}
	
	@PutMapping("/put/{document}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable  String document, @RequestBody @Valid EmployeeDto employeeDto){
		EmployeeDto employeeUpdate = employeeService.updateEmployee(document, employeeDto);
		return ResponseEntity.ok().body(employeeUpdate);
	}
}
