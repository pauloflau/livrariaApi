package com.jmp.paulo.livrariaApi.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.entities.Livro;
import com.jmp.paulo.livrariaApi.exceptions.AutorNaoEncontradoException;
import com.jmp.paulo.livrariaApi.repositories.AutorRepository;
import com.jmp.paulo.livrariaApi.repositories.LivroRepository;

@Service
public class LivroService {

	private LivroRepository livroRepository;
	private AutorRepository autorRepository;

	public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
		super();
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
	}

	public Optional<Livro> buscarId(UUID id) {
		return livroRepository.findById(id);
	}
	
	public Livro salvar(Livro livro) {

		Autor autor = autorRepository.findById(livro.getAutor().getId())
				.orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));

		livro.setAutor(autor);

		return livroRepository.save(livro);
	}
	
	public void deletar(Livro livro){
		livroRepository.delete(livro);
	}
}