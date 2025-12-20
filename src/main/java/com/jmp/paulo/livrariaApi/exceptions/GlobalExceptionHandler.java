package com.jmp.paulo.livrariaApi.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErroResposta handlerOperacaoNaoPermitidaException(DataIntegrityViolationException e) {
		return new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Nao pode excluir autor com livro cadastrado",
				List.of());
	}

	@ExceptionHandler(RegistroDuplicadoException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e) {
		return ErroResposta.conflito(e.getMessage());
	}

	@ExceptionHandler(AutorNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErroResposta handleAutorNaoEncontradoException(AutorNaoEncontradoException e) {
		return new ErroResposta(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println(e);
		List<FieldError> fielErrors = e.getFieldErrors();

		List<ErroPadrao> listaErros = fielErrors.stream()
				.map(fe -> new ErroPadrao(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());

		return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validacao", listaErros);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErroResposta handleErroNaoTratadoException(Exception e) {
		e.printStackTrace(); // Ou use logger
		return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor", List.of());
	}
}
