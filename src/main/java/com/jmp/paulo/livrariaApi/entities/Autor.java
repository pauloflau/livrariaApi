package com.jmp.paulo.livrariaApi.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_autor")
@Getter
@Setter
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false)
	private LocalDate dataNascimento;
	
	@Column(length = 100, nullable = false)
	private String nacionalidade;
	
	@OneToMany(mappedBy = "autor")
	private List<Livro> livros;
}
