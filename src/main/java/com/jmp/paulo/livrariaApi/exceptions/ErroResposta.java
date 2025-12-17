package com.jmp.paulo.livrariaApi.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ErroResposta {
	private int status;
	private String mensagem;
	private List<ErroPadrao> erros;
	
	public ErroResposta() {
		// TODO Auto-generated constructor stub
	}

	
	public ErroResposta(int status, String mensagem, List<ErroPadrao> erros) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.erros = erros;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<ErroPadrao> getErros() {
		return erros;
	}

	public void setErros(List<ErroPadrao> erros) {
		this.erros = erros;
	}
	
	public static ErroResposta respostaPadrao(String mensagem) {
		 return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
	 }
		
	 //para erros de conflito
	 public static ErroResposta conflito(String mensagem) {
	   return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
	 }
}

