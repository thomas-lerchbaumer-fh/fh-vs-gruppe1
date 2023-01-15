package com.fh.vs.gruppe1.account.service;


import com.fh.vs.gruppe1.account.Address;
import com.fh.vs.gruppe1.account.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repo;

    public Address saveAddress(Address address) {
        return repo.save(address);
    }


}
