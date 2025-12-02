package com.jmp.paulo.livrariaApi.Repositories;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	public void salvarLivro() {
		Livro livro = new Livro();
		livro.setIsbn("984758349");
		livro.setPreco(BigDecimal.valueOf(100));
		livro.setGenero(GeneroLivro.FICCAO);
		livro.setTitulo("UFO");
		livro.setDataPublicacao(LocalDate.of(1980, 1,2));
		
		Autor autor = new Autor("Maria", LocalDate.of(1983, 2, 22), "Italiana");
		//autor = autorRepository.save(autor);

		livro.setAutor(autor);
		
		livroRepository.save(livro);
		
		System.out.println("LIVRO: " + livro);
		System.out.println("AUTOR: " + autor);

	}

}
