package com.mullen.commander.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_entity")
public class User {
    @Id
    public String userId;
}
