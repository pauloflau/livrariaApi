package com.jmp.paulo.livrariaApi.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println(e);
		List<FieldError> fielErrors = e.getFieldErrors();
		
		List<ErroPadrao> listaErros = fielErrors				
		   .stream()
		   .map(fe -> new ErroPadrao(fe.getField(), fe.getDefaultMessage()))
	 	   .collect(Collectors.toList());
				
		return new ErroResposta(
			HttpStatus.UNPROCESSABLE_ENTITY.value(), 
			"Erro de validacao",
			listaErros);
	}
}
