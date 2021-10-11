package com.example.demo.constant;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyConstant {

  public static final String SECURITY_REFERENCE = "Token Access";
  public static final String AUTHENTICATION_DESCRIPTION = "Full API Permission";
  public static final String AUTHENTICATION_SCOPE = "Unlimited";
  public static final String CONTACT_EMAIL = "chindapao79@gmail.com";
  public static final String CONTACT_URL = "http://www.ktv.com";
  public static final String CONTACT_NAME = "Invoice API Support";
  public static final String API_TITLE = "Invoice Management Open API";
  public static final String API_DESCRIPTION = "";
  public static final String TEAM_OF_SERVICE = "";
  public static final String API_VERSION = "";
  public static final String LICENCE = "";
  public static final String LICENCE_URL = "";
  public static final String SECURE_PATH = "";
  public static final String API_TAG = "API Service";

  private static final long serialVersionUID = 2636936156391265891L;
  public static final long TOKEN_VALIDITY = 3000 * 60 * 60;
  public static final String jwtSecret =
    "9283UKLDSJF39R9WRUEIARUWEUR3R932URFEF";
  public static final String DEFAULT_API_INFO =
    "9283UKLDSJF39R9WRUEIARUWEUR3R932URFEF";
}
