package com.fh.vs.gruppe1.account.repository;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);


    @Query("SELECT c from Customer c Where c.surname LIKE CONCAT('%',:search,'%') or c.email LIKE CONCAT('%',:search,'%') or c.firstName LIKE CONCAT('%',:search,'%') or c.id LIKE CONCAT('%',:search,'%')")
    List<SearchCustomerProjection> findAllBySearchQuery(String search);


}
