package com.jmp.paulo.livrariaApi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmp.paulo.livrariaApi.entities.Autor;



@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID>{
	List<Autor> findByNome(String nome);
	List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);
	List<Autor> findByNomeOrNacionalidade(String nome, String nacionalidade);
	List<Autor> findByNacionalidade(String nacionalidade);
}
