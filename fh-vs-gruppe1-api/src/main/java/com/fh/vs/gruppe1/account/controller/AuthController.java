package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.dto.AuthResponseDto;
import com.fh.vs.gruppe1.dto.LoginDto;
import com.fh.vs.gruppe1.sec.jwt.CustomUserDetailsService;
import com.fh.vs.gruppe1.sec.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private JwtTokenUtil jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto loginDto) throws Exception {

        authenticate(loginDto.getUsername(), loginDto.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginDto.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user disabled");
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "bad user credentials");
        }
    }
}
