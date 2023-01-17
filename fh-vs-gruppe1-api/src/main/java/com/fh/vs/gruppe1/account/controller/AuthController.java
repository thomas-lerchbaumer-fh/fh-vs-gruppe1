package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.dto.AuthResponseDto;
import com.fh.vs.gruppe1.dto.LoginDto;
import com.fh.vs.gruppe1.sec.jwt.CustomUserDetailsService;
import com.fh.vs.gruppe1.sec.jwt.JWTGenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        log.info(loginDto.getUsername() + " " + loginDto.getPassword()+ " login dto");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

}
