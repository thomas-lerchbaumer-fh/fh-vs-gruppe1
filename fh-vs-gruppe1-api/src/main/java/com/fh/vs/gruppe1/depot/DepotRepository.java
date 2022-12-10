package com.fh.vs.gruppe1.depot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotRepository extends JpaRepository<Depot,Long> {

}
