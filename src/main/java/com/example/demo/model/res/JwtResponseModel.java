package com.example.demo.model.res;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseModel implements Serializable {
    private String token;
}
