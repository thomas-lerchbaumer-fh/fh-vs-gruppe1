package com.fh.vs.gruppe1.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientOrderRepository extends CrudRepository<ClientOrder,Long> {


    Optional<ClientOrder> findByDepot(long depotId);

}

