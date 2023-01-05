package com.fh.vs.gruppe1.account.service;

import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    public void deleteEmployee(String id) {
        repo.deleteById(Long.valueOf(id));
    }

}
