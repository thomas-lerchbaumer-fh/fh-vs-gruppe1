package com.fh.vs.gruppe1.account.repository;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {

    Optional<Person> findByEmail(String email);
}
