package com.fh.vs.gruppe1.transaction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fh.vs.gruppe1.depot.Depot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name="client_order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String symbol;

    @Getter
    @Setter
    private int amount;

    @Getter
    @Setter
    private double unitPrice;

    @CreationTimestamp
    @Getter
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "depot_id")
    @JsonBackReference
    @Setter
    private Depot depot;
    /* Getters and setters */
}