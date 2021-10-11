package com.example.demo.config;

import static com.example.demo.constant.KeyConstant.*;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class JwtSwaggerConfigure {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.example.demo")).paths(PathSelectors.any()).build()
        .securitySchemes(Lists.newArrayList(apiKey())).securityContexts(Arrays.asList(securityContext()))
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(API_TITLE, API_DESCRIPTION, API_VERSION, TEAM_OF_SERVICE, contact(), LICENCE, LICENCE_URL,
        Collections.emptyList());
  }

  private Contact contact() {
    return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
  }

  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "header");
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
  }
}
