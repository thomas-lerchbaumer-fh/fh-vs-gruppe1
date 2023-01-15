package com.fh.vs.gruppe1.account.controller;


import com.fh.vs.gruppe1.account.Address;
import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.projection.AllCustomersProjection;
import com.fh.vs.gruppe1.account.projection.SearchCustomerProjection;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import com.fh.vs.gruppe1.account.service.AddressService;
import com.fh.vs.gruppe1.account.service.CustomerService;
import com.fh.vs.gruppe1.account.service.EmployeeService;
import com.fh.vs.gruppe1.bank.service.Bank;
import com.fh.vs.gruppe1.bank.service.BankRepository;
import com.fh.vs.gruppe1.bank.service.BankService;
import com.fh.vs.gruppe1.depot.Depot;
import com.fh.vs.gruppe1.depot.DepotService;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EmployeeController {

    private final EmployeeService eservice;
    private final AddressService aservice;
    private final CustomerService cservice;
    private final DepotService dservice;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public BankService bankService;

    @Autowired
    public BankRepository bankRepository;

    @RequestMapping("/stuff/hello")
    public String person() {
        return "Hello World";
    }

    @PostMapping("/searchUser")
    public ResponseEntity<List<SearchCustomerProjection>> searchUser(@CurrentSecurityContext(expression = "authentication.name")
                                                   @RequestBody Map<String,String> searchQuery) {
        List<SearchCustomerProjection> customers = customerRepository.findAllBySearchQuery(searchQuery.get("search"));
        if(customers.isEmpty()){
            log.error("no customer fond");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No customer found")
            );
        }


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

    //setCustomer({firstname:'',lastname:'',email:'',streetname:'',housenumber:'',postcode:'',city:''});
    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody String json) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

        String email = (String) jsonObject.get("email");

        if(employeeRepository.findByEmail(email).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "employee already exists");
        }

        String firstname = (String) jsonObject.get("firstname");
        String lastname = (String) jsonObject.get("lastname");
        //------
        String streetname = (String) jsonObject.get("streetname");
        String housenumber = (String) jsonObject.get("housenumber");
        String postcode = (String) jsonObject.get("postcode");
        String city = (String) jsonObject.get("city");

        /*
        Map employeeInput = new HashMap();
        employeeInput.put("email", email);
        employeeInput.put("firstName", firstname);
        employeeInput.put("surname", lastname);
        employeeInput.put("createdAt", LocalDateTime.now());

        Map addressInput = new HashMap();
        addressInput.put("street", streetname);
        addressInput.put("housenumber", housenumber);
        addressInput.put("postcode", postcode);
        addressInput.put("city", city);

        String empjson = JSONValue.toJSONString(employeeInput);
        String addjson = JSONValue.toJSONString(addressInput);
         */

        Employee empobj = new Employee();
        empobj.setEmail(email);
        empobj.setSurname(lastname);
        empobj.setFirstName(firstname);
        empobj.setCreatedAt(LocalDateTime.now());

        Address addobj = new Address();
        addobj.setCity(city);
        addobj.setHousenumber(housenumber);
        addobj.setStreet(streetname);
        addobj.setPostcode(postcode);

        Employee employee = eservice.saveEmployee(empobj);
        Address address = aservice.saveAddress(addobj);

        return employee == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(employee);

    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody String json) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

        String email = (String) jsonObject.get("email");

        if(customerRepository.findByEmail(email).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "customer already exists");
        }

        String firstname = (String) jsonObject.get("firstname");
        String lastname = (String) jsonObject.get("lastname");
        //------
        String streetname = (String) jsonObject.get("streetname");
        String housenumber = (String) jsonObject.get("housenumber");
        String postcode = (String) jsonObject.get("postcode");
        String city = (String) jsonObject.get("city");

        Customer custobj = new Customer();
        custobj.setEmail(email);
        custobj.setSurname(lastname);
        custobj.setFirstName(firstname);
        custobj.setCreatedAt(LocalDateTime.now());

        Address addobj = new Address();
        addobj.setCity(city);
        addobj.setHousenumber(housenumber);
        addobj.setStreet(streetname);
        addobj.setPostcode(postcode);

        Customer customer = cservice.saveCustomer(custobj);

        addobj.setPerson(
                new HashSet<>(Arrays.asList(cservice.findCustomer(customer)))
        );

        Address address = aservice.saveAddress(addobj);

        /*
        Depot depot = new Depot();
        depot.setCustomer(cservice.findCustomer(customer));
        Depot dobj = dservice.saveDepot(depot);

         */

        return customer == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(customer);

    }

    /*
        @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employeeInput) {

        Employee employee = eservice.saveEmployee(employeeInput);
        return employee == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(employee);

    }
     */

//    @PostMapping("/createCustomer")
//    public ResponseEntity<Employee> createCustomer(@RequestBody Employee employeeInput) {
//        Employee employee = eservice.saveEmployee(employeeInput);
//
//        return employee == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(employee);
//    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<AllCustomersProjection>> getAllCustomers(){
        List<AllCustomersProjection> customers = customerRepository.findAllProjectedBy();

       return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @GetMapping("/getBankVolume")
    public ResponseEntity<Bank> getBankVolume(){
        Optional<Bank> bank = bankRepository.findById(1l);

        if(bank.isEmpty()){
            log.error("Customer not found");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No baml volume found")
            );
        }

        return new ResponseEntity<>(bank.get(), HttpStatus.OK);

    }

    @PostMapping("/buyStock")
    public ResponseEntity<ClientOrder> buyStock(@RequestBody Map<String,String> request){
        String symbol = request.get("symbol");
        Integer amount = Integer.valueOf(request.get("amount"));
        String userEmail = request.get("userEmail");

        ClientOrder clientOrder = bankService.buyStock(symbol,amount,userEmail);
        return new ResponseEntity<>(clientOrder,HttpStatus.OK);

    }

}
