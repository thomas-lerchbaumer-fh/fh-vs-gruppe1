package com.fh.vs.gruppe1.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Customer extends Person{

    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String clientNumber;
}
