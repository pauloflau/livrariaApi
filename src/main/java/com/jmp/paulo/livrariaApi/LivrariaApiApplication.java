package com.jmp.paulo.livrariaApi;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jmp.paulo.livrariaApi.entities.Autor;
import com.jmp.paulo.livrariaApi.repositories.AutorRepository;

@SpringBootApplication
public class LivrariaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApiApplication.class, args);
	}
	 @Bean
	 CommandLineRunner run(AutorRepository autorRepository) {
		return args -> {
			Autor autor = new Autor();
			autor.setNome("Paulo");
			autor.setNacionalidade("Brasileira");
			autor.setDataNascimento(LocalDate.of(1983, 2, 22));
		
			Autor autorSalvo = autorRepository.save(autor);
			System.out.println(autorSalvo);
		};
	  }
}
