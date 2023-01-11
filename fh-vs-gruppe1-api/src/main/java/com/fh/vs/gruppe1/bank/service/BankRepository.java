package com.fh.vs.gruppe1.bank.service;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BankRepository extends CrudRepository<Bank,Long> {

    Optional<Bank> findById(long id);


}
