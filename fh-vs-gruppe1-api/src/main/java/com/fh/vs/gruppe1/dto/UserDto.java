package com.fh.vs.gruppe1.dto;

import com.fh.vs.gruppe1.account.Person;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String role;

    public UserDto (Person person, String role){
        this.email = person.getEmail();
        this.name = person.getFirstName() + " " + person.getSurname();
        this.role = role;
    }
}
