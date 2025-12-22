package com.jmp.paulo.livrariaApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable).formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())

				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/login").permitAll();
					authorize.requestMatchers("/autores/**").hasRole("ADMIN");
					authorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN");
					authorize.anyRequest().authenticated();

				}).build();
	}
	
	 @Bean
	  public UserDetailsService userDatailsService(PasswordEncoder encoder) {
	//acima mostro que vou retornar um UserDetailsService
			
	//vou criar os usuarios e pra isso uso a interface UserDetails
	    UserDetails user1 = User.builder()
		.username("usuario1")
		.password(encoder.encode("123"))//criptografo a senha
		.roles("USER")
		.build();
			
	    UserDetails user2 = User.builder()
		.username("usuario2")
		.password(encoder.encode("321"))//criptografo a senha
		.roles("ADMIN")
		.build();

	//adiciono meus usuarios do tipo UserDetails e retornando eles em memoria. Essa classe que salva em memoria e do tipo UserDetailsService
	    return new InMemoryUserDetailsManager(user1, user2);
	  }
		
	  @Bean
	  public PasswordEncoder passwordEncoder() {//interface p criptografar a senha
	    return new BCryptPasswordEncoder(10); //defino o metodo de criptografia (byCryptPasswordEncoder) da senha
	  }
}
