package com.example.demo.service;

import com.example.demo.model.entities.User;
import com.example.demo.model.entities.UserDto;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.security.FormatString;
import com.example.demo.security.JwtPasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserInfoRepository userInfoRepository;

  @Autowired
  private FormatString formatString;

  @Autowired
  private JwtPasswordEncode jwtPasswordEncode;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userInfoRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found by username: " + user);
    } else {
      return new JwtUserDetails(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
  }

  public ResponseEntity<User> create(UserDto item) {
    User user = new User();
    String username = formatString.fmString(item.getUsername());
    user.setUsername(username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase());
    user.setEmail(formatString.fmString(item.getEmail()).toLowerCase() + "@gmail.com".toLowerCase());
    user.setPassword(formatString.fmString(jwtPasswordEncode.encoderPasswordEncoder().encode(item.getPassword())));
    user.setAuthorities("ROLE_" + formatString.fmString(item.getAuthorities().toUpperCase()));
    return new ResponseEntity<User>(userInfoRepository.save(user), HttpStatus.CREATED);
  }
}
