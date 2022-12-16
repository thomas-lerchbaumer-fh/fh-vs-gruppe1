package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Person;
import com.fh.vs.gruppe1.account.repository.PersonRepository;
import com.fh.vs.gruppe1.bank.service.BankService;
import com.fh.vs.gruppe1.dto.UserDto;
import com.fh.vs.gruppe1.external.tradingservice.tmp.PublicStockQuote;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private BankService bankService;


    @GetMapping("/loadUser")
    public ResponseEntity<UserDto> loadUser(@CurrentSecurityContext(expression = "authentication.name")
                                      String username) {
        Optional<Person> p = personRepository.findByEmail(username);
        UserDto user = new UserDto(p.get(),p.get().getClass().getSimpleName());
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/searchStock")
    public ResponseEntity<List<PublicStockQuote>> searchStock(@CurrentSecurityContext(expression = "authentication.name")
                                                                  @RequestBody Map<String,String> searchQuery){
        List<PublicStockQuote> req = bankService.getStocksByNameOrSymbol(searchQuery.get("search"));
        return new ResponseEntity<>(req,HttpStatus.OK);

    }

}
