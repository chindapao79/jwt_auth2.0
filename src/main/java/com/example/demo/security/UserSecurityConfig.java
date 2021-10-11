package com.example.demo.security;

import com.example.demo.filter.JwtFilter;
import com.example.demo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtEntryPoint jwtEntryPoint;

  @Autowired
  private JwtFilter jwtFilter;

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  private JwtPasswordEncode jwtPasswordEncode;

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(jwtPasswordEncode.encoderPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/auth/v1/login").permitAll().and().authorizeRequests()
        .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger.json",
            "/swagger-resources", "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security")
        .permitAll().and().authorizeRequests().antMatchers("/api/v1/**").permitAll().and().authorizeRequests()
        .antMatchers("/auth/v1/register").hasRole("ADMIN").anyRequest().authenticated().and().exceptionHandling()
        .authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
