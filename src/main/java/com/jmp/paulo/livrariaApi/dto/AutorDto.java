package com.jmp.paulo.livrariaApi.dto;

import java.time.LocalDate;
import java.util.UUID;

public class AutorDto {
	private UUID id;
	private String nome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	
	public AutorDto() {
		// TODO Auto-generated constructor stub
	}

	public AutorDto(String nome, LocalDate dataNascimento, String nacionalidade) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
	

