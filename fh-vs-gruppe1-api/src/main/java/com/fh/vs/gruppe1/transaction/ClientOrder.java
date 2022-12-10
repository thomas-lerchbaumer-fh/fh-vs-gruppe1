package com.fh.vs.gruppe1.transaction;

import com.fh.vs.gruppe1.depot.Depot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(name="clientOrder")
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


    @NotNull
    @Column(name = "order_date", nullable = false, updatable = false)
    private Instant orderDate = Instant.now();


    @ManyToOne
    @JoinColumn(name = "depot_id")
    @Getter
    @Setter
    private Depot depot;
    /* Getters and setters */
}