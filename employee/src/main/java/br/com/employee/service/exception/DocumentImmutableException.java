package br.com.employee.service.exception;

public class DocumentImmutableException extends RuntimeException{
	
	private static final String DOCUMENT_IMMUTABLE = "O Document não pode ser alterado!";
	
	public DocumentImmutableException() {
		super(DOCUMENT_IMMUTABLE);
	}

}
