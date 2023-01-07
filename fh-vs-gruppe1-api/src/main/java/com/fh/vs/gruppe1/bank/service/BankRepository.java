package com.fh.vs.gruppe1.bank.service;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankRepository extends CrudRepository<Customer,Long> {


}
