package com.mullen.commander.services;

import com.mullen.commander.entities.user.User;
import com.mullen.commander.entities.user.dto.UserCreateDTO;
import com.mullen.commander.entities.user.dto.UserLoginDTO;
import com.mullen.commander.interfaces.IKeyCloak;
import com.mullen.commander.mapper.UserMapper;
import com.mullen.commander.repositories.UserRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    private final IKeyCloak iKeyCloak;

    public UserService(UserRepository userRepository, IKeyCloak iKeyCloak) {
        this.userRepository = userRepository;
        this.iKeyCloak = iKeyCloak;
    }

    public void createUser(UserCreateDTO userCreateDTO) {
        UserRepresentation userRepresentation = this.iKeyCloak.createUser(userCreateDTO);
        this.userRepository.save(UserMapper.toUser(userRepresentation));
    }
}
