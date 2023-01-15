package com.fh.vs.gruppe1.account.service;


import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import com.fh.vs.gruppe1.depot.Depot;
import com.fh.vs.gruppe1.external.tradingservice.TradingServiceClient;
import com.fh.vs.gruppe1.external.tradingservice.tmp.GetStockQuotesResponse;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository repo;

    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }

    public Customer findCustomer(Customer customer) {
        return repo.findByEmail(customer.getEmail()).get();
    }

}
