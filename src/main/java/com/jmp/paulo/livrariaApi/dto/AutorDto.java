package com.jmp.paulo.livrariaApi.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class AutorDto {
	private UUID id;
	
	@NotBlank(message = "Campo obrigatorio")
	@Size(min = 1, max=100, message="campo fora do tamanho padrão")
	private String nome;
	@NotNull(message = "Campo obrigatorio")
	@Past(message= "não pode ser uma data futura")
	private LocalDate dataNascimento;
	@NotNull(message = "Campo obrigatorio")
	@Size(min = 5, max=50, message="campo fora do tamanho padrão")
	private String nacionalidade;
	
	private UUID idUsuario;
	
	public AutorDto() {
		// TODO Auto-generated constructor stub
	}



	public AutorDto(UUID id,
			@NotBlank(message = "Campo obrigatorio") @Size(min = 1, max = 100, message = "campo fora do tamanho padrão") String nome,
			@NotNull(message = "Campo obrigatorio") @Past(message = "não pode ser uma data futura") LocalDate dataNascimento,
			@NotNull(message = "Campo obrigatorio") @Size(min = 5, max = 50, message = "campo fora do tamanho padrão") String nacionalidade,
			UUID idUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.idUsuario = idUsuario;
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



	public UUID getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
	

