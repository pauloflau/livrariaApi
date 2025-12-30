package com.jmp.paulo.livrariaApi.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jmp.paulo.livrariaApi.entities.Usuario;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	  private UsuarioService usuarioService; //para verificar o usuario
	  private PasswordEncoder encoder;

	  public CustomAuthenticationProvider(UsuarioService usuarioService, PasswordEncoder encoder) {
		super();
		this.usuarioService = usuarioService;
		this.encoder = encoder;
	  }

	  @Override
	  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		  String login = authentication.getName();
			String senhaDigitada = authentication.getCredentials().toString();
			
			Usuario usuarioEncontrado = usuarioService.obterLogin(login);
			
			if(usuarioEncontrado ==null) { 
				throw getErroUsuarioNaoEncontrado();//criei um método abaixo		
			}
				
			String senhaCriptorafada = usuarioEncontrado.getSenha();
			
		//compara a senha já criptografada se eu não criptografei posso fazer assim
			boolean senhasBatem = encoder.matches(senhaDigitada, senhaCriptorafada);
			
			if(senhasBatem) {
				return new CustomAuthentication(usuarioEncontrado);
			}
				
			throw getErroUsuarioNaoEncontrado();
	  }
	  
	  private UsernameNotFoundException getErroUsuarioNaoEncontrado() {
			return new UsernameNotFoundException("Usuario / senha incorretos!");
		  }

	  @Override
	  public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom
				(UsernamePasswordAuthenticationToken.class);
	  }

	  
}
