package com.jmp.paulo.livrariaApi.security;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import com.jmp.paulo.livrariaApi.services.ClientService;

@Component
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

	private ClientService clientService;
	  private TokenSettings tokenSettings;
	  private ClientSettings clientSettings;

	public CustomRegisteredClientRepository(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		var client = clientService.obterPorClientID(clientId);// procura cliente

		if (client == null) {// se não achar um cliente entao
			return null;
		}

		// senao
		return RegisteredClient // registro o cliente
				.withId(client.getId().toString()).clientId(client.getClientId()).clientSecret(client.getClientSecret())
				.redirectUri(client.getRedirectURI()).scope(client.getScope())

				// abaixo esta como vou passar a minha credencial
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.tokenSettings(tokenSettings)
				.clientSettings(clientSettings)
				.build();
	}

	@Override
	public void save(RegisteredClient registeredClient) {
	}

	@Override
	public RegisteredClient findById(String id) {
		return null;
	}

}
