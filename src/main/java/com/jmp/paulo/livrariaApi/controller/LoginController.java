package com.jmp.paulo.livrariaApi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jmp.paulo.livrariaApi.security.CustomAuthentication;

@Controller
public class LoginController {

	@GetMapping
	@ResponseBody
	public String paginaHome(Authentication authentication) {
		Object principal = authentication.getPrincipal();

		/* se eu entrar com o google o authentication e do tipo OAuth2AuthenticationToken
		if (principal instanceof org.springframework.security.oauth2.core.user.OAuth2User oauthUser) {
			System.out.println("DADOS DO GOOGLE " );
			System.out.println(oauthUser.getAttributes());
			return "Olá " + oauthUser.getAttribute("name"); // ou outro atributo que você queira
		}*/

		//se eu digitar usuario e senha o authentication e do tipo CustomAuthentication
		if (authentication instanceof CustomAuthentication customAuth) {
			System.out.println("DADOS PASSANDO USUARIO E SENHA: " );
			System.out.println(customAuth.getUsuario());
			System.out.println("EMAIL: " + customAuth.getUsuario().getEmail());
			System.out.println("ROLE: " + customAuth.getAuthorities());			
		}
		return "Olá " + authentication.getName();
	}
}
