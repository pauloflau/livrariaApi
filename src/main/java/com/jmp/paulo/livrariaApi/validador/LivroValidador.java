package com.jmp.paulo.livrariaApi.validador;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jmp.paulo.livrariaApi.entities.Livro;
import com.jmp.paulo.livrariaApi.exceptions.RegistroDuplicadoException;
import com.jmp.paulo.livrariaApi.repositories.LivroRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroValidador {
  private final LivroRepository repository;
	
  public void validar(Livro livro) {
	if (existeIsbnDuplicado(livro)) {
        throw new RegistroDuplicadoException("ISBN já cadastrado!");
      }
  }
	
  private boolean existeIsbnDuplicado(Livro livro) {

	//se voltar um null e porque não encontrou nenhum livro
      Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

      if (livro.getId() == null) { //se id do livro e null então e um novo livro

//o metodo isPresent volta true se tiver algo e false se for vazio
        return livroEncontrado.isPresent();
      }

      // Atualização de livro existente
      return livroEncontrado //retorna true ou false
        .map(encontrado -> !encontrado.getId().equals(livro.getId()))
        .orElse(false);
  }
}
