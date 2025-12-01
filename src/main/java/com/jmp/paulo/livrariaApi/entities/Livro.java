package com.jmp.paulo.livrariaApi.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_livro")
@Data
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@Column(length = 20, nullable = false)
	private String isbn;
	
	@Column(length = 150, nullable = false)
	private String titulo;
	
	@Column(name="data_publicacao")
	private LocalDate dataPublicacao;
	
	@Column(length = 20, nullable = false)
	private GeneroLivro genero;
}
