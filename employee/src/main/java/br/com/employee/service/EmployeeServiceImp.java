package br.com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.employee.dto.EmployeeDto;
import br.com.employee.mapper.EmployeeMapper;
import br.com.employee.model.Employee;
import br.com.employee.repository.EmployeeRepository;
import br.com.employee.service.exception.DocumentAlreadyExistsException;
import br.com.employee.service.exception.DocumentNotFoundException;
import br.com.employee.service.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImp implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeValidator employeeValidator;
	private final EmployeeMapper employeeMapper;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		String document = employeeDto.document();
		employeeValidator.validateDocumentNotExists(document);
		Employee createdEmployee = employeeRepository.save(employeeMapper.toEntity(employeeDto));
		return employeeMapper.toDto(createdEmployee);
	}

	@Override
	public EmployeeDto findEmployeeByDocument(String document) {		
		Employee employee = employeeRepository.findByDocument(document)
				.orElseThrow(() -> new DocumentNotFoundException());
		return employeeMapper.toDto(employee);
	}

	@Override
	public List<EmployeeDto> findAllEmployees() {
		return employeeMapper.toDtoList(employeeRepository.findAll());
	}

	@Override
	public void deleteEmployee(String document) {
		employeeValidator.validateDocumentExists(document);;
		employeeRepository.deleteAllByDocument(document);
	}

	@Override
	public EmployeeDto updateEmployee(String document, EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findByDocument(document)
				.orElseThrow(() -> new DocumentNotFoundException());
		// Verifica se o user esta tentando mudar o document.
		employeeValidator.validateDocumentImmutable(employee.getDocument(), employeeDto.document());
		// Atualiza os campos da entidade com os dados do Dto.
		employeeMapper.updateFromDto(employeeDto, employee);
		Employee updateEmployee = employeeRepository.save(employee);
		return employeeMapper.toDto(updateEmployee);
	}
}
