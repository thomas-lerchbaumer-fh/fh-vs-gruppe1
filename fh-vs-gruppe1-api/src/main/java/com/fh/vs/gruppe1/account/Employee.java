package com.fh.vs.gruppe1.account;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Employee")
public class Employee extends Person {

    /* check back with Thomas
    @Column
    private LocalDate dateOfEntry;

     */


}
