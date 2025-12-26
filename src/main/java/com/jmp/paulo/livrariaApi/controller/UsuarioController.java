package com.jmp.paulo.livrariaApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.paulo.livrariaApi.Mapper.MapperUsuario;
import com.jmp.paulo.livrariaApi.dto.UsuarioDto;
import com.jmp.paulo.livrariaApi.entities.Usuario;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody UsuarioDto dto) {
		 Usuario usuario = MapperUsuario.dtoToUsuario(dto);
		 service.salvar(usuario);
	}
}
