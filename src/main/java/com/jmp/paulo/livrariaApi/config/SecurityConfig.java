package com.jmp.paulo.livrariaApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.jmp.paulo.livrariaApi.security.CustomUserDetailsService;
import com.jmp.paulo.livrariaApi.security.LoginSocialSuccessHandler;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSuccessHandler successHandler)
			throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.headers(headers -> headers.frameOptions(frame -> frame.disable()))

				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())

				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/h2-console/**").permitAll();

					authorize.requestMatchers("/usuarios/**").permitAll();
					authorize.requestMatchers("/login").permitAll();
					authorize.anyRequest().authenticated();

				})
				// .oauth2Login(Customizer.withDefaults())
				.oauth2Login(oauth2 -> {
					oauth2.successHandler(successHandler);
				})
				// habilito o jwt para autenticar o usuario
				.oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults())).build();
	}

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		// DEFINO PARA NAO USAR MAIS NENHUM PREFIXO
		return new GrantedAuthorityDefaults("");
	}

	@Bean // configura no token jwt o prefixo scope
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthorityPrefix("");

		var converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

		return converter;
	}

	// @Bean
	public UserDetailsService userDatailsService(UsuarioService usuarioService) {
		return new CustomUserDetailsService(usuarioService);
	}

	//@Bean
	public PasswordEncoder passwordEncoder() {// interface p criptografar a senha
		return new BCryptPasswordEncoder(10); // defino o metodo de criptografia (byCryptPasswordEncoder) da senha
	}
}
