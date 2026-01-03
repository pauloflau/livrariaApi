package com.jmp.paulo.livrariaApi.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmp.paulo.livrariaApi.entities.Client;
import com.jmp.paulo.livrariaApi.repositories.ClientRepository;

import lombok.Data;

@Service
@Data
public class ClientService {
	private ClientRepository repository;
	private PasswordEncoder passwordEncoder;

	public ClientService(ClientRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public void salvar(Client client) {
		if (!client.getClientSecret().startsWith("$2")) {
			client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
		}

		repository.save(client);
	}

	public Client obterPorClientID(String clientId) {
		return repository.findByClientId(clientId);
	}

}
