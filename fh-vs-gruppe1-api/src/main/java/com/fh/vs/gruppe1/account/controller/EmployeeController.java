package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Person;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    @Autowired
    public CustomerRepository customerRepository;


    @RequestMapping("/stuff/hello")
    public String person() {
        return "Hello World";
    }

    @GetMapping("/searchUser")
    public ResponseEntity<List<SearchCustomerProjection>> loadUser(@CurrentSecurityContext(expression = "authentication.name")
                                                   @RequestBody Map<String,String> searchQuery) {
        List<SearchCustomerProjection> customers = customerRepository.findAllBySearchQuery(searchQuery.get("search"));

        return new ResponseEntity<>(customers, HttpStatus.OK);

    }


}
