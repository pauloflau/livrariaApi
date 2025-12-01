package com.jmp.paulo.livrariaApi.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_livro")
@Data
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(length = 20, nullable = false)
	private String isbn;

	@Column(length = 150, nullable = false)
	private String titulo;

	@Column(name = "data_publicacao")
	private LocalDate dataPublicacao;

	@Enumerated(EnumType.STRING)
	@Column(length = 39, nullable = false)
	private GeneroLivro genero;

	@Column(precision = 18, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(name="id_autor")
	private Autor autor;

}
