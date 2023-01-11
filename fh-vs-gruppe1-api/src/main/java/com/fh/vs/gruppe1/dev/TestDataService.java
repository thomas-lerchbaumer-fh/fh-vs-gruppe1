package com.fh.vs.gruppe1.dev;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import com.fh.vs.gruppe1.account.repository.PersonRepository;
import com.fh.vs.gruppe1.bank.service.Bank;
import com.fh.vs.gruppe1.bank.service.BankRepository;
import com.fh.vs.gruppe1.depot.Depot;
import com.fh.vs.gruppe1.depot.DepotRepository;
import com.fh.vs.gruppe1.external.tradingservice.TradingServiceClient;
import com.fh.vs.gruppe1.external.tradingservice.config.TradingServiceConfig;
import com.fh.vs.gruppe1.external.tradingservice.tmp.FindStockQuotesByCompanyNameResponse;
import com.fh.vs.gruppe1.transaction.ClientOrder;
import com.fh.vs.gruppe1.transaction.ClientOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@Slf4j
@Transactional(readOnly = false)
@Order(0)
public class TestDataService implements ApplicationRunner {

    @Autowired
    private TradingServiceClient tradingServiceClient;


    @Autowired
    private HibernateProperties hibernateProperties;
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (hibernateProperties.getDdlAuto().startsWith("create")) {
            logger.info("population data to db  ");
            this.populate();
        }
    }

    public void populate() {
        Bank bank = new Bank();
        bank.setTotalOrderVolume(1000000000.00);
        bankRepository.save(bank);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Customer customer = new Customer();
        customer.setFirstName("Thomas");
        customer.setSurname("LB");

        Depot dep = new Depot();
        String depName = customer.getFirstName() + " " + customer.getSurname() + " depot";
        dep.setName(depName);
        depotRepository.save(dep);

        customer.setDepot(dep);

        ClientOrder co = new ClientOrder();
        co.setSymbol("AAPL");
        co.setAmount(2);
        co.setDepot(customer.getDepot());
        co.setUnitPrice(12.33);
        clientOrderRepository.save(co);


        Employee emp = new Employee();
        emp.setFirstName("Admin");
        emp.setSurname("Super");
        emp.setPassword((bCryptPasswordEncoder.encode("admin")));
        emp.setEmail("admin@admin.at");
        employeeRepository.save(emp);

        customer.setPassword((bCryptPasswordEncoder.encode("client")));
        customer.setEmail("client@client.at");
        customerRepository.save(customer);

        FindStockQuotesByCompanyNameResponse res = tradingServiceClient.getStockQuotebyCompanyName("apple").getValue();
        log.info(res.getReturn().toString() + " MY RES ");


    }
}
