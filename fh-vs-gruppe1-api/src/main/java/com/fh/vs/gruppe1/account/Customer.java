package com.fh.vs.gruppe1.account;

import com.fh.vs.gruppe1.depot.Depot;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="Customer")
public class Customer extends Person {

    @Getter
    @Setter
    @OneToOne (mappedBy = "d_id")
    private Depot depot;
    public Customer(String id, String email, LocalDateTime createdAt, @NotNull String password, String surname, String firstName, Depot depot) {
        super(id, email, createdAt, password, surname, firstName);
        this.depot = depot;
    }

    public Customer(Depot depot) {
        this.depot = depot;
    }

    public Customer() {    }

}
