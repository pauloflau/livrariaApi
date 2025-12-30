package com.jmp.paulo.livrariaApi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jmp.paulo.livrariaApi.entities.Usuario;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

@Component
public class SecurityService {
	private UsuarioService usuarioService;

	public SecurityService(UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;
	}

	public Usuario obterUsuarioLogado() {

		 Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

		 if(authentication instanceof CustomAuthentication customAuth) {
				return customAuth.getUsuario();
			}
		

	        return null;
	}
}
