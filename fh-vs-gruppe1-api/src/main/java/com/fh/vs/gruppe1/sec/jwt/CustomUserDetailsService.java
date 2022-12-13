package com.fh.vs.gruppe1.sec.jwt;


import com.fh.vs.gruppe1.account.Person;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import com.fh.vs.gruppe1.account.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("received un " + username);
        Optional<Person> p = employeeRepository.findByEmail(username)
                .<Person>map(Function.identity())
                .or(() -> customerRepository.findByEmail(username));

        if(!p.isPresent()){
            throw new UsernameNotFoundException("user not found");
        }

        Collection<GrantedAuthority> tmp = new ArrayList<>();
        tmp.add(new SimpleGrantedAuthority(p.get().getClass().getSimpleName()));
        log.info("correct user role " +new SimpleGrantedAuthority(p.get().getClass().getSimpleName()));
        return new User(p.get().getEmail(),p.get().getPassword(),tmp);
    }


}
