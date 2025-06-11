package com.mullen.commander.services;

import com.mullen.commander.entities.user.dto.UserLoginDTO;
import com.mullen.commander.interfaces.IKeyCloak;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Value("${keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    private final IKeyCloak iKeyCloak;

    public UserService(Keycloak keycloak, IKeyCloak iKeyCloak) {
        this.keycloak = keycloak;
        this.iKeyCloak = iKeyCloak;
    }

    public void createUser(String username, String email, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        user.setCredentials(List.of(credential));

        keycloak.realm(realm).users().create(user);
    }

    public String loginUser (UserLoginDTO userLoginDTO) {
        return iKeyCloak.loginUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}
