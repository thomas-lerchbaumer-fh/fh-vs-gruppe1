package com.fh.vs.gruppe1.account;

import com.fh.vs.gruppe1.depot.Depot;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Customer extends Person {

    @Getter
    @Setter
    @OneToOne
    private Depot depot;

}
