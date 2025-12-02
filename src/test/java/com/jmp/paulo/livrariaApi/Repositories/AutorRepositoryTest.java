package com.jmp.paulo.livrariaApi.Repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class AutorRepositoryTest {

	@Autowired
	AutorRepository autorRepository;

	@Autowired
	LivroRepository livroRepository;

	@Test
	@Order(0)
	public void salvarTest() {
		System.out.println("SALVAR");
		Autor autor = new Autor();
		autor.setNome("Paulo");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimento(LocalDate.of(1983, 2, 22));

		Autor autorSalvo = autorRepository.save(autor);
		System.out.println("Autor salvo: " + autorSalvo);

	}

	@Test
	@Order(2)
	public void atualizarTest() {
		System.out.println("ATUALIZAR");
		Autor autor = new Autor("Maria", LocalDate.of(1983, 2, 22), "Italiana");
		autor = autorRepository.save(autor);

		UUID id = autor.getId();
		System.out.println("ID: " + id);
		Optional<Autor> possivelAutor = autorRepository.findById(id);

		if (possivelAutor.isPresent()) {
			Autor autorEncontrado = possivelAutor.get();
			System.out.println("Dados do autor:");
			System.out.println(autorEncontrado);
			autorEncontrado.setDataNascimento(LocalDate.of(2000, 1, 30));
			autorEncontrado.setNacionalidade("Americano");
			autorRepository.save(autorEncontrado);
			System.out.println("Autor atualizado: " + autorEncontrado);
		} else {
			System.out.println("NAO ACHOU O AUTOR");
		}
	}

	@Test
	@Order(3)
	public void listarTodosAutores() {
		System.out.println("LISTAR AUTORES");
		List<Autor> autores = autorRepository.findAll();

		if (autores.isEmpty()) {
			System.out.println("Nenhum autor encontrado.");
		} else {
			System.out.println("Lista de autores:");
			autores.forEach(System.out::println);
		}
	}

	@Test
	@Order(4)
	public void countTest() {
		System.out.println("CONTAGEM");
		System.out.println("Contagem de autores: " + autorRepository.count());
	}

	@Test
	@Order(5)
	public void deletarTest() {
		System.out.println("DELETE");
		Autor autor = new Autor("carlos", LocalDate.of(1983, 2, 22), "alemao");
		autor = autorRepository.save(autor);

		UUID id = autor.getId();
		System.out.println("Dados do autor:");
		System.out.println("Autor que sera apagado: " + autor);
		System.out.println();
		System.out.println("LISTAGEM DE AUTORES ANTES DE APAGAR");
		listarTodosAutores();

		autorRepository.deleteById(id);

		System.out.println();
		System.out.println("LISTAGEM DE AUTORES DEPOIS DE APAGAR");
		listarTodosAutores();
	}

	@Test
	@Order(6)
	public void salvarAutorComLivrosTest() {
		Autor autor = new Autor();
		autor.setNome("Duda");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimento(LocalDate.of(1983, 2, 22));

		Livro livro1 = new Livro();
		livro1.setIsbn("9999999");
		livro1.setPreco(BigDecimal.valueOf(130));
		livro1.setGenero(GeneroLivro.FICCAO);
		livro1.setTitulo("UFO");
		livro1.setDataPublicacao(LocalDate.of(1980, 1, 2));

		Livro livro2 = new Livro();
		livro2.setIsbn("2222222");
		livro2.setPreco(BigDecimal.valueOf(80));
		livro2.setGenero(GeneroLivro.FANTASIA);
		livro2.setTitulo("casa assombrada");
		livro2.setDataPublicacao(LocalDate.of(1990, 1, 2));
		
		livro1.setAutor(autor);
		livro2.setAutor(autor);

		autor.setLivros(new ArrayList<>()); // crio uma lista de livros
		autor.getLivros().add(livro1); // adiciono os livros
		autor.getLivros().add(livro2);

		autorRepository.save(autor);// salvo o autor
		livroRepository.saveAll(autor.getLivros()); // salvo o livro
		System.out.println(autor.toString());
	}

	@Test
	@Order(7)
	@Transactional
	public void listarLivrosAutor() {
		Autor autor = autorRepository.findByNome("Duda");
		System.out.println("Listou livros do autor: " + autor.getLivros().toString());
		//autor.getLivros().forEach(System.out::println);
	}
}
