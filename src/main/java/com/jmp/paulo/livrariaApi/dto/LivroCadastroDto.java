package com.jmp.paulo.livrariaApi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.jmp.paulo.livrariaApi.entities.GeneroLivro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

public class LivroCadastroDto {

	@NotBlank(message = "campo obrigatório")
	private String isbn;

	@NotBlank(message = "campo obrigatório")
	private String titulo;

	//@NotBlank(message = "campo obrigatório")
	@Past(message = "nao pode ser uma data futura")
	private LocalDate dataPublicacao;
	private GeneroLivro genero;
	private BigDecimal preco;
	private UUID idAutor;

	public LivroCadastroDto() {
		// TODO Auto-generated constructor stub
	}

	public LivroCadastroDto(String isbn, String titulo, LocalDate dataPublicacao, GeneroLivro genero, BigDecimal preco,
			UUID idAutor) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.genero = genero;
		this.preco = preco;
		this.idAutor = idAutor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public GeneroLivro getGenero() {
		return genero;
	}

	public void setGenero(GeneroLivro genero) {
		this.genero = genero;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public UUID getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(UUID idAutor) {
		this.idAutor = idAutor;
	}
	
	

}
