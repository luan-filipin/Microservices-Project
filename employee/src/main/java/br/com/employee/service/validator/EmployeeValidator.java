package br.com.employee.service.validator;

import org.springframework.stereotype.Component;

import br.com.employee.repository.EmployeeRepository;
import br.com.employee.service.exception.DocumentAlreadyExistsException;
import br.com.employee.service.exception.DocumentImmutableException;
import br.com.employee.service.exception.DocumentNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmployeeValidator {

	private final EmployeeRepository employeeRepository;
	
    public void validateDocumentExists(String document) {
        if (!employeeRepository.existsByDocument(document)) {
            throw new DocumentNotFoundException();
        }
    }

    public void validateDocumentNotExists(String document) {
        if (employeeRepository.existsByDocument(document)) {
            throw new DocumentAlreadyExistsException();
        }
    }
    
    public void validateDocumentImmutable(String originalDocument, String updateDocument) {
    	if (!originalDocument.equals(updateDocument)) {
    		throw new DocumentImmutableException();
    	}
    }
}
