package com.fh.vs.gruppe1.account.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Validated
public class EmployeeController {



    @RequestMapping("/stuff/hello")
    public String person(){
        return "Hello World";
    }


}
