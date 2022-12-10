package com.fh.vs.gruppe1.depot;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name="depot")
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();


    @OneToOne
    @Getter
    @Setter
    private Customer customer;

    @OneToMany(mappedBy = "depot", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    private List<ClientOrder> transactions;

    /* Getters and setters */
}