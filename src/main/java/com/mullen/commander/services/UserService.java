package com.mullen.commander.services;

import com.mullen.commander.entities.user.dto.UserCreateDTO;
import com.mullen.commander.entities.user.dto.UserLoginDTO;
import com.mullen.commander.interfaces.IKeyCloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${keycloak.realm}")
    private String realm;


    private final IKeyCloak iKeyCloak;

    public UserService(IKeyCloak iKeyCloak) {
        this.iKeyCloak = iKeyCloak;
    }

    public void createUser(UserCreateDTO userCreateDTO) {
        this.iKeyCloak.createUser(userCreateDTO);
    }
}
