package com.fh.vs.gruppe1.account.repository;

import com.fh.vs.gruppe1.account.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository<P, L extends Number> extends CrudRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

}
