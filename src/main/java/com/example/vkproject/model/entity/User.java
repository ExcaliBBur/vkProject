package com.example.vkproject.model.entity;

import com.example.vkproject.model.utility.Address;
import com.example.vkproject.model.utility.Company;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    Long id;

    String name;

    String username;

    String email;

    Address address;

    String phone;

    String website;

    Company company;
}
