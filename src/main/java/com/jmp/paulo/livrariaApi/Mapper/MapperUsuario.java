package com.jmp.paulo.livrariaApi.Mapper;

import com.jmp.paulo.livrariaApi.dto.UsuarioDto;
import com.jmp.paulo.livrariaApi.entities.Usuario;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUsuario {

	public Usuario dtoToUsuario(UsuarioDto dto) {
		Usuario usuario = new Usuario();
		usuario.setLogin(dto.getLogin());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setRoles(dto.getRoles());
		return usuario;
	}
	
	public UsuarioDto usuarioToDto(Usuario usuario) {
		UsuarioDto dto = new UsuarioDto();
		dto.setLogin(usuario.getLogin());
		dto.setEmail(usuario.getEmail());
		dto.setSenha(usuario.getSenha());
		dto.setRoles(usuario.getRoles());
		return dto;
	}
}
