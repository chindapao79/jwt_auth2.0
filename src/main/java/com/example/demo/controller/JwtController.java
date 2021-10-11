package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.entities.User;
import com.example.demo.model.entities.UserDto;
import com.example.demo.model.req.JwtRequestModel;
import com.example.demo.model.res.JwtResponseModel;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.utils.JwtUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth/v1/")
public class JwtController {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "home")
    public String home() {
        return "Jwt home@@@";
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<User> items = new ArrayList<User>();
            map.put("REC", items);
            map.put("STATUS", true);

            userInfoRepository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("INVALID_CREDENTIALS", badCredentialsException);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtUtility.generateJwtToken(userDetails);
        return new ResponseEntity<>(new JwtResponseModel(jwtToken), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<User> create(@RequestBody UserDto item) {
        return jwtUserDetailsService.create(item);
    }

}
