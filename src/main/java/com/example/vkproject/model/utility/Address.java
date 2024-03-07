package com.example.vkproject.model.utility;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    String street;

    String suite;

    String city;

    String zipcode;

    Geo geo;
}
