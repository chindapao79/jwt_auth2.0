package com.example.demo.model.req;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequestModel implements Serializable {
    private String username;
    private String password;

}
