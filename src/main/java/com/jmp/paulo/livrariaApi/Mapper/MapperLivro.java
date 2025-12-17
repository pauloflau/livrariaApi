package com.jmp.paulo.livrariaApi.Mapper;

import com.jmp.paulo.livrariaApi.dto.AutorDto;
import com.jmp.paulo.livrariaApi.dto.LivroCadastroDto;
import com.jmp.paulo.livrariaApi.dto.LivroRespostaPesquisaDto;
import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.entities.Livro;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperLivro {

	public Livro dtoToLivro(LivroCadastroDto dto) {
		Livro livro = new Livro();
		livro.setIsbn(dto.getIsbn());
		livro.setTitulo(dto.getTitulo());
		livro.setDataPublicacao(dto.getDataPublicacao());
		livro.setGenero(dto.getGenero());
		livro.setPreco(dto.getPreco());
	
		Autor autor = new Autor();
		autor.setId(dto.getIdAutor());
		livro.setAutor(autor);
		return livro;
	
	}
	
	public LivroRespostaPesquisaDto livroToDto(Livro livro) {
		LivroRespostaPesquisaDto dto = new LivroRespostaPesquisaDto();
		dto.setId(livro.getId());
		dto.setIsbn(livro.getIsbn());
		dto.setTitulo(livro.getTitulo());
		dto.setDataPublicacao(livro.getDataPublicacao());
		dto.setGenero(livro.getGenero());
		dto.setPreco(livro.getPreco());

		AutorDto autorDto = new AutorDto();
		autorDto.setId(livro.getAutor().getId());
		autorDto.setNome(livro.getAutor().getNome());
		autorDto.setDataNascimento(livro.getAutor().getDataNascimento());
		autorDto.setNacionalidade(livro.getAutor().getNacionalidade());
		
		dto.setAutor(autorDto);
		return dto;
	}
}
