package com.jmp.paulo.livrariaApi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.entities.Livro;
import com.jmp.paulo.livrariaApi.exceptions.AutorNaoEncontradoException;
import com.jmp.paulo.livrariaApi.repositories.AutorRepository;
import com.jmp.paulo.livrariaApi.repositories.LivroRepository;
import com.jmp.paulo.livrariaApi.validador.LivroValidador;

@Service
public class LivroService {

	private LivroRepository livroRepository;
	private AutorRepository autorRepository;
	private LivroValidador validador;

	public LivroService(LivroRepository livroRepository, AutorRepository autorRepository, LivroValidador validador) {
		super();
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
		this.validador = validador;
	}

	public Optional<Livro> buscarId(UUID id) {
		return livroRepository.findById(id);
	}
	
	public List<Livro> buscarTudo() {
		return livroRepository.findAll();
	}
	
	public Livro salvar(Livro livro) {

		Autor autor = autorRepository.findById(livro.getAutor().getId())
				.orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));

		livro.setAutor(autor);
		
		validador.validar(livro);
		return livroRepository.save(livro);
	}
	
	public void deletar(Livro livro){
		livroRepository.delete(livro);
	}
	
	public void atualizar(Livro livro) {
	    Livro livroExistente = livroRepository.findById(livro.getId())
		.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

	    livroExistente.setTitulo(livro.getTitulo());
	    livroExistente.setIsbn(livro.getIsbn());
	    livroExistente.setGenero(livro.getGenero());
	    livroExistente.setDataPublicacao(livro.getDataPublicacao());
	    livroExistente.setPreco(livro.getPreco());

	    // Atualiza o autor corretamente
	    Autor autor = autorRepository.findById(livro.getAutor().getId())
		.orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
	    livroExistente.setAutor(autor);

	    // Não altera dataCadastro! Mantém o valor já salvo

	    validador.validar(livro);
	    livroRepository.save(livroExistente);
	  }
}