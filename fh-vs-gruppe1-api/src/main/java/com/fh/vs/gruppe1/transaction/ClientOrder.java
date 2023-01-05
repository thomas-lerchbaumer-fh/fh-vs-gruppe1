package com.fh.vs.gruppe1.transaction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fh.vs.gruppe1.bank.service.BankService;
import com.fh.vs.gruppe1.depot.Depot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name="Transaction")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="t_symbol")
    private String symbol;

    @Getter
    @Setter

    @Column(name="t_amount")
    private int amount;

    @Getter
    @Setter

    @Column(name="t_unitprice")
    private double unitPrice;

    @Transient
    @Setter
    @Getter
    private double currentPrice;

    @CreationTimestamp
    @Getter
    @Column(name="t_datetime")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "d_id")
    @JsonBackReference
    @Setter
    private Depot depot;
    /* Getters and setters */
}