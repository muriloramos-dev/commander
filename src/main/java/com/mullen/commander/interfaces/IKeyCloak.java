package com.mullen.commander.interfaces;

import com.mullen.commander.entities.user.dto.UserCreateDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.common.util.KeycloakUriBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IKeyCloak {
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    private final Keycloak keycloak;

    private final RestTemplate restTemplate = new RestTemplate();

    public IKeyCloak(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public void createUser (UserCreateDTO dto) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setEnabled(true);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        Map<String, List<String>> clientRoles = new HashMap<>();
        clientRoles.put(realm, List.of("user"));
        user.setClientRoles(clientRoles);
        user.setRequiredActions(List.of("CONFIGURE_TOTP"));
        keycloak.realm(realm).users().create(user);
    }
}
