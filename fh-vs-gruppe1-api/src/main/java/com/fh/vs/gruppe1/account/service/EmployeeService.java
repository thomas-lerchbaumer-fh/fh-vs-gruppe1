package com.fh.vs.gruppe1.account.service;

import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeRepository repo;

    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Employee findEmployee(Employee employee){
        return repo.findByEmail(employee.getEmail()).get();
    }

    public void deleteEmployee(String id) {
        repo.deleteById(Long.valueOf(id));
    }

}
