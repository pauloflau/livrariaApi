package com.jmp.paulo.livrariaApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.paulo.livrariaApi.entities.Client;
import com.jmp.paulo.livrariaApi.services.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {
	private ClientService service;
	
	
	public ClientController(ClientService service) {
		this.service = service;
	}


	@PostMapping	
	@PreAuthorize("hasRole('GERENTE')")//so quem pode usar o post e o gerente
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Client client) {
		service.salvar(client);
	}
}
