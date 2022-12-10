package com.fh.vs.gruppe1.account;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Employee extends Person {


    @Column(nullable = false)
    @Getter
    @Setter
    protected String employeeNumber;

}
