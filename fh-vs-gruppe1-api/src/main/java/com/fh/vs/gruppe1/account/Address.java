package com.fh.vs.gruppe1.account;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ad_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="ad_street")
    private String street;

    @Column(name="ad_housenumber")
    private String housenumber;

    @Column(name="ad_postcode")
    private String postcode;

    @Column(name="ad_city")
    private String city;

    @ManyToMany(cascade= {CascadeType.ALL})
    @JoinTable(
            name="Person_has_Address",
            joinColumns={@JoinColumn(name="ad_id")},
            inverseJoinColumns = { @JoinColumn(name="p_id")}
    )
    Set<Person> person = new HashSet<>();

}
