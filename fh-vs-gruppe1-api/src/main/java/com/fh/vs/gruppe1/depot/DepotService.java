package com.fh.vs.gruppe1.depot;

import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepotService {
    @Autowired
    private final DepotRepository repo;

    public Depot saveDepot(Depot depot) {
        return repo.save(depot);
    }

}
