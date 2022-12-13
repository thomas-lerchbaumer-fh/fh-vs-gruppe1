package com.fh.vs.gruppe1.account.repository;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);

}
