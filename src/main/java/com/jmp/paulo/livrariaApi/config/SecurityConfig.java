package com.jmp.paulo.livrariaApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jmp.paulo.livrariaApi.security.CustomUserDetailsService;
import com.jmp.paulo.livrariaApi.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.headers(headers -> headers.frameOptions(frame -> frame.disable())).httpBasic(Customizer.withDefaults())

				 .formLogin(Customizer.withDefaults())
		         .httpBasic(Customizer.withDefaults())
		
				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/h2-console/**").permitAll();

					authorize.requestMatchers("/usuarios/**").permitAll();
					authorize.requestMatchers("/login").permitAll();
					authorize.requestMatchers("/autores/**").hasRole("ADMIN");
					authorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN");
					authorize.anyRequest().authenticated();

				}).build();
	}

	@Bean
	public UserDetailsService userDatailsService( UsuarioService usuarioService) {
		return new CustomUserDetailsService(usuarioService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {// interface p criptografar a senha
		return new BCryptPasswordEncoder(10); // defino o metodo de criptografia (byCryptPasswordEncoder) da senha
	}
}
