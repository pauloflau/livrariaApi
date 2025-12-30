package com.jmp.paulo.livrariaApi.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jmp.paulo.livrariaApi.entities.Usuario;

public class CustomAuthentication implements Authentication {

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public CustomAuthentication() {

	}

	public CustomAuthentication(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public String getName() {
		return usuario.getLogin();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return usuario;
	}

	@Override
	public Object getPrincipal() {
		return usuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}

}
