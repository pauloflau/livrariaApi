package com.jmp.paulo.livrariaApi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmp.paulo.livrariaApi.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID>{

}
