package com.fh.vs.gruppe1.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Address")
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Getter
    @Setter
    @Column(name = "ad_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="ad_street")
    @Getter
    @Setter
    private String street;

    @Column(name="ad_housenumber")
    @Getter
    @Setter
    private String housenumber;

    @Column(name="ad_postcode")
    @Getter
    @Setter
    private String postcode;

    @Column(name="ad_city")
    @Getter
    @Setter
    private String city;

    @ManyToMany(cascade= {CascadeType.ALL})
    @JoinTable(
            name="Person_has_Address",
            joinColumns={@JoinColumn(name="ad_id")},
            inverseJoinColumns = { @JoinColumn(name="p_id")}
    )
    @Getter
    @Setter
    Set<Person> person = new HashSet<>();

}
