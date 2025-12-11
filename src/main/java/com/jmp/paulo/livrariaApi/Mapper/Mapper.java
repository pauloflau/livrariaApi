package com.jmp.paulo.livrariaApi.Mapper;

import com.jmp.paulo.livrariaApi.dto.AutorDto;
import com.jmp.paulo.livrariaApi.entities.Autor;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

	public Autor dtoToAutor(AutorDto dto) {
		Autor autor = new Autor();
		autor.setNome(dto.getNome());
		autor.setDataNascimento(dto.getDataNascimento());
		autor.setNacionalidade(dto.getNacionalidade());
		return autor;
	}
	
	public AutorDto autorToDto(Autor autor) {
		AutorDto dto = new AutorDto();
		dto.setId(autor.getId());
		dto.setNome(autor.getNome());
		dto.setNacionalidade(autor.getNacionalidade());
		dto.setDataNascimento(autor.getDataNascimento());
		return dto;
	}
}
