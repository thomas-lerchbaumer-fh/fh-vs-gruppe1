package com.fh.vs.gruppe1.account;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
public abstract class Person {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private String surname;

    @Getter
    @Setter
    @Column(nullable = false)
    private String firstName;




}
