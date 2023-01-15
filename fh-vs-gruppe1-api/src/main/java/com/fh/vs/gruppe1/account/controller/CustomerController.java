package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.bank.service.BankService;
import com.fh.vs.gruppe1.depot.Depot;
import com.fh.vs.gruppe1.transaction.ClientOrder;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
    public String person() {
        return "Hello World";
    }

    @PostMapping("/buyStock")
    public ResponseEntity<ClientOrder> buyStock(Authentication authentication, @RequestBody Map<String, String> request) {
        String symbol = request.get("symbol");
        Integer amount = Integer.valueOf(request.get("amount"));
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());

        log.info(authentication.getName() + "customer request");

        if (customer.isEmpty()) {
            log.warn("customer cannot be found via active sess toke, access denied");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "your message string");
        }
        String userEmail = customer.get().getEmail();
        log.info(userEmail + " my user email @customer login");

        ClientOrder response = bankService.buyStock(symbol, amount, userEmail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/sellStock")
    public ResponseEntity<Boolean> sellStock(Authentication authentication, @RequestBody Map<String, String> request) {
        String symbol = request.get("symbol");
        Integer amount = Integer.valueOf(request.get("amount"));
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        Long transaction = Long.valueOf(request.get("id"));
        log.info(authentication.getName() + " customer request");

        if (customer.isEmpty()) {
            log.warn("customer cannot be found via active session token, access denied");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "your message string");
        }
        String userEmail = customer.get().getEmail();
        log.info(userEmail + " my user email @customer login");

        boolean response = bankService.sellStock(symbol, amount, userEmail, transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getCustomer")
    public ResponseEntity<Customer> getDepot(Authentication authentication){
        Optional<Customer> customerSearch = customerRepository.findByEmail(authentication.getName());
        log.info(authentication.getName() + "customer request");
        if (customerSearch.isEmpty()) {
            log.warn("customer cannot be found via active sess toke, access denied");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "customer cannot be found via active sess toke, access denied");

        }
        Customer customer = customerSearch.get();
        List<ClientOrder> co = customer.getDepot().getTransactions();
        if(co != null){
            co.forEach(order ->{
                double currentSharePrice = bankService.getCurrentShareValue(order.getSymbol());
                order.setCurrentPrice(currentSharePrice);
            });
            customer.getDepot().setCurrentTotalDepotValue(
                    customer.getDepot().getTransactions().stream().mapToDouble(item -> item.getCurrentPrice() * item.getAmount()).sum()
            );
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }


}
