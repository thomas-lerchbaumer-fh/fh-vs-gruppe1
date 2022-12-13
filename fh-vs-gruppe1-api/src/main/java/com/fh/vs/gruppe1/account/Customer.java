package com.fh.vs.gruppe1.account;


import jakarta.persistence.*;
import lombok.*;



@Entity
public class Customer extends Person {
    @Column
    private String customerNumber;


}
