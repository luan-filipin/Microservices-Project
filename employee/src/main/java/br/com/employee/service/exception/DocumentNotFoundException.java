package br.com.employee.service.exception;

public class DocumentNotFoundException extends RuntimeException{
	
	private static final String DOCUMENT_NOT_FOUND = "Este documento não existe"; 
	
	public DocumentNotFoundException() {
		super(DOCUMENT_NOT_FOUND);
	}

}
