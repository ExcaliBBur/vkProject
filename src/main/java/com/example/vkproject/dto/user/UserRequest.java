package com.example.vkproject.dto.user;

import com.example.vkproject.model.utility.Address;
import com.example.vkproject.model.utility.Company;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    String name;

    String username;

    String email;

    Address address;

    String phone;

    String website;

    Company company;
}
