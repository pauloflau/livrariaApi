package com.jmp.paulo.livrariaApi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmp.paulo.livrariaApi.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID>{

}
