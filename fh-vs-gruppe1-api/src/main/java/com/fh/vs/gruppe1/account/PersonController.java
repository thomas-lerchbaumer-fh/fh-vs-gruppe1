package com.fh.vs.gruppe1.account;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @RequestMapping("/stuff/hello")
    public String person(){
        return "Hello World";
    }
}
