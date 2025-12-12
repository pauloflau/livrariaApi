package com.jmp.paulo.livrariaApi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmp.paulo.livrariaApi.Mapper.Mapper;
import com.jmp.paulo.livrariaApi.dto.AutorDto;
import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.services.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

	private AutorService autorService;

	public AutorController(AutorService autorService) {
		super();
		this.autorService = autorService;
	}
	
	@PutMapping("{idAutor}")
	public ResponseEntity<Void> atualizar(@PathVariable String idAutor, @RequestBody AutorDto dto){
		UUID id = UUID.fromString(idAutor);
		Optional<Autor> buscarAutor = autorService.findById(id);
		
		if(buscarAutor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Autor autor = buscarAutor.get();
		
		autor.setNome(dto.getNome());
		autor.setDataNascimento(dto.getDataNascimento());
		autor.setNacionalidade(dto.getNacionalidade());
		
		autorService.atualizar(autor);
		return ResponseEntity.noContent().build();		
	}

	@GetMapping("/pesquisar")
	public ResponseEntity<List<AutorDto>> pesquisar(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

		List<Autor> lista = autorService.pesquisar(nome, nacionalidade);
		List<AutorDto> listaDto = new ArrayList<>();
		
		for (Autor autor : lista) {
			listaDto.add(Mapper.autorToDto(autor));
		}
		return ResponseEntity.ok(listaDto);
	}

	@DeleteMapping("/{idAutor}")
	public ResponseEntity<Void> delete(@PathVariable String idAutor) {
		UUID id = UUID.fromString(idAutor);
		Optional<Autor> autor = autorService.findById(id);
		if (autor.isPresent()) {
			autorService.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{idAutor}")
	public ResponseEntity<AutorDto> findById(@PathVariable String idAutor) {
		UUID id = UUID.fromString(idAutor);
		Optional<Autor> autor = autorService.findById(id);
		if (autor.isPresent()) {
			AutorDto dto = new AutorDto();
			dto = Mapper.autorToDto(autor.get());
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<AutorDto>> findAll() {
		List<Autor> lista = autorService.findAll();
		List<AutorDto> listaDto = new ArrayList<>();

		for (Autor autor : lista) {
			listaDto.add(Mapper.autorToDto(autor));
		}
		return ResponseEntity.ok(listaDto);

	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody AutorDto dto) {
		Autor autor = autorService.salvar(Mapper.dtoToAutor(dto));

		URI location = ServletUriComponentsBuilder
				// .fromCurrentContextPath()
				.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
