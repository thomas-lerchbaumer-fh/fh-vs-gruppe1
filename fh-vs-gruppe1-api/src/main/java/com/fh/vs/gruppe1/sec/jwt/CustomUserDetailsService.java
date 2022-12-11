package com.fh.vs.gruppe1.sec.jwt;


import com.fh.vs.gruppe1.account.Employee;
import com.fh.vs.gruppe1.account.Person;
import com.fh.vs.gruppe1.account.repository.CustomerRepository;
import com.fh.vs.gruppe1.account.repository.EmployeeRepository;
import com.fh.vs.gruppe1.account.repository.PersonRepository;
import com.fh.vs.gruppe1.sec.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

@Slf4j
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        log.info("received un" + username);
        Person p = employeeRepository.findByEmail(username)
                .<Person>map(Function.identity())
                .or(() -> customerRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new UserDetailsImpl(p);
    }

//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
}
