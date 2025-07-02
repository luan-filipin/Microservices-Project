package br.com.employee.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.employee.dto.response.ErrorResponseDto;
import br.com.employee.service.exception.DocumentAlreadyExistsException;
import br.com.employee.service.exception.DocumentImmutableException;
import br.com.employee.service.exception.DocumentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Exception generica para quando nao houver tratamento.
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponseDto> handleGeneric(RuntimeException ex, HttpServletRequest request) {
		ErrorResponseDto error = new ErrorResponseDto("Erro inesperado: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(DocumentNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleDocumentNotFound(DocumentNotFoundException ex,
			HttpServletRequest request) {
		ErrorResponseDto error = new ErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DocumentAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleDocumentAreadyExistsException(DocumentAlreadyExistsException ex,
			HttpServletRequest request) {
		ErrorResponseDto error = new ErrorResponseDto(ex.getMessage(), HttpStatus.CONFLICT.value(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(DocumentImmutableException.class)
	public ResponseEntity<ErrorResponseDto>handleDocumentImmutableException(DocumentImmutableException ex, 
			HttpServletRequest request) {
		ErrorResponseDto error = new ErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
}
