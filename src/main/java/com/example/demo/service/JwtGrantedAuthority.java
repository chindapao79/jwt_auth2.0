package com.example.demo.service;

import org.springframework.security.core.GrantedAuthority;

public class JwtGrantedAuthority implements GrantedAuthority {

  private String authorities;

  public JwtGrantedAuthority(String authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getAuthority() {
    return authorities;
  }
}
