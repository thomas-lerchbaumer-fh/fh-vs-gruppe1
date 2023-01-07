package com.fh.vs.gruppe1.bank.service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="b_id")
    private Long id;

    @Getter
    @Setter
    @Column(name="b_volume")
    private static Long totalOrderVolume;

}
