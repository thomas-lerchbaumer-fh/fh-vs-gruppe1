package com.fh.vs.gruppe1.account.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Validated
public class CustomerController {


    @RequestMapping("/loadUser")
    public String person(){
        return "Hello World";
    }



}
