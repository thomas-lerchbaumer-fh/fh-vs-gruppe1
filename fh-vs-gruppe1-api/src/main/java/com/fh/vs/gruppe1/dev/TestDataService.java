package com.fh.vs.gruppe1.dev;
import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.account.CustomerRepository;
import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.EmployeeRepository;
import com.fh.vs.gruppe1.depot.Depot;
import com.fh.vs.gruppe1.depot.DepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.logging.Logger;

@Service
@Transactional(readOnly = false)
@Order(0)
public class TestDataService implements ApplicationRunner
{
    @Autowired
    private HibernateProperties hibernateProperties;
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(hibernateProperties.getDdlAuto().startsWith("create")){
            logger.info("population data to db  ");
            this.populate();
        }
    }

    public void populate(){
        Customer customer = new Customer();
        customer.setFirstName("Thomas");
        customer.setSurname("LB");
        customer.setPassword("client");
        customerRepository.save(customer);

        Depot dep = new Depot();
        dep.setCustomer(customer);
        String depName = customer.getFirstName() + " " + customer.getSurname() + " depot";
        dep.setName(depName);
        depotRepository.save(dep);

        Employee emp = new Employee();
        emp.setFirstName("Admin");
        emp.setSurname("Super");
        emp.setPassword("admin");
        employeeRepository.save(emp);


    }
}
