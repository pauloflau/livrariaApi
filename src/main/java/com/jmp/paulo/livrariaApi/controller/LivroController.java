package com.jmp.paulo.livrariaApi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmp.paulo.livrariaApi.Mapper.MapperLivro;
import com.jmp.paulo.livrariaApi.dto.LivroCadastroDto;
import com.jmp.paulo.livrariaApi.dto.LivroRespostaPesquisaDto;
import com.jmp.paulo.livrariaApi.entities.Livro;
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

		// crio um location mostrando meu id
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId())
				.toUri();

		// 4. Retorna 200 OK com os dados do livro cadastrado
		return ResponseEntity.created(location).body(resposta);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroRespostaPesquisaDto>> findAll(){
		List<Livro> livros = service.buscarTudo();
		List<LivroRespostaPesquisaDto> listaDtos = new ArrayList<LivroRespostaPesquisaDto>();
		for(Livro livro: livros) {
			LivroRespostaPesquisaDto temp = new LivroRespostaPesquisaDto();
			temp = MapperLivro.livroToDto(livro);
			listaDtos.add(temp);
		}
		return ResponseEntity.ok(listaDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroRespostaPesquisaDto> obterId(@PathVariable String id) {
		UUID idLivro = UUID.fromString(id);
		Optional<Livro> livro = service.buscarId(idLivro);
		if (livro.isPresent()) {
			LivroRespostaPesquisaDto dto = new LivroRespostaPesquisaDto();
			dto = MapperLivro.livroToDto(livro.get());
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable UUID id) {

		Optional<Livro> buscarLivro = service.buscarId(id);

		if (buscarLivro.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		service.deletar(buscarLivro.get());
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	   public ResponseEntity<Void> atualizar(@PathVariable UUID id, @RequestBody LivroCadastroDto dto) {

		Optional<Livro> buscarLivro = service.buscarId(id);// busco esse id

		if (buscarLivro.isEmpty()) {// se nao achar nada
			return ResponseEntity.notFound().build();// da erro de 404
		}

		Livro livro = MapperLivro.dtoToLivro(dto);//pego meu dto e transformo p livro
			
		livro.setId(id); //incluo o id no livro
		service.atualizar(livro);//mando p classe service
			
		return ResponseEntity.noContent().build();
	   }	
}
