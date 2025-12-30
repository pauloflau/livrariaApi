package com.jmp.paulo.livrariaApi.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmp.paulo.livrariaApi.entities.Usuario;
import com.jmp.paulo.livrariaApi.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	private PasswordEncoder encoder;
	public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}
	
	public void salvar(Usuario usuario) {
		String senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		repository.save(usuario);
	}
	
	public Usuario obterLogin(String login) {
		return repository.findByLogin(login).orElse(null);
	}
	
	public Usuario obterPorEmail(String email) {
		return repository.findByEmail(email).orElse(null);
	}	
}
