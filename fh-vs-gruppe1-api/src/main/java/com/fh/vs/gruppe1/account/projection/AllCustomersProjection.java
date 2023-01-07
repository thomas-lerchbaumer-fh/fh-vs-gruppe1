package com.fh.vs.gruppe1.account.projection;

import com.fh.vs.gruppe1.account.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "SearchCustomerProjection", types = Customer.class)
public interface AllCustomersProjection {

    String getEmail();
}
