package com.fh.vs.gruppe1.account.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fh.vs.gruppe1.account.controller.CustomerController.API_CUSTOMER;

@RestController
@RequestMapping(API_CUSTOMER)
@RequiredArgsConstructor
@Validated
public class CustomerController {

    public static final String API_CUSTOMER = "/api/customer";

    @RequestMapping("/stuff/hello")
    public String person(){
        return "Hello World";
    }


}
