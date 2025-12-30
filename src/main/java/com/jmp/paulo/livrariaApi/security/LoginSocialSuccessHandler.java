package com.jmp.paulo.livrariaApi.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jmp.paulo.livrariaApi.entities.Usuario;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UsuarioService usuarioService;

	// fiz login no google vai chamar o metodo abaixo
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// peguei o objeto OAuth2AuthenticationToken
		OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

		// dentro do objeto acima pego o Principal
		OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();

		// dentro do objeto acima pego o atributo email
		String email = oAuth2User.getAttribute("email");

		Usuario usuario = usuarioService.obterPorEmail(email);

		if (usuario == null) {// se o usuario nao esta cadastrado e vier nulo
			usuario = new Usuario(); // crio e cadastro
			usuario.setEmail(email);

			// o login só pode ter 20 caracteres e vai ter @gmail, vou pegar somente o nome
			usuario.setLogin(email.substring(0, email.indexOf("@")));
			usuario.setSenha("123");
			usuario.setRoles(List.of("OPERADOR"));// defino o usuario como operador

			// salvo o usuario novo que veio pela primeira vez pelo oogle
			usuarioService.salvar(usuario);
		}

		// recebi uma authentication mas transformo em uma CustomAuthentication
		authentication = new CustomAuthentication(usuario);

		// agora eu quero que logue via objeto CustomAuthentication
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// agora depois que logar, o sistema vai continuar com o comando abaixo
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
