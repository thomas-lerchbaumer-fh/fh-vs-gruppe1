package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.bank.service.BankService;
import com.fh.vs.gruppe1.transaction.ClientOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CustomerController {
    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public ClientOrderRepository clientOrderRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public BankService bankService;
    @RequestMapping("/loadUser")
    public String person(){
        return "Hello World";
    }

    @PostMapping("/buyStock")
    public ResponseEntity<Customer> buyStock(Authentication authentication,@RequestBody Map<String,String> request){
        String symbol = request.get("symbol");
        Integer amount = Integer.valueOf(request.get("amount"));
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
    log.info(authentication.getName() + "customer request");
        if(customer.isEmpty()){
            log.warn("customer cannot be found via active sess toke, access denied");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "your message string");
        }

        String userEmail = customer.get().getEmail();

        log.info(userEmail + " my user email @customer login");

        Customer response = bankService.buyStock(symbol,amount,userEmail);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }




}
