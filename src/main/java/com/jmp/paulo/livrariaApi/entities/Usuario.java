package com.jmp.paulo.livrariaApi.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String login;
	
	private String senha;
	
	private List<String> roles;

}
