package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Person;
import com.fh.vs.gruppe1.account.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/loadUser")
    public ResponseEntity<?> loadUser(@CurrentSecurityContext(expression = "authentication.name")
                                      String username) {
        Optional<Person> p = personRepository.findByEmail(username);
        JSONObject resp = new JSONObject();
        resp.put("role", p.get().getClass().getSimpleName());
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

}
