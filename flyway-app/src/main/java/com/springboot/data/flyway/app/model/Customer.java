package com.springboot.data.flyway.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Customer {

    private String fullName;
    private String email;
    private String address;
    private String phone;
    private Date dob;

}
