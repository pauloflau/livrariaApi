package com.jmp.paulo.livrariaApi.Repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.entities.GeneroLivro;
import com.jmp.paulo.livrariaApi.entities.Livro;
import com.jmp.paulo.livrariaApi.repositories.AutorRepository;
import com.jmp.paulo.livrariaApi.repositories.LivroRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LivroRepositoryTest {

	@Autowired
	LivroRepository livroRepository;

	@Autowired
	AutorRepository autorRepository;

	@Test
	@Order(0)
	public void salvarLivro() {
		Livro livro = new Livro();
		livro.setIsbn("984758349");
		livro.setPreco(BigDecimal.valueOf(100));
		livro.setGenero(GeneroLivro.FICCAO);
		livro.setTitulo("UFO");
		livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

		Autor autor = new Autor("Maria", LocalDate.of(1983, 2, 22), "Italiana");
		autor = autorRepository.save(autor);

		livro.setAutor(autor);

		livroRepository.save(livro);

		System.out.println("LIVRO: " + livro);
		System.out.println("AUTOR: " + autor);
	}

	@Test
	@Order(1)
	public void atualizarAutorDoLivroTest() {
		Livro livro = livroRepository.findByIsbn("984758349");
		
		Autor autor2 = new Autor("Jose", LocalDate.of(1983, 2, 22), "Alemao");
		autor2 = autorRepository.save(autor2);

		livro.setAutor(autor2);

		livroRepository.save(livro);

		System.out.println("Livro atualizado: " + livro);
	}

	@Test
	@Order(2)
	@Transactional
	public void buscarLivroEAutor() {
		Livro livro = livroRepository.findByIsbn("984758349");
		System.out.println("Livro: " + livro.getTitulo() );
		System.out.println("Autor: " + livro.getAutor().getNome());
	}
	
	@Test
	@Order(3)
	public void ListarLivros() {
		Autor autor = autorRepository.findByNome("Jose").get(0);
		List<Livro> livros = livroRepository.findByAutor(autor);
		autor.setLivros(livros);
		System.out.println("LISTA DE LIVROS");
		autor.getLivros().forEach(System.out::println);
	}
	
	@Test
	@Order(4)
	public void deletar() {
		Livro livro = livroRepository.findByIsbn("984758349");

		UUID id = livro.getId();
		livroRepository.deleteById(id);
	}
	

}
