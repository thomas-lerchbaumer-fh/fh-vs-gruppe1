package com.fh.vs.gruppe1.account;


import com.fh.vs.gruppe1.depot.Depot;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Person")
public abstract class Person {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "p_id", updatable = false, nullable = false)
    private String id;

    @Getter
    @Setter
    @Column(unique=true)
    private String email;


    @CreationTimestamp
    private LocalDateTime createdAt;


    @Getter
    @Setter
    @NotNull
    @Column(nullable = false, name="p_password")
    private String password;

    @Getter
    @Setter
    @Column(nullable = false, name="p_surname")
    private String surname;

    @Getter
    @Setter
    @Column(nullable = false, name="p_firstname")
    private String firstName;


}
