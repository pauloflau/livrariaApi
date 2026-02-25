# 📚 Livraria API

API RESTful desenvolvida com Spring Boot para gerenciamento de livros, autores e usuários.
O projeto implementa autenticação e autorização com Spring Security, OAuth2, Login Social, emissão de tokens JWT, validações com Bean Validation, documentação da API e testes automatizados.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
   - Spring Web
   - Spring Data JPA
   - Spring Security
   - OAuth2 Client / Resource Server (JWT)
- Hibernate & Bean Validation
- Banco de Dados: Inicialmente h2 podendo ser alterado para PostgreSQL (ou outro de sua escolha)
- Documentação: Springdoc OpenAPI (Swagger)
- Testes: JUnit 5, Mockito

---

## 📦 Funcionalidades da API
### 🔐 Autenticação & Autorização
- Login tradicional com usuário e senha.
- Login social (OAuth2) — Google, GitHub ou outro provedor.
- Geração e validação de JWT.
- Controle de acesso por perfis (ex.: ADMIN, USER).

### 📚 Módulo de Livros
- Cadastro, edição, remoção e listagem de livros.
- Filtros por categoria, título e autor.

### 👤 Módulo de Usuários
- Cadastro e gestão de usuários.
- Atualização de perfil.
- Associação com contas de login social.

### 🛡 Validações
- Bean Validation para validações de entrada:
- Títulos obrigatórios
- Formato de email
- Tamanho de campos
- Regras customizadas

### 🧪 Testes
- Testes unitários (JUnit + Mockito)
- Testes de integração
- Testes de endpoints da API

---

## 📘 Documentação da API

Após iniciar o projeto:

- Swagger UI:
👉 http://localhost:8080/swagger-ui.html

- OpenAPI JSON:
👉 http://localhost:8080/v3/api-docs

---

### 📧 Contato

Caso queira trocar ideias ou sugerir melhorias:  
[LinkedIn](https://www.linkedin.com/in/paulo-flau-43b667382/) 

### 🤝 Contribuição

Sinta-se à vontade para abrir issues ou enviar pull requests.
Toda ajuda é bem-vinda!

### 📄 Licença

Este projeto está sob a licença MIT.
Você pode usá-lo e modificá-lo livremente.

---
