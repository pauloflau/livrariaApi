package com.jmp.paulo.livrariaApi.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmp.paulo.livrariaApi.Mapper.Mapper;
import com.jmp.paulo.livrariaApi.dto.AutorDto;
import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.services.AutorService;

@RestController
@RequestMapping("autores")
public class AutorController {
	
	private AutorService autorService;
	
	
	public AutorController(AutorService autorService) {
		super();
		this.autorService = autorService;
	}


	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody AutorDto dto) {
		Autor autor = autorService.salvar(Mapper.dtoToAutor(dto));
		
		URI location = ServletUriComponentsBuilder
		    .fromCurrentContextPath()	 
		    .path("/{id}")		 
		    .buildAndExpand(autor.getId())  
		    .toUri();                               
			
		return ResponseEntity.created(location).build();		
	}
}
