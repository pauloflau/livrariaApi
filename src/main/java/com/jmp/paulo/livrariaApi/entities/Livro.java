package com.jmp.paulo.livrariaApi.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
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
	@JoinColumn(name = "id_autor")
	private Autor autor;

	@CreatedDate 
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dataCadastro;

	@LastModifiedDate 
	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao;

	@Override
	public String toString() {
		return "Livro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", dataPublicacao=" + dataPublicacao
				+ ", genero=" + genero + ", preco=" + preco + "]";
	}

}
