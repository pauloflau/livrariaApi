package com.jmp.paulo.livrariaApi.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfiguration {

	@Bean
	@Order(1)
	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {

		// habilito o authoriation server
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		// pego o configuration que habilitei acima e ativo o plugin para saber
		// informações do token
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());

		// valido os tokens que farão gerados pelo resourceServer
		http.oauth2ResourceServer(oauth2Rs -> oauth2Rs.jwt(Customizer.withDefaults()));

		// defino que vou logar com a pagina que fiz antes
		http.formLogin(configurer -> configurer.loginPage("/login"));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {// criptografar a senha
		return new BCryptPasswordEncoder(10); // defino o tamanho da senha 10
	}

	@Bean
	public TokenSettings tokenSettings() {// configuração de token
		return TokenSettings.builder().accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED) // formato do token
				.accessTokenTimeToLive(Duration.ofMinutes(60))// dura 60 min
				.build();
	}

	@Bean
	public ClientSettings clientSettings() {// configuração de client
		return ClientSettings.builder().requireAuthorizationConsent(false)// nao quero tela de consentimento
				.build();
	}

	@Bean // vou gerar um token JWK para assinar o token
	public JWKSource<SecurityContext> jwkSource() throws Exception {
		RSAKey rsaKey = gerarChaveRSA();// devo criar esse metodo tb
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	// gerar par de chaves RSA
	private RSAKey gerarChaveRSA() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		RSAPublicKey chavePublica = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey chavePrivada = (RSAPrivateKey) keyPair.getPrivate();

		return new RSAKey.Builder(chavePublica).privateKey(chavePrivada).keyID(UUID.randomUUID().toString()).build();
	}

	@Bean // metodo para decodificar o jwkSource
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
}
