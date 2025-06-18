package com.mullen.commander.mapper;

import com.mullen.commander.entities.user.User;
import org.keycloak.representations.idm.UserRepresentation;

public class UserMapper {
    public static User toUser (UserRepresentation userRepresentation) {
        User user = new User();
        user.setUserId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setEmail(userRepresentation.getEmail());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        return user;
    }
}
