package com.fh.vs.gruppe1.depot;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name="Depot")
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name="d_id")
    private Long id;

    @Getter
    @Setter
    @Column(name="d_name")
    private String name;

    @NotNull
    @Column(name = "d_creationdate", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @OneToOne
    @Getter
    @Setter
    private Customer customer;


    @JsonManagedReference
    @OneToMany(mappedBy = "depot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<ClientOrder> transactions;

    @Transient
    @Setter
    @Getter
    private double currentTotalDepotValue;

    /* Getters and setters */
}