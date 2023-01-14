package com.fh.vs.gruppe1.account;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Employee")
public class Employee extends Person {
    public Employee(String id, String email, LocalDateTime createdAt, @NotNull String password, String surname, String firstName) {
        super(id, email, createdAt, password, surname, firstName);
    }

    public Employee() {
    }

    /* check back with Thomas
    @Column
    private LocalDate dateOfEntry;

     */


}
