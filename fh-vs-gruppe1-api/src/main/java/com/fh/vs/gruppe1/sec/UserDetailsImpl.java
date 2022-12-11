package com.fh.vs.gruppe1.sec;

import com.fh.vs.gruppe1.account.Person;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*this is a bit weird but we dont have any real roles (just the two possible instances of superclass person)
        this means whatever user uses the application always only has the role its an instance of (employee or customer).
         */
        return List.of(new SimpleGrantedAuthority(person.getClass().getSimpleName()));
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        //always true because we dont expire accounts
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //always true because we dont lock persons
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //always true because we dont expire credentials
        return true;
    }

    @Override
    public boolean isEnabled() {
        //always true because every person is always enabled
        return true;
    }
}