package com.fh.vs.gruppe1.account.projection;

import com.fh.vs.gruppe1.account.Customer;
import com.fh.vs.gruppe1.depot.Depot;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "SearchCustomerProjection", types = Customer.class)
public interface SearchCustomerProjection {
    UUID getId();
    String getFirstName();
    String getEmail();
    String getSurname();
    Depot getDepot();

}
