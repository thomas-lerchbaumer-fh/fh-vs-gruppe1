package com.fh.vs.gruppe1.depot;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import com.fh.vs.gruppe1.account.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepotService {
    @Autowired
    private final DepotRepository repo;

    private final CustomerService cservice;


    public Depot saveDepot(Depot depot) {
        return repo.save(depot);
    }

    public Depot findDepotByCustomer(Customer customer) {
        Iterable<Depot> depot = repo.findAll();
        for (Depot d : depot){
            if(customer.getId() == d.getCustomer().getId())
            return d;
        }
        return null;
    }
}
