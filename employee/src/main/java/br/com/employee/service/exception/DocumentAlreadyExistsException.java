package br.com.employee.service.exception;

public class DocumentAlreadyExistsException extends RuntimeException{

	private static final String DOCUMENT_ALREADY_EXISTS = "Este documento já está cadastrado";
	
    public DocumentAlreadyExistsException() {
        super(DOCUMENT_ALREADY_EXISTS);
    }
}
