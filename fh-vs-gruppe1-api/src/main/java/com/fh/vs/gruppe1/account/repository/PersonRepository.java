package com.fh.vs.gruppe1.account.repository;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Customer,Long> {

    Optional<Person> findByEmail(String username);
}
