package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.Person;
import com.fh.vs.gruppe1.account.projection.AllCustomersProjection;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.account.service.CustomerService;
import com.fh.vs.gruppe1.account.service.EmployeeService;
import com.fh.vs.gruppe1.bank.service.BankService;
import com.fh.vs.gruppe1.depot.Depot;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EmployeeController {

    private final EmployeeService eservice;

    @Autowired
    public CustomerRepository customerRepository;


    @Autowired
    public BankService bankService;

    @RequestMapping("/stuff/hello")
    public String person() {
        return "Hello World";
    }

    @PostMapping("/searchUser")
    public ResponseEntity<List<SearchCustomerProjection>> searchUser(@CurrentSecurityContext(expression = "authentication.name")
                                                   @RequestBody Map<String,String> searchQuery) {
        List<SearchCustomerProjection> customers = customerRepository.findAllBySearchQuery(searchQuery.get("search"));

        customers.forEach(customer ->{
            List<ClientOrder> co = customer.getDepot().getTransactions();
            if(co != null){

                co.forEach(order ->{
                    double currentSharePrice = bankService.getCurrentShareValue(order.getSymbol());
                    order.setCurrentPrice(currentSharePrice);
                });
                customer.getDepot().setCurrentTotalDepotValue(
                        customer.getDepot().getTransactions().stream().mapToDouble(item -> item.getCurrentPrice() * item.getAmount()).sum()
                );
            }
        });

        log.info(customers.get(0).getDepot().getTransactions().toString() + "my customer");

        return new ResponseEntity<>(customers, HttpStatus.OK);

    }

    @PostMapping("/createUser")
    public ResponseEntity<Employee> createUser(@RequestBody Employee employeeInput) {
        Employee employee = eservice.saveEmployee(employeeInput);

        return employee == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(employee);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<AllCustomersProjection>> getAllCustomers(){
        List<AllCustomersProjection> customers = customerRepository.findAllProjectedBy();

       return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/buyStock")
    public ResponseEntity<Customer> buyStock(@RequestBody Map<String,String> request){
        String symbol = request.get("symbol");
        Integer amount = Integer.valueOf(request.get("amount"));
        String userEmail = request.get("userEmail");

        Customer customer = bankService.buyStock(symbol,amount,userEmail);
        return new ResponseEntity<>(customer,HttpStatus.OK);

    }

}
