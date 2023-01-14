package com.fh.vs.gruppe1.account.repository;

import com.fh.vs.gruppe1.account.Address;
import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.projection.AllCustomersProjection;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

}
