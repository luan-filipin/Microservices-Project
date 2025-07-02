package br.com.employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.employee.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

	boolean existsByDocument(String document);

	Optional<Employee> findByDocument(String document);

	void deleteAllByDocument(String document);
	
}
