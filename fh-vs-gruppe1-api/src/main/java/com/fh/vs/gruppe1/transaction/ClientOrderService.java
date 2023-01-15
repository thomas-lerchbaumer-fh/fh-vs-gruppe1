package com.fh.vs.gruppe1.transaction;

import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientOrderService {

    @Autowired
    private final ClientOrderRepository repo;

    public ClientOrder saveClientOrder(ClientOrder clientOrder) {
        return repo.save(clientOrder);
    }

    public void deleteClientOrder(Long id) {
        repo.deleteById(id);
    }

    public void updateClientOrder(Long id, int newamount) {
        ClientOrder current = repo.findById(id).get();
        current.setAmount(newamount);
        repo.save(current);
    }
}
