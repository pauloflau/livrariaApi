package com.jmp.paulo.livrariaApi.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	
	public Autor atualizar(Autor autor) {
		return autorRepository.save(autor);
	}
	
	public List<Autor> pesquisar(String nome, String nacionalidade){
		if(nome != null && nacionalidade != null) {
			return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
		}else if(nome == null && nacionalidade == null) {
			return autorRepository.findAll();
		}else if(nome != null && nacionalidade == null) {
			return autorRepository.findByNome(nome);
		}else{
			return autorRepository.findByNacionalidade(nacionalidade);
		}
	}
	
	public void delete(UUID id){
		autorRepository.deleteById(id);
	}
	
	public Optional<Autor> findById(UUID id){
		return autorRepository.findById(id);
	}
	
	public List<Autor> findAll(){
		return autorRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		return autorRepository.save(autor);
	}

}
