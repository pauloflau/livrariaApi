package com.jmp.paulo.livrariaApi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmp.paulo.livrariaApi.Mapper.MapperLivro;
import com.jmp.paulo.livrariaApi.dto.LivroCadastroDto;
import com.jmp.paulo.livrariaApi.dto.LivroRespostaPesquisaDto;
import com.jmp.paulo.livrariaApi.entities.Livro;
import com.jmp.paulo.livrariaApi.exceptions.ErroResposta;
import com.jmp.paulo.livrariaApi.exceptions.RegistroDuplicadoException;
import com.jmp.paulo.livrariaApi.services.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {

	@Autowired
	private LivroService service;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid LivroCadastroDto dto) {
		
			// 1. Converte o DTO de entrada em uma entidade Livro
			Livro livro = MapperLivro.dtoToLivro(dto);

			// 2. Chama o service para validar e salvar o livro
			service.salvar(livro);

			// 3. Converte o Livro salvo em um DTO de resposta
			LivroRespostaPesquisaDto resposta = MapperLivro.livroToDto(livro);

			//crio um location mostrando meu id
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest() 
					.path("/{id}") 
					.buildAndExpand(livro.getId())
					.toUri(); 
					
			// 4. Retorna 200 OK com os dados do livro cadastrado
			return ResponseEntity.created(location).body(resposta);

		
	}
}
