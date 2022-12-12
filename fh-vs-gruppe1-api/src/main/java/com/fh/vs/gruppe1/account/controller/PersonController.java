package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.dto.AuthResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {



    @PostMapping("/stuff/hello")
    public ResponseEntity<AuthResponseDto> person(){

        String test = "test";
        return new ResponseEntity<>(new AuthResponseDto(test), HttpStatus.OK);
    }
}
