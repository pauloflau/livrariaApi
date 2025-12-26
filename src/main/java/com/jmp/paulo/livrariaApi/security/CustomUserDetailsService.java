package com.jmp.paulo.livrariaApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jmp.paulo.livrariaApi.entities.Usuario;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

public class CustomUserDetailsService implements UserDetailsService {

	
	private UsuarioService service;
	
	
	public CustomUserDetailsService(UsuarioService service) {
		super();
		this.service = service;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Usuario usuario = service.obterLogin(username);//método q compara o login
			
		    if(usuario==null) {
		    	throw new UsernameNotFoundException("Usuário não encontrado");
		    }

		//a classe user abaixo ela implementa a interface UserDetailsService que devo retornar, e usei um builder assim retorno o objeto completo
		    return User.builder()
		    		.username(usuario.getLogin())
		    		.password(usuario.getSenha())
		    		.roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
		    		.build();
	}	
}


