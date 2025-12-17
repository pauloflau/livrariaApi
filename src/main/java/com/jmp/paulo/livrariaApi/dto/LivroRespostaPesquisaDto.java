package com.jmp.paulo.livrariaApi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.entities.GeneroLivro;

public class LivroRespostaPesquisaDto {

	private UUID id;
	private String isbn;
	private String titulo;
	private LocalDate dataPublicacao;
	private GeneroLivro genero;
	private BigDecimal preco;
	private AutorDto autor;
	
	public LivroRespostaPesquisaDto() {
		// TODO Auto-generated constructor stub
	}

	public LivroRespostaPesquisaDto(UUID id, String isbn, String titulo, LocalDate dataPublicacao, GeneroLivro genero,
			BigDecimal preco, AutorDto autor) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.genero = genero;
		this.preco = preco;
		this.autor = autor;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}
}
