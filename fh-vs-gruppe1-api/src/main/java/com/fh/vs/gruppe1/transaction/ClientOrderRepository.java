package com.fh.vs.gruppe1.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderRepository extends CrudRepository<ClientOrder,Long> {



}
