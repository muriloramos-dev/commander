package com.mullen.commander.controllers;

import com.mullen.commander.entities.user.dto.UserCreateDTO;
import com.mullen.commander.entities.user.dto.UserLoginDTO;
import com.mullen.commander.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        this.userService.createUser(userCreateDTO.getUsername(), userCreateDTO.getEmail(), userCreateDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        String token = this.userService.loginUser(userLoginDTO);
        if (token != null) {
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
