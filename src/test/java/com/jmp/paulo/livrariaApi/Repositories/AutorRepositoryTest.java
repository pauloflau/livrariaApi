package com.jmp.paulo.livrariaApi.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.repositories.AutorRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutorRepositoryTest {

	@Autowired
	AutorRepository autorRepository;

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
}
