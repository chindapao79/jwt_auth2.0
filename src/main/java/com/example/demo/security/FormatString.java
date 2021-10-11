package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class FormatString {

  public String fmString(String string) {
    return string.trim();
  }
}
