package com.jmp.paulo.livrariaApi.services;
import org.springframework.stereotype.Service;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.repositories.AutorRepository;

@Service
public class AutorService {
	
	private AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}
	
	public Autor salvar(Autor autor) {
		return autorRepository.save(autor);
	}

}
