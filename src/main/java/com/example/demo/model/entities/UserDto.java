package com.example.demo.model.entities;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String authorities;

}
